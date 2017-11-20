package ar.edu.utn.frba.dds.modelo;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import ar.edu.utn.frba.dds.expresion.Expresion;
import ar.edu.utn.frba.dds.util.ExpressionParser;

@Entity
public class IndicadorPrecalculado {
	@Id @GeneratedValue
	private int indPrecal_id;
	@OneToOne(fetch=FetchType.EAGER) @JoinColumn(name="indPrecal_indicador")
	private Indicador indPrecal_indicador;
	
	@OneToOne(fetch=FetchType.EAGER) @JoinColumn(name="indPrecal_empresa")
	private Empresa indPrecal_empresa;
	
	private String indPrecal_periodo;
	
	private Integer indPrecal_valor;
	
	public IndicadorPrecalculado() {
		super();
	}

	public IndicadorPrecalculado(Indicador ind, Empresa emp, String periodo, Integer valor) throws Exception{
		this.indPrecal_indicador = ind;
		this.indPrecal_empresa = emp;
		this.indPrecal_periodo = periodo;
		this.indPrecal_valor = valor;
	}

	public int getIndPrecal_id() {
		return indPrecal_id;
	}

	public void setIndPrecal_id(int indPrecal_id) {
		this.indPrecal_id = indPrecal_id;
	}

	public Indicador getIndPrecal_indicador() {
		return indPrecal_indicador;
	}

	public void setIndPrecal_indicador(Indicador indPrecal_indicador) {
		this.indPrecal_indicador = indPrecal_indicador;
	}

	public Empresa getIndPrecal_empresa() {
		return indPrecal_empresa;
	}

	public void setIndPrecal_empresa(Empresa indPrecal_empresa) {
		this.indPrecal_empresa = indPrecal_empresa;
	}

	public String getIndPrecal_periodo() {
		return indPrecal_periodo;
	}

	public void setIndPrecal_periodo(String indPrecal_periodo) {
		this.indPrecal_periodo = indPrecal_periodo;
	}

	public Integer getIndPrecal_valor() {
		return indPrecal_valor;
	}

	public void setIndPrecal_valor(Integer indPrecal_valor) {
		this.indPrecal_valor = indPrecal_valor;
	}
	
	public void calcularValor(EntityManager entityManager) throws Exception{
		ExpressionParser parser = new ExpressionParser();
		
		indPrecal_empresa.setBalances(
				(entityManager.createQuery(
						"FROM Balance "
						+ "WHERE balance_empresa = "
						+ indPrecal_empresa.getEmpresa_id()).
						getResultList())
		);
		
		try {
			Expresion expresion = parser.buildExpressionFrom(indPrecal_indicador.getIndicador_expresion());
			indPrecal_indicador.setExpresion(expresion);
			indPrecal_indicador.inicializarIndicadoresCorruptos();
			//RepositorioIndicadores.agregarIndicador(indPrecal_indicador);
			} catch (Exception e) {
				System.out.println("ERROR al cargar indicador"); 
			}
		this.setIndPrecal_valor(this.indPrecal_indicador.calcular(this.indPrecal_empresa, this.indPrecal_periodo));
		indPrecal_indicador.setExpresion(null);
		indPrecal_empresa.setBalances(null);
	}
	
}
