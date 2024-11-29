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
        
        int bits_de_desplazamiento = Integer.bitCount(tamano_de_pagina - 1); // obtenemos el desplazamiento
        int mascara = direccion_virtual ^ Integer.MAX_VALUE; // obtenemos la mascara
        int desplazamiento = direccion_virtual & mascara; // obtenemos el desplazamiento

        int memoria_fisica_valor = memoria_fisica.getMemoria_con_pagina(obtener_pagina_de_virtual(direccion_virtual, tamano_de_pagina)); // obtenemos el numero de marco
        Paginas pagina = new Paginas(memoria_fisica_valor, tamano_de_marco); // creamos una nueva pagina

        int virtual = pagina.getFrame() << bits_de_desplazamiento; // obtenemos la direccion virtual
        virtual |= desplazamiento; // obtenemos la direccion virtual
        return virtual;
    }

    
}
