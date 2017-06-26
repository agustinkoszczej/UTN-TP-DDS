package ar.edu.utn.frba.dds;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import ar.edu.utn.frba.dds.expresion.ExpresionCuenta;
import ar.edu.utn.frba.dds.metodologia.Comparador;
import ar.edu.utn.frba.dds.metodologia.ComparadorDesempenio;
import ar.edu.utn.frba.dds.metodologia.CondicionConsistenciaTiempo;
import ar.edu.utn.frba.dds.metodologia.CondicionSuperaValor;
import ar.edu.utn.frba.dds.modelo.Balance;
import ar.edu.utn.frba.dds.modelo.Empresa;
import ar.edu.utn.frba.dds.modelo.Indicador;
import ar.edu.utn.frba.dds.modelo.TipoDeCuenta;
import scala.Int;

import static org.junit.Assert.*;

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
}
