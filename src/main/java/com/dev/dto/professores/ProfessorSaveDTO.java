package com.dev.dto.professores;

import java.util.List;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.dev.domain.Projeto;
import com.dev.domain.enums.Perfil;
import com.dev.dto.usuarios.UsuarioSaveDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProfessorSaveDTO extends UsuarioSaveDTO{

	private static final long serialVersionUID = 1L;

	@NotEmpty(message="Preenchimento obrigatório.")
	@Length(min=5, max=20, message="O tamanho deve ser entre 5 e 20 caracteres.")
	private String atuacao;
    
    @NotEmpty(message="Preenchimento obrigatório.")
	@Length(min=5, max=20, message="O tamanho deve ser entre 5 e 20 caracteres.")
    private String formacao;
    
    private List<Projeto> projetos;
    
    public ProfessorSaveDTO(Integer id, String matricula, String nome, String email, String password, Perfil perfil,
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