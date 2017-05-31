package ar.edu.utn.frba.dds.modelo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

public class RepositorioIndicadores {
	public static Collection<Indicador> indicadores = new ArrayList<Indicador>();
	public static Indicador existeIndicador(String nombreIndicador) {
		if (indicadores.isEmpty()) {
			return null;
		} else if (indicadores.stream().map(indicador -> indicador.getNombreIndicador()).collect(Collectors.toList()).contains(nombreIndicador)) {
			return indicadores.stream().filter(indicador -> indicador.getNombreIndicador().equals(nombreIndicador)).collect(Collectors.toList()).get(0);
		} else {
			return null;
		}
	}
}
