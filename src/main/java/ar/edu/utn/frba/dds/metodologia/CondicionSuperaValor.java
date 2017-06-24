package ar.edu.utn.frba.dds.metodologia;

import ar.edu.utn.frba.dds.modelo.Empresa;
import ar.edu.utn.frba.dds.modelo.Indicador;

public class CondicionSuperaValor implements CondicionTaxativa {
	private Indicador indicador;
	private Comparador comparador;
	private String inicioPeriodo;
	private String finPeriodo;
	private Double valorSuperar;
	
	@Override
	public Boolean deberiaInvertirEn(Empresa empresa) {
		// TODO Auto-generated method stub
		return null;
	}

	public Indicador getIndicador() {
		return indicador;
	}

	public void setIndicador(Indicador indicador) {
		this.indicador = indicador;
	}

	public Comparador getComparador() {
		return comparador;
	}

	public void setComparador(Comparador comparador) {
		this.comparador = comparador;
	}

	public String getInicioPeriodo() {
		return inicioPeriodo;
	}

	public void setInicioPeriodo(String inicioPeriodo) {
		this.inicioPeriodo = inicioPeriodo;
	}

	public String getFinPeriodo() {
		return finPeriodo;
	}

	public void setFinPeriodo(String finPeriodo) {
		this.finPeriodo = finPeriodo;
	}

	public Double getValorSuperar() {
		return valorSuperar;
	}

	public void setValorSuperar(Double valorSuperar) {
		this.valorSuperar = valorSuperar;
	}
	
	
}
