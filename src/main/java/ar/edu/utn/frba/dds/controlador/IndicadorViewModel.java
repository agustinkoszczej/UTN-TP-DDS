package ar.edu.utn.frba.dds.controlador;

import java.util.Collection;
import java.util.List;

import org.uqbar.commons.utils.Observable;

import ar.edu.utn.frba.dds.modelo.Empresa;
import ar.edu.utn.frba.dds.modelo.Indicador;
import ar.edu.utn.frba.dds.modelo.RepositorioIndicadores;
import ar.edu.utn.frba.dds.servicio.ServicioCuentas;

@Observable
public class IndicadorViewModel {
	private Empresa unaEmpresa;
	private Indicador unIndicador;
	private Collection<Indicador> indicadoresRegistrados;
	private Indicador indicadorSeleccionado;


public IndicadorViewModel() {
	unIndicador = new Indicador(null, null);
	indicadoresRegistrados = RepositorioIndicadores.indicadores;
	}

	public void setCadena(String cadena){
		this.unIndicador.setExpresion(cadena);
	}
	
	public String getCadena(){
		return unIndicador.getExpresion();
	}
	
	public void setNombreIndicador(String nombreIndicador){
		this.unIndicador.setNombreIndicador(nombreIndicador);
	}
	
	public String getNombreIndicador(){
		return unIndicador.getNombreIndicador();
	}
}