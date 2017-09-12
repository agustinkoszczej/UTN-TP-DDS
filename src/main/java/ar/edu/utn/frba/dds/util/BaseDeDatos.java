package ar.edu.utn.frba.dds.util;

import java.util.List;

import javax.persistence.EntityManager;

import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;

import ar.edu.utn.frba.dds.modelo.Empresa;

public class BaseDeDatos {

	private static boolean bdEnabled = true; //Cambiar atributo a False si se quiere cargar desde JSON
	
	public BaseDeDatos (){
	}

	public boolean isBdEnabled() {
		return bdEnabled;
	}
	
	public List<Empresa> obtenerEmpresas() {
		EntityManager entityManager = PerThreadEntityManagers.getEntityManager();
		//entityManager.clear();
		System.out.println("YEEEEY");
	
		List<Empresa> empresas = entityManager.createQuery("FROM Empresa").getResultList();
		
		System.out.println("YEEEEY 2");
		System.out.println(empresas.size());
		System.out.println(empresas.isEmpty());
		
		for(int i=0; i<empresas.size(); i++){
			System.out.println(empresas.get(i).getEmpresa_nombre());
		}
		
		System.out.println("YEEEEY 3");
		
		empresas.stream().forEach(
		empresa -> 
			empresa.setBalances(
					(entityManager.createQuery(
							"FROM Balance JOIN BalancesEmpresa ON balancesEmpresa_balance = balance_id"
							+ "WHERE balancesEmpresa_empresa = "
							/*"FROM Balance JOIN BalancesEmpresa ON balancesEmpresa_empresa = balance_id"
							+ "WHERE balance_id = "*/
							+ empresa.getEmpresa_id()
					).getResultList())
			)
		);
		
		return empresas;
	}
}