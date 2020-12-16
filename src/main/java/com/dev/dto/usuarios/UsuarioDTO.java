package com.dev.dto.usuarios;

import java.io.Serializable;

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
public class UsuarioDTO implements Serializable{

    private static final long serialVersionUID = 1L;
    
    private Integer id;
    private String matricula;
    private String nome;
    private String email;
}