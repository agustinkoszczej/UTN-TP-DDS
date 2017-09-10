package ar.edu.utn.frba.dds.expresion;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import ar.edu.utn.frba.dds.modelo.Empresa;

public class ExpresionCompuesta extends Expresion{

	private Expresion operando1;
	private Operacion op;
	private Expresion operando2;
	
	@JsonCreator
	public ExpresionCompuesta(@JsonProperty("operando1")Expresion operando1, 
							  @JsonProperty("operacion")Operacion op, 
							  @JsonProperty("operando2")Expresion operando2)
	{
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

	public Expresion getOperando1() {
		return operando1;
	}

	public void setOperando1(Expresion operando1) {
		this.operando1 = operando1;
	}

	public Operacion getOp() {
		return op;
	}

	public void setOp(Operacion op) {
		this.op = op;
	}

	public Expresion getOperando2() {
		return operando2;
	}

	public void setOperando2(Expresion operando2) {
		this.operando2 = operando2;
	}

}
