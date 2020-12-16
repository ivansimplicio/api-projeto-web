package com.dev.dto.usuarios;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.dev.domain.enums.Perfil;

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
public class UsuarioSaveDTO implements Serializable{

    private static final long serialVersionUID = 1L;
    
    private Integer id;
    
    @Length(min = 8, max = 10, message="O tamanho deve ser entre 8 e 10 caracteres.")
	@NotEmpty(message="Preenchimento obrigatório.")
    private String matricula;
    
    @NotEmpty(message="Preenchimento obrigatório.")
	@Length(min=4, max=30, message="O tamanho deve ser entre 4 e 30 caracteres.")
    private String nome;
    
    @NotEmpty(message="Preenchimento obrigatório.")
	@Length(min=12, max=50, message="O tamanho deve ser entre 12 e 50 caracteres.")
    private String email;
    
    @NotEmpty(message="Preenchimento obrigatório.")
	@Length(min=4, max=20, message="O tamanho deve ser entre 4 e 20 caracteres.")
	private String password;
    
    private Set<Integer> perfis = new HashSet<>();
    
    public UsuarioSaveDTO(Integer id, String matricula, String nome, String email, String password, Perfil perfil){
    	this.id = id;
    	this.matricula = matricula;
    	this.nome = nome;
    	this.email = email;
    	this.password = password;
    	addPerfil(perfil);
    }
    
    public Set<Perfil> getPerfis(){
		return perfis.stream().map(x -> Perfil.toEnum(x)).collect(Collectors.toSet());
	}

	public void addPerfil(Perfil perfil) {
		perfis.add(perfil.getCod());
	}
}