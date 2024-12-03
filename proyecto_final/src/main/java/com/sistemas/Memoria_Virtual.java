package com.sistemas;

public class Memoria_Virtual {
    private final int tamano_de_memoria; // Tamaño de la memoria virtual - Se Recibira 1024 bytes por ejemplo   
    private final int[] memoria; // Memoria virtual - Se guardara en un arreglo de 1024 bytes
    private final short tamano_de_pagina; // Tamaño de los marcos de la memoria física - Se Recibira 32 bytes por ejemplo
    private int porcentaje_de_uso; // Porcentaje de uso de la memoria virtual - Este porcentaje no es de 0-100, sino de 0-1024

    public Memoria_Virtual(int tamano_de_memoria, int tamano_de_pagina) {
        this.tamano_de_memoria = tamano_de_memoria;
        this.memoria = new int[tamano_de_memoria];
        this.tamano_de_pagina = (short) tamano_de_pagina;
        for (int i = 0; i < tamano_de_memoria; i++) {
            memoria[i] = 0;
        }
    }

    public short getTamanoDePagina() {
        return tamano_de_pagina;
    }

    public byte getTamanoDePagina_bytes() {
        return (byte) Integer.bitCount(tamano_de_pagina - 1);
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
        } else {
            return false;
        }
    }

    public boolean removeMemoria_con_pagina(int pagina) {
        if ((pagina < tamano_de_memoria) & (memoria[pagina] == 0)) {
            memoria[pagina] = 0;
            porcentaje_de_uso--;
            return true;
        } else {
            return false;
        }
    }

    public float getPorcentajeDeUso() { // Regresa el porcentaje 1.0 - 0.0
        return (float) porcentaje_de_uso / tamano_de_memoria;
    }

    public int getMemoria(int direccion_virtual) {
        return memoria[direccion_virtual];
    }

    public void emptyMemoria() {
        for (int i = 0; i < tamano_de_memoria; i++) {
            memoria[i] = 0;
        }
    }

    public int obtener_pagina_de_virtual(int direccion_virtual) {
        int pagina = direccion_virtual; // iniciamos pagina con la direccion virtual para recorrer los bytes
        int desplazamiento = tamano_de_pagina - 1; // obtenemos el desplazamiento
        pagina >>= desplazamiento; // desplazamos la pagina
        return pagina;
    }


}
