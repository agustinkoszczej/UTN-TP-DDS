package ar.edu.utn.frba.dds.spark.controllers;

import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class HomeController {
	public static ModelAndView home(Request req, Response res){
		return new ModelAndView(null, "home/home.hbs");
	}
	public static ModelAndView login(Request req, Response res){
		return new ModelAndView(null, "home/login.hbs");
	}
	
	public static Void accederDesdeLogin(Request req, Response res){
		
		if(req.queryParams("user").equals("Nick")){
			System.out.println("recibido");
			res.redirect("/proyectos");
		}else res.redirect("/");
		
		//return new ModelAndView(null, "home/home.hbs");
		return null;
	}
}
