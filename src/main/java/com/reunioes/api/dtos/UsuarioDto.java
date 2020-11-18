package com.reunioes.api.dtos;
 
import javax.validation.constraints.NotEmpty;
import org.hibernate.validator.constraints.Length;

public class UsuarioDto {
 
   	private String id;
   	
   	@NotEmpty(message = "Nome não pode ser vazio.")
   	@Length(min = 5, max = 45,
   	message = "Nome deve conter entre 5 e 45 caracteres.")
   	private String nome;
   	
   	@NotEmpty(message = "Email não pode ser vazio.")
   	@Length(min = 5, max = 100,
   	message = "Email deve conter entre 5 e 100 caracteres.")
   	private String email;
   	
   	@NotEmpty(message = "Senha não pode ser vazio.")
   	@Length(min = 5, max = 100,
   	message = "Senha deve conter entre 5 e 100 caracteres.")
   	private String senha;
   	
   	@NotEmpty(message = "Ativo não pode ser vazio.")
   	private String ativo;
   	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
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
	public String getAtivo() {
		return ativo;
	}
	public void setAtivo(String ativo) {
		this.ativo = ativo;
	}
	
	@Override
	public String toString() {
		return "Usuario[id=" + id + ", nome=" + nome + ", email=" + email + ", senha=" + senha + ", ativo=" + ativo
				+ "]";
	}
   	
   	
}