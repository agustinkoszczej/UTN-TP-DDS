package ar.edu.utn.frba.dds.expresion;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import ar.edu.utn.frba.dds.modelo.Empresa;
import ar.edu.utn.frba.dds.modelo.Indicador;

public class ExpresionIndicador extends ExpresionSimple{

	private Indicador indicador;
	
	@JsonCreator
	public ExpresionIndicador(@JsonProperty("indicador")Indicador indicador){
		this.indicador = indicador;
	}
	
	@Override
	public Integer calculate(Empresa empresa, String periodo) {
		try {
			return indicador.calcular(empresa, periodo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public String toString(){
		return indicador.getNombreIndicador();
	}
	
	@Override
	public List<Object> listaDeElementos() {
		List<Object> listaConUnElemento = new ArrayList<Object>();
		listaConUnElemento.add(indicador);
		return listaConUnElemento;
	}

	public Indicador getIndicador() {
		return indicador;
	}

	public void setIndicador(Indicador indicador) {
		this.indicador = indicador;
	}

}
