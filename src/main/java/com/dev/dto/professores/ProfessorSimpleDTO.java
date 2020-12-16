package com.dev.dto.professores;

import com.dev.domain.Professor;
import com.dev.dto.usuarios.UsuarioDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProfessorSimpleDTO extends UsuarioDTO{
	
	private static final long serialVersionUID = 1L;
	
	private String atuacao;
    private String formacao;
    
    public ProfessorSimpleDTO(Professor obj) {
    	super(obj.getId(), obj.getMatricula(), obj.getNome(), obj.getEmail());
    	this.atuacao = obj.getAtuacao();
		this.formacao = obj.getFormacao();
    }
}