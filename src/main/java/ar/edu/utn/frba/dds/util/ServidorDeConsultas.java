package ar.edu.utn.frba.dds.util;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

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
	
	public String obtenerJson(File JSONFile){
		String json = null;
		try {
			if(Files.notExists(JSONFile.toPath())) // Paths.get(JSONFile.getName())))
				Files.createFile(JSONFile.toPath());
	
			json = new String(Files.readAllBytes(JSONFile.toPath()), StandardCharsets.UTF_8);
		} catch (IOException e) {
			// TODO Tirar un mensaje de que no existe el archivo de indicadores.json, que deberia existir siempre como dijo Jona
			e.printStackTrace();
		}
		return json;
	}
}
