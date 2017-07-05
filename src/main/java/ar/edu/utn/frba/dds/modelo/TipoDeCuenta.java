package ar.edu.utn.frba.dds.modelo;

public enum TipoDeCuenta {
	EBITDA,
	FDS,
	FreeCashFlow,
	IngresoNetoOperacionesContinuas,
	IngresoNetoOperatcionesDiscontinuas,
	IngresoNeto,
	Dividendos,
	CapitalTotal,
	Deuda,
	CostoTotal;
	
	@Override
	public String toString() {
		return name();
	}
}
