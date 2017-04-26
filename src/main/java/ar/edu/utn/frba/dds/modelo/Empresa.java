package main.java.ar.edu.utn.frba.dds.modelo;

import java.util.List;

import org.uqbar.commons.utils.Observable;

import com.fasterxml.jackson.annotation.JsonProperty;

@Observable
public class Empresa {
	@JsonProperty("empresa")
	private String nombre;
	@JsonProperty("balances")
	private List<Balance> balances;

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Balance> getBalances() {
		return balances;
	}

	public void setBalances(List<Balance> balances) {
		this.balances = balances;
	}

	public double valorBalances() {
		return balances
				.stream()
				.mapToDouble(cuenta -> cuenta.getValor())
				.sum();
	}

	public Empresa() {
		super();
	}

}
