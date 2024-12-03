package com.sistemas;

public class Memoria_Fisica {
    private final int tamano_de_memoria; // Tamaño de la memoria física - Se Recibira 1024 bytes por ejemplo
    private final int[] memoria; // Memoria física - Se guardara en un arreglo de 1024 bytes
    private final short tamano_de_marco; // Tamaño de los marcos de la memoria física - Se Recibira 32 bytes por ejemplo
    private int porcentaje_de_uso; // Porcentaje de uso de la memoria física - Este porcentaje no es de 0-100, sino de 0-1024
    
    public Memoria_Fisica(int tamano_de_memoria, int tamano_de_marco) {
        this.tamano_de_memoria = tamano_de_memoria;
        this.memoria = new int[tamano_de_memoria];
        this.tamano_de_marco = (short) tamano_de_marco;
        for (int i = 0; i < tamano_de_memoria; i++) {
            memoria[i] = 0;
        }
    }

    public short getTamanoDeMarco() {
        return tamano_de_marco;
    }

    public byte getTamanoDeMarco_bytes() {
        return (byte) Integer.bitCount(tamano_de_marco - 1);
    }

    public int getTamanoDeMemoria() {
        return tamano_de_memoria;
    }
    
    public int getMemoria_con_pagina(int pagina) {
        return memoria[pagina];
    }

    public boolean getMemoriaLlena() {
        return porcentaje_de_uso == tamano_de_memoria;
    }

    public boolean addMemoria_con_pagina(int pagina, int dato) {
        if ((pagina < tamano_de_memoria) & (memoria[pagina] == 0)) {
            memoria[pagina] = dato;
            if (dato == 0) { // Si el dato es 0, se decrementa el porcentaje de uso ya que se esta liberando memoria
                porcentaje_de_uso--;
                
            } else {
                porcentaje_de_uso++;
            }
            return true;
        }
        else {
            return false;
        }
    }
    public boolean removeMemoria_con_pagina(int pagina) {
        if ((pagina < tamano_de_memoria) & (memoria[pagina] == 0)) {
            memoria[pagina] = 0;
            porcentaje_de_uso--;
            return true;
        }
        else {
            return false;
        }
    }

    public float getPorcentajeDeUso() { // Regresa el porcentaje 1.0 - 0.0
        return porcentaje_de_uso/tamano_de_memoria;
    }

    public void emptyMemoria() {
        for (int i = 0; i < tamano_de_memoria; i++) {
            memoria[i] = 0;
        }
        porcentaje_de_uso = 0;
    }
}
