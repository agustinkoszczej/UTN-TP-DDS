package ar.edu.utn.frba.dds.servicio;

import java.util.List;

import ar.edu.utn.frba.dds.util.ServidorDeConsultas;
import ar.edu.utn.frba.dds.modelo.Empresa;
import ar.edu.utn.frba.dds.util.ConversorJson;

public class ServicioCuentas {

	private ServidorDeConsultas unServidorParaConsultar;
	private ConversorJson unConversorDeCuentas;
	
	public ServicioCuentas() {
			// Inicializo el conversor
			unConversorDeCuentas = new ConversorJson();
			// Inicializo el servidor de consultas para leer los datos JSON
			unServidorParaConsultar = new ServidorDeConsultas();
	}

	public List<Empresa> obtenerEmpresas() {
		String jsonCuentas = unServidorParaConsultar.obtenerDatosDeCuentas("");
		return unConversorDeCuentas.mapearEmpresas(jsonCuentas);
	}

	public List<Empresa> obtenerEmpresas(String rutaArchivo) {
			String jsonCuentas = unServidorParaConsultar.obtenerDatosDeCuentas(rutaArchivo);
			return unConversorDeCuentas.mapearEmpresas(jsonCuentas);
	}
}
