package ar.edu.utn.frba.dds.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Collection;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import ar.edu.utn.frba.dds.modelo.Empresa;
import ar.edu.utn.frba.dds.modelo.Balance;

public class ConversorJsonCuentas {
	
	/*
	public Cuenta mappearAlumno(String jsonString) {
		ObjectMapper objectMapper = new ObjectMapper();
		
		Alumno unAlumno = null;
		try {
			unAlumno = objectMapper.readValue(jsonString, Alumno.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return unAlumno;
	}
*/
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
