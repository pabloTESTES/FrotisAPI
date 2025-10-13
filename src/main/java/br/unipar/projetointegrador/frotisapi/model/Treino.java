package br.unipar.projetointegrador.frotisapi.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Treino {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String diaSemana;

    // Relacionamento com Instrutor continua o mesmo (Muitos treinos para Um instrutor)
    @ManyToOne
    @JoinColumn(name = "instrutor_id")
    private Instrutor instrutor;

    /**
     * CORREÇÃO PRINCIPAL:
     * Um Treino tem uma lista (@OneToMany) de Alunos.
     * "mappedBy = 'treino'" diz ao Hibernate para procurar o campo "treino" na classe Aluno
     * para encontrar a configuração da chave estrangeira.
     */
    @OneToMany(mappedBy = "treino", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Aluno> alunos;

    // A lista de itens do treino continua a mesma
    @OneToMany(mappedBy = "treino", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Item_treino> itens;
}