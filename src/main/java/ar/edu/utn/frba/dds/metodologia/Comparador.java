package ar.edu.utn.frba.dds.metodologia;

import org.uqbar.commons.utils.Observable;

import ar.edu.utn.frba.dds.metodologia.Comparador.Comparadores;

@Observable
public class Comparador {
	
	public enum Comparadores {MAYOR, MENOR, IGUAL, MAYOREIGUAL, MENOREIGUAL};
	
	public Comparadores comparador;
	
	public Comparadores getComparador() {
		return comparador;
	}

	public void setComparador(Comparadores comparador) {
		this.comparador = comparador;
	}

	public Boolean cumpleCondicion(double valor, double valorASuperar) {
		if(comparador.name() == "MAYOR")
			return valor > valorASuperar;
		if(comparador.name() == "MENOR")
			return valor < valorASuperar;
		if(comparador.name() == "IGUAL")
			return valor == valorASuperar;
		if(comparador.name() == "MAYOREIGUAL")
			return valor >= valorASuperar;
		if(comparador.name() == "MENOREIGUAL")
			return valor <= valorASuperar;	
		return false;
	}
	
	public double devolverSegunComparador(Double valor1, Double valor2) {
		if(comparador.name() == "MAYOR")
			return Double.max(valor1, valor2);
		else if(comparador.name() == "MENOR")
			return Double.min(valor1, valor2);
		else
			return valor1;
	}	
}
