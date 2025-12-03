package com.locacao.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "veiculos")
public class Veiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 10)
    private String placa;

    @Column(length = 20)
    private String cor;

    @Column(name = "ano_modelo")
    private Integer anoModelo;

    @Column(nullable = false, length = 20)
    @Enumerated(EnumType.STRING)
    private StatusVeiculo status = StatusVeiculo.DISPONIVEL;

    private Integer quilometragem;

    @ManyToOne
    @JoinColumn(name = "modelo_id", nullable = false)
    private Modelo modelo;

    @OneToMany(mappedBy = "veiculo", cascade = CascadeType.ALL)
    private List<Locacao> locacoes = new ArrayList<>();

    @OneToMany(mappedBy = "veiculo", cascade = CascadeType.ALL)
    private List<Manutencao> manutencoes = new ArrayList<>();

    public Veiculo() {
    }

    public Veiculo(String placa, String cor, Integer anoModelo) {
        this.placa = placa;
        this.cor = cor;
        this.anoModelo = anoModelo;
        this.quilometragem = 0; // Inicializa com 0 para evitar null pointer
        this.status = StatusVeiculo.DISPONIVEL;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public Integer getAnoModelo() {
        return anoModelo;
    }

    public void setAnoModelo(Integer anoModelo) {
        this.anoModelo = anoModelo;
    }

    public StatusVeiculo getStatus() {
        return status;
    }

    public void setStatus(StatusVeiculo status) {
        this.status = status;
    }

    public Integer getQuilometragem() {
        return quilometragem;
    }

    public void setQuilometragem(Integer quilometragem) {
        this.quilometragem = quilometragem;
    }

    public Modelo getModelo() {
        return modelo;
    }

    public void setModelo(Modelo modelo) {
        this.modelo = modelo;
    }

    public List<Locacao> getLocacoes() {
        return locacoes;
    }

    public void setLocacoes(List<Locacao> locacoes) {
        this.locacoes = locacoes;
    }

    public List<Manutencao> getManutencoes() {
        return manutencoes;
    }

    public void setManutencoes(List<Manutencao> manutencoes) {
        this.manutencoes = manutencoes;
    }
}