package com.reunioes.api.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.reunioes.api.entities.Regra;
import com.reunioes.api.entities.Usuario;
import com.reunioes.api.repositories.RegraRepository;
import com.reunioes.api.repositories.UsuarioRepository;
import com.reunioes.api.security.utils.JwtTokenUtil;
import com.reunioes.api.utils.ConsistenciaException;
import com.reunioes.api.utils.SenhaUtils;

@Service
public class UsuarioService {

	private static final Logger log = LoggerFactory.getLogger(UsuarioService.class);

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
   	private RegraRepository regraReprository;
 
   	@Autowired
   	private HttpServletRequest httpServletRequest;
   	
   	@Autowired
   	private JwtTokenUtil jwtTokenUtil;

	public Optional<Usuario> buscarPorId(int id) throws ConsistenciaException {

		log.info("Service: buscando um usuário com o id: {}", id);

		Optional<Usuario> usuario = usuarioRepository.findById(id);

		if (!usuario.isPresent()) {

			log.info("Service: Nenhum usuário com id: {} foi encontrado", id);
			throw new ConsistenciaException("Nenhum usuário com id: {} foi encontrado", id);

		}

		return usuario;

	}
	
	public Optional<Usuario> verificarCredenciais(String email) throws ConsistenciaException {
		 
      	log.info("Service: criando credenciais para o usuário de Email: '{}'", email);

      	Optional<Usuario> usuario = Optional.ofNullable(usuarioRepository.findByEmailAndAtivo(email, 1));

      	if (!usuario.isPresent()) {
             	log.info("Service: Nenhum usuário ativo com Email: {} foi encontrado", email);
             	throw new ConsistenciaException("Nenhum usuário ativo com Email: {} foi encontrado", email);
      	}

      	usuario.get().setRegras(
                   	usuario.get().getRegras()
                   	.stream().filter(r -> r.getAtivo() == true)
                   	.collect(Collectors.toList())
      	);

      	return usuario;

	}

	public Usuario salvar(Usuario usuario) throws ConsistenciaException {

		log.info("Service: salvando o usuário: {}", usuario);

		if (usuario.getId() > 0) {
			
			Optional<Usuario> usr = buscarPorId(usuario.getId());
			
			usuario.setSenha(usr.get().getSenha());
		}else {
			
			usuario.setSenha(SenhaUtils.gerarHash(usuario.getEmail()));
		}
		
		if (usuario.getRegras() != null) {
			 
         	List<Regra> aux = new ArrayList<Regra>(usuario.getRegras().size());

         	for (Regra regra : usuario.getRegras()) {

               	Optional<Regra> rg = Optional.ofNullable(regraReprository.findByNome(regra.getNome()));

               	if (rg.isPresent()) {
                      	aux.add(rg.get());
               	} else {

                      	log.info("A regra '{}' não existe", regra.getNome());
                      	throw new ConsistenciaException("A regra '{}' não existe", regra.getNome());

               	}

         	}

         	usuario.setRegras(aux);

  	}
		
		
		
		try {
			return usuarioRepository.save(usuario);
		} catch (DataIntegrityViolationException e) {

			log.info("Service: O Email: {} já está cadastrado para outro usuário", usuario.getEmail());
			throw new ConsistenciaException("O Email: {} já está cadastrado para outro usuário", usuario.getEmail());

		}
		

	}
	
	public void alterarSenhaUsuario(String senhaAtual, String novaSenha, int id) throws ConsistenciaException {
		 
      	log.info("Service: alterando a senha do usuário: {}", id);

      	Optional<Usuario> usr = buscarPorId(id);
  
      	String token = httpServletRequest.getHeader("Authorization");

      	if (token != null && token.startsWith("Bearer ")) {
             	token = token.substring(7);
      	}

      	String username = jwtTokenUtil.getUsernameFromToken(token);

      	if (!usr.get().getEmail().equals(username)) {

             	
             	log.info("Service: Email do token diferente do Email do usuário a ser alterado");
             	throw new ConsistenciaException("Você não tem permissão para alterar a senha de outro usuário.");

      	}

      	if (!SenhaUtils.compararHash(senhaAtual, usr.get().getSenha())) {

             	log.info("Service: A senha atual informada não é válida");
             	throw new ConsistenciaException("A senha atual informada não é válida.");

      	}

      	usuarioRepository.alterarSenhaUsuario(SenhaUtils.gerarHash(novaSenha), id);

	}

}



