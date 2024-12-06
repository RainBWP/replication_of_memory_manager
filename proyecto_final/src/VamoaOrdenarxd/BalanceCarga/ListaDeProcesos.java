package BalanceCarga;
import java.util.LinkedList;
//esta lista sera del NododelSD, aqui se encolaran los procesos previos a procesar

public class ListaDeProcesos {
    private final LinkedList<Proceso> lista;

    public ListaDeProcesos() {
        lista = new LinkedList<>();
    }

    public void push(Proceso proceso) {
        lista.addLast(proceso);
    }

    public Proceso pull() {
        return lista.pollFirst();
    }

    public boolean isEmpty() {
        return lista.isEmpty();
    }

    public int size() {
        return lista.size();
    }
}