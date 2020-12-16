package com.dev.dto.projetos;

import java.io.Serializable;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class ProjetoSaveDTO implements Serializable{

    private static final long serialVersionUID = 1L;
    
    private Integer id;
    
    @NotEmpty(message="Preenchimento obrigatório.")
	@Length(min=5, max=30, message="O tamanho deve ser entre 5 e 30 caracteres.")
	private String nome;
    
    @NotEmpty(message="Preenchimento obrigatório.")
	@Length(min=5, max=50, message="O tamanho deve ser entre 5 e 50 caracteres.")
    private String descricao;
}