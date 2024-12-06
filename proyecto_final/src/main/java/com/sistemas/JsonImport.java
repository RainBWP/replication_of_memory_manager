package com.sistemas;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class JsonImport {
    @JsonProperty("tamano_de_pagina")
    private int tamanoDePagina;

    @JsonProperty("nodos")
    private List<JsonNodo> nodos;

    // Getters y Setters
    public int getTamanoDePagina() {
        return tamanoDePagina;
    }

    public void setTamanoDePagina(int tamanoDePagina) {
        this.tamanoDePagina = tamanoDePagina;
    }

    public List<JsonNodo> getNodos() {
        return nodos;
    }

    public void setNodos(List<JsonNodo> nodos) {
        this.nodos = nodos;
    }

    public int sizeNodos(){
        return nodos.size();
    }
}

