package ar.edu.utn.frba.dds.metodologia;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.uqbar.commons.utils.Observable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Observable
@JsonIgnoreProperties(value = { "changeSupport" })
public class Comparador {
	
	@Id	@GeneratedValue
	private int id;
	
	public enum Comparadores {MAYOR, MENOR, IGUAL, MAYOREIGUAL, MENOREIGUAL};
	
	@Column
	public Comparadores comparador;
	@Column
	public String nombre;
	
	public Comparadores getComparador() {
		return comparador;
	}

	public void setComparador(Comparadores comparador) {
		this.comparador = comparador;
		this.nombre = comparador.name();
	}
	
	public String getNombre(){
		return nombre;
	}

	public Boolean cumpleCondicion(double valor, double valorASuperar) {
		if(comparador.name() == "MAYOR")
			return valor > valorASuperar;
		if(comparador.name() == "MENOR")
			return valor < valorASuperar;
		if(comparador.name() == "IGUAL")
			return valor == valorASuperar;
		if(comparador.name() == "MAYOREIGUAL")
			return valor >= valorASuperar;
		if(comparador.name() == "MENOREIGUAL")
			return valor <= valorASuperar;	
		return false;
	}
	
	public double devolverSegunComparador(Double valor1, Double valor2) {
		if(comparador.name() == "MAYOR"){
			return Double.max(valor1, valor2);}
		else if(comparador.name() == "MENOR")
			return Double.min(valor1, valor2);
		else
			return valor1;
	}	
}
