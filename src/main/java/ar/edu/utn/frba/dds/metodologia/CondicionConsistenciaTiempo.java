package ar.edu.utn.frba.dds.metodologia;

import ar.edu.utn.frba.dds.modelo.Empresa;
import ar.edu.utn.frba.dds.modelo.Indicador;

public class CondicionConsistenciaTiempo implements CondicionTaxativa {

	private Indicador indicador;
	private Comparador comparador;
	private int inicioConsistencia;
	private int finConsistencia;
	
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

	public int getInicioConsistencia() {
		return inicioConsistencia;
	}

	public void setInicioConsistencia(int inicioConsistencia) {
		this.inicioConsistencia = inicioConsistencia;
	}

	public int getFinConsistencia() {
		return finConsistencia;
	}

	public void setFinConsistencia(int finConsistencia) {
		this.finConsistencia = finConsistencia;
	}
	
	

}
