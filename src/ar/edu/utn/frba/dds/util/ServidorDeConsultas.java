package ar.edu.utn.frba.dds.util;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ServidorDeConsultas {
	public String obtenerDatosDeCuentas() {
		FileReader fileReader = null; 
		BufferedReader readBuffer = null;
		String resultado = "";
		String unaLinea = "";
		try {
			 fileReader = new FileReader("datos.json");
			 readBuffer = new BufferedReader(fileReader);
			 while ((unaLinea = readBuffer.readLine()) != null) {
				resultado += unaLinea; 
			 }
			readBuffer.close();
			fileReader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return resultado;
	}
}
