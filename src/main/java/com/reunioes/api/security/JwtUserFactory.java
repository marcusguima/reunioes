package com.reunioes.api.security;
 
import com.reunioes.api.entities.Usuario;
 
public class JwtUserFactory {
   	
   	public static JwtUser create(Usuario usuario) {
   	
         	return new JwtUser(usuario);	
   	
   	}
   	
}
