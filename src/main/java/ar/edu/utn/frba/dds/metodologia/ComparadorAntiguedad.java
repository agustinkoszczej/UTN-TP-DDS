package ar.edu.utn.frba.dds.metodologia;


import ar.edu.utn.frba.dds.modelo.Empresa;

public class ComparadorAntiguedad extends CondicionComparativa {
	
	


	public Empresa cualEmpresaInvertir(Empresa emp1, Empresa emp2) {
		//Opcion 1: Lo opera como lista de dos elementos
		/*
		List<Empresa> empresas = new ArrayList<Empresa>();
		empresas.add(emp1);
		empresas.add(emp2);
		return empresas
				.stream()
				.max((empresa1, empresa2) -> Double.compare(empresa1.getAntiguedad(), empresa2.getAntiguedad()))
				.get();
		*/
		int valor = esMejorValor(emp1.getAntiguedad(), emp2.getAntiguedad());
		
		if(valor == emp1.getAntiguedad())
			return emp1;
		if(valor == emp2.getAntiguedad())
			return emp2;
		return null;
		
	}

}
