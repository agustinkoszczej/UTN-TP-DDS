package ar.edu.utn.frba.dds.servicio;

import java.io.File;
import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import ar.edu.utn.frba.dds.metodologia.Metodologia;
import ar.edu.utn.frba.dds.util.ConversorJson;
import ar.edu.utn.frba.dds.util.ServidorDeConsultas;

public class ServicioMetodologias {
	private String fileName = "metodologias.json";
	private ServidorDeConsultas unServidorParaConsultar;
	private ConversorJson unConversorDeMetodologias;

	public ServicioMetodologias() {
		// Inicializo el conversor
		unConversorDeMetodologias = new ConversorJson();
		// Inicializo el servidor de consultas para leer los datos JSON
		unServidorParaConsultar = new ServidorDeConsultas();
	}
	
	public List<Metodologia> obtenerMetodologias() {
		String jsonMetodologias = unServidorParaConsultar.obtenerJson(fileName);
		return unConversorDeMetodologias.mapearMetodologias(jsonMetodologias);
	}
	
	public void guardarMetodologia(Metodologia unaMetodologia) throws IOException{
		ObjectMapper mapper = new ObjectMapper();
		List<Metodologia> metodologias = obtenerMetodologias();

		if(metodologias.contains(unaMetodologia))
			metodologias.remove(unaMetodologia);
		
		metodologias.add(unaMetodologia);
		mapper.writeValue(new File(fileName), metodologias);
	}
}
