package ar.edu.utn.frba.dds.modelo;


import javax.swing.JOptionPane;

import ar.edu.utn.frba.dds.metodologia.Comparador;
import ar.edu.utn.frba.dds.metodologia.ComparadorAntiguedad;
import ar.edu.utn.frba.dds.metodologia.ComparadorDesempenio;
import ar.edu.utn.frba.dds.metodologia.Condicion;
import ar.edu.utn.frba.dds.metodologia.CondicionAntiguedad;
import ar.edu.utn.frba.dds.metodologia.CondicionComparativa;
import ar.edu.utn.frba.dds.metodologia.CondicionConsistenciaTiempo;
import ar.edu.utn.frba.dds.metodologia.CondicionGeneral;
import ar.edu.utn.frba.dds.metodologia.CondicionSuperaValor;
import ar.edu.utn.frba.dds.metodologia.CondicionTaxativa;
import ar.edu.utn.frba.dds.metodologia.TipoOperacion;

public class BuilderCondicion {

		private String nombre;
		private Condicion condicion;
		private Indicador indicador;
		private Comparador comparador;
		private Integer antiguedadRequerida;
		private Integer valorNumericoAComparar;
		private String periodoInicio;
		private String periodoFin;
		private TipoOperacion tipoOperacion;
		private Boolean esComparativa = false;
		private Boolean comparaAntiguedad = false;

		public TipoOperacion getTipoOperacion() {
			return tipoOperacion;
		}

		public void setTipoOperacion(TipoOperacion tipoOperacion) {
			this.tipoOperacion = tipoOperacion;
		}

		public int getAntiguedadRequerida() {
			return antiguedadRequerida;
		}

		public void setAntiguedadRequerida(Integer antiguedadRequerida) {
			this.antiguedadRequerida = antiguedadRequerida;
		}

		public int getValorNumericoAComparar() {
			return valorNumericoAComparar;
		}

		public void setValorNumericoAComparar(Integer valorNumericoAComparar) {
			this.valorNumericoAComparar = valorNumericoAComparar;
		}		

		public String getNombre() {
			return nombre;
		}

		public void setNombre(String nombre) {
			this.nombre = nombre;
		}
		public Condicion getCondicion() {
			return condicion;
		}
		public void setCondicion(Condicion condicion) {
			this.condicion = condicion;
		}
		public Indicador getIndicador() {
			return indicador;
		}
		public void setIndicador(Indicador indicador) {
			this.indicador = indicador;
		}
		public Comparador getComparador() {
			return comparador;
		}
		public void setComparador(Comparador comparador) {
			this.comparador = comparador;
		}
		public int getValorASetear() {
			return valorNumericoAComparar;
		}

		public void setValorASetear(int valorASetear) {
			this.valorNumericoAComparar = valorASetear;
		}

		public String getPeriodoInicio() {
			return periodoInicio;
		}

		public void setPeriodoInicio(String periodoInicio) {
			this.periodoInicio = periodoInicio;
		}

		public String getPeriodoFin() {
			return periodoFin;
		}

		public void setPeriodoFin(String periodoFin) {
			this.periodoFin = periodoFin;
			
		}

		public Condicion devolverCondicion(){
			if(hayDatosVacios())
				throw new NullPointerException("Ningun tipo de condicion pudo ser creado en base a lo ingresado. Faltan datos para crear la condicion");
					
			if(esComparativa)
				condicion = new CondicionComparativa();
			else
				condicion = new CondicionTaxativa();
		
			if(antiguedadRequerida != null){
				condicion = new CondicionAntiguedad();
				((CondicionAntiguedad) condicion).setAniosNecesarios(antiguedadRequerida);
			}
			
			if(tipoOperacion != null){
				condicion = new CondicionGeneral();
				((CondicionGeneral) condicion).setTipoOperacion(tipoOperacion);
			}
			if(valorNumericoAComparar != null && periodoInicio != null && periodoFin != null){
				condicion = new CondicionSuperaValor();
				((CondicionSuperaValor) condicion).setValorSuperar(valorNumericoAComparar);
		}
			if(valorNumericoAComparar == null && periodoInicio != null && periodoFin != null && !esComparativa)
				condicion = new CondicionConsistenciaTiempo();
			
			if(esComparativa && periodoInicio != null && periodoFin != null)
				condicion = new ComparadorDesempenio();
			
			if(comparaAntiguedad)
				condicion = new ComparadorAntiguedad();
		
			
			if(condicion == null || condicion.getClass().getSuperclass() == Condicion.class)
				throw new NullPointerException("Ningun tipo de condicion pudo ser creado en base a lo ingresado. Faltan datos para crear la condicion");
			
			
			condicion.setNombreCondicion(nombre);
			condicion.setComparador(comparador);
			condicion.setIndicador(indicador);	
			condicion.setFinPeriodo(periodoFin);
			condicion.setInicioPeriodo(periodoInicio);
			
			return condicion;
		}

		private void setearValores(Condicion condicion2) {
			
		}

		private boolean hayDatosVacios() {
			
			return nombre == null;	// || indicador == null || comparador == null;
		}

		public void setEsComparativa(Boolean esComparativa) {
			this.esComparativa = esComparativa;
		}

		public void setComparaAntiguedad(Boolean comparaAntiguedad) {
			this.comparaAntiguedad = comparaAntiguedad;
		}	
}
