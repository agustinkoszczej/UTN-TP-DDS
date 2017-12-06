package ar.edu.utn.frba.dds.spark.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

public class IndicadorController implements WithGlobalEntityManager, TransactionalOps {

	private Empresa empresa_indicador;
	private List<Indicador> indicadores;
	private Integer valorIndicador;
	private String log;
	
	
	public ModelAndView mostrarIndicadores(Request req, Response res) {

		ProveedorAcceso proveedor = new ProveedorAcceso();

		Map<String, IndicadorController> model = new HashMap<>();
		IndicadorController controllerIndicador = new IndicadorController();

		List<Indicador> indicadores = proveedor.obtenerIndicadores();
		controllerIndicador.setIndicadores(indicadores);

		model.put("controllerIndicador", controllerIndicador);

		return new ModelAndView(model, "indicadores/show_indicadores.hbs");
	}

	public ModelAndView aplicarIndicador(Request req, Response res) {

		String nombre_empresa = req.queryParams("empresa");
		String periodo = req.queryParams("periodo");
		String nombre_indicador = req.queryParams("indicador");

		IndicadorController controllerIndicador = new IndicadorController();
		ProveedorAcceso proveedor = new ProveedorAcceso();

		List<Indicador> indicadores = proveedor.obtenerIndicadores();
		controllerIndicador.setIndicadores(indicadores);
		
		Map<String, IndicadorController> model = new HashMap<>();

		if (nombre_empresa != null){
			List<Empresa> allEmpresas = proveedor.obtenerEmpresas();
			Empresa empresa;
			try{
			 empresa = allEmpresas.stream().filter(e -> e.getEmpresa_nombre().equals(nombre_empresa)).findFirst().get();
			} catch(Exception e){
				// TODO Auto-generated catch block
				// e.printStackTrace();
				
				model.put("controllerIndicador", controllerIndicador);
				return new ModelAndView(model, "indicadores/apply_indicadores.hbs");
			}
			controllerIndicador.setEmpresa_indicador(empresa);
		
			if (nombre_indicador != null && periodo != null && nombre_indicador != null) {
				Indicador indicador = indicadores.stream().filter(i -> i.getNombreIndicador().equals(nombre_indicador))
						.findFirst().get();
				try {
					//TODO hay que obtenerlo de la tabla de indicadores pre calculados no calcularlo de nuevo
					//controllerIndicador.setValorIndicador(BaseDeDatos.obtenerValorIndicadorPrecalculado(indicador, empresa, periodo));
					
					controllerIndicador.setValorIndicador(indicador.calcular(empresa, periodo));
				} catch (Exception e) {
					// TODO Auto-generated catch block
					// e.printStackTrace();
				}
			}
		}
		model.put("controllerIndicador", controllerIndicador);
		return new ModelAndView(model, "indicadores/apply_indicadores.hbs");
	}

	public ModelAndView crearIndicador(Request req, Response res) {
		String nombre_ind = req.queryParams("nombre");
		String expresion_ind = req.queryParams("expresion");
		
		ProveedorAcceso proveedor = new ProveedorAcceso();

		if (nombre_ind != null && expresion_ind != null) {
			ExpressionParser parser = new ExpressionParser();
			try {
				Expresion expresion_parseada = parser.buildExpressionFrom(expresion_ind);
				Indicador ind = new Indicador(nombre_ind, expresion_parseada);
				if (RepositorioIndicadores.indicadores.contains(ind)) {
					// TODO Mostrar que el indicador ya existe
					System.out.println("YA EXISTE INDICADOR");
					log = "ERROR INDICADOR: EL INDICADOR INGRESADO YA EXISTE";
					res.redirect("/error/indicador");
					
					return null;
					//TODO  
				} else {
					RepositorioIndicadores.agregarIndicador(ind);
					proveedor.guardarIndicador(ind);
				}
			} catch (Exception e) {
				// TODO Mostrar que esta mal cargado el indicador
				log = "ERROR INDICADOR: LA EXPRESION INGRESADA NO ES VALIDA";
				res.redirect("/error/indicador");
				return null;
			}
		}

		res.redirect("/consultar/indicadores");
		return null;
	}
	
	public ModelAndView errorIndicador(Request req, Response res){
		ProveedorAcceso proveedor = new ProveedorAcceso();

		Map<String, IndicadorController> model = new HashMap<>();
		IndicadorController controllerIndicador = new IndicadorController();

		List<Indicador> indicadores = proveedor.obtenerIndicadores();
		controllerIndicador.setIndicadores(indicadores);
		controllerIndicador.setLog(log);
		System.out.println(log);
		
		model.put("controllerIndicador", controllerIndicador);
		return new ModelAndView(model, "indicadores/error_indicadores.hbs");
	}

	public Empresa getEmpresa_indicador() {
		return empresa_indicador;
	}

	public void setEmpresa_indicador(Empresa empresa_indicador) {
		this.empresa_indicador = empresa_indicador;
	}

	public void setLog(String log) {
		this.log = log;
	}
	
	public String getLog() {
		return log;
	}
	
	public List<Indicador> getIndicadores() {
		return indicadores;
	}

	public void setIndicadores(List<Indicador> indicadores) {
		this.indicadores = indicadores;
	}

	public Integer getValorIndicador() {
		return valorIndicador;
	}

	public void setValorIndicador(Integer valorIndicador) {
		this.valorIndicador = valorIndicador.intValue();
	}	
}