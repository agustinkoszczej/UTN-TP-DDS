package ar.edu.utn.frba.dds.spark.controllers;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;

import ar.edu.utn.frba.dds.modelo.Empresa;
import ar.edu.utn.frba.dds.modelo.Indicador;
import ar.edu.utn.frba.dds.util.ProveedorAcceso;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class IndicadorController implements WithGlobalEntityManager, TransactionalOps{
	
	
	
	
	public static ModelAndView mostrarIndicadores(Request req, Response res){
		
		ProveedorAcceso proveedor = new ProveedorAcceso();
		List<Indicador> indicadores = proveedor.obtenerIndicadores();

		Map<String, List<Indicador>> model = new HashMap<>();
		model.put("indicadores", indicadores);

		return new ModelAndView(model, "indicadores/show.hbs");	
	}
}