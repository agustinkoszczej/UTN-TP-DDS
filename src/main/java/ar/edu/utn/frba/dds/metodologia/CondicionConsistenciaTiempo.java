package ar.edu.utn.frba.dds.metodologia;

import java.util.List;

import ar.edu.utn.frba.dds.modelo.Balance;
import ar.edu.utn.frba.dds.modelo.Empresa;

public class CondicionConsistenciaTiempo extends CondicionTaxativa {

	private int valorInicio = 0;

	@Override
	public Boolean deberiaInvertirEn(Empresa empresa) {
		List<Balance> balancesAceptados = devolverBalancesDentroDelPeriodo(empresa);
		valorInicio = obtenerValorIndicador(empresa, balancesAceptados.get(0).getBalance_periodo());
		balancesAceptados.remove(0);
		return balancesAceptados.stream().allMatch(balance -> esConsistente(empresa,balance.getBalance_periodo()));
		
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
		int valor = obtenerValorIndicador(empresa, periodo);
		return comparador.cumpleCondicion(valor, valorInicio);
	}

}
