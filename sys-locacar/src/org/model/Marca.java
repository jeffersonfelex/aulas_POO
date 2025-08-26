package org.model;

import java.util.List;

public class Marca {
    private long id;
    private String nome;
    private List<Modelo> listaModelos;

    public Marca() {
    }

    public Marca(long id, String nome, List<Modelo> listaModelos) {
        this.id = id;
        this.nome = nome;
        this.listaModelos = listaModelos;
    }

    // --- Getters e Setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Modelo> getListaModelos() {
        return listaModelos;
    }

    public void setListaModelos(List<Modelo> listaModelos) {
        this.listaModelos = listaModelos;
    }
}
