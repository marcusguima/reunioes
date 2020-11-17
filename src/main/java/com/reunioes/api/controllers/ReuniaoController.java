package com.reunioes.api.controllers;
 
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
 
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
 
import com.reunioes.api.entities.Reuniao;
import com.reunioes.api.services.ReuniaoService;
import com.reunioes.api.utils.ConsistenciaException;
 
@RestController
@RequestMapping("/api/reuniao")
@CrossOrigin(origins = "*")
public class ReuniaoController {
 
   	private static final Logger log = LoggerFactory.getLogger(ReuniaoController.class);
 
   	@Autowired
   	private ReuniaoService reuniaoService;
 
   	/**
   	 * Retorna as reuniões do informado no parâmetro
   	 *
   	 * @param Id do usuário a ser consultado
   	 * @return Lista de reuniões que o usuário possui
   	 */
   	@GetMapping(value = "/usuario/{usuarioId}")
   	public ResponseEntity<List<Reuniao>> buscarPorUsuarioId(@PathVariable("usuarioId") int usuarioId) {
 
         	try {
 
                	log.info("Controller: buscando reuniões do usuário de ID: {}", usuarioId);
 
                	Optional<List<Reuniao>> listaReunioes = reuniaoService.buscarPorUsuarioId(usuarioId);
 
                	return ResponseEntity.ok(listaReunioes.get());
 
         	} catch (ConsistenciaException e) {
                	log.info("Controller: Inconsistência de dados: {}", e.getMessage());
                	return ResponseEntity.badRequest().body(new ArrayList<Reuniao>());
         	} catch (Exception e) {
                	log.error("Controller: Ocorreu um erro na aplicação: {}", e.getMessage());
                	return ResponseEntity.status(500).body(new ArrayList<Reuniao>());
         	}
 
   	}
 
   	/**
   	 * Persiste um usuário na base.
   	 *
   	 * @param Dados de entrada da reunião
   	 * @return Dados da reunião persistido
   	 */
   	@PostMapping
   	public ResponseEntity<Reuniao> salvar(@RequestBody Reuniao reuniao) {
 
         	try {
 
                	log.info("Controller: salvando a reunião: {}", reuniao.toString());
         	
                	return ResponseEntity.ok(this.reuniaoService.salvar(reuniao));
 
         	} catch (ConsistenciaException e) {
                	log.info("Controller: Inconsistência de dados: {}", e.getMessage());
                	return ResponseEntity.badRequest().body(new Reuniao());
         	} catch (Exception e) {
                	log.error("Controller: Ocorreu um erro na aplicação: {}", e.getMessage());
                	return ResponseEntity.status(500).body(new Reuniao());
         	}
 
   	}
   	
   	/**
   	 * Exclui uma reunião a partir do id informado no parâmtero
   	 * @param id da reunião a ser excluído
   	 * @return Sucesso/erro
   	 */
   	@DeleteMapping(value = "excluir/{id}")
   	public ResponseEntity<String> excluirPorId(@PathVariable("id") int id){
         	
         	try {
 
                	log.info("Controller: excluíndo reunião de ID: {}", id);
 
                	reuniaoService.excluirPorId(id);
 
                	return ResponseEntity.ok("Reunião de id: " + id + " excluído com sucesso");
 
         	} catch (ConsistenciaException e) {
                	log.info("Controller: Inconsistência de dados: {}", e.getMessage());
                	return ResponseEntity.badRequest().body(e.getMensagem());
         	} catch (Exception e) {
                	log.error("Controller: Ocorreu um erro na aplicação: {}", e.getMessage());
                	return ResponseEntity.status(500).body(e.getMessage());
         	}
         	
   	}
 
}
