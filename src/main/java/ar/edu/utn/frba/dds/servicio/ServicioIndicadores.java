package ar.edu.utn.frba.dds.servicio;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import ar.edu.utn.frba.dds.modelo.Indicador;
import ar.edu.utn.frba.dds.modelo.RepositorioIndicadores;
import ar.edu.utn.frba.dds.util.ConversorJson;
import ar.edu.utn.frba.dds.util.ServidorDeConsultas;

public class ServicioIndicadores {
	private ServidorDeConsultas servidor;
	private ConversorJson conversorJson;

	private String archivo = "indicadores.json";
	
	public ServicioIndicadores() {
		// Inicializo el conversor
		conversorJson = new ConversorJson();
		// Inicializo el servidor de consultas para leer los datos JSON
		servidor = new ServidorDeConsultas();
	}
	
	public List<Indicador> obtenerIndicadores() {
		String jsonIndicadores = null;
		jsonIndicadores = servidor.obtenerJson(archivo);
		return conversorJson.mapearIndicadores(jsonIndicadores);
	}
	
	//Agarra los ya guardados en el archivo, le agrega el nuevo y los vuelve a guardar
	public void guardarIndicador(Indicador indicador) throws IOException{
		
		ObjectMapper mapper = new ObjectMapper();
		List<Indicador> indicadoresActuales = obtenerIndicadores();
		if(indicadoresActuales.contains(indicador))
			return;
		RepositorioIndicadores.agregarIndicador(indicador);
		indicadoresActuales.add(indicador);
		mapper.writeValue(new File(archivo), indicadoresActuales);
	}
}
