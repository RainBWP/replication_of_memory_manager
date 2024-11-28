package com.sistemas;

public class Paginas {
    private final int memoria; // Memoria física
    private final boolean bit_de_referencia; // Bit de referencia
    private final boolean bit_de_modificacion; // Bit de modificación
    private final boolean bit_de_permiso; // Bit de permiso
    private final boolean bit_de_cache; // Bit de cache
    private final boolean bit_de_presente_ausente; // Bit de presente/ausente
    private final int frame; // Frame

    public Paginas(int memoria, int numero_de_marco) {
        this.memoria = memoria;
        
        int desplazamiento = Integer.bitCount(numero_de_marco - 1);

        this.bit_de_referencia = ((memoria >> (desplazamiento + 4)) & 1) == 1;
        this.bit_de_modificacion = ((memoria >> (desplazamiento + 3)) & 1) == 1;
        this.bit_de_permiso = ((memoria >> (desplazamiento + 2)) & 1) == 1;
        this.bit_de_cache = ((memoria >> (desplazamiento + 1)) & 1) == 1;
        this.bit_de_presente_ausente = (memoria & 1) == 1;
        this.frame = memoria & ((1 << desplazamiento) - 1);
    }

    public int getMemoria() {
        return memoria;
    }

    public boolean getReferencia() {
        return bit_de_referencia;
    }

    public boolean getModificacion() {
        return bit_de_modificacion;
    }

    public boolean getPermiso() {
        return bit_de_permiso;
    }

    public boolean getCache() {
        return bit_de_cache;
    }

    public boolean getPresenteAusente() {
        return bit_de_presente_ausente;
    }

    public int getFrame() {
        return frame;
    }

}
