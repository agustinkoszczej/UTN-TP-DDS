package ar.edu.utn.frba.dds.modelo;

import java.util.Calendar;
import java.util.List;
import java.util.NoSuchElementException;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;

import org.uqbar.commons.utils.Observable;

import com.fasterxml.jackson.annotation.JsonProperty;

@Observable @Entity
public class Empresa {
	
	@Id @GeneratedValue
	private int empresa_id;
	
	@JsonProperty("empresa")
	private String empresa_nombre;
	
	@JsonProperty("anioCreacion")
	private Integer empresa_anioCreacion;
	
	@JsonProperty("balances")@Transient
	private List<Balance> balances;
	
	public Empresa() {
		super();
	}
	
	public int getEmpresa_id() {
		return empresa_id;
	}

	public void setEmpresa_id(int empresa_id) {
		this.empresa_id = empresa_id;
	}

	public int getEmpresa_anioCreacion() {
		return empresa_anioCreacion;
		}
		
	public void setEmpresa_anioCreacion(int anioCreacion) {
		this.empresa_anioCreacion = anioCreacion;
	}

	public Balance obtenerBalance(TipoDeCuenta tipoCuenta, String periodo){
		try{
			Balance bal = balances.stream().filter(balance -> balance.getBalance_periodo().equals(periodo) && balance.getBalance_tipoCuenta().equals(tipoCuenta)).findFirst().get();
			return bal;
		}catch(NoSuchElementException e){
			//TODO: hacer algo si no tiene el balance que buscamos
		}
		return null;
	}
	
	public Double valorCuenta(TipoDeCuenta tipoCuenta, String periodo){
		try{
		return this.obtenerBalance(tipoCuenta, periodo).getBalance_valor();
		}catch(NullPointerException e){
			return (double) 0;
		}
	}
	
	public String getEmpresa_nombre() {
		return empresa_nombre;
	}

	public void setEmpresa_nombre(String nombre) {
		this.empresa_nombre = nombre;
	}
	
	public List<Balance> getBalances() {
		return balances;
	}

	public void setBalances(List<Balance> balances) {
		this.balances = balances;
	}

	public double getValorBalances() {
		double result;
		try{
			result = balances
					.stream()
					.mapToDouble(cuenta -> cuenta.getBalance_valor())
					.sum();
		}catch(NullPointerException e){
			result = 0;
		}
		return result; 
	}
	
	public double getAntiguedad() {
		Calendar cal= Calendar.getInstance(); 
		int anioActual = cal.get(Calendar.YEAR); 
		if(balances != null && !(balances.isEmpty())){
		double antiguedad =  balances
					.stream()
					.mapToInt(balance -> Integer.parseInt(balance.getBalance_periodo().substring(0, 4)))
					.min()
					.getAsInt();
		return anioActual - antiguedad;
		}
		return 0;
	}
	public boolean seLlamaIgualQue(Empresa otraEmpresa) {
		return this.getEmpresa_nombre().equals(otraEmpresa.getEmpresa_nombre());
	}

	public boolean tieneBalances(){
		return this.getBalances() != null;
	}
}
