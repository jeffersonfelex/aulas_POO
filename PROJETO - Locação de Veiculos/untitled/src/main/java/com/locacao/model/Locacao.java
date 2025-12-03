package com.locacao.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "locacoes")
public class Locacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "data_retirada", nullable = false)
    private LocalDate dataRetirada;

    @Column(name = "data_devolucao_prevista", nullable = false)
    private LocalDate dataDevolucaoPrevista;

    @Column(name = "data_devolucao_efetiva")
    private LocalDate dataDevolucaoEfetiva;

    @Column(name = "valor_total", precision = 10, scale = 2)
    private BigDecimal valorTotal;

    @Column(name = "km_inicial")
    private Integer kmInicial;

    @Column(name = "km_final")
    private Integer kmFinal;

    @Column(nullable = false, length = 20)
    @Enumerated(EnumType.STRING)
    private StatusLocacao status = StatusLocacao.ATIVA;

    @ManyToOne
    @JoinColumn(name = "veiculo_id", nullable = false)
    private Veiculo veiculo;

    @ManyToOne
    @JoinColumn(name = "contrato_id")
    private ContratoLocacao contrato;

    // --- CORREÇÃO AQUI ---
    // Antes estava "com/locacao", mudamos para "locacao"
    @OneToMany(mappedBy = "locacao", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Ocorrencia> ocorrencias = new ArrayList<>();

    // --- CORREÇÃO AQUI TAMBÉM ---
    @OneToMany(mappedBy = "locacao", cascade = CascadeType.ALL)
    private List<Pagamento> pagamentos = new ArrayList<>();

    public Locacao() {}

    public Locacao(LocalDate dataRetirada, LocalDate dataDevolucaoPrevista) {
        this.dataRetirada = dataRetirada;
        this.dataDevolucaoPrevista = dataDevolucaoPrevista;
    }

    public void adicionarOcorrencia(Ocorrencia ocorrencia) {
        ocorrencias.add(ocorrencia);
        ocorrencia.setLocacao(this);
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public LocalDate getDataRetirada() { return dataRetirada; }
    public void setDataRetirada(LocalDate dataRetirada) { this.dataRetirada = dataRetirada; }
    public LocalDate getDataDevolucaoPrevista() { return dataDevolucaoPrevista; }
    public void setDataDevolucaoPrevista(LocalDate dataDevolucaoPrevista) { this.dataDevolucaoPrevista = dataDevolucaoPrevista; }
    public LocalDate getDataDevolucaoEfetiva() { return dataDevolucaoEfetiva; }
    public void setDataDevolucaoEfetiva(LocalDate dataDevolucaoEfetiva) { this.dataDevolucaoEfetiva = dataDevolucaoEfetiva; }
    public BigDecimal getValorTotal() { return valorTotal; }
    public void setValorTotal(BigDecimal valorTotal) { this.valorTotal = valorTotal; }
    public Integer getKmInicial() { return kmInicial; }
    public void setKmInicial(Integer kmInicial) { this.kmInicial = kmInicial; }
    public Integer getKmFinal() { return kmFinal; }
    public void setKmFinal(Integer kmFinal) { this.kmFinal = kmFinal; }
    public StatusLocacao getStatus() { return status; }
    public void setStatus(StatusLocacao status) { this.status = status; }
    public Veiculo getVeiculo() { return veiculo; }
    public void setVeiculo(Veiculo veiculo) { this.veiculo = veiculo; }
    public ContratoLocacao getContrato() { return contrato; }
    public void setContrato(ContratoLocacao contrato) { this.contrato = contrato; }
    public List<Ocorrencia> getOcorrencias() { return ocorrencias; }
    public void setOcorrencias(List<Ocorrencia> ocorrencias) { this.ocorrencias = ocorrencias; }
    public List<Pagamento> getPagamentos() { return pagamentos; }
    public void setPagamentos(List<Pagamento> pagamentos) { this.pagamentos = pagamentos; }
}