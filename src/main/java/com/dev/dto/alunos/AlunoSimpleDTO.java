package com.dev.dto.alunos;

import com.dev.domain.Aluno;
import com.dev.dto.usuarios.UsuarioDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AlunoSimpleDTO extends UsuarioDTO{

	private static final long serialVersionUID = 1L;
	
	private String papel;
	private String curso;
	
	public AlunoSimpleDTO(Aluno obj) {
		super(obj.getId(), obj.getMatricula(), obj.getNome(), obj.getEmail());
		this.papel = obj.getPapel();
		this.curso = obj.getCurso();
	}
}