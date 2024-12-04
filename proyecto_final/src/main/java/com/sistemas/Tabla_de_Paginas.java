package com.sistemas;

public class Tabla_de_Paginas {
    private final Paginas [] tabla_de_paginas;

    public Tabla_de_Paginas(int numero_de_paginas) {
        tabla_de_paginas = new Paginas[numero_de_paginas];

        //No es conveniente inicializarlo de esta forma
//        for (int i = 0; i < numero_de_paginas; i++) {
//            tabla_de_paginas[i] = new Paginas(0, Integer.bitCount(tam_pag - 1));
//        }
    }

    public Paginas getPagina(int indice) {
        return tabla_de_paginas[indice];
    }

    public void setPagina(int indice, Paginas pagina) {
        tabla_de_paginas[indice] = pagina;
    }

    public int getTamano() {
        return tabla_de_paginas.length;
    }

}
