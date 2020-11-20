package com.reunioes.api.security.services;
 
import java.util.Optional;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
 
import com.reunioes.api.entities.Usuario;
import com.reunioes.api.security.JwtUserFactory;
import com.reunioes.api.services.UsuarioService;
import com.reunioes.api.utils.ConsistenciaException;
 
@Service
public class JwtUserDetailsService implements UserDetailsService {
 
   	@Autowired
   	private UsuarioService usuarioService;
 
   	@Override
   	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
 
         	Optional<Usuario> usuario;
         	
         	try {
 
                	usuario = usuarioService.verificarCredenciais(username);
 
                	return JwtUserFactory.create(usuario.get());
 
         	} catch (ConsistenciaException e) {
                	throw new UsernameNotFoundException(e.getMensagem());
         	}
         	
   	}
 
}
