package ar.edu.utn.frba.dds.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import ar.edu.utn.frba.dds.ast.AST;
import ar.edu.utn.frba.dds.ast.Constante;
import ar.edu.utn.frba.dds.ast.Operacion;
import ar.edu.utn.frba.dds.modelo.Indicador;

public class ExpressionParser {

	private static String patternParser = "\\+|\\-|\\*|\\/|[0-9]+(\\.[0-9]*)?|[a-zA-Z]+[a-zA-Z0-9]*";
	private static Pattern evaluationPattern = Pattern.compile(patternParser);

	private static String patternVariable = "^[a-zA-Z]+[a-zA-Z0-9]*$";

	private static Map<String, Operator> ops=new HashMap<String,Operator>(){{put("+",Operator.ADD);put("-",Operator.SUBSTRACT);put("*",Operator.MULTIPLY);put("/",Operator.DIVIDE);}};

	private AST ASTgenerado;
	private Operacion lastOperator;
	private AST lastToken;

	public AST ASTresult(String expression) {
		// Integer expressionLength = expression.replaceAll("\\s+",
		// "").length();

		// TODO: verificar sintaxis no validas como ++ o 25PT [25 PT]
		// TODO: verificar que todos los tokens son validos

		Matcher expressionMatch = ExpressionParser.evaluationPattern.matcher(expression);
		while (expressionMatch.find()) {
			parseAST(expressionMatch.group());
		}
		return ASTgenerado;
	}

	private void parseAST(String token) {
		if (isOperator(token)) {
			addOperator(token);
		} else if (token.length() > 0) {
			addConstant(token);
		} else {
			// TODO: error!
		}
	}

	// TODO: Ver casos con multiples operadores
	private void addOperator(String operator) {
		if (lastOperator == null) {
			lastOperator = new Operacion(getOperator(operator));
		}
		if ( (lastOperator != null) && (lastToken != null) ) {
			if ((ASTgenerado != null) && (ASTgenerado.getClass() == Operacion.class)) {
				Operacion newOperator = new Operacion(getOperator(operator));
				if (lastOperator.getOperacion().precedence > getOperator(operator).precedence) { // de multiplicacion sigue suma
					newOperator.agregarOperando(lastOperator);
					lastOperator.setOperacionPrecedente(newOperator);
					lastOperator = newOperator;
					ASTgenerado = lastOperator; // TODO: ver si se puede poner en otro lado para no repetir asignacion
				} else if (lastOperator.getOperacion().precedence == getOperator(operator).precedence) {
					lastOperator.setOperacionPrecedente(newOperator);
					newOperator.agregarOperando(lastOperator);
					lastOperator = newOperator;
					ASTgenerado = lastOperator; // TODO: ver si se puede poner en otro lado para no repetir asignacion
				} else { // de suma a multiplicacion
					newOperator.agregarOperando(lastOperator.getOperando(2));
					newOperator.setOperacionPrecedente(lastOperator);
					lastOperator.setOperando(2,newOperator);
					lastOperator = newOperator;
				}
			} else if ( (ASTgenerado == null) || (ASTgenerado.getClass() != Operacion.class) )  {
					lastOperator.agregarOperando(lastToken);
					ASTgenerado = lastOperator;
			} else {
				// TODO: Excepcion no contralada
			}
		} else {
			//TODO: explotar
		}
	}

	private void addConstant(String constant) {
		if (lastToken == null) {
			lastToken = addVariable(constant);
		}
		if (ASTgenerado == null) {
			ASTgenerado = lastToken;
		} else {
			if (ASTgenerado.getClass() == Operacion.class) {
				lastToken = addVariable(constant);
				lastOperator.agregarOperando(lastToken);
			} else if (ASTgenerado.getClass() == Constante.class) {
				// TODO: explotar por doble contaste
			}
		}
	}
	
	private AST addVariable(String constant) {
		if (!isVariable(constant)) {
			return new Constante(constant);
		} else {
			return new Indicador(); // TODO: validar que sea una cuenta o un indicador que ya existe
			// TODO: ver si se implementa un ConstanteIndicador y ConstanteCuenta con tipo AST y que o bien hereden o llamen a Indicador y Cuentas
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

	// De aca para abajo se depreca todo!

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

		// TODO: verificar sintaxis no validas como ++ o 25PT [25 PT]

		// TODO: Ver porque no funciona el forEach, no esta haciendo el analisis
		// de la expresion regular
		// ExpressionParser.evaluationPattern.splitAsStream(expression).forEach(token
		// -> stackExpression(token));
		// expressionLength -=
		// ExpressionParser.evaluationPattern.splitAsStream(expression).mapToInt(token
		// -> token.length()).sum();
		// }
		System.out.println(expression);

		Matcher expressionMatch = ExpressionParser.evaluationPattern.matcher(expression);
		while (expressionMatch.find()) {
			stackExpression(expressionMatch.group());
		}

		return expressionLength.equals(0);
	}

	private void stackExpression(String token) {
		if (isOperator(token)) {
			stackOperators.push(getOperator(token));
		} else if (token.length() > 0) {
			stackTokens.push(token);
			if (isVariable(token))
				variables.add(token);
		}
	}

}
