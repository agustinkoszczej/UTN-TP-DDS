package ar.edu.utn.frba.dds.modelo;

import java.util.List;
import java.util.stream.Collectors;

import org.uqbar.commons.utils.Observable;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ibm.icu.text.DecimalFormat;

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

	public String valorBalances() {
		double valor = balances
				.stream()
				.mapToDouble(cuenta -> Double.parseDouble(cuenta.getValor()))
				.sum();
		//Esto lo hago porque sino aparece un numero del estilo "275E10" (con exponencial)
		DecimalFormat formatter = new DecimalFormat("##0.000");
		String s = formatter.format(valor); 
		return "$ " + s;
	}

	public Empresa() {
		super();
	}

}
