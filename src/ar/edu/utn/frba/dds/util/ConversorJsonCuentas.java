package ar.edu.utn.frba.dds.util;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import ar.edu.utn.frba.dds.modelo.Empresa;

public class ConversorJsonCuentas {
	
	public List<Empresa> mappearEmpresas(String jsonString) {
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
}
