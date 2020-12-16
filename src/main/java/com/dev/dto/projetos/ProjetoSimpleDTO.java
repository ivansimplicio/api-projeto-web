package com.dev.dto.projetos;

import java.io.Serializable;

import com.dev.domain.Projeto;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class ProjetoSimpleDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String nome;
    private String descricao;
	
	public ProjetoSimpleDTO(Projeto obj) {
		this.id = obj.getId();
    	this.nome = obj.getNome();
    	this.descricao = obj.getDescricao();
	}
}