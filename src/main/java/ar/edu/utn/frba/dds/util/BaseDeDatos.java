package ar.edu.utn.frba.dds.util;

import java.util.List;

import javax.persistence.EntityManager;
import javax.swing.JOptionPane;

import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;

import ar.edu.utn.frba.dds.expresion.Expresion;
import ar.edu.utn.frba.dds.modelo.Balance;
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
		System.out.println(RepositorioIndicadores.indicadores);
		System.out.println("Hola");
		System.out.println(indicadores.size());
	
		for(int i=0; i<indicadores.size(); i++){	
			System.out.println(indicadores.get(i).getIndicador_expresion());
			try {
			Expresion expresion = parser.buildExpressionFrom(indicadores.get(i).getIndicador_expresion());
			Indicador ind = new Indicador(indicadores.get(i).getNombreIndicador(), expresion);
			RepositorioIndicadores.agregarIndicador(ind);
			} catch (Exception e) {
				System.out.println("ERROR al cargar indicador"); 
			}
		}
		return indicadores;
	}
}