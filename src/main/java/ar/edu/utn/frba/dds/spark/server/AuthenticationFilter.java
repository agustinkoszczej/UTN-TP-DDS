package ar.edu.utn.frba.dds.spark.server;

import spark.Request;
import spark.Response;
import spark.Filter;

public class AuthenticationFilter implements Filter{

	private boolean isPublic(String pathInfo) {
		// TODO Auto-generated method stub
		return pathInfo.equals("/login");
	}

	@Override
	public void handle(Request req, Response res) throws Exception {
		boolean authenticated = req.session().attribute("username") != null;
		if(!isPublic(req.pathInfo()) && !authenticated){
			res.redirect("/login");
		}
	}
}
