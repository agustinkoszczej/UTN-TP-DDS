package ar.edu.utn.frba.dds;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import ar.edu.utn.frba.dds.metodologia.Condicion;
import ar.edu.utn.frba.dds.metodologia.CondicionSuperaValor;
import ar.edu.utn.frba.dds.modelo.RepositorioCondiciones;
import ar.edu.utn.frba.dds.servicio.ServicioCondiciones;

public class PersistenciaCondicion {
	List<Condicion> TEST_listaCondiciones; 
	
	@Before
	public void init() {
		
	}
	
	@Rule
	public TemporaryFolder folder = new TemporaryFolder();
	
	private void clearSingleton(String fileName) {
		String archivoJSON = folder.toString() + "\\" + fileName;
		ServicioCondiciones nuevoServicio = new ServicioCondiciones(archivoJSON);
			
		// Aplico reflection para limpiar las instancias de la singleton entre test
		Field repositorioCondiciones;
		Field servicioCondiciones;
		try {
			repositorioCondiciones = RepositorioCondiciones.class.getDeclaredField("repositorioCondiciones");
			repositorioCondiciones.setAccessible(true);
			repositorioCondiciones.set(null, null);
			servicioCondiciones = RepositorioCondiciones.class.getDeclaredField("servicioCondiciones");
			servicioCondiciones.setAccessible(true);
			servicioCondiciones.set(null, nuevoServicio);
		} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void instanciarRepositorioCondiciones() {
		RepositorioCondiciones repoCondiciones = RepositorioCondiciones.getInstance();
		// TODO: Ver que pasa si el archivo no existe o esta vacio
		Assert.assertNotEquals(null, repoCondiciones); 
	}

	@Test
	public void instanciarDosRepositorioCondicionesYValidarQueSeanLaMismaInstacia() {
		RepositorioCondiciones repoCond1 = RepositorioCondiciones.getInstance();
		RepositorioCondiciones repoCond2 = RepositorioCondiciones.getInstance();
		Assert.assertEquals(true, repoCond1 == repoCond2); 
	}
	
	@Test
	public void agregarCondicionesAlRepositorio() throws IOException {
		clearSingleton("test1.json");
		RepositorioCondiciones repoCondiciones = RepositorioCondiciones.getInstance();
		CondicionSuperaValor unaCondicion = new CondicionSuperaValor();
		unaCondicion.setNombreCondicion("prueba");
		TEST_listaCondiciones = new ArrayList<Condicion>();
		TEST_listaCondiciones.add(unaCondicion);
		repoCondiciones.agregarCondicion(unaCondicion);
		assertThat(repoCondiciones.getCondiciones(), is(TEST_listaCondiciones));
	}

	
	@Test
	public void agregarDosCondicionesAlRepositorio() throws IOException {
		clearSingleton("test2.json");
		RepositorioCondiciones repoCondiciones = RepositorioCondiciones.getInstance();
		CondicionSuperaValor unaCondicion = new CondicionSuperaValor();
		unaCondicion.setNombreCondicion("prueba");
		CondicionSuperaValor otraCondicion = new CondicionSuperaValor();
		otraCondicion.setNombreCondicion("test");
		TEST_listaCondiciones = new ArrayList<Condicion>();
		TEST_listaCondiciones.add(unaCondicion);
		TEST_listaCondiciones.add(otraCondicion);
		repoCondiciones.agregarCondicion(unaCondicion);
		repoCondiciones.agregarCondicion(otraCondicion);
		assertThat(repoCondiciones.getCondiciones(), is(TEST_listaCondiciones));
	}
/*
	@Test
	public void encontrarMetodologiaEnLista() throws IOException {
		clearSingleton("test3.json");
		RepositorioCondiciones repoMetod = RepositorioCondiciones.getInstance();
		Metodologia unaMetodologia = new Metodologia();
		unaMetodologia.setNombre("primera");
		Metodologia dosMetodologia = new Metodologia();
		dosMetodologia.setNombre("segundo");
		Metodologia tresMetodologia = new Metodologia();
		tresMetodologia.setNombre("tercera");
		repoMetod.agregarMetodologia(unaMetodologia);
		repoMetod.agregarMetodologia(dosMetodologia);
		repoMetod.agregarMetodologia(tresMetodologia);
		Assert.assertEquals(repoMetod.obtenerMetodologia("segundo"), dosMetodologia);
	}

	@Test
	public void reemplazarMetodologiaEnLista() throws IOException {
		clearSingleton("test4.json");
		RepositorioCondiciones repoMetod = RepositorioCondiciones.getInstance();
		Metodologia unaMetodologia = new Metodologia();
		unaMetodologia.setNombre("primera");
		Metodologia dosMetodologia = new Metodologia();
		dosMetodologia.setNombre("segundo");
		Metodologia tresMetodologia = new Metodologia();
		tresMetodologia.setNombre("tercera");
		repoMetod.agregarMetodologia(unaMetodologia);
		repoMetod.agregarMetodologia(dosMetodologia);
		repoMetod.agregarMetodologia(tresMetodologia);
		repoMetod.agregarMetodologia(dosMetodologia);
		Assert.assertEquals(repoMetod.obtenerMetodologia("tercera"), tresMetodologia);
	}

	// TODO: Que pasa con dos metodologias con igual nombre y distinto hash 
	
	@Test
	public void leerMetodologiasGuardadasDesdeArchivo() throws IOException {
		clearSingleton("test5.json");
		RepositorioCondiciones repoMetod = RepositorioCondiciones.getInstance();
		Metodologia unaMetodologia = new Metodologia();
		unaMetodologia.setNombre("primera");
		Metodologia dosMetodologia = new Metodologia();
		dosMetodologia.setNombre("segundo");
		Metodologia tresMetodologia = new Metodologia();
		tresMetodologia.setNombre("tercera");
		repoMetod.agregarMetodologia(unaMetodologia);
		repoMetod.agregarMetodologia(dosMetodologia);
		repoMetod.agregarMetodologia(tresMetodologia);
		clearSingleton("test5.json");
		repoMetod = RepositorioCondiciones.getInstance();
		Assert.assertEquals(repoMetod.obtenerMetodologia("segundo"), dosMetodologia);
	}
*/
}
