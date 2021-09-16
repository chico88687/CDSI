package com.iscte.youcredit.actions;

import java.io.*;
import java.net.*;
import java.util.*;

import javax.persistence.*;

import org.openxava.actions.*;
import org.openxava.jpa.*;
import org.openxava.util.*;

import com.iscte.youcredit.model.*;

public class ActionPedirConversao extends ViewBaseAction {
	private static Simulacao simulacao;
	private static String urlAPI = "http://localhost:8080/YouCreditAPI/";


	@Override
	public void execute() throws Exception {
		String RetornoCall2;
		
		
		simulacao = (Simulacao) getView().getEntity();
		if (verificarEstadoAnterior()) {
			
			
			
			idParaAtivar();
			
			HashMap<String, String> estado = new HashMap();
			estado.put("estado", "Para Ativar");
			getView().setValue("classeEstadoSimulacao", estado);
			
			
			
			System.out.println("Passo2");
			simulacao = (Simulacao) getView().getEntity();
			RetornoCall2 = callGetCRM("CRMSimulacao");
			
			
			System.out.println("callGet "+RetornoCall2);
			
		} else {
			System.out.println("Nao muda");
			XavaResources.getString("Não é possivel converter");
		}
		
		
	}

	public boolean verificarEstadoAnterior() {
		boolean continuar = false;
		String querySql = "SELECT estado FROM EstadoSimulacao WHERE estadoSimulacaoid = " + "'"
				+ simulacao.getClasseEstadoSimulacao().getEstadoSimulacaoid() + "'";
		System.out.println(querySql);
		Query conexao = XPersistence.getManager().createQuery(querySql);
		if (conexao != null) {
			try {
				String estado_anterior = (String) conexao.getSingleResult();
				if (estado_anterior.equals("Aprovado"))
					continuar = true;
			} catch (Exception erro) {
				System.out.println("Erro no select");
				
			}
		}
		System.out.println("boolean = " + continuar);
		return continuar;
	}
	
	
	public void idParaAtivar() {
		int resultado = 0;
		
		String querySql = "SELECT estadoSimulacao_id FROM EstadoSimulacao WHERE estado = " + "'Para Ativar'";
		Query conexao = XPersistence.getManager().createNativeQuery(querySql);
		if (conexao !=null) {
	  	try {resultado = (int) conexao.getSingleResult();
	  	}
	  	catch (Exception erro) {resultado=9;};}

		System.out.println("estado_entidade_id= "+resultado); 
		simulacao.setEstadoSimulacaoid(resultado);
		simulacao.getClasseEstadoSimulacao().setEstadoSimulacaoid(resultado);
		
	  
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
		listaParametros += "&estadosimulacao=" + URLEncoder.encode("Para Ativar","UTF-8");
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

}