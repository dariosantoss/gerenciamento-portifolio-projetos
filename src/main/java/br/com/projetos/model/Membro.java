package br.com.projetos.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "membros")
public class Membro {
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "idprojeto", referencedColumnName = "id", nullable = false)
    private Projeto projeto;

    @ManyToOne
    @JoinColumn(name = "idpessoa", referencedColumnName = "id", nullable = false)
    private Pessoa pessoa;


}
