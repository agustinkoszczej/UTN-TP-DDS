package ar.edu.utn.frba.dds.modelo;

import java.util.Calendar;
import java.util.List;
import java.util.NoSuchElementException;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.uqbar.commons.utils.Observable;

import com.fasterxml.jackson.annotation.JsonProperty;

@Observable @Entity
public class Empresa {
	
	@Id
	@GeneratedValue
	private int id;
	
	@JsonProperty("empresa")
	private String nombre;
	
	@JsonProperty("balances")
	private List<Balance> balances;
	
	public Empresa() {
		super();
	}

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
	
	public double getAntiguedad() {
		Calendar cal= Calendar.getInstance(); 
		int anioActual = cal.get(Calendar.YEAR); 
		if(balances != null){
		Double antiguedad =  balances
				.stream()
				.mapToDouble(balance -> Integer.parseInt(balance.getPeriodo().substring(0, 4)))
				.min().getAsDouble();
		return anioActual - antiguedad;
		}
		return 0;
		//return anioActual - anioCreacion;
	}


}
