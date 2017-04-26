package main.java.ar.edu.utn.frba.dds.modelo;

public enum Cuenta {
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
