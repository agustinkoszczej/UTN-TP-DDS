package ar.edu.utn.frba.dds.util;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import ar.edu.utn.frba.dds.modelo.Empresa;
import ar.edu.utn.frba.dds.modelo.Indicador;

public class ConversorJson {
	
	public List<Empresa> mapearEmpresas(String jsonString) {
		ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.ACCEPT_EMPTY_ARRAY_AS_NULL_OBJECT, true);
		List<Empresa> empresas = null;
		TypeReference<List<Empresa>> mapEmpresasList = new TypeReference<List<Empresa>>() {};
		try {
			empresas = objectMapper.readValue(jsonString, mapEmpresasList);
		}
		catch (IOException e) {
			    e.printStackTrace();
		}
		return empresas;
	}
	
	public List<Indicador> mapearIndicadores(String jsonString){
		ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.ACCEPT_EMPTY_ARRAY_AS_NULL_OBJECT, true);
		List<Indicador> list = null;
		try {
			list = objectMapper.readValue(jsonString, new TypeReference<List<Indicador>>(){});
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
}
