package main.java.ar.edu.utn.frba.dds;

import org.uqbar.arena.Application;
import org.uqbar.arena.windows.Window;

import main.java.ar.edu.utn.frba.dds.controlador.CuentaViewModel;
import main.java.ar.edu.utn.frba.dds.servicio.ServicioCuentas;
import main.java.ar.edu.utn.frba.dds.vista.CuentaWindow;


public class CuentasApp extends Application {

	
	public static void main(String[] args) {
		// Instancio la clase de la aplicacion visual y inicio el entorno visual
		new CuentasApp().start();
	}
	
	
	@Override
	protected Window<?> createMainWindow() {
		// Llamo a la clase que es mi primer ventana.
		ServicioCuentas unServicio = new ServicioCuentas();
		return new CuentaWindow(this, new CuentaViewModel(unServicio));
	}
	
	
	
}
