package com.dev.dto.professores;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.dev.domain.Professor;
import com.dev.dto.projetos.ProjetoSimpleDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProfessorDTO extends ProfessorSimpleDTO{

	private static final long serialVersionUID = 1L;
	
    private List<ProjetoSimpleDTO> projetos = new ArrayList<>();
    
    public ProfessorDTO(Professor obj) {
    	super(obj);
		
		this.projetos = obj.getProjetos() == null ? new ArrayList<>() : 
			obj.getProjetos().stream().map(proj -> new ProjetoSimpleDTO(proj)).collect(Collectors.toList());
    }

	public void addProjeto(ProjetoSimpleDTO proj) {
		projetos.add(proj);
	}
	
	public ProjetoSimpleDTO getProjeto(int id) {
		return projetos.get(id);
	}
	
	public void removeProjeto(int id) {
		projetos.remove(id);
	}
}