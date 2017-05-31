package ar.edu.utn.frba.dds.util;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ExpressionEval {
	
	private ExpressionParser parser = new ExpressionParser();
	private String expression;
	private Map<String, Integer> variables = new HashMap<String, Integer>();
	
	public ExpressionEval(String expression) {
		this.expression = expression;
		this.evaluate();
	}
	
	public void setVariableValue(String variableName, Integer valor) {
		variables.remove(variableName);
		variables.put(variableName, valor);
	}
	
	public Collection<String> getVaVariableNames() {
		Collection<String> variableList = new ArrayList<String>();
		for (Map.Entry<String, Integer> variable : variables.entrySet()) {
			variableList.add(variable.getKey());
		}
		return variableList;
	}
	
	public void setExpression(String expression) {
		this.expression = expression;
		this.evaluate();
	}
	
	public String getExpression() {
		return expression;
	}
	
	private Stack<String> stackTokens = null;
	private Stack<Operator> stackOperators = null;
	
	private Boolean evaluate() {
		parser.parse(expression);
		stackTokens = parser.getStackTokens();
		stackOperators = parser.getStackOperators();
		for(String variable: parser.getVariables()) {
			variables.put(variable, 0);
		}
		//FIXME: que devuelva lo que hay que devolver
		return true;
	}
	
	public Integer calculate() {
		// TODO: Ver casos de cadena vacia y solo un operaando

        Operator operation;
        Integer operand1;
        Integer operand2;
        Integer result = 0;
        
        if(stackOperators.empty() && stackTokens.size() == 1){
        	result = Integer.parseInt(expression);
        }
        else{
        while (!stackOperators.empty()) {
          operation = stackOperators.pop();
          operand1 = tokenValue(stackTokens.pop());
          operand2 = tokenValue(stackTokens.pop());
          if (!stackOperators.empty() && isHigerPrec(operation, stackOperators.peek())) {
        	operation = HigerPrec(stackOperators, Operator.class, operation);
        	operand1 = tokenValue(HigerPrec(stackTokens, String.class , operand1.toString()));
          }
          result = calculateExpression(operation.methodName, operand2, operand1);
          stackTokens.push(result.toString());
		}
        }
		return result;
    }
	
	private <T> T  HigerPrec(Stack<T> stack, Class<T> type, Object value ) {
		T precOp = type.cast(value);
		T newOp = stack.pop();
		stack.push(precOp);
		precOp = null;
		return newOp;		
	}
	
	private Integer tokenValue(String token) {
		if (variables.containsKey(token)) 
			return variables.get(token);
		else
			return Integer.parseInt(token);
	}
	
	private Integer calculateExpression(String methodName, Integer operand1, Integer operand2) {
		Class<ExpressionEval> expressionParserClass = ExpressionEval.class;
        try {
        	Method methodCall = expressionParserClass.getDeclaredMethod(methodName, Integer.class, Integer.class);
            return (Integer) methodCall.invoke(null, operand1, operand2);
         } catch (Exception e) {
            e.printStackTrace();
         }
		return 0;
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
	
	private boolean isHigerPrec(Operator op, Operator sub) {
		return (sub.precedence > op.precedence);
	}

}
