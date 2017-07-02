package ar.edu.utn.frba.dds.metodologia;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.uqbar.commons.utils.Observable;

import ar.edu.utn.frba.dds.modelo.Empresa;

@Observable
public class Metodologia {
	
	private int contador; //la unica forma que encontre para que no rompa la funcion aplicaCondicionesComparativas
	//TODO ver si se puede poner local y que no me pida final
	protected String nombre;
	//protected List<CondicionTaxativa> condicionesTaxativas;
	//protected List<CondicionComparativa> condicionesComparativas;
	protected List<Condicion> condiciones;
	
	public Metodologia(){
		this.condiciones = new ArrayList<Condicion>();
	}
	
	public List<CondicionComparativa> getCondicionesComparativas() {
		//return condiciones.stream().filter(condicion -> condicion instanceof CondicionComparativa).collect(Collectors.toList());
		List<CondicionComparativa> condiciones2 = condiciones.stream().
				filter(condicion -> condicion instanceof CondicionComparativa)
				.map(condicion -> (CondicionComparativa) condicion).
				collect(Collectors.toList());
		return condiciones2.stream().filter(condicion -> condicion instanceof CondicionComparativa).collect(Collectors.toList());
	}

	
	
	public List<Empresa> aplicar(List<Empresa> empresas){
		List<Empresa> empresasInvertibles = empresas.stream().filter(empresa -> aplicaCondicionesTaxativas(empresa)).collect(Collectors.toList());
		empresasInvertibles.stream().sorted((empresa1, empresa2) -> aplicaCondicionesComparativas(empresa1, empresa2));
		return empresasInvertibles;
	}
	
	private int aplicaCondicionesComparativas(Empresa empresa1, Empresa empresa2) {
		getCondicionesComparativas().stream().forEach(condicion -> {
			//if con contador para comparar las empresas. Si termina el forEach con el contador positivo,  
			//significa que conviene la empresa2
			if(empresa1 == condicion.cualEmpresaInvertir(empresa1, empresa2))
				contador--;
			else if(empresa2 == condicion.cualEmpresaInvertir(empresa1, empresa2))
				contador++;
		});
		
		return contador; //si es positivo, quiere decir que la empresa2 conviene mas que la empresa1 (y cambia el orden)
						//si es negativo, quiere decir que la empresa1 conviene mas que la empresa2 (no cambia el orden)
		
	}

	private Boolean aplicaCondicionesTaxativas(Empresa empresa) {
		return null;//condicionesTaxativas.stream().allMatch(condicion -> condicion.deberiaInvertirEn(empresa));
	}

	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public List<CondicionTaxativa> getCondicionesTaxativas() {
		List<CondicionTaxativa> condiciones2 = condiciones.stream().
				filter(condicion -> condicion instanceof CondicionTaxativa)
				.map(condicion -> (CondicionTaxativa) condicion).
				collect(Collectors.toList());
		return condiciones2.stream().filter(condicion -> condicion instanceof CondicionTaxativa).collect(Collectors.toList());
	}
	
	
	public void agregarCondicion(Condicion condicion){
		System.out.println(condicion.getClass());
		//condiciones = new ArrayList<Condicion>();
		condiciones.add(condicion);
		//System.out.println(condicion.getClass() == CondicionAntiguedad.class);
		//System.out.println(condicion instanceof CondicionTaxativa);
	}
	/*
	public void setCondicionesComparativas(List<CondicionComparativa> condicionesComparativas) {
		this.condicionesComparativas = condicionesComparativas;
	}
	public void setCondicionesTaxativas(List<CondicionTaxativa> condicionesTaxativas) {
		this.condicionesTaxativas = condicionesTaxativas;
	}
	*/



	public List<Condicion> getCondiciones() {
		return condiciones;
	}
	
	public void setCondiciones(List<Condicion> condiciones){
		this.condiciones = condiciones;
	}
}
