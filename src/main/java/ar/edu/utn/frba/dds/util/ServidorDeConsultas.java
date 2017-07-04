package ar.edu.utn.frba.dds.util;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

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
		String json = null;
		try {
			if(Files.notExists(Paths.get(archivo)))
				Files.createFile(Paths.get(archivo));
	
			json = new String(Files.readAllBytes(Paths.get(archivo)), StandardCharsets.UTF_8);
		} catch (IOException e) {
			// TODO Tirar un mensaje de que no existe el archivo de indicadores.json, que deberia existir siempre como dijo Jona
			e.printStackTrace();
		}
		return json;
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
