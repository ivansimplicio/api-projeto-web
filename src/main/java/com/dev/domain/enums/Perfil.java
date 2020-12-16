package com.dev.domain.enums;

public enum Perfil {

	PROFESSOR(1, "ROLE_PROFESSOR"),
	ALUNO(2, "ROLE_ALUNO");

	private int cod;
	private String descricao;

	private Perfil(int cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}

	public int getCod() {
		return cod;
	}

	public String getDescricao() {
		return descricao;
	}

	public static Perfil toEnum(Integer cod) {

		if(cod == null) {
			return null;
		}

		for(Perfil ep : Perfil.values()) {
			if(cod.equals(ep.getCod())) {
				return ep;
			}
		}
		throw new IllegalArgumentException("Id inválido: "+cod);
	}
}