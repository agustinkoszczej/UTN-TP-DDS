package ar.edu.utn.frba.dds.metodologia;

import org.uqbar.commons.utils.Observable;

import ar.edu.utn.frba.dds.modelo.Empresa;

@Observable
public class CondicionComparativa extends Condicion {

	protected double valorSegunComparador(double valorEmpresa1, double valorEmpresa2) {
		return comparador.devolverSegunComparador(valorEmpresa1, valorEmpresa2);
	}

	public Empresa cualEmpresaInvertir(Empresa empresa1, Empresa empresa2) {
		return null;
	}
}