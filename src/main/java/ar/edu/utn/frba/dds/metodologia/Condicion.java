package ar.edu.utn.frba.dds.metodologia;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.uqbar.commons.utils.Observable;

import ar.edu.utn.frba.dds.modelo.Balance;
import ar.edu.utn.frba.dds.modelo.Empresa;
import ar.edu.utn.frba.dds.modelo.Indicador;

@Observable
public abstract class Condicion {
	protected String nombreCondicion;
	protected Comparador comparador;
	protected Indicador indicador;
	protected String inicioPeriodo; //tambien usado como periodo cuando es uno solo
	protected String finPeriodo;


	public void agregarAMetodologia(Metodologia metodo){
		metodo.agregarCondicion((Condicion)this);
	}
	
	protected int valorBalance(Empresa empresa, Balance balance) {
		
		
		try {
			return indicador.calcular(empresa, balance.getPeriodo());
		} catch (Exception e) {
			e.printStackTrace();
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
		return this.getClass().getSimpleName();
	}
	
	public void setClaseCondicion() {
		//nada
	}
	
	public Comparador getComparador() {
		return comparador;
	}

	public void setComparador(Comparador comparador) {
		this.comparador = comparador;
	}


	protected Balance buscarBalanceEnPeriodo(Empresa empresa) {
		try{
		return empresa.getBalances().stream().filter(balance -> balance.getPeriodo().equals(getInicioPeriodo())).findFirst().get();
		}catch(NoSuchElementException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	protected List<Balance> devolverBalancesDentroDelPeriodo(Empresa empresa) {
		return (List<Balance>) empresa.getBalances()
			   .stream()
			   .filter(balance -> balance.getPeriodo().compareTo(inicioPeriodo) >= 0 && 
			   						balance.getPeriodo().compareTo(finPeriodo) <= 0).collect(Collectors.toList());
	}
	
	
}
