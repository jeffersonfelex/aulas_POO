package org.model;

import java.util.List;


public class Cinema {
    private String nome;
    private String endereco;
    private List<Sala> salas;
    
    public Cinema(String nome, String endereco, List<Sala> salas) {
        this.nome = nome;
        this.endereco = endereco;
        this.salas = salas;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public List<Sala> getSalas() {
        return salas;
    }

    public void setSalas(List<Sala> salas) {
        this.salas = salas;
    }

    @Override
    public String toString() {
        return "Cinema nome= " + nome + ", endereco= " + endereco + ", salas= " + salas;
    }

    
    
}
