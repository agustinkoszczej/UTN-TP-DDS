package ar.edu.utn.frba.dds.metodologia;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils.Collections;

import ar.edu.utn.frba.dds.modelo.Balance;
import ar.edu.utn.frba.dds.modelo.Empresa;
import ar.edu.utn.frba.dds.modelo.Indicador;

public class CondicionGeneral implements CondicionTaxativa {

	private Indicador indicador;
	private TipoOperacion tipoOperacion;
	private double valorASuperar;
	private Comparador comparador;
	
	public Indicador getIndicador() {
		return indicador;
	}

	public void setIndicador(Indicador indicador) {
		this.indicador = indicador;
	}

	public TipoOperacion getTipoOperacion() {
		return tipoOperacion;
	}

	public void setTipoOperacion(TipoOperacion tipoOperacion) {
		this.tipoOperacion = tipoOperacion;
	}

	public double getValorASuperar() {
		return valorASuperar;
	}

	public void setValorASuperar(double valorASuperar) {
		this.valorASuperar = valorASuperar;
	}

	public Comparador getComparador() {
		return comparador;
	}

	public void setComparador(Comparador comparador) {
		this.comparador = comparador;
	}

	@Override
	public Boolean deberiaInvertirEn(Empresa empresa) {
		double valor = 0;
		if(tipoOperacion.name() == "PROMEDIO")
			valor = promedioValor(empresa);
		if(tipoOperacion.name() == "MEDIANA")
			valor = medianaValor(empresa);
		if(tipoOperacion.name() == "SUMATORIA")
			valor = sumatoriaValor(empresa);
		return cumpleCondicion(valor, valorASuperar);
	}

	private Boolean cumpleCondicion(double valor, double valorASuperar2) {
		if(comparador.name() == "MAYOR")
			return valor > valorASuperar2;
		if(comparador.name() == "MENOR")
			return valor < valorASuperar2;
		else throw new NullPointerException("No es posible comparar con el comparador usado");
					
		
	}

	private double sumatoriaValor(Empresa empresa) {
		return empresa.getBalances()
				.stream()
				.mapToDouble(balance -> valorBalance(empresa, balance))
				.sum();
	}

	private double valorBalance(Empresa empresa, Balance balance) {
		
		
		try {
			return indicador.calcular(empresa, balance.getPeriodo());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	private double medianaValor(Empresa empresa) {
		List<Double> valores = new ArrayList<Double>();
		valores = (List<Double>) empresa.getBalances()
				.stream()
				.mapToDouble(balance -> valorBalance(empresa, balance))
				.sorted()
				.boxed().collect(Collectors.toList());
		if(valores.size() % 2 == 0)
			return medianaListaPar(valores);
		else

			return medianaListaImpar(valores);
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

	private double promedioValor(Empresa empresa) {
		try{
		return sumatoriaValor(empresa)/empresa.getBalances().size();
		}catch(ArithmeticException e){
			e.printStackTrace();
		}
		return 0;
	}

}
