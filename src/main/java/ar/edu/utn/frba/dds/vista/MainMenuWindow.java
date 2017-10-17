package ar.edu.utn.frba.dds.vista;

import org.uqbar.arena.layout.VerticalLayout;
import org.uqbar.arena.widgets.Button;
import org.uqbar.arena.widgets.Label;
import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.windows.SimpleWindow;
import org.uqbar.arena.windows.WindowOwner;

import ar.edu.utn.frba.dds.controlador.CuentaViewModel;
import ar.edu.utn.frba.dds.controlador.MetodologiaViewModel;
import ar.edu.utn.frba.dds.util.ProveedorAcceso;

public class MainMenuWindow<T> extends SimpleWindow<T> {

	private ProveedorAcceso proveedor;
	
	@SuppressWarnings("unchecked")
	public MainMenuWindow(WindowOwner parent) {
		super(parent, (T) new Object());
		proveedor = new ProveedorAcceso();
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
		ConsultaIndicadorWindow dialog = new ConsultaIndicadorWindow(this, proveedor);
		dialog.open();
	}

	public void consultarCuentas() {
		ConsultaCuentaWindow dialog = new ConsultaCuentaWindow(this, new CuentaViewModel(proveedor));
		dialog.open();
	}
	
	public void consultarMetodologias() {
		ConsultaMetodologiaWindow dialog = new ConsultaMetodologiaWindow(this, new MetodologiaViewModel(proveedor));

		dialog.open();
	}

	@Override
	protected void addActions(Panel actionsPanel) {

	}

}