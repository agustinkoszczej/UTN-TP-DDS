package ar.edu.utn.frba.dds.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;

import ar.edu.utn.frba.dds.modelo.Balance;
import ar.edu.utn.frba.dds.modelo.Empresa;
import ar.edu.utn.frba.dds.servicio.ServicioCuentas;

public class BatchCuentas {
	//TODO falta que se borre el archivo cada vez que lo lea, para asi evitar que lea lo mismo 2 veces (como dice enunciado)
	static ServicioCuentas batch;
	static ProveedorAcceso proveedor;
	static EntityManager entityManager;
	
	public static void verificarCuentas(){
		batch = new ServicioCuentas();
		proveedor = new ProveedorAcceso();
		
		entityManager = PerThreadEntityManagers.getEntityManager();
		EntityTransaction tx = entityManager.getTransaction();
		
		tx.begin();
		List<Empresa> empresas_batch = batch.obtenerEmpresas(""); //Se puede especificar la ruta del archivo
		List<Empresa> empresas_bd = proveedor.obtenerEmpresas();
		for (Empresa empresaBatch: empresas_batch){
			int id = reemplazarSiexisteEmpresa(empresaBatch, empresas_bd);
			Optional<Empresa> empresa_bd = empresas_bd.stream()
							.filter(empresa->empresa.seLlamaIgualQue(empresaBatch))
							.findFirst();
			if (empresa_bd.isPresent()){
				List<Balance> balances_bd;
				if (!empresa_bd.get().tieneBalances()) {
					empresa_bd.get().setBalances(new ArrayList<Balance>());
					EntityTransaction tx_upd_empresa = PerThreadEntityManagers.getEntityManager().getTransaction();
					if(!tx_upd_empresa.isActive())
						tx_upd_empresa.begin();
					entityManager.persist(empresa_bd.get());
					tx_upd_empresa.commit();
				}

				for (Balance unBalance : empresaBatch.getBalances()){
					balances_bd = empresa_bd.get().getBalances();
					reemplazarSiexisteBalance(unBalance, balances_bd, id);
				}
			}
		}
		tx.commit();
	}
	
	private static int reemplazarSiexisteEmpresa(Empresa empresaNueva, List<Empresa> empresas){
		//Si tiene mismo nombre de Empresa lo pisa
		Optional<Empresa> empresaModif = empresas.stream().filter(empresa->empresa.seLlamaIgualQue(empresaNueva)).findFirst();
		if(empresaModif.isPresent()){
			empresaModif.get().setEmpresa_anioCreacion(empresaNueva.getEmpresa_anioCreacion());
			empresaModif.get().setEmpresa_nombre(empresaNueva.getEmpresa_nombre());
			return empresaModif.get().getEmpresa_id();
		}
		else{
			EntityTransaction tx = PerThreadEntityManagers.getEntityManager().getTransaction();
			if(!tx.isActive())
				tx.begin();
			entityManager.persist(empresaNueva);
			tx.commit();
			return empresaNueva.getEmpresa_id();
		}
	}
	
	private static void reemplazarSiexisteBalance(Balance balanceNuevo, List<Balance> balances, int empresa_id){
		//Si tiene mismo periodo de Balance lo pisa
		Optional<Balance> balanceModif = balances.stream()
											.filter(balance->balance.esDelMismoPeriodoQue(balanceNuevo))
											.findFirst();
		if(balanceModif.isPresent()){
			balanceModif = Optional.of(balanceNuevo);
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
