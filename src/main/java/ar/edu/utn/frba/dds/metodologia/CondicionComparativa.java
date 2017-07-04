package ar.edu.utn.frba.dds.metodologia;

import org.uqbar.commons.utils.Observable;

import ar.edu.utn.frba.dds.modelo.Empresa;
@Observable
public class CondicionComparativa extends Condicion {

	public Empresa cualEmpresaInvertir(Empresa emp1, Empresa emp2) {
		return null;
	}


	protected int esMejorValor(int valorEmpresa1, int valorEmpresa2) {
		if(comparador.name() == "MAYOR")
			return Integer.max(valorEmpresa1, valorEmpresa2);
		if(comparador.name() == "MENOR")
			return Integer.min(valorEmpresa1, valorEmpresa2);
		else throw new NullPointerException("No es posible comparar con el comparador usado");
	}
	
	
}
