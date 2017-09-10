package ar.edu.utn.frba.dds.modelo;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.uqbar.commons.utils.Observable;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import ar.edu.utn.frba.dds.expresion.Expresion;

@Observable
@JsonIgnoreProperties(value = { "changeSupport" })
public class Indicador {
	
	// Se agrego el metodo para generar un indicador vacio
	public Indicador() {
		super();
	}

	@Id	@GeneratedValue
	private int id;
	
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
		// Si est�n, validamos que esten actualizados (que no hayan cambiado)
		
		List<Indicador> indicadoresPropios = listaIndicadores();
		if(indicadoresPropios.isEmpty())
			return;
		
		if(!indicadores.containsAll(indicadoresPropios)){
			//Si falta algun indicador en el archivo, lo agregamos a los indicadoresCorruptos
			indicadoresPropios.removeAll(indicadores);
			indicadoresCorruptos.addAll(indicadoresPropios);
		}
		
		//Actualizamos los indicadores que si estan en el archivo Y en �ste Indicador
		
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
}
