package ar.edu.utn.frba.dds.metodologia;

import java.util.List;

import ar.edu.utn.frba.dds.modelo.Empresa;

public class Metodologia {
	
	private String nombre;
	private List<CondicionComparativa> condionesComparativas;
	private List<CondicionTaxativa> condionesTaxativas;
	
	public List<Empresa> aplicar(List<Empresa> empresas){
		return empresas;
	}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public List<CondicionComparativa> getCondionesComparativas() {
		return condionesComparativas;
	}
	public void setCondionesComparativas(List<CondicionComparativa> condionesComparativas) {
		this.condionesComparativas = condionesComparativas;
	}
	public List<CondicionTaxativa> getCondionesTaxativas() {
		return condionesTaxativas;
	}
	public void setCondionesTaxativas(List<CondicionTaxativa> condionesTaxativas) {
		this.condionesTaxativas = condionesTaxativas;
	}
}
