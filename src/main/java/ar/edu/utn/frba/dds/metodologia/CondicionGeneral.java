package ar.edu.utn.frba.dds.metodologia;

import ar.edu.utn.frba.dds.modelo.Empresa;

public class CondicionGeneral extends CondicionTaxativa {

	private TipoOperacion tipoOperacion;
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
