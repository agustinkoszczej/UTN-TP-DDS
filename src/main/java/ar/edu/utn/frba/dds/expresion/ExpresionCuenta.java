package ar.edu.utn.frba.dds.expresion;

import ar.edu.utn.frba.dds.modelo.Cuenta;
import ar.edu.utn.frba.dds.modelo.Empresa;

public class ExpresionCuenta extends ExpresionSimple{

	Cuenta cuenta;
	
	public ExpresionCuenta(Cuenta cuenta){
		this.cuenta = cuenta;
	}
	
	@Override
	public Integer calculate(Empresa empresa, String periodo) {
		//TODO: corregir y sacarle el intValue() cuando devolvamos Double
		return empresa.valorCuenta(cuenta, periodo).intValue();
	}
	
	public String toString(){
		return cuenta.toString();
	}

}


