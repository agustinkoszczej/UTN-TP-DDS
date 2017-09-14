package ar.edu.utn.frba.dds.controlador;

import java.io.IOException;
import java.util.List;

import javax.swing.JOptionPane;

import org.uqbar.commons.model.ObservableUtils;
import org.uqbar.commons.utils.Observable;

import ar.edu.utn.frba.dds.metodologia.Condicion;
import ar.edu.utn.frba.dds.metodologia.Metodologia;
import ar.edu.utn.frba.dds.modelo.Empresa;
import ar.edu.utn.frba.dds.servicio.ServicioCondiciones;
import ar.edu.utn.frba.dds.servicio.ServicioCuentas;
import ar.edu.utn.frba.dds.servicio.ServicioIndicadores;
import ar.edu.utn.frba.dds.servicio.ServicioMetodologias;
import ar.edu.utn.frba.dds.util.BaseDeDatos;

@Observable
public class MetodologiaViewModel{
	
	public ServicioCuentas servicioCuentas;
	public ServicioIndicadores servicioIndicadores;
	private List<Metodologia> metodologias;
	private Metodologia metodologiaSeleccionada;
	private Condicion condicionSeleccionada;
	private Condicion condicionAAgregarSeleccionada;
	public List<Empresa> empresasOrdenadas;
	
	public MetodologiaViewModel(ServicioCuentas unServicioDeCuentas, ServicioIndicadores servicioIndicadores) {
		this.servicioCuentas = unServicioDeCuentas;
		this.servicioIndicadores = servicioIndicadores;
		//this.condicionesTotales = new ServicioCondiciones().obtenerCondiciones();
		this.metodologias = new ServicioMetodologias().obtenerMetodologias();
		this.metodologiaSeleccionada = new Metodologia();
	}

	public List<Metodologia> getMetodologias() {
		return metodologias;
	}

	public void setMetodologias(List<Metodologia> metodologias) {
		this.metodologias = metodologias;
	}

	public Metodologia getMetodologiaSeleccionada() {
		return metodologiaSeleccionada;
	}

	public void setMetodologiaSeleccionada(Metodologia metodologiaSeleccionada) {
		this.metodologiaSeleccionada = metodologiaSeleccionada;
		ObservableUtils.firePropertyChanged(this, "condicionesMetodologia");
	}

	public String getNombreCondicion() {
		return condicionSeleccionada.getNombreCondicion();
	}


	public List<Condicion> getCondicionesMetodologia() {
		return metodologiaSeleccionada.getCondiciones();
		
	}

	public void setCondicionesMetodologia(List<Condicion> condicionesMetodologia) {
	}

	public Condicion getCondicionSeleccionada() {
		return condicionSeleccionada;
	}

	public void setCondicionSeleccionada(Condicion condicionSeleccionada) {
		this.condicionSeleccionada = condicionSeleccionada;
	}

	public List<Condicion> getCondicionesTotales() {
		//Tiene que traer todas las condiciones que existan en el programa (x json o repo)
		return new ServicioCondiciones().obtenerCondiciones();
	}

	public void setCondicionesTotales(List<Condicion> condicionesTotales) {
		//this.condicionesTotales = condicionesTotales;
	}

	public Condicion getCondicionAAgregarSeleccionada() {
		return condicionAAgregarSeleccionada;
	}

	public void setCondicionAAgregarSeleccionada(Condicion condicionAAgregarSeleccionada) {
		this.condicionAAgregarSeleccionada = condicionAAgregarSeleccionada;
	}

	public void agregarCondicionSeleccionadaAMetodologia() {
		try{
		metodologiaSeleccionada.agregarCondicion(condicionAAgregarSeleccionada);
		}catch(NullPointerException e) {
			JOptionPane.showMessageDialog(null,"No selecciono condicion");
		}
		ObservableUtils.firePropertyChanged(this, "condicionesMetodologia");
	}

	public void guardarMetodologia() {
		if(metodologiaSeleccionada.getNombre() == null)
		{
			String nombre = JOptionPane.showInputDialog("ingrese nombre para la metodologia");
			metodologiaSeleccionada.setNombre(nombre);
		}
		try {
			new ServicioMetodologias().guardarMetodologia(metodologiaSeleccionada); 
			JOptionPane.showMessageDialog(null, "Metodologia guardada");
			//TODO: si ya existe, guarda una nueva en lugar de "actualizarla"
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public List<Empresa> getEmpresasOrdenadas() {
		return empresasOrdenadas;
	}

	public void setEmpresasOrdenadas(List<Empresa> empresasOrdenadas) {
		this.empresasOrdenadas = empresasOrdenadas;
	}

	public void aplicarMetodologia() {
		List<Empresa> empresas = new ServicioCuentas(new BaseDeDatos()).obtenerEmpresas();
		empresasOrdenadas = metodologiaSeleccionada.aplicar(empresas);
	}
}
