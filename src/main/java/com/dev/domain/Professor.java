package com.dev.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

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
@Entity(name = "professores")
public class Professor extends Usuario{
	
	private static final long serialVersionUID = 1L;

	private String atuacao;
	
	private String formacao;

	@JsonIgnore
	@OneToMany(mappedBy = "coordenador")
	private List<Projeto> projetos = new ArrayList<>();
	
	public Professor(Integer id, String matricula, String nome, String email, String password, Perfil perfil,
						String atuacao, String formacao, List<Projeto> projetos) {
		super(id, matricula, nome, email, password, perfil);
		this.atuacao = atuacao;
		this.formacao = formacao;
		this.projetos = projetos;
	}
	
	public void addProjeto(Projeto proj) {
		projetos.add(proj);
	}
	
	public Projeto getProjeto(int id) {
		return projetos.get(id);
	}
	
	public void removeProjeto(int id) {
		projetos.remove(id);
	}
}