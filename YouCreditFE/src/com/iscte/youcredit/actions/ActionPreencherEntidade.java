package com.iscte.youcredit.actions;

import java.util.*;

import org.hsqldb.rights.*;
import org.openxava.calculators.*;
import org.openxava.jpa.*;
import org.openxava.util.*;

import com.iscte.youcredit.model.*;

public class ActionPreencherEntidade implements ICalculator {

	@Override
	public Object calculate() throws Exception {
		EN_Entidade entidade = XPersistence.getManager().find(EN_Entidade.class, Utilizador.getUtilizadorEntidade(Users.getCurrent()));
		return entidade;
	}
}