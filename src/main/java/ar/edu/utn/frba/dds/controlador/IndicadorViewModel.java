package ar.edu.utn.frba.dds.controlador;

import java.io.IOException;
import java.util.Collection;

import org.uqbar.commons.utils.Observable;

import ar.edu.utn.frba.dds.modelo.Indicador;
import ar.edu.utn.frba.dds.modelo.RepositorioIndicadores;
import ar.edu.utn.frba.dds.servicio.ServicioIndicadores;

@Observable
public class IndicadorViewModel {
	private Indicador unIndicador;
	@SuppressWarnings("unused")
	private Collection<Indicador> indicadoresRegistrados;
	private String expresion;
	private String nombre;


public IndicadorViewModel() {
	try {
		//unIndicador = new Indicador(null, null);
	} catch (Exception e) {
		// Tira excepcion si le metiste cualquiera
		e.printStackTrace();
	}
	indicadoresRegistrados = RepositorioIndicadores.indicadores;
	}

	public void setCadena(String cadena){
		//this.unIndicador.setExpresion(cadena);
		this.expresion = cadena;
	}
	
	public String getCadena(){
		//return unIndicador.getExpresion();
		return expresion;
	}
	
	public void setNombreIndicador(String nombreIndicador){
		//this.unIndicador.setNombreIndicador(nombreIndicador);
		this.nombre = nombreIndicador;
	}
	
	public String getNombreIndicador(){
		//return unIndicador.getNombreIndicador();
		return nombre;
	}

	public void guardarIndicador() {
		// TODO Auto-generated method stub
			try {
				//unIndicador = new Indicador(nombre, expresion);
				
				RepositorioIndicadores.agregarYguardarIndicador(unIndicador);
			} catch (IOException e) {
				// TODO: Mostrar alerta de que no se pudo guardar el indicador
				e.printStackTrace();
			}
	}
}