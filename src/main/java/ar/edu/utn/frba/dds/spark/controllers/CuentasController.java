package ar.edu.utn.frba.dds.spark.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;

import ar.edu.utn.frba.dds.modelo.Empresa;
import ar.edu.utn.frba.dds.util.ProveedorAcceso;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class CuentasController implements WithGlobalEntityManager, TransactionalOps{
	
	private Empresa empresa;
	private List<Empresa> allEmpresas;
	
	public ModelAndView mostrarEmpresa(Request req, Response res){
		
		String nombre_empresa = req.queryParams("nombre");
	
		ProveedorAcceso proveedor = new ProveedorAcceso();
		
		List<Empresa> allEmpresas = proveedor.obtenerEmpresas();
		
		Map<String, CuentasController> model = new HashMap<>();
		CuentasController controllerCuentas = new CuentasController();
		
		if(nombre_empresa != null){
			Empresa empresa = allEmpresas.stream().filter(e->e.getEmpresa_nombre().equals(nombre_empresa)).findFirst().get();
			controllerCuentas.setEmpresa(empresa);
		}
		controllerCuentas.setAllEmpresas(allEmpresas);
		
		model.put("controllerCuentas", controllerCuentas);
		
		return new ModelAndView(model, "cuentas/show_cuentas.hbs");	
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public List<Empresa> getAllEmpresas() {
		return allEmpresas;
	}

	public void setAllEmpresas(List<Empresa> allEmpresas) {
		this.allEmpresas = allEmpresas;
	}
	
}
