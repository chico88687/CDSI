package com.iscte.youcredit.model;

import javax.persistence.*;
import org.openxava.jpa.*;

public class Utilizador {
       
        public static int getUtilizadorid(String yutilizador)
       {int pos_separador = yutilizador.indexOf("_");
        String utilizadortemp=""; 
        int utilizadorid=0;  
        if (pos_separador > 0)
        	{utilizadortemp= yutilizador.substring(pos_separador+1, yutilizador.length());}
        if (utilizadortemp.matches("[0-9]")) {utilizadorid= Integer.parseInt(utilizadortemp);}
        return utilizadorid;
        }
        
        
        public static String getUtilizadorEntidade(String yutilizador) {
    		String entidade = "";
    		String querySql = "SELECT entidade FROM EN_Entidade WHERE entidade_id = " + getUtilizadorid(yutilizador);
    		Query conexao = XPersistence.getManager().createQuery(querySql);
    		if (conexao != null) {
    			try {
    				entidade = (String) conexao.getSingleResult();
    			} catch (Exception erro) {
    			}
    			;
    		}
    		return entidade;
    	}
        
        
        

        public static String getUtilizadorarea(String yutilizador)
       {int pos_separador = yutilizador.indexOf("_");
        String utilizadorarea=""; 
        if (pos_separador > 0)
        	{utilizadorarea= yutilizador.substring(0,pos_separador);}
        return utilizadorarea;
        }
        
        
//        public static String getUtilizadorparecer(String yutilizador)
//        {String parecer="";
//     	String querySql = "SELECT parecer_resultado FROM log_parecer_quotacao WHERE log_parecer_quotacao_id = " + getUtilizadorid(yutilizador);
//         Query conexao = XPersistence.getManager().createQuery(querySql);
//        	if (conexao != null) {
//         try {parecer = (String) conexao.getSingleResult();} 
//        	catch(Exception erro) {};}
//     	return parecer;
//        }

       public static String getUtilizadornome(String yutilizador)
       {String utilizadornome="";
    	String querySql = "SELECT nome FROM EN_Entidade WHERE entidade_id = " + getUtilizadorid(yutilizador);
        Query conexao = XPersistence.getManager().createQuery(querySql);
       	if (conexao != null) {
        try {utilizadornome = (String) conexao.getSingleResult();} 
       	catch(Exception erro) {};}
    	return utilizadornome;
       }

       public static String getUtilizadornif(String yutilizador)
       {String utilizadornif="";
    	String querySql = "SELECT nif FROM EN_Entidade WHERE entidade_id = " + getUtilizadorid(yutilizador);
        Query conexao = XPersistence.getManager().createQuery(querySql);
       	if (conexao != null) {
        try {utilizadornif = (String) conexao.getSingleResult();} 
       	catch(Exception erro) {};}
    	return utilizadornif;
       }

       
       
       public static int getUtilizadorestado(String yutilizador)
       {int estadoid=0;
    	String querySql = "SELECT estado_entidade_id FROM EN_Entidade WHERE entidade_id = " + getUtilizadorid(yutilizador);
    	Query conexao = XPersistence.getManager().createQuery(querySql);
      	if (conexao !=null) {
      	try {estadoid = (int) conexao.getSingleResult();}
      	catch (Exception erro) {};}
      	System.out.println("Raios");
      	return estadoid;
       }

	public static int getUtilizadorRating(String yutilizador) {
	int rating = 0;
	String querySql = "SELECT rating FROM EN_Entidade WHERE entidade_id = " + getUtilizadorid(yutilizador);
	Query conexao = XPersistence.getManager().createQuery(querySql);
		if (conexao !=null) {
			try {rating = (int) conexao.getSingleResult();}
				catch (Exception erro) {};}
	  				System.out.println("Raios");
	     	return rating;
	   }
	
	public static double getUtilizadorSalario(String yutilizador) {
		double salario = 0.0;
		String querySql = "SELECT salarioanual FROM EN_Entidade WHERE entidade_id = " + getUtilizadorid(yutilizador);
		Query conexao = XPersistence.getManager().createQuery(querySql);
		if (conexao !=null) {
			try {salario = (double) conexao.getSingleResult();}
				catch (Exception erro) {};}
	  				System.out.println("Raios");
	     	return salario;
	   }
			

		
	}

