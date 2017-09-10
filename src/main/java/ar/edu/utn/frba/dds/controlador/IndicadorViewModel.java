package ar.edu.utn.frba.dds.controlador;

import java.util.List;

import javax.swing.JOptionPane;

import org.uqbar.commons.model.ObservableUtils;
import org.uqbar.commons.utils.Observable;

import ar.edu.utn.frba.dds.expresion.Expresion;
import ar.edu.utn.frba.dds.modelo.Balance;
import ar.edu.utn.frba.dds.modelo.Empresa;
import ar.edu.utn.frba.dds.modelo.Indicador;
import ar.edu.utn.frba.dds.modelo.RepositorioIndicadores;
import ar.edu.utn.frba.dds.servicio.ServicioCuentas;
import ar.edu.utn.frba.dds.servicio.ServicioIndicadores;
import ar.edu.utn.frba.dds.util.ExpressionParser;

@Observable
public class IndicadorViewModel {
	//ConsultaIndicadorWindow
	private List<Empresa> empresas;
	private Empresa empresaSeleccionada;
	
	private List<Indicador> indicadores;
	private Indicador indicadorSeleccionado;
	
	private Balance balanceSeleccionado;
	private Integer valorIndicador;

	private String nombreIndicadorAIngresar;
	private String cadenaIndicadorAIngresar;

public String getNombreIndicadorAIngresar() {
		return nombreIndicadorAIngresar;
	}



	public void setNombreIndicadorAIngresar(String nombreIndicadorAIngresar) {
		this.nombreIndicadorAIngresar = nombreIndicadorAIngresar;
	}



	public String getCadenaIndicadorAIngresar() {
		return cadenaIndicadorAIngresar;
	}



	public void setCadenaIndicadorAIngresar(String cadenaIndicadorAIngresar) {
		this.cadenaIndicadorAIngresar = cadenaIndicadorAIngresar;
	}



public IndicadorViewModel(ServicioCuentas servicioCuentas, ServicioIndicadores servicioIndicadores) {
	this.empresas = servicioCuentas.obtenerEmpresas();
	this.indicadores = servicioIndicadores.obtenerIndicadores();
	
	this.empresaSeleccionada = empresas.get(0);
	this.balanceSeleccionado = empresaSeleccionada.getBalances().get(0);
	//RepositorioIndicadores.CargarYValidarIndicadores();
	//indicadoresRegistrados = RepositorioIndicadores.indicadores;
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
	
	
	public Indicador getIndicadorSeleccionado() {
		return indicadorSeleccionado;
	}
	
	public List<Indicador> getIndicadores() {
		return indicadores;
	}



	public void setIndicadores(List<Indicador> indicadores) {
		this.indicadores = indicadores;
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
		this.setValorIndicador(indicadorSeleccionado.calcular(empresaSeleccionada, balanceSeleccionado.getBalance_periodo()));
	}
	
	public void guardarIndicador() {
		ExpressionParser parser = new ExpressionParser();
			
		try {
				Expresion expresion = parser.buildExpressionFrom(cadenaIndicadorAIngresar);
				Indicador ind = new Indicador(nombreIndicadorAIngresar, expresion);
				RepositorioIndicadores.agregarYguardarIndicador(ind);
				this.indicadores = new ServicioIndicadores().obtenerIndicadores();
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null,"ERROR al cargar indicador"); 
				//e.printStackTrace();
			}
	}
	
	public void limpiarIngreso(){
		this.nombreIndicadorAIngresar = "";
		this.cadenaIndicadorAIngresar = "";
	}
}