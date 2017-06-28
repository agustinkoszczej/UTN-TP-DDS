package ar.edu.utn.frba.dds.metodologia;

import java.util.ArrayList;
import java.util.List;

import ar.edu.utn.frba.dds.modelo.Empresa;

public class ComparadorAntiguedad implements CondicionComparativa {
	
	private Comparador comparador;
	
	public Comparador getComparador() {
		return comparador;
	}

	public void setComparador(Comparador comparador) {
		this.comparador = comparador;
	}

	@Override
	public Empresa cualEmpresaInvertir(Empresa emp1, Empresa emp2) {
		//Opcion 1: Lo opera como lista de dos elementos
		/*
		List<Empresa> empresas = new ArrayList<Empresa>();
		empresas.add(emp1);
		empresas.add(emp2);
		return empresas
				.stream()
				.max((empresa1, empresa2) -> Double.compare(empresa1.getAntiguedad(), empresa2.getAntiguedad()))
				.get();
		*/
		int valor = esMejorValor(emp1.getAntiguedad(), emp2.getAntiguedad());
		
		if(valor == emp1.getAntiguedad())
			return emp1;
		if(valor == emp2.getAntiguedad())
			return emp2;
		return null;
		
	}

	private int esMejorValor(int valor1, int valor2) {
		if(comparador.name() == "MAYOR")
			return Integer.max(valor1, valor2);
		if(comparador.name() == "MENOR")
			return Integer.min(valor1, valor2);
		else throw new NullPointerException("No es posible comparar con el comparador usado");
	}
	
}
