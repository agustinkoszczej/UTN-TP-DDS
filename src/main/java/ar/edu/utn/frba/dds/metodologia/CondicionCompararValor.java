package ar.edu.utn.frba.dds.metodologia;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;

import ar.edu.utn.frba.dds.modelo.Balance;
import ar.edu.utn.frba.dds.modelo.Empresa;

@Entity
public class CondicionCompararValor extends CondicionTaxativa {
	@Column
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
