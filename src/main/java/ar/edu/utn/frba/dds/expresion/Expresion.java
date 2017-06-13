package ar.edu.utn.frba.dds.expresion;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonTypeInfo;

import ar.edu.utn.frba.dds.modelo.Empresa;

@JsonTypeInfo(
	    use = JsonTypeInfo.Id.MINIMAL_CLASS,
	    include = JsonTypeInfo.As.PROPERTY,
	    property = "@class")
public interface Expresion{
	public Integer calculate(Empresa empresa, String periodo);
	
	public String toString();
	
	public List<Object> listaDeElementos();
	
}