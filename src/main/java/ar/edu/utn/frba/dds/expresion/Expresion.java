package ar.edu.utn.frba.dds.expresion;

import ar.edu.utn.frba.dds.modelo.Empresa;

interface Expresion{
	Integer calculate(Empresa empresa, String periodo);
}