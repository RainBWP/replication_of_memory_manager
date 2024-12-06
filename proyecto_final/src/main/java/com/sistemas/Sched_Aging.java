package com.sistemas;

import java.util.concurrent.locks.ReentrantLock; // Funciones para activar concurrencia
// Este algoritmo de reemplazo: de envejecimiento usará una longitud de 8 bits

// Faltará hacer que esto se ejecute de forma concurrente

public class Sched_Aging {
    private final int MAX_BITS_REG = 8;     // Se simulará un unsigned byte para la cantidad de registros que llevará a cabo el algoritmo
    private final int numero_paginas;
    private short[] registros;
    private final ReentrantLock lock = new ReentrantLock(); // Gestor de concurrencia

    public Sched_Aging(int numero_paginas){
        this.numero_paginas = numero_paginas;
        registros = new short[numero_paginas];

        // Se inicializan los registros con 0
        for (int i = 0; i < numero_paginas; i++) {
            registros[i] = 0;
        }
    }

    public void actualizarRegistrosAging(){
        lock.lock();
        try{
            //Se deberá ejecutar cada segundo
            for (int i = 0; i < numero_paginas; i++) {
                registros[i] >>= 1; //Corrimiento de bits a la derecha
            }
        } finally {
            lock.unlock();
        }
    }

    public void actualizarReferenciado(int pagina_a_actualizar){
        //Se deberá ejecutar cada segundo
        lock.lock();
        try{
            registros[pagina_a_actualizar] |= (1 << MAX_BITS_REG -1);   //Se enciende el bit más significativo en caso de que se referencie
        }  finally {
            lock.unlock();
        }
    }

    public int seleccionarReemplazable(){
        lock.lock();
        try {
            int indiceReemplazable = 0; //Índice de la página a reemplazar
            short menor = registros[0];

            // Comparar cada uno de los valores para obtener el menor
            for (int i = 1; i < numero_paginas; i++) {
                if (registros[i] < menor) {
                    menor = registros[i];
                    indiceReemplazable = i;
                }
            }
            return indiceReemplazable;
        } finally {
            lock.unlock();
        }
    }

}
