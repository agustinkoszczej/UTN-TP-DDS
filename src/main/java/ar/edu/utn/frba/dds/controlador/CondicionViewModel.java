package ar.edu.utn.frba.dds.controlador;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.uqbar.commons.utils.Observable;

import com.ibm.icu.util.Calendar;

import ar.edu.utn.frba.dds.metodologia.Comparador;
import ar.edu.utn.frba.dds.metodologia.Condicion;
import ar.edu.utn.frba.dds.metodologia.CondicionAntiguedad;
import ar.edu.utn.frba.dds.metodologia.CondicionSuperaValor;
import ar.edu.utn.frba.dds.modelo.BuilderCondicion;
import ar.edu.utn.frba.dds.modelo.Indicador;
import ar.edu.utn.frba.dds.servicio.ServicioIndicadores;

@Observable
public class CondicionViewModel {

	public ServicioIndicadores servicioIndicadores;
	private List<Condicion> condicionesTotales;
	private Condicion condicionAAgregarSeleccionada;
	

	private String nombreCondicion;

	private String stringCondicion;
	private List<String> tipoClases;
	private String claseSeleccionada;
	private Condicion condicionSeleccionada;
	private List<Condicion> condicionesDisponibles;
	private Indicador indicadorSeleccionado;
	private List<Indicador> indicadoresDisponibles;
	private List<Comparador> comparadores;
	private Comparador comparador;
	private List<Condicion> condiciones;
	private Boolean comparaEmpresas;
	//private String condicionSeleccionada;
	private BuilderCondicion builder;
	
	
	public CondicionViewModel() {
		Calendar cal = Calendar.getInstance();
		cal.get(Calendar.EXTENDED_YEAR);
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		System.out.println(format.format(cal.getTime()));
		
		
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

		BuilderCondicion builder = new BuilderCondicion();
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

	public List<String> getTipoClases() {
		return Arrays.asList("Taxativa","Comparativa","Combinada");
	}

	public void setTipoClases(List<String> tipoClase) {
		this.tipoClases = tipoClase;
	}

	public String getClaseSeleccionada() {
		return claseSeleccionada;
	}

	public void setClaseSeleccionada(String claseSeleccionada) {
		this.claseSeleccionada = claseSeleccionada;
	}

	public Boolean getComparaEmpresas() {
		return comparaEmpresas;
	}

	public void setComparaEmpresas(Boolean comparaEmpresas) {
		this.comparaEmpresas = comparaEmpresas;
		builder.setEsComparativa(comparaEmpresas);
	}
	
	public void setPeriodoInicioActual(Boolean periodoInicioActual)
	{
		if(periodoInicioActual)
			builder.setPeriodoInicio(getPeriodoActual());
	}

	public void setPeriodoFinActual(Boolean periodoFinActual)
	{
		if(periodoFinActual)
			builder.setPeriodoFin(getPeriodoActual());
	}

	
	public String getPeriodoActual(){
		Calendar cal = Calendar.getInstance();
		cal.get(Calendar.EXTENDED_YEAR);
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		return format.format(cal.getTime());
	}

	
}
