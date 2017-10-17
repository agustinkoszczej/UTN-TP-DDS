package ar.edu.utn.frba.dds.spark.server;

import spark.Request;
import spark.Response;

public class AuthenticationFilter {

	
	public void isAuthorized(Request req, Response res){
		boolean authenticated = req.session().attribute("usuario") != null;
		if(!isPublic(req.pathInfo()) && !authenticated){
			res.redirect("/login");
		}
	}

	private boolean isPublic(String pathInfo) {
		// TODO Auto-generated method stub
		return pathInfo.equals("/login");
	}
}
