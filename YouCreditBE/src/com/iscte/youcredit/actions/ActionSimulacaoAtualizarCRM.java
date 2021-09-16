package com.iscte.youcredit.actions;

import java.io.*;
import java.net.*;
import java.util.*;

import org.openxava.actions.*;


import com.iscte.youcredit.model.*;

public class ActionSimulacaoAtualizarCRM extends OnChangePropertyBaseAction  {
	private static Simulacao simulacao;
    private static String urlAPI = "http://localhost:8080/YouCreditAPI/"; 

	
	@Override
	public void execute() throws Exception {
		
		 String RetornoCall;
		 String estado;
		
			System.out.println("Fodasse");

		 
		 
		estado = (String) getNewValue();
		
		System.out.println(estado);
		
		
		if(estado == null || estado.equals("Aprovado") == false ) { return ;}
		
		if (estado.equals("Aprovado")) {
			
			simulacao = (Simulacao) getView().getEntity();

	    RetornoCall = callGet("CRMSimulacao");
	        
	    System.out.println("callGet "+RetornoCall);
		}
		
	}
	
	private static String callGet(String servico) throws IOException{
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

	
//	private static String callPost(String servico) throws IOException{
//		String retorno="";
//		HttpURLConnection connection = (HttpURLConnection) new URL(urlAPI + servico).openConnection();
//
//		String listaParametros = "?simulacaoid=" + URLEncoder.encode(String.valueOf(simulacao.getSimulacaoid()),"UTF-8");
//		listaParametros += "&produto=" + URLEncoder.encode(simulacao.getClasseProduto().getProduto(),"UTF-8");
//		listaParametros += "&instituicaocredito=" + URLEncoder.encode(simulacao.getClasseProduto().getClasseinstituicaocredito().getNome(),"UTF-8");
//		listaParametros += "&entidadeid=" + URLEncoder.encode(String.valueOf(simulacao.getClasseEntidade().getEntidadeid()),"UTF-8");
//		listaParametros += "&nome=" + URLEncoder.encode(simulacao.getClasseEntidade().getNome(),"UTF-8");
//		listaParametros += "&nif=" + URLEncoder.encode(simulacao.getClasseEntidade().getNif(),"UTF-8");
//		listaParametros += "&telefone=" + URLEncoder.encode(simulacao.getClasseEntidade().getTelefone(),"UTF-8");
//		listaParametros += "&email=" + URLEncoder.encode(simulacao.getClasseEntidade().getEmail(),"UTF-8");
//		listaParametros += "&rating=" + URLEncoder.encode(String.valueOf(simulacao.getClasseEntidade().getRating()),"UTF-8");
//		listaParametros += "&estadoentidade=" + URLEncoder.encode(simulacao.getClasseEntidade().getClasseestadoentidade().getEstadoentidade(),"UTF-8");
//		listaParametros += "&totalsolicitado=" + URLEncoder.encode(String.valueOf(simulacao.getTotalSolicitado()),"UTF-8");
//		listaParametros += "&totalpossivel=" + URLEncoder.encode(String.valueOf(simulacao.getTotalPossivel()),"UTF-8");
//		listaParametros += "&estadosimulacao=" + URLEncoder.encode(simulacao.getClasseEstadoSimulacao().getEstado(),"UTF-8");
//		listaParametros += "&scoring=" + URLEncoder.encode(String.valueOf(simulacao.getScoring()),"UTF-8");
//		listaParametros += "&scoring=" + URLEncoder.encode(String.valueOf(simulacao.getScoring()),"UTF-8");
//
//		if(simulacao.getClasseEstadoSimulacao().getEstado().equals("Aprovado")) {
//			listaParametros += "&evento=" + URLEncoder.encode("criar","UTF-8");}
//		
//		connection.setDoOutput(true);
//	    OutputStreamWriter wr = new OutputStreamWriter(connection.getOutputStream());
//	    wr.write(listaParametros);
//	    wr.flush();
//		
//		int responseCode = connection.getResponseCode();
//		if (responseCode == 200){
//			String response = "";
//			Scanner scanner = new Scanner(connection.getInputStream());
//			while(scanner.hasNextLine()){
//				response += scanner.nextLine();
//				response += "\n";
//			}
//			scanner.close();
//
//			return response;
//		}
//	return null;
// }
	
	
	
}
	


