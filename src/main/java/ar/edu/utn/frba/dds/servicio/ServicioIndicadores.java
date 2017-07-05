package ar.edu.utn.frba.dds.servicio;

import java.io.File;
import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import ar.edu.utn.frba.dds.modelo.Indicador;
import ar.edu.utn.frba.dds.util.ConversorJson;
import ar.edu.utn.frba.dds.util.ServidorDeConsultas;

public class ServicioIndicadores {
	private ServidorDeConsultas servidor;
	private ConversorJson conversorJson;

	private File JSONFile = new File("indicadores.json");
	
	public ServicioIndicadores() {
		// Inicializo el conversor
		conversorJson = new ConversorJson();
		// Inicializo el servidor de consultas para leer los datos JSON
		servidor = new ServidorDeConsultas();
	}
	
	public List<Indicador> obtenerIndicadores() {
		String jsonIndicadores = null;
		jsonIndicadores = servidor.obtenerJson(JSONFile);
		return conversorJson.mapearIndicadores(jsonIndicadores);
	}
	
	//Agarra los ya guardados en el archivo, le agrega el nuevo y los vuelve a guardar
	public void guardarIndicador(Indicador indicador) throws IOException{
		
		ObjectMapper mapper = new ObjectMapper();
		List<Indicador> indicadoresActuales = obtenerIndicadores();
		//si esta un indicador con el mismo nombre lo reemplazamos
		if(indicadoresActuales.contains(indicador))
			indicadoresActuales.remove(indicador);
		
		indicadoresActuales.add(indicador);
		mapper.writeValue(JSONFile, indicadoresActuales);
	}
}
