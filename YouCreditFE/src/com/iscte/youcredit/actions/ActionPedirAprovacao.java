package com.iscte.youcredit.actions;

import java.io.*;
import java.net.*;
import java.util.*;

import javax.persistence.*;

import org.openxava.actions.*;
import org.openxava.jpa.*;
import org.openxava.util.*;

import com.iscte.youcredit.model.*;

public class ActionPedirAprovacao extends ViewBaseAction {
	private static Simulacao sim;
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
		
		sim = (Simulacao) getView().getEntity();
		System.out.println("Vai entrar");
		if (verificarEstadoAnterior()) {
			
			System.out.println("Passo1");
			idParaAprovacao();
			System.out.println(sim.getEstadoSimulacaoid());
			
			HashMap<String, String> estado = new HashMap(); 
			estado.put("estado", "Para Aprovação");
			getView().setValue("classeEstadoSimulacao", estado);
			
			
			calcularScoring();
			
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
	    		idAprovado();
	    		System.out.println(sim.getEstadoSimulacaoid());
	    		
	    		
	    		
	    		HashMap<String, String> estadoo = new HashMap<String, String>(); 
				estadoo.put("estado", "Aprovado");
				getView().setValue("classeEstadoSimulacao", estadoo);

	    		RetornoCall2 = callGetCRM("CRMSimulacao");
	    		
	    		System.out.println("callGet "+RetornoCall2);
	     }
			
			
			
			
			
		} else {
			System.out.println("Nao muda");
			XavaResources.getString("Não é possivel converter");

		}
		

 }
		
		
		
	

	public boolean verificarEstadoAnterior() {
		System.out.println("Entrou");
		
		
		boolean continuar = false;
		String querySql = "SELECT estado FROM EstadoSimulacao WHERE estadoSimulacaoid = " + "'"
				+ sim.getClasseEstadoSimulacao().getEstadoSimulacaoid() + "'";
		System.out.println(querySql);
		Query conexao = XPersistence.getManager().createQuery(querySql);
		if (conexao != null) {
			try {
				String estado_anterior = (String) conexao.getSingleResult();
				if (estado_anterior.equals("Registado"))
					continuar = true;
			} catch (Exception erro) {
				System.out.println("Erro no select");
			}
		}
		System.out.println("boolean = " + continuar);
		return continuar;
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
	
private static String callGet(String servico) throws IOException{
		
		String listaParametros = "?simulacaoid=" + URLEncoder.encode(String.valueOf(sim.getSimulacaoid()),"UTF-8");
		listaParametros += "&produto=" + URLEncoder.encode(String.valueOf(sim.getClasseProduto()),"UTF-8");
		listaParametros += "&nome=" + URLEncoder.encode(nome,"UTF-8");
		listaParametros += "&nif=" + URLEncoder.encode(nif,"UTF-8");
		listaParametros += "&totalsolicitado=" + URLEncoder.encode(String.valueOf(sim.getTotalSolicitado()),"UTF-8");
		if(sim.getClasseEstadoSimulacao().getEstado().contains("Registado")) {
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
	String listaParametros = "?simulacaoid=" + URLEncoder.encode(String.valueOf(sim.getSimulacaoid()),"UTF-8");
	listaParametros += "&produto=" + URLEncoder.encode(sim.getClasseProduto().getProduto(),"UTF-8");
	listaParametros += "&instituicaocredito=" + URLEncoder.encode(sim.getClasseProduto().getClasseinstituicaocredito().getNome(),"UTF-8");
	listaParametros += "&entidadeid=" + URLEncoder.encode(String.valueOf(sim.getClasseEntidade().getEntidadeid()),"UTF-8");
	listaParametros += "&nome=" + URLEncoder.encode(sim.getClasseEntidade().getNome(),"UTF-8");
	listaParametros += "&nif=" + URLEncoder.encode(sim.getClasseEntidade().getNif(),"UTF-8");
	listaParametros += "&telefone=" + URLEncoder.encode(sim.getClasseEntidade().getTelefone(),"UTF-8");
	listaParametros += "&email=" + URLEncoder.encode(sim.getClasseEntidade().getEmail(),"UTF-8");
	listaParametros += "&rating=" + URLEncoder.encode(String.valueOf(sim.getClasseEntidade().getRating()),"UTF-8");
	listaParametros += "&estadoentidade=" + URLEncoder.encode(sim.getClasseEntidade().getClasseestadoentidade().getEstadoentidade(),"UTF-8");
	listaParametros += "&totalsolicitado=" + URLEncoder.encode(String.valueOf(sim.getTotalSolicitado()),"UTF-8");
	listaParametros += "&totalpossivel=" + URLEncoder.encode(String.valueOf(sim.getTotalPossivel()),"UTF-8");
	listaParametros += "&estadosimulacao=" + URLEncoder.encode("Para Aprovação","UTF-8");
	listaParametros += "&scoring=" + URLEncoder.encode(String.valueOf(scoring),"UTF-8");

	if(sim.getClasseEstadoSimulacao().getEstado().contains("Aprovado")) {
		listaParametros += "&evento=" + URLEncoder.encode("criar","UTF-8");
		}
	if(sim.getClasseEstadoSimulacao().getEstado().contains("Para Ativar")) {
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


public void idAprovado() {
	int resultado = 0;
	
	String querySql = "SELECT estadoSimulacao_id FROM EstadoSimulacao WHERE estado = " + "'Aprovado'";
	Query conexao = XPersistence.getManager().createNativeQuery(querySql);
	if (conexao !=null) {
  	try {resultado = (int) conexao.getSingleResult();
  	}
  	catch (Exception erro) {resultado=9;};}

	System.out.println("estado_entidade_id= "+resultado); 
	sim.setEstadoSimulacaoid(resultado);
	sim.getClasseEstadoSimulacao().setEstadoSimulacaoid(resultado );
	
  
}

public void idParaAprovacao() {
	int resultado = 0;
	
	String querySql = "SELECT estadoSimulacao_id FROM EstadoSimulacao WHERE estado = " + "'Para Aprovação'";
	Query conexao = XPersistence.getManager().createNativeQuery(querySql);
	if (conexao !=null) {
  	try {resultado = (int) conexao.getSingleResult();
  	}
  	catch (Exception erro) {resultado=9;};}

	System.out.println("estado_entidade_id= "+resultado); 
	sim.setEstadoSimulacaoid(resultado);
	sim.getClasseEstadoSimulacao().setEstadoSimulacaoid(resultado );
	
}


	
	
	
	
}
