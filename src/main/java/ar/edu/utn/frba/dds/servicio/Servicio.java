package ar.edu.utn.frba.dds.servicio;

import java.io.IOException;
import java.util.List;

import ar.edu.utn.frba.dds.metodologia.Condicion;
import ar.edu.utn.frba.dds.metodologia.Metodologia;
import ar.edu.utn.frba.dds.modelo.Empresa;
import ar.edu.utn.frba.dds.modelo.Indicador;

public interface Servicio {

	public List<Empresa> obtenerEmpresas();
	public List<Indicador> obtenerIndicadores();
	public List<Metodologia> obtenerMetodologias();
	public List<Condicion> obtenerCondiciones();
	public void guardarMetodologia(Metodologia unaMetodologia) throws IOException;
	public void guardarIndicador(Indicador indicador) throws IOException;
	public void guardarCondicion(Condicion unaCondicion) throws IOException;
}
