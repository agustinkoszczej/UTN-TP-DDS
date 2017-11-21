package ar.edu.utn.frba.dds.util;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;

import ar.edu.utn.frba.dds.modelo.Balance;
import ar.edu.utn.frba.dds.modelo.Empresa;
import ar.edu.utn.frba.dds.servicio.ServicioCuentas;

public class BatchCuentas {
	//TODO falta que se persista, y validar que si ya existe lo updateo en vez de insertarlo de nuevo
	// y tambien de borrar el archivo cada vez que lo lea, para asi evitar que lea lo mismo 2 veces (como dice enunciado)
	static ServicioCuentas batch;
	static ProveedorAcceso proveedor;
	static EntityManager entityManager;
	
	public static void verificarCuentas(){
		batch = new ServicioCuentas();
		proveedor = new ProveedorAcceso();
		
		entityManager = PerThreadEntityManagers.getEntityManager();
		EntityTransaction tx = entityManager.getTransaction();
		
		tx.begin();
		List<Empresa> empresas_batch = batch.obtenerEmpresas();
		List<Empresa> empresas_bd = proveedor.obtenerEmpresas();
		for (int i=0;i<empresas_batch.size();i++){
			Empresa empresaBatch = empresas_batch.get(i);
			int id = reemplazarSiexisteEmpresa(empresaBatch, empresas_bd);
			try{
			for (int j=0;j<empresaBatch.getBalances().size();j++){
				List<Balance> balances_bd = empresas_bd.stream().filter(empresa->empresa.getEmpresa_nombre().equals(empresaBatch.getEmpresa_nombre())).findFirst().orElse(null).getBalances();
				reemplazarSiexisteBalance(empresaBatch.getBalances().get(j), balances_bd, id);
			}
			} catch(Exception e){
				//es empresa Vacia
			}
		}
		tx.commit();
	}
	
	private static int reemplazarSiexisteEmpresa(Empresa empresaNueva, List<Empresa> empresas){
		//Si tiene mismo nombre de Empresa lo pisa
		Empresa empresaModif = empresas.stream().filter(empresa->empresa.getEmpresa_nombre().equals(empresaNueva.getEmpresa_nombre())).findFirst().orElse(null);
		if(empresaModif != null){
			try{
			empresaModif.setEmpresa_anioCreacion(empresaNueva.getEmpresa_anioCreacion());
			} catch (Exception e){
				//es empresa Vacia
			}
			empresaModif.setEmpresa_nombre(empresaNueva.getEmpresa_nombre());
			return empresaModif.getEmpresa_id();
		}
		else{
			EntityTransaction tx = PerThreadEntityManagers.getEntityManager().getTransaction();
			if(!tx.isActive())
				tx.begin();
			entityManager.persist(empresaNueva);
			tx.commit();
			return -1;
		}
	}
	
	private static void reemplazarSiexisteBalance(Balance balanceNuevo, List<Balance> balances, int empresa_id){
		//Si tiene mismo periodo de Balance lo pisa
		Balance balanceModif = balances.stream().filter(balance->balance.getBalance_periodo().equals(balanceNuevo.getBalance_periodo())).findFirst().orElse(null);
		if(balanceModif != null){
			balanceModif = balanceNuevo;
		}
		else{
			EntityTransaction tx = PerThreadEntityManagers.getEntityManager().getTransaction();
			if(!tx.isActive())
				tx.begin();
			balanceNuevo.setBalance_empresa(empresa_id);
			entityManager.persist(balanceNuevo);
			tx.commit();
		}
	}
}
