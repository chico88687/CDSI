package com.iscte.youcredit.actions;

import org.openxava.actions.*;

public class ActionSimulacaoRefresh extends  ViewBaseAction {

	@Override
	public void execute() throws Exception {
		getView().findObject();
		
	}

	
}
