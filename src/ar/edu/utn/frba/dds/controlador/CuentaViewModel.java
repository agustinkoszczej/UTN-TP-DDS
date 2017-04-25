package ar.edu.utn.frba.dds.controlador;

import java.util.List;

import org.uqbar.commons.model.ObservableUtils;
import org.uqbar.commons.utils.Observable;

import ar.edu.utn.frba.dds.modelo.Balance;
import ar.edu.utn.frba.dds.modelo.Empresa;
import ar.edu.utn.frba.dds.servicio.ServicioCuentas;

@Observable
public class CuentaViewModel {
	private List<Empresa> empresas;
	private Empresa empresaSeleccionada;
	private ServicioCuentas unServicioDeCuentas;
	private List<Balance> balancesEmpresaSeleccionada;

	private Balance balaceSeleccionadoEmpresaSeleccionada;
	
	public CuentaViewModel(ServicioCuentas unServicioDeCuentas) {
		this.unServicioDeCuentas = unServicioDeCuentas;
		this.empresas = unServicioDeCuentas.obtenerEmpresas();
		this.empresaSeleccionada = empresas.get(0);
	}

	public List<Empresa> getEmpresas() {
		return empresas;
	}
	
	public Empresa getEmpresaSeleccionada() {
		return empresaSeleccionada;
	}

	public void setEmpresaSeleccionada(Empresa empresaSeleccionada) {
		this.empresaSeleccionada = empresaSeleccionada;
		ObservableUtils.firePropertyChanged(this, "balancesEmpresaSeleccionada");

	}
	
	public Balance getBalaceSeleccionadoEmpresaSeleccionada() {
		return balaceSeleccionadoEmpresaSeleccionada;
	}

	public void setBalaceSeleccionadoEmpresaSeleccionada(Balance balaceSeleccionadoEmpresaSeleccionada) {
		this.balaceSeleccionadoEmpresaSeleccionada = balaceSeleccionadoEmpresaSeleccionada;
	}

	public List<Balance> getBalancesEmpresaSeleccionada() {
		return empresaSeleccionada.getBalances();
	}
	
}
