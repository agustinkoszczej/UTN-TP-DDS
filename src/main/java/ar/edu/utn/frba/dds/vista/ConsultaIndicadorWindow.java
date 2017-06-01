package ar.edu.utn.frba.dds.vista;

import java.awt.Color;

import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.widgets.Selector;
import org.uqbar.arena.widgets.tables.Column;
import org.uqbar.arena.widgets.tables.Table;
import org.uqbar.arena.windows.SimpleWindow;
import org.uqbar.arena.windows.WindowOwner;

import ar.edu.utn.frba.dds.controlador.ConsultaIndicadorViewModel;
import ar.edu.utn.frba.dds.controlador.IndicadorViewModel;
import ar.edu.utn.frba.dds.modelo.Balance;
import ar.edu.utn.frba.dds.modelo.Empresa;
import ar.edu.utn.frba.dds.modelo.Indicador;
import ar.edu.utn.frba.dds.modelo.RepositorioIndicadores;

public class ConsultaIndicadorWindow extends SimpleWindow<ConsultaIndicadorViewModel> {
	
	public ConsultaIndicadorWindow(WindowOwner parent, ConsultaIndicadorViewModel model) {
		super(parent, model);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void addActions(Panel panel) {
		// TODO Auto-generated method stub
		new Button(panel)
		.setCaption("Ingresar indicador")
		.onClick(this::abrirIndicadorWindow);
	}

	public void abrirIndicadorWindow() {
		IndicadorWindow dialog = new IndicadorWindow(this, new IndicadorViewModel());
				//ConsultaIndicadorWindow dialog = new ConsultaIndicadorWindow(this, new ConsultaIndicadorViewModel(unServicioCuentas,unServicioIndicadores));

		dialog.open();
	}
	
	@Override
	protected void createFormPanel(Panel form) {
		// TODO Auto-generated method stub
		this.setTitle("Consulta Indicadores");
		
		new Label(form).setText("Empresa");
		
		Selector<Empresa> selectorEmpresa = new Selector<Empresa>(form).allowNull(true);
		selectorEmpresa.bindValueToProperty("empresaSeleccionada");
		selectorEmpresa.bindItemsToProperty("empresas").adaptWith(Empresa.class, "nombre");
		selectorEmpresa.setWidth(280);
		
		new Label(form).setText("Periodo");
		
		Selector<Balance> selectorPeriodo = new Selector<Balance>(form).allowNull(true);
		selectorPeriodo.bindValueToProperty("balanceSeleccionado");
		selectorPeriodo.bindItemsToProperty("balancesEmpresaSeleccionada").adaptWith(Balance.class, "periodo");
		selectorPeriodo.setWidth(280);
		
		Table<Indicador> tableIndicadores = new Table<Indicador>(form, Indicador.class);
		tableIndicadores.setHeight(400);
		
		tableIndicadores.bindItemsToProperty("repositorioIndicadores");
		tableIndicadores.bindValueToProperty("indicadorSeleccionado");
		
	
		Column<Indicador> columnaNombre = new Column<Indicador>(tableIndicadores);
		columnaNombre.setTitle("nombre");
		columnaNombre.bindContentsToProperty("nombreIndicador");
		
		Column<Indicador> columnaExpresion = new Column<Indicador>(tableIndicadores);
		columnaExpresion.setTitle("expresion");
		columnaExpresion.bindContentsToProperty("expresion");
		
	}


	

	
}
