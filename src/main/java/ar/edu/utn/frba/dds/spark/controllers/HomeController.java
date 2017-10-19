package ar.edu.utn.frba.dds.spark.controllers;

import ar.edu.utn.frba.dds.servicio.BaseDeDatos;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class HomeController {
	
	public static Void home(Request req, Response res){
		res.redirect("/consultar/empresas");
		return null;
	}
	public static ModelAndView login(Request req, Response res){
		return new ModelAndView(null, "home/login.hbs");
	}
	
	public static Void accederDesdeLogin(Request req, Response res){
		String username = req.queryParams("username");
		if(new BaseDeDatos().login(username, req.queryParams("password"))){
			req.session(true);
			req.session().attribute("username", username);
		}
		res.redirect("/");
		//return new ModelAndView(null, "home/home.hbs");
		return null;
	}
}
