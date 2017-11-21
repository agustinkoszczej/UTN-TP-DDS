package ar.edu.utn.frba.dds.util;

import java.util.List;

import javax.persistence.EntityTransaction;

import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;

import ar.edu.utn.frba.dds.modelo.Balance;
import ar.edu.utn.frba.dds.modelo.Empresa;
import ar.edu.utn.frba.dds.servicio.ServicioCuentas;

public class BatchCuentas {
	//TODO falta que se persista, y validar que si ya existe lo updateo en vez de insertarlo de nuevo
	// y tambien de borrar el archivo cada vez que lo lea, para asi evitar que lea lo mismo 2 veces (como dice enunciado)
	public static void verificarCuentas(){
		ServicioCuentas batch = new ServicioCuentas();
		EntityTransaction tx = PerThreadEntityManagers.getEntityManager().getTransaction();
		tx.begin();
		List<Empresa> empresas = batch.obtenerEmpresas();
		tx.commit();
		for (int i=0;i<empresas.size();i++){
			tx.begin();
			List<Balance> balances = empresas.get(i).getBalances();
			tx.commit();
		}
	}
}
