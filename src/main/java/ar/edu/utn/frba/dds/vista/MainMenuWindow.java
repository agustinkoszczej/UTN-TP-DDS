package ar.edu.utn.frba.dds.vista;

import org.uqbar.arena.layout.VerticalLayout;
import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.windows.SimpleWindow;
import org.uqbar.arena.windows.WindowOwner;

import ar.edu.utn.frba.dds.controlador.ConsultaIndicadorViewModel;
import ar.edu.utn.frba.dds.controlador.CuentaViewModel;
import ar.edu.utn.frba.dds.controlador.MainMenuModel;
import ar.edu.utn.frba.dds.servicio.ServicioCuentas;
import ar.edu.utn.frba.dds.servicio.ServicioIndicadores;

public class MainMenuWindow extends SimpleWindow<MainMenuModel> {

	static MainMenuModel model = new MainMenuModel();

	public MainMenuWindow(WindowOwner parent) {
		super(parent, model);
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

	}

	public void consultarIndicadores() {
		// TODO
		ServicioCuentas unServicioCuentas = new ServicioCuentas();
		ServicioIndicadores unServicioIndicadores = new ServicioIndicadores();
		//IndicadorWindow dialog = new IndicadorWindow(this, new IndicadorViewModel());
		ConsultaIndicadorWindow dialog = new ConsultaIndicadorWindow(this, new ConsultaIndicadorViewModel(unServicioCuentas,unServicioIndicadores));

		dialog.open();
	}

	public void consultarCuentas() {
		ServicioCuentas unServicio = new ServicioCuentas();

		CuentaWindow dialog = new CuentaWindow(this, new CuentaViewModel(unServicio));

		dialog.open();
		// dialog.onAccept(() -> { });
	}

	@Override
	protected void addActions(Panel actionsPanel) {

	}

}