package ar.edu.utn.frba.dds.modelo;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonProperty;

import ar.edu.utn.frba.dds.util.ExpressionEval;

public class Indicador {

	public Indicador(@JsonProperty("nombre")String nombreIndicador, @JsonProperty("expresion")String expresion) {
		super();
		this.nombreIndicador = nombreIndicador;
		this.expresion = expresion;
	}
	
	@JsonProperty("nombre")
	public String nombreIndicador;
	@JsonProperty("expresion")
	public String expresion;
	
	private Collection<Indicador> indicadores = new ArrayList<Indicador>();
	private Collection<Cuenta> cuentas = new ArrayList<Cuenta>();
	
	private ExpressionEval parser;
	
	public Double calcular(Empresa empresa, String periodo) throws Exception{
		
		parser = new ExpressionEval(expresion);

		for(String nombreVariable : parser.getVaVariableNames()) {
			validarVariables(nombreVariable);
			parser.setVariableValue(nombreVariable, (int)(double) obtenerOperando(empresa, periodo, nombreVariable));
		}
		return (double) parser.calculate();
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
	
	private Double obtenerOperando(Empresa empresa, String periodo, String operandoActual) throws Exception{			
			Double operando;
			
			Optional<Indicador> optIndicador = indicadores.stream().filter(indicador -> indicador.getNombreIndicador() == operandoActual).findFirst();
			if(optIndicador.isPresent())
				operando = optIndicador.get().calcular(empresa, periodo);
			else
				operando = empresa.valorCuenta(Cuenta.valueOf(operandoActual), periodo);	
			
			return operando;
		}
	
	@Override
	public boolean equals(Object other){
	    if (other == null) return false;
	    if (other == this) return true;
	    if (!(other instanceof Indicador))return false;
	    Indicador otroIndicador = (Indicador)other;

	    return otroIndicador.getExpresion().equals(this.expresion) && otroIndicador.getNombreIndicador().equals(this.nombreIndicador);
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
