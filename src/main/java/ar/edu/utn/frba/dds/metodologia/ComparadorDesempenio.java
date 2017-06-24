package ar.edu.utn.frba.dds.metodologia;

import ar.edu.utn.frba.dds.modelo.Empresa;
import ar.edu.utn.frba.dds.modelo.Indicador;

public class ComparadorDesempenio implements CondicionComparativa{

	private Indicador indicador;
	private Comparador comparador;
	private String periodoComparacion;
	
	@Override
	public Empresa cualEmpresaInvertir(Empresa emp1, Empresa emp2) {
		// TODO Auto-generated method stub
		return Empresa;
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

	public String getPeriodoComparacion() {
		return periodoComparacion;
	}

	public void setPeriodoComparacion(String periodoComparacion) {
		this.periodoComparacion = periodoComparacion;
	}
	
	

}
