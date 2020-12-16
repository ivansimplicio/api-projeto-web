package com.dev.dto.projetos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.dev.domain.Projeto;
import com.dev.dto.alunos.AlunoDTO;
import com.dev.dto.professores.ProfessorDTO;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class ProjetoDTO implements Serializable{

    private static final long serialVersionUID = 1L;
    
    private Integer id;
	private String nome;
    private String descricao;
    private ProfessorDTO coordenador;
    private List<AlunoDTO> alunos = new ArrayList<>();
    
    public ProjetoDTO(Projeto obj) {
    	this.id = obj.getId();
    	this.nome = obj.getNome();
    	this.descricao = obj.getDescricao();
    	this.coordenador = new ProfessorDTO(obj.getCoordenador());
    	this.alunos = obj.getAlunos().stream().map(aluno -> new AlunoDTO(aluno)).collect(Collectors.toList());
    }
}