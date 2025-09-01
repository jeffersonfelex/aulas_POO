package org.model;

public class Cliente {
    private String nome;
    private String cpf;
    private int comprovante;
    
    public Cliente(String nome, String cpf, int comprovante) {
        this.nome = nome;
        this.cpf = cpf;
        this.comprovante = comprovante;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public int getComprovante() {
        return comprovante;
    }

    public void setComprovante(int comprovante) {
        this.comprovante = comprovante;
    }

    @Override
    public String toString() {
        return "Cliente nome= " + nome + ", cpf= " + cpf + ", comprovante= " + comprovante;
    }

    
    
}
