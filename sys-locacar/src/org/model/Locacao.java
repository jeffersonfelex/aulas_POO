package org.model;

import java.util.Date;
import java.util.List;

public class Locacao {
    private long id;
    private Date dataRetirada;
    private Date dataDevolucao;
    private float valorLocacao;
    private List <Ocorrencia> listaOcorrencias;
    private Veiculo veiculo;
    
    public Locacao(long id, Date dataRetirada, Date dataDevolucao, float valorLocacao,
            List<Ocorrencia> listaOcorrencias, Veiculo veiculo) {
        this.id = id;
        this.dataRetirada = dataRetirada;
        this.dataDevolucao = dataDevolucao;
        this.valorLocacao = valorLocacao;
        this.listaOcorrencias = listaOcorrencias;
        this.veiculo = veiculo;
    }
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public Date getDataRetirada() {
        return dataRetirada;
    }
    public void setDataRetirada(Date dataRetirada) {
        this.dataRetirada = dataRetirada;
    }
    public Date getDataDevolucao() {
        return dataDevolucao;
    }
    public void setDataDevolucao(Date dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }
    public float getValorLocacao() {
        return valorLocacao;
    }
    public void setValorLocacao(float valorLocacao) {
        this.valorLocacao = valorLocacao;
    }
    public List<Ocorrencia> getListaOcorrencias() {
        return listaOcorrencias;
    }
    public void setListaOcorrencias(List<Ocorrencia> listaOcorrencias) {
        this.listaOcorrencias = listaOcorrencias;
    }
    public Veiculo getVeiculo() {
        return veiculo;
    }
    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }

    
}
