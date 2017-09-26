package ar.edu.utn.frba.dds.servicio;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


import com.fasterxml.jackson.databind.ObjectMapper;

import ar.edu.utn.frba.dds.metodologia.Metodologia;
import ar.edu.utn.frba.dds.util.ConversorJson;
import ar.edu.utn.frba.dds.util.ServidorDeConsultas;

public class ServicioMetodologias {
	private File JSONFile = new File("metodologias.json");
	private ServidorDeConsultas unServidorParaConsultar;
	private ConversorJson unConversorDeMetodologias;
	
	public ServicioMetodologias(String archivoJSON) {
		JSONFile = new File(archivoJSON);
		unConversorDeMetodologias = new ConversorJson();
		unServidorParaConsultar = new ServidorDeConsultas();
	}
	
	public ServicioMetodologias() {
			unConversorDeMetodologias = new ConversorJson();
			unServidorParaConsultar = new ServidorDeConsultas();
	}
	
	public List<Metodologia> obtenerMetodologias() {
			String jsonMetodologias = unServidorParaConsultar.obtenerJson(JSONFile);
			if ((jsonMetodologias == null) || jsonMetodologias.isEmpty()) 
				return new ArrayList<Metodologia>();
			else
				return unConversorDeMetodologias.mapearMetodologias(jsonMetodologias);
	}
	
	public void guardarMetodologia(Metodologia unaMetodologia) throws IOException{
			ObjectMapper mapper = new ObjectMapper();
			List<Metodologia> metodologias = obtenerMetodologias();
	
			if(metodologias.contains(unaMetodologia))
				metodologias.remove(unaMetodologia);
			
			metodologias.add(unaMetodologia);
			mapper.writeValue(JSONFile, metodologias);
	}
}
