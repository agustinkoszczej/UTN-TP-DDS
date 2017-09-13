package ar.edu.utn.frba.dds.servicio;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.persistence.EntityTransaction;

import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;

import com.fasterxml.jackson.databind.ObjectMapper;

import ar.edu.utn.frba.dds.modelo.Indicador;
import ar.edu.utn.frba.dds.util.BaseDeDatos;
import ar.edu.utn.frba.dds.util.ConversorJson;
import ar.edu.utn.frba.dds.util.ServidorDeConsultas;

public class ServicioIndicadores {
	private ServidorDeConsultas servidor;
	private ConversorJson conversorJson;

	private File JSONFile = new File("indicadores.json");
	
	private BaseDeDatos db;
	
	public ServicioIndicadores(BaseDeDatos base) {
		// Inicializo el conversor
		this.db = base;
		if(!db.isBdEnabled()){
			conversorJson = new ConversorJson();
			// Inicializo el servidor de consultas para leer los datos JSON
			servidor = new ServidorDeConsultas();
		}
	}
	
	public List<Indicador> obtenerIndicadores() {
		if(!db.isBdEnabled()){
			String jsonIndicadores = null;	
		jsonIndicadores = servidor.obtenerJson(JSONFile);
		return conversorJson.mapearIndicadores(jsonIndicadores);
		}
		return db.obtenerIndicadores();
	}
	
	//Agarra los ya guardados en el archivo, le agrega el nuevo y los vuelve a guardar
	public void guardarIndicador(Indicador indicador) throws IOException{
		
		ObjectMapper mapper = new ObjectMapper();
		List<Indicador> indicadoresActuales = obtenerIndicadores();
		//si esta un indicador con el mismo nombre lo reemplazamos
		if(indicadoresActuales.contains(indicador))
			indicadoresActuales.remove(indicador);
		
		indicadoresActuales.add(indicador);
		
		if(!db.isBdEnabled()){
			mapper.writeValue(JSONFile, indicadoresActuales);
		} else{
			indicador.setIndicador_expresion(indicador.formula());
			EntityTransaction tx = PerThreadEntityManagers.getEntityManager().getTransaction();
			tx.begin();
			Indicador ind = indicador;
			db.getEntityManager().persist(ind);
			tx.commit();
		}
	}	
}
