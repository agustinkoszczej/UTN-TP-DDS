package ar.edu.utn.frba.dds.expresion;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import ar.edu.utn.frba.dds.modelo.Empresa;

public class ExpresionConstante extends ExpresionSimple {

	Integer constante;
	
	@JsonCreator
	public ExpresionConstante(@JsonProperty("constante")Integer constante){
		this.constante = constante;
	}

	public Integer calculate(Empresa empresa, String periodo) {
		//TODO: Cambiar a double cuando pasemos todo a double
		return constante;
	}
	
	public String toString(){
		return constante.toString();
	}

	public List<Object> listaDeElementos() {
		List<Object> listaConUnElemento = new ArrayList<Object>();
		listaConUnElemento.add(constante);
		return listaConUnElemento;
	}

	public Integer getConstante() {
		return constante;
	}

	public void setConstante(Integer constante) {
		this.constante = constante;
	}

}




