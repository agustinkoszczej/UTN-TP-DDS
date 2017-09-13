package ar.edu.utn.frba.dds.modelo;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import ar.edu.utn.frba.dds.metodologia.Condicion;
import ar.edu.utn.frba.dds.servicio.ServicioCondiciones;
import ar.edu.utn.frba.dds.util.BaseDeDatos;

public class RepositorioCondiciones {
	private static RepositorioCondiciones repositorioCondiciones = null;
	private static ServicioCondiciones servicioCondiciones = new ServicioCondiciones(new BaseDeDatos());
	private static List<Condicion> condiciones;
	
	private RepositorioCondiciones() { }
	
	public static RepositorioCondiciones getInstance() {
		if (repositorioCondiciones != null)
			return repositorioCondiciones;
		else {
			repositorioCondiciones = new RepositorioCondiciones();
			condiciones = servicioCondiciones.obtenerCondiciones();
			return repositorioCondiciones;
		}
	}
	
	public List<Condicion> getCondiciones() {
		return condiciones;
	}
	
	public void agregarCondicion(Condicion unaCondicion){
		if(condiciones.contains(unaCondicion))
			condiciones.remove(unaCondicion);
		condiciones.add(unaCondicion);
		try {
			servicioCondiciones.guardarCondicion(unaCondicion);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Condicion obtenerCondicion(String nombreCondicion) {
		return condiciones.stream().filter(unaCondicion -> unaCondicion.getNombreCondicion().equals(nombreCondicion)).collect(Collectors.toList()).get(0);
	}
	

}
