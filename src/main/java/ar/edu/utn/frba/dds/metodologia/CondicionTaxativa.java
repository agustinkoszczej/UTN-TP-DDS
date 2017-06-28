package ar.edu.utn.frba.dds.metodologia;

import ar.edu.utn.frba.dds.modelo.Empresa;

public interface CondicionTaxativa {
	
	public Boolean deberiaInvertirEn(Empresa empresa);
	
}
