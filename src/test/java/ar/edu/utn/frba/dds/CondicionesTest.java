package ar.edu.utn.frba.dds;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import ar.edu.utn.frba.dds.expresion.ExpresionCuenta;
import ar.edu.utn.frba.dds.metodologia.Comparador;
import ar.edu.utn.frba.dds.metodologia.ComparadorAntiguedad;
import ar.edu.utn.frba.dds.metodologia.ComparadorDesempenio;
import ar.edu.utn.frba.dds.metodologia.CondicionAntiguedad;
import ar.edu.utn.frba.dds.metodologia.CondicionConsistenciaTiempo;
import ar.edu.utn.frba.dds.metodologia.CondicionGeneral;
import ar.edu.utn.frba.dds.metodologia.CondicionSuperaValor;
import ar.edu.utn.frba.dds.metodologia.TipoOperacion;
import ar.edu.utn.frba.dds.modelo.Balance;
import ar.edu.utn.frba.dds.modelo.Empresa;
import ar.edu.utn.frba.dds.modelo.Indicador;
import ar.edu.utn.frba.dds.modelo.TipoDeCuenta;

public class CondicionesTest {

	Empresa empresa;
	Balance balance;
	List<Balance> listaBalances;
	Indicador indicador;
	@Before
	public void init(){

		empresa = new Empresa();
		balance = new Balance();
		balance.setPeriodo("20170100");
		balance.setTipoCuenta(TipoDeCuenta.EBITDA);
		balance.setValor(new Double(25000));
		listaBalances = new ArrayList<Balance>();
		listaBalances.add(balance);
		empresa.setBalances(listaBalances);

		indicador = new Indicador("indicador", new ExpresionCuenta(TipoDeCuenta.EBITDA));
	}
	
	@Test
	public void unaEmpresaEsInvertibleConValorSuperior(){
		
		CondicionSuperaValor condicionValor = new CondicionSuperaValor();
		condicionValor.setInicioPeriodo("20170100");
		condicionValor.setFinPeriodo("20170100");
		condicionValor.setComparador(Comparador.MAYOR);
		condicionValor.setValorSuperar(20000);
		Indicador indicador = new Indicador("indicador", new ExpresionCuenta(TipoDeCuenta.EBITDA));
		condicionValor.setIndicador(indicador);
		
		Assert.assertEquals(true, condicionValor.deberiaInvertirEn(empresa));
	}
	
	@Test
	public void unaEmpresaEsInvertibleConValorInferior(){
		
		CondicionSuperaValor condicionValor = new CondicionSuperaValor();
		condicionValor.setInicioPeriodo("20170100");
		condicionValor.setFinPeriodo("20170100");
		condicionValor.setComparador(Comparador.MENOR);
		condicionValor.setValorSuperar(30000);
		condicionValor.setIndicador(indicador);
		
		Assert.assertEquals(true, condicionValor.deberiaInvertirEn(empresa));
	}

	//TODO: ver porque falla
	@Test(expected = Exception.class)
	public void noEsPosibleDefinirCondicionValorConComparadorIgual() {
		
		CondicionSuperaValor condicionValor = new CondicionSuperaValor();
		condicionValor.setInicioPeriodo("20170100");
		condicionValor.setFinPeriodo("20170100");
		condicionValor.setComparador(Comparador.IGUAL);
		condicionValor.setValorSuperar(30000);
		condicionValor.setIndicador(indicador);
		condicionValor.deberiaInvertirEn(empresa);
		
	}
	
	@Test
	public void convieneEmpresaConIndicadorMasAlto() {
		Empresa empresa2 = new Empresa();
		Balance balance2 = new Balance();
		balance2.setPeriodo("20170100");
		balance2.setTipoCuenta(TipoDeCuenta.EBITDA);
		balance2.setValor(new Double(20000));
		List<Balance> listaBalances = new ArrayList<Balance>();
		listaBalances.add(balance2);
		empresa2.setBalances(listaBalances);
		
		ComparadorDesempenio comparadorDesempenio = new ComparadorDesempenio();
		comparadorDesempenio.setComparador(Comparador.MAYOR);
		comparadorDesempenio.setIndicador(indicador);
		comparadorDesempenio.setPeriodoComparacion("20170100");
		Assert.assertEquals(empresa, comparadorDesempenio.cualEmpresaInvertir(empresa, empresa2));
	}
	
	@Test
	public void convieneEmpresaConIndicadorMasBajo() {
		Empresa empresa2 = new Empresa();
		Balance balance2 = new Balance();
		balance2.setPeriodo("20170100");
		balance2.setTipoCuenta(TipoDeCuenta.EBITDA);
		balance2.setValor(new Double(20000));
		List<Balance> listaBalances = new ArrayList<Balance>();
		listaBalances.add(balance2);
		empresa2.setBalances(listaBalances);
		
		ComparadorDesempenio comparadorDesempenio = new ComparadorDesempenio();
		comparadorDesempenio.setComparador(Comparador.MENOR);
		comparadorDesempenio.setIndicador(indicador);
		comparadorDesempenio.setPeriodoComparacion("20170100");
		Assert.assertEquals(empresa2, comparadorDesempenio.cualEmpresaInvertir(empresa, empresa2));
	}
	
	@Test(expected = Exception.class)
	public void noDefineQueEmpresaConviene() {
		Empresa empresa2 = new Empresa();
		Balance balance2 = new Balance();
		balance2.setPeriodo("20170100");
		balance2.setTipoCuenta(TipoDeCuenta.EBITDA);
		balance2.setValor(new Double(20000));
		List<Balance> listaBalances = new ArrayList<Balance>();
		listaBalances.add(balance2);
		empresa2.setBalances(listaBalances);
		
		
		ComparadorDesempenio comparadorDesempenio = new ComparadorDesempenio();
		comparadorDesempenio.setComparador(Comparador.IGUAL);
		comparadorDesempenio.setIndicador(indicador);
		comparadorDesempenio.setPeriodoComparacion("20170100");
		comparadorDesempenio.cualEmpresaInvertir(empresa, empresa2);
	}
	
	@Test
	public void esConsistentementeCrecienteEnPeriodo(){
		Balance balance2 = new Balance();
		balance2.setPeriodo("20170600");
		balance2.setTipoCuenta(TipoDeCuenta.EBITDA);
		balance2.setValor(new Double(26000));
		listaBalances.add(balance2);
		empresa.setBalances(listaBalances);
		
		CondicionConsistenciaTiempo condicionCreciente = new CondicionConsistenciaTiempo();
		condicionCreciente.setInicioPeriodo("20170100");
		condicionCreciente.setFinPeriodo("20170600");
		condicionCreciente.setComparador(Comparador.MAYOR);
		Indicador indicador = new Indicador("indicador", new ExpresionCuenta(TipoDeCuenta.EBITDA));
		condicionCreciente.setIndicador(indicador);
		Assert.assertTrue(condicionCreciente.deberiaInvertirEn(empresa));
	}
	
	@Test
	public void cumpleLongevidadTaxativa(){
		CondicionAntiguedad condicionAntiguedad = new CondicionAntiguedad();
		balance.setPeriodo("200706");
		listaBalances.add(balance);
		empresa.setBalances(listaBalances);
		condicionAntiguedad.setAniosNecesarios(10);
		Assert.assertTrue(condicionAntiguedad.deberiaInvertirEn(empresa));
	}
	
	@Test
	public void cumpleLongetividadComparativa(){
		Empresa empresa2 = new Empresa();
		Balance balance2 = new Balance();
		balance2.setPeriodo("20130600");
		balance2.setTipoCuenta(TipoDeCuenta.EBITDA);
		balance2.setValor(new Double(26000));
		List<Balance> listaBalances2 = new ArrayList<Balance>();
		listaBalances2.add(balance2);
		empresa2.setBalances(listaBalances2);
		ComparadorAntiguedad comparadorAntiguedad = new ComparadorAntiguedad();
		comparadorAntiguedad.setComparador(Comparador.MAYOR);
		Assert.assertEquals(empresa2, comparadorAntiguedad.cualEmpresaInvertir(empresa, empresa2));
	}
	
	@Test
	public void cumpleCondicionSumatoria(){
		listaBalances.add(balance);
		empresa.setBalances(listaBalances);
		CondicionGeneral condicionGeneral = new CondicionGeneral();

		condicionGeneral.setComparador(Comparador.MAYOR);
		condicionGeneral.setValorASuperar(40000);
		condicionGeneral.setIndicador(indicador);
		condicionGeneral.setTipoOperacion(TipoOperacion.SUMATORIA);
		Assert.assertTrue(condicionGeneral.deberiaInvertirEn(empresa));
	}
	
	@Test
	public void cumpleCondicionPromedio(){
		Balance balance2 = new Balance();

		balance2.setPeriodo("20130600");
		balance2.setTipoCuenta(TipoDeCuenta.EBITDA);
		balance2.setValor((double)15000);
		listaBalances.add(balance2);
		empresa.setBalances(listaBalances);
		CondicionGeneral condicionGeneral = new CondicionGeneral();

		condicionGeneral.setComparador(Comparador.MAYOR);
		condicionGeneral.setValorASuperar(19999);
		condicionGeneral.setIndicador(indicador);
		condicionGeneral.setTipoOperacion(TipoOperacion.PROMEDIO);
		Assert.assertTrue(condicionGeneral.deberiaInvertirEn(empresa));
	}
	
	@Test
	public void cumpleCondicionMediana(){
		Balance balance2 = new Balance();
		balance2.setPeriodo("20130600");
		balance2.setTipoCuenta(TipoDeCuenta.EBITDA);
		balance2.setValor((double)15000);
		Balance balance3 = new Balance();
		balance3.setPeriodo("20150600");
		balance3.setTipoCuenta(TipoDeCuenta.EBITDA);
		balance3.setValor((double)20000);
		listaBalances.add(balance2);
		listaBalances.add(balance3);
		empresa.setBalances(listaBalances);
		CondicionGeneral condicionGeneral = new CondicionGeneral();

		condicionGeneral.setComparador(Comparador.MAYOR);
		condicionGeneral.setValorASuperar(19999); //deberia dar 20000 la mediana de los tres valores
		condicionGeneral.setIndicador(indicador);
		condicionGeneral.setTipoOperacion(TipoOperacion.MEDIANA);
		Assert.assertTrue(condicionGeneral.deberiaInvertirEn(empresa));
	}
	
}
