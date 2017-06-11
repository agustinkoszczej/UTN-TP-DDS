package ar.edu.utn.frba.dds.modelo;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.uqbar.commons.utils.Observable;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import ar.edu.utn.frba.dds.expresion.Expresion;
import ar.edu.utn.frba.dds.expresion.ExpresionIndicador;

@Observable
@JsonIgnoreProperties(value = { "changeSupport" })
public class Indicador {
	
	// Se agrego el metodo para generar un indicador vacio
	public Indicador() {
		super();
	}

	@JsonCreator
	public Indicador(@JsonProperty("nombre")String nombreIndicador, @JsonProperty("expresion")Expresion expresion){
		this.nombreIndicador = nombreIndicador;
		this.expresion = expresion;
		this.indicadoresCorruptos = new ArrayList<Indicador>();
	}

	private String nombreIndicador;
	private Expresion expresion;
	private List<Indicador> indicadoresCorruptos;
	
	public Integer calcular(Empresa empresa, String periodo) throws Exception{
		if(!tieneIndicadoresCorruptos())
			return expresion.calculate(empresa, periodo);
		else
			throw new Exception("Los indicadores " + indicadoresCorruptos + " no estan en el archivo de indicadores.");
	}
	
	public String formula(){
		return expresion.toString();
	}

	public List<Indicador> listaIndicadores(){
		return (List<Indicador>) expresion.listaDeElementos().stream().filter(elemento -> elemento.getClass().equals(Indicador.class)).map(elemento -> (Indicador) elemento).collect(Collectors.toList());
	}
	
	public void validarYActualizarVariables(List<Indicador> indicadores) {
		// Validamos que los indicadores que tiene nuestra expresion estan en la lista de nuestros indicadores globales
		// Si están, validamos que esten actualizados (que no hayan cambiado)
		
		List<Indicador> indicadoresPropios = listaIndicadores();
		if(indicadoresPropios.isEmpty())
			return;
		
		if(!indicadores.containsAll(indicadoresPropios)){
			//Si falta algun indicador en el archivo, lo agregamos a los indicadoresCorruptos
			indicadoresPropios.removeAll(indicadores);
			indicadoresCorruptos.addAll(indicadoresPropios);
		}
		
		//Actualizamos los indicadores que si estan en el archivo Y en éste Indicador
		
		List<Indicador> indicadoresQueCoinciden = (List<Indicador>) indicadores.stream()
																	.filter(indicador -> indicadoresPropios.contains(indicador))
																	.collect(Collectors.toList());
		if(!indicadoresQueCoinciden.isEmpty())
			indicadoresPropios.stream()
							  .forEach(indicadorPropio -> indicadorPropio.setExpresion(indicadoresQueCoinciden.stream().filter(otro -> otro.getNombreIndicador().equals(indicadorPropio.getNombreIndicador())).findFirst().get().getExpresion()));

	}
	
	@Override
	public String toString(){
		return this.nombreIndicador + " = " + this.formula();
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
		return other.getNombreIndicador().equals(this.getNombreIndicador());
	}

	public Expresion getExpresion() {
		return expresion;
	}

	public void setExpresion(Expresion expresion) {
		this.expresion = expresion;
	}

	public void setNombreIndicador(String nombreIndicador) {
		this.nombreIndicador = nombreIndicador;
	}
	
	public String getNombreIndicador(){
		return nombreIndicador;
	}

	public boolean tieneIndicadoresCorruptos() {
		return !indicadoresCorruptos.isEmpty();
	}

	
	/*
	public List<Indicador> indicadores = new ArrayList<Indicador>();
	public List<TipoDeCuenta> cuentas = new ArrayList<TipoDeCuenta>();
	
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
			} else if (TipoDeCuenta.valueOf(nombreVariable) != null) {
				List<TipoDeCuenta> cuentass = this.cuentas;
				cuentass.add(TipoDeCuenta.valueOf(nombreVariable));
				this.setCuentas(cuentass);
			} else{
				throw new Exception("No existe la cuenta o indicador");
			}
		}
	}
	
	public void setIndicadores(List<Indicador> indicadores){
		this.indicadores = indicadores;
	}
	
	public void setCuentas(List<TipoDeCuenta> cuentas){
		this.cuentas = cuentas;
	}
	
	private Double obtenerOperando(Empresa empresa, String periodo, String operandoActual) throws Exception{			
			Double operando;
			
			Optional<Indicador> optIndicador = this.getIndicadores().stream().filter(indicador -> indicador.getNombreIndicador().equals(operandoActual)).findFirst();
			if(optIndicador.isPresent())
				operando = optIndicador.get().calcular(empresa, periodo);
			else
				operando = empresa.valorCuenta(TipoDeCuenta.valueOf(operandoActual), periodo);	
			
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
	*/
}
