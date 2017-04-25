package test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.junit.Test;

import ar.edu.utn.frba.dds.modelo.Empresa;
import ar.edu.utn.frba.dds.servicio.ServicioCuentas;
import ar.edu.utn.frba.dds.util.ConversorJsonCuentas;
import ar.edu.utn.frba.dds.util.ServidorDeConsultas;

public class EmpresaTest {
	@Test
	public void esEmpresa(){
		ServicioCuentas serv = new ServicioCuentas();
		List<Empresa> empresas = new ArrayList<Empresa>();
		empresas = serv.obtenerEmpresas();
		int a = 0;
		for(Empresa e: empresas) { 
			if(a==0)
			assertEquals(e.getNombre(),"Facebook");
			else
			assertEquals(e.getNombre(),"Fibertel");
			a++;
		}
		assertEquals(a,2);
	}
}
