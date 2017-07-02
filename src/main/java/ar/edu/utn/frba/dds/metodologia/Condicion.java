package ar.edu.utn.frba.dds.metodologia;

import org.uqbar.commons.utils.Observable;

@Observable
public abstract class Condicion {
	private String nombreCondicion;

	public String getNombreCondicion() {
		return nombreCondicion;
	}

	public void setNombreCondicion(String nombreCondicion) {
		this.nombreCondicion = nombreCondicion;
	}
	
	public String getClaseCondicion() {
		return this.getClass().getSimpleName();
	}
	
	public void setClaseCondicion() {
		//nada
	}
	
	
	
}
