package org.model;

import java.util.Date;
import java.util.List;

public class ContratoLocacao {
    private long id;
    private Date dataContrato;
    private float valorCaucao;
    private statusContrato status;
    private List<Locacao> listaLocacao;
    private Funcionario funcionario;
    private Cliente cliente;


    

    
    public long getId() {
        return id;
    }





    public void setId(long id) {
        this.id = id;
    }





    public Date getDataContrato() {
        return dataContrato;
    }





    public void setDataContrato(Date dataContrato) {
        this.dataContrato = dataContrato;
    }





    public float getValorCaucao() {
        return valorCaucao;
    }





    public void setValorCaucao(float valorCaucao) {
        this.valorCaucao = valorCaucao;
    }





    public statusContrato getStatus() {
        return status;
    }





    public void setStatus(statusContrato status) {
        this.status = status;
    }





    public List<Locacao> getListaLocacao() {
        return listaLocacao;
    }





    public void setListaLocacao(List<Locacao> listaLocacao) {
        this.listaLocacao = listaLocacao;
    }





    public Funcionario getFuncionario() {
        return funcionario;
    }





    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }





    public Cliente getCliente() {
        return cliente;
    }





    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }





    public ContratoLocacao(long id, Date dataContrato, float valorCaucao, statusContrato status,
            List<Locacao> listaLocacao, Funcionario funcionario, Cliente cliente) {
        this.id = id;
        this.dataContrato = dataContrato;
        this.valorCaucao = valorCaucao;
        this.status = status;
        this.listaLocacao = listaLocacao;
        this.funcionario = funcionario;
        this.cliente = cliente;
    }





    public enum statusContrato{
        ativo,
        encerrado,
        pendente
    }
}
