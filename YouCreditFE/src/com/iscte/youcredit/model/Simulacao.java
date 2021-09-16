package com.iscte.youcredit.model;

import java.sql.*;
import java.time.*;

import javax.persistence.*;

import org.openxava.annotations.*;
import org.openxava.util.*;

import com.iscte.youcredit.actions.*;

@View(members = "Simulação [" +
//"simulacaoid;" +
		" simulacao, classeProduto, classeEntidade;" + "dataInicio, dataFim;" + "dataAvaliacao;" + "totalSolicitado, totalPossivel;"
		+ "duracao;" + "totalCapitalContratado, totalDespesaContratada;" + "totalImpostosContratado;"
		+ "descricaoObjetoCriado;" + "scoring;" + "classeEstadoSimulacao, dataAlteracaoEstado;" + "]" + "Logging ["
		+ "estadoRegisto;" + "dataCriacaoRegisto, dataAtualizacaoRegisto;"
		+ "utilizadorCriaRegisto, utilizadorAtualizaRegisto" + "]"

)

@Entity
@Table(name = "Simulacao")

public class Simulacao {
	
	
	@Id 
	@Column(name="simulacao",length = 25)
	private String simulacao;

	
	@Hidden
	@Column(name = "simulacao_id")
	private int simulacaoid;

//	@Required
	@Column(name = "codigo_produto")
	private int codigoProduto;

	@Required
	@ManyToOne(fetch = FetchType.LAZY)
	@DescriptionsList(descriptionProperties = "produto")
	private PR_Produto classeProduto;

	//@ReadOnly
	@Column(name = "codigo_entidade")
	private int codigoEntidade;

	
	@Required
	@DefaultValueCalculator(com.iscte.youcredit.actions.ActionPreencherEntidade.class)
	@ManyToOne(fetch = FetchType.LAZY)
	@DescriptionsList(descriptionProperties = "entidade")
	private EN_Entidade classeEntidade;

//	@Required
	@Column(name = "estadoSimulacao_id")
	private int estadoSimulacaoid;

	@Required
	@ReadOnly
	@ManyToOne(fetch = FetchType.LAZY)
	@DefaultValueCalculator(com.iscte.youcredit.actions.ActionPreencherRegistado.class)
    @DescriptionsList(descriptionProperties = "estado")
	private EstadoSimulacao classeEstadoSimulacao;

	@ReadOnly
	@Column(name = "estado_registo", length = 50)
	private String estadoRegisto;

	@ReadOnly
	@Column(name = "utilizador_criaRegisto")
	private int utilizadorCriaRegisto;

	@ReadOnly
	@Column(name = "utilizador_atualizaRegisto" )
	private int utilizadorAtualizaRegisto;

	@ReadOnly
	@Column(name = "data_criacaoRegisto")
	private LocalDate dataCriacaoRegisto;

	@ReadOnly
	@Column(name = "data_atualizacaoRegisto")
	private LocalDate dataAtualizacaoRegisto;

	@Required
	@Column(name = "data_inicio")
	private LocalDate dataInicio;

	@ReadOnly
	@Column(name = "data_fim")
	private LocalDate dataFim;

	@ReadOnly
	@Column(name = "data_avaliacao")
	private LocalDate dataAvaliacao;

	@Required
	@Column(name = "total_solicitado")
	private double totalSolicitado;

	@ReadOnly
	@Column(name = "total_possivel")
	private double totalPossivel;

	@Required
	@Column(name = "duracao")
	private int duracao;

	@ReadOnly
	@Column(name = "total_capitalContratado")
	private double totalCapitalContratado;

	@ReadOnly
	@Column(name = "total_despesaContratada")
	private double totalDespesaContratada;

	@ReadOnly
	@Column(name = "total_impostosContratado")
	private double totalImpostosContratado;

	@Required
	@Column(name = "descricao_objetoCredito", length = 500)
	private String descricaoObjetoCriado;

	@ReadOnly
	@Column(name = "scoring")
	private int scoring;

	@ReadOnly
	@Column(name = "data_alteracaoEstado")
	private Timestamp dataAlteracaoEstado;
	
	
	public String getSimulacao() {
		return simulacao;
		
	}
	

	public void setSimulacao(String simulacao) {
		
		
//		
//		String id =  String.valueOf(simulacaoid);
//		String produto = classeProduto.getProduto();
//		String d = dataCriacaoRegisto.toString();
//		simulacao = id + produto + d ;
//		
		
		
		this.simulacao = simulacao;
	}
		

	public int getSimulacaoid() {
		return simulacaoid;
	}

	public void setSimulacaoid(int simulacaoid) {
//		this.simulacaoid = simulacaoid;
	}

	public int getCodigoProduto() {
		return codigoProduto;
	}

	public void setCodigoProduto(int codigoProduto) {
		this.codigoProduto = codigoProduto;
	}

	public PR_Produto getClasseProduto() {
		return classeProduto;
	}

	public void setClasseProduto(PR_Produto classeProduto) {
		this.classeProduto = classeProduto;
		this.codigoProduto = classeProduto.getProdutoid();
	}

	public int getCodigoEntidade() {
		return codigoEntidade;
	}

	public void setCodigoEntidade(int codigoEntidade) {
		this.codigoEntidade = codigoEntidade;
	}

	public EN_Entidade getClasseEntidade() {
		return classeEntidade;
	}

	public void setClasseEntidade(EN_Entidade classeEntidade) {
		this.classeEntidade = classeEntidade;
		this.codigoEntidade = classeEntidade.getEntidadeid();
	}

	public int getEstadoSimulacaoid() {
		return estadoSimulacaoid;
	}

	public void setEstadoSimulacaoid(int estadoSimulacaoid) {
		this.estadoSimulacaoid = estadoSimulacaoid;
	}

	public EstadoSimulacao getClasseEstadoSimulacao() {
		return classeEstadoSimulacao;
	}

	public void setClasseEstadoSimulacao(EstadoSimulacao classeEstadoSimulacao) {
		this.classeEstadoSimulacao = classeEstadoSimulacao;
		
		this.estadoSimulacaoid = classeEstadoSimulacao.getEstadoSimulacaoid();
		
	}

	public String getEstadoRegisto() {
		return estadoRegisto;
	}

	public void setEstadoRegisto(String estadoRegisto) {
		this.estadoRegisto = estadoRegisto;
	}

	public int getUtilizadorCriaRegisto() {
		return utilizadorCriaRegisto;
	}

	public void setUtilizadorCriaRegisto(int utilizadorCriaRegisto) {
		utilizadorCriaRegisto = Utilizador.getUtilizadorid(Users.getCurrent());
		this.utilizadorCriaRegisto = utilizadorCriaRegisto;
	}

	public int getUtilizadorAtualizaRegisto() {
		return utilizadorAtualizaRegisto;
	}

	public void setUtilizadorAtualizaRegisto(int utilizadorAtualizaRegisto) {
		utilizadorAtualizaRegisto = Utilizador.getUtilizadorid(Users.getCurrent());
		this.utilizadorAtualizaRegisto = utilizadorAtualizaRegisto;
	}

	public LocalDate getDataCriacaoRegisto() {
		return dataCriacaoRegisto;
	}

	public void setDataCriacaoRegisto(LocalDate dataCriacaoRegisto) {
		if (dataCriacaoRegisto == null) {
			dataCriacaoRegisto = LocalDate.now();
		}
		this.dataCriacaoRegisto = dataCriacaoRegisto;
	}

	public LocalDate getDataAtualizacaoRegisto() {
		return dataAtualizacaoRegisto;
	}

	public void setDataAtualizacaoRegisto(LocalDate dataAtualizacaoRegisto) {
		
			dataAtualizacaoRegisto = LocalDate.now();
		
		this.dataAtualizacaoRegisto = dataAtualizacaoRegisto;
	}

	public LocalDate getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(LocalDate dataInicio) {
		if (dataInicio == null) {
		      dataInicio=LocalDate.now();}

		this.dataInicio = dataInicio;
	}

	public LocalDate getDataFim() {
		return dataFim;
	}

	public void setDataFim(LocalDate dataFim) {
		if(dataFim == null) {
			dataFim=LocalDate.now().plusYears(10);
			
		}
		this.dataFim = dataFim;
	}

	public LocalDate getDataAvaliacao() {
		return dataAvaliacao;
	}

	public void setDataAvaliacao(LocalDate dataAvaliacao) {
		this.dataAvaliacao = dataAvaliacao;
	}

	public double getTotalSolicitado() {
		return totalSolicitado;
	}

	public void setTotalSolicitado(double totalSolicitado) {
		if (totalSolicitado == 0 || totalSolicitado < 500 || totalSolicitado > 5000) {
			throw new javax.validation.ValidationException(
					XavaResources.getString("Valor <total solicitado> inválido", totalSolicitado));
		} 
			this.totalSolicitado = totalSolicitado;
	}
	

	public double getTotalPossivel() {
		return totalPossivel;
	}

	public void setTotalPossivel(double totalPossivel) {
		if (totalPossivel == 0)
			this.totalPossivel = 501;
		else if (totalPossivel < 500 || totalPossivel > 5000) {
			throw new javax.validation.ValidationException(
					XavaResources.getString("Valor <total possivel> inválido", totalPossivel));
		} else {
			this.totalPossivel = totalPossivel;
		}
	}

	public int getDuracao() {
		return duracao;
	}

	public void setDuracao(int duracao) {
		if (duracao == 0) {
			throw new javax.validation.ValidationException(
			   XavaResources.getString("Valor <duracao> inválido", duracao));
			
		}
		this.duracao = duracao;
	}

	public double getTotalCapitalContratado() {
		return totalCapitalContratado;
	}

	public void setTotalCapitalContratado(double totalCapitalContratado) {
		this.totalCapitalContratado = totalCapitalContratado;
	}

	public double getTotalDespesaContratada() {
		return totalDespesaContratada;
	}

	public void setTotalDespesaContratada(double totalDespesaContratada) {
		this.totalDespesaContratada = totalDespesaContratada;
	}

	public double getTotalImpostosContratado() {
		return totalImpostosContratado;
	}

	public void setTotalImpostosContratado(double totalImpostosContratado) {
		this.totalImpostosContratado = totalImpostosContratado;
	}

	public String getDescricaoObjetoCriado() {
		return descricaoObjetoCriado;
	}

	public void setDescricaoObjetoCriado(String descricaoObjetoCriado) {
		this.descricaoObjetoCriado = descricaoObjetoCriado;
	}

	public int getScoring() {
		return scoring;
	}

	public void setScoring(int scoring) {
		
		this.scoring = scoring;
	}

	public Timestamp getDataAlteracaoEstado() {
		return dataAlteracaoEstado;
	}

	public void setDataAlteracaoEstado(Timestamp dataAlteracaoEstado) {
		
		dataAlteracaoEstado = new Timestamp(System.currentTimeMillis());
		this.dataAlteracaoEstado = dataAlteracaoEstado;
	}

	@PrePersist @PreUpdate // Antes de criar ou alterar o registo
	private void validate()  throws Exception {
		if (dataInicio.isAfter(dataFim) ) { 
			throw new javax.validation.ValidationException(
			          XavaResources.getString(
			                "Data inicio superior a data de fim ",
			                dataInicio,dataFim)
			        );
			 }
		
//
//		if (totalSolicitado < totalPossivel ) {
//			throw new javax.validation.ValidationException(
//			          XavaResources.getString(
//			                "Total solicitado inferior a  total possivel ",
//			                totalSolicitado,totalPossivel)
//			        );
//		}
	}
}
	



