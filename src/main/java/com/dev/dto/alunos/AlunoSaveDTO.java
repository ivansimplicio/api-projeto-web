package com.dev.dto.alunos;

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
public class AlunoSaveDTO extends UsuarioSaveDTO{
  
	private static final long serialVersionUID = 1L;

	@Length(min=4, max=30, message="O tamanho deve ser entre 4 e 30 caracteres.")
    @NotEmpty(message="Preenchimento obrigatório.")
	private String curso;
    
    private String papel;
    
    private Projeto projeto;
    
    public AlunoSaveDTO(Integer id, String matricula, String nome, String email, String password, Perfil perfil,
					String curso, Projeto projeto, String papel) {
    	super(id, matricula, nome, email, password, perfil);
		this.curso = curso;
		this.projeto = projeto;
		this.papel = papel;
    }
}