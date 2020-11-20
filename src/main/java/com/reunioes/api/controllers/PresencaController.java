package com.reunioes.api.controllers;
 
import java.util.List;
import java.util.Optional;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
 
import com.reunioes.api.dtos.PresencaDto;
import com.reunioes.api.entities.Presenca;
import com.reunioes.api.services.PresencaService;
import com.reunioes.api.utils.ConsistenciaException;
import com.reunioes.api.utils.ConversaoUtils;
import com.reunioes.api.response.Response;
 
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
   	public ResponseEntity<Response<List<PresencaDto>>> buscarPorUsuarioId(@PathVariable("usuarioId") int usuarioId) {
 
   		Response<List<PresencaDto>> response = new Response<List<PresencaDto>>();
   		
         	try {
 
                	log.info("Controller: buscando presenças do usuário de ID: {}", usuarioId);
 
                	Optional<List<Presenca>> listaPresencasUsuario = presencaService.buscarPorUsuarioId(usuarioId);
 
                	response.setDados(ConversaoUtils.ConverterListaPresenca(listaPresencasUsuario.get()));
                	 
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
   	public ResponseEntity<Response<List<PresencaDto>>> buscarPorReuniaoId(@PathVariable("reuniaoId") int reuniaoId) {
 
   		Response<List<PresencaDto>> response = new Response<List<PresencaDto>>();
   		
         	try {
 
                	log.info("Controller: buscando presenças da reunião de ID: {}", reuniaoId);
 
                	Optional<List<Presenca>> listaPresencasReuniao = presencaService.buscarPorReuniaoId(reuniaoId);
 
                	response.setDados(ConversaoUtils.ConverterListaPresenca(listaPresencasReuniao.get()));
                	 
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
   	public ResponseEntity<Response<PresencaDto>> salvar(@Valid @RequestBody PresencaDto presencaDto, BindingResult result) {
   		
   		Response<PresencaDto> response = new Response<PresencaDto>();
 
         	try {
 
                	log.info("Controller: salvando a presença: {}", presencaDto.toString());
         	
                	if (result.hasErrors()) {
                		 
                       	for (int i = 0; i < result.getErrorCount(); i++) {
                       	   	response.adicionarErro(result.getAllErrors().get(i).getDefaultMessage());
                       	}
 
                       	log.info("Controller: Os campos obrigatórios não foram preenchidos");
                       	return ResponseEntity.badRequest().body(response);
 
                	}

                	Presenca presenca = this.presencaService.salvar(ConversaoUtils.Converter(presencaDto));
                	response.setDados(ConversaoUtils.Converter(presenca));

                	 
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
   	@PreAuthorize("hasAnyRole('EXCLUSAO')")
   	public ResponseEntity<Response<String>> excluirPorId(@PathVariable("id") int id){
         	
   		Response<String> response = new Response<String>();
   		
         	try {
 
                	log.info("Controller: excluíndo presença de ID: {}", id);
 
                	presencaService.excluirPorId(id);
 
                	response.setDados("Presença de id: " + id + " excluído com sucesso");
                	 
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
