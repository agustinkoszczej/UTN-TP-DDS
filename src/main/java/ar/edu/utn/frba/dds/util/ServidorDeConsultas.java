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
	
	public String obtenerJson(String archivo){
		FileReader fileReader = null; 
		BufferedReader readBuffer = null;
		String resultado = "";
		String unaLinea = "";
		try {
			 fileReader = new FileReader(archivo);
			 readBuffer = new BufferedReader(fileReader);
			 while ((unaLinea = readBuffer.readLine()) != null) {
				resultado += unaLinea; 
			 }
			readBuffer.close();
			fileReader.close();
		} catch (IOException e) {
			return "";
		}
		return resultado;
	}
	/*
	public String obtenerJson(String archivo) {
		Path path = (Path) FileSystems.getDefault().getPath(archivo);
		try {
			return String.join("", Files.readAllLines(path));
		} catch (IOException e) {
			return "";
		}
	}
	*/
}
