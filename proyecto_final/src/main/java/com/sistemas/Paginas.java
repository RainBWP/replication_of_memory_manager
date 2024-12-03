package com.sistemas;

public class Paginas {
    private final int memoria; // Memoria física
    private final byte desplazamiento;

    public Paginas(int memoria, int numero_de_marco) {
        this.memoria = memoria;    
        this.desplazamiento = (byte) Integer.bitCount(numero_de_marco - 1);
    }

    public int getMemoria() {
        return memoria;
    }

    public boolean getReferencia() {
        return ((memoria >> (desplazamiento + 4)) & 1) == 1;
    }

    public boolean getModificacion() {
        return ((memoria >> (desplazamiento + 3)) & 1) == 1;
    }

    public boolean getPermiso() {
        return ((memoria >> (desplazamiento + 2)) & 1) == 1;
    }

    public boolean getCache() {
        return ((memoria >> (desplazamiento + 1)) & 1) == 1;
    }

    public boolean getPresenteAusente() {
        return ((memoria >> desplazamiento) & 1) == 1;
    }

    public int getFrame() {
        return memoria & ((1 << desplazamiento) - 1);
    }

}
