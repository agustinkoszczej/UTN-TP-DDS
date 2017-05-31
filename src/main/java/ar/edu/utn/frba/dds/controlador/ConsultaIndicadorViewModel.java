package ar.edu.utn.frba.dds.controlador;

import java.io.IOException;
import java.util.List;

import org.uqbar.commons.model.ObservableUtils;
import org.uqbar.commons.utils.Observable;

import ar.edu.utn.frba.dds.modelo.Balance;
import ar.edu.utn.frba.dds.modelo.Empresa;
import ar.edu.utn.frba.dds.modelo.Indicador;
import ar.edu.utn.frba.dds.modelo.RepositorioIndicadores;
import ar.edu.utn.frba.dds.servicio.ServicioCuentas;
import ar.edu.utn.frba.dds.servicio.ServicioIndicadores;
import ar.edu.utn.frba.dds.vista.ConsultaIndicadorWindow;
import ar.edu.utn.frba.dds.vista.IndicadorWindow;

@Observable
public class ConsultaIndicadorViewModel {
	private List<Empresa> empresas;
	private Empresa empresaSeleccionada;
	private Indicador unIndicador;
	private List<Indicador> repositorioIndicadores;
	private Indicador indicadorSeleccionado;
	private ServicioCuentas unServicioCuentas;
	private ServicioIndicadores unServicioIndicadores;
	private List<Balance> balancesEmpresaSeleccionada;
	private Balance balanceSeleccionado;
	


public ConsultaIndicadorViewModel(ServicioCuentas unServicioCuentas, ServicioIndicadores unServicioIndicadores) {
	
	unIndicador = new Indicador(null, null);
	this.unServicioCuentas = unServicioCuentas;
	this.unServicioIndicadores = unServicioIndicadores;
	empresas = unServicioCuentas.obtenerEmpresas();
	this.empresaSeleccionada = empresas.get(0);
	this.balanceSeleccionado = empresaSeleccionada.getBalances().get(0);
	repositorioIndicadores = unServicioIndicadores.obtenerIndicadores();
	}
	
	

	public List<Empresa> getEmpresas() {
		return empresas;
	}

	public Empresa getEmpresaSeleccionada() {
		this.balanceSeleccionado = empresaSeleccionada.getBalances().get(0);
		return empresaSeleccionada;
	}
	
	public void setEmpresaSeleccionada(Empresa empresaSeleccionada) {
		this.empresaSeleccionada = empresaSeleccionada;
		ObservableUtils.firePropertyChanged(this, "balancesEmpresaSeleccionada");
	}
	
	public List<Balance> getBalancesEmpresaSeleccionada() {
		
		return empresaSeleccionada.getBalances();
		
	}
	
	public Balance getBalanceSeleccionado() {
		return balanceSeleccionado;
	}

	public void setBalanceSeleccionado(Balance balanceSeleccionado) {
		this.balanceSeleccionado = balanceSeleccionado;
	}
	
	public List<Indicador> getRepositorioIndicadores() {
		return repositorioIndicadores;
	}
	
	public Indicador getIndicadorSeleccionado() {
		return indicadorSeleccionado;
	}
	
	public void setIndicadorSeleccionado(Indicador unIndicador) {
		this.indicadorSeleccionado = unIndicador;
	}
}