package ar.edu.utn.frba.dds;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import ar.edu.utn.frba.dds.modelo.Empresa;
import ar.edu.utn.frba.dds.servicio.ServicioCuentas;
import ar.edu.utn.frba.dds.util.ConversorJson;
import ar.edu.utn.frba.dds.util.ServidorDeConsultas;

public class EmpresaTest {
	private static final double DELTA = 1e-15;
	
	@Test
	public void esEmpresa(){
		ServicioCuentas serv = new ServicioCuentas();
		List<Empresa> empresas = new ArrayList<Empresa>();
		empresas = serv.obtenerEmpresas();
		int a = 0;
		for(Empresa e: empresas) { 
			if(a==0)
			Assert.assertEquals(e.getNombre(),"Facebook");
			else
				Assert.assertEquals(e.getNombre(),"Fibertel");
			a++;
		}
		Assert.assertEquals(a,2);
	}
	@Test
	public void valorDeBalancesFacebook(){
		ServicioCuentas serv = new ServicioCuentas();
		List<Empresa> empresas = new ArrayList<Empresa>();
		empresas = serv.obtenerEmpresas();
		Assert.assertEquals(empresas.get(0).getNombre(), "Facebook");
		double valorBalancesFacebook = 135000000+140000000;
		//El delta es para comprar doubles (es el margen de diferencia entre cada valor)
		Assert.assertEquals(empresas.get(0).valorBalances(), valorBalancesFacebook, DELTA);
	}
	@Test
	public void valorDeBalancesFibertel(){
		ServicioCuentas serv = new ServicioCuentas();
		List<Empresa> empresas = new ArrayList<Empresa>();
		empresas = serv.obtenerEmpresas();
		Assert.assertEquals(empresas.get(1).getNombre(), "Fibertel");
		double valorBalancesFibertel = 134000000;
		//El delta es para comprar doubles (es el margen de diferencia entre cada valor)
		Assert.assertEquals(empresas.get(1).valorBalances(), valorBalancesFibertel, DELTA);
	}
}
