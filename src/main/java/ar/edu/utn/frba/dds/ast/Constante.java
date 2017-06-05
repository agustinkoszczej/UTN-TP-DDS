package ar.edu.utn.frba.dds.ast;


public class Constante implements AST {
	private Integer constante;

	public Constante(String constante) {
		this.constante = Integer.parseInt(constante);
	}
	
	public Integer getConstante() {
		return constante;
	}
	
	public void setConstante(Integer constante) {
		this.constante = constante;
	}

	@Override
	public Integer resultado() {
		return constante;
	}
}
