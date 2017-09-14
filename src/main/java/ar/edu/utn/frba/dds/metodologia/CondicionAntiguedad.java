package ar.edu.utn.frba.dds.metodologia;

import javax.persistence.Column;
import javax.persistence.Entity;

import ar.edu.utn.frba.dds.modelo.Empresa;

@Entity
public class CondicionAntiguedad extends CondicionTaxativa {
	
	@Column
	private int aniosNecesarios;
	
	public int getAniosNecesarios() {
		return aniosNecesarios;
	}

	public void setAniosNecesarios(int aniosNecesarios) {
		this.aniosNecesarios = aniosNecesarios;
	}

	@Override
	public Boolean deberiaInvertirEn(Empresa empresa) {	
		double antiguedadEmpresa = empresa.getAntiguedad();		
		return antiguedadEmpresa >= aniosNecesarios;
	}

	
	
}
