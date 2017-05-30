package main.java.ar.edu.utn.frba.dds.modelo;

import java.util.Collection;
import java.util.Optional;
import java.util.Stack;

import org.apache.commons.lang.StringUtils;

import main.java.ar.edu.utn.frba.dds.util.expressionParser.Operator;

public class Indicador {

	public String nombreIndicador;
	public String expresion;
	
	private Collection<Indicador> indicadores;
	private Collection<Cuenta> cuentas;
	
	private Stack<Operator> operadores;
	private Stack<String> operandos;
	
	public Double calcular(Empresa empresa, String periodo){
		Double resultado = null;
		//TODO: Deben llegar los operadores y operandos en los stacks segun su orden de precedencia
		// A + B * D deberia llegar como B * D + A
		do{
			Operator operador = operadores.pop();
			resultado = (resultado == null)? this.obtenerOperando(empresa, periodo) : resultado;
			Double operando2 = this.obtenerOperando(empresa, periodo);
			
			switch(operador){
			case ADD:
				resultado += operando2;
				break;
			case SUBSTRACT:
				resultado -= operando2;
				break;
			case MULTIPLY:
				resultado *= operando2;
				break;
			case DIVIDE:
				resultado /= operando2;
				break;
			}
			
		}while(!operadores.empty());
		
		return resultado;
	}
	
	private Double obtenerOperando(Empresa empresa, String periodo){
		String operandoActual = operandos.pop();
		
		Double operando;
		
		if(StringUtils.isNumeric(operandoActual))
			operando = Double.parseDouble(operandoActual);
		else{
			Optional<Indicador> optIndicador = indicadores.stream().filter(indicador -> indicador.getNombreIndicador() == operandoActual).findFirst();
			if(optIndicador.isPresent())
				operando = optIndicador.get().calcular(empresa, periodo);
			else
				operando = empresa.valorCuenta(Cuenta.valueOf(operandoActual), periodo);	
		}
		
		return operando;
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
