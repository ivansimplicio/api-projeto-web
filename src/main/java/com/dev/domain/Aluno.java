package com.dev.domain;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.dev.domain.enums.Perfil;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity(name = "alunos")
public class Aluno extends Usuario{
	
	private static final long serialVersionUID = 1L;
	
	private String curso;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "projeto_id")
	private Projeto projeto;

	private String papel;
	
	public Aluno(Integer id, String matricula, String nome, String email, String password, Perfil perfil,
					String curso, Projeto projeto, String papel) {
		super(id, matricula, nome, email, password, perfil);
		this.curso = curso;
		this.projeto = projeto;
		this.papel = papel;
	}
}