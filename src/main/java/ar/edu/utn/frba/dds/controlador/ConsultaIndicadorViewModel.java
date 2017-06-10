package ar.edu.utn.frba.dds.controlador;

import java.util.Collection;
import java.util.List;

import org.uqbar.commons.model.ObservableUtils;
import org.uqbar.commons.utils.Observable;

import ar.edu.utn.frba.dds.modelo.Balance;
import ar.edu.utn.frba.dds.modelo.Empresa;
import ar.edu.utn.frba.dds.modelo.Indicador;
import ar.edu.utn.frba.dds.modelo.RepositorioIndicadores;
import ar.edu.utn.frba.dds.servicio.ServicioCuentas;
import ar.edu.utn.frba.dds.servicio.ServicioIndicadores;

@Observable
public class ConsultaIndicadorViewModel {
	private List<Empresa> empresas;
	private Empresa empresaSeleccionada;
	private Collection<Indicador> indicadoresRegistrados;
	private Indicador indicadorSeleccionado;
	@SuppressWarnings("unused")
	private ServicioCuentas unServicioCuentas;
	@SuppressWarnings("unused")
	private ServicioIndicadores unServicioIndicadores;
	private Balance balanceSeleccionado;
	
	private Integer valorIndicador;
	


public ConsultaIndicadorViewModel(ServicioCuentas unServicioCuentas, ServicioIndicadores unServicioIndicadores) {
	
	this.unServicioCuentas = unServicioCuentas;
	this.unServicioIndicadores = unServicioIndicadores;
	empresas = unServicioCuentas.obtenerEmpresas();
	this.empresaSeleccionada = empresas.get(0);
	this.balanceSeleccionado = empresaSeleccionada.getBalances().get(0);
	RepositorioIndicadores.CargarYValidarIndicadores();
	indicadoresRegistrados = RepositorioIndicadores.indicadores;
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
	
	public Collection<Indicador> getRepositorioIndicadores() {
		return indicadoresRegistrados;
	}
	
	public Indicador getIndicadorSeleccionado() {
		return indicadorSeleccionado;
	}
	
	public void setIndicadorSeleccionado(Indicador unIndicador) {
		this.indicadorSeleccionado = unIndicador;
	}
	
	
	public Integer getValorIndicador(){
		return valorIndicador;
	}
	public void setValorIndicador(Integer valorIndicador){
		this.valorIndicador = valorIndicador;
	}
	
	public void aplicarIndicador() throws Exception {
		this.setValorIndicador(indicadorSeleccionado.calcular(empresaSeleccionada, balanceSeleccionado.getPeriodo()));
	}
}