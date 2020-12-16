package com.dev.dto.alunos;

import com.dev.domain.Aluno;
import com.dev.dto.projetos.ProjetoSimpleDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AlunoDTO extends AlunoSimpleDTO{
	
	private static final long serialVersionUID = 1L;
	
    private ProjetoSimpleDTO projeto;
    
    public AlunoDTO(Aluno obj) {
		super(obj);
		this.projeto = obj.getProjeto() == null ? null : new ProjetoSimpleDTO(obj.getProjeto());
	}
}