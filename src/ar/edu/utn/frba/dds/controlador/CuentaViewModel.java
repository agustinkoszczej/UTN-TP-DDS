package ar.edu.utn.frba.dds.controlador;

import java.util.List;

import org.uqbar.commons.utils.Observable;

import ar.edu.utn.frba.dds.modelo.Balance;
import ar.edu.utn.frba.dds.modelo.Empresa;
import ar.edu.utn.frba.dds.servicio.ServicioCuentas;

/**
 * Clase que representa a la dinamica de un alumno, en su realcion entre
 * el Modelo y la Vista, y es quien realaciona los Modelos de Alumno
 * con sus Tareas, y se encarga de Cargar/Actualizar los datos.
 * Tiene las siguientes propiedades y sus correspondientes Setters/Getters:
 * <ul>
 * 		<li><b>unAlumno</b>: representa al alumno sobre el cual estoy 
 * 					consultando/li>
 *  	<li><b>tareas</b>: Colleccion de elementos del tipo Tarea</li>
 *  	<li><b>tareaSeleccionada</b>: elemento del tipo Tarea sobre el que 
 *  				estoy trabajando actualmente</li>
 * 		<li><b>token</b>: Token asignado al Alumno para poder consultar
 * 					y actualizar datos</li>
 * </ul>
 * <p>Tiene un constructor:
 * <ul>
 * 		<li><b>AlumnoViewModel(String token, CalificacionesApp aplicacion)</b>: 
 * 				Establece el token para el alumno actual sobre el cual se van
 * 				a realizar las consultas y establece a quien le tengo que pedir
 * 				los datos para generar los objetos.
 * 				Tambien inicializa el objeto unAlumno y el objeto tareas</li>
 * <ul>
 */
@Observable
public class CuentaViewModel {
	private List<Empresa> empresas;
	private Empresa empresaSeleccionada;
	private ServicioCuentas unServicioDeCuentas;
	private List<Balance> balancesEmpresaSeleccionada;

	private Balance balaceSeleccionadoEmpresaSeleccionada;
	
	public CuentaViewModel(ServicioCuentas unServicioDeCuentas) {
		this.unServicioDeCuentas = unServicioDeCuentas;
		this.empresas = unServicioDeCuentas.obtenerEmpresas();
		this.empresaSeleccionada = empresas.get(0);
	}

	public List<Empresa> getEmpresas() {
		return empresas;
	}
	
	public Empresa getEmpresaSeleccionada() {
		return empresaSeleccionada;
	}

	public void setEmpresaSeleccionada(Empresa empresaSeleccionada) {
		this.empresaSeleccionada = empresaSeleccionada;
	}
	
	public Balance getBalaceSeleccionadoEmpresaSeleccionada() {
		return balaceSeleccionadoEmpresaSeleccionada;
	}

	public void setBalaceSeleccionadoEmpresaSeleccionada(Balance balaceSeleccionadoEmpresaSeleccionada) {
		this.balaceSeleccionadoEmpresaSeleccionada = balaceSeleccionadoEmpresaSeleccionada;
	}

	public List<Balance> getBalancesEmpresaSeleccionada() {
		return empresaSeleccionada.getBalances();
	}
	
}
