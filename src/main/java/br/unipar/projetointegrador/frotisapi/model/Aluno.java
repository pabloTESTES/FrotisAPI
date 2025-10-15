package br.unipar.projetointegrador.frotisapi.model;

import com.fasterxml.jackson.annotation.JsonBackReference; // <-- Mude a importação
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
public class Aluno {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String CPF;
    private String telefone;
    private String email;
    private float peso;
    private float altura;
    private Date dataNascimento;
    private String sexo;

    @OneToOne(cascade = CascadeType.ALL)
    private Endereco endereco;


    @JsonBackReference("aluno-treinos")
    @ManyToOne
    @JoinColumn(name = "treino_id")
    private Treino treino;


    @JsonManagedReference("aluno-matriculas")
    @OneToMany(mappedBy = "aluno", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<Matricula> matriculaList;
}