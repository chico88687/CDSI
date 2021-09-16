package com.iscte.youcredit.actions;

import org.openxava.calculators.*;
import org.openxava.jpa.*;
import org.openxava.util.*;

import com.iscte.youcredit.model.*;

public class ActionPreencherRegistado implements ICalculator {

	@Override
	public Object calculate() throws Exception {
		EstadoSimulacao estado = XPersistence.getManager().find(EstadoSimulacao.class, "Registado");
		return estado;
	}

}