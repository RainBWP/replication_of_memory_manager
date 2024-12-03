package com.sistemas;

public class Proceso {
    private final String nombre_de_proceso;
    private final int numero_de_paginas_a_usar;
    private int direccion_virtual;
    private int [] referencias;

    public Proceso(String nombre_de_proceso, int numero_de_paginas_a_usar, int [] referencias) {
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

}
