package com.placeti.avaliacao.repository;

import com.placeti.avaliacao.model.Comercio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//----------------------------------------------
/** Repositório para entidade Comercio */
//----------------------------------------------
@Repository
public interface ComercioRepository extends JpaRepository<Comercio, Long> {
}
