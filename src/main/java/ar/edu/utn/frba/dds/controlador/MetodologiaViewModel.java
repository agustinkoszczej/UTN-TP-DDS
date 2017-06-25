package ar.edu.utn.frba.dds.controlador;

import ar.edu.utn.frba.dds.servicio.ServicioCuentas;
import ar.edu.utn.frba.dds.servicio.ServicioIndicadores;

public class MetodologiaViewModel{
	
	public ServicioCuentas servicioCuentas;
	public ServicioIndicadores servicioIndicadores;

	public MetodologiaViewModel(ServicioCuentas unServicioDeCuentas, ServicioIndicadores servicioIndicadores) {
		this.servicioCuentas = unServicioDeCuentas;
		this.servicioIndicadores = servicioIndicadores;
		// TODO Auto-generated constructor stub
	}

}
