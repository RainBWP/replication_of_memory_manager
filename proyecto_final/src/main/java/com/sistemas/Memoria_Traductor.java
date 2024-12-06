package com.sistemas;

public class Memoria_Traductor {

    public static int obtener_pagina_de_virtual(int direccion_virtual, int tamano_de_pagina) {
        int pagina = direccion_virtual; // iniciamos pagina con la direccion virtual para recorrer los bytes
        int desplazamiento = Integer.bitCount(tamano_de_pagina - 1); // obtenemos el desplazamiento
        pagina >>= desplazamiento; // desplazamos la pagina
        return pagina;
    }

    public static int convertir_virtual_a_fisica(int direccion_virtual,
                                                int tamano_de_pagina,
                                                Tabla_de_Paginas Tabla) {

        int direccion_fisica = 0;
        int numero_de_marco = 0;    //Parte importante de una dirección física

        int numero_de_pagina_indice = obtener_pagina_de_virtual(direccion_virtual, tamano_de_pagina);

        // En caso de que no esté encendido el bit de presente/ausente entonces se devuelve un -1 (error)
//        if(!Tabla.getPagina(numero_de_pagina_indice).getPresenteAusente()){
//            return -1;
//        }

        //numero_de_marco = Tabla.getPagina(numero_de_pagina_indice).getFrame();

        int bits_de_desplazamiento = Integer.bitCount(tamano_de_pagina - 1); // obtenemos los bits para desplazamiento
//        System.out.println("Valor de bits de desplazamiento: "+bits_de_desplazamiento);

        /* Procederemos a obtener el valor del desplazamiento */

        int direccion_virtual_temporal; //Se guarda el valor de la página en una variable auxiliar
        direccion_virtual_temporal = numero_de_pagina_indice;
        direccion_virtual_temporal <<= bits_de_desplazamiento;  //Se regresan los bits de desplazamiento a su lugar
        int mascara = direccion_virtual_temporal ^ Integer.MAX_VALUE; //Se crea una máscara con el número mayor para Int 11111...
        //System.out.println("Valor de mascara: "+mascara);

        int desplazamiento = direccion_virtual & mascara; //Se obtiene el valor de desplazamiento con enmascaramiento al valor original de d.v.

        direccion_fisica = numero_de_marco << bits_de_desplazamiento;
        direccion_fisica |= desplazamiento;

        return direccion_fisica;
    }

    public static void crearEntrada(Tabla_de_Paginas tabla, int num_pagina, int numero_marco){
        int entrada = numero_marco;
        tabla.setEntrada(num_pagina, entrada);
        // Al crearse una entrada común se enciende el bit de:
        // - Permiso lectura/escritura
        tabla.setBitPermiso(num_pagina);
    }


}
