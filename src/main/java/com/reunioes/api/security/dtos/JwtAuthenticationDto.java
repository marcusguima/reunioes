package com.reunioes.api.security.dtos;
 
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
 
import org.hibernate.validator.constraints.Length;

 
public class JwtAuthenticationDto {
 
	@NotEmpty(message = "Email não pode ser vazio.")
	@Length(min = 5, max = 100,
   	message = "Email atual deve conter entre 5 e 100 caracteres.")
   	@Email( message = "Email inválido.")
   	private String email;
   	
   	@NotEmpty(message = "Senha não pode ser vazia.")
   	@Length(min = 5, max = 100,
   	message = "Senha atual deve conter entre 5 e 100 caracteres.")
   	private String senha;
   	
   	public String getEmail() {
         	return email;
   	}
   	
   	public void setEmail(String email) {
         	this.email = email;
   	}
 
   	public String getSenha() {
         	return senha;
   	}
   	
   	public void setSenha(String senha) {
         	this.senha = senha;
   	}
 
   	@Override
   	public String toString() {
         	return "JwtAuthenticationRequestDto[email=" + email + ", senha=" + senha + "]";
   	}
   	
}
