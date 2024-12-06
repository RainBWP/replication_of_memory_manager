package com.sistemas;
import com.fasterxml.jackson.annotation.JsonProperty;

public class JsonNodo {
    private int id;

    @JsonProperty("memoria_fisica")
    private int memoriaFisica;

    @JsonProperty("memoria_virtual")
    private int memoriaVirtual;

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMemoriaFisica() {
        return memoriaFisica;
    }

    public void setMemoriaFisica(int memoriaFisica) {
        this.memoriaFisica = memoriaFisica;
    }

    public int getMemoriaVirtual() {
        return memoriaVirtual;
    }

    public void setMemoriaVirtual(int memoriaVirtual) {
        this.memoriaVirtual = memoriaVirtual;
    }
}
