package com.dev.dto.projetos;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.dev.domain.Projeto;
import com.dev.dto.alunos.AlunoSimpleDTO;
import com.dev.dto.professores.ProfessorSimpleDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProjetoDTO extends ProjetoSimpleDTO{

    private static final long serialVersionUID = 1L;
    
    private ProfessorSimpleDTO coordenador;
    private List<AlunoSimpleDTO> alunos = new ArrayList<>();
    
    public ProjetoDTO(Projeto obj) {
    	super(obj);
    	this.coordenador = new ProfessorSimpleDTO(obj.getCoordenador());
    	this.alunos = obj.getAlunos().stream().map(aluno -> new AlunoSimpleDTO(aluno)).collect(Collectors.toList());
    }
}