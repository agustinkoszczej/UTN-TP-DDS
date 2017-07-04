package ar.edu.utn.frba.dds.metodologia;

import ar.edu.utn.frba.dds.modelo.Balance;
import ar.edu.utn.frba.dds.modelo.Empresa;
public class ComparadorDesempenio extends CondicionComparativa{

	
	public Empresa cualEmpresaInvertir(Empresa emp1, Empresa emp2) {
	
		int valorEmpresa1 = 0;
		int valorEmpresa2 = 0;
		int mejorValor = 0;
		Balance balanceEmpresa1 = buscarBalanceEnPeriodo(emp1);
		Balance balanceEmpresa2 = buscarBalanceEnPeriodo(emp2);
		valorEmpresa1 = valorBalance(emp1, balanceEmpresa1);
		valorEmpresa2 = valorBalance(emp2, balanceEmpresa2);
		
		
		mejorValor = esMejorValor(valorEmpresa1, valorEmpresa2);
		
		if(mejorValor == valorEmpresa1)
			return emp1;
		if(mejorValor == valorEmpresa2)
			return emp2;
		return null;
	}

	

}
