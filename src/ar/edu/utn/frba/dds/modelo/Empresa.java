package ar.edu.utn.frba.dds.modelo;

import java.util.List;

import org.uqbar.commons.utils.Observable;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Clase que representa a un alumno, en el Modelo, com un POJO, con las 
 * siguientes propiedades y sus correspondientes Setters/Getters:
 * <ul>
 * 		<li><b>legajo</b>: valor de solo lectura</li>
 *  	<li><b>nombre</b></li>
 *  	<li><b>apellido</b></li>
 * 		<li><b>github</b>: usuario github</li>
 * </ul>
 * <p>Tiene dos constructores:
 * <ul>
 * 		<li><b>Alumno()</b>: no setea ninguna propiedad</li>
 * 		<li><b>Alumno(String legajo)</b>: setea la propiedad legajo, ya 
 * 				que el legajo se se considera unico y no modificable</li>
 * <ul>
 * Es Observable para poder accedida por Arena
 */
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


	/**
	 * Constructor de Alumno: este constructor inicializa la Collecion Tarea
	 * 		como un ArrayList.
	 * El resto de las propiedades se deberan asignar individualmente mediante
	 * 		Setters/Getters
	 * @return	Alumno
	 */
	public Empresa() {
		super();
	}
	
}
