package com.dev.dto.alunos;

import lombok.Setter;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class AlunoAuxDTO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String papel;

}