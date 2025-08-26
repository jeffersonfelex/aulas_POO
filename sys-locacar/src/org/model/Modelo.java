package org.model;

import java.util.Date;
import java.util.List;

public class Modelo {
    private long id;
    private String nome;
    private Date ano;
    private int qtModelo;
    private List<Veiculo> listaVeiculos;

    public Modelo(long id, String nome, Date ano, int qtModelo, List<Veiculo> listaVeiculos) {
        this.id = id;
        this.nome = nome;
        this.ano = ano;
        this.qtModelo = qtModelo;
        this.listaVeiculos = listaVeiculos;
    }
    
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
    public Date getAno() {
        return ano;
    }
    public void setAno(Date ano) {
        this.ano = ano;
    }
    public int getQtModelo() {
        return qtModelo;
    }
    public void setQtModelo(int qtModelo) {
        this.qtModelo = qtModelo;
    }
    public List<Veiculo> getListaVeiculos() {
        return listaVeiculos;
    }
    public void setListaVeiculos(List<Veiculo> listaVeiculos) {
        this.listaVeiculos = listaVeiculos;
    }
    
}
