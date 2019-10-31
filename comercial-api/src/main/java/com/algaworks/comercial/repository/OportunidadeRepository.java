package com.algaworks.comercial.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.algaworks.comercial.model.Oportunidade;

public interface OportunidadeRepository extends JpaRepository<Oportunidade, Long>{

	Oportunidade findByDescricaoAndNomeProspecto(String descricao, String nomeProspecto);
	
	Oportunidade findByDescricao(String descricao);
	
	@Query("select op from Oportunidade op where id = ?1")
	Oportunidade find(Long id);
	
}
