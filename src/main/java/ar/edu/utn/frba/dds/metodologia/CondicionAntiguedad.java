package ar.edu.utn.frba.dds.metodologia;

import java.util.List;

import ar.edu.utn.frba.dds.modelo.Balance;
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
		
		int antiguedadEmpresa = (int)empresa.getAntiguedad();
		
		
		return antiguedadEmpresa >= aniosNecesarios;
	}

	
	
}
