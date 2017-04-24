package ar.edu.utn.frba.dds.servicio;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.List;

import ar.edu.utn.frba.dds.util.ServidorDeConsultas;
import ar.edu.utn.frba.dds.modelo.Empresa;
import ar.edu.utn.frba.dds.util.ConversorJsonCuentas;
import ar.edu.utn.frba.dds.util.RequestAppPath;

public class ServicioCuentas {

	private ServidorDeConsultas unServidorParaConsultar;
	private ConversorJsonCuentas unConversorDeCuentas;

	public ServicioCuentas() {
		// Inicializo el conversor
		unConversorDeCuentas = new ConversorJsonCuentas();
		// Inicializo el servidor de consultas para leer los datos JSON
		unServidorParaConsultar = new ServidorDeConsultas();
	}
	
	public List<Empresa> obtenerEmpresas() {
		//Busco las tareas de ese alumno
		String jsonCuentas = unServidorParaConsultar.obtenerDatosDeCuentas();
		return unConversorDeCuentas.mappearEmpresas(jsonCuentas);
	}
	
/*	public Alumno obtenerAlumno() {
		// Busco el alumno dado por el token y le seteo las tareas
		String jsonStudent = requestMaker.getRequest(RequestAppPath.student, this.token);
		return jsonConverter.mappearAlumno(jsonStudent);
	}
	*/

	
}
