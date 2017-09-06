package ar.edu.utn.frba.dds;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import ar.edu.utn.frba.dds.controlador.CuentaViewModel;
import ar.edu.utn.frba.dds.modelo.Balance;
import ar.edu.utn.frba.dds.modelo.Empresa;
import ar.edu.utn.frba.dds.modelo.Frecuencia;
import ar.edu.utn.frba.dds.modelo.TipoDeCuenta;
import ar.edu.utn.frba.dds.servicio.ServicioCuentas;

public class EmpresaTest {
	//El delta es para comprar doubles (es el margen de diferencia entre cada valor)
	private static final double DELTA = 1e-15;
	
	ServicioCuentas serv;
	List<Empresa> empresas;
	
	@Before
	public void init() {
		serv = new ServicioCuentas();
		empresas = new ArrayList<Empresa>();	
		empresas = serv.obtenerEmpresas();
		
	}
	@Test
	public void empresasActualesCargadas(){
			Assert.assertEquals(empresas.get(0).getNombre(),"Facebook");
			Assert.assertEquals(empresas.get(1).getNombre(),"Fibertel");
			Assert.assertEquals(empresas.get(2).getNombre(),"Arena");
			Assert.assertEquals(empresas.get(3).getNombre(),"Balances Negativos");
			Assert.assertEquals(empresas.get(4).getNombre(),"Vacia");
	}
	@Test
	public void valorDeBalancesFacebook(){
		Assert.assertEquals(empresas.get(0).getNombre(), "Facebook");
		double valorBalancesFacebook = 135000000+140000000;
		Assert.assertEquals(empresas.get(0).valorBalances(), valorBalancesFacebook, DELTA);
	}
	@Test
	public void valorDeBalancesFibertel(){
		Assert.assertEquals(empresas.get(1).getNombre(), "Fibertel");
		double valorBalancesFibertel = 134000000;
		Assert.assertEquals(empresas.get(1).valorBalances(), valorBalancesFibertel, DELTA);
	}
	@Test(expected = Exception.class)
	public void valorDeBalancesVaciaRompe(){
		Assert.assertEquals(empresas.get(4).getNombre(), "Vacia");
		empresas.get(4).valorBalances();
	}
	@Test(expected = Exception.class)
	public void rompeCuandoSeleccionoEmpresaVacia(){
		CuentaViewModel cuentaVM = new CuentaViewModel(serv);
		Empresa rota = empresas.get(4);
		Assert.assertEquals(rota.getNombre(), "Vacia");
		cuentaVM.setEmpresaSeleccionada(rota);
	}
	@Test
	public void sumaBienAPesarDeSerNegativoUnValorDeLosBalances(){
		Empresa negativa = empresas.get(3);
		double valorBalancesNegativos = 5-20;
		Assert.assertEquals(negativa.valorBalances(), valorBalancesNegativos, DELTA);
		
	}
	
	@Test(expected = Exception.class)
	public void rompeCuandoSeObtienenBalancesQueLeFaltaValor(){
		Empresa empresaRota = new Empresa();
		Balance balanceNormal = new Balance();
		Balance balanceSinValor = new Balance();
		
		balanceNormal.setFrecuencia(Frecuencia.Mensual);
		balanceNormal.setPeriodo("201705");
		balanceNormal.setTipoCuenta(TipoDeCuenta.FDS);
		balanceNormal.setValor((double) 100);
		
		balanceSinValor.setFrecuencia(Frecuencia.Mensual);
		balanceSinValor.setPeriodo("201705");
		balanceSinValor.setTipoCuenta(TipoDeCuenta.FDS);
		
		List<Balance> balances;
		balances = new ArrayList<Balance>();
		
		balances.add(balanceNormal);
		balances.add(balanceSinValor);
		
		empresaRota.setNombre("EmpresaRota");
		empresaRota.setBalances(balances);
		empresaRota.valorBalances();
	}
	
}
