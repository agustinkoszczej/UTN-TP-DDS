package ar.edu.utn.frba.dds;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import ar.edu.utn.frba.dds.modelo.Cuenta;
import ar.edu.utn.frba.dds.modelo.Empresa;
import ar.edu.utn.frba.dds.modelo.Indicador;
import ar.edu.utn.frba.dds.servicio.ServicioCuentas;
import ar.edu.utn.frba.dds.servicio.ServicioIndicadores;
import ar.edu.utn.frba.dds.util.ExpressionEval;
import ar.edu.utn.frba.dds.util.ExpressionParser;

public class IndicadoresTest {
	private Indicador indicadorW, indicadorX, indicadorY, indicadorZ, indicadorInexistente, indicadorComplejo;
	private ServicioCuentas servicio_cuentas;
	private ServicioIndicadores servicio_indicadores;
	private List<Empresa> empresas;
	private Empresa facebook;
	private Double cuenta_EBITDA = (double) 140000000;

	@Before
	public void init() {

		servicio_cuentas = new ServicioCuentas();
		empresas = new ArrayList<Empresa>();
		
		servicio_indicadores = new ServicioIndicadores();

		empresas = servicio_cuentas.obtenerEmpresas();
		
		facebook = empresas.get(0);
		
	}

	@Test
	public void probandoParserConNumeroDeUnDigitoSolamente() {
		ExpressionEval expression = new ExpressionEval("1"); //
		Assert.assertEquals((Integer) 1, expression.calculate());
	}

	@Test
	public void probandoParserConVariosDigitos() {
		ExpressionEval expression = new ExpressionEval("1500");
		Assert.assertEquals((Integer) 1500, expression.calculate());
	}

	@Test
	public void probandoParserConNumerosNegativosDevuelve0() {
		ExpressionEval expression = new ExpressionEval("-1500");
		Assert.assertEquals((Integer) 0, expression.calculate());
	}

	@Test
	public void probandoParserConOperadoresBasicos() {
		// Suma
		ExpressionEval expression = new ExpressionEval("15 + 3");
		Assert.assertEquals((Integer) 18, expression.calculate());
		// Resta
		expression = new ExpressionEval("15 - 3");
		Assert.assertEquals((Integer) 12, expression.calculate());
		// Multiplicación
		expression = new ExpressionEval("15 * 3");
		Assert.assertEquals((Integer) 45, expression.calculate());
		// División
		expression = new ExpressionEval("15 / 3");
		Assert.assertEquals((Integer) 5, expression.calculate());
	}

	@Test
	public void facebookConIndicadorW() throws Exception {
		
		indicadorW = new Indicador("IndicadorW", "15");
		indicadorW.validarVariables();
		new ServicioIndicadores().guardarIndicador(indicadorW);
		
		Double resultado = (double) 15;
		Assert.assertEquals(resultado, indicadorW.calcular(facebook, "201706"));
	}

	@Test
	public void facebookConIndicadorX() throws Exception {

		indicadorX = new Indicador("IndicadorX", "EBITDA");
		indicadorX.validarVariables();
		new ServicioIndicadores().guardarIndicador(indicadorX);
		
		Double resultado = (double) cuenta_EBITDA;
		Assert.assertEquals(resultado, indicadorX.calcular(facebook, "201706"));
	}

	@Test
	public void facebookConIndicadorY() throws Exception {

		indicadorY = new Indicador("IndicadorY", "EBITDA + 5");
		indicadorY.validarVariables();
		new ServicioIndicadores().guardarIndicador(indicadorY);
		Double resultado = (double) (cuenta_EBITDA + 5);
		Assert.assertEquals(resultado, indicadorY.calcular(facebook, "201706"));
	}
	
	@Test
	public void facebookConIndicadorZ() throws Exception {
		indicadorZ = new Indicador("IndicadorZ", "IndicadorW + 5 + EBITDA");
		indicadorZ.validarVariables();
		new ServicioIndicadores().guardarIndicador(indicadorZ);
		Double resultado = (double) (15 + 5 + cuenta_EBITDA);
		Assert.assertEquals(resultado, indicadorZ.calcular(facebook, "201706"));
	}
	
	@Test
	public void facebookConIndicadorComplejo() throws Exception {
		indicadorW = new Indicador("IndicadorW", "15");
		indicadorW.validarVariables();
		new ServicioIndicadores().guardarIndicador(indicadorW);
		indicadorZ = new Indicador("IndicadorZ", "IndicadorW + 5 + EBITDA");
		indicadorZ.validarVariables();
		new ServicioIndicadores().guardarIndicador(indicadorZ);
		indicadorComplejo = new Indicador("IndicadorBastanteComplejo", "IndicadorZ - 10");
		indicadorComplejo.validarVariables();
		new ServicioIndicadores().guardarIndicador(indicadorComplejo);
		Double resultado = (double) ((15 + 5 + cuenta_EBITDA) - 10);
		Assert.assertEquals(resultado, indicadorZ.calcular(facebook, "201706"));
	}

	@Test(expected = Exception.class)
	public void facebookConIndicadorInexistenteDeberiaFallar() throws Exception {

		indicadorInexistente = new Indicador("IndicadorInexistente", "5 + Error");
		indicadorInexistente.validarVariables();
		indicadorInexistente.calcular(facebook, "201706");
	}
	
	/*@Test
	public void precedenciaParser(){

		indicadorInexistente = new Indicador("IndicadorInexistente", "5 + Error");
		
		indicadorInexistente.calcular(facebook, "201706");
	}*/

}
