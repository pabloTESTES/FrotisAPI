package br.unipar.projetointegrador.frotisapi.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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

    // No arquivo Treino.java
// ...
    @JsonManagedReference("aluno-treinos") // O "pai" (lado da lista) usa ManagedReference
    @OneToMany(mappedBy = "treino")
    private List<Aluno> alunos;

    // A lista de itens do treino continua a mesma
    @OneToMany(mappedBy = "treino", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Item_treino> itens;
}