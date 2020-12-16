package com.dev.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@Entity(name = "projetos")
public class Projeto implements Serializable{

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nome;

    private String descricao;

    @ManyToOne
    @JoinColumn(name="coordenador_id")
    private Professor coordenador;
    
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "projeto")
    private List<Aluno> alunos = new ArrayList<>();
    
    public void addAluno(Aluno aluno) {
    	alunos.add(aluno);
    }
    
    public Aluno getAluno(int id) {
    	return alunos.get(id);
    }
    
    public void removeAluno(int id) {
    	alunos.remove(id);
    }
}