package ar.edu.utn.frba.dds.metodologia;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.uqbar.commons.utils.Observable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import ar.edu.utn.frba.dds.modelo.Empresa;
import ar.edu.utn.frba.dds.servicio.ServicioCuentas;

@Observable
@JsonIgnoreProperties(value = { "changeSupport", "claseCondicion" })
public class Metodologia {

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	private int contador; // la unica forma que encontre para que no rompa la funcion aplicaCondicionesComparativas
	// TODO ver si se puede poner local sin que sea un final
	@JsonProperty("nombre")
	protected String nombre;
	@JsonProperty("condiciones")
	protected List<Condicion> condiciones;

	public Metodologia() {
		this.condiciones = new ArrayList<Condicion>();
	}

	public List<CondicionComparativa> getCondicionesComparativas() {
		List<CondicionComparativa> condiciones2 = condiciones.stream()
				.filter(condicion -> !(condicion.esTaxativa()))
				.map(condicion -> (CondicionComparativa) condicion).collect(Collectors.toList());
		return condiciones2; 
	}

	public List<Empresa> aplicar(List<Empresa> empresas) {
		contador = 0;
		List<Empresa> empresasInvertibles = empresas.stream().filter(empresa -> aplicaCondicionesTaxativas(empresa))
				.collect(Collectors.toList());
		return empresasInvertibles.stream().sorted((empresa1, empresa2) -> (comparaAtodasLasEmpresas(empresa2) - comparaAtodasLasEmpresas(empresa1))).collect(Collectors.toList());
		
		//return empresasInvertibles.stream()
			//	.sorted((empresa1, empresa2) -> aplicaCondicionesComparativas(empresa1, empresa2))
				//.collect(Collectors.toList());
	}

	private int comparaAtodasLasEmpresas(Empresa empresa1) {
		List<Empresa> empresasAComparar = new ServicioCuentas().obtenerEmpresas();
		empresasAComparar.remove(empresa1);
		return empresasAComparar.stream().mapToInt(empresa -> aplicaCondicionesComparativas(empresa, empresa1)).sum();
	}

	private int aplicaCondicionesComparativas(Empresa empresa1, Empresa empresa2) {
		getCondicionesComparativas().stream().forEach(condicion -> {
			// if con contador para comparar las empresas. Si termina el forEach
			// con el contador positivo,
			// significa que conviene la empresa2
			if (empresa1 == condicion.cualEmpresaInvertir(empresa1, empresa2))
				contador-=condicion.getPeso();
			else if (empresa2 == condicion.cualEmpresaInvertir(empresa1, empresa2))
				contador+=condicion.getPeso();
		});
		return contador; 
	}

	private Boolean aplicaCondicionesTaxativas(Empresa empresa) {
		return getCondicionesTaxativas().stream().allMatch(condicion -> condicion.deberiaInvertirEn(empresa));
	}

	public List<CondicionTaxativa> getCondicionesTaxativas() {
		List<CondicionTaxativa> condiciones2 = condiciones.stream().filter(condicion -> condicion.esTaxativa())
				.map(condicion -> (CondicionTaxativa) condicion).collect(Collectors.toList());
		return condiciones2;
	}

	public void agregarCondicion(Condicion condicion) {
		condiciones.add(condicion);
	}

	public List<Condicion> getCondiciones() {
		return condiciones;
	}

	public void setCondiciones(List<Condicion> condiciones) {
		this.condiciones = condiciones;
	}
}
