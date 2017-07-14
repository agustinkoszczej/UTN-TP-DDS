package ar.edu.utn.frba.dds.metodologia;

import java.util.List;

import ar.edu.utn.frba.dds.modelo.Balance;
import ar.edu.utn.frba.dds.modelo.Empresa;

public class CondicionSuperaValor extends CondicionTaxativa {

	private double valorSuperar;
	
	@Override
	public Boolean deberiaInvertirEn(Empresa empresa) {
		List<Balance> balancesAceptados = devolverBalancesDentroDelPeriodo(empresa);
		return balancesAceptados.stream().allMatch(balance -> seCumpleComparacion(valorBalance(empresa, balance), valorSuperar));	
	}

	private Boolean seCumpleComparacion(double valor, double valorSuperar) {
		return comparador.cumpleCondicion(valor, valorSuperar);	
	}

	public double getValorSuperar() {
		return valorSuperar;
	}

	public void setValorSuperar(int valorSuperar) {
		this.valorSuperar = valorSuperar;
	}
}
