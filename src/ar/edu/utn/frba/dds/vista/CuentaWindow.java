package ar.edu.utn.frba.dds.vista;

import org.uqbar.arena.layout.ColumnLayout;
import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.widgets.Selector;
import org.uqbar.arena.widgets.tables.Column;
import org.uqbar.arena.widgets.tables.Table;
//import org.uqbar.arena.widgets.tables.Column;
//import org.uqbar.arena.widgets.tables.Table;
import org.uqbar.arena.windows.SimpleWindow;
import org.uqbar.arena.windows.WindowOwner;
import org.uqbar.commons.model.ObservableUtils;

import ar.edu.utn.frba.dds.controlador.CuentaViewModel;
import ar.edu.utn.frba.dds.modelo.Balance;
import ar.edu.utn.frba.dds.modelo.Empresa;

public class CuentaWindow extends SimpleWindow<CuentaViewModel> {
	
	public CuentaWindow(WindowOwner parent, CuentaViewModel cuentaModel) {
		super(parent, cuentaModel);
	}

	@Override
	protected void addActions(Panel actionsPanel) {
		new Button(actionsPanel).setCaption("Salir").onClick(this::close);
	}

	protected void actualizarBalances() {
		ObservableUtils.firePropertyChanged(getModel().getSource(), "balancesEmpresaSeleccionada");
		return;
	}
	
	@Override
	protected void createFormPanel(Panel form) {
		this.setTitle("Cuentas");
//		Panel form = new Panel(mainPanel);
		//form.setLayout(new ColumnLayout(2));
//		mainPanel.set

		//new Label(form).setText("Datos");
		new Label(form).setText("Empresa");
		Selector<Empresa> selectorEmpresa = new Selector<Empresa>(form).allowNull(true);
		selectorEmpresa.bindValueToProperty("empresaSeleccionada");
		selectorEmpresa.bindItemsToProperty("empresas").adaptWith(Empresa.class, "nombre");
		selectorEmpresa.setWidth(200);
		selectorEmpresa.onSelection(this::actualizarBalances);
				
		// Tabla de Balances
		Table<Balance> tableBalances = new Table<Balance>(form, Balance.class);
		tableBalances.setHeight(300);
		tableBalances.setWidth(600);
		tableBalances.bindItemsToProperty("balancesEmpresaSeleccionada");
		tableBalances.bindValueToProperty("balaceSeleccionadoEmpresaSeleccionada");
		
		Column<Balance> columnaPeriodo = new Column<Balance>(tableBalances);
		columnaPeriodo.setTitle("Tarea");
		columnaPeriodo.bindContentsToProperty("periodo");
		
		Column<Balance> columnaTipoCuenta = new Column<Balance>(tableBalances);
		columnaTipoCuenta.setTitle("Tarea");
		columnaTipoCuenta.bindContentsToProperty("tipoCuenta");

		
	}

}
