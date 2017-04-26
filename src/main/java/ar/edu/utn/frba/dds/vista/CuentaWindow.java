package main.java.ar.edu.utn.frba.dds.vista;

import java.awt.Color;

import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.widgets.Selector;
import org.uqbar.arena.widgets.tables.Column;
import org.uqbar.arena.widgets.tables.Table;
import org.uqbar.arena.windows.SimpleWindow;
import org.uqbar.arena.windows.WindowOwner;

import main.java.ar.edu.utn.frba.dds.controlador.CuentaViewModel;
import main.java.ar.edu.utn.frba.dds.modelo.Balance;
import main.java.ar.edu.utn.frba.dds.modelo.Empresa;

public class CuentaWindow extends SimpleWindow<CuentaViewModel> {
	
	public CuentaWindow(WindowOwner parent, CuentaViewModel cuentaModel) {
		super(parent, cuentaModel);
	}

	@Override
	protected void addActions(Panel actionsPanel) {
		new Button(actionsPanel).setCaption("Salir").onClick(this::close);
	}

	@Override
	protected void createFormPanel(Panel form) {
		this.setTitle("Cuentas");
		new Label(form)
		.setText("Empresa")
		.setBackground(Color.ORANGE);
		
		Selector<Empresa> selectorEmpresa = new Selector<Empresa>(form).allowNull(true);
		selectorEmpresa.bindValueToProperty("empresaSeleccionada");
		selectorEmpresa.bindItemsToProperty("empresas").adaptWith(Empresa.class, "nombre");
		selectorEmpresa.setWidth(270);
				
		// Tabla de Balances
		Table<Balance> tableBalances = new Table<Balance>(form, Balance.class);
		tableBalances.setHeight(300);
		tableBalances.bindItemsToProperty("balancesEmpresaSeleccionada");
		tableBalances.bindValueToProperty("balanceSeleccionadoEmpresaSeleccionada");
		
		Column<Balance> columnaPeriodo = new Column<Balance>(tableBalances);
		columnaPeriodo.setTitle("Periodo");
		columnaPeriodo.bindContentsToProperty("periodo");

		Column<Balance> columnaFrecuencia = new Column<Balance>(tableBalances);
		columnaFrecuencia.setTitle("Frecuencia");
		columnaFrecuencia.bindContentsToProperty("frecuencia");

		
		Column<Balance> columnaValor = new Column<Balance>(tableBalances);
		columnaValor.setTitle("Valor");
		columnaValor.bindContentsToProperty("valor").setTransformer(new DoubleStringTransformer());
		
		Column<Balance> columnaTipoCuenta = new Column<Balance>(tableBalances);
		columnaTipoCuenta.setTitle("Tipo de Cuenta");
		columnaTipoCuenta.bindContentsToProperty("tipoCuenta");
		
		//Valores Cuentas

		new Label(form)
		.setText("Valor Balances:")
		.setBackground(Color.GREEN);
		Label valorTotal = new Label(form);
		valorTotal.bindValueToProperty("valorBalances").setTransformer(new DoubleStringValueTransformer());
		
	}

}
