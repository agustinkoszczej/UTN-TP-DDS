package ar.edu.utn.frba.dds.metodologia;

import ar.edu.utn.frba.dds.modelo.Empresa;

public class CondicionAntiguedad extends CondicionTaxativa {
	
	private int aniosNecesarios;
	
	

	public int getAniosNecesarios() {
		return aniosNecesarios;
	}

	public void setAniosNecesarios(int aniosNecesarios) {
		this.aniosNecesarios = aniosNecesarios;
	}

	@Override
	public Boolean deberiaInvertirEn(Empresa empresa) {
		
		int antiguedadEmpresa = empresa.getAntiguedad();
		
		
		return antiguedadEmpresa >= aniosNecesarios;
	}

	
	
}
