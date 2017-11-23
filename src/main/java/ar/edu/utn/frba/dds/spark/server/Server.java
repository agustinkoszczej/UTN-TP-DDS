package ar.edu.utn.frba.dds.spark.server;

import ar.edu.utn.frba.dds.util.BatchCuentas;
import spark.Spark;
import spark.Spark.*;
import spark.debug.DebugScreen;

public class Server {
	public static void main(String[] args) {
		BatchCuentas.verificarCuentas();
		
		Spark.port(getHerokuAssignedPort());
		DebugScreen.enableDebugScreen();
		Router.configure();
	}

    static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        return 4567; //return default port if heroku-port isn't set (i.e. on localhost)
    }

}
