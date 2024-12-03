package com.sistemas;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class MainTest {
    @Test
    public void testObtenerPaginaDeVirtual() {
        int pagina = Memoria_Traductor.obtener_pagina_de_virtual(185, 32);
        assertEquals(5, pagina);
    }
    
    @Test
    public void testConvertirVirtualAFisica() {
        Memoria_Fisica memoria_fisica = new Memoria_Fisica(1024);
        memoria_fisica.addMemoria_con_pagina(5, 168);
        int direccion_fisica = Memoria_Traductor.convertir_virtual_a_fisica(185, 
                                                8, 
                                                32, 
                                                memoria_fisica);
        
        assertEquals(168, memoria_fisica.getMemoria_con_pagina(5));
        assertEquals(25, direccion_fisica);
    }

    @Test
    public void testPaginas() {
        Paginas pagina = new Paginas(168, 8);
        assertEquals(168, pagina.getMemoria());
        assertEquals(true, pagina.getReferencia());
        assertEquals(false, pagina.getModificacion());
        assertEquals(true, pagina.getPermiso());
        assertEquals(false, pagina.getCache());
        assertEquals(true, pagina.getPresenteAusente());
        assertEquals(0, pagina.getFrame());

        pagina = new Paginas(235, 8);
        assertEquals(235, pagina.getMemoria());
        assertEquals(true, pagina.getReferencia());
        assertEquals(true, pagina.getModificacion());
        assertEquals(true, pagina.getPermiso());
        assertEquals(false, pagina.getCache());
        assertEquals(true, pagina.getPresenteAusente());
        assertEquals(3, pagina.getFrame());
    }
}