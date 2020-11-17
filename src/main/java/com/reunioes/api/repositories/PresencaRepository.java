package com.reunioes.api.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
 
import com.reunioes.api.entities.Presenca;

@Transactional(readOnly = true)
public interface PresencaRepository extends JpaRepository<Presenca, Integer> {

	@Query("SELECT ca FROM Presenca ca WHERE ca.usuario.id = :usuarioId")
   	List<Presenca> findByUsuarioId(@Param("usuarioId") int usuarioId);

	@Query("SELECT ca FROM Presenca ca WHERE ca.reuniao.id = :reuniaoId")
   	List<Presenca> findByReuniaoId(@Param("reuniaoId") int reuniaoId);
	
}
