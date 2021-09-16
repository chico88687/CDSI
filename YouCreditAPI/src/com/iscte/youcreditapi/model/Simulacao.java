package com.iscte.youcreditapi.model;

import java.math.*;
import java.sql.*;
import java.time.*;
import javax.persistence.*;
import org.openxava.annotations.*;
import org.openxava.model.*;
import org.openxava.util.*;

@View(members = "Estado [" + 
	"estadoSimulacao_id;"  +
	"simulacao_id;"        + 
	"]")

@Entity
@Table(name = "simulacao")

public class Simulacao {
	
	@Id	
	@Column(name="simulacao_id")
	private int simulacao_id;
	
	@Required
	@Column(name="codigo_produto")
	private int codigo_produto;
	
	@Required
	@Column(name= "estadoSimulacao_id")
	private int estadoSimulacao_id;
	
	@Required
	@Column(name = "utilizador_criaRegisto", length = 50)
	private String utilizador_criaRegisto;
	
	@Required
	@Hidden
	@Column(name = "utilizador_atualizaRegisto", length = 50)
	private String utilizador_atualizaRegisto;
	
	@Required
	@Column(name = "data_inicio")
	private Timestamp data_inicio;
	
	@Required
	@Column(name = "data_fim")
	private Timestamp data_fim;

	@Column(name = "data_avaliacao")
	private Timestamp data_avaliacao;
	
	@Column(name = "total_solicitado")
	private double total_solicitado;
	
	@Column(name = "total_possivel")
	private double total_possivel;
	
	@Column(name = "duracao")
	private int duracao;
	
	@Column(name = "total_capitalContratado")
	private double total_capitalContratado;
	
	@Column(name = "total_impostosContratado")
	private double total_impostosContratado;
	
	@Column(name="descricao_objetoCriado", length = 500)
	private String descricao_objetoCriado;
	
	@Hidden
	@Column(name = "scoring")
	private int scoring;
	
	@Hidden
	@Column(name = "data_alteracaoEstado") 
	private Timestamp data_alteracaoEstado;

	public int getSimulacao_id() {
		return simulacao_id;
	}

	public void setSimulacao_id(int simulacao_id) {
		this.simulacao_id = simulacao_id;
	}

	public int getCodigo_produto() {
		return codigo_produto;
	}

	public void setCodigo_produto(int codigo_produto) {
		this.codigo_produto = codigo_produto;
	}

	public int getEstadoSimulacao_id() {
		return estadoSimulacao_id;
	}

	public void setEstadoSimulacao_id(int estadoSimulacao_id) {
		this.estadoSimulacao_id = estadoSimulacao_id;
	}

	public String getUtilizador_criaRegisto() {
		return utilizador_criaRegisto;
	}

	public void setUtilizador_criaRegisto(String utilizador_criaRegisto) {
		this.utilizador_criaRegisto = utilizador_criaRegisto;
	}

	public String getUtilizador_atualizaRegisto() {
		return utilizador_atualizaRegisto;
	}

	public void setUtilizador_atualizaRegisto(String utilizador_atualizaRegisto) {
		this.utilizador_atualizaRegisto = utilizador_atualizaRegisto;
	}

	public Timestamp getData_inicio() {
		return data_inicio;
	}

	public void setData_inicio(Timestamp data_inicio) {
		this.data_inicio = data_inicio;
	}

	public Timestamp getData_fim() {
		return data_fim;
	}

	public void setData_fim(Timestamp data_fim) {
		this.data_fim = data_fim;
	}

	public Timestamp getData_avaliacao() {
		return data_avaliacao;
	}

	public void setData_avaliacao(Timestamp data_avaliacao) {
		this.data_avaliacao = data_avaliacao;
	}

	public double getTotal_solicitado() {
		return total_solicitado;
	}

	public void setTotal_solicitado(double total_solicitado) {
		this.total_solicitado = total_solicitado;
	}

	public double getTotal_possivel() {
		return total_possivel;
	}

	public void setTotal_possivel(double total_possivel) {
		this.total_possivel = total_possivel;
	}

	public int getDuracao() {
		return duracao;
	}

	public void setDuracao(int duracao) {
		this.duracao = duracao;
	}

	public double getTotal_capitalContratado() {
		return total_capitalContratado;
	}

	public void setTotal_capitalContratado(double total_capitalContratado) {
		this.total_capitalContratado = total_capitalContratado;
	}

	public double getTotal_impostosContratado() {
		return total_impostosContratado;
	}

	public void setTotal_impostosContratado(double total_impostosContratado) {
		this.total_impostosContratado = total_impostosContratado;
	}

	public String getDescricao_objetoCriado() {
		return descricao_objetoCriado;
	}

	public void setDescricao_objetoCriado(String descricao_objetoCriado) {
		this.descricao_objetoCriado = descricao_objetoCriado;
	}

	public int getScoring() {
		return scoring;
	}

	public void setScoring(int scoring) {
		this.scoring = scoring;
	}

	public Timestamp getData_alteracaoEstado() {
		return data_alteracaoEstado;
	}

	public void setData_alteracaoEstado(Timestamp data_alteracaoEstado) {
		this.data_alteracaoEstado = data_alteracaoEstado;
	}
	
	
	
}
