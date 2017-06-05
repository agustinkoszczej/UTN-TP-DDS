package ar.edu.utn.frba.dds.vista;

import java.awt.Color;

import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.widgets.TextBox;
import org.uqbar.arena.windows.SimpleWindow;
import org.uqbar.arena.windows.WindowOwner;
import org.uqbar.lacar.ui.model.Action;

import ar.edu.utn.frba.dds.controlador.ConsultaIndicadorViewModel;
import ar.edu.utn.frba.dds.controlador.IndicadorViewModel;
import ar.edu.utn.frba.dds.servicio.ServicioCuentas;
import ar.edu.utn.frba.dds.servicio.ServicioIndicadores;

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
		// Se comento porque sigue sin hacer nada
		//ServicioCuentas unServicio = new ServicioCuentas();
		getModel().getSource().guardarIndicador();
		this.close();
		ServicioCuentas unServicioCuentas = new ServicioCuentas();
		ServicioIndicadores unServicioIndicadores = new ServicioIndicadores();
		ConsultaIndicadorWindow dialog = new ConsultaIndicadorWindow(this, new ConsultaIndicadorViewModel(unServicioCuentas,unServicioIndicadores));
		dialog.open();
	}

	// TODO: Ver si esta funcion se usa en algun lado!
	@SuppressWarnings("unused")
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
