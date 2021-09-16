package com.iscte.youcredit.model;

import java.sql.*;

import javax.persistence.*;

import org.openxava.annotations.*;

@View(members = "Estado [" + "estado;" + "descricao;" + "]" + "Logging [" + "dataAlteracao;" + "utilizadorAlteracao;"
		+ "estadoRegisto;" + "];")

@Entity
@Table(name = "EstadoSimulacao")

public class EstadoSimulacao {
	
	@Hidden
	@Column(name = "estadoSimulacao_id")
	private int estadoSimulacaoid;

	@Id
	@Column(name = "estado", length = 80)
	private String estado;

	@ReadOnly
	@Column(name = "descricao", length = 500)
	private String descricao;

	@ReadOnly
	@Column(name = "data_alteracao")
	private Timestamp dataAlteracao;

	@ReadOnly
	@Column(name = "utilizador_alteracao", length = 80)
	private String utilizadorAlteracao;

	@ReadOnly
	@Column(name = "estado_registo", length = 80)
	private String estadoRegisto;

	public int getEstadoSimulacaoid() {
		return estadoSimulacaoid;
	}

	public void setEstadoSimulacaoid(int estadoSimulacaoid) {
	//	this.estadoSimulacaoid = estadoSimulacaoid;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Timestamp getDataAlteracao() {
		return dataAlteracao;
	}

	public void setDataAlteracao(Timestamp dataAlteracao) {
		this.dataAlteracao = dataAlteracao;
	}

	public String getUtilizadorAlteracao() {
		return utilizadorAlteracao;
	}

	public void setUtilizadorAlteracao(String utilizadorAlteracao) {
		this.utilizadorAlteracao = utilizadorAlteracao;
	}

	public String getEstadoRegisto() {
		return estadoRegisto;
	}

	public void setEstadoRegisto(String estadoRegisto) {
		this.estadoRegisto = estadoRegisto;
	}

	

}
