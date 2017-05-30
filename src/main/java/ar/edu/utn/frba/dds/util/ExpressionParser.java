package main.java.ar.edu.utn.frba.dds.util;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import main.java.ar.edu.utn.frba.dds.modelo.Cuenta;
import main.java.ar.edu.utn.frba.dds.modelo.Indicador;
import main.java.ar.edu.utn.frba.dds.modelo.RepositorioIndicadores;

public class ExpressionParser {

	public ExpressionParser(String expression) {
		this.expression = expression;
		this.evaluate();
	}
	
	private String expression;
	private Stack<String> stackTokens = new Stack<String>();
	private Stack<Operator> stackOperators = new Stack<Operator>();
	private Collection<Cuenta> cuentaAssociated= new ArrayList<Cuenta>();
	private Collection<Indicador> indicadorAssociated = new ArrayList<Indicador>();
	
	public Collection<Cuenta> getCuentaAssociated() {
		return cuentaAssociated;
	}

	public Collection<Indicador> getIndicadorAssociated() {
		return indicadorAssociated;
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
			if (Cuenta.valueOf(token) != null) {
				cuentaAssociated.add(Cuenta.valueOf(token));
			} else if (RepositorioIndicadores.existeIndicador(token) != null) {
				indicadorAssociated.add(RepositorioIndicadores.existeIndicador(token));
			} else {
				throw new Exception("No existe el indicador/cuenta especificado");
			}
		}
	}
	
	public Integer calculate() {
        Operator operation;
        Integer operand1;
        Integer operand2;
        Integer result = 0;

        while (!stackOperators.empty()) {
          operation = stackOperators.pop();
          
          // TODO: Hacer una funcion aparte para parsear los operadores, donde le paso el string y me devuelve un int
          //       Esa funcion es la que va a verificar que exista la cuenta o el indicador, y lo devuelve como entero
          //       o en caso de ser entero lo devuelve como entero
          
          operand1 = Integer.parseInt(stackTokens.pop());
          operand2 = Integer.parseInt(stackTokens.pop());
          if (!stackOperators.empty() && isHigerPrec(operation, stackOperators.peek())) {
            Operator tmpOperation = operation;
            Integer tmpOperand = operand1;
            operand1 = Integer.parseInt(stackTokens.pop());
            operation = stackOperators.pop();
            stackTokens.push(tmpOperand.toString());
            stackOperators.push(tmpOperation);
          }
          result = calculateExpression(operation.methodName, operand1, operand2);
          stackTokens.push(result.toString());
		}
		return result;
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
