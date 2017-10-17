package ar.edu.utn.frba.dds.spark.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;

import ar.edu.utn.frba.dds.modelo.Balance;
import ar.edu.utn.frba.dds.modelo.Empresa;
import ar.edu.utn.frba.dds.util.ProveedorAcceso;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class CuentasController implements WithGlobalEntityManager, TransactionalOps{
	
	public ModelAndView obtenerBalances(Request req, Response res){
		
		String nombre_empresa = req.params("nombre");

		ProveedorAcceso proveedor = new ProveedorAcceso();
		List<Empresa> empresas = proveedor.obtenerEmpresas();
		

		Empresa empresa = empresas.stream().filter(e->e.getEmpresa_nombre().equals(nombre_empresa)).findFirst().get();
		
		Map<String, List<Balance>> model = new HashMap<>();

		model.put("balances", empresa.getBalances());
		return new ModelAndView(model, "cuentas/show.hbs");	
	}
	/*
	public ModelAndView mostrar(Request req, Response res){
		Map<String, Proyecto> model = new HashMap<>();
		String id = req.params("id");
		
		Proyecto proyecto = RepositorioProyectos.instancia.buscar(Long.parseLong(id));
		model.put("proyecto", proyecto);
		return new ModelAndView(model, "proyectos/show.hbs");
	}
	
	public ModelAndView nuevo(Request req, Response res){
		return new ModelAndView(null, "proyectos/new.hbs");
	}
	
	public Void crear(Request req, Response res){
		Proyecto proyectoNuevo = new Proyecto(req.queryParams("nombre"), new BigDecimal(req.queryParams("costoEstimado")));
		withTransaction(() ->{
			RepositorioProyectos.instancia.agregar(proyectoNuevo);
		});
		res.redirect("/proyectos");
		return null;
	}
	*/
	
}
