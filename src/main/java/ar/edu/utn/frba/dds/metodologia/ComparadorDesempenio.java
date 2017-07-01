package ar.edu.utn.frba.dds.metodologia;

import java.util.NoSuchElementException;

import ar.edu.utn.frba.dds.modelo.Balance;
import ar.edu.utn.frba.dds.modelo.Empresa;
import ar.edu.utn.frba.dds.modelo.Indicador;
import net.sf.oval.constraint.Max;

public class ComparadorDesempenio extends CondicionComparativa{

	private String nombre;
	private Indicador indicador;
	private Comparador comparador;
	private String periodoComparacion;
	
	
	public Empresa cualEmpresaInvertir(Empresa emp1, Empresa emp2) {
	
		int valorEmpresa1 = 0;
		int valorEmpresa2 = 0;
		int mejorValor = 0;
		Balance balanceEmpresa1 = buscarBalanceEnPeriodo(emp1);
		Balance balanceEmpresa2 = buscarBalanceEnPeriodo(emp2);
		try{
			valorEmpresa1 = indicador.calcular(emp1, balanceEmpresa1.getPeriodo());
			valorEmpresa2 = indicador.calcular(emp2, balanceEmpresa2.getPeriodo());
		} catch (Exception e) {
			e.printStackTrace();
		}
		mejorValor = esMejorValor(valorEmpresa1, valorEmpresa2);
		
		if(mejorValor == valorEmpresa1)
			return emp1;
		if(mejorValor == valorEmpresa2)
			return emp2;
		return null;
	}

	private int esMejorValor(int valorEmpresa1, int valorEmpresa2) {
		if(comparador.name() == "MAYOR")
			return Integer.max(valorEmpresa1, valorEmpresa2);
		if(comparador.name() == "MENOR")
			return Integer.min(valorEmpresa1, valorEmpresa2);
		else throw new NullPointerException("No es posible comparar con el comparador usado");
	}

	private Balance buscarBalanceEnPeriodo(Empresa empresa) {
		try{
		return empresa.getBalances().stream().filter(balance -> balance.getPeriodo().equals(periodoComparacion)).findFirst().get();
		}catch(NoSuchElementException e) {
			e.printStackTrace();
		}
		return null;
	}

	public Indicador getIndicador() {
		return indicador;
	}

	public void setIndicador(Indicador indicador) {
		this.indicador = indicador;
	}

	public Comparador getComparador() {
		return comparador;
	}

	public void setComparador(Comparador comparador) {
		this.comparador = comparador;
	}

	public String getPeriodoComparacion() {
		return periodoComparacion;
	}

	public void setPeriodoComparacion(String periodoComparacion) {
		this.periodoComparacion = periodoComparacion;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	

}
