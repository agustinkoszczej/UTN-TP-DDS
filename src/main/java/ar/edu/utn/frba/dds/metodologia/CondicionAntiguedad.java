package ar.edu.utn.frba.dds.metodologia;

import ar.edu.utn.frba.dds.modelo.Empresa;

public class CondicionAntiguedad implements CondicionTaxativa {
	
	private int aniosNecesarios;
	
	

	public int getAniosNecesarios() {
		return aniosNecesarios;
	}

	public void setAniosNecesarios(int aniosNecesarios) {
		this.aniosNecesarios = aniosNecesarios;
	}

	@Override
	public Boolean deberiaInvertirEn(Empresa empresa) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
