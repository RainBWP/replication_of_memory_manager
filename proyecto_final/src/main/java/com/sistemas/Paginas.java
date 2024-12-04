package com.sistemas;

public class Paginas {
    private final int entrada_tabla_pagina; // Memoria física
    private final byte bits_desplazamiento;

    public Paginas(int entrada, int numero_marcos) {
        this.entrada_tabla_pagina = entrada;
        this.bits_desplazamiento = (byte) Integer.bitCount(numero_marcos - 1);
//        System.out.println("Valor de bits de desplazamiento para obtener número de marco: " + bits_desplazamiento);
    }

    public int getEntrada() {
        return entrada_tabla_pagina;
    }

    public boolean getReferencia() {
        return ((entrada_tabla_pagina >> (bits_desplazamiento + 4)) & 1) == 1;
    }

    public boolean getModificacion() {
        return ((entrada_tabla_pagina >> (bits_desplazamiento + 3)) & 1) == 1;
    }

    public boolean getPermiso() {
        return ((entrada_tabla_pagina >> (bits_desplazamiento + 2)) & 1) == 1;
    }

    public boolean getCache() {
        return ((entrada_tabla_pagina >> (bits_desplazamiento + 1)) & 1) == 1;
    }

    public boolean getPresenteAusente() {
        return ((entrada_tabla_pagina >> bits_desplazamiento) & 1) == 1;
    }

    public int getFrame() {
        return entrada_tabla_pagina & ((1 << bits_desplazamiento)-1);
    }

}
