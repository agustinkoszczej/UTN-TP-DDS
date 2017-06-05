package ar.edu.utn.frba.dds.modelo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.uqbar.commons.utils.Observable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import ar.edu.utn.frba.dds.ast.AST;
import ar.edu.utn.frba.dds.util.ExpressionEval;
import ar.edu.utn.frba.dds.util.ExpressionParser;

@Observable
@JsonIgnoreProperties(value = { "changeSupport" })
public class Indicador {

	// Se agrego el metodo para generar un indicador vacio
	public Indicador() {
		super();
	}
	
	// FIXME: Es necesario llamar a los Json Property aca??
	public Indicador(@JsonProperty("nombre")String nombreIndicador, @JsonProperty("expresion")String expresion) throws Exception {
		// super();
		// FIXME: Cual es la superclase de Indicador si no esta heredando nada???
		this.nombreIndicador = nombreIndicador;
		
		// TODO: Aca se deberia reemplazar por el metodo que devuelve un AST
		this.expresion = expresion;
		
		// Asigno el AST al resultado de parsear la expresion
		ExpressionParser parser = new ExpressionParser();
		expression = parser.ASTresult(expresion);
	}

	
	// FIXME: porque los property son publicos??? Tendria que tener setter/getters
	@JsonProperty("nombre")
	public String nombreIndicador;
	@JsonProperty("expresion")
	public String expresion;
	
	private AST expression;
	
	
	public Integer calcularAST(Empresa empresa, String periodo){
		return expression.resultado();
	}
	
	
	
	public List<Indicador> indicadores = new ArrayList<Indicador>();
	public List<Cuenta> cuentas = new ArrayList<Cuenta>();
	
	private ExpressionEval parser;
	
	public Double calcular(Empresa empresa, String periodo) throws Exception{
		
		parser = new ExpressionEval(expresion);

		for(String nombreVariable : parser.getVaVariableNames()) {
			parser.setVariableValue(nombreVariable, (int)(double) obtenerOperando(empresa, periodo, nombreVariable));
		}
		return (double) parser.calculate();
	}
	
	public void validarVariables() throws Exception {
		
		for(String nombreVariable : new ExpressionEval(expresion).getVaVariableNames()){
			if (RepositorioIndicadores.existeIndicador(nombreVariable) != null) {
				List<Indicador> indicadoress = this.indicadores;
				indicadoress.add(RepositorioIndicadores.existeIndicador(nombreVariable));
				this.setIndicadores(indicadoress);
			} else if (Cuenta.valueOf(nombreVariable) != null) {
				List<Cuenta> cuentass = this.cuentas;
				cuentass.add(Cuenta.valueOf(nombreVariable));
				this.setCuentas(cuentass);
			} else{
				throw new Exception("No existe la cuenta o indicador");
			}
		}
	}
	
	public void setIndicadores(List<Indicador> indicadores){
		this.indicadores = indicadores;
	}
	
	public void setCuentas(List<Cuenta> cuentas){
		this.cuentas = cuentas;
	}
	
	private Double obtenerOperando(Empresa empresa, String periodo, String operandoActual) throws Exception{			
			Double operando;
			
			Optional<Indicador> optIndicador = this.getIndicadores().stream().filter(indicador -> indicador.getNombreIndicador().equals(operandoActual)).findFirst();
			if(optIndicador.isPresent())
				operando = optIndicador.get().calcular(empresa, periodo);
			else
				operando = empresa.valorCuenta(Cuenta.valueOf(operandoActual), periodo);	
			
			return operando;
		}
	
	private List<Indicador> getIndicadores() {
		return this.indicadores;
	}

	
	public String getNombreIndicador() {
		return nombreIndicador;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nombreIndicador == null) ? 0 : nombreIndicador.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (this == obj)
			return true;
		if (getClass() != obj.getClass())
			return false;
		Indicador other = (Indicador) obj;
		if (nombreIndicador == null) {
			if (other.nombreIndicador != null)
				return false;
		} else if (!nombreIndicador.equals(other.nombreIndicador))
			return false;
		return true;
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
