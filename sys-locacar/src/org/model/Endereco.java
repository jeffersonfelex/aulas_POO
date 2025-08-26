package org.model;

public class Endereco {
    private long id;
    private String cep;
    private String logradouro;
    private String complemento;
    private String numero;
    private String refencia;

    public Endereco(long id, String cep, String lograduro, String complemento, String numero, String refencia) {
        this.id = id;
        this.cep = cep;
        this.logradouro = lograduro;
        this.complemento = complemento;
        this.numero = numero;
        this.refencia = refencia;
    }
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getCep() {
        return cep;
    }
    public void setCep(String cep) {
        this.cep = cep;
    }
    public String getLograduro() {
        return logradouro;
    }
    public void setLograduro(String lograduro) {
        this.logradouro = lograduro;
    }
    public String getComplemento() {
        return complemento;
    }
    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }
    public String getNumero() {
        return numero;
    }
    public void setNumero(String numero) {
        this.numero = numero;
    }
    public String getRefencia() {
        return refencia;
    }
    public void setRefencia(String refencia) {
        this.refencia = refencia;
    }

    
}
