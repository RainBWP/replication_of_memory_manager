package com.sistemas;

public class Memoria_Traductor {

    public static int obtener_pagina_de_virtual(int direccion_virtual, int tamano_de_pagina) {
        int pagina = direccion_virtual; // iniciamos pagina con la direccion virtual para recorrer los bytes
        int desplazamiento = Integer.bitCount(tamano_de_pagina - 1); // obtenemos el desplazamiento
        pagina >>= desplazamiento; // desplazamos la pagina
        return pagina;
    }

    public static int convertir_virtual_a_fisica(int direccion_virtual, 
                                                int tamano_de_marco, 
                                                int tamano_de_pagina, 
                                                Memoria_Fisica memoria_fisica) {

        int direccion_virtual_temporal = direccion_virtual; //Se guarda el valor de dirección virtual en una variable auxiliar
        int bits_de_desplazamiento = Integer.bitCount(tamano_de_pagina - 1); // obtenemos los bits para desplazamiento

        //System.out.println("Valor de bits de desplazamiento: "+bits_de_desplazamiento);

        //Se obtiene el número de páginas haciendo corrimiento de bits
        direccion_virtual_temporal >>= bits_de_desplazamiento;

        //Procederemos a obtener el valor del desplazamiento
        direccion_virtual_temporal <<= bits_de_desplazamiento;  //Se regresan los bits de desplazamiento a su lugar

        int mascara = direccion_virtual_temporal ^ Integer.MAX_VALUE; //Se crea una máscara con el número mayor para Int 11111...
        //System.out.println("Valor de mascara: "+mascara);
        int desplazamiento = direccion_virtual & mascara; //Se obtiene el valor de desplazamiento con enmascaramiento al valor original de d.v.
        //System.out.println("Valor de desplazamiento: "+desplazamiento);

        int memoria_fisica_valor = memoria_fisica.getMemoria_con_pagina(obtener_pagina_de_virtual(direccion_virtual, tamano_de_pagina)); // obtenemos el numero de marco
        Paginas pagina = new Paginas(memoria_fisica_valor, tamano_de_marco); // creamos una nueva pagina

        int virtual = pagina.getFrame() << bits_de_desplazamiento; // obtenemos la direccion virtual
        virtual |= desplazamiento; // obtenemos la direccion virtual
        System.out.println("DV: " + virtual);
        return virtual;
    }

    
}
