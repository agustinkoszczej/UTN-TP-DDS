package ar.edu.utn.frba.dds.metodologia;

import javax.persistence.Entity;

import ar.edu.utn.frba.dds.modelo.Empresa;

@Entity
public class ComparadorAntiguedad extends CondicionComparativa {

	public Empresa cualEmpresaInvertir(Empresa emp1, Empresa emp2) {
		double valor = valorSegunComparador(emp1.getAntiguedad(), emp2.getAntiguedad());
		if(valor == emp1.getAntiguedad())
			return emp1;
		else
			return emp2;	
	}

}
