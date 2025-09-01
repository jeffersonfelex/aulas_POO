package org.model;

public class Ingresso {
    private Tipo tipo;
    private double preco;
    private Cliente cliente;

    public Ingresso(Tipo tipo, double preco, Cliente cliente) {
        this.tipo = tipo;
        this.preco = preco;
        this.cliente = cliente;
    }

    public Tipo getTipo() {
        return tipo;
    }
    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }
    public double getPreco() {
        return preco;
    }
    public void setPreco(double preco) {
        this.preco = preco;
    }
    public Cliente getCliente() {
        return cliente;
    }
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public enum Tipo{
        INTEIRA,
        MEIA
    }

    @Override
    public String toString() {
        return "Ingresso tipo= " + tipo + ", preco= " + preco + ", cliente= " + cliente;
    }
    

}
