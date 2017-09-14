package ar.edu.utn.frba.dds.metodologia;

import javax.persistence.Column;
import javax.persistence.Entity;

import org.uqbar.commons.utils.Observable;

import com.fasterxml.jackson.annotation.JsonProperty;

import ar.edu.utn.frba.dds.modelo.Empresa;

@Entity
@Observable
public abstract class CondicionComparativa extends Condicion {
	
	@Column
	@JsonProperty("peso")
	private int peso = 1;
	
	protected double valorSegunComparador(double valorEmpresa1, double valorEmpresa2) {
		return comparador.devolverSegunComparador(valorEmpresa1, valorEmpresa2);
	}

	@Override
	public boolean esTaxativa() {
		// TODO Auto-generated method stub
		return false;
	}

	public abstract Empresa cualEmpresaInvertir(Empresa empresa1, Empresa empresa2);

	public int getPeso() {
		return peso;
	}

	public void setPeso(int peso) {
		this.peso = peso;
	}	
}