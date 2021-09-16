package com.iscte.youcredit.actions;

import org.openxava.actions.*;
import org.openxava.util.*;

import com.iscte.youcredit.model.*;

public class ActionSimulacaoInicializar extends ViewBaseAction {
	private static Simulacao simulacao;

	@Override
	public void execute() throws Exception {
		try {
			simulacao = (Simulacao) getView().getEntity();
			int scoring = simulacao.getScoring();
			
			System.out.println(scoring);
			
				
				if(scoring == 5) {
					System.out.println(scoring);
					getView().setEditable("classeEstadoSimulacao",false);
				}
				
				if(scoring == 4) {
					if(Utilizador.getUtilizadorarea(Users.getCurrent()).compareToIgnoreCase("com") == 0){
					 getView().setEditable("classeEstadoSimulacao",true);
				   }
					else {
						getView().setEditable("classeEstadoSimulacao",false);
					}
				}
				
				if(scoring == 3)
					if(Utilizador.getUtilizadorarea(Users.getCurrent()).compareToIgnoreCase("crd") == 0){
					getView().setEditable("classeEstadoSimulacao", true);
				}
					else {
						getView().setEditable("classeEstadoSimulacao", false);
					}
				
				if(scoring == 2) {
					if(Utilizador.getUtilizadorarea(Users.getCurrent()).compareToIgnoreCase("rco") == 0){
					getView().setEditable("classeEstadoSimulacao", true);
				}
				
					else {
						getView().setEditable("classeEstadoSimulacao", false);
					}
				}
				if(scoring == 1) {
					if(Utilizador.getUtilizadorarea(Users.getCurrent()).compareToIgnoreCase("adm") == 0){
					getView().setEditable("classeEstadoSimulacao", true);

			   }
					else {
						getView().setEditable("classeEstadoSimulacao", false);
					}
				}
				
			
		} catch (Exception erro) {}
				
		}
		
		
		
	}
