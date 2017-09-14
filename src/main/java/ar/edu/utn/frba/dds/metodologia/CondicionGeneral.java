package ar.edu.utn.frba.dds.metodologia;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

import ar.edu.utn.frba.dds.modelo.Empresa;

@Entity
public class CondicionGeneral extends CondicionTaxativa {

	@OneToOne
	private TipoOperacion tipoOperacion;
	@Column
	private double valorASuperar;
	

	public TipoOperacion getTipoOperacion() {
		return tipoOperacion;
	}

	public void setTipoOperacion(TipoOperacion operacion) {
		this.tipoOperacion = operacion;
	}

	public double getValorASuperar() {
		return valorASuperar;
	}

	public void setValorASuperar(double valorASuperar) {
		this.valorASuperar = valorASuperar;
	}


	@Override
	public Boolean deberiaInvertirEn(Empresa empresa) {
		double valor = 0;
		valor = tipoOperacion.valor(empresa, this);
		return cumpleCondicion(valor, valorASuperar);
	}
	private Boolean cumpleCondicion(double valor, double valorASuperar) {
		return comparador.cumpleCondicion(valor, valorASuperar);
	}
}
