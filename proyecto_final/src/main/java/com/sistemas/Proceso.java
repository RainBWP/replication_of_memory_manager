package com.sistemas;
import java.io.Serializable;
import java.util.ArrayDeque;
import java.util.Deque;

public class Proceso implements Serializable {
    private final String nombre_de_proceso;
    private final int numero_de_paginas_a_usar;
    private final Deque<Integer> referencias;

    public Proceso(String id, int numero_de_paginas_a_usar, int[] referencias){
        this.nombre_de_proceso = id;
        this.numero_de_paginas_a_usar = numero_de_paginas_a_usar;
        this.referencias = new ArrayDeque<>();

        for(int referencia : referencias){
            this.referencias.addLast(referencia);
        }
    }

    public int popReferencia() {
        if(referencias.isEmpty()){
            throw new IllegalStateException("No hay más referencias");
        }
        return referencias.removeFirst();
    }

        public String sendLog(Paginas pagina, int direccion_fisica) {
            StringBuilder log = new StringBuilder();
            log.append("\nProceso: ").append(nombre_de_proceso).append("\n");
            log.append("Dirección virtual: ").append(0).append("\n");
            log.append("Dirección física: ").append(direccion_fisica).append("\n");
            log.append("Número de marco: ").append(pagina.getFrame()).append("\n");
            log.append("Bits de control:\n");
            log.append("\tReferencia: ").append(pagina.getReferencia()).append("\n");
            log.append("\tModificación: ").append(pagina.getModificacion()).append("\n");
            log.append("\tPermiso: ").append(pagina.getPermiso()).append("\n");
            log.append("\tCaché: ").append(pagina.getCache()).append("\n");
            log.append("\tPresente/Ausente: ").append(pagina.getPresenteAusente()).append("\n");
            log.append("-----------------------------------\n");
            return log.toString();
        }

   public void ejecutar(Tabla_de_Paginas tabla, Memoria_Fisica memoria_fisica, Memoria_Virtual memoria_virtual){
       // Suponiendo que la tabla de páginas ya venga cargada
       try{
           while(!referencias.isEmpty()){
               int referencia_pag_actual = popReferencia();

               // Verificando el bit de presente/ausente
               // Se llama a la función para fallo de página
               if(tabla.getBitPresenteAusente(referencia_pag_actual) == 0){
                   memoria_fisica.falloDePagina(tabla, memoria_virtual, referencia_pag_actual);
                   //Se tiene que obtener el número de marco para actualizar el referenciado y se tiene que añadir la página a memoria virtual
                   //memoria_fisica.actualizarReferenciado();
                   Thread.sleep(1000);
               }

               int marco_temporal = memoria_virtual.getMarcoFromPagina(referencia_pag_actual);
               memoria_fisica.actualizarReferenciado(marco_temporal);
               Thread.sleep(1000);
               memoria_fisica.actualizarRegistrosAging();
           }
       }
       catch(IllegalStateException e){
           System.out.println("Ejecución del proceso finalizada");
       } catch (InterruptedException e) {
           System.err.println("Ha ocurrido un error en la ejecución");
       }
   }

//    private final String nombre_de_proceso;
//    private final int numero_de_paginas_a_usar;
//    private int direccion_virtual;
//    private int [] referencias;
//
//    public Proceso(String nombre_de_proceso,int numero_de_paginas_a_usar,int [] referencias) {
//        this.nombre_de_proceso = nombre_de_proceso;
//        this.direccion_virtual = 0;
//        this.numero_de_paginas_a_usar = numero_de_paginas_a_usar;
//        this.referencias = referencias;
//    }
//
    public String getNombreDeProceso() {
        return nombre_de_proceso;
    }
//
//    public int getDireccionVirtual() {
//        return direccion_virtual;
//    }
//
//    public void setDireccionVirtual(int direccion_virtual) {
//        this.direccion_virtual = direccion_virtual;
//    }
//
//    public int getNumeroDePaginasAUsar() {
//        return numero_de_paginas_a_usar;
//    }
//
//    public int getTamanoDeReferencias() {
//        return referencias.length;
//    }
//
//    public int popReferencia() {
//        if (referencias.length == 0) {
//            throw new IllegalStateException("No references to pop");
//        }
//        int lastReference = referencias[referencias.length - 1];
//        referencias = java.util.Arrays.copyOf(referencias, referencias.length - 1);
//        return lastReference;
//    }
//
//    public void ejecutar(Memoria_Virtual memoria_virtual, Memoria_Fisica memoria_fisica, Tabla_de_Paginas tabla){
//        // En caso de que la memoria física esté vacía
//        if(memoria_fisica.getPorcentajeDeUso() == 0.f){
//            int pagina_actual = popReferencia();
//            Paginas actual = new Paginas(memoria_virtual.getTamanoDePagina(),
//                    memoria_fisica.getTamanoDeMemoria(),
//                    0);
//            memoria_virtual.addMemoria_con_pagina(pagina_actual, 1);    //Se pasa como dato el número de marco 1
//                                                                            // (porque la función reconoce al 0 como dato nulo)
//
//            //Al estar vacía la memoria física entonces se provocará fallo de página
//
//
//        }
//
//        while (getTamanoDeReferencias() != 0){
//            // Se va a hacer la simulación de ejecución de cada proceso
//
//        }
//    }
//
//    public void ejecutar(Memoria_Virtual memoriaVirtual, Memoria_Fisica memoriaFisica) {
//        for (int referencia : referencias) {
//            try {
//                // Simula 1 segundo por referencia
//
//                // Actualiza la dirección virtual
//                //
//
//                // Obtiene la página correspondiente
//                //Paginas pagina = new Paginas(direccion_virtual+referencia, memoriaFisica.getTamanoDeMarco());
//
//                // Maneja el fallo de página si la página no está en memoria física
//                if (!pagina.getPresenteAusente()) {
//                    System.out.println("Fallo de página en proceso " + nombre_de_proceso);
//                    //memoriaFisica.fallo_de_pagina(pagina);
//                    continue; // quitar al agregar el manejo de fallo de página
//                }
//
//                // Calcula la dirección física
//                int direccion_fisica = 0;
//
//                /* Memoria_Traductor.convertir_virtual_a_fisica(direccion_virtual+referencia,
//                                                memoriaFisica.getTamanoDeMarco(),
//                                                memoriaVirtual.getTamanoDePagina(),
//                                                memoriaFisica);*/
//
//                // Muestra la información requerida
//                System.out.println("Proceso: " + nombre_de_proceso);
//                System.out.println("Dirección virtual: " + direccion_virtual);
//                System.out.println("Dirección física: " + direccion_fisica);
//                System.out.println("Número de marco: " + pagina.getFrame());
//                System.out.println("Bits de control:");
//                System.out.println("  Referencia: " + pagina.getReferencia());
//                System.out.println("  Modificación: " + pagina.getModificacion());
//                System.out.println("  Permiso: " + pagina.getPermiso());
//                System.out.println("  Caché: " + pagina.getCache());
//                System.out.println("  Presente/Ausente: " + pagina.getPresenteAusente());
//                System.out.println("-----------------------------------");
//
//                Thread.sleep(1000);
//
//
//            } catch (InterruptedException e) {
//                System.err.println("Thread was interrupted: " + e.getMessage());
//            }
//        }
//    }

}
