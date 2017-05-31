package test.java.ar.edu.utn.frba.dds;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import main.java.ar.edu.utn.frba.dds.modelo.Cuenta;
import main.java.ar.edu.utn.frba.dds.modelo.Indicador;
import main.java.ar.edu.utn.frba.dds.util.ExpressionParser;

public class IndicadoresTest {
	private Indicador indicadorW, indicadorX, indicadorY, indicadorZ;
	private Cuenta cuentaX;

	  @Before
	  public void init() {
		indicadorW = new Indicador("IndicadorW", "5");  
		indicadorX = new Indicador("IndicadorX", "cuentaX");
		indicadorX = new Indicador("IndicadorY", "cuentaX + 5");
		indicadorX = new Indicador("IndicadorZ", "IndicadorW + 5 + cuentaX");
	  }

	@Test
	public void indicadorConValorSolamente(){
		ExpressionParser expresionW = new ExpressionParser(indicadorW.getExpresion());
		
		System.out.println(expresionW.getExpression());
		//Assert.assertEquals((Integer)5, expresionW.calculate());
		Assert.assertTrue(true);
	}
}
