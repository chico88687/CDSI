package com.iscte.youcredit.actions;
import net.sf.jasperreports.web.actions.*;



import org.openxava.actions.*;

import com.iscte.youcredit.model.*;

public class ActionEntidadeEliminar extends DeleteAction {

	@Override
	public void execute() throws Exception {
		//EN_Entidade EntidadeClasse = (EN_Entidade) getView().getEntity();
        //EntidadeClasse.setEstadolog("I"); 
        //EntidadeClasse.setDatalog(LocalDate.now());

		getView().setValue("estadolog", "I");
		
		//super.execute();
	    //getView().refresh();
	}
}