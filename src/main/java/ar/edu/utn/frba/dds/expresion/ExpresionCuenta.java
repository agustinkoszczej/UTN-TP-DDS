package ar.edu.utn.frba.dds.expresion;

import ar.edu.utn.frba.dds.modelo.TipoDeCuenta;

import java.util.ArrayList;
import java.util.List;

import ar.edu.utn.frba.dds.modelo.Empresa;

public class ExpresionCuenta extends ExpresionSimple{

	TipoDeCuenta cuenta;
	
	public ExpresionCuenta(TipoDeCuenta cuenta){
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

	public List<Object> listaDeElementos() {
		List<Object> listaConUnElemento = new ArrayList<Object>();
		listaConUnElemento.add(cuenta);
		return listaConUnElemento;
	}
}


