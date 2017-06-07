package ar.edu.utn.frba.dds;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import ar.edu.utn.frba.dds.expresion.ExpresionCompuesta;
import ar.edu.utn.frba.dds.expresion.ExpresionConstante;
import ar.edu.utn.frba.dds.expresion.ExpresionCuenta;
import ar.edu.utn.frba.dds.expresion.Operacion;
import ar.edu.utn.frba.dds.modelo.Balance;
import ar.edu.utn.frba.dds.modelo.Cuenta;
import ar.edu.utn.frba.dds.modelo.Empresa;

public class ExpresionesTest {

	Empresa empresaprueba;
	
	@Before
	public void init() {
		empresaprueba = new Empresa();
		Balance balance = new Balance();
		balance.setPeriodo("20170100");
		balance.setTipoCuenta(Cuenta.EBITDA);
		balance.setValor(new Double(25000));
		
		List<Balance> listaBalances = new ArrayList<Balance>();
		listaBalances.add(balance);
		empresaprueba.setBalances(listaBalances);
	}
	
	
	@Test
	public void instanciarYCalcularUnaExpresionConstante(){
		Integer constante = 7;
		ExpresionConstante exp = new ExpresionConstante(constante);
		Integer resultado = exp.calculate(new Empresa(), "cualquiercosatotalnoimporta");
		Assert.assertEquals(constante,  resultado);
	}
	
	@Test
	public void instanciarYCalcularUnaExpresionCuenta(){
		ExpresionCuenta exp = new ExpresionCuenta(Cuenta.EBITDA);
		Integer resultado = exp.calculate(empresaprueba, "20170100");
		Assert.assertEquals((Integer)25000,  resultado);
	}
	
	@Test
	public void instanciarYCalcularUnaExpresionCompuesta(){
		ExpresionCuenta expCuentaEBITDA = new ExpresionCuenta(Cuenta.EBITDA);
		ExpresionConstante expConstante = new ExpresionConstante(7);
		
		ExpresionCompuesta exp = new ExpresionCompuesta(expCuentaEBITDA, Operacion.operacionSuma(), expConstante);
				
		Integer resultado = exp.calculate(empresaprueba, "20170100");
		Assert.assertEquals((Integer)25007,  resultado);
	}
	
	@Test
	public void instanciarYCalcularUnaExpresionCompuestaConExpresionesCompuestas(){
		ExpresionCuenta expCuentaEBITDA = new ExpresionCuenta(Cuenta.EBITDA);
		ExpresionConstante expConstante = new ExpresionConstante(7);
		
		ExpresionCompuesta expCompuesta1 = new ExpresionCompuesta(expCuentaEBITDA, Operacion.operacionSuma(), expConstante); // da 25007
		ExpresionCompuesta expCompuesta2 = new ExpresionCompuesta(expCompuesta1, Operacion.operacionSuma(), expCuentaEBITDA);		
		
		Integer resultado = expCompuesta2.calculate(empresaprueba, "20170100");
		
		Assert.assertEquals((Integer)50007, resultado);
	}
	
	@Test
	public void instanciarYCalcularUnaExpresionCompuestaConExpresionesCompuestasMasComplicada(){
		ExpresionCuenta expCuentaEBITDA = new ExpresionCuenta(Cuenta.EBITDA);
		ExpresionConstante expConstante = new ExpresionConstante(7);
		
		ExpresionCompuesta expCompuesta1 = new ExpresionCompuesta(expCuentaEBITDA, Operacion.operacionSuma(), expConstante); // da 25007
		ExpresionCompuesta expCompuesta2 = new ExpresionCompuesta(expCompuesta1, Operacion.operacionSuma(), expCuentaEBITDA); // da 50007		
		
		ExpresionCompuesta expCompuesta3 = new ExpresionCompuesta(expCompuesta2, Operacion.operacionResta(), expCompuesta1);
		
		Integer resultado = expCompuesta3.calculate(empresaprueba, "20170100");
		
		Assert.assertEquals((Integer)25000, resultado);
	}
	
}
