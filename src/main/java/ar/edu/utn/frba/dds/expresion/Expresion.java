package ar.edu.utn.frba.dds.expresion;

import ar.edu.utn.frba.dds.modelo.Empresa;

public interface Expresion{
	public Integer calculate(Empresa empresa, String periodo);
	
	public String toString();
}