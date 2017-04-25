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

	public Cuenta getTipoCuenta() {
		return tipoCuenta;
	}

	public void setTipoCuenta(Cuenta tipoCuenta) {
		this.tipoCuenta = tipoCuenta;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	@JsonProperty("periodicidad")
	private String periodicidad;
	@JsonProperty("tipoCuenta")
	private Cuenta tipoCuenta;
	@JsonProperty("valor")
	private Double valor;

	public Balance() {
		super();
	}

}
