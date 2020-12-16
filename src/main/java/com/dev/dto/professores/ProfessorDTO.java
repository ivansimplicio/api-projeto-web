package com.dev.dto.professores;

import java.util.ArrayList;
import java.util.List;

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
public class ProfessorDTO extends UsuarioDTO{

	private static final long serialVersionUID = 1L;
	
	private String atuacao;
    private String formacao;
    private List<String> projetos = new ArrayList<>();
    
    public ProfessorDTO(Professor obj) {
    	super(obj.getId(), obj.getMatricula(), obj.getNome(), obj.getEmail());
		this.atuacao = obj.getAtuacao();
		this.formacao = obj.getFormacao();
		//this.projetos = new ArrayList<>();
    }

	public void addProjeto(String proj) {
		projetos.add(proj);
	}
	
	public String getProjeto(int id) {
		return projetos.get(id);
	}
	
	public void removeProjeto(int id) {
		projetos.remove(id);
	}
}