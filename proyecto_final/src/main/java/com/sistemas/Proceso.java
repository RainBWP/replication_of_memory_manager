package com.sistemas;

import java.io.Serializable;

public class Proceso implements Serializable {
    private final String nombre_de_proceso;
    private final int numero_de_paginas_a_usar;
    private int direccion_virtual;
    private int [] referencias;

    public Proceso(String nombre_de_proceso,int numero_de_paginas_a_usar,int [] referencias) {
        this.nombre_de_proceso = nombre_de_proceso;
        this.direccion_virtual = 0;
        this.numero_de_paginas_a_usar = numero_de_paginas_a_usar;
        this.referencias = referencias;
    }

    public String getNombreDeProceso() {
        return nombre_de_proceso;
    }

    public int getDireccionVirtual() {
        return direccion_virtual;
    }

    public void setDireccionVirtual(int direccion_virtual) {
        this.direccion_virtual = direccion_virtual;
    }

    public int getNumeroDePaginasAUsar() {
        return numero_de_paginas_a_usar;
    }

    public int getTamanoDeReferencias() {
        return referencias.length;
    }

    public int popReferencia() {
        if (referencias.length == 0) {
            throw new IllegalStateException("No references to pop");
        }
        int lastReference = referencias[referencias.length - 1];
        referencias = java.util.Arrays.copyOf(referencias, referencias.length - 1);
        return lastReference;
        }

        public String sendLog(Paginas pagina, int direccion_fisica) {
            StringBuilder log = new StringBuilder();
            log.append("\nProceso: ").append(nombre_de_proceso).append("\n");
            log.append("Dirección virtual: ").append(direccion_virtual).append("\n");
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

        public void ejecutar(Memoria_Virtual memoriaVirtual, Memoria_Fisica memoriaFisica) {
        for (int referencia : referencias) {
            try {
                // Simula 1 segundo por referencia

                // Actualiza la dirección virtual
                // 

                // Obtiene la página correspondiente
                Paginas pagina = new Paginas(direccion_virtual+referencia, memoriaFisica.getTamanoDeMarco());

                // Maneja el fallo de página si la página no está en memoria física
                if (!pagina.getPresenteAusente()) {
                    System.out.println("Fallo de página en proceso " + nombre_de_proceso);
                    //memoriaFisica.fallo_de_pagina(pagina);
                    continue; // quitar al agregar el manejo de fallo de página
                }

                // Calcula la dirección física
                int direccion_fisica = 0;

                /* Memoria_Traductor.convertir_virtual_a_fisica(direccion_virtual+referencia,
                                                memoriaFisica.getTamanoDeMarco(), 
                                                memoriaVirtual.getTamanoDePagina(),
                                                memoriaFisica);*/

                // Muestra la información requerida
                System.out.println("Proceso: " + nombre_de_proceso);
                System.out.println("Dirección virtual: " + direccion_virtual);
                System.out.println("Dirección física: " + direccion_fisica);
                System.out.println("Número de marco: " + pagina.getFrame());
                System.out.println("Bits de control:");
                System.out.println("  Referencia: " + pagina.getReferencia());
                System.out.println("  Modificación: " + pagina.getModificacion());
                System.out.println("  Permiso: " + pagina.getPermiso());
                System.out.println("  Caché: " + pagina.getCache());
                System.out.println("  Presente/Ausente: " + pagina.getPresenteAusente());
                System.out.println("-----------------------------------");
                
                Thread.sleep(1000);


            } catch (InterruptedException e) {
                System.err.println("Thread was interrupted: " + e.getMessage());
            }
        }
    }

}
