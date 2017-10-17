package ar.edu.utn.frba.dds.spark.server;

import ar.edu.utn.frba.dds.spark.controllers.CuentasController;
import ar.edu.utn.frba.dds.spark.controllers.HomeController;
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
		Spark.get("consultar/cuentas/:nombre", cuentasController::obtenerBalances, engine);
		
		Spark.get("/", HomeController::home, engine);
		/*Spark.get("/login", HomeController::login, engine);
		Spark.get("/proyectos", proyectosController::listar, engine);
		Spark.get("/proyectos/new", proyectosController::nuevo, engine);
		Spark.get("/proyectos/:id", proyectosController::mostrar, engine);
		Spark.post("/", HomeController::accederDesdeLogin);
		Spark.post("/proyectos", proyectosController::crear);*/
	}
}