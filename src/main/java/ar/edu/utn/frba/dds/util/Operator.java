package ar.edu.utn.frba.dds.util;

public enum Operator {
	ADD(1, "sum"), SUBSTRACT(1, "minus"), MULTIPLY(2, "mult"), DIVIDE(2, "div");
	final Integer precedence;
	final String methodName;

	Operator(Integer p, String m) {
		precedence = p;
		methodName = m;
	}
}
