package ar.edu.utn.frba.dds;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import ar.edu.utn.frba.dds.modelo.Cuenta;
import ar.edu.utn.frba.dds.modelo.Indicador;
import ar.edu.utn.frba.dds.modelo.RepositorioIndicadores;
import ar.edu.utn.frba.dds.servicio.ServicioIndicadores;
import ar.edu.utn.frba.dds.util.ExpressionParser;
import ar.edu.utn.frba.dds.util.ExpressionEval;

public class IndicadoresTest {
	private Indicador indicadorW, indicadorX, indicadorY, indicadorZ;
	private Cuenta cuentaX;

	  @Before
	  public void init() {
		indicadorW = new Indicador("IndicadorW", "15");  
		indicadorX = new Indicador("IndicadorX", "cuentaX");
		indicadorY = new Indicador("IndicadorY", "cuentaX + 5");
		indicadorZ = new Indicador("IndicadorZ", "IndicadorW + 5 + cuentaX");
		//cuentaX = 20
	  }

	@Test
	public void indicadorConValorSolamente(){
		ExpressionEval expresionW = new ExpressionEval(indicadorW.getExpresion());
		Assert.assertEquals((Integer)15, expresionW.calculate());
	}
	@Test
	public void indicadorConCuentaSolamente(){
		ExpressionEval expresionX = new ExpressionEval(indicadorX.getExpresion());
		/*System.out.println(expresionX.getExpression());
		System.out.println(expresionX.getStackTokens());
		System.out.println(expresionX.getStackOperators());
		System.out.println(expresionX.getVaVariableNames());
		System.out.println(expresionX.calculate());*/
		//Assert.assertEquals((Integer)20, expresionX.calculate());
	}
	@Test
	public void indicadorConCuentaYValor(){
		ExpressionEval expresionY = new ExpressionEval(indicadorY.getExpresion());
		//Assert.assertEquals((Integer)25, expresionY.calculate());
	}
	@Test
	public void indicadorConIndicadorValorYCuenta(){
		ExpressionEval expresionZ = new ExpressionEval(indicadorZ.getExpresion());
		//Assert.assertEquals((Integer)40, expresionZ.calculate());
	}
	
	@Test
	public void guardarIndicadorEnJson(){
		
		//Guardamos el indicador
		try {
			new ServicioIndicadores().guardarIndicador(indicadorY);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Lo levantamos del archivo
		Indicador levantado = RepositorioIndicadores.existeIndicador(indicadorY.nombreIndicador);
		
		Assert.assertEquals(indicadorY, levantado);
	}
}
