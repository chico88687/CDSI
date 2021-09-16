package com.iscte.youcredit.actions;

import java.util.*;

import javax.persistence.*;

import org.openxava.actions.*;
import org.openxava.jpa.*;

import com.iscte.youcredit.model.*;

public class ActionPedirConversao extends ViewBaseAction {
	private static Simulacao sim;

	@Override
	public void execute() throws Exception {
		sim = (Simulacao) getView().getEntity();
		if (verificarEstadoAnterior()) {
			HashMap<String, String> estado = new HashMap();
			estado.put("estado", "Para Ativar");
			getView().setValue("classeEstadoSimulacao", estado);
		} else {
			System.out.println("Nao muda");
		}
	}

	public boolean verificarEstadoAnterior() {
		boolean continuar = false;
		String querySql = "SELECT estado FROM EstadoSimulacao WHERE estadoSimulacaoid = " + "'"
				+ sim.getEstadoSimulacaoid() + "'";
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

}