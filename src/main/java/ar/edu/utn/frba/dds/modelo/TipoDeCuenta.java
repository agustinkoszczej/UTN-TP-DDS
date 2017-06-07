package ar.edu.utn.frba.dds.modelo;

public enum TipoDeCuenta {
	EBITDA,
	FDS,
	FreeCashFlow,
	IngresoNetoOperacionesContinuas,
	IngresoNetoOperatcionesDiscontinuas;
	
	@Override
	public String toString() {
		return name();
	}
}
