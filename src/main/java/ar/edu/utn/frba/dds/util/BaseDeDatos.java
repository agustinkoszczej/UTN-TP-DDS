package ar.edu.utn.frba.dds.util;

import java.util.List;

import javax.persistence.EntityManager;

import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;

import ar.edu.utn.frba.dds.modelo.Balance;
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
		entityManager.clear();
	
		List<Empresa> empresas = entityManager.createQuery("FROM Empresa").getResultList();
		
				
		empresas.stream().forEach(empresa -> System.out.println(empresa.getEmpresa_nombre()));
		
		List<Balance> bals = entityManager.createQuery(
				"SELECT b.balance_id, b.balance_periodo, b.balance_frecuencia, b.balance_tipoCuenta, b.balance_valor"
				+" FROM Balance b INNER JOIN BalancesEmpresa be ON (be.balancesEmpresa_balance = b.balance_id)"
				+" WHERE be.balancesEmpresa_empresa = 1").getResultList();
		bals.stream().forEach(balance -> System.out.println(balance.getBalance_valor()));
		
		System.out.println("YEEEEY 3");
		/*
		empresas.stream().forEach(
		empresa -> 
			empresa.setBalances(
					(entityManager.createQuery(
							"FROM Balance B JOIN BalancesEmpresa BM ON (BM.balancesEmpresa_balance = B.balance_id)"
							+" WHERE BM.balancesEmpresa_empresa = "
							+ empresa.getEmpresa_id()
					).getResultList())
			)
		);*/
		return empresas;
	}
}