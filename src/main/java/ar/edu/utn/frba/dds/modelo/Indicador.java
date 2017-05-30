package main.java.ar.edu.utn.frba.dds.modelo;

import java.util.ArrayList;
import java.util.Collection;

import main.java.ar.edu.utn.frba.dds.util.ExpressionParser;

public class Indicador {

	public String nombreIndicador;
	public String expresion;
	
	private Collection<Indicador> indicadores = new ArrayList<Indicador>();
	private Collection<Cuenta> cuentas = new ArrayList<Cuenta>();
	
	private ExpressionParser parser;
	
	public Double calcular(Empresa empresa, String periodo){
		Double resultado = null;
		return resultado;
	}
	
	public String getNombreIndicador() {
		return nombreIndicador;
	}
	public void setNombreIndicador(String nombreIndicador) {
		this.nombreIndicador = nombreIndicador;
	}
	public String getCadena() {
		return expresion;
	}
	public void setCadena(String cadena) {
		this.expresion = cadena;
	}
}
