package main.java.ar.edu.utn.frba.dds.util;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ExpressionParser {
	
	public ExpressionParser(String expression) {
		this.expression = expression;
		this.evaluate();
	}
	
	private String expression;
	private Stack<String> stackTokens = new Stack<String>();
	private Stack<Operator> stackOperators = new Stack<Operator>();
	private Map<String, Integer> variables = new HashMap<String, Integer>();
	
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
	
	public Stack<String> getStackTokens() {
		return stackTokens;
	}

	public Stack<Operator> getStackOperators() {
		return stackOperators;
	}
	
	public String getExpression() {
		return expression;
	}
	
	private Boolean evaluate() {
		Integer expressionLength = this.expression.replaceAll("\\s+", "").length();

		// TODO: verificar sintaxis no validas como ++ o 25PT [25 PT]
		Matcher evaluationMatcher = ExpressionParser.evaluationPattern.matcher(this.expression);
		while (evaluationMatcher.find()) {
			expressionLength -= evaluationMatcher.group().length();
			if (ExpressionParser.ops.containsKey(evaluationMatcher.group())) {
				stackOperators.push(ExpressionParser.ops.get(evaluationMatcher.group()));
			} else {
				stackTokens.push(evaluationMatcher.group());
				try {
					isValidTokenVariable(evaluationMatcher.group());
				} catch (Exception e) {
					return false;
					//e.printStackTrace();
				}
			}
		}
		return expressionLength.equals(0);
	}
	
	private void isValidTokenVariable(String token) throws Exception {
		Matcher variableToken = ExpressionParser.evaluationPatternVariable.matcher(token);
		if (variableToken.matches()) {
			variables.put(token, 0);
		}
	}
	
	public Integer calculate() {
        Operator operation;
        Integer operand1;
        Integer operand2;
        Integer result = 0;

        while (!stackOperators.empty()) {
          operation = stackOperators.pop();
          operand1 = tokenValue(stackTokens.pop());
          operand2 = tokenValue(stackTokens.pop());
          if (!stackOperators.empty() && isHigerPrec(operation, stackOperators.peek())) {
        	operation = HigerPrec(stackOperators, Operator.class, operation);
        	operand1 = tokenValue(HigerPrec(stackTokens, String.class , operand1.toString()));
          }
          result = calculateExpression(operation.methodName, operand1, operand2);
          stackTokens.push(result.toString());
		}
		return result;
    }
	
	private <T extends Object> T  HigerPrec(Stack<T> stack, Class<T> type, Object value ) {
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
		Class<ExpressionParser> expressionParserClass = ExpressionParser.class;
        try {
        	Method methodCall = expressionParserClass.getDeclaredMethod(methodName, Integer.class, Integer.class);
            return (Integer) methodCall.invoke(null, operand1, operand2);
         } catch (Exception e) {
            e.printStackTrace();
         }
		return 0;
	}

	private static String pattern = "\\+|\\-|\\*|\\/|[0-9]+(\\.[0-9]*)?|[a-zA-Z]+[a-zA-Z0-9]*";
	private static Pattern evaluationPattern = Pattern.compile(pattern);

	private static String patternVariable = "[a-zA-Z]+[a-zA-Z0-9]*";
	private static Pattern evaluationPatternVariable = Pattern.compile(patternVariable);

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
	
	private static enum Operator {
		ADD(1, "sum"), SUBSTRACT(1, "minus"), MULTIPLY(2, "mult"), DIVIDE(2, "div");
		final Integer precedence;
		final String methodName;

		Operator(Integer p, String m) {
			precedence = p;
			methodName = m;
		}
	}

	private static Map<String, Operator> ops = new HashMap<String, Operator>() {
		{
			put("+", Operator.ADD);
			put("-", Operator.SUBSTRACT);
			put("*", Operator.MULTIPLY);
			put("/", Operator.DIVIDE);
		}
	};

	private static boolean isHigerPrec(Operator op, Operator sub) {
		return (sub.precedence > op.precedence);
	}

}
