package ar.edu.utn.frba.dds.spark.controllers;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;

import ar.edu.utn.frba.dds.metodologia.Metodologia;
import ar.edu.utn.frba.dds.modelo.Empresa;
import ar.edu.utn.frba.dds.modelo.Indicador;
import ar.edu.utn.frba.dds.util.ProveedorAcceso;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class MetodologiasController implements WithGlobalEntityManager, TransactionalOps{
	
	
	
	public ModelAndView mostrarMetodologias(Request req, Response res){
		
		ProveedorAcceso proveedor = new ProveedorAcceso();
		List<Metodologia> metodologias = proveedor.obtenerMetodologias();

		Map<String, List<Metodologia>> model = new HashMap<>();
		model.put("metodologias", metodologias);

		return new ModelAndView(model, "indicadores/show.hbs");	
	}
}