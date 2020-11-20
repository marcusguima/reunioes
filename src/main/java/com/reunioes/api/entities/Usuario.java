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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.JoinColumn;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "usuario")
public class Usuario implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
   	@GeneratedValue(strategy = GenerationType.AUTO)
   	private int id;
	
	@Column(name = "nome", nullable = false, length = 45)
   	private String nome;	

	@Column(name = "email", nullable = false, length = 100)
   	private String email;

	@Column(name = "senha", nullable = false, length = 100)
   	private String senha;

	@Column(name = "data_Atualizacao", nullable = false)
   	private Date dataAtualizacao;

	@Column(name = "ativo", nullable = false, length = 1)
	private int ativo;
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
   	@JoinTable(name = "usuario_regra",
   	           joinColumns = { @JoinColumn(name = "usuario_id") },
   	           inverseJoinColumns = { @JoinColumn(name = "regra_id") })
   	private List<Regra> regras;
	
	@JsonManagedReference(value = "reuniao-usuario")
   	@OneToMany(mappedBy = "usuario", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
   	private List<Reuniao> reunioes;
	
	@JsonManagedReference(value = "presenca-usuario")
	@OneToMany(mappedBy = "usuario", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
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

	public Date getDataAtualizacao() {
		return dataAtualizacao;
	}

	public void setDataAtualizacao(Date dataAtualizacao) {
		this.dataAtualizacao = dataAtualizacao;
	}

	public int getAtivo() {
		return ativo;
	}

	public void setAtivo(int ativo) {
		this.ativo = ativo;
	}
	
	public List<Regra> getRegras() {
      	return regras;
	}
	
	public void setRegras(List<Regra> regras) {
      	this.regras = regras;
	}
	
	public List<Reuniao> getReunioes() {
		return reunioes;
	}

	public void setReunioes(List<Reuniao> reunioes) {
		this.reunioes = reunioes;
	}

	public List<Presenca> getPresencas() {
		return presencas;
	}

	public void setPresencas(List<Presenca> presencas) {
		this.presencas = presencas;
	}

	@PreUpdate
	public void preUpdate() {
		dataAtualizacao = new Date();
	}

	@PrePersist
	public void prePersist() {
		dataAtualizacao = new Date();
	}
	
	@Override
	public String toString() {
		return "Usuario[" + "id=" + id + ", nome=" + nome + ", email=" + email + ", senha=" + senha + ", dataAtualizacao="
				+ dataAtualizacao + ", ativo=" + ativo + "]";
	}

}


