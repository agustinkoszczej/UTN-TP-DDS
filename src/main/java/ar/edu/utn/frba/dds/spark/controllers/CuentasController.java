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
	
	public ModelAndView mostrarEmpresa(Request req, Response res){
		
		String nombre_empresa = req.queryParams("nombre");
	
		ProveedorAcceso proveedor = new ProveedorAcceso();
		List<Empresa> allEmpresas = proveedor.obtenerEmpresas();

		Empresa empresa = allEmpresas.stream().filter(e->e.getEmpresa_nombre().equals(nombre_empresa)).findFirst().get();

		Map<String, Empresa> model = new HashMap<>();
		model.put("empresa", empresa);
		
		Map<String, List<Empresa>> model_all_empresas = new HashMap<>();
		model_all_empresas.put("allEmpresas", allEmpresas);
		
		return new ModelAndView(model, "cuentas/show.hbs");	
	}
}
