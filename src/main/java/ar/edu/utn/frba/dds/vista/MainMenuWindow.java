package ar.edu.utn.frba.dds.vista;

import org.uqbar.arena.layout.VerticalLayout;
import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.windows.SimpleWindow;
import org.uqbar.arena.windows.WindowOwner;

import ar.edu.utn.frba.dds.controlador.IndicadorViewModel;
import ar.edu.utn.frba.dds.controlador.MetodologiaViewModel;
import ar.edu.utn.frba.dds.controlador.CuentaViewModel;
import ar.edu.utn.frba.dds.servicio.ServicioCuentas;
import ar.edu.utn.frba.dds.servicio.ServicioIndicadores;

public class MainMenuWindow<T> extends SimpleWindow<T> {

	private ServicioCuentas servicioCuentas;
	@SuppressWarnings("unchecked")
	public MainMenuWindow(WindowOwner parent) {
		super(parent, (T) new Object());
		servicioCuentas = new ServicioCuentas();
	}

	@Override
	protected void createFormPanel(Panel mainPanel) {
		Panel form = new Panel(mainPanel);
		form.setLayout(new VerticalLayout());

		this.setTitle("Menu Principal");
		new Label(form)
		.setText("Menu Principal")
		.setFontSize(30);

		new Button(mainPanel)
		.setCaption("Consultar Cuentas")
		.onClick(this::consultarCuentas)
		.setFontSize(15);

		// Agrego boton de consultar indicador
		new Button(mainPanel)
		.setCaption("Consultar Indicadores")
		.onClick(this::consultarIndicadores)
		.setFontSize(15);

		new Button(mainPanel)
		.setCaption("Consultar Metodologias")
		.onClick(this::consultarMetodologias)
		.setFontSize(15);
	}

	public void consultarIndicadores() {
		ServicioIndicadores servicioIndicadores = new ServicioIndicadores();
		ConsultaIndicadorWindow dialog = new ConsultaIndicadorWindow(this, servicioCuentas, servicioIndicadores);
		dialog.open();
	}

	public void consultarCuentas() {
		ConsultaCuentaWindow dialog = new ConsultaCuentaWindow(this, new CuentaViewModel(servicioCuentas));
		dialog.open();
	}
	
	public void consultarMetodologias() {
		ConsultaMetodologiaWindow dialog = new ConsultaMetodologiaWindow(this, new MetodologiaViewModel(servicioCuentas,new ServicioIndicadores()));

		System.out.println("olaff");
		dialog.open();

		System.out.println("ola3");
	}

	@Override
	protected void addActions(Panel actionsPanel) {

	}

}