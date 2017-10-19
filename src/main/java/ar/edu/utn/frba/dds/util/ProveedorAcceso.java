package ar.edu.utn.frba.dds.util;

import java.io.IOException;
import java.util.List;

import ar.edu.utn.frba.dds.metodologia.Condicion;
import ar.edu.utn.frba.dds.metodologia.Metodologia;
import ar.edu.utn.frba.dds.modelo.Empresa;
import ar.edu.utn.frba.dds.modelo.Indicador;
import ar.edu.utn.frba.dds.servicio.BaseDeDatos;
import ar.edu.utn.frba.dds.servicio.Servicio;
import ar.edu.utn.frba.dds.servicio.ServicioJson;

public class ProveedorAcceso {

	private Servicio proveedorDeDatos;

	public ProveedorAcceso(BaseDeDatos _proveedorDeDatos){
		if(BaseDeDatos.bdEnabled)
			this.proveedorDeDatos = _proveedorDeDatos;
		else
			this.proveedorDeDatos = new ServicioJson();
	}


	public ProveedorAcceso(){
		//TODO Este pasaria a definir como arranca la app por default.
		//new ServicioJson para levantar de un Json
		//new BaseDeDatos para levantar de mysql
		if(BaseDeDatos.bdEnabled)
			this.proveedorDeDatos = new BaseDeDatos(); 
		else
			this.proveedorDeDatos = new ServicioJson();
	}

	public void setProveedorBD(){
		proveedorDeDatos = new BaseDeDatos();
	}
	
	public void setProveedorJson(){
		proveedorDeDatos = new ServicioJson();
	}
	
	public List<Empresa> obtenerEmpresas() {
		return this.proveedorDeDatos.obtenerEmpresas();
	}
	
	public List<Indicador> obtenerIndicadores(){
		return this.proveedorDeDatos.obtenerIndicadores();
	}
	
	public List<Metodologia> obtenerMetodologias(){
		return this.proveedorDeDatos.obtenerMetodologias();
	}

	public List<Condicion> obtenerCondiciones(){
		return this.proveedorDeDatos.obtenerCondiciones();
	}
	
	public void guardarIndicador(Indicador unIndicador) throws IOException{
		this.proveedorDeDatos.guardarIndicador(unIndicador);
	}
	
	public void guardarMetodologia(Metodologia unaMeto) throws IOException{
		this.proveedorDeDatos.guardarMetodologia(unaMeto);
	}

	public void guardarCondicion(Condicion unaCondicion) throws IOException{
		this.proveedorDeDatos.guardarCondicion(unaCondicion);
	}
}
