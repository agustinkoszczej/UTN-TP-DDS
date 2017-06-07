package ar.edu.utn.frba.dds.expresion;

import ar.edu.utn.frba.dds.modelo.Empresa;

public class ExpresionConstante extends ExpresionSimple {

	Integer constante;
	
	public ExpresionConstante(Integer constante){
		this.constante = constante;
	}
	
	@Override
	public Integer calculate(Empresa empresa, String periodo) {
		//TODO: Cambiar a double cuando pasemos todo a double
		return constante;
	}
	
	public String toString(){
		return constante.toString();
	}

}




