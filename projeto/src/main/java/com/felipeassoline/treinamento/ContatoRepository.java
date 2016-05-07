package com.felipeassoline.treinamento;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ContatoRepository extends JpaRepository<Contato, Integer> {

	Contato findByEmail(String email);

	@Query("select c from Contato c where c.nome like concat('%', :nome, '%')")
	List<Contato> findByNome(@Param("nome") String nome);

}
