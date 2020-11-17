package com.reunioes.api.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
 
import com.reunioes.api.entities.Reuniao;

@Transactional(readOnly = true)
public interface ReuniaoRepository extends JpaRepository<Reuniao, Integer> {

	@Query("SELECT ca FROM Reuniao ca WHERE ca.usuario.id = :usuarioId")
   	List<Reuniao> findByUsuarioId(@Param("usuarioId") int usuarioId);
	
}
