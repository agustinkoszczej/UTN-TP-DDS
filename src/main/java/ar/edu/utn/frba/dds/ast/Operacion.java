package ar.edu.utn.frba.dds.ast;

import java.lang.reflect.Method;

import ar.edu.utn.frba.dds.util.Operator;

public class Operacion implements AST {
	private Operator operacion;
	public Operator getOperacion() {
		return operacion;
	}

	private Operacion operacionPrecedente;
	
	public void setOperacionPrecedente(Operacion operacionPrecedente) {
		this.operacionPrecedente = operacionPrecedente;
	}

	public Operacion getOperacionPrecedente() {
		return operacionPrecedente;
	}

	// TODO: Cambiar implementacion por Array de Optional
	private AST[] operandos = {null, null};

	public Operacion(Operator operator) {
		operacion = operator;
	}

	public Operacion(Operator operator, Operacion operacionPrecedente) {
		this.operacion = operator;
		this.operacionPrecedente = operacionPrecedente;
	}

	
	@Override
	public Integer resultado() {
		if ((operandos[1] == null) && (operandos[0].getClass() == Constante.class) ) {
			return ((Constante)operandos[0]).resultado();
		} else {
			return calcularExpression(operacion.name(), operandos[0], operandos[1]);
		}
	}
	
	public void agregarOperando(AST operando) {
		if (operandos[0] == null)
			operandos[0] = operando;
		else if ((operandos[0] != null) && (operandos[1] == null)) 
			operandos[1] = operando;
	}
	
	private Integer calcularExpression(String methodName, Object operand1, Object operand2) {
		// Evaluar si los operandos son numeros o son otra operacion o una variable
		//Class<ExpressionEval> expressionParserClass = ExpressionEval.class;
        try {
        	Method methodCall = this.getClass().getDeclaredMethod(methodName, Integer.class, Integer.class);
            return (Integer) methodCall.invoke(this, operand1, operand2);
         } catch (Exception e) {
            //e.printStackTrace();
        	 return 0;
         }
		//return 0;
	}

	@SuppressWarnings("unused")
	private static Integer sum(Integer a, Integer b) {
		return a + b;
	}

	@SuppressWarnings("unused")
	private static Integer minus(Integer a, Integer b) {
		return a - b;
	}

	@SuppressWarnings("unused")
	private static Integer mult(Integer a, Integer b) {
		return a * b;
	}

	@SuppressWarnings("unused")
	private static Integer div(Integer a, Integer b) {
		return a / b;
	}

	public AST getOperando(int i) {
		return operandos[i];
	}

	public void setOperando(int i, Operacion newOperator) {
		operandos[i] = newOperator;
	}

}
