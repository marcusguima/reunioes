package com.reunioes.api.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "reuniao")
public class Reuniao implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
   	@GeneratedValue(strategy = GenerationType.AUTO)
   	private int id;
	
	@Column(name = "nome", nullable = false, length = 45)
   	private String nome;

	@Column(name = "data", nullable = false)
   	private Date data;

	@Column(name = "finalizado", nullable = false, length = 1)
	private int finalizado;
	
	@Column(name = "ativo", nullable = false, length = 1)
	private int ativo;
	
	@JsonBackReference(value = "reuniao-usuario")
   	@ManyToOne(fetch = FetchType.EAGER)
   	private Usuario usuario;
	
	@JsonManagedReference(value = "presenca-reuniao")
   	@OneToMany(mappedBy = "reuniao", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
   	private List<Presenca> presencas;


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public int getFinalizado() {
		return finalizado;
	}

	public void setFinalizado(int finalizado) {
		this.finalizado = finalizado;
	}

	public int getAtivo() {
		return ativo;
	}

	public void setAtivo(int ativo) {
		this.ativo = ativo;
	}
	
	public List<Presenca> getPresencas() {
		return presencas;
	}

	public void setPresencas(List<Presenca> presencas) {
		this.presencas = presencas;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	@Override
	public String toString() {
		return "Reuniao [id=" + id + ", nome=" + nome + ", data=" + data + ", finalizado=" + finalizado + ", ativo="
				+ ativo + ", usuario=" + usuario + "]";
	}
	
}
