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
import ar.edu.utn.frba.dds.servicio.BaseDeDatos;
import ar.edu.utn.frba.dds.servicio.ServicioJson;
import ar.edu.utn.frba.dds.servicio.ServicioCuentas;
import ar.edu.utn.frba.dds.util.ProveedorAcceso;

public class EmpresaTest {
	//El delta es para comprar doubles (es el margen de diferencia entre cada valor)
	private static final double DELTA = 1e-15;
	
	ProveedorAcceso serv;
	List<Empresa> empresas;
	
	@Before
	public void init() {
		serv = new ProveedorAcceso();
		empresas = new ArrayList<Empresa>();	
		empresas = serv.obtenerEmpresas();
		
	}
	@Test
	public void empresasActualesCargadas(){
			Assert.assertEquals(empresas.get(0).getEmpresa_nombre(),"Facebook");
			Assert.assertEquals(empresas.get(1).getEmpresa_nombre(),"Fibertel");
			Assert.assertEquals(empresas.get(2).getEmpresa_nombre(),"Arena");
			Assert.assertEquals(empresas.get(3).getEmpresa_nombre(),"Balances Negativos");
			Assert.assertEquals(empresas.get(4).getEmpresa_nombre(),"Vacia");
	}
	@Test
	public void valorDeBalancesFacebook(){
		Assert.assertEquals(empresas.get(0).getEmpresa_nombre(), "Facebook");
		double valorBalancesFacebook = 135000000+140000000;
		Assert.assertEquals(empresas.get(0).valorBalances(), valorBalancesFacebook, DELTA);
	}
	@Test
	public void valorDeBalancesFibertel(){
		Assert.assertEquals(empresas.get(1).getEmpresa_nombre(), "Fibertel");
		double valorBalancesFibertel = 134000000;
		Assert.assertEquals(empresas.get(1).valorBalances(), valorBalancesFibertel, DELTA);
	}
	@Test
	public void devuelveCeroCuandoSeleccionoEmpresaVacia(){
		CuentaViewModel cuentaVM = new CuentaViewModel(serv);
		Empresa emp = empresas.get(4);
		Assert.assertEquals(emp.valorBalances(), 0, DELTA);
	}
	@Test
	public void sumaBienAPesarDeSerNegativoUnValorDeLosBalances(){
		Empresa negativa = empresas.get(3);
		double valorBalancesNegativos = 5-20;
		Assert.assertEquals(negativa.valorBalances(), valorBalancesNegativos, DELTA);
	}
	
	@Test
	public void devuelveCeroCuandoSeObtienenBalancesQueLeFaltaValor(){
		Empresa empresaCero = new Empresa();
		Balance balanceNormal = new Balance();
		Balance balanceSinValor = new Balance();
		
		balanceNormal.setBalance_frecuencia(Frecuencia.Mensual);
		balanceNormal.setBalance_periodo("201705");
		balanceNormal.setBalance_tipoCuenta(TipoDeCuenta.FDS);
		balanceNormal.setBalance_valor((double) 100);
		
		balanceSinValor.setBalance_frecuencia(Frecuencia.Mensual);
		balanceSinValor.setBalance_periodo("201705");
		balanceSinValor.setBalance_tipoCuenta(TipoDeCuenta.FDS);
		
		List<Balance> balances;
		balances = new ArrayList<Balance>();
		
		balances.add(balanceNormal);
		balances.add(balanceSinValor);
		
		empresaCero.setEmpresa_nombre("EmpresaCero");
		empresaCero.setBalances(balances);
		empresaCero.valorBalances();
		Assert.assertEquals(empresaCero.valorBalances(), 0, DELTA);
	}
	
}
