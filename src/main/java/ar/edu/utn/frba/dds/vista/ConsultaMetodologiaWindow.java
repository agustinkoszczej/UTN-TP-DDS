package ar.edu.utn.frba.dds.vista;

import javax.swing.JOptionPane;

import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.widgets.Selector;
import org.uqbar.arena.widgets.tables.Column;
import org.uqbar.arena.widgets.tables.Table;
import org.uqbar.arena.windows.SimpleWindow;
import org.uqbar.arena.windows.WindowOwner;

import ar.edu.utn.frba.dds.controlador.CuentaViewModel;
import ar.edu.utn.frba.dds.controlador.IndicadorViewModel;
import ar.edu.utn.frba.dds.controlador.MetodologiaViewModel;
import ar.edu.utn.frba.dds.metodologia.Condicion;
import ar.edu.utn.frba.dds.metodologia.CondicionTaxativa;
import ar.edu.utn.frba.dds.metodologia.Metodologia;
import ar.edu.utn.frba.dds.modelo.Balance;
import ar.edu.utn.frba.dds.modelo.Empresa;
import ar.edu.utn.frba.dds.modelo.Indicador;
import ar.edu.utn.frba.dds.servicio.ServicioIndicadores;

public class ConsultaMetodologiaWindow extends SimpleWindow<MetodologiaViewModel> {

	public ConsultaMetodologiaWindow(WindowOwner parent, MetodologiaViewModel metodologiaViewModel) {
		super(parent, metodologiaViewModel);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void addActions(Panel panel) {
		// TODO Auto-generated method stub
		new Button(panel)
		.setCaption("Agregar condicion")
		.onClick(this::abrirCondicionWindow);
		
		new Button(panel)
		.setCaption("Aceptar metodologia")
		.onClick(this::aceptarMetodologia);
		new Button(panel)
		.setCaption("Salir")
		.onClick(this::close)
		.setWidth(100);
	}
	
	private void abrirCondicionWindow(){
		AgregarCondicionWindow dialog = new AgregarCondicionWindow(this, getModel().getSource());
		dialog.open();

	}
	
	private void aceptarMetodologia(){
		this.getModel().getSource().aceptarMetodologia();
	}

	@Override
	protected void createFormPanel(Panel form) {
		// TODO Auto-generated method stub
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
