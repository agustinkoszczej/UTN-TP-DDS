package ar.edu.utn.frba.dds.modelo;

import org.uqbar.commons.utils.Observable;

import com.fasterxml.jackson.annotation.JsonProperty;

@Observable
public class Balance {
	@JsonProperty("periodo")
	private String periodo;

	public String getPeriodo() {
		return periodo;
	}

	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}

	public String getPeriodicidad() {
		return periodicidad;
	}

	public void setPeriodicidad(String periodicidad) {
		this.periodicidad = periodicidad;
	}

	public String getTipoCuenta() {
		return tipoCuenta;
	}

	public void setTipoCuenta(String tipoCuenta) {
		this.tipoCuenta = tipoCuenta;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	@JsonProperty("periodicidad")
	private String periodicidad;
	@JsonProperty("tipoCuenta")
	private String tipoCuenta;
	@JsonProperty("valor")
	private String valor;

	public Balance() {
		super();
	}

}
