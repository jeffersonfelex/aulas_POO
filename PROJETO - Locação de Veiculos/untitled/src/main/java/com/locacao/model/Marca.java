package com.locacao.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "marcas")
public class Marca {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nome;

    @OneToMany(mappedBy = "marca", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Modelo> modelos = new ArrayList<>();

    public Marca() {}

    public Marca(String nome) {
        this.nome = nome;
    }

    public void adicionarModelo(Modelo modelo) {
        modelos.add(modelo);
        modelo.setMarca(this);
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public List<Modelo> getModelos() { return modelos; }
    public void setModelos(List<Modelo> modelos) { this.modelos = modelos; }
}