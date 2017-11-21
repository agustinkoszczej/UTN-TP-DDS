package ar.edu.utn.frba.dds.spark.server;

import ar.edu.utn.frba.dds.util.BatchCuentas;
import spark.Spark;
import spark.debug.DebugScreen;

public class Server {
	public static void main(String[] args) {
		BatchCuentas.verificarCuentas();
		
		Spark.port(9000);
		DebugScreen.enableDebugScreen();
		Router.configure();
	}

}
