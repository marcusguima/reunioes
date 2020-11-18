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
 
import com.reunioes.api.entities.Presenca;
import com.reunioes.api.response.Response;
import com.reunioes.api.services.PresencaService;
import com.reunioes.api.utils.ConsistenciaException;
 
@RestController
@RequestMapping("/api/presenca")
@CrossOrigin(origins = "*")
public class PresencaController {
 
   	private static final Logger log = LoggerFactory.getLogger(PresencaController.class);
 
   	@Autowired
   	private PresencaService presencaService;
 
   	/**
   	 * Retorna as presenças do informado no parâmetro
   	 *
   	 * @param Id do usuário a ser consultado
   	 * @return Lista de presenças que o usuário possui
   	 */
   	@GetMapping(value = "/usuario/{usuarioId}")
   	public ResponseEntity<Response<List<Presenca>>> buscarPorUsuarioId(@PathVariable("usuarioId") int usuarioId) {
 
   		Response<List<Presenca>> response = new Response<List<Presenca>>();
   		
         	try {
 
                	log.info("Controller: buscando presenças do usuário de ID: {}", usuarioId);
 
                	Optional<List<Presenca>> listaPresencas = presencaService.buscarPorUsuarioId(usuarioId);
                	
                	response.setDados(listaPresencas.get());
 
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
   	 * Retorna as presenças do informado no parâmetro
   	 *
   	 * @param Id da reunião a ser consultado
   	 * @return Lista de presenças que a reunião possui
   	 */
   	@GetMapping(value = "/presenca/{presencaId}")
   	public ResponseEntity<Response<List<Presenca>>> buscarPorReuniaoId(@PathVariable("reuniaoId") int reuniaoId) {
 
   		Response<List<Presenca>> response = new Response<List<Presenca>>();
   		
         	try {
 
                	log.info("Controller: buscando presenças da reunião de ID: {}", reuniaoId);
 
                	Optional<List<Presenca>> listaPresencas = presencaService.buscarPorReuniaoId(reuniaoId);
                	
                	response.setDados(listaPresencas.get());
 
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
   	 * @param Dados de entrada da presença
   	 * @return Dados da presença persistido
   	 */
   	@PostMapping
   	public ResponseEntity<Response<Presenca>> salvar(@RequestBody Presenca presenca) {
 
   		Response<Presenca> response = new Response<Presenca>();
   		
         	try {
 
                	log.info("Controller: salvando a presença: {}", presenca.toString());
                	
                	response.setDados(this.presencaService.salvar(presenca));
         	
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
   	 * Exclui uma presença a partir do id informado no parâmtero
   	 * @param id da presença a ser excluído
   	 * @return Sucesso/erro
   	 */
   	@DeleteMapping(value = "excluir/{id}")
   	public ResponseEntity<Response<String>> excluirPorId(@PathVariable("id") int id){
         	
   		Response<String> response = new Response<String>();
   		
         	try {
 
                	log.info("Controller: excluíndo presença de ID: {}", id);
                	
                	response.setDados("Presença de id: " + id + " excluído com sucesso");
 
                	presencaService.excluirPorId(id);
 
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

