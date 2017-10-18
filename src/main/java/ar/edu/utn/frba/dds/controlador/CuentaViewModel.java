package  ar.edu.utn.frba.dds.controlador;

import java.util.List;

import org.uqbar.commons.model.ObservableUtils;
import org.uqbar.commons.utils.Observable;

import  ar.edu.utn.frba.dds.modelo.Balance;
import  ar.edu.utn.frba.dds.modelo.Empresa;
import  ar.edu.utn.frba.dds.servicio.ServicioCuentas;
import ar.edu.utn.frba.dds.util.ProveedorAcceso;

@Observable
public class CuentaViewModel {
	private List<Empresa> empresas;
	private Empresa empresaSeleccionada;
	@SuppressWarnings("unused")
	private ServicioCuentas unServicioDeCuentas;
	@SuppressWarnings("unused")
	private String valorBalances;

	private Balance balanceSeleccionadoEmpresaSeleccionada;
	private ProveedorAcceso servicio;
	
	public CuentaViewModel(ProveedorAcceso proveedor) {
		this.servicio = proveedor;
		this.empresas = servicio.obtenerEmpresas();
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
		ObservableUtils.firePropertyChanged(this, "valorBalances");
	}
	
	public Balance getBalanceSeleccionadoEmpresaSeleccionada() {
		return balanceSeleccionadoEmpresaSeleccionada;
	}

	public void setBalanceSeleccionadoEmpresaSeleccionada(Balance balaceSeleccionadoEmpresaSeleccionada) {
		this.balanceSeleccionadoEmpresaSeleccionada = balaceSeleccionadoEmpresaSeleccionada;
	}

	public List<Balance> getBalancesEmpresaSeleccionada() {
		return empresaSeleccionada.getBalances();
	}
	
	public Double getValorBalances(){
		return empresaSeleccionada.getValorBalances();
	}
	public void setValorBalances(String valorBalances){
		this.valorBalances = valorBalances;
	}	
}
