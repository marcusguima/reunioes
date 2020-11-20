package com.reunioes.api.dtos;
import javax.validation.constraints.NotEmpty;
 
import org.hibernate.validator.constraints.Length;
 
public class SenhaDto {
 
   	@NotEmpty(message = "Id do usuário não pode ser vazio.")
   	private String idUsuario;
   	
   	@NotEmpty(message = "Senha atual não pode ser vazio.")
   	@Length(min = 5, max = 100,
   	message = "Senha atual deve conter entre 5 e 100 caracteres.")
   	private String senhaAtual;
   	
   	@NotEmpty(message = "Nova senha não pode ser vazio.")
   	@Length(min = 5, max = 100,
   	message = "Nova Senha deve conter entre 5 e 100 caracteres.")
   	private String novaSenha;
   	
   	public String getIdUsuario() {
         	return idUsuario;
   	}
   	
   	public void setIdUsuario(String idUsuario) {
         	this.idUsuario = idUsuario;
   	}
   	
   	public String getSenhaAtual() {
         	return senhaAtual;
   	}
   	
   	public void setSenhaAtual(String senhaAtual) {
         	this.senhaAtual = senhaAtual;
   	}
   	
   	public String getNovaSenha() {
         	return novaSenha;
   	}
   	
   	public void setNovaSenha(String novaSenha) {
         	this.novaSenha = novaSenha;
   	}
   	
   	@Override
   	public String toString() {
         	return "SenhaDto[idUsuario=" + idUsuario + ","
                       	+ "senhaAtual=" + senhaAtual + ","
                       	+ "novaSenha=" + novaSenha + "]";
   	}
   	
}
