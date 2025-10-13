package br.unipar.projetointegrador.frotisapi.model;

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

    @JsonManagedReference
    @OneToMany(mappedBy = "aluno",orphanRemoval = true,cascade = jakarta.persistence.CascadeType.ALL)
    private List<Matricula> matriculaList;



}
