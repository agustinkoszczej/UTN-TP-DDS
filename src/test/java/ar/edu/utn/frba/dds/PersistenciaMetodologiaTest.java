package ar.edu.utn.frba.dds;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import ar.edu.utn.frba.dds.metodologia.Metodologia;
import ar.edu.utn.frba.dds.repositorios.RepositorioMetodologias;
import ar.edu.utn.frba.dds.servicio.ServicioMetodologias;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;


public class PersistenciaMetodologiaTest {

	List<Metodologia> TEST_listaMetodologias; 

	@Rule
	public TemporaryFolder folder = new TemporaryFolder();
	
	private void clearSingleton(String fileName) {
		String archivoJSON = folder.toString() + "\\" + fileName;
		ServicioMetodologias nuevoServicio = new ServicioMetodologias(archivoJSON);
			
		// Aplico reflection para limpiar las instancias de la singleton entre test
		Field repositorioMetodologias;
		Field servicioMetodologias;
		try {
			repositorioMetodologias = RepositorioMetodologias.class.getDeclaredField("repositorioMetodologias");
			repositorioMetodologias.setAccessible(true);
			repositorioMetodologias.set(null, null);
			servicioMetodologias = RepositorioMetodologias.class.getDeclaredField("servicioMetodologias");
			servicioMetodologias.setAccessible(true);
			servicioMetodologias.set(null, nuevoServicio);
		} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void instanciarRepositorioMetodologias() {
		RepositorioMetodologias repoMetod = RepositorioMetodologias.getInstance();
		// TODO: Ver que pasa si el archivo no existe o esta vacio
		Assert.assertNotEquals(null, repoMetod); 
	}

	@Test
	public void instanciarDosRepositorioMetodologiasYValidarQueSeanLaMismaInstacia() {
		RepositorioMetodologias repoMetod1 = RepositorioMetodologias.getInstance();
		RepositorioMetodologias repoMetod2 = RepositorioMetodologias.getInstance();
		Assert.assertEquals(true, repoMetod1 == repoMetod2); 
	}
	
	@Test
	public void agregarMetodologiaAlRepositorio() throws IOException {
		clearSingleton("test1.json");
		RepositorioMetodologias repoMetod = RepositorioMetodologias.getInstance();
		Metodologia unaMetodologia = new Metodologia();
		unaMetodologia.setNombre("prueba");
		TEST_listaMetodologias = new ArrayList<Metodologia>();
		TEST_listaMetodologias.add(unaMetodologia);
		repoMetod.agregarMetodologia(unaMetodologia);
		assertThat(repoMetod.getMetodologias(), is(TEST_listaMetodologias));
	}

	
	@Test
	public void agregarDosMetodologiasAlRepositorio() throws IOException {
		clearSingleton("test2.json");
		RepositorioMetodologias repoMetod = RepositorioMetodologias.getInstance();
		Metodologia unaMetodologia = new Metodologia();
		unaMetodologia.setNombre("prueba");
		Metodologia otraMetodologia = new Metodologia();
		otraMetodologia.setNombre("test");
		TEST_listaMetodologias = new ArrayList<Metodologia>();
		TEST_listaMetodologias.add(unaMetodologia);
		TEST_listaMetodologias.add(otraMetodologia);
		repoMetod.agregarMetodologia(unaMetodologia);
		repoMetod.agregarMetodologia(otraMetodologia);
		assertThat(repoMetod.getMetodologias(), is(TEST_listaMetodologias));
	}

	@Test
	public void encontrarMetodologiaEnLista() throws IOException {
		clearSingleton("test3.json");
		RepositorioMetodologias repoMetod = RepositorioMetodologias.getInstance();
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
		RepositorioMetodologias repoMetod = RepositorioMetodologias.getInstance();
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
	
	@Test // Este test esta fallando
	public void leerMetodologiasGuardadasDesdeArchivo() throws IOException {
		clearSingleton("test5.json");
		RepositorioMetodologias repoMetod = RepositorioMetodologias.getInstance();
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
		repoMetod = RepositorioMetodologias.getInstance();
		Assert.assertEquals(repoMetod.obtenerMetodologia("segundo"), dosMetodologia);
	}
	
	@After
	public void borrarTemporaryFolder() throws IOException {
		folder.delete();
	}

}
