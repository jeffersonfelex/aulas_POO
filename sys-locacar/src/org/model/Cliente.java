package org.model;

public class Cliente {
    private long id;
    private String nome;;
    private String cnh;

    public Cliente(long id, String nome, String cnh) {
        this.id = id;
        this.nome = nome;
        this.cnh = cnh;
    }

    public long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCnh() {
        return cnh;
    }

    public void setCnh(String cnh) {
        this.cnh = cnh;
    }

    

    

}
