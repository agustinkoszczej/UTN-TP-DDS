package ar.edu.utn.frba.dds;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import ar.edu.utn.frba.dds.expresion.ExpresionCuenta;
import ar.edu.utn.frba.dds.metodologia.Comparador;
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
		Indicador indicador = new Indicador("indicador", new ExpresionCuenta(TipoDeCuenta.EBITDA));
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
}
