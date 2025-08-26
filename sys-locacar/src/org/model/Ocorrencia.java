package org.model;

import java.util.Date;

public class Ocorrencia {
    private long id;
    private String descricao;
    private Date data;
    private Veiculo veiculo;
    private Tipo tipo;

    public enum Tipo {
        multa,
        acidente,
        avaria,
        atraso,
        outro
    }

    public Ocorrencia() {
    }

    public Ocorrencia(long id, String descricao, Date data, Veiculo veiculo, Tipo tipo) {
        this.id = id;
        this.descricao = descricao;
        this.data = data;
        this.veiculo = veiculo;
        this.tipo = tipo;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }
}
