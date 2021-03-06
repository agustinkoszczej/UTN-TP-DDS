package ar.edu.utn.frba.dds.spark.controllers;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;

import ar.edu.utn.frba.dds.metodologia.Condicion;
import ar.edu.utn.frba.dds.metodologia.Metodologia;
import ar.edu.utn.frba.dds.modelo.Empresa;
import ar.edu.utn.frba.dds.util.ProveedorAcceso;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class MetodologiasController implements WithGlobalEntityManager, TransactionalOps{
	
	private List<Condicion> condiciones;
	private List<Metodologia> metodologias;
	private Metodologia metodologiaAplicada;
	private List<Empresa> empresasOrdenadas;
	private String nombre_metodologia;
	
	
	public ModelAndView mostrarMetodologias(Request req, Response res){
		
		String nombre_meto = req.queryParams("nombre");
		
		
		ProveedorAcceso proveedor = new ProveedorAcceso();
		List<Metodologia> metodologias = proveedor.obtenerMetodologias();
		List<Empresa> empresas = proveedor.obtenerEmpresas();
		
		Map<String, MetodologiasController> model = new HashMap<>();
		
		MetodologiasController controllerMetodologia = new MetodologiasController();
		controllerMetodologia.setMetodologias(metodologias);		
		
		if(nombre_meto != null){
			Metodologia meto = metodologias.stream().filter(e->e.nombreEquals(nombre_meto)).findFirst().get();
			controllerMetodologia.setNombre_metodologia(nombre_meto);
			controllerMetodologia.setEmpresasOrdenadas(meto.aplicar(empresas));
		}

		model.put("controllerMetodologias", controllerMetodologia);
		
		return new ModelAndView(model,"metodologias/show_metodologias.hbs");	
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

	public Metodologia getMetodologiaAplicada() {
		return metodologiaAplicada;
	}

	public void setMetodologiaAplicada(Metodologia metodologiaAplicada) {
		this.metodologiaAplicada = metodologiaAplicada;
	}

	public List<Empresa> getEmpresasOrdenadas() {
		return empresasOrdenadas;
	}

	public void setEmpresasOrdenadas(List<Empresa> empresasOrdenadas) {
		this.empresasOrdenadas = empresasOrdenadas;
	}

	public String getNombre_metodologia() {
		return nombre_metodologia;
	}

	public void setNombre_metodologia(String nombre_metodologia) {
		this.nombre_metodologia = nombre_metodologia;
	}
}