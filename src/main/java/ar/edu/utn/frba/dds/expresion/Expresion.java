package ar.edu.utn.frba.dds.expresion;

import java.util.List;

import ar.edu.utn.frba.dds.modelo.Empresa;

public interface Expresion{
	public Integer calculate(Empresa empresa, String periodo);
	
	public String toString();
	
	public List<Object> listaDeElementos();
}