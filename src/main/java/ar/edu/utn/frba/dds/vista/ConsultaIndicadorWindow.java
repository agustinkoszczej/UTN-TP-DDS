package ar.edu.utn.frba.dds.vista;

import java.awt.Color;

import javax.swing.JOptionPane;

import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.widgets.Selector;
import org.uqbar.arena.widgets.tables.Column;
import org.uqbar.arena.widgets.tables.Table;
import org.uqbar.arena.windows.SimpleWindow;
import org.uqbar.arena.windows.WindowOwner;

import ar.edu.utn.frba.dds.controlador.IndicadorViewModel;
import ar.edu.utn.frba.dds.modelo.Balance;
import ar.edu.utn.frba.dds.modelo.Empresa;
import ar.edu.utn.frba.dds.modelo.Indicador;
import ar.edu.utn.frba.dds.util.ProveedorAcceso;

public class ConsultaIndicadorWindow extends SimpleWindow<IndicadorViewModel> {
	
	public ConsultaIndicadorWindow(WindowOwner parent, ProveedorAcceso servicioCuentas) {
		super(parent, new IndicadorViewModel(servicioCuentas));
	}

	@Override
	protected void addActions(Panel panel) {

		new Button(panel)
		.setCaption("Ingresar indicador")
		.onClick(this::ingresarIndicador);
		
		new Button(panel)
		.setCaption("Aplicar indicador")
		.onClick(this::aplicarIndicador);
		new Button(panel)
		.setCaption("Salir")
		.onClick(this::close)
		.setWidth(100);
	}

	public void ingresarIndicador() {
		AgregarIndicadorWindow dialog = new AgregarIndicadorWindow(this, getModel().getSource());
		getModel().getSource().limpiarIngreso();	
		dialog.open();
	}
	
	public void aplicarIndicador(){
		try {
			getModel().getSource().aplicarIndicador();
		} catch (Exception e) {
			// TODO Mostrar un mensaje con el mensaje de la excepcion
			JOptionPane.showMessageDialog(null,"No se pudo aplicar el Indicador"); 
		}
	}
	
	@Override
	protected void createFormPanel(Panel form) {
		
		this.setTitle("Consulta Indicadores");
		
		new Label(form).setText("Empresa");
		
		Selector<Empresa> selectorEmpresa = new Selector<Empresa>(form).allowNull(true);
		selectorEmpresa.bindValueToProperty("empresaSeleccionada");
		selectorEmpresa.bindItemsToProperty("empresas").adaptWith(Empresa.class, "empresa_nombre");
		selectorEmpresa.setWidth(280);
		
		new Label(form).setText("Periodo");
		
		Selector<Balance> selectorPeriodo = new Selector<Balance>(form).allowNull(true);
		selectorPeriodo.bindValueToProperty("balanceSeleccionado");
		selectorPeriodo.bindItemsToProperty("balancesEmpresaSeleccionada").adaptWith(Balance.class, "balance_periodo");
		selectorPeriodo.setWidth(280);
		
		new Label(form).setText("Indicador");
		
		Selector<Indicador> selectorIndicador = new Selector<Indicador>(form).allowNull(true);
		selectorIndicador.bindValueToProperty("indicadorSeleccionado");
		selectorIndicador.bindItemsToProperty("indicadores").adaptWith(Indicador.class, "nombreIndicador");
		selectorIndicador.setWidth(280);
		
		new Label(form).setText("Indicadores disponibles");
		Table<Indicador> tableIndicadores = new Table<Indicador>(form, Indicador.class);
		tableIndicadores.setHeight(400);
		
		tableIndicadores.bindItemsToProperty("indicadores");
		tableIndicadores.bindValueToProperty("indicadorSeleccionado");
		
	
		Column<Indicador> columnaNombre = new Column<Indicador>(tableIndicadores);
		columnaNombre.setTitle("nombre").setWeight(32);
		columnaNombre.bindContentsToProperty("nombreIndicador");
		
		Column<Indicador> columnaExpresion = new Column<Indicador>(tableIndicadores);
		columnaExpresion.setTitle("expresion").setWeight(50);
		columnaExpresion.bindContentsToProperty("expresion");
		
		new Label(form)
		.setText("Valor Indicador Aplicado")
		.setBackground(Color.GREEN);
		Label valorIndicador = new Label(form);
		valorIndicador.bindValueToProperty("valorIndicador");
		
	}
}
