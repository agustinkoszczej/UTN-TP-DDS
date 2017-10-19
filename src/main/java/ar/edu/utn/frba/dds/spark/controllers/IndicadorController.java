package ar.edu.utn.frba.dds.spark.controllers;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JOptionPane;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;

import ar.edu.utn.frba.dds.expresion.Expresion;
import ar.edu.utn.frba.dds.modelo.Empresa;
import ar.edu.utn.frba.dds.modelo.Indicador;
import ar.edu.utn.frba.dds.repositorios.RepositorioIndicadores;
import ar.edu.utn.frba.dds.util.ExpressionParser;
import ar.edu.utn.frba.dds.util.ProveedorAcceso;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class IndicadorController implements WithGlobalEntityManager, TransactionalOps{
	
	private Empresa empresa_indicador;
	private List<Indicador> indicadores;
	
	public static ModelAndView mostrarIndicadores(Request req, Response res){
		
		String nombre_empresa = req.queryParams("nombre");
		String periodo = req.queryParams("periodo");
		
		ProveedorAcceso proveedor = new ProveedorAcceso();
		List<Empresa> allEmpresas = proveedor.obtenerEmpresas();
		
		Map<String, IndicadorController> model = new HashMap<>();
		IndicadorController controllerIndicador = new IndicadorController();

		if(nombre_empresa != null && periodo != null){
			Empresa empresa = allEmpresas.stream().filter(e->e.getEmpresa_nombre().equals(nombre_empresa)).findFirst().get();
			controllerIndicador.setEmpresa_indicador(empresa);
		}
		
		List<Indicador> indicadores = proveedor.obtenerIndicadores();
		controllerIndicador.setIndicadores(indicadores);

		model.put("controllerIndicador", controllerIndicador);

		return new ModelAndView(model, "indicadores/show_indicadores.hbs");	
	}
	
	public ModelAndView crearIndicador(Request req, Response res){
		String nombre_ind = req.queryParams("nombre");
		String expresion_ind = req.queryParams("expresion");
		
		ProveedorAcceso proveedor = new ProveedorAcceso();
		
		if(nombre_ind != null && expresion_ind != null){
		ExpressionParser parser = new ExpressionParser();	
			try {
					Expresion expresion_parseada = parser.buildExpressionFrom(expresion_ind);
					Indicador ind = new Indicador(nombre_ind, expresion_parseada);
					if(RepositorioIndicadores.indicadores.contains(ind)){
						//TODO Mostrar que el indicador ya existe
						System.out.println("YA EXISTE INDICADOR");
					}
					else{
						RepositorioIndicadores.agregarIndicador(ind);
						proveedor.guardarIndicador(ind);
					}
				} catch (Exception e) {
					//TODO Mostrar que esta mal cargado el indicador
					System.out.println("ESTA MAL CARGADO INDICADOR");
				}
		}

		res.redirect("/consultar/indicadores");
		return null;	
	}
	
	public ModelAndView aplicarIndicador(Request req, Response res){
		String nombre_ind = req.queryParams("nombre");
		
		ProveedorAcceso proveedor = new ProveedorAcceso();
		
		
		
		return null;
	}

	public Empresa getEmpresa_indicador() {
		return empresa_indicador;
	}

	public void setEmpresa_indicador(Empresa empresa_indicador) {
		this.empresa_indicador = empresa_indicador;
	}

	public List<Indicador> getIndicadores() {
		return indicadores;
	}

	public void setIndicadores(List<Indicador> indicadores) {
		this.indicadores = indicadores;
	}
	
	
}