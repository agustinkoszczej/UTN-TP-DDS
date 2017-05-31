package main.java.ar.edu.utn.frba.dds.servicio;

import java.io.File;
import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import main.java.ar.edu.utn.frba.dds.modelo.Indicador;
import main.java.ar.edu.utn.frba.dds.util.ConversorJson;
import main.java.ar.edu.utn.frba.dds.util.ServidorDeConsultas;

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
	
	public List<Indicador> obtenerIndicadores() throws IOException {
		String jsonIndicadores = servidor.obtenerJson(archivo);
		return conversorJson.mapearIndicadores(jsonIndicadores);
	}
	
	//Agarra los ya guardados en el archivo, le agrega el nuevo y los vuelve a guardar
	public void guardarIndicador(Indicador indicador) throws JsonGenerationException, JsonMappingException, IOException{
		ObjectMapper mapper = new ObjectMapper();
		List<Indicador> indicadoresActuales = obtenerIndicadores();
		indicadoresActuales.add(indicador);
		mapper.writeValue(new File(archivo), indicadoresActuales);
	}
}
