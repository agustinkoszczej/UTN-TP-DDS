package ar.edu.utn.frba.dds.expresion;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonTypeInfo;

import ar.edu.utn.frba.dds.modelo.Empresa;

@JsonTypeInfo(
	    use = JsonTypeInfo.Id.MINIMAL_CLASS,
	    include = JsonTypeInfo.As.PROPERTY,
	    property = "@class")
public abstract class Expresion{
	public abstract Integer calculate(Empresa empresa, String periodo);
	
	public abstract String toString();
	
	public abstract List<Object> listaDeElementos();
	
}