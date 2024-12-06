package com.sistemas;

public class Memoria_Virtual {
    // La memoria virtual se compone de páginas que contienen el número de marco al que referencian
    // Comparte características con la memoria física, solo que esta posee una capacidad mayor

    private final int tamano_de_pagina;
    private final int numero_de_paginas;
    private final int tamano_en_bytes;
    private final int[] memoria;  // Un arreglo de números que contendrá el número de marco en el que se refiera
    private int paginas_usadas = 0;

    /** Constructor de la memoria virtual
     * @param tamano_de_pagina Tamaño de página (en bytes)
     * @param numero_de_paginas Número de páginas que tendrá la memoria virtual
     * */

    public Memoria_Virtual(int tamano_de_pagina, int numero_de_paginas){
        this.tamano_de_pagina = tamano_de_pagina;
        this.numero_de_paginas = numero_de_paginas;

        this.memoria = new int[numero_de_paginas];
        //Se inicializa con 0 (aunque sería mejor inicializarlo con -1)
        for (int i = 0; i < numero_de_paginas; i++)
            memoria[i] = 0;
        this.tamano_en_bytes = numero_de_paginas*tamano_de_pagina;
    }

    /**@param numero_de_pagina Índice para mapear el número de marco*/
    public int getMarcoFromPagina(int numero_de_pagina){
        return memoria[numero_de_pagina];
    }

    public int getTamanoDePagina() {
        return tamano_de_pagina;
    }

    // Función para obtener el contenido de la página
    public int getMemoria_con_pagina(int numero_de_pagina) {
        return memoria[numero_de_pagina];
    }

    public boolean getMemoriaLlena() {
        return paginas_usadas == numero_de_paginas;
    }

    public boolean addMemoria_con_pagina(int pagina, int dato) {
        if ((pagina < numero_de_paginas) && (memoria[pagina] == 0)) {
            memoria[pagina] = dato;
            if (dato == 0) { // Si el dato es 0, se decrementa el porcentaje de uso ya que se esta liberando memoria
                paginas_usadas--;
            } else {
                paginas_usadas++;
            }
            return true;
        } else {
            return false;
        }
    }

    public boolean removeMemoria_con_pagina(int pagina) {
        if ((pagina < numero_de_paginas) && (memoria[pagina] == 0)) {
            memoria[pagina] = 0;
            paginas_usadas--;
            return true;
        } else {
            return false;
        }
    }

    public float getPorcentajeDeUso() { // Regresa el porcentaje 1.0 - 0.0
        return (float) paginas_usadas / numero_de_paginas;
    }

    public int getMemoria(int numero_de_pagina) {
        return memoria[numero_de_pagina] - 1;
    }

    public void emptyMemoria() {
        for (int i = 0; i < numero_de_paginas; i++) {
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
