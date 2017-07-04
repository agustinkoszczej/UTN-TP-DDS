package ar.edu.utn.frba.dds;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

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

	@Before
	public void init() {
		
	}
	
	@Test
	public void crearUnaMetodologiaTaxativaDeUnaEmpresaConSumatoria() throws InvalidTokenException, SyntaxErrorException, TypeExpresionException {
		Empresa unaEmpresa = new Empresa();
		unaEmpresa.setNombre("AnyEmpresa");
		Balance unBalance = new Balance();
		unBalance.setTipoCuenta(TipoDeCuenta.EBITDA);
		unBalance.setFrecuencia(Frecuencia.Anual);
		unBalance.setPeriodo("012016");
		unBalance.setValor(10000d);
		List<Balance> unaListaDeBalances = new ArrayList<Balance>(); 
		unaListaDeBalances.add(unBalance);
		unaEmpresa.setBalances(unaListaDeBalances);
		Indicador unIndicador = new Indicador("IndEBITDA", (new ExpressionParser()).buildExpressionFrom("EBITDA"));
		
	}
}
