package com.locacao.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "contratos_locacao")
public class ContratoLocacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "data_contrato", nullable = false)
    private LocalDate dataContrato;

    @Column(name = "valor_total", precision = 10, scale = 2)
    private BigDecimal valorTotal;

    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "funcionario_id")
    private Usuario funcionario;

    @OneToMany(mappedBy = "contrato", cascade = CascadeType.ALL)
    private List<Locacao> locacoes = new ArrayList<>();

    public ContratoLocacao() {}

    public ContratoLocacao(LocalDate dataContrato, Cliente cliente) {
        this.dataContrato = dataContrato;
        this.cliente = cliente;
    }

    public void adicionarLocacao(Locacao locacao) {
        locacoes.add(locacao);
        locacao.setContrato(this);
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public LocalDate getDataContrato() { return dataContrato; }
    public void setDataContrato(LocalDate dataContrato) { this.dataContrato = dataContrato; }
    public BigDecimal getValorTotal() { return valorTotal; }
    public void setValorTotal(BigDecimal valorTotal) { this.valorTotal = valorTotal; }
    public Cliente getCliente() { return cliente; }
    public void setCliente(Cliente cliente) { this.cliente = cliente; }
    public Usuario getFuncionario() { return funcionario; }
    public void setFuncionario(Usuario funcionario) { this.funcionario = funcionario; }
    public List<Locacao> getLocacoes() { return locacoes; }
    public void setLocacoes(List<Locacao> locacoes) { this.locacoes = locacoes; }
}