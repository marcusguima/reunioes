package com.reunioes.api.controllers;
 
import java.util.Optional;
 
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
 
import com.reunioes.api.entities.Usuario;
import com.reunioes.api.services.UsuarioService;
import com.reunioes.api.utils.ConsistenciaException;
 
@RestController
@RequestMapping("/api/usuario")
@CrossOrigin(origins = "*")
public class UsuarioController {
 
   	private static final Logger log = LoggerFactory.getLogger(UsuarioController.class);
 
   	@Autowired
   	private UsuarioService usuarioService;
 
   	/**
   	 * Retorna os dados de um usuário a partir do seu id
   	 *
   	 * @param Id do usuário
   	 * @return Dados do usuário
   	 */
   	@GetMapping(value = "/{id}")
   	public ResponseEntity<Usuario> buscarPorId(@PathVariable("id") int id) {
 
         	try {
 
                	log.info("Controller: buscando usuário com id: {}", id);
                	
                	Optional<Usuario> usuario = usuarioService.buscarPorId(id);
 
                	return ResponseEntity.ok(usuario.get());
 
         	} catch (ConsistenciaException e) {
                	log.info("Controller: Inconsistência de dados: {}", e.getMessage());
                	return ResponseEntity.badRequest().body(new Usuario());
         	} catch (Exception e) {
                	log.error("Controller: Ocorreu um erro na aplicação: {}", e.getMessage());
                	return ResponseEntity.status(500).body(new Usuario());
         	}
 
   	}
 
   	/**
   	 * Persiste um usuário na base.
   	 *
   	 * @param Dados de entrada do usuário
   	 * @return Dados do usuário persistido
   	 */
   	@PostMapping
   	public ResponseEntity<Usuario> salvar(@RequestBody Usuario usuario) {
 
         	try {
 
                	log.info("Controller: salvando o usuário: {}", usuario.toString());
 
                	return ResponseEntity.ok(this.usuarioService.salvar(usuario));
 
         	} catch (ConsistenciaException e) {
                	log.info("Controller: Inconsistência de dados: {}", e.getMessage());
                	return ResponseEntity.badRequest().body(new Usuario());
         	} catch (Exception e) {
                	log.error("Controller: Ocorreu um erro na aplicação: {}", e.getMessage());
                	return ResponseEntity.status(500).body(new Usuario());
         	}
 
   	}
 
}
