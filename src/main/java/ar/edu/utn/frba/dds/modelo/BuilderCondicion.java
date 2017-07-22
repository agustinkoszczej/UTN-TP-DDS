package ar.edu.utn.frba.dds.modelo;


import ar.edu.utn.frba.dds.metodologia.Comparador;
import ar.edu.utn.frba.dds.metodologia.ComparadorAntiguedad;
import ar.edu.utn.frba.dds.metodologia.ComparadorDesempenio;
import ar.edu.utn.frba.dds.metodologia.Condicion;
import ar.edu.utn.frba.dds.metodologia.CondicionAntiguedad;
import ar.edu.utn.frba.dds.metodologia.CondicionCompararValor;
import ar.edu.utn.frba.dds.metodologia.CondicionConsistenciaTiempo;
import ar.edu.utn.frba.dds.metodologia.CondicionGeneral;
import ar.edu.utn.frba.dds.metodologia.TipoOperacion;

public class BuilderCondicion {
	private Condicion condicion;
		
	public Condicion devolverCondicionComparativa(String nombre, Comparador comparador, Boolean comparaAntiguedad, String periodoInicio, String periodoFin, Indicador indicador){
		if(periodoInicio != null && periodoFin != null)
			condicion = new ComparadorDesempenio();
		
		if(comparaAntiguedad)
			condicion = new ComparadorAntiguedad();
	
		
		if(condicion == null)
			throw new NullPointerException("Ningun tipo de condicion pudo ser creado en base a lo ingresado. Faltan datos para crear la condicion");
				
		condicion.setNombreCondicion(nombre);
		condicion.setComparador(comparador);
		condicion.setIndicador(indicador);	
		condicion.setFinPeriodo(periodoFin);
		condicion.setInicioPeriodo(periodoInicio);
		
		return condicion;
	}
	
	public Condicion devolverCondicionTaxativa(String nombre, Comparador comparador, String periodoInicio, String periodoFin, Integer antiguedadRequerida, TipoOperacion tipoOperacion, Integer valorNumericoAComparar, Indicador indicador){
		if(antiguedadRequerida != null){
			condicion = new CondicionAntiguedad();
			((CondicionAntiguedad) condicion).setAniosNecesarios(antiguedadRequerida);
		}
		
		if(tipoOperacion != null){
			condicion = new CondicionGeneral();
			((CondicionGeneral) condicion).setTipoOperacion(tipoOperacion);
		}
		if(valorNumericoAComparar != null && periodoInicio != null && periodoFin != null){
			condicion = new CondicionCompararValor();
			((CondicionCompararValor) condicion).setValorSuperar(valorNumericoAComparar);
		}
		if(valorNumericoAComparar == null && periodoInicio != null && periodoFin != null && comparador != null)
			condicion = new CondicionConsistenciaTiempo();
		
		if(condicion == null)
			throw new NullPointerException("Ningun tipo de condicion pudo ser creado en base a lo ingresado. Faltan datos para crear la condicion");

		
		condicion.setNombreCondicion(nombre);
		condicion.setComparador(comparador);
		condicion.setIndicador(indicador);	
		condicion.setFinPeriodo(periodoFin);
		condicion.setInicioPeriodo(periodoInicio);
		
		return condicion;
	}
}
