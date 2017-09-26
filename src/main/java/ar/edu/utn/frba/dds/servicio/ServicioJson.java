package ar.edu.utn.frba.dds.servicio;

import java.io.IOException;
import java.util.List;

import ar.edu.utn.frba.dds.metodologia.Condicion;
import ar.edu.utn.frba.dds.metodologia.Metodologia;
import ar.edu.utn.frba.dds.modelo.Empresa;
import ar.edu.utn.frba.dds.modelo.Indicador;

public class ServicioJson implements Servicio{

	private ServicioCuentas servicioCuentas;
	private ServicioCondiciones servicioCondiciones;
	private ServicioIndicadores servicioIndicadores;
	private ServicioMetodologias servicioMetodologias;
	
	public ServicioJson(){
		this.servicioCondiciones = new ServicioCondiciones();
		this.servicioCuentas = new ServicioCuentas();
		this.servicioIndicadores = new ServicioIndicadores();
		this.servicioMetodologias = new ServicioMetodologias();
		
	}
	
	public List<Empresa> obtenerEmpresas() {
		return this.servicioCuentas.obtenerEmpresas();
	}
	
	public List<Indicador> obtenerIndicadores(){
		return this.servicioIndicadores.obtenerIndicadores();
	}
	
	public List<Metodologia> obtenerMetodologias(){
		return this.servicioMetodologias.obtenerMetodologias();
	}

	public List<Condicion> obtenerCondiciones(){
		return this.servicioCondiciones.obtenerCondiciones();
	}
	
	public void guardarMetodologia(Metodologia unaMetodologia) throws IOException{
		servicioMetodologias.guardarMetodologia(unaMetodologia);
	}
	
	public void guardarIndicador(Indicador indicador) throws IOException{
		servicioIndicadores.guardarIndicador(indicador);	
	}
	
	public void guardarCondicion(Condicion unaCondicion) throws IOException{
		servicioCondiciones.guardarCondicion(unaCondicion);
	}
}
