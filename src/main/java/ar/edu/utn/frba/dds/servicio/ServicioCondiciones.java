package ar.edu.utn.frba.dds.servicio;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import ar.edu.utn.frba.dds.metodologia.Condicion;
import ar.edu.utn.frba.dds.util.BaseDeDatos;
import ar.edu.utn.frba.dds.util.ConversorJson;
import ar.edu.utn.frba.dds.util.ServidorDeConsultas;

public class ServicioCondiciones {
	private File JSONFile = new File("condiciones.json");
	private ServidorDeConsultas unServidorParaConsultar;
	private ConversorJson unConversorDeCondiciones;

	public ServicioCondiciones(String fileName) {
		JSONFile = new File(fileName);
		unConversorDeCondiciones = new ConversorJson();
		unServidorParaConsultar = new ServidorDeConsultas();
	}

	public ServicioCondiciones() {
		// Inicializo el conversor
		BaseDeDatos db = new BaseDeDatos();
		if(!db.isBdEnabled()){
			unConversorDeCondiciones = new ConversorJson();
		// Inicializo el servidor de consultas para leer los datos JSON
			unServidorParaConsultar = new ServidorDeConsultas();
		}	
	}
	
	public List<Condicion> obtenerCondiciones() {
		String jsonCondiciones = unServidorParaConsultar.obtenerJson(JSONFile);
		if ((jsonCondiciones == null) || jsonCondiciones.isEmpty()) 
			return new ArrayList<Condicion>();
		else
			return unConversorDeCondiciones.mapearCondiciones(jsonCondiciones);
	}
	
	public void guardarCondicion(Condicion unaCondicion) throws IOException{
		ObjectMapper mapper = new ObjectMapper();
		List<Condicion> condiciones = obtenerCondiciones();

		if(condiciones.contains(unaCondicion))
			condiciones.remove(unaCondicion);
		
		condiciones.add(unaCondicion);
		mapper.writeValue(JSONFile, condiciones);
	}
}
