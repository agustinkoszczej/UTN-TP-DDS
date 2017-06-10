package ar.edu.utn.frba.dds.expresion;

import java.util.ArrayList;
import java.util.List;

import ar.edu.utn.frba.dds.modelo.Empresa;

public class ExpresionCompuesta implements Expresion{

	Expresion operando1;
	Operacion op;
	Expresion operando2;
	
	public ExpresionCompuesta(Expresion operando1, Operacion op, Expresion operando2){
		this.operando1 = operando1;
		this.op = op;
		this.operando2 = operando2;
	}
	
	public Integer calculate(Empresa empresa, String periodo) {
		return op.calcular(operando1.calculate(empresa, periodo), operando2.calculate(empresa, periodo));
	}
	
	public String toString(){
		return operando1.toString() + op.toString() + operando2.toString();
	}

	public List<Object> listaDeElementos() {
		List<Object> elementosDeLaExpresion = new ArrayList<Object>();
		elementosDeLaExpresion.addAll(operando1.listaDeElementos());
		elementosDeLaExpresion.add(op);
		elementosDeLaExpresion.addAll(operando2.listaDeElementos());
		return elementosDeLaExpresion;
	}

}
