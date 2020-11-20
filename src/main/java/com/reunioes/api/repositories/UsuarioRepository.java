package com.reunioes.api.repositories;

import java.util.Date;
import java.util.List;
 
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
 
import com.reunioes.api.entities.Usuario;

@Transactional(readOnly = true)
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

	@Transactional(readOnly = true)
   	List<Usuario> findByDataAtualizacao(Date dataAtualizacao);
	
	@Transactional(readOnly = true)
   	Usuario findByEmailAndAtivo(String email, int ativo);
	
	@Transactional
   	@Modifying(clearAutomatically = true)
   	@Query("UPDATE Usuario SET senha = :novasenha WHERE id = :idusuario")
   	void alterarSenhaUsuario(@Param("novasenha") String novasenha, @Param("idusuario") int idusuario);
	
}
