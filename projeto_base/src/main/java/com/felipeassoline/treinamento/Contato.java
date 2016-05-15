package com.felipeassoline.treinamento;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;

import lombok.Data;

@Data
@Entity
public class Contato {

	@Id
	@GeneratedValue
	private Integer id;

	@NotNull
	@Size(max = 100, min = 3)
	private String nome;

	@NotNull
	@Email
	@Size(max = 100)
	private String email;

	public Contato() {

	}

	public Contato(String nome, String email) {
		this.nome = nome;
		this.email = email;
	}

}