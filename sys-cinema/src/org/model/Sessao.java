package org.model;

import java.time.LocalDateTime;
import java.util.List;

public class Sessao {
    private LocalDateTime horario;
    private Filme filme;
    private Sala sala;
    private List<Ingresso> IngressosVendidos;
    
    public Sessao(LocalDateTime horario, Filme filme, Sala sala, List<Ingresso> ingressosVendidos) {
        this.horario = horario;
        this.filme = filme;
        this.sala = sala;
        IngressosVendidos = ingressosVendidos;
    }
    public LocalDateTime getHorario() {
        return horario;
    }
    public void setHorario(LocalDateTime horario) {
        this.horario = horario;
    }
    public Filme getFilme() {
        return filme;
    }
    public void setFilme(Filme filme) {
        this.filme = filme;
    }
    public Sala getSala() {
        return sala;
    }
    public void setSala(Sala sala) {
        this.sala = sala;
    }
    public List<Ingresso> getIngressosVendidos() {
        return IngressosVendidos;
    }
    public void setIngressosVendidos(List<Ingresso> ingressosVendidos) {
        IngressosVendidos = ingressosVendidos;
    }
    @Override
    public String toString() {
        return "Sessao horario= " + horario + ", filme= " + filme + ", sala= " + sala + ", IngressosVendidos= "
                + IngressosVendidos;
    }
    
}
