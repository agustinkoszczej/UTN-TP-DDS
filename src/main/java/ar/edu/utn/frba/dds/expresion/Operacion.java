package ar.edu.utn.frba.dds.expresion;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import ar.edu.utn.frba.dds.util.Operator;

public class Operacion {

	Operator operador;
	
	@JsonCreator
	public Operacion(@JsonProperty("operador")Operator operador){
		this.operador = operador;
	}
	
	public Integer calcular(Integer a, Integer b){
		Method methodCall;
		try {
			methodCall = this.getClass().getDeclaredMethod(operador.methodName, Integer.class, Integer.class);
			return (Integer) methodCall.invoke(this, a, b);
		} catch (NoSuchMethodException e) {
			System.out.println("No existe el metodo correspondiente a la operacion solicitada");
			e.printStackTrace();
		} catch (SecurityException e) {
			System.out.println("Se quiere acceder a un metodo protegido sin tener los privilegios");
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			System.out.println("Se quiere acceder a un metodo privado sin tener los privilegios");
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			System.out.println("El metodo solicitado no se encuentra con los argumentos solicitados");
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			System.out.println("Error en la invocación del metodo de operación solicitado");
			e.printStackTrace();
		}
	    return null;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (this == obj)
			return true;
		if (getClass() != obj.getClass())
			return false;
		Operacion other = (Operacion) obj;
		return other.toString().equals(this.toString());
	}
	
	public String toString(){
		return operador.symbol;
	}
	
	//Instanciadores estaticos
	public static Operacion operacionSuma(){
		return new Operacion(Operator.ADD);
	}
	
	public static Operacion operacionResta(){
		return new Operacion(Operator.SUBSTRACT);
	}
	
	public static Operacion operacionMultiplicar(){
		return new Operacion(Operator.MULTIPLY);
	}
	
	public static Operacion operacionDividir(){
		return new Operacion(Operator.DIVIDE);
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

	public Operator getOperador() {
		return operador;
	}

	public void setOperador(Operator operador) {
		this.operador = operador;
	}
}
