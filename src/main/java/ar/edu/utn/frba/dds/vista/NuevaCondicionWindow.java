package ar.edu.utn.frba.dds.vista;

import org.uqbar.arena.layout.ColumnLayout;
import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.CheckBox;
import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.NumericField;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.widgets.Selector;
import org.uqbar.arena.widgets.TextBox;
import org.uqbar.arena.windows.SimpleWindow;
import org.uqbar.arena.windows.WindowOwner;

import ar.edu.utn.frba.dds.controlador.CondicionViewModel;
import ar.edu.utn.frba.dds.metodologia.Comparador;
import ar.edu.utn.frba.dds.metodologia.TipoOperacion;
import ar.edu.utn.frba.dds.modelo.Indicador;

public class NuevaCondicionWindow extends SimpleWindow<CondicionViewModel>  {

	public NuevaCondicionWindow(WindowOwner parent, CondicionViewModel model) {
		super(parent, model);
	}

	@Override
	protected void addActions(Panel panel) {
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
		this.close();
	}

	@Override
	protected void createFormPanel(Panel form) {
		new Label(form).setText("Nombre de la condicion");
		TextBox textBoxNombreCondicion = new TextBox(form);
		textBoxNombreCondicion.bindValueToProperty("nombreCondicion");
		textBoxNombreCondicion.setWidth(200);
		
		Panel form2 = new Panel(form);
		form2.setLayout(new ColumnLayout(2));
		
		new Label(form2).setText("Seleccione indicador");
		Selector<Indicador> selectorIndicador = new Selector<Indicador>(form2).allowNull(true);
		
		selectorIndicador.bindValueToProperty("indicadorSeleccionado");
		selectorIndicador.bindItemsToProperty("indicadoresDisponibles").adaptWith(Indicador.class, "nombreIndicador");
		selectorIndicador.setWidth(280);
		
		new Label(form2).setText("Tipo Comparacion:");
		Selector<Comparador> selectorComparador = new Selector<Comparador>(form2).allowNull(true);
		selectorComparador.bindValueToProperty("comparador");
		selectorComparador.bindItemsToProperty("comparadores");
		selectorComparador.setWidth(280);
		
		new Label(form2).setText("Compara Empresas:");
		CheckBox checkEmpresa = new CheckBox(form2);
		checkEmpresa.bindValueToProperty("comparaEmpresas");
		
		new Label(form2).setText("Operador a todos los balances:");
		Selector<TipoOperacion> selectorTipoOperacion = new Selector<TipoOperacion>(form2).allowNull(true);
		selectorTipoOperacion.bindValueToProperty("tipoOperacion");
		selectorTipoOperacion.bindItemsToProperty("operaciones");
		selectorTipoOperacion.setWidth(280);
				
		new Label(form2).setText("Fecha periodo inicio (YYYYMMDD)");
		TextBox textBoxPeriodoInicio = new TextBox(form2);
		textBoxPeriodoInicio.bindValueToProperty("periodoInicio");
		
		new Label(form2).setText("Tomar fecha actual como periodo inicial");
		new CheckBox(form2).bindValueToProperty("periodoInicioActual");
		
		new Label(form2).setText("Fecha periodo fin (YYYYMMDD)");
		TextBox textBoxPeriodoFin = new TextBox(form2);
		textBoxPeriodoFin.bindValueToProperty("periodoFin");
		
		new Label(form2).setText("Tomar fecha actual como periodo final");
		new CheckBox(form2).bindValueToProperty("periodoFinActual");
	
		new Label(form2).setText("Ingresar un valor a comparar de ser necesario");
		NumericField numericValorSuperar = new NumericField(form2);
		numericValorSuperar.bindValueToProperty("valorSuperar");
		
		new Label(form2).setText("Ingresar antiguedad requerida de ser necesario");
		NumericField numericValorAntiguedad = new NumericField(form2);
		numericValorAntiguedad.bindValueToProperty("valorAntiguedad");
		
		new Label(form2).setText("Compara antiguedad");
		CheckBox checkAnti = new CheckBox(form2);
		checkAnti.bindValueToProperty("comparadorAntiguedad");	
	}
}
