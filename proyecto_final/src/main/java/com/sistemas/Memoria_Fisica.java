package com.sistemas;

public class Memoria_Fisica {
    private final int tamano_de_memoria; // Tamaño de la memoria física - Se Recibira 1024 bytes por ejemplo
    private int[] memoria; // Memoria física - Se guardara en un arreglo de 1024 bytes
    private boolean memoria_llena; // Memoria física llena

    public Memoria_Fisica(int tamano_de_memoria) {
        this.tamano_de_memoria = tamano_de_memoria;
        this.memoria = new int[tamano_de_memoria];
        for (int i = 0; i < tamano_de_memoria; i++) {
            memoria[i] = 0;
        }
        this.memoria_llena = false;
    }

    public int getTamanoDeMemoria() {
        return tamano_de_memoria;
    }
    
    public int[] getMemoria() {
        return memoria;
    }

    public boolean getMemoriaLlena() {
        return memoria_llena;
    }

    public void setMemoriaLlena(boolean memoria_llena) {
        this.memoria_llena = memoria_llena;
    }

    public void setMemoria(int[] memoria) {
        this.memoria = memoria;
    }

    public boolean addMemoria(int direccion, int dato) {
        if (direccion < tamano_de_memoria || memoria[direccion] == 0) {
            memoria[direccion] = dato;
            return true;
        }
        else {
            return false;
        }
    }
}
