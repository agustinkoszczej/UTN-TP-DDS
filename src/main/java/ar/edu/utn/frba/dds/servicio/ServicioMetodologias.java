package ar.edu.utn.frba.dds.servicio;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityTransaction;

import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;

import com.fasterxml.jackson.databind.ObjectMapper;

import ar.edu.utn.frba.dds.metodologia.Metodologia;
import ar.edu.utn.frba.dds.modelo.Indicador;
import ar.edu.utn.frba.dds.util.BaseDeDatos;
import ar.edu.utn.frba.dds.util.ConversorJson;
import ar.edu.utn.frba.dds.util.ServidorDeConsultas;

public class ServicioMetodologias {
	private File JSONFile = new File("metodologias.json");
	private ServidorDeConsultas unServidorParaConsultar;
	private ConversorJson unConversorDeMetodologias;
	private BaseDeDatos db;
	
	public ServicioMetodologias(String archivoJSON) {
		JSONFile = new File(archivoJSON);
		unConversorDeMetodologias = new ConversorJson();
		unServidorParaConsultar = new ServidorDeConsultas();
	}
	
	public ServicioMetodologias() {
		this.db = new BaseDeDatos();
		if(!db.isBdEnabled()){
			unConversorDeMetodologias = new ConversorJson();
			unServidorParaConsultar = new ServidorDeConsultas();
		}
	}
	
	public List<Metodologia> obtenerMetodologias() {
		if(!db.isBdEnabled()){
			String jsonMetodologias = unServidorParaConsultar.obtenerJson(JSONFile);
			if ((jsonMetodologias == null) || jsonMetodologias.isEmpty()) 
				return new ArrayList<Metodologia>();
			else
				return unConversorDeMetodologias.mapearMetodologias(jsonMetodologias);
		}
		return db.obtenerMetodologias();
	}
	
	public void guardarMetodologia(Metodologia unaMetodologia) throws IOException{
		if(!db.isBdEnabled()){
			ObjectMapper mapper = new ObjectMapper();
			List<Metodologia> metodologias = obtenerMetodologias();
	
			if(metodologias.contains(unaMetodologia))
				metodologias.remove(unaMetodologia);
			
			metodologias.add(unaMetodologia);
			mapper.writeValue(JSONFile, metodologias);
		}else{
			EntityTransaction tx = PerThreadEntityManagers.getEntityManager().getTransaction();
			tx.begin();
			Metodologia unaMeto = unaMetodologia;
			db.getEntityManager().persist(unaMeto);
			tx.commit();
		}
	}
}
