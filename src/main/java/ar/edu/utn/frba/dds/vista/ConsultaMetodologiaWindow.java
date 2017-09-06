package ar.edu.utn.frba.dds.vista;

import org.uqbar.arena.layout.ColumnLayout;
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
import ar.edu.utn.frba.dds.modelo.Empresa;

public class ConsultaMetodologiaWindow extends SimpleWindow<MetodologiaViewModel> {

	public ConsultaMetodologiaWindow(WindowOwner parent, MetodologiaViewModel metodologiaViewModel) {
		super(parent, metodologiaViewModel);
	}

	@Override
	protected void addActions(Panel panel) {
		Panel doble = new Panel(panel);
		doble.setLayout(new ColumnLayout(2));
		
		new Button(doble)
		.setCaption("Guardar Metodologia seleccionada")
		.onClick(this::guardarMetodologia);
		
		new Button(doble)
		.setCaption("Aplicar metodologia a empresas")
		.onClick(this::aplicarMetodologia);

		new Button(doble)
		.setCaption("Agregar Condicion")
		.onClick(this::abrirCondicionWindow);
		
		new Button(doble)
		.setCaption("Salir")
		.onClick(this::close);
	}
	
	private void abrirCondicionWindow(){
		AgregarCondicionWindow dialog = new AgregarCondicionWindow(this, getModel().getSource());
		dialog.open();
	}
	
	private void aplicarMetodologia(){
		this.getModel().getSource().aplicarMetodologia();
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
		selectorMetodologia.setWidth(80);
		
		new Label(form).setText("Condiciones de la metodologia");
		Table<Condicion> tableCondiciones = new Table<Condicion>(form, Condicion.class);
		tableCondiciones.setHeight(200).setWidth(80);
		tableCondiciones.bindItemsToProperty("condicionesMetodologia");
		tableCondiciones.bindValueToProperty("condicionSeleccionada");
		
		Column<Condicion> columnaNombre = new Column<Condicion>(tableCondiciones);
		columnaNombre.setTitle("nombre");
		columnaNombre.bindContentsToProperty("nombreCondicion");

		new Label(form).setText("Lista empresas a invertir, de mejor a peor");
		Table<Empresa> empresasMetodologia = new Table<Empresa>(form, Empresa.class);
		empresasMetodologia.setHeight(200).setWidth(80);
		empresasMetodologia.bindItemsToProperty("empresasOrdenadas");
	
		Column<Empresa> columnaEmpresa = new Column<Empresa>(empresasMetodologia);
		columnaEmpresa.setTitle("nombre");
		columnaEmpresa.bindContentsToProperty("nombre");
	}
}
