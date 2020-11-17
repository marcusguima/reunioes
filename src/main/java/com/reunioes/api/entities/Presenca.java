package com.reunioes.api.entities;

import java.io.Serializable;
 
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
 
import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "presenca")
public class Presenca implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
   	@GeneratedValue(strategy = GenerationType.AUTO)
   	private int id;

	@Column(name = "presente", nullable = false, length = 1)
	private int presente;
	
	@JsonBackReference(value = "presenca-usuario")
   	@ManyToOne(fetch = FetchType.EAGER)
   	private Usuario usuario;
	
	@JsonBackReference(value = "presenca-reuniao")
   	@ManyToOne(fetch = FetchType.EAGER)
   	private Reuniao reuniao;
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPresente() {
		return presente;
	}

	public void setPresente(int presente) {
		this.presente = presente;
	}

	public Reuniao getReuniao() {
		return reuniao;
	}

	public void setReuniao(Reuniao reuniao) {
		this.reuniao = reuniao;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	@Override
	public String toString() {
		return "Presenca [id=" + id + ", presente=" + presente + ", reuniao=" + reuniao + ", usuario=" + usuario + "]";
	}
	
}
