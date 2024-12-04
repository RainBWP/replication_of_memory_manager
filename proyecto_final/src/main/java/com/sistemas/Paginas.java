package com.sistemas;

public class Paginas {
    private final int entrada_tabla_pagina; // Memoria física
    private final byte desplazamiento;

    public Paginas(int entrada, int desplazamiento) {
        this.entrada_tabla_pagina = entrada;
        this.desplazamiento = (byte) desplazamiento;
    }

    public int getEntrada() {
        return entrada_tabla_pagina;
    }

    public boolean getReferencia() {
        return ((entrada_tabla_pagina >> (desplazamiento + 4)) & 1) == 1;
    }

    public boolean getModificacion() {
        return ((entrada_tabla_pagina >> (desplazamiento + 3)) & 1) == 1;
    }

    public boolean getPermiso() {
        return ((entrada_tabla_pagina >> (desplazamiento + 2)) & 1) == 1;
    }

    public boolean getCache() {
        return ((entrada_tabla_pagina >> (desplazamiento + 1)) & 1) == 1;
    }

    public boolean getPresenteAusente() {
        return ((entrada_tabla_pagina >> desplazamiento) & 1) == 1;
    }

    public int getFrame() {
        return entrada_tabla_pagina & ((1 << desplazamiento) - 1);
    }

}
