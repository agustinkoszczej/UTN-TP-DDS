package ar.edu.utn.frba.dds.metodologia;

import java.util.List;
import java.util.stream.Collectors;

import ar.edu.utn.frba.dds.modelo.Balance;
import ar.edu.utn.frba.dds.modelo.Empresa;
import ar.edu.utn.frba.dds.modelo.Indicador;

public class CondicionSuperaValor implements CondicionTaxativa {
	private Indicador indicador;
	private Comparador comparador;
	private String inicioPeriodo;
	private String finPeriodo;
	private int valorSuperar;
	
	@Override
	public Boolean deberiaInvertirEn(Empresa empresa) {
		// TODO Auto-generated method stub
		List<Balance> balancesAceptados = devolverBalancesDentroDelPeriodo(empresa);
		Boolean cumplio = false;
			
		cumplio = balancesAceptados.stream().allMatch(balance -> {
			try {
			return	seCumpleComparacion(indicador.calcular(empresa, balance.getPeriodo()), valorSuperar);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return false;
		});
		
		return cumplio;
	}

	private List<Balance> devolverBalancesDentroDelPeriodo(Empresa empresa) {
		return (List<Balance>) empresa.getBalances()
			   .stream()
			   .filter(balance -> balance.getPeriodo().compareTo(inicioPeriodo) >= 0 && 
			   						balance.getPeriodo().compareTo(finPeriodo) <= 0).collect(Collectors.toList());
	}

	private Boolean seCumpleComparacion(int valor, int valorSuperar2) throws Exception {
		// TODO Auto-generated method stub
		if(comparador.name() == "MAYOR")
			return valor > valorSuperar2;
		if(comparador.name() == "MENOR")
			return valor < valorSuperar2;
		else throw new Exception("No es posible comparar con el comparador usado");
					
		
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

	public int getValorSuperar() {
		return valorSuperar;
	}

	public void setValorSuperar(int valorSuperar) {
		this.valorSuperar = valorSuperar;
	}
	
	
}
