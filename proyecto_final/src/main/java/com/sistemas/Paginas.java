package com.sistemas;

public class Paginas {
    private int entrada_tabla_pagina; // Para mapear número de marco
    private final byte bits_desplazamiento;
    private final int tamano_de_pagina;
//    private int direccion_virtual;

/*
    public Paginas(int entrada, int tamano_de_pagina, int numero_marcos) {
        this.entrada_tabla_pagina = entrada;
        this.tamano_de_pagina = tamano_de_pagina;
        this.bits_desplazamiento = (byte) Integer.bitCount(numero_marcos - 1);
        System.out.println("Valor de bits de desplazamiento para obtener número de marco: " + bits_desplazamiento);
    }
 */

    // Función para crear una página nueva con el bit de presente/ausente encendido
    public Paginas(int tamano_de_pagina, int total_marcos, int numero_de_marco){
        this.tamano_de_pagina = tamano_de_pagina;
        this.entrada_tabla_pagina = numero_de_marco;
        this.bits_desplazamiento = (byte) Integer.bitCount(total_marcos - 1);
        //entrada_tabla_pagina
    }

//    public void setDireccion_virtual(int numero_de_pagina) {
//        int direccion_virtual_temporal = 0;
//        int bits_desplazamiento = Integer.bitCount(tamano_de_pagina - 1);
//        numero_de_pagina <<= bits_desplazamiento;
//        direccion_virtual_temporal ^= numero_de_pagina;
//        //Se tomará como desplazamiento al 0
//        //Aquí iría la obtención de máscara y el enmascaramiento para obtener una dirección válida
//        this.direccion_virtual = direccion_virtual_temporal;
//    }

//    public int getDireccion_virtual(){
//        return this.direccion_virtual;
//    }


    // Getters de la entrada
    public int getEntrada() {
        return entrada_tabla_pagina;
    }

    public boolean getReferencia() {
        return ((entrada_tabla_pagina >> (bits_desplazamiento + 4)) & 1) == 1;
    }

    public boolean getModificacion() {
        return ((entrada_tabla_pagina >> (bits_desplazamiento + 3)) & 1) == 1;
    }

    public boolean getPermiso() {
        return ((entrada_tabla_pagina >> (bits_desplazamiento + 2)) & 1) == 1;
    }

    public boolean getCache() {
        return ((entrada_tabla_pagina >> (bits_desplazamiento + 1)) & 1) == 1;
    }

    public boolean getPresenteAusente() {
        return ((entrada_tabla_pagina >> bits_desplazamiento) & 1) == 1;
    }

    public int getFrame() {
        return entrada_tabla_pagina & ((1 << bits_desplazamiento)-1);
    }

    // Setter para el número de marco
    public void setFrame(int marco){
        int mask = 0;

    }

    // Setters de los bits de control de la entrada
    public void setPresenteAusente(){
        int mask = 0;
        mask = 1 << bits_desplazamiento;
        entrada_tabla_pagina ^= mask;
    }

    public void setReferencia(){
        int mask = 0;
        mask = 1 << (bits_desplazamiento + 4);
        entrada_tabla_pagina ^= mask;
    }


}
