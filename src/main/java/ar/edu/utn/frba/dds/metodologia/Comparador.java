package ar.edu.utn.frba.dds.metodologia;

public enum Comparador {
	MAYOR,
	MENOR,
	IGUAL,
	MAYOREIGUAL,
	MENOREIGUAL;
	
	@Override
	public String toString() {
		return name();
	}
}
