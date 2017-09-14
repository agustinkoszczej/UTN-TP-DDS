package ar.edu.utn.frba.dds.util;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import ar.edu.utn.frba.dds.metodologia.Condicion;
import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;

import ar.edu.utn.frba.dds.expresion.Expresion;
import ar.edu.utn.frba.dds.metodologia.Metodologia;
import ar.edu.utn.frba.dds.modelo.Empresa;
import ar.edu.utn.frba.dds.modelo.Indicador;
import ar.edu.utn.frba.dds.modelo.RepositorioIndicadores;

public class BaseDeDatos {

	private static boolean bdEnabled = true; //Cambiar atributo a False si se quiere cargar desde JSON
	private EntityManager entityManager;
	
	public BaseDeDatos (){
		this.entityManager = PerThreadEntityManagers.getEntityManager();
		entityManager.clear();
	}

	public boolean isBdEnabled() {
		return bdEnabled;
	}
	
	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public List<Empresa> obtenerEmpresas() {
	
		List<Empresa> empresas = entityManager.createQuery("FROM Empresa").getResultList();
						
		empresas.stream().forEach(
		empresa -> 
			empresa.setBalances(
					(entityManager.createQuery(
							"FROM Balance "
							+ "WHERE balance_empresa = "
							+ empresa.getEmpresa_id()).
							getResultList())
			)
		);
		return empresas;
	}
	public List<Indicador> obtenerIndicadores(){
		ExpressionParser parser = new ExpressionParser();
		List<Indicador> indicadores = entityManager.createQuery("FROM Indicador").getResultList();
	
		for(int i=0; i<indicadores.size(); i++){	
			try {
			Expresion expresion = parser.buildExpressionFrom(indicadores.get(i).getIndicador_expresion());
			indicadores.get(i).setExpresion(expresion);
			indicadores.get(i).inicializarIndicadoresCorruptos();
			RepositorioIndicadores.agregarIndicador(indicadores.get(i));
			} catch (Exception e) {
				System.out.println("ERROR al cargar indicador"); 
			}
		}
		return indicadores;
	}
	public List<Metodologia> obtenerMetodologias(){
		List<Metodologia> metodologias = entityManager.createQuery("FROM Metodologia").getResultList(); 
		return metodologias;
	}

	public List<Condicion> obtenerCondiciones(){
		List<Condicion> condiciones = entityManager.createQuery("FROM Condicion").getResultList();
		return condiciones;
	}
}