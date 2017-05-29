package main.java.ar.edu.utn.frba.dds.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class expressionParser {
	
	@SuppressWarnings("unused")
	private static void sum(String a, String b) {
		System.out.println("ACA VA LA SUMA");
	}
	
    private enum Operator
    {
        ADD(1, "sum"), SUBTRACT(1, "minus"), MULTIPLY(2, "mult"), DIVIDE(2, "divide"), LPARENTH(0, null), RPARENTH(0, null);
        final Integer precedence;
        final String methodName;
        Operator(Integer p, String m) { precedence = p; methodName = m;}
    }
    
    private enum symbolType {
    	constant,
    	function,
    	operator,
    	invalid,
    	epsilon;
    }

    private static Map<String, Operator> ops = new HashMap<String, Operator>() {{
        put("+", Operator.ADD);
        put("-", Operator.SUBTRACT);
        put("*", Operator.MULTIPLY);
        put("/", Operator.DIVIDE);
        put("(", Operator.LPARENTH);
        put(")", Operator.RPARENTH);
    }};

    private static boolean isHigerPrec(String op, String sub)
    {
        return (ops.containsKey(sub) && ops.get(sub).precedence >= ops.get(op).precedence);
    }

  	private static String currentString;
    private static symbolType currentState;
	private static Map<String, symbolType> tokens = new LinkedHashMap<String, symbolType>();
  
    public static String evaluate(String expression)  {
	  currentString = "";
      currentState = null;
      // TODO: Verificar que siempre haga una vuelta mas con un epsilon para la ultima expresion
      //       se puede agregar un caracter de espacio
      for(Integer currentCharPosition = 0; currentCharPosition < expression.length(); currentCharPosition++) {
        char charToEval = expression.charAt(currentCharPosition);
        evaluateChar(charToEval);
      }
	  
      Class<expressionParser> expressionParserClass = expressionParser.class;
      Method methodCall;
      for(Map.Entry<String, symbolType> token : tokens.entrySet()) {
        System.out.print(token.getKey());
        System.out.print(" - ");
        System.out.println(token.getValue());
        if (ops.containsKey(token.getKey())) {
        	// FIXME: ver que el el metodo no sea null, como el casod e los parentesis
            try {
    			methodCall = expressionParserClass.getDeclaredMethod(ops.get(token.getKey()).methodName, String.class, String.class);
    			methodCall.invoke(null, "a", "b");
    		} catch (NoSuchMethodException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		} catch (SecurityException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
	  }
      return "";
    }

    private static void evaluateChar(char charToEval) {
		switch (symbolType(charToEval)) {
		case constant:
          if ((currentState == null) || (currentState == symbolType.constant)) {
            currentState = symbolType.constant;
          	currentString += String.valueOf(charToEval);
          } else if (currentState == symbolType.function) {
            currentState = symbolType.function;
          	currentString += String.valueOf(charToEval);
          } else if (currentState == symbolType.operator) {
            tokens.put(currentString, currentState);
            currentState = symbolType.constant;
          	currentString = String.valueOf(charToEval);
          } else {
            // tirar excepcion
          }
          //System.out.println(String.valueOf(charToEval));
		  break;
		case epsilon:
          if (currentState != null) {
          	tokens.put(currentString, currentState);
		  	currentString = "";
          	currentState = null;
          }
          break;
		case function:
          if ((currentState == null) || (currentState == symbolType.function)) {
            currentState = symbolType.function;
          	currentString += String.valueOf(charToEval);
          } else if (currentState == symbolType.operator) {
            tokens.put(currentString, currentState);
            currentState = symbolType.function;
          	currentString = String.valueOf(charToEval);
          } else {
          	// tirar excepcion
          }
			break;
		case operator:
          if (currentState == null) {
            currentState = symbolType.operator;
          	currentString += String.valueOf(charToEval);
          } else { 
            tokens.put(currentString, currentState);
            currentState = symbolType.operator;
          	currentString = String.valueOf(charToEval);
          }
			break;
		case invalid:
          // tirar excepcion de caracter no valido
			break;
		}
	}
	
	private static symbolType symbolType(char symbolToEval) {
		Integer symbolValue = (int) symbolToEval;
		if ((symbolValue >= 48) && (symbolValue <= 57)) // [0..9]
			return symbolType.constant;
		if ((symbolValue >= 40) && (symbolValue <= 41)) // ()
			return symbolType.operator;
		if (symbolValue == 42) // *
			return symbolType.operator;
		if (symbolValue == 43) // +
			return symbolType.operator;
		if (symbolValue == 45) // -
			return symbolType.operator;
		if (symbolValue == 47) // /
			return symbolType.operator;
		if ((symbolValue >= 65) && (symbolValue <= 90)) // [A..Z]
			return symbolType.function;
		if ((symbolValue >= 97) && (symbolValue <= 122)) // [a..z]
			return symbolType.function;
		if (symbolValue == 9)  // tab
			return symbolType.epsilon;
		if (symbolValue == 13)  // enter
			return symbolType.epsilon;
		if (symbolValue == 32)  // space
			return symbolType.epsilon;
		return symbolType.invalid;
	}
}
