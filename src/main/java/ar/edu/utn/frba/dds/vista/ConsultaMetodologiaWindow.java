package ar.edu.utn.frba.dds.vista;

import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.widgets.Selector;
import org.uqbar.arena.widgets.tables.Column;
import org.uqbar.arena.widgets.tables.Table;
import org.uqbar.arena.windows.SimpleWindow;
import org.uqbar.arena.windows.WindowOwner;

import ar.edu.utn.frba.dds.controlador.MetodologiaViewModel;
import ar.edu.utn.frba.dds.metodologia.Condicion;
import ar.edu.utn.frba.dds.metodologia.Metodologia;

public class ConsultaMetodologiaWindow extends SimpleWindow<MetodologiaViewModel> {

	public ConsultaMetodologiaWindow(WindowOwner parent, MetodologiaViewModel metodologiaViewModel) {
		super(parent, metodologiaViewModel);
	}

	@Override
	protected void addActions(Panel panel) {
		new Button(panel)
		.setCaption("Agregar Condicion")
		.onClick(this::abrirCondicionWindow);
		
		new Button(panel)
		.setCaption("Guardar Metodologia seleccionada")
		.onClick(this::guardarMetodologia);
		new Button(panel)
		.setCaption("Salir")
		.onClick(this::close)
		.setWidth(100);
	}
	
	private void abrirCondicionWindow(){
		AgregarCondicionWindow dialog = new AgregarCondicionWindow(this, getModel().getSource());
		dialog.open();

	}
	
	private void guardarMetodologia(){
		this.getModel().getSource().guardarMetodologia();
	}

	@Override
	protected void createFormPanel(Panel form) {
		this.setTitle("Consulta Metodologias");
		
		new Label(form).setText("Metodologia");
		
		Selector<Metodologia> selectorMetodologia = new Selector<Metodologia>(form).allowNull(true);
		
		selectorMetodologia.bindValueToProperty("metodologiaSeleccionada");
		selectorMetodologia.bindItemsToProperty("metodologias").adaptWith(Metodologia.class, "nombre");
		selectorMetodologia.setWidth(280);
		

		new Label(form).setText("Condiciones de la metodologia");
		Table<Condicion> tableCondiciones = new Table<Condicion>(form, Condicion.class);
		tableCondiciones.setHeight(400);
		
		tableCondiciones.bindItemsToProperty("condicionesMetodologia");
		tableCondiciones.bindValueToProperty("condicionSeleccionada");
		
	
		Column<Condicion> columnaNombre = new Column<Condicion>(tableCondiciones);
		columnaNombre.setTitle("nombre").setWeight(32);
		columnaNombre.bindContentsToProperty("nombreCondicion");

	}

}
