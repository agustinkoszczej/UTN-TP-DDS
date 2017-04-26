package main.java.ar.edu.utn.frba.dds.servicio;

import java.util.List;

import main.java.ar.edu.utn.frba.dds.util.ServidorDeConsultas;
import main.java.ar.edu.utn.frba.dds.modelo.Empresa;
import main.java.ar.edu.utn.frba.dds.util.ConversorJsonCuentas;

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
		String jsonCuentas = unServidorParaConsultar.obtenerDatosDeCuentas();
		return unConversorDeCuentas.mappearEmpresas(jsonCuentas);
	}
}
