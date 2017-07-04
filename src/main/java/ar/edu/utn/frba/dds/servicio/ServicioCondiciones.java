package ar.edu.utn.frba.dds.servicio;

import java.io.File;
import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import ar.edu.utn.frba.dds.metodologia.Condicion;
import ar.edu.utn.frba.dds.util.ConversorJson;
import ar.edu.utn.frba.dds.util.ServidorDeConsultas;

public class ServicioCondiciones {
	private String fileName = "condiciones.json";
	private ServidorDeConsultas unServidorParaConsultar;
	private ConversorJson unConversorDeCondiciones;

	public ServicioCondiciones() {
		// Inicializo el conversor
		unConversorDeCondiciones = new ConversorJson();
		// Inicializo el servidor de consultas para leer los datos JSON
		unServidorParaConsultar = new ServidorDeConsultas();
	}
	
	public List<Condicion> obtenerCondiciones() {
		String jsonCondiciones = unServidorParaConsultar.obtenerJson(fileName);
		return unConversorDeCondiciones.mapearCondiciones(jsonCondiciones);
	}
	
	public void guardarCondicion(Condicion unaCondicion) throws IOException{
		ObjectMapper mapper = new ObjectMapper();
		List<Condicion> condiciones = obtenerCondiciones();

		if(condiciones.contains(unaCondicion))
			condiciones.remove(unaCondicion);
		
		condiciones.add(unaCondicion);
		mapper.writeValue(new File(fileName), condiciones);
	}
}
