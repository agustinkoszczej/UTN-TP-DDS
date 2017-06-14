package ar.edu.utn.frba.dds.modelo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.JOptionPane;

import ar.edu.utn.frba.dds.servicio.ServicioIndicadores;

public class RepositorioIndicadores {
	
	public static void CargarYValidarIndicadores(){
		indicadores = new ServicioIndicadores().obtenerIndicadores();
		indicadores.stream().forEach(indicador -> indicador.validarYActualizarVariables(indicadores.stream().collect(Collectors.toList())));
	}
	
	public void setIndicadores(List<Indicador> indicadores){
		RepositorioIndicadores.indicadores = indicadores;
	}
	
	public static Collection<Indicador> indicadores = new ArrayList<Indicador>();
	
	public static Indicador obtenerSiExiste(String nombreIndicador) {
		//actualizamos los indicadores
		
		if (indicadores.isEmpty())
			return null;
		if (indicadores.stream().map(indicador -> indicador.getNombreIndicador()).collect(Collectors.toList()).contains(nombreIndicador)) {
			return indicadores.stream().filter(indicador -> indicador.getNombreIndicador().equals(nombreIndicador)).collect(Collectors.toList()).get(0);
		} else {
			return null;
		}
	}
	
	private static void agregarIndicador(Indicador in){
		indicadores.add(in);
	}

	public static void agregarYguardarIndicador(Indicador in) throws IOException {
		//si esta un indicador con el mismo nombre lo reemplazamos
		if(indicadores.contains(in)){
			indicadores.remove(in);
			JOptionPane.showMessageDialog(null,"Se sobrescribira el indicador"); 
		}	
		agregarIndicador(in);
		new ServicioIndicadores().guardarIndicador(in);
	}
}
