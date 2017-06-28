package ar.edu.utn.frba.dds.metodologia;

public enum TipoOperacion {
	PROMEDIO,
	MEDIANA,
	SUMATORIA;
	
	@Override
	public String toString() {
		return name();
	}
}


