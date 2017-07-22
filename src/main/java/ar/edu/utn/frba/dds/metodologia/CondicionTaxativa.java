package ar.edu.utn.frba.dds.metodologia;

import org.uqbar.commons.utils.Observable;

import ar.edu.utn.frba.dds.modelo.Empresa;
@Observable
public abstract class CondicionTaxativa extends Condicion {
	
	public abstract Boolean deberiaInvertirEn(Empresa empresa);

	@Override
	public boolean esTaxativa() {
		return true;
	};	
}
