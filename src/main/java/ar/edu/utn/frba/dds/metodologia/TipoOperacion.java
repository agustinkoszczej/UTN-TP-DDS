package ar.edu.utn.frba.dds.metodologia;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.uqbar.commons.utils.Observable;

import ar.edu.utn.frba.dds.modelo.Empresa;

@Observable
public class TipoOperacion {
	
	public enum Operaciones {PROMEDIO, MEDIANA, SUMATORIA};
	
	public Operaciones tipoOperacion;
	public String nombre;
	
	public Operaciones getTipoOperacion() {
		return tipoOperacion;
	}

	public void setTipoOperacion(Operaciones tipoOperacion) {
		this.tipoOperacion = tipoOperacion;
		this.nombre = tipoOperacion.name();
	}
	
	public String getNombre(){
		return nombre;
	}

	public double valor(Empresa empresa, CondicionTaxativa condicion){
		double valor = -1;
		if(tipoOperacion.name() == "PROMEDIO")
			valor = promedioValor(empresa, condicion);
		if(tipoOperacion.name() == "MEDIANA")
			valor = medianaValor(empresa, condicion);
		if(tipoOperacion.name() == "SUMATORIA")
			valor = sumatoriaValor(empresa, condicion);
		return valor;
	}
	
	private double promedioValor(Empresa empresa, CondicionTaxativa condicion) {
		try{
		return sumatoriaValor(empresa, condicion)/empresa.getBalances().size();
		}catch(ArithmeticException e){
			e.printStackTrace();
		}
		return 0;
	}
	
	private double medianaValor(Empresa empresa, CondicionTaxativa condicion) {
		List<Double> valores = new ArrayList<Double>();
		valores = (List<Double>) empresa.getBalances()
				.stream()
				.mapToDouble(balance -> (double) condicion.valorBalance(empresa, balance))
				.sorted()
				.boxed().collect(Collectors.toList());
		if(valores.size() % 2 == 0)
			return medianaListaPar(valores);
		else

			return medianaListaImpar(valores);
	}
	
	private double sumatoriaValor(Empresa empresa, CondicionTaxativa condicion) {
		return empresa.getBalances()
				.stream()
				.mapToDouble(balance -> condicion.valorBalance(empresa, balance))
				.sum();
	}
	
	private Double medianaListaPar(List<Double> valores) {

		double valorInferior = valores.get((int) valores.size()/2-1);
		double valorSuperior = valores.get((int) valores.size()/2);
		return (valorInferior + valorSuperior)/2;
	}

	private Double medianaListaImpar(List<Double> valores) {
		double valor = valores.get((int)(valores.size()/2));
		return valor;
	}	
}


