package ar.edu.utn.frba.dds.util;

import java.util.List;

import javax.persistence.EntityManager;

import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;

import ar.edu.utn.frba.dds.modelo.Balance;
import ar.edu.utn.frba.dds.modelo.Empresa;
import ar.edu.utn.frba.dds.modelo.Indicador;

public class BaseDeDatos {

	private static boolean bdEnabled = false; //Cambiar atributo a False si se quiere cargar desde JSON
	
	public BaseDeDatos (){
	}

	public boolean isBdEnabled() {
		return bdEnabled;
	}
	
	public List<Empresa> obtenerEmpresas() {
		EntityManager entityManager = PerThreadEntityManagers.getEntityManager();
		entityManager.clear();
	
		List<Empresa> empresas = entityManager.createQuery("FROM Empresa").getResultList();
		
				
		empresas.stream().forEach(empresa -> System.out.println(empresa.getEmpresa_nombre()));
		
		System.out.println("YEEEEY 2");
		
		List<Balance> bals = entityManager.createQuery(
				"FROM Balance "
				+ "WHERE balance_empresa = 1").getResultList();
		bals.stream().forEach(balance -> System.out.println(balance.getBalance_valor()));
		
		System.out.println("YEEEEY 3");
		
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
	/*public List<Indicador> obtenerJson(){
		
	}*/
}