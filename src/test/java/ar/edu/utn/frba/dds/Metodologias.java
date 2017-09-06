package ar.edu.utn.frba.dds;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import ar.edu.utn.frba.dds.metodologia.Condicion;
import ar.edu.utn.frba.dds.metodologia.CondicionCompararValor;
import ar.edu.utn.frba.dds.metodologia.Metodologia;
import ar.edu.utn.frba.dds.modelo.Balance;
import ar.edu.utn.frba.dds.modelo.Empresa;
import ar.edu.utn.frba.dds.modelo.Frecuencia;
import ar.edu.utn.frba.dds.modelo.Indicador;
import ar.edu.utn.frba.dds.modelo.TipoDeCuenta;
import ar.edu.utn.frba.dds.util.ExpressionParser;
import ar.edu.utn.frba.dds.util.exceptions.InvalidTokenException;
import ar.edu.utn.frba.dds.util.exceptions.SyntaxErrorException;
import ar.edu.utn.frba.dds.util.exceptions.TypeExpresionException;

public class Metodologias {

	List<Empresa> empresas;
	List<Indicador> indicadores;

	private void addValoresBalances(List<Balance> listaBalances, Double valorBase) {
		Balance unBalance;
		for (int i = 2005; i != 2017; i++) {
			unBalance = new Balance();
			unBalance.setTipoCuenta(TipoDeCuenta.IngresoNeto);
			unBalance.setFrecuencia(Frecuencia.Anual);
			unBalance.setPeriodo("01" + String.valueOf(i));
			unBalance.setValor(valorBase * (i-2004));
			listaBalances.add(unBalance);
			unBalance = new Balance();
			unBalance.setTipoCuenta(TipoDeCuenta.Dividendos);
			unBalance.setFrecuencia(Frecuencia.Anual);
			unBalance.setPeriodo("01" + String.valueOf(i));
			unBalance.setValor(valorBase * (i-2004));
			listaBalances.add(unBalance);
			unBalance = new Balance();
			unBalance.setTipoCuenta(TipoDeCuenta.CapitalTotal);
			unBalance.setFrecuencia(Frecuencia.Anual);
			unBalance.setPeriodo("01" + String.valueOf(i));
			unBalance.setValor(valorBase * (i-2004));
			listaBalances.add(unBalance);
			unBalance = new Balance();
			unBalance.setTipoCuenta(TipoDeCuenta.Deuda);
			unBalance.setFrecuencia(Frecuencia.Anual);
			unBalance.setPeriodo("01" + String.valueOf(i));
			unBalance.setValor(valorBase * (i-2004));
			listaBalances.add(unBalance);
			unBalance = new Balance();
			unBalance.setTipoCuenta(TipoDeCuenta.CostoTotal);
			unBalance.setFrecuencia(Frecuencia.Anual);
			unBalance.setPeriodo("01" + String.valueOf(i));
			unBalance.setValor(valorBase * (i-2004));
			listaBalances.add(unBalance);
		}
	}
	
	@Before
	public void init() {
		Empresa unaEmpresa = new Empresa();
		unaEmpresa.setNombre("Empresa A");
		List<Balance> unaListaDeBalances = new ArrayList<Balance>(); 
		addValoresBalances(unaListaDeBalances, 10000d);
		unaEmpresa.setBalances(unaListaDeBalances);
		empresas = new ArrayList<Empresa>();
		empresas.add(unaEmpresa);

		unaEmpresa = new Empresa();
		unaEmpresa.setNombre("Empresa B");
		unaListaDeBalances = new ArrayList<Balance>(); 
		addValoresBalances(unaListaDeBalances, 5000d);
		unaEmpresa.setBalances(unaListaDeBalances);
		empresas = new ArrayList<Empresa>();
		empresas.add(unaEmpresa);

		unaEmpresa = new Empresa();
		unaEmpresa.setNombre("Empresa C");
		unaListaDeBalances = new ArrayList<Balance>(); 
		addValoresBalances(unaListaDeBalances, 15000d);
		unaEmpresa.setBalances(unaListaDeBalances);
		empresas = new ArrayList<Empresa>();
		empresas.add(unaEmpresa);
		
		indicadores = new ArrayList<Indicador>();
		Indicador unIndicador = null;
		try {
			unIndicador = new Indicador("ROE", (new ExpressionParser()).buildExpressionFrom("IngresoNeto-Dividendos/CapitalTotal"));
			indicadores.add(unIndicador);
			unIndicador = new Indicador("Proporcion Deuda", (new ExpressionParser()).buildExpressionFrom("Deuda/CapitalTotal-Deuda"));
			indicadores.add(unIndicador);
			unIndicador = new Indicador("Margen", (new ExpressionParser()).buildExpressionFrom("CostoTotal-IngresoNeto"));
			indicadores.add(unIndicador);
			unIndicador = new Indicador("Indicador EBITDA", (new ExpressionParser()).buildExpressionFrom("EBITDA"));
			indicadores.add(unIndicador);
		} catch (InvalidTokenException | SyntaxErrorException | TypeExpresionException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void crearUnaMetodologiaTaxativaDeUnaEmpresa() {
		Metodologia unaMetodologia = new Metodologia();
		unaMetodologia.setNombre("metodologiaPrueba");
		CondicionCompararValor unaCondicion = new CondicionCompararValor();
		unaCondicion.setNombreCondicion("prueba");
		List<Condicion> condiciones = new ArrayList<Condicion>();
		condiciones.add(unaCondicion);
		unaMetodologia.setCondiciones(condiciones);
	}
	
	@Test
	public void crearUnaMetodologiaTaxativaDeVariasEmpresa() {
		Metodologia unaMetodologia = new Metodologia();
		unaMetodologia.setNombre("metodologiaPrueba");
		CondicionCompararValor unaCondicion = new CondicionCompararValor();
		unaCondicion.setNombreCondicion("prueba");
		List<Condicion> condiciones = new ArrayList<Condicion>();
		condiciones.add(unaCondicion);
		unaMetodologia.setCondiciones(condiciones);
	}
	
	@Test
	public void crearUnaMetodologiaEvolutivaDeUnaEmpresa() {
		Metodologia unaMetodologia = new Metodologia();
		unaMetodologia.setNombre("metodologiaPrueba");
		CondicionCompararValor unaCondicion = new CondicionCompararValor();
		unaCondicion.setNombreCondicion("prueba");
		List<Condicion> condiciones = new ArrayList<Condicion>();
		condiciones.add(unaCondicion);
		unaMetodologia.setCondiciones(condiciones);
	}
	
	@Test
	public void crearUnaMetodologiaEvolutivaDeVariasEmpresa() {
		Metodologia unaMetodologia = new Metodologia();
		unaMetodologia.setNombre("metodologiaPrueba");
		CondicionCompararValor unaCondicion = new CondicionCompararValor();
		unaCondicion.setNombreCondicion("prueba");
		List<Condicion> condiciones = new ArrayList<Condicion>();
		condiciones.add(unaCondicion);
		unaMetodologia.setCondiciones(condiciones);
	}
	
	@Test
	public void crearUnaMetodologiaTaxativaEvolutivaDeUnaEmpresa() {
		Metodologia unaMetodologia = new Metodologia();
		unaMetodologia.setNombre("metodologiaPrueba");
		CondicionCompararValor unaCondicion = new CondicionCompararValor();
		unaCondicion.setNombreCondicion("prueba");
		List<Condicion> condiciones = new ArrayList<Condicion>();
		condiciones.add(unaCondicion);
		unaMetodologia.setCondiciones(condiciones);
	}

	@Test
	public void crearUnaMetodologiaTaxativaEvolutivaDeVariasEmpresa() {
		Metodologia unaMetodologia = new Metodologia();
		unaMetodologia.setNombre("metodologiaPrueba");
		CondicionCompararValor unaCondicion = new CondicionCompararValor();
		unaCondicion.setNombreCondicion("prueba");
		List<Condicion> condiciones = new ArrayList<Condicion>();
		condiciones.add(unaCondicion);
		unaMetodologia.setCondiciones(condiciones);
	}
	
	@Test
	public void ROEDurante10AnosMejorQueOtras1() {
		Metodologia unaMetodologia = new Metodologia();
		unaMetodologia.setNombre("metodologiaPrueba");
		CondicionCompararValor unaCondicion = new CondicionCompararValor();
		unaCondicion.setNombreCondicion("prueba");
		List<Condicion> condiciones = new ArrayList<Condicion>();
		condiciones.add(unaCondicion);
		unaMetodologia.setCondiciones(condiciones);
	}
	
	@Test
	public void MenorNivelDeDeuda() {
		Metodologia unaMetodologia = new Metodologia();
		unaMetodologia.setNombre("metodologiaPrueba");
		CondicionCompararValor unaCondicion = new CondicionCompararValor();
		unaCondicion.setNombreCondicion("prueba");
		List<Condicion> condiciones = new ArrayList<Condicion>();
		condiciones.add(unaCondicion);
		unaMetodologia.setCondiciones(condiciones);
	}
	
	@Test
	public void MargenesCrecientesDurante10Anos() {
		Metodologia unaMetodologia = new Metodologia();
		unaMetodologia.setNombre("metodologiaPrueba");
		CondicionCompararValor unaCondicion = new CondicionCompararValor();
		unaCondicion.setNombreCondicion("prueba");
		List<Condicion> condiciones = new ArrayList<Condicion>();
		condiciones.add(unaCondicion);
		unaMetodologia.setCondiciones(condiciones);
	}
	
	@Test
	public void Longevidad() {
		Metodologia unaMetodologia = new Metodologia();
		unaMetodologia.setNombre("metodologiaPrueba");
		CondicionCompararValor unaCondicion = new CondicionCompararValor();
		unaCondicion.setNombreCondicion("prueba");
		List<Condicion> condiciones = new ArrayList<Condicion>();
		condiciones.add(unaCondicion);
		unaMetodologia.setCondiciones(condiciones);
	}
	
	@Test
	public void MetodologiaWarrenBuffet() {
		Metodologia unaMetodologia = new Metodologia();
		unaMetodologia.setNombre("metodologiaPrueba");
		CondicionCompararValor unaCondicion = new CondicionCompararValor();
		unaCondicion.setNombreCondicion("prueba");
		List<Condicion> condiciones = new ArrayList<Condicion>();
		condiciones.add(unaCondicion);
		unaMetodologia.setCondiciones(condiciones);
	}
}
