public class Requerimientos {
	// métodos
	public void cargarCuenta(Empresa empresa, Cuenta cuenta, String periodo){
		Balance balance = empresa.buscarBalanceEnUnPeriodo(periodo);
		balance.agregarCuenta(cuenta);	
	}
	public double consultarValoresCuentas(Empresa empresa, String periodo){
		Balance balance = empresa.buscarBalanceEnUnPeriodo(periodo);
		return(balance.valoresCuentas(cuenta));	
	}
}

public class Empresa {
	private String nombre; // atributo
	private List<Balances> balances; // atributo
	// métodos
	public Balance buscarBalanceEnUnPeriodo(periodo){
		return (balances
		.stream()
		.filter(balance -> balance.getPeriodo() == periodo)
		.findFirst());
  }
}

public class Balance {
	private int anio; // atributo
	private String periodo; // atributo
	private List<Cuenta> cuentas; // atributo
	// métodos
	public void agregarCuenta(Cuenta cuenta){
		cuentas
		.add(cuenta);
	}
	public String getPeriodo(){
		return periodo;
	}
	public double valoresCuentas(){
		return (cuentas
		.map(cuenta -> cuenta.getValor())
		.sum());
	}
}

public class Cuenta {
	private double valor; // atributo
	// métodos
	public double getValor(){
		return valor;
	}
}
