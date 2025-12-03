package com.locacao.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "categorias")
public class Categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String nome;

    @Column(length = 500)
    private String descricao;

    @Column(name = "valor_diaria", precision = 10, scale = 2)
    private BigDecimal valorDiaria;

    @OneToMany(mappedBy = "categoria")
    private List<Modelo> modelos = new ArrayList<>();

    public Categoria() {}

    public Categoria(String nome, String descricao, BigDecimal valorDiaria) {
        this.nome = nome;
        this.descricao = descricao;
        this.valorDiaria = valorDiaria;
    }
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    public BigDecimal getValorDiaria() { return valorDiaria; }
    public void setValorDiaria(BigDecimal valorDiaria) { this.valorDiaria = valorDiaria; }
    public List<Modelo> getModelos() { return modelos; }
    public void setModelos(List<Modelo> modelos) { this.modelos = modelos; }
}