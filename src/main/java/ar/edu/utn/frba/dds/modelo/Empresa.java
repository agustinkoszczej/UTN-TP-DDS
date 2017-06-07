package ar.edu.utn.frba.dds.modelo;

import java.util.List;
import java.util.NoSuchElementException;

import org.uqbar.commons.utils.Observable;

import com.fasterxml.jackson.annotation.JsonProperty;

@Observable
public class Empresa {
	@JsonProperty("empresa")
	private String nombre;
	@JsonProperty("balances")
	private List<Balance> balances;

	public Balance obtenerBalance(TipoDeCuenta tipoCuenta, String periodo){
		try{
			Balance bal = balances.stream().filter(balance -> balance.getPeriodo().equals(periodo) && balance.getTipoCuenta().equals(tipoCuenta)).findFirst().get();
			return bal;
		}catch(NoSuchElementException e){
			//TODO: hacer algo si no tiene el balance que buscamos
		}
		return null;
	}
	
	public Double valorCuenta(TipoDeCuenta tipoCuenta, String periodo){
		return this.obtenerBalance(tipoCuenta, periodo).getValor();
	}
	
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
