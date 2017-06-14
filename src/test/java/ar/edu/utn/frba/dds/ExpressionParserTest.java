package ar.edu.utn.frba.dds;

import org.junit.Assert;
import org.junit.Test;

import ar.edu.utn.frba.dds.expresion.Expresion;
import ar.edu.utn.frba.dds.util.ExpressionParser;
import ar.edu.utn.frba.dds.util.exceptions.InvalidTokenException;
import ar.edu.utn.frba.dds.util.exceptions.SyntaxErrorException;
import ar.edu.utn.frba.dds.util.exceptions.TypeExpresionException;

public class ExpressionParserTest {
	ExpressionParser parser;
	@Test
	public void numerosEnterosPositivos() throws InvalidTokenException, SyntaxErrorException, TypeExpresionException {
		parser = new ExpressionParser();
		Expresion resultado = parser.buildExpressionFrom("5");
		Integer resultadoCalculo = resultado.calculate(null, null);
		Integer resultadoEsperado = 5;
		Assert.assertEquals(resultadoCalculo,resultadoEsperado);
	}

	@Test
	public void numerosEnterosNegativos() throws InvalidTokenException, SyntaxErrorException, TypeExpresionException {
		parser = new ExpressionParser();
		Expresion resultado = parser.buildExpressionFrom("-5");
		Integer resultadoCalculo = resultado.calculate(null, null);
		Integer resultadoEsperado = -5;
		Assert.assertEquals(resultadoCalculo,resultadoEsperado);
	}

	@Test
	public void sumaNumerosEnterosPositivos() throws InvalidTokenException, SyntaxErrorException, TypeExpresionException {
		parser = new ExpressionParser();
		Expresion resultado = parser.buildExpressionFrom("5+4");
		Integer resultadoCalculo = resultado.calculate(null, null);
		Integer resultadoEsperado = 9;
		Assert.assertEquals(resultadoCalculo,resultadoEsperado);
	}

	@Test
	public void sumaNumerosEnterosNegativos() throws InvalidTokenException, SyntaxErrorException, TypeExpresionException {
		parser = new ExpressionParser();
		Expresion resultado = parser.buildExpressionFrom("-5+7");
		Integer resultadoCalculo = resultado.calculate(null, null);
		Integer resultadoEsperado = 2;
		Assert.assertEquals(resultadoCalculo,resultadoEsperado);
	}


}
