package ar.edu.utn.frba.dds.util;

public enum Operator {
	ADD(1, "sum", "+"), SUBSTRACT(1, "minus", "-"), MULTIPLY(2, "mult", "*"), DIVIDE(2, "div", "/"), CONSTANT(0, "number", "number"), VARIABLE(0, "variable", "variable");
	final Integer precedence;
	public final String methodName;
	public final String symbol;

	Operator(Integer p, String m, String s) {
		precedence = p;
		methodName = m;
		symbol = s;
	}
}
