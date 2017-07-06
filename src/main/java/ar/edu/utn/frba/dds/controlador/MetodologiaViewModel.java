package ar.edu.utn.frba.dds.controlador;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import org.junit.Assert;
import org.uqbar.commons.model.ObservableUtils;
import org.uqbar.commons.utils.Observable;

import ar.edu.utn.frba.dds.metodologia.Comparador;
import ar.edu.utn.frba.dds.metodologia.Condicion;
import ar.edu.utn.frba.dds.metodologia.CondicionAntiguedad;
import ar.edu.utn.frba.dds.metodologia.CondicionSuperaValor;
import ar.edu.utn.frba.dds.metodologia.CondicionTaxativa;
import ar.edu.utn.frba.dds.metodologia.Metodologia;
import ar.edu.utn.frba.dds.servicio.ServicioCondiciones;
import ar.edu.utn.frba.dds.servicio.ServicioCuentas;
import ar.edu.utn.frba.dds.servicio.ServicioIndicadores;
import ar.edu.utn.frba.dds.servicio.ServicioMetodologias;

@Observable
public class MetodologiaViewModel{
	
	public ServicioCuentas servicioCuentas;
	public ServicioIndicadores servicioIndicadores;
	private List<Metodologia> metodologias;
	private Metodologia metodologiaSeleccionada;
	private String nombreCondicion;
	private List<Condicion> condicionesMetodologia;
	private List<Condicion> condicionesTotales;
	private Condicion condicionSeleccionada;
	private Condicion condicionAAgregarSeleccionada;
	

	public MetodologiaViewModel(ServicioCuentas unServicioDeCuentas, ServicioIndicadores servicioIndicadores) {
		this.servicioCuentas = unServicioDeCuentas;
		this.servicioIndicadores = servicioIndicadores;
		this.condicionesTotales = new ServicioCondiciones().obtenerCondiciones();
		//this.metodologias = new ServicioMetodologias().obtenerMetodologias();
		
		this.metodologias = new ArrayList<Metodologia>();
		
		this.condicionesTotales = new ArrayList<Condicion>();
		condicionesTotales = new ServicioCondiciones().obtenerCondiciones();
		CondicionSuperaValor condicionValor = new CondicionSuperaValor();
		condicionValor.setInicioPeriodo("20170100");
		condicionValor.setFinPeriodo("20170100");
		condicionValor.setComparador(Comparador.IGUAL);
		condicionValor.setValorSuperar(30000);
		condicionValor.setNombreCondicion("supera EBITDA 30000");
		//condicionesTotales.add(condicionValor);
		
		CondicionAntiguedad condicionAntiguedad = new CondicionAntiguedad();
		List<Condicion> condiciones = new ArrayList<Condicion>();
		condicionAntiguedad.setAniosNecesarios(10);
		condicionAntiguedad.setNombreCondicion("longetividad");
		Metodologia metodologia = new Metodologia();
		metodologia.setNombre("metodo1");
		metodologia.agregarCondicion(condicionAntiguedad);
		metodologias.add(metodologia);
		metodologiaSeleccionada = metodologias.get(0);
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
	}

	public String getNombreCondicion() {
		return condicionSeleccionada.getNombreCondicion();
	}


	public List<Condicion> getCondicionesMetodologia() {
		return metodologiaSeleccionada.getCondiciones();
		
	}

	public void setCondicionesMetodologia(List<Condicion> condicionesMetodologia) {
		this.condicionesMetodologia = condicionesMetodologia;
	}

	public Condicion getCondicionSeleccionada() {
		return condicionSeleccionada;
	}

	public void setCondicionSeleccionada(Condicion condicionSeleccionada) {
		this.condicionSeleccionada = condicionSeleccionada;
	}

	public List<Condicion> getCondicionesTotales() {
		//Tiene que traer todas las condiciones que existan en el programa (x json o repo)
		return condicionesTotales;
	}

	public void setCondicionesTotales(List<Condicion> condicionesTotales) {
		this.condicionesTotales = condicionesTotales;
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

	public void aceptarMetodologia() {
		// TODO Auto-generated method stub
		try {
			new ServicioMetodologias().guardarMetodologia(metodologiaSeleccionada);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}




}
