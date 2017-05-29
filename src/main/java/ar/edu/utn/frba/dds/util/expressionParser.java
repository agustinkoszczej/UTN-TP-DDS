package main.java.ar.edu.utn.frba.dds.util;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class expressionParser {

	@SuppressWarnings("unused")
	private static Integer sum(Integer a, Integer b) {
		//System.out.print("FUNCION SUMA: ");
		//Integer total = a + b;
		//System.out.println(total.toString());
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
	
	private enum Operator {
		ADD(1, "sum"), SUBTRACT(1, "minus"), MULTIPLY(2, "mult"), DIVIDE(2, "div");
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
			put("-", Operator.SUBTRACT);
			put("*", Operator.MULTIPLY);
			put("/", Operator.DIVIDE);
		}
	};

	private static boolean isHigerPrec(String op, String sub) {
		return (ops.containsKey(sub) && ops.get(sub).precedence >= ops.get(op).precedence);
	}

	private static String pattern = "\\+|\\-|\\*|\\/|\\(|\\)|[0-9]+(\\.[0-9]*)?|[a-zA-Z]+[a-zA-Z0-9]*";
	private static Pattern evaluationPattern = Pattern.compile(pattern);

	public static Boolean isValid(String expression) {
		Integer expressionLength = expression.replaceAll("\\s+", "").length();

		Matcher evaluationMatcher = evaluationPattern.matcher(expression);
		while (evaluationMatcher.find()) {
			expressionLength -= evaluationMatcher.group().length();
		}
		return expressionLength == 0;
	}

	public static void evaluate(String expression) {
		Class<expressionParser> expressionParserClass = expressionParser.class;
		Method methodCall;
		Stack<String> stackTokens = new Stack<String>();
		Stack<Operator> stackOperators = new Stack<Operator>();

		Matcher evaluationMatcher = evaluationPattern.matcher(expression);
		while (evaluationMatcher.find()) {
			System.out.println("Valor: \"" + evaluationMatcher.group() + "\"");
			if (ops.containsKey(evaluationMatcher.group()))
				stackOperators.push(ops.get(evaluationMatcher.group()));
			else
				stackTokens.push(evaluationMatcher.group());
		}

		while (!stackOperators.empty()) {
			Operator operation = stackOperators.pop();
			Integer operand1 = Integer.parseInt(stackTokens.pop());
			Integer operand2 = Integer.parseInt(stackTokens.pop());
			Integer result = 0;
			try {
				methodCall = expressionParserClass.getDeclaredMethod(operation.methodName, Integer.class,
						Integer.class);
				result = (Integer) methodCall.invoke(null, operand1, operand2);
				stackTokens.push(result.toString());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

}
