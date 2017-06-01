package ar.edu.utn.frba.dds.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ExpressionParser {
	
	private static String patternParser = "\\+|\\-|\\*|\\/|[0-9]+(\\.[0-9]*)?|[a-zA-Z]+[a-zA-Z0-9]*";
	private static Pattern evaluationPattern = Pattern.compile(patternParser);

	private static String patternVariable = "^[a-zA-Z]+[a-zA-Z0-9]*$";
	
	private static Map<String, Operator> ops = new HashMap<String, Operator>() {
		{
			put("+", Operator.ADD);
			put("-", Operator.SUBSTRACT);
			put("*", Operator.MULTIPLY);
			put("/", Operator.DIVIDE);
		}
	};

	private Stack<String> stackTokens = new Stack<String>();
	private Stack<Operator> stackOperators = new Stack<Operator>();
	private List<String> variables = new ArrayList<String>();
	
	public Stack<String> getStackTokens() {
		return stackTokens;
	}

	public Stack<Operator> getStackOperators() {
		return stackOperators;
	}

	public List<String> getVariables() {
		return variables;
	}

	public Boolean parse(String expression) {
		Integer expressionLength = expression.replaceAll("\\s+", "").length();
		String previousToken = "";
		String unaryOperator = "";
		
		// TODO: verificar sintaxis no validas como ++ o 25PT [25 PT]

		// TODO: Ver porque no funciona el forEach, no esta haciendo el analisis de la expresion regular
		//ExpressionParser.evaluationPattern.splitAsStream(expression).forEach(token -> stackExpression(token));
		//	expressionLength -= ExpressionParser.evaluationPattern.splitAsStream(expression).mapToInt(token -> token.length()).sum();
		//}
		Matcher expressionMatch = ExpressionParser.evaluationPattern.matcher(expression);
		while (expressionMatch.find()) {
			try {
				stackExpression(expressionMatch.group(), previousToken, unaryOperator);
			} catch (Exception e) {
				e.printStackTrace();
			}
			previousToken = expressionMatch.group();
		}
		System.out.println(stackTokens);
		System.out.println(variables);
		return expressionLength.equals(0);
	}
	
	private void stackExpression(String token, String previousToken, String unaryOperator) throws Exception {
		if (unaryOperator.length() != 0) {
			if (isOperator(token)) {
				throw new Exception("Expresion sintactica no valida");
			} else {
				stackTokens.push(unaryOperator.concat(token));
				unaryOperator = "";
				if (isVariable(token))
					throw new Exception("Expresion sintactica no valida");
			}
		} else {
			if (isOperator(token)) {
				stackOperators.push(getOperator(token));
			} else {
				stackTokens.push(token);
				if (isVariable(token))
					variables.add(token);
			}
		}
		if (previousToken.length() > 0) {
			if (isOperator(previousToken) && (token.compareTo("-") == 0)) 
				unaryOperator = token;
		} else if (token.compareTo("-") == 0) {
			unaryOperator = token;
		}
	}
	
	private Boolean isOperator(String token) {
		return ExpressionParser.ops.containsKey(token);
	}
	
	private Operator getOperator(String token) {
		return ExpressionParser.ops.get(token);
	}
	
	private Boolean isVariable(String token) {
		return token.matches(ExpressionParser.patternVariable);
	}

}
