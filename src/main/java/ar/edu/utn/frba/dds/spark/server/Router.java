package ar.edu.utn.frba.dds.spark.server;

import ar.edu.utn.frba.dds.spark.controllers.CuentasController;
import ar.edu.utn.frba.dds.spark.controllers.HomeController;
import ar.edu.utn.frba.dds.spark.controllers.IndicadorController;
import ar.edu.utn.frba.dds.spark.controllers.MetodologiasController;
import ar.edu.utn.frba.dds.spark.utils.BooleanHelper;
import ar.edu.utn.frba.dds.spark.utils.HandlebarsTemplateEngineBuilder;
import spark.Spark;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class Router {
	
	public static void configure() {
		
		HandlebarsTemplateEngine engine = HandlebarsTemplateEngineBuilder
				.create()
				.withDefaultHelpers()
				.withHelper("isTrue", BooleanHelper.isTrue)
				.build();

		Spark.staticFiles.location("/public");
		
		CuentasController cuentasController = new CuentasController();
		MetodologiasController metodologiasController = new MetodologiasController();
		IndicadorController indicadorController = new IndicadorController();
		
		Spark.get("consultar/empresas", cuentasController::mostrarEmpresa, engine);
		Spark.get("consultar/indicadores", IndicadorController::mostrarIndicadores, engine);
		Spark.get("consultar/metodologias", metodologiasController::mostrarMetodologias, engine);
		
		Spark.get("aplicar/indicador", indicadorController::aplicarIndicador, engine);
		
		Spark.post("crear/indicador", indicadorController::crearIndicador);
		
		Spark.get("/", HomeController::home, engine);
	}
}