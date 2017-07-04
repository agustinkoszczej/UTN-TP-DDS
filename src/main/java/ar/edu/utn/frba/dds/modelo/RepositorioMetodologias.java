package ar.edu.utn.frba.dds.modelo;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import ar.edu.utn.frba.dds.metodologia.Metodologia;
import ar.edu.utn.frba.dds.servicio.ServicioMetodologias;

public class RepositorioMetodologias {
	private static RepositorioMetodologias repositorioMetodologias = null;
	
	private static List<Metodologia> metodologias;
	
	private RepositorioMetodologias() { }
	
	public static RepositorioMetodologias getInstance() {
		if (repositorioMetodologias != null)
			return repositorioMetodologias;
		else {
			repositorioMetodologias = new RepositorioMetodologias();
			metodologias = new ServicioMetodologias().obtenerMetodologias();
			return repositorioMetodologias;
		}
	}
	
	public List<Metodologia> getMetodologias() {
		return metodologias;
	}
	
	public void agregarMetodologia(Metodologia unaMetodologia){
		metodologias.add(unaMetodologia);
		try {
			new ServicioMetodologias().guardarMetodologia(unaMetodologia);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Metodologia obtenerMetodologia(String nombreMetodologia) {
		return metodologias.stream().filter(unaMetodologia -> unaMetodologia.getNombre().equals(nombreMetodologia)).collect(Collectors.toList()).get(0);
	}
	
}
