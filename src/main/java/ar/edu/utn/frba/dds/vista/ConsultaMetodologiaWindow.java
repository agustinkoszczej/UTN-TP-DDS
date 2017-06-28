package ar.edu.utn.frba.dds.vista;

import org.uqbar.arena.widgets.Panel;
import org.uqbar.arena.windows.SimpleWindow;
import org.uqbar.arena.windows.WindowOwner;

import ar.edu.utn.frba.dds.controlador.CuentaViewModel;
import ar.edu.utn.frba.dds.controlador.IndicadorViewModel;
import ar.edu.utn.frba.dds.controlador.MetodologiaViewModel;

public class ConsultaMetodologiaWindow extends SimpleWindow<MetodologiaViewModel> {

	public ConsultaMetodologiaWindow(WindowOwner parent, MetodologiaViewModel metodologiaViewModel) {
		super(parent, metodologiaViewModel);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void addActions(Panel arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void createFormPanel(Panel arg0) {
		// TODO Auto-generated method stub
		this.setTitle("Consulta Metodologias");
	}

}
