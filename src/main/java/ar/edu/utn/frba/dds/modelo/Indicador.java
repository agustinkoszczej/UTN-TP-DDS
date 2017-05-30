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
		
		parser = new ExpressionParser("10+2*10");
		for(String nombreVariable : parser.getVaVariableNames()) {
			try {
				validarVariables(nombreVariable);
			} catch (Exception e) {
				return Double.parseDouble("0");
			}
		}
		return resultado;
	}
	
	private void validarVariables(String nombreVariable) throws Exception {
		if (Cuenta.valueOf(nombreVariable) != null) {
			cuentas.add(Cuenta.valueOf(nombreVariable));
		} else if (RepositorioIndicadores.existeIndicador(nombreVariable) != null) {
			indicadores.add(RepositorioIndicadores.existeIndicador(nombreVariable));
		} else {
			throw new Exception("No existe el indicador/cuenta especificado");
		}
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
