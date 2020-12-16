package com.dev.dto.professores;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

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
	@Length(min=5, max=50, message="O tamanho deve ser entre 5 e 50 caracteres.")
	private String atuacao;
    
    @NotEmpty(message="Preenchimento obrigatório.")
	@Length(min=5, max=50, message="O tamanho deve ser entre 5 e 50 caracteres.")
    private String formacao;
    
    public ProfessorSaveDTO(Integer id, String matricula, String nome, String email, String password, Perfil perfil,
						String atuacao, String formacao) {
		super(id, matricula, nome, email, password, perfil);
		this.atuacao = atuacao;
		this.formacao = formacao;
    }
}