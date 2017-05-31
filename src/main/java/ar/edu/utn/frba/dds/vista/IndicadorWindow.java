package ar.edu.utn.frba.dds.vista;

import java.awt.Color;

import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.widgets.Selector;
import org.uqbar.arena.widgets.TextBox;
import org.uqbar.arena.widgets.tables.Column;
import org.uqbar.arena.widgets.tables.Table;
import org.uqbar.arena.windows.SimpleWindow;
import org.uqbar.arena.windows.WindowOwner;
import org.uqbar.lacar.ui.model.Action;

import ar.edu.utn.frba.dds.controlador.CuentaViewModel;
import ar.edu.utn.frba.dds.controlador.IndicadorViewModel;
import ar.edu.utn.frba.dds.modelo.Balance;
import ar.edu.utn.frba.dds.modelo.Empresa;
import ar.edu.utn.frba.dds.servicio.ServicioCuentas;

public class IndicadorWindow extends SimpleWindow<IndicadorViewModel> {
	
	public IndicadorWindow(WindowOwner parent, IndicadorViewModel cuentaModel) {
		super(parent, cuentaModel);
	}

	@Override
	protected void addActions(Panel actionsPanel) {
		new Button(actionsPanel).setCaption("Guardar").onClick(this::guardarExpresionRegular);
		new Button(actionsPanel).setCaption("Salir").onClick(this::close);
		
	}
	
	private void guardarExpresionRegular() {
		//no hace nada aun
		getModel().getSource().guardarIndicador();
	}

	private Action createCuentaWindow() {
		// TODO Auto-generated method stub
		ServicioCuentas unServicio = new ServicioCuentas();
		//IndicadorWindow ventanaInd = new IndicadorWindow(this, new IndicadorViewModel(unServicio));
		//ventanaInd.open();
		
		return this::close;
	}
	
	@Override
	protected void createFormPanel(Panel form) {
		this.setTitle("Indicadores");
		new Label(form)
		.setText("Indicador")
		.setBackground(Color.ORANGE);
		
		new Label(form).setText("Nombre indicador");
		TextBox textBoxNombreIndicador = new TextBox(form);
		textBoxNombreIndicador.bindValueToProperty("nombreIndicador");
		textBoxNombreIndicador.setWidth(200);
		
		new Label(form).setText("Expresion regular");
		TextBox textBoxCadena = new TextBox(form);
		textBoxCadena.bindValueToProperty("cadena");
		textBoxCadena.setWidth(200);
		
		
	}

}
