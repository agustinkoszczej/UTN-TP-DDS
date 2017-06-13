package ar.edu.utn.frba.dds.util;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import ar.edu.utn.frba.dds.expresion.Expresion;
import ar.edu.utn.frba.dds.expresion.ExpresionCompuesta;
import ar.edu.utn.frba.dds.expresion.ExpresionConstante;
import ar.edu.utn.frba.dds.expresion.ExpresionCuenta;
import ar.edu.utn.frba.dds.expresion.Operacion;
import ar.edu.utn.frba.dds.modelo.Indicador;
import ar.edu.utn.frba.dds.modelo.TipoDeCuenta;

public class ExpressionParser {

	private static String patternParser = "\\+|\\-|\\*|\\/|[0-9]+(\\.[0-9]*)?|[a-zA-Z]+[a-zA-Z0-9]*";
	private static Pattern evaluationPattern = Pattern.compile(patternParser);

	private static String patternVariable = "^[a-zA-Z]+[a-zA-Z0-9]*$";
	private static String patternNumber = "^[0-9]+(\\.[0-9]*)?$";

	private static Map<String, Operator> ops = new HashMap<String, Operator>() {
		{
			put("+", Operator.ADD);
			put("-", Operator.SUBSTRACT);
			put("*", Operator.MULTIPLY);
			put("/", Operator.DIVIDE);

	}};

	private Boolean isOperator(String token) {
		return ExpressionParser.ops.containsKey(token);
	}

	private Boolean isVariable(String token) {
		return token.matches(ExpressionParser.patternVariable);
	}

	private Boolean isNumber(String token) {
		return token.matches(ExpressionParser.patternNumber);
	}
	
	private Expresion parseExpresion(String token, String proxToken, Expresion resultExpresion) {
		if ((resultExpresion == null) && (proxToken == null)) {
			return new ExpresionConstante(Integer.parseInt(token));
		}
		if (resultExpresion.getClass() == ExpresionConstante.class) {
		}
		return null;
	}
	
	public Expresion buildExpressionFrom(String expresion) {
		Expresion resultExpression = null;
		Matcher expressionMatch = ExpressionParser.evaluationPattern.matcher(expresion);
		String token = "";
		String proxToken = "";
		
		if (expressionMatch.find()) {
			token = expressionMatch.group();
			if ((isOperator(token)) && (token.compareTo("-") == 0) && (expressionMatch.find())) {
				token += expressionMatch.group();
			} else if ((!isNumber(token)) || (!isVariable(token))) {
				// ERROR: Solo se paso un operador
			}
		} else {
			// ERROR: No se pudo parsear expresion
		}
		resultExpression = parseExpresion(token, null, resultExpression);
		
		// -5*-5 => token = -5;  proxToken = *
		while (expressionMatch.find()) {
			proxToken = expressionMatch.group();
			// TODO: Refactorizar por un metodo
			if ((proxToken.compareTo("-") == 0) && isOperator(token)) {
				if (expressionMatch.find()) {
					if (isNumber(expressionMatch.group()) || isVariable(expressionMatch.group())) { // FIXME: Que pasa con Cuentas o Indicadores negativos?? Rta: Habria que agregar un *(-1)
						proxToken += expressionMatch.group();
					} else {
						// ERROR se pasaron 3 operadores juntos
					}
				} else {
					// ERROR se paso un operador y no hay operando
				}
			}
			resultExpression = parseExpresion(token, proxToken, resultExpression);
			token=proxToken; 
		}

		
		return resultExpression;
	}

	private Operator tokenType(String token) {
		if (ops.get(token) != null) {
			return ops.get(token);
		} else {
			if (isNumber(token)) {
				return Operator.CONSTANT;
			} else if (isVariable(token)) {
				return Operator.VARIABLE;
			} else {
				// caracter no valido
				return null;
			}
		}
	}
	
	private Expresion buildExpresion(Expresion anterior, Expresion operando, Operacion operacion) {
		// segun precedencia armo expresion
		return null;
	}
	
	private Expresion getToken(Matcher expressionMatch) {
		expressionMatch.find();
		String token = expressionMatch.group();
		//parseExpression(token);
		return null;
		// devolver Expression
	}

}
