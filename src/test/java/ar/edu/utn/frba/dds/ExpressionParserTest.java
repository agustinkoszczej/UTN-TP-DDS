package ar.edu.utn.frba.dds;

import org.junit.Assert;
import org.junit.Test;

import ar.edu.utn.frba.dds.expresion.Expresion;
import ar.edu.utn.frba.dds.util.ExpressionParser;

public class ExpressionParserTest {
	ExpressionParser parser;
	@Test
	public void numerosEnterosPositivos() {
		parser = new ExpressionParser();
		Expresion resultado = parser.buildExpressionFrom("5");
		Integer resultadoCalculo = resultado.calculate(null, null);
		Integer resultadoEsperado = 5;
		Assert.assertEquals(resultadoCalculo,resultadoEsperado);
	}

	@Test
	public void numerosEnterosNegativos() {
		parser = new ExpressionParser();
		Expresion resultado = parser.buildExpressionFrom("-5");
		Integer resultadoCalculo = resultado.calculate(null, null);
		Integer resultadoEsperado = -5;
		Assert.assertEquals(resultadoCalculo,resultadoEsperado);
	}

	@Test
	public void sumaNumerosEnterosPositivos() {
		parser = new ExpressionParser();
		Expresion resultado = parser.buildExpressionFrom("5+4");
		Integer resultadoCalculo = resultado.calculate(null, null);
		Integer resultadoEsperado = 9;
		Assert.assertEquals(resultadoCalculo,resultadoEsperado);
	}

	@Test
	public void sumaNumerosEnterosNegativos() {
		parser = new ExpressionParser();
		Expresion resultado = parser.buildExpressionFrom("-5+7");
		Integer resultadoCalculo = resultado.calculate(null, null);
		Integer resultadoEsperado = 2;
		Assert.assertEquals(resultadoCalculo,resultadoEsperado);
	}


}
