
package com.sistemas;

import java.util.LinkedList;

public class ListaDeProcesos {
    private final LinkedList<Proceso> lista;

    public ListaDeProcesos() {
        lista = new LinkedList<>();
    }

    public void push(Proceso proceso) {
        lista.addFirst(proceso);
    }

    public Proceso pull() {
        return lista.pollLast();
    }

    public boolean isEmpty() {
        return lista.isEmpty();
    }

    public int size() {
        return lista.size();
    }
}