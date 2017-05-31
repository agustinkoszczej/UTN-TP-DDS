package main.java.ar.edu.utn.frba.dds.modelo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import main.java.ar.edu.utn.frba.dds.util.ExpressionParser;

public class Indicador {

	public Indicador(String nombreIndicador, String expresion) {
		super();
		this.nombreIndicador = nombreIndicador;
		this.expresion = expresion;
	}
	
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
	
	private Double obtenerOperando(Empresa empresa, String periodo){
			String operandoActual = null;// = operandos.pop();
			
			Double operando;
			
				Optional<Indicador> optIndicador = indicadores.stream().filter(indicador -> indicador.getNombreIndicador() == operandoActual).findFirst();
				if(optIndicador.isPresent())
					operando = optIndicador.get().calcular(empresa, periodo);
				else
					operando = empresa.valorCuenta(Cuenta.valueOf(operandoActual), periodo);	
			
			return operando;
		}
	
	public String getNombreIndicador() {
		return nombreIndicador;
	}
	public void setNombreIndicador(String nombreIndicador) {
		this.nombreIndicador = nombreIndicador;
	}
	public String getExpresion() {
		return expresion;
	}
	public void setExpresion(String expresion) {
		this.expresion = expresion;
	}
}
