package ar.edu.utn.frba.dds;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import ar.edu.utn.frba.dds.metodologia.Metodologia;
import ar.edu.utn.frba.dds.modelo.RepositorioMetodologias;
import ar.edu.utn.frba.dds.util.exceptions.InvalidTokenException;
import ar.edu.utn.frba.dds.util.exceptions.SyntaxErrorException;
import ar.edu.utn.frba.dds.util.exceptions.TypeExpresionException;

public class Persistencia {

	@Before
	public void init() {
		
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

/*	@Test
	public void instanciarNuevoRepositorio() {
		RepositorioMetodologias repoMetod1 = new RepositorioMetodologias();
		Assert.assertEquals(true, repoMetod1); 
	}
*/

	@Test
	public void agregarMetodologiaAlRepositorio() {
		RepositorioMetodologias repoMetod = RepositorioMetodologias.getInstance();
		Metodologia unaMetodologia = new Metodologia();
		unaMetodologia.setNombre("prueba");
		List<Metodologia> listaMetod = new ArrayList<Metodologia>();
		repoMetod.agregarMetodologia(unaMetodologia);
		//Assert.assertThat(repoMetod.getMetodologias(), is(listaMetod));
	}
}
