package com.sistemas;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class MainTest {
    @Test
    public void testConvertirVirtualAFisica() {
        int tamano_de_pagina = 64;
        int numero_de_paginas = 4;
        int numero_de_marcos = 8;

        Tabla_de_Paginas entries =  new Tabla_de_Paginas(numero_de_paginas);
        Paginas pag1 = new Paginas(0, numero_de_marcos);
        Paginas pag2 = new Paginas(0, numero_de_marcos);
        Paginas pag3 = new Paginas(0, numero_de_marcos);
        Paginas pag4 = new Paginas(159, numero_de_marcos);

        entries.setPagina(0, pag1);
        entries.setPagina(1, pag2);
        entries.setPagina(2, pag3);
        entries.setPagina(3, pag4);

        assertEquals(448 ,Memoria_Traductor.convertir_virtual_a_fisica(192, tamano_de_pagina, entries));
    }
}