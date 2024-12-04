package com.sistemas;

public class Nodos {
    private final byte id;
    private final Memoria_Fisica memoria_fisica;
    private final Memoria_Virtual memoria_virtual;
    // private final Tabla_de_Paginas tabla_de_paginas;

    private final ListaDeProcesos lista_de_procesos = new ListaDeProcesos();

    public Nodos(byte id, 
    int Tamano_de_Memoria_Fisica, 
    int Tamano_de_Memoria_Virtual, 
    int Tamano_de_Pagina) {
        this.id = id; // id del nodo
        this.memoria_virtual = new Memoria_Virtual(Tamano_de_Memoria_Virtual, Tamano_de_Pagina); // Creamos la Memoria Virtual del Nodo

        int desplazamiento = Tamano_de_Memoria_Fisica - Tamano_de_Pagina; // Calculamos el desplazamiento para crear el marco

        this.memoria_fisica = new Memoria_Fisica(Tamano_de_Memoria_Fisica, Tamano_de_Memoria_Fisica-desplazamiento); // Creamos la Memoria Fisica del Nodo
        // this.tabla_de_paginas = new Tabla_de_Paginas(Tamano_de_Memoria_Virtual, Tamano_de_Pagina);
    }

//    @Override
//    public String toString(){
//        return "Información del nodo " + id
//                + "Tamaño de memoria física: " + memoria_fisica.getTamanoDeMemoria()
//                + "Tamaño de página: " + memoria_fisica.getTamanoDeMemoria()
//                + ;
//    }

    public byte getId() {
        return id;
    }

    public Memoria_Fisica getMemoriaFisica() {
        return memoria_fisica;
    }

    public Memoria_Virtual getMemoriaVirtual() {
        return memoria_virtual;
    }

    public ListaDeProcesos getListaDeProcesos() {
        return lista_de_procesos;
    }

    public Proceso popProceso() {
        return lista_de_procesos.pull();
    }

    public void pushProceso(Proceso proceso) {
        lista_de_procesos.push(proceso);
    }

    public boolean isEmpty() {
        return lista_de_procesos.isEmpty();
    }

    public int size() {
        return lista_de_procesos.size();
    }

    public float getPorcentajeDeUso() {
        return memoria_fisica.getPorcentajeDeUso();
    }
}
