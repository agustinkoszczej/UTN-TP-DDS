package ar.edu.utn.frba.dds.metodologia;

import java.util.List;
import java.util.stream.Collectors;

import ar.edu.utn.frba.dds.modelo.Empresa;

public class Metodologia {
	
	private int contador; //la unica forma que encontre para que no rompa la funcion aplicaCondicionesComparativas
	//TODO ver si se puede poner local y que no me pida final
	private String nombre;
	private List<CondicionComparativa> condicionesComparativas;
	public List<CondicionComparativa> getCondicionesComparativas() {
		return condicionesComparativas;
	}

	public void setCondicionesComparativas(List<CondicionComparativa> condicionesComparativas) {
		this.condicionesComparativas = condicionesComparativas;
	}
	private List<CondicionTaxativa> condicionesTaxativas;
	
	public List<Empresa> aplicar(List<Empresa> empresas){
		List<Empresa> empresasInvertibles = empresas.stream().filter(empresa -> aplicaCondicionesTaxativas(empresa)).collect(Collectors.toList());
		empresasInvertibles.stream().sorted((empresa1, empresa2) -> aplicaCondicionesComparativas(empresa1, empresa2));
		return empresasInvertibles;
	}
	
	private int aplicaCondicionesComparativas(Empresa empresa1, Empresa empresa2) {
		condicionesComparativas.stream().forEach(condicion -> {
			if(empresa1 == condicion.cualEmpresaInvertir(empresa1, empresa2))
				contador--;
			else if(empresa2 == condicion.cualEmpresaInvertir(empresa1, empresa2))
				contador++;
		});
		
		return contador; //si es positivo, quiere decir que la empresa2 conviene mas que la empresa1 (y cambia el orden)
						//si es negativo, quiere decir que la empresa1 conviene mas que la empresa2 (no cambia el orden)
		
	}

	private Boolean aplicaCondicionesTaxativas(Empresa empresa) {
		return condicionesTaxativas.stream().allMatch(condicion -> condicion.deberiaInvertirEn(empresa));
	}

	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public List<CondicionTaxativa> getCondicionesTaxativas() {
		return condicionesTaxativas;
	}
	public void setCondicionesTaxativas(List<CondicionTaxativa> condionesTaxativas) {
		this.condicionesTaxativas = condionesTaxativas;
	}
}
