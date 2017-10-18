package ar.edu.utn.frba.dds.spark.controllers;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;

import ar.edu.utn.frba.dds.metodologia.Condicion;
import ar.edu.utn.frba.dds.metodologia.Metodologia;
import ar.edu.utn.frba.dds.modelo.Empresa;
import ar.edu.utn.frba.dds.modelo.Indicador;
import ar.edu.utn.frba.dds.util.ProveedorAcceso;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class MetodologiasController implements WithGlobalEntityManager, TransactionalOps{
	
	private List<Condicion> condiciones;
	private List<Metodologia> metodologias;
	
	
	
	public ModelAndView mostrarMetodologias(Request req, Response res){
		
		ProveedorAcceso proveedor = new ProveedorAcceso();
		List<Metodologia> metodologias = proveedor.obtenerMetodologias();
		
		//Map<String, List<Metodologia>> model = new HashMap<>();
		Map<String, MetodologiasController> model = new HashMap<>();
		
		MetodologiasController controllerMetodologia = new MetodologiasController();
		controllerMetodologia.setMetodologias(metodologias);
		
		model.put("controllerMetodologias", controllerMetodologia);
		
		return new ModelAndView(model, "metodologias/show.hbs");	
	}

	public List<Metodologia> getMetodologias() {
		return metodologias;
	}

	public void setMetodologias(List<Metodologia> metodologias) {
		this.metodologias = metodologias;
	}

	public List<Condicion> getCondiciones() {
		return condiciones;
	}

	public void setCondiciones(List<Condicion> condiciones) {
		this.condiciones = condiciones;
	}
}