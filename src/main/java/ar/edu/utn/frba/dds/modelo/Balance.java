package ar.edu.utn.frba.dds.modelo;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.uqbar.commons.utils.Observable;

import com.fasterxml.jackson.annotation.JsonProperty;

@Observable @Entity
public class Balance {
	
	@Id @GeneratedValue
	private int balance_id;
	
	@JsonProperty("periodo")
	private String balance_periodo;
	@JsonProperty("frecuencia")
	//@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
	@Enumerated(EnumType.STRING)
	private Frecuencia balance_frecuencia;
	@JsonProperty("tipoCuenta") 
	//@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
	@Enumerated(EnumType.STRING)
	private TipoDeCuenta balance_tipoCuenta;
	@JsonProperty("valor")
	private Double balance_valor;

	private int balance_empresa;

	public Balance() {
		super();
	}
	
	
	public int getBalance_id() {
		return balance_id;
	}


	public void setBalance_id(int balance_id) {
		this.balance_id = balance_id;
	}


	public String getBalance_periodo() {
		return balance_periodo;
	}

	public void setBalance_periodo(String periodo) {
		this.balance_periodo = periodo;
	}

	public Frecuencia getBalance_frecuencia() {
		return balance_frecuencia;
	}

	public void setBalance_frecuencia(Frecuencia frecuencia) {
		this.balance_frecuencia = frecuencia;
	}

	public TipoDeCuenta getBalance_tipoCuenta() {
		return balance_tipoCuenta;
	}

	public void setBalance_tipoCuenta(TipoDeCuenta tipoCuenta) {
		this.balance_tipoCuenta = tipoCuenta;
	}

	public Double getBalance_valor() {
		return balance_valor;
	}

	public void setBalance_valor(Double valor) {
		this.balance_valor = valor;
	}
}
