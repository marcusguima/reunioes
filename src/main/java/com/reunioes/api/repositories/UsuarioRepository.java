package com.reunioes.api.repositories;

import java.util.Date;
import java.util.List;
 
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
 
import com.reunioes.api.entities.Usuario;

@Transactional(readOnly = true)
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

	@Transactional(readOnly = true)
   	List<Usuario> findByDataAtualizacao(Date dataAtualizacao);
	
}
