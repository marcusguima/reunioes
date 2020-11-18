package com.reunioes.api.controllers;
 
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
import com.reunioes.api.response.Response;
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
   	public ResponseEntity<Response<List<Reuniao>>> buscarPorUsuarioId(@PathVariable("usuarioId") int usuarioId) {
 
   		Response<List<Reuniao>> response = new Response<List<Reuniao>>();
   		
         	try {
 
                	log.info("Controller: buscando reuniões do usuário de ID: {}", usuarioId);
 
                	Optional<List<Reuniao>> listaReunioes = reuniaoService.buscarPorUsuarioId(usuarioId);
                	
                	response.setDados(listaReunioes.get());
 
                	return ResponseEntity.ok(response);
 
         	} catch (ConsistenciaException e) {
                	log.info("Controller: Inconsistência de dados: {}", e.getMessage());
                	response.adicionarErro(e.getMensagem());
                	return ResponseEntity.badRequest().body(response);
         	} catch (Exception e) {
                	log.error("Controller: Ocorreu um erro na aplicação: {}", e.getMessage());
                	response.adicionarErro("Ocorreu um erro na aplicação: {}", e.getMessage());
                	return ResponseEntity.status(500).body(response);
         	}
 
   	}
 
   	/**
   	 * Persiste um usuário na base.
   	 *
   	 * @param Dados de entrada da reunião
   	 * @return Dados da reunião persistido
   	 */
   	@PostMapping
   	public ResponseEntity<Response<Reuniao>> salvar(@RequestBody Reuniao reuniao) {
 
   		Response<Reuniao> response = new Response<Reuniao>();
   		
         	try {
 
                	log.info("Controller: salvando a reunião: {}", reuniao.toString());
                	
                	response.setDados(this.reuniaoService.salvar(reuniao));
         	
                	return ResponseEntity.ok(response);
 
         	} catch (ConsistenciaException e) {
                	log.info("Controller: Inconsistência de dados: {}", e.getMessage());
                	response.adicionarErro(e.getMensagem());
                	return ResponseEntity.badRequest().body(response);
         	} catch (Exception e) {
                	log.error("Controller: Ocorreu um erro na aplicação: {}", e.getMessage());
                	response.adicionarErro("Ocorreu um erro na aplicação: {}", e.getMessage());
                	return ResponseEntity.status(500).body(response);
         	}
 
   	}
   	
   	/**
   	 * Exclui uma reunião a partir do id informado no parâmtero
   	 * @param id da reunião a ser excluído
   	 * @return Sucesso/erro
   	 */
   	@DeleteMapping(value = "excluir/{id}")
   	public ResponseEntity<Response<String>> excluirPorId(@PathVariable("id") int id){
         	
   		Response<String> response = new Response<String>();
   		
         	try {
 
                	log.info("Controller: excluíndo reunião de ID: {}", id);
 
                	reuniaoService.excluirPorId(id);
                	
                	response.setDados("Reunião de id: " + id + " excluído com sucesso");
 
                	return ResponseEntity.ok(response);
 
         	} catch (ConsistenciaException e) {
                	log.info("Controller: Inconsistência de dados: {}", e.getMessage());
                	response.adicionarErro(e.getMensagem());
                	return ResponseEntity.badRequest().body(response);
         	} catch (Exception e) {
                	log.error("Controller: Ocorreu um erro na aplicação: {}", e.getMessage());
                	response.adicionarErro("Ocorreu um erro na aplicação: {}", e.getMessage());
                	return ResponseEntity.status(500).body(response);
         	}
         	
   	}
 
}
