package ar.edu.utn.frba.dds.controlador;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.uqbar.commons.utils.Observable;

import ar.edu.utn.frba.dds.metodologia.Comparador;
import ar.edu.utn.frba.dds.metodologia.Condicion;
import ar.edu.utn.frba.dds.metodologia.CondicionAntiguedad;
import ar.edu.utn.frba.dds.metodologia.CondicionSuperaValor;
import ar.edu.utn.frba.dds.modelo.Indicador;
import ar.edu.utn.frba.dds.servicio.ServicioIndicadores;

@Observable
public class CondicionViewModel {

	public ServicioIndicadores servicioIndicadores;
	private List<Condicion> condicionesTotales;
	private Condicion condicionAAgregarSeleccionada;
	

	private String nombreCondicion;

	private String stringCondicion;
	private Condicion condicionSeleccionada;
	private List<Condicion> condicionesDisponibles;
	private Indicador indicadorSeleccionado;
	private List<Indicador> indicadoresDisponibles;
	private List<Comparador> comparadores;
	private Comparador comparador;
	private List<Condicion> condiciones;
	//private String condicionSeleccionada;
	
	
	
	public CondicionViewModel() {
		this.servicioIndicadores = new ServicioIndicadores();
		
		this.condicionesTotales = new ArrayList<Condicion>();
		CondicionSuperaValor condicionValor = new CondicionSuperaValor();
		condicionValor.setInicioPeriodo("20170100");
		condicionValor.setFinPeriodo("20170100");
		condicionValor.setComparador(Comparador.IGUAL);
		condicionValor.setValorSuperar(30000);
		condicionValor.setNombreCondicion("supera EBITDA 30000");
		condicionesTotales.add(condicionValor);
		
		condiciones = new ArrayList<Condicion>();

		condiciones.addAll(Arrays.asList(new CondicionAntiguedad(), new CondicionSuperaValor()));
		//condiciones.add(Condicion.devolverCondicionDesdeNombre("CondicionTaxativa"));
		//condiciones.add("CondicionTaxativa");
	}
	
	public List<Condicion> getCondicionesTotales() {
		return condicionesTotales;
	}



	public void setCondicionesTotales(List<Condicion> condicionesTotales) {
		this.condicionesTotales = condicionesTotales;
	}



	public String getNombreCondicion() {
		return nombreCondicion;
	}



	public void setNombreCondicion(String nombreCondicion) {
		this.nombreCondicion = nombreCondicion;
	}



	public Condicion getCondicionSeleccionada() {
	
		return condicionSeleccionada;
	}



	public void setCondicionSeleccionada(Condicion condicionSeleccionada) {
		this.condicionSeleccionada = condicionSeleccionada;
	}



	public List<Condicion> getCondicionesDisponibles() {
		return condicionesDisponibles;
	}



	public void setCondicionesDisponibles(List<Condicion> condicionesDisponibles) {
		this.condicionesDisponibles = condicionesDisponibles;
	}



	public Indicador getIndicadorSeleccionado() {
		return indicadorSeleccionado;
	}



	public void setIndicadorSeleccionado(Indicador indicadorSeleccionado) {
		this.indicadorSeleccionado = indicadorSeleccionado;
	}



	public List<Indicador> getIndicadoresDisponibles() {
		return indicadoresDisponibles;
	}



	public void setIndicadoresDisponibles(List<Indicador> indicadoresDisponibles) {
		this.indicadoresDisponibles = indicadoresDisponibles;
	}



	public List<Comparador> getComparadores() {
		return comparadores;
	}



	public void setComparadores(List<Comparador> comparadores) {
		this.comparadores = comparadores;
	}



	public Comparador getComparador() {
		return comparador;
	}



	public void setComparador(Comparador comparador) {
		this.comparador = comparador;
	}

	public void guardarCondicion() {
		//TODO Aca tiene que guardar la condicion al archivo (persistencia)
	}

	public List<Condicion> getCondiciones() {
		
		return condiciones;
		
	}

	public void setCondiciones(List<Condicion> condiciones) {
		this.condiciones = condiciones;
	}

	
}
