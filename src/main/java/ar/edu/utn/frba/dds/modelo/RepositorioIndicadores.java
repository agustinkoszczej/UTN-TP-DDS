package ar.edu.utn.frba.dds.modelo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import ar.edu.utn.frba.dds.servicio.ServicioIndicadores;

public class RepositorioIndicadores {
	
	public static void CargarYValidarIndicadores(){
		indicadores = new ServicioIndicadores().obtenerIndicadores();
		indicadores.forEach(indicador -> {
			try {
				indicador.validarVariables();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		
	}
	public void setIndicadores(List<Indicador> indicadores){
		RepositorioIndicadores.indicadores = indicadores;
	}
	
	public static Collection<Indicador> indicadores = new ArrayList<Indicador>();
	public static Indicador existeIndicador(String nombreIndicador) {
		//actualizamos los indicadores
		
		if (indicadores.isEmpty())
			return null;
		if (indicadores.stream().map(indicador -> indicador.getNombreIndicador()).collect(Collectors.toList()).contains(nombreIndicador)) {
			return indicadores.stream().filter(indicador -> indicador.getNombreIndicador().equals(nombreIndicador)).collect(Collectors.toList()).get(0);
		} else {
			return null;
		}
	}
	
	public static void agregarIndicador(Indicador in){
		indicadores.add(in);
	}
}
