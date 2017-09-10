package ar.edu.utn.frba.dds.modelo;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import org.uqbar.commons.utils.Observable;

import com.fasterxml.jackson.annotation.JsonProperty;

@Observable
public class Balance {
	@Id	@GeneratedValue
	private int id;
	
	@JsonProperty("periodo")
	private String periodo;
	@JsonProperty("frecuencia") @Transient
	private Frecuencia frecuencia;
	@JsonProperty("periodicidad")
	private String periodicidad;
	
	@JsonProperty("tipoCuenta") 
	//@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
	@Transient
	private TipoDeCuenta tipoCuenta;
	@JsonProperty("valor")
	private Double valor;

	public Balance() {
		super();
	}
	
	public String getPeriodo() {
		return periodo;
	}

	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}

	public Frecuencia getFrecuencia() {
		return frecuencia;
	}

	public void setFrecuencia(Frecuencia frecuencia) {
		this.frecuencia = frecuencia;
	}

	public TipoDeCuenta getTipoCuenta() {
		return tipoCuenta;
	}

	public void setTipoCuenta(TipoDeCuenta tipoCuenta) {
		this.tipoCuenta = tipoCuenta;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}



}
