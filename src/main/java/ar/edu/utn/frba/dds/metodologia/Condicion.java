package ar.edu.utn.frba.dds.metodologia;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToOne;

import org.uqbar.commons.utils.Observable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import ar.edu.utn.frba.dds.modelo.Balance;
import ar.edu.utn.frba.dds.modelo.Empresa;
import ar.edu.utn.frba.dds.modelo.Indicador;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Observable
@JsonIgnoreProperties(value = { "changeSupport", "nombreClaseCondicion" })
@JsonTypeInfo(
	    use = JsonTypeInfo.Id.CLASS,
	    include = JsonTypeInfo.As.EXISTING_PROPERTY,
	    property = "claseCondicion")
public abstract class Condicion {
	
	@Id	//@GeneratedValue
	private int id;
	
	@Column
	@JsonProperty("nombreCondicion")
	protected String nombreCondicion;
	@OneToOne
	@JsonProperty("comparador")
	protected Comparador comparador;
	@OneToOne
	@JsonProperty("indicador")
	protected Indicador indicador;
	@Column
	@JsonProperty("inicioPeriodo")
	protected String inicioPeriodo; //tambien usado como periodo cuando es uno solo
	@Column
	@JsonProperty("finPeriodo")
	protected String finPeriodo;


	public void agregarAMetodologia(Metodologia metodo){
		metodo.agregarCondicion((Condicion)this);
	}
	
	protected int valorBalance(Empresa empresa, Balance balance) {
		try {
			return indicador.calcular(empresa, balance.getBalance_periodo());
		} catch (Exception e) {
			//e.printStackTrace();
		}
		return 0;
	}
	
	public String getInicioPeriodo() {
		return inicioPeriodo;
	}

	public void setInicioPeriodo(String inicioPeriodo) {
		this.inicioPeriodo = inicioPeriodo;
	}

	public String getFinPeriodo() {
		return finPeriodo;
	}

	public void setFinPeriodo(String finPeriodo) {
		this.finPeriodo = finPeriodo;
	}
	
	
	public Indicador getIndicador() {
		return indicador;
	}

	public void setIndicador(Indicador indicador) {
		this.indicador = indicador;
	}

	
	public String getNombreCondicion() {
		return nombreCondicion;
	}

	public void setNombreCondicion(String nombreCondicion) {
		this.nombreCondicion = nombreCondicion;
	}
	
	public String getClaseCondicion() {
		return this.getClass().getName();
	}
	
	public String getNombreClaseCondicion() {
		return this.getClass().getSimpleName();
	}
	
	public Comparador getComparador() {
		return comparador;
	}

	public void setComparador(Comparador comparador) {
		this.comparador = comparador;
	}

	protected Balance buscarBalanceEnPeriodo(Empresa empresa) {
		try{
		return empresa.getBalances().stream().filter(balance -> balance.getBalance_periodo().equals(getInicioPeriodo())).findFirst().get();
		}catch(NoSuchElementException e) {
			//e.printStackTrace();
		}
		return null;
	}
	
	protected List<Balance> devolverBalancesDentroDelPeriodo(Empresa empresa) {
		return (List<Balance>) empresa.getBalances()
			   .stream()
			   .filter(balance -> balance.getBalance_periodo().compareTo(inicioPeriodo) >= 0 && 
			   						balance.getBalance_periodo().compareTo(finPeriodo) <= 0).collect(Collectors.toList());
	}

	public abstract boolean esTaxativa();
	public void setClaseCondicion() {}
}
