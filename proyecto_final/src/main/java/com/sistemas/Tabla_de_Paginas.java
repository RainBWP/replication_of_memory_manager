package com.sistemas;

public class Tabla_de_Paginas {
    // La tabla de páginas contiene un número de entrada (para mapear a memoria física)
    // el índice de página, que en este caso se puede obtener fácilmente por las características de un array
    // Puede hacerse con un array de enteros (este únicamente contendrá un número de entrada)
    // O con un array de un objeto de tipo EntradaTabla, que solo contendrá el número de entrada

    private final int tamano_pagina;
    private final int bits_desplazamiento;

    private final int [] tabla_de_paginas;

    public Tabla_de_Paginas(int numero_de_paginas, int tamano_pagina) {
        this.tamano_pagina = tamano_pagina;
        this.bits_desplazamiento = Integer.bitCount(tamano_pagina - 1);
        this.tabla_de_paginas = new int[numero_de_paginas];
    }

    public int getEntrada(int indice) {
        return tabla_de_paginas[indice];
    }

    public void setEntrada(int indice, int entrada) {
        tabla_de_paginas[indice] = entrada;
    }

    // Se prescindirá de una clase páginas por lo que aquí mismo
    // se hará la lógica para los bits de control desde esta clase
    public void setBitReferencia(int indice){
        int mask = 0;
        mask = 1 << (bits_desplazamiento + 4);
        tabla_de_paginas[indice] ^= mask;
    }

    public int getBitReferencia(int indice){
        return (tabla_de_paginas[indice] >> (bits_desplazamiento + 4)) & 1;
    }

    public int getBitModificacion(int indice) {
        return (tabla_de_paginas[indice] >> (bits_desplazamiento + 3)) & 1;
    }


    public int getBitPermiso(int indice) {
        return (tabla_de_paginas[indice] >> (bits_desplazamiento + 2)) & 1;
    }

    public void setBitPermiso(int indice){
        int mask = 0;
        mask = 1 << bits_desplazamiento + 2;
        tabla_de_paginas[indice] ^= mask;
    }

    public int getBitCache(int indice) {
        return (tabla_de_paginas[indice] >> (bits_desplazamiento + 1)) & 1;
    }


    public int getBitPresenteAusente(int indice) {
        return (tabla_de_paginas[indice] >> bits_desplazamiento) & 1;
    }

    public void setBitPresenteAusente(int indice){
        int mask = 0;
        mask = 1 << bits_desplazamiento;
        tabla_de_paginas[indice] ^= mask;
    }

    public int getFrame(int indice) {
        return (tabla_de_paginas[indice]) & ((1 << bits_desplazamiento)-1);
    }
}
