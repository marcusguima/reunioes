package com.reunioes.api.dtos;

import javax.validation.constraints.NotEmpty;

public class PresencaDto {

	private String id;
	
	@NotEmpty(message = "Presente não pode ser vazio.")
	private String presente;
	
	@NotEmpty(message = "O ID do usuário não pode ser vazio.")
	private String usuarioId;
	
	@NotEmpty(message = "O ID do reunião não pode ser vazio.")
	private String reuniaoId;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPresente() {
		return presente;
	}
	public void setPresente(String presente) {
		this.presente = presente;
	}
	public String getUsuarioId() {
		return usuarioId;
	}
	public void setUsuarioId(String usuarioId) {
		this.usuarioId = usuarioId;
	}
	public String getReuniaoId() {
		return reuniaoId;
	}
	public void setReuniaoId(String reuniaoId) {
		this.reuniaoId = reuniaoId;
	}
	
	@Override
	public String toString() {
		return "Presenca[id=" + id + ", presente=" + presente + ", usuarioId=" + usuarioId + ", reuniaoId=" + reuniaoId
				+ "]";
	}
	
	
}