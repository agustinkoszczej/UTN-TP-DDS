package ar.edu.utn.frba.dds.controlador;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JOptionPane;

import org.uqbar.commons.model.ObservableUtils;
import org.uqbar.commons.utils.Observable;

import com.ibm.icu.util.Calendar;

import ar.edu.utn.frba.dds.metodologia.Comparador;
import ar.edu.utn.frba.dds.metodologia.Condicion;
import ar.edu.utn.frba.dds.metodologia.CondicionAntiguedad;
import ar.edu.utn.frba.dds.metodologia.CondicionSuperaValor;
import ar.edu.utn.frba.dds.metodologia.TipoOperacion;
import ar.edu.utn.frba.dds.modelo.BuilderCondicion;
import ar.edu.utn.frba.dds.modelo.Indicador;
import ar.edu.utn.frba.dds.servicio.ServicioCondiciones;
import ar.edu.utn.frba.dds.servicio.ServicioIndicadores;

@Observable
public class CondicionViewModel {

	public ServicioIndicadores servicioIndicadores;
	private List<Condicion> condicionesTotales;
	private Condicion condicionAAgregarSeleccionada;
	

	private String nombreCondicion;

	private TipoOperacion tipoOperacion;
	private List<TipoOperacion> operaciones;
	private String claseSeleccionada;
	private Condicion condicionSeleccionada;
	private List<Condicion> condicionesDisponibles;
	private Indicador indicadorSeleccionado;
	private List<Indicador> indicadoresDisponibles;
	private List<Comparador> comparadores;
	private Comparador comparador;
	private List<Condicion> condiciones;
	private Boolean comparaEmpresas = false;
	private String periodoInicio;
	private String periodoFin;
	private Boolean periodoInicioActual = false;
	private Boolean periodoFinActual = false;
	private Integer valorSuperar;
	private Integer valorAntiguedad;
	private Boolean comparadorAntiguedad = false;
	//private String condicionSeleccionada;
	private BuilderCondicion builder;
		
	
	
	
	
public CondicionViewModel() {
		Calendar cal = Calendar.getInstance();
		cal.get(Calendar.EXTENDED_YEAR);
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		
		
		this.servicioIndicadores = new ServicioIndicadores();
		indicadoresDisponibles = servicioIndicadores.obtenerIndicadores();
		
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

		builder = new BuilderCondicion();
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
		System.out.println(nombreCondicion);
		builder.setNombre(nombreCondicion);
	}



	public Condicion getCondicionSeleccionada() {
	
		return condicionSeleccionada;
	}



	public void setCondicionSeleccionada(Condicion condicionSeleccionada) {
		this.condicionSeleccionada = condicionSeleccionada;
		builder.setCondicion(condicionSeleccionada);
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
		System.out.println(indicadorSeleccionado);
		builder.setIndicador(indicadorSeleccionado);
	}



	public List<Indicador> getIndicadoresDisponibles() {
		return indicadoresDisponibles;
	}



	public void setIndicadoresDisponibles(List<Indicador> indicadoresDisponibles) {
		this.indicadoresDisponibles = indicadoresDisponibles;
	}



	public List<Comparador> getComparadores() {
		return Arrays.asList(Comparador.values());
	}



	public void setComparadores(List<Comparador> comparadores) {
		this.comparadores = comparadores;
	}



	public Comparador getComparador() {
		return comparador;
	}



	public void setComparador(Comparador comparador) {
		this.comparador = comparador;
		builder.setComparador(comparador);
	}

	public String getPeriodoFin() {
		return periodoFin;
	}

	public void setPeriodoFin(String periodoFin) {
		this.periodoFin = periodoFin;
		builder.setPeriodoFin(periodoFin);
	}

	public Boolean getPeriodoInicioActual() {
		return periodoInicioActual;
	}

	public Boolean getPeriodoFinActual() {
		return periodoFinActual;
	}
	

	public List<Condicion> getCondiciones() {
		
		return condiciones;
		
	}

	public void setCondiciones(List<Condicion> condiciones) {
		this.condiciones = condiciones;
	}

	public TipoOperacion getTipoOperacion() {
		return this.tipoOperacion;
	}

	public void setTipoOperacion(TipoOperacion tipoOperacion) {
		this.tipoOperacion = tipoOperacion;
	}

	public List<TipoOperacion> getOperaciones() {
		return Arrays.asList(TipoOperacion.values());
	}

	public void setOperaciones(List<TipoOperacion> operaciones) {
		this.operaciones = operaciones;
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
		builder.setEsComparativa(this.comparaEmpresas);
	}
	
	public void setPeriodoInicioActual(Boolean periodoInicioActual)
	{
		if(periodoInicioActual){
			builder.setPeriodoInicio(getPeriodoActual());
			setPeriodoInicio(getPeriodoActual());
		}
		
	}

	public void setPeriodoFinActual(Boolean periodoFinActual)
	{
		if(periodoFinActual){
			builder.setPeriodoFin(getPeriodoActual());
			setPeriodoFin(getPeriodoActual());
		}
	}

	
	public String getPeriodoActual(){
		Calendar cal = Calendar.getInstance();
		cal.get(Calendar.EXTENDED_YEAR);
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		return format.format(cal.getTime());
	}

	public String getPeriodoInicio() {
		return periodoInicio;
	}

	public void setPeriodoInicio(String periodoInicio) {
		this.periodoInicio = periodoInicio;
		builder.setPeriodoFin(periodoInicio);
	}

	public Integer getValorSuperar(){
		return valorSuperar;
	}
	
	public void setValorSuperar(Integer valor){
		this.valorSuperar = valor;
		builder.setValorNumericoAComparar(valor);
	}

	public Integer getValorAntiguedad() {
		return valorAntiguedad;
		
	}

	public void setValorAntiguedad(Integer valorAntiguedad) {
		this.valorAntiguedad = valorAntiguedad;
		builder.setAntiguedadRequerida(valorAntiguedad);
	}
	

	public void guardarCondicion() {
		//TODO Aca tiene que guardar la condicion al archivo (persistencia)
		
		System.out.println(builder.devolverCondicion());
		Condicion condicion = builder.devolverCondicion();
		try {
			new ServicioCondiciones().guardarCondicion(condicion);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Error al guardar la condicion");
			}catch(NullPointerException e){
			JOptionPane.showMessageDialog(null, "Ningun tipo de condicion pudo ser creado en base a lo ingresado. Faltan datos para crear la condicion");
		}
		//ObservableUtils.firePropertyChanged(model, property);
	}

	public Boolean getComparadorAntiguedad() {
		return comparadorAntiguedad;
	}

	public void setComparadorAntiguedad(Boolean comparadorAntiguedad) {
		this.comparadorAntiguedad = comparadorAntiguedad;
		builder.setComparaAntiguedad(comparadorAntiguedad);
	}
	
}
