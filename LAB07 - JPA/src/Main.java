package com.gestao.projetos.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "perfis_acesso")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "colaborador")
public class PerfilAcesso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 50)
    private String login;

    @Column(nullable = false, length = 255)
    private String senha;

    @Enumerated(EnumType.STRING)
    @Column(name = "nivel_acesso", nullable = false, length = 20)
    private NivelAcesso nivelAcesso;

    // Relacionamento 1:1 com Colaborador (lado não-proprietário)
    @OneToOne(mappedBy = "perfilAcesso", fetch = FetchType.LAZY)
    private Colaborador colaborador;

    // Enum para níveis de acesso
    public enum NivelAcesso {
        ADMINISTRADOR,
        DEV,
        TESTER
    }
}