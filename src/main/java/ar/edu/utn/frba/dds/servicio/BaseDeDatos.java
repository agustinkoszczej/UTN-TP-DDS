package ar.edu.utn.frba.dds.servicio;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;

import ar.edu.utn.frba.dds.expresion.Expresion;
import ar.edu.utn.frba.dds.metodologia.Condicion;
import ar.edu.utn.frba.dds.metodologia.Metodologia;
import ar.edu.utn.frba.dds.modelo.Empresa;
import ar.edu.utn.frba.dds.modelo.Indicador;
import ar.edu.utn.frba.dds.modelo.IndicadorPrecalculado;
import ar.edu.utn.frba.dds.modelo.User;
import ar.edu.utn.frba.dds.repositorios.RepositorioIndicadores;
import ar.edu.utn.frba.dds.util.ExpressionParser;

public class BaseDeDatos implements Servicio {

	public static boolean bdEnabled = true; //Cambiar atributo a False si se quiere cargar desde JSON
	private static EntityManager entityManager;

	public BaseDeDatos (EntityManager entityManager){
		this.entityManager = entityManager;
	}

	public BaseDeDatos (){
		if (this.entityManager != null){
			if (this.entityManager.isOpen())
					this.entityManager.close();
		}
		this.entityManager = PerThreadEntityManagers.getEntityManager();
		this.entityManager.clear();
		cargarValoresIndicadoresPrecalculados();
	}

	
	public void cargarValoresIndicadoresPrecalculados(){
		EntityTransaction tx = PerThreadEntityManagers.getEntityManager().getTransaction();
		tx.begin();
		entityManager
				.createNativeQuery("INSERT INTO IndicadorPrecalculado (indPrecal_indicador, indPrecal_empresa, indPrecal_periodo) (SELECT DISTINCT indicador_id, empresa_id, balance_periodo "
				+ "FROM Empresa JOIN Balance ON (empresa_id=balance_empresa), Indicador "
				+ "WHERE NOT EXISTS (SELECT 1 FROM IndicadorPrecalculado WHERE indPrecal_empresa=empresa_id AND indPrecal_indicador=indicador_id AND indPrecal_periodo=balance_periodo))").executeUpdate();
		tx.commit();
		
		List<IndicadorPrecalculado> indicadoresPrecalculados =  
        entityManager.createQuery("FROM IndicadorPrecalculado WHERE indPrecal_valor IS NULL").getResultList();
		
		for (int i =0; i<indicadoresPrecalculados.size(); i++){
			tx.begin();
			try {
				indicadoresPrecalculados.get(i).calcularValor(entityManager);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			tx.commit();
		}
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

	public static Integer obtenerValorIndicadorPrecalculado(Indicador indicador, Empresa empresa, String periodo){
		Object valor = entityManager.createNativeQuery("SELECT indPrecal_valor FROM IndicadorPrecalculado WHERE indPrecal_empresa = "
				+empresa.getEmpresa_id()+" AND indPrecal_indicador = "+indicador.getIndicador_id()+" AND indPrecal_periodo = "+periodo).getSingleResult();
		Double num = Double.parseDouble(valor.toString());
		return num.intValue();
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
	
	public Boolean login(String username, String password){
		List<User> loggedUsers = entityManager.createQuery("FROM User WHERE user_username = '" + username + "' AND user_password = MD5('" + password + "')").getResultList();
		return !loggedUsers.isEmpty();
	}
	
	public void guardarCondicion(Condicion unaCondicion) throws IOException{
			EntityTransaction tx = PerThreadEntityManagers.getEntityManager().getTransaction();
			tx.begin();
			Condicion unaCondi = unaCondicion;
			entityManager.persist(unaCondi);
			tx.commit();
		}
	
	public void guardarMetodologia(Metodologia unaMetodologia) throws IOException{
			EntityTransaction tx = PerThreadEntityManagers.getEntityManager().getTransaction();
			tx.begin();
			Metodologia unaMeto = unaMetodologia;
			entityManager.persist(unaMeto);
			tx.commit();
	}
	
	public void guardarIndicador(Indicador indicador) throws IOException{
			indicador.setIndicador_expresion(indicador.formula());
			EntityTransaction tx = PerThreadEntityManagers.getEntityManager().getTransaction();
			tx.begin();
			Indicador ind = indicador;
			entityManager.persist(ind);
			tx.commit();
	}

}