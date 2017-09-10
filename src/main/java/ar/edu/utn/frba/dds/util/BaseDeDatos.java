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
		
		List<Empresa> empresas = entityManager.createQuery("SELECT * FROM Empresa").getResultList();
		
		for(int i=0; i<empresas.size(); i++){
			System.out.println(empresas.get(i).getEmpresa_nombre());
		}
		empresas.stream().forEach(empresa -> 
		empresa.setBalances(entityManager.createQuery(
				"SELECT *" 
				+ "FROM Balance JOIN BalancesEmpresa ON balancesEmpresa_empresa = balance_id"
				+ "WHERE balance_id = "
				+ empresa.getEmpresa_id())
				.getResultList()));
		
		return empresas;
	}
}