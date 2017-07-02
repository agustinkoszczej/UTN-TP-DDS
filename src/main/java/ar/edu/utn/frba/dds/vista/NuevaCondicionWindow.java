package ar.edu.utn.frba.dds.vista;

import java.awt.Checkbox;

import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.widgets.RadioSelector;
import org.uqbar.arena.widgets.Selector;
import org.uqbar.arena.widgets.TextBox;
import org.uqbar.arena.windows.SimpleWindow;
import org.uqbar.arena.windows.WindowOwner;

import ar.edu.utn.frba.dds.controlador.CondicionViewModel;
import ar.edu.utn.frba.dds.metodologia.Condicion;
import ar.edu.utn.frba.dds.metodologia.Metodologia;

public class NuevaCondicionWindow extends SimpleWindow<CondicionViewModel>  {

	public NuevaCondicionWindow(WindowOwner parent, CondicionViewModel model) {
		super(parent, model);
	}

	



	@Override
	protected void addActions(Panel panel) {
		// TODO Auto-generated method stub
		new Button(panel)
		.setCaption("Guardar")
		.onClick(this::guardarCondicion);
		new Button(panel)
		.setCaption("Cancelar")
		.onClick(this::close)
		.setWidth(100);
	}

	private void guardarCondicion(){
		this.getModel().getSource().guardarCondicion();
	}

	@Override
	protected void createFormPanel(Panel form) {
		// TODO Auto-generated method stub
		
		new Label(form).setText("Nombre de la condicion");
		TextBox textBoxNombreCondicion = new TextBox(form);
		textBoxNombreCondicion.bindValueToProperty("nombreCondicion");
		textBoxNombreCondicion.setWidth(200);
		
		Selector<String> selectorConsulta = new Selector<String>(form).allowNull(true);
		selectorConsulta.bindValueToProperty("condicionSeleccionada");
		selectorConsulta.bindItemsToProperty("condiciones").adaptWith(Condicion.class, "claseCondicion");
		
		//Checkbox<
		//RadioSelector<String> selectorPeriodo = new RadioSelector<String>(form);
		//selectorPeriodo.bindValueToProperty("periodo");
	}
}
