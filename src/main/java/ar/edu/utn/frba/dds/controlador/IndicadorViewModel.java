package main.java.ar.edu.utn.frba.dds.controlador;

import java.util.List;

import org.uqbar.commons.utils.Observable;

import main.java.ar.edu.utn.frba.dds.modelo.Empresa;
import main.java.ar.edu.utn.frba.dds.modelo.Indicador;
import main.java.ar.edu.utn.frba.dds.servicio.ServicioCuentas;

@Observable
public class IndicadorViewModel {
	private Empresa unaEmpresa;
	private Indicador unIndicador;
	private List<Indicador> indicadoresRegistrados;
	private Indicador indicadorSeleccionado;


public IndicadorViewModel() {
		unIndicador = new Indicador();
	}

	public void setCadena(String cadena){
		this.unIndicador.setCadena(cadena);
	}
	
	public String getCadena(){
		return unIndicador.getCadena();
	}
	
	public void setNombreIndicador(String nombreIndicador){
		this.unIndicador.setNombreIndicador(nombreIndicador);
	}
	
	public String getNombreIndicador(){
		return unIndicador.getNombreIndicador();
	}
}