package com.reunioes.api.dtos;

import javax.validation.constraints.NotEmpty;
import org.hibernate.validator.constraints.Length;

public class ReuniaoDto {

	private String id;
	
	@NotEmpty(message = "Nome não pode ser vazio.")
   	@Length(min = 5, max = 45,
   	message = "Nome deve conter entre 5 e 45 caracteres.")
	private String nome;
	
	@NotEmpty(message = "Data não pode ser vazio.")
	private String data;
	
	@NotEmpty(message = "Finalizado não pode ser vazio.")
	private String finalizado;
	
	@NotEmpty(message = "Ativo não pode ser vazio.")
	private String ativo;
	
	@NotEmpty(message = "O ID do usuário não pode ser vazio.")
	private String usuarioId;
	
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
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public String getFinalizado() {
		return finalizado;
	}
	public void setFinalizado(String finalizado) {
		this.finalizado = finalizado;
	}
	public String getAtivo() {
		return ativo;
	}
	public void setAtivo(String ativo) {
		this.ativo = ativo;
	}
	public String getUsuarioId() {
		return usuarioId;
	}
	public void setUsuarioId(String usuarioId) {
		this.usuarioId = usuarioId;
	}
	
	@Override
	public String toString() {
		return "Reuniao[id=" + id + ", nome=" + nome + ", data=" + data + ", finalizado=" + finalizado + ", ativo="
				+ ativo + ", usuarioId=" + usuarioId + "]";
	}
	
	
}