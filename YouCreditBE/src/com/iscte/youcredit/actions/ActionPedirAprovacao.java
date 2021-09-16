package com.iscte.youcredit.actions;

import java.util.*;

import javax.persistence.*;

import org.openxava.actions.*;
import org.openxava.jpa.*;

import com.iscte.youcredit.model.*;

public class ActionPedirAprovacao extends ViewBaseAction {
	private static Simulacao sim;

	@Override
	public void execute() throws Exception {
		sim = (Simulacao) getView().getEntity();
		System.out.println("Vai entrar");
		
		
		
		if (verificarEstadoAnterior()) {
			
			
			System.out.println("Passo1");
			sim.setEstadoSimulacaoid(2);
			System.out.println(sim.getEstadoSimulacaoid());
			HashMap<String, String> estado = new HashMap(); 
			estado.put("estado", "Para Aprovação");
			getView().setValue("classeEstadoSimulacao", estado);
		} else {
			System.out.println("Nao muda");

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

}
