package com.iscte.youcredit.actions;

import java.io.*;
import java.net.*;
import java.util.*;

import org.openxava.actions.*;
import org.openxava.util.*;

import com.iscte.youcredit.model.*;
import java.time.*;
import java.math.*;
import javax.persistence.*;
import org.openxava.actions.*;
import org.openxava.jpa.*;


public class ActionSimulacaoObterParecer extends OnChangePropertyBaseAction {
	private static Simulacao simulacao;
	private static String nome;
	private static String nif;
	private static int rating;
	private static double salario;
	private static int scoring;
	private static String urlAPI = "http://localhost:8080/YouCreditAPI/";
	
	
	@Override
	public void execute() throws Exception {
		 
		 String RetornoCall;
		 String RetornoCall2;
		 String novo;
		
		 
		novo = (String) getNewValue();
		
		System.out.println(novo);
		
		
		if(novo == null || novo.equals("Para aprovação") == false && novo.equals("Para Ativar") == false ) { return ;}
		
		if (novo.equals("Para aprovação")) {
			
			
			calcularScoring();
			
			simulacao = (Simulacao) getView().getEntity();
			nome = Utilizador.getUtilizadornome(Users.getCurrent());
			nif = Utilizador.getUtilizadornif(Users.getCurrent()); 
			scoring = (int) getView().getValue("scoring");
			RetornoCall = callGet("ObterParecerIC");
	    
	    System.out.println("callGet "+RetornoCall);
	    
	    String [] g = RetornoCall.split("\n");
	    System.out.println(g[g.length-2]);
	    System.out.println(getView().getValue("scoring"));
	    
	    
	    	if(g[g.length-2].endsWith(": OK,") && scoring == 5)  {
	    		System.out.println("Passo1");
	    		simulacao.setEstadoSimulacaoid(3);
	    		System.out.println(simulacao.getEstadoSimulacaoid());
	    		
	    		
	    		
	    		HashMap<String, String> estadoo = new HashMap<String, String>(); 
				estadoo.put("estado", "Aprovado");
				getView().setValue("classeEstadoSimulacao", estadoo);
	    		
	    		RetornoCall2 = callGetCRM("CRMSimulacao");
	    		
	    		System.out.println("callGet "+RetornoCall2);
	     }
	 }
		
		if(novo.equals("Para Ativar")) {
			
			System.out.println("Passo2");
			simulacao.setEstadoSimulacaoid(3);
			simulacao = (Simulacao) getView().getEntity();
			RetornoCall2 = callGetCRM("CRMSimulacao");
			
			
			System.out.println("callGet "+RetornoCall2);
			
		}	
	}
	
	
	private static String callGet(String servico) throws IOException{
		
		String listaParametros = "?simulacaoid=" + URLEncoder.encode(String.valueOf(simulacao.getSimulacaoid()),"UTF-8");
		listaParametros += "&produto=" + URLEncoder.encode(String.valueOf(simulacao.getClasseProduto()),"UTF-8");
		listaParametros += "&nome=" + URLEncoder.encode(nome,"UTF-8");
		listaParametros += "&nif=" + URLEncoder.encode(nif,"UTF-8");
		listaParametros += "&totalsolicitado=" + URLEncoder.encode(String.valueOf(simulacao.getTotalSolicitado()),"UTF-8");
		if(simulacao.getClasseEstadoSimulacao().getEstado().contains("Para aprovação")) {
			listaParametros += "&evento=" + URLEncoder.encode("parecer","UTF-8");

		}
		HttpURLConnection connection = (HttpURLConnection) new URL(urlAPI + servico + listaParametros).openConnection();

		connection.setRequestMethod("GET");

		int responseCode = connection.getResponseCode();
		if (responseCode == 200){
				String response = "";
				Scanner scanner = new Scanner(connection.getInputStream());
				while(scanner.hasNextLine()){
					response += scanner.nextLine();
					response += "\n";
				}
				scanner.close();

				return response;
			}
			
		return null;
	}	
	
	
	private static String callGetCRM(String servico) throws IOException{
		String listaParametros = "?simulacaoid=" + URLEncoder.encode(String.valueOf(simulacao.getSimulacaoid()),"UTF-8");
		listaParametros += "&produto=" + URLEncoder.encode(simulacao.getClasseProduto().getProduto(),"UTF-8");
		listaParametros += "&instituicaocredito=" + URLEncoder.encode(simulacao.getClasseProduto().getClasseinstituicaocredito().getNome(),"UTF-8");
		listaParametros += "&entidadeid=" + URLEncoder.encode(String.valueOf(simulacao.getClasseEntidade().getEntidadeid()),"UTF-8");
		listaParametros += "&nome=" + URLEncoder.encode(simulacao.getClasseEntidade().getNome(),"UTF-8");
		listaParametros += "&nif=" + URLEncoder.encode(simulacao.getClasseEntidade().getNif(),"UTF-8");
		listaParametros += "&telefone=" + URLEncoder.encode(simulacao.getClasseEntidade().getTelefone(),"UTF-8");
		listaParametros += "&email=" + URLEncoder.encode(simulacao.getClasseEntidade().getEmail(),"UTF-8");
		listaParametros += "&rating=" + URLEncoder.encode(String.valueOf(simulacao.getClasseEntidade().getRating()),"UTF-8");
		listaParametros += "&estadoentidade=" + URLEncoder.encode(simulacao.getClasseEntidade().getClasseestadoentidade().getEstadoentidade(),"UTF-8");
		listaParametros += "&totalsolicitado=" + URLEncoder.encode(String.valueOf(simulacao.getTotalSolicitado()),"UTF-8");
		listaParametros += "&totalpossivel=" + URLEncoder.encode(String.valueOf(simulacao.getTotalPossivel()),"UTF-8");
		listaParametros += "&estadosimulacao=" + URLEncoder.encode(simulacao.getClasseEstadoSimulacao().getEstado(),"UTF-8");
		listaParametros += "&scoring=" + URLEncoder.encode(String.valueOf(simulacao.getScoring()),"UTF-8");

		if(simulacao.getClasseEstadoSimulacao().getEstado().contains("Aprovado")) {
			listaParametros += "&evento=" + URLEncoder.encode("criar","UTF-8");
			}
		if(simulacao.getClasseEstadoSimulacao().getEstado().contains("Para Ativar")) {
			listaParametros += "&evento=" + URLEncoder.encode("alterar","UTF-8");
		}
		
		
		HttpURLConnection connection = (HttpURLConnection) new URL(urlAPI + servico + listaParametros).openConnection();

		
		connection.setRequestMethod("GET");

		int responseCode = connection.getResponseCode();
		if (responseCode == 200){
				String response = "";
				Scanner scanner = new Scanner(connection.getInputStream());
				while(scanner.hasNextLine()){
					response += scanner.nextLine();
					response += "\n";
				}
				scanner.close();

				return response;
			}
			
		return null;
	}
	
	
	public void calcularScoring() {
		rating = Utilizador.getUtilizadorRating(Users.getCurrent());
		salario = Utilizador.getUtilizadorSalario(Users.getCurrent());
		double totalSolicitado = (double) getView().getValueInt("totalSolicitado");
		
		if (rating == 5 && totalSolicitado < (salario * 0.05) ) {
			scoring = 5;}
		else 
			if (rating == 5 && totalSolicitado < (salario * 0.10) ) {
				scoring = 4;}
		else 
			if (rating > 3 && totalSolicitado < (salario * 0.20) ) {
				scoring = 3;}
		else 
			if (rating > 2 && totalSolicitado < (salario * 0.10) ) {
				scoring = 2;}
		else 
				scoring = 1;
		
		
		getView().setValue("scoring", scoring);
		
		
     }
	
	
	
	public void id() {
		int resultado = 0;
		
		String querySql = "SELECT estadoSimulacao_id FROM EstadoSimulacao WHERE estado = " + "Aprovado";
    	Query conexao = XPersistence.getManager().createNativeQuery(querySql);
    	if (conexao !=null) {
      	try {resultado = (int) conexao.getSingleResult();}
      	catch (Exception erro) {resultado=9;};}
   
    	System.out.println("estado_entidade_id= "+resultado); 
    	simulacao.getClasseEstadoSimulacao().setEstadoSimulacaoid(resultado);
    	
      
	}
	
	
		
		
		
	}


	
	
	


