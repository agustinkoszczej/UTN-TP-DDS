package ar.edu.utn.frba.dds.servicio;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.List;

import ar.edu.utn.frba.dds.util.ServidorDeConsultas;
import ar.edu.utn.frba.dds.modelo.Empresa;
import ar.edu.utn.frba.dds.util.ConversorJsonCuentas;

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
