package ar.edu.utn.frba.dds.metodologia;

import java.util.List;
import java.util.stream.Collectors;

import ar.edu.utn.frba.dds.modelo.Balance;
import ar.edu.utn.frba.dds.modelo.Empresa;
import ar.edu.utn.frba.dds.modelo.Indicador;

public class CondicionConsistenciaTiempo extends CondicionTaxativa {

	private Indicador indicador;
	private Comparador comparador;
	private String inicioPeriodo;
	private String finPeriodo;
	private int valorInicio = 0;
	private String nombre;

	@Override
	public Boolean deberiaInvertirEn(Empresa empresa) {
		List<Balance> balancesAceptados = devolverBalancesDentroDelPeriodo(empresa);
		valorInicio = obtenerValorIndicador(empresa, balancesAceptados.get(0).getPeriodo());
		balancesAceptados.remove(0);
		return balancesAceptados.stream().allMatch(balance -> esConsistente(empresa,balance.getPeriodo()));
		
	}


	private Integer obtenerValorIndicador(Empresa empresa, String periodo) {
		try {
			return indicador.calcular(empresa, periodo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}


	private Boolean esConsistente(Empresa empresa, String periodo) {

		Boolean cumple;
		int valor = obtenerValorIndicador(empresa, periodo);
		if(comparador.name() == "MAYOR")
			cumple = valor > valorInicio;
		else if(comparador.name() == "MENOR")
			cumple = valor < valorInicio;
		else throw new NullPointerException("No es posible comparar con el comparador usado");
			valorInicio = valor;
		return cumple;
	}



	private List<Balance> devolverBalancesDentroDelPeriodo(Empresa empresa) {
		return (List<Balance>) empresa.getBalances()
			   .stream()
			   .filter(balance -> balance.getPeriodo().compareTo(inicioPeriodo) >= 0 && 
			   						balance.getPeriodo().compareTo(finPeriodo) <= 0).collect(Collectors.toList());
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

	public String getFinPeriodo() {
		return finPeriodo;
	}

	public void setFinPeriodo(String finPeriodo) {
		this.finPeriodo = finPeriodo;
	}

	public String getInicioPeriodo() {
		return inicioPeriodo;
	}

	public void setInicioPeriodo(String inicioPeriodo) {
		this.inicioPeriodo = inicioPeriodo;
	}




	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	

}
