package ar.edu.utn.frba.dds.metodologia;

import java.util.List;

import ar.edu.utn.frba.dds.modelo.Balance;
import ar.edu.utn.frba.dds.modelo.Empresa;

public class CondicionSuperaValor extends CondicionTaxativa {

	private int valorSuperar;
	
	@Override
	public Boolean deberiaInvertirEn(Empresa empresa) {
		// TODO Auto-generated method stub
		List<Balance> balancesAceptados = devolverBalancesDentroDelPeriodo(empresa);
		return balancesAceptados.stream().allMatch(balance -> seCumpleComparacion(valorBalance(empresa, balance), valorSuperar));
		
	}

	

	private Boolean seCumpleComparacion(int valor, int valorSuperar2) {
		// TODO Auto-generated method stub
		if(comparador.name() == "MAYOR")
			return valor > valorSuperar2;
		if(comparador.name() == "MENOR")
			return valor < valorSuperar2;
		else throw new NullPointerException("No es posible comparar con el comparador usado");
					
		
	}

	public int getValorSuperar() {
		return valorSuperar;
	}

	public void setValorSuperar(int valorSuperar) {
		this.valorSuperar = valorSuperar;
	}
	
	
}
