package ar.edu.utn.frba.dds.controlador;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JOptionPane;

import org.uqbar.commons.utils.Observable;

import com.ibm.icu.util.Calendar;

import ar.edu.utn.frba.dds.metodologia.Comparador;
import ar.edu.utn.frba.dds.metodologia.Comparador.Comparadores;
import ar.edu.utn.frba.dds.metodologia.Condicion;
import ar.edu.utn.frba.dds.metodologia.TipoOperacion;
import ar.edu.utn.frba.dds.metodologia.TipoOperacion.Operaciones;
import ar.edu.utn.frba.dds.modelo.BuilderCondicion;
import ar.edu.utn.frba.dds.modelo.Indicador;
import ar.edu.utn.frba.dds.servicio.ServicioCondiciones;
import ar.edu.utn.frba.dds.servicio.ServicioIndicadores;

@Observable
public class CondicionViewModel {

	public ServicioIndicadores servicioIndicadores;
	private List<Condicion> condicionesTotales;
	private String nombreCondicion;


	private String claseSeleccionada;
	private Condicion condicionSeleccionada;
	private List<Condicion> condicionesDisponibles;
	private Indicador indicadorSeleccionado;
	private List<Indicador> indicadoresDisponibles;
	private List<Condicion> condiciones;
	private Boolean comparaEmpresas = false;
	private String periodoInicio;
	private String periodoFin;
	private Boolean periodoInicioActual = false;
	private Boolean periodoFinActual = false;
	private Integer valorSuperar;
	private Integer valorAntiguedad;
	private Boolean comparadorAntiguedad = false;
	private BuilderCondicion builder;
	
	private TipoOperacion tipoOperacion;
	private Comparador comparador;
	
public CondicionViewModel() {
		Calendar cal = Calendar.getInstance();
		cal.get(Calendar.EXTENDED_YEAR);
		this.servicioIndicadores = new ServicioIndicadores();
		this.indicadoresDisponibles = servicioIndicadores.obtenerIndicadores();
		this.condicionesTotales = new ArrayList<Condicion>();
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
		builder.setIndicador(indicadorSeleccionado);
	}

	public List<Indicador> getIndicadoresDisponibles() {
		return indicadoresDisponibles;
	}

	public void setIndicadoresDisponibles(List<Indicador> indicadoresDisponibles) {
		this.indicadoresDisponibles = indicadoresDisponibles;
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

	public void setTipoOperacion(Operaciones tipoOperacion){
		TipoOperacion tipo = new TipoOperacion();
		tipo.setTipoOperacion(tipoOperacion);
		this.tipoOperacion = tipo;
	}

	public List<Operaciones> getOperaciones() {
		return Arrays.asList(TipoOperacion.Operaciones.values());
	}

	public void setOperaciones(List<TipoOperacion> operaciones) {
	}
	
	public List<Comparadores> getComparadores() {
		return Arrays.asList(Comparador.Comparadores.values());
	}

	public void setComparadores(List<Comparador> comparadores) {
	}
	
	public Comparador getComparador() {
		return this.comparador;
	}
	public void setComparador(Comparadores comparador) {
		this.comparador.setComparador(comparador);
		builder.setComparador(this.comparador);
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
