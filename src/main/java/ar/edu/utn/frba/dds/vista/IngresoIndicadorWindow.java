package ar.edu.utn.frba.dds.vista;

import java.awt.Color;

import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.widgets.TextBox;
import org.uqbar.arena.windows.SimpleWindow;
import org.uqbar.arena.windows.WindowOwner;
import ar.edu.utn.frba.dds.controlador.IndicadorViewModel;

public class IngresoIndicadorWindow extends SimpleWindow<IndicadorViewModel> {
	
	public IngresoIndicadorWindow(WindowOwner parent, IndicadorViewModel model) {
		super(parent, model);
	}

	@Override
	protected void addActions(Panel actionsPanel) {
		new Button(actionsPanel).setCaption("Guardar").onClick(this::guardarExpresionRegular);
		new Button(actionsPanel).setCaption("Salir").onClick(this::close);
		
	}
	
	private void guardarExpresionRegular() {
		//no hace nada aun
		// Se comento porque sigue sin hacer nada
		getModel().getSource().guardarIndicador();
		this.close();
	}
	
	@Override
	protected void createFormPanel(Panel form) {
		this.setTitle("Indicadores");
		new Label(form)
		.setText("Indicador")
		.setBackground(Color.ORANGE);
		
		new Label(form).setText("Nombre indicador");
		TextBox textBoxNombreIndicador = new TextBox(form);
		textBoxNombreIndicador.bindValueToProperty("nombreIndicadorAIngresar");
		textBoxNombreIndicador.setWidth(200);
		
		new Label(form).setText("Expresion regular");
		TextBox textBoxCadena = new TextBox(form);
		textBoxCadena.bindValueToProperty("cadenaIndicadorAIngresar");
		textBoxCadena.setWidth(200);
		
		
	}

}
