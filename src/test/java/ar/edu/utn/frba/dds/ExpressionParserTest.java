package ar.edu.utn.frba.dds;

import org.junit.Assert;
import org.junit.Test;

import ar.edu.utn.frba.dds.expresion.Expresion;
import ar.edu.utn.frba.dds.util.ExpressionParser;

public class ExpressionParserTest {
	ExpressionParser parser;
	@Test
	public void sumaNumerosEnterosPositivos() {
		parser = new ExpressionParser();
		Expresion resultado = parser.buildExpressionFrom("1+2");
		Integer resultadoCalculo = resultado.calculate(null, null);
		Integer resultadoEsperado = 3;
		Assert.assertEquals(resultadoCalculo,resultadoEsperado);
	}
}
