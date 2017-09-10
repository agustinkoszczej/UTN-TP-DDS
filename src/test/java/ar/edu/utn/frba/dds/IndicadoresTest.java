package ar.edu.utn.frba.dds;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import ar.edu.utn.frba.dds.expresion.ExpresionCompuesta;
import ar.edu.utn.frba.dds.expresion.ExpresionConstante;
import ar.edu.utn.frba.dds.expresion.ExpresionCuenta;
import ar.edu.utn.frba.dds.expresion.ExpresionIndicador;
import ar.edu.utn.frba.dds.expresion.Operacion;
import ar.edu.utn.frba.dds.modelo.Balance;
import ar.edu.utn.frba.dds.modelo.Empresa;
import ar.edu.utn.frba.dds.modelo.Indicador;
import ar.edu.utn.frba.dds.modelo.TipoDeCuenta;

public class IndicadoresTest {

	Empresa empresaPrueba;
	Indicador indicador1;
	ExpresionCuenta expCuentaEBITDA;
	ExpresionConstante expConstante;
	ExpresionCompuesta expCompuesta1;
	String mockJsonIndicadores = "mockIndicadores.json";
	List<Indicador> listaAGuardar;
	ObjectMapper mapper;
	
	@Before
	public void init() throws JsonMappingException, JsonGenerationException, IOException {
		empresaPrueba = new Empresa();
		Balance balance = new Balance();
		balance.setBalance_periodo("20170100");
		balance.setBalance_tipoCuenta(TipoDeCuenta.EBITDA);
		balance.setBalance_valor(new Double(25000));
		
		List<Balance> listaBalances = new ArrayList<Balance>();
		listaBalances.add(balance);
		empresaPrueba.setBalances(listaBalances);
		 expCuentaEBITDA = new ExpresionCuenta(TipoDeCuenta.EBITDA);
		 expConstante = new ExpresionConstante(7);
		
		expCompuesta1 = new ExpresionCompuesta(expCuentaEBITDA, Operacion.operacionSuma(), expConstante); // da 25007

		indicador1 = new Indicador("Indicador1", expCompuesta1);
		listaAGuardar = new ArrayList<Indicador>();
		listaAGuardar.add(indicador1);
		mapper = new ObjectMapper();
		mapper.writeValue(new File(mockJsonIndicadores), listaAGuardar);
			
	}
	
	@After
	public void terminate() throws IOException{
		listaAGuardar.clear();
		Files.delete(Paths.get(mockJsonIndicadores));
	}
	
	@Test
	public void calcularUnIndicador() throws Exception{
		
		
		Assert.assertEquals((Integer)25007, indicador1.calcular(empresaPrueba, "20170100"));
	}
	

	@Test
	public void leeIndicadorDeUnJson() throws Exception {
		String json = new String(Files.readAllBytes(Paths.get(mockJsonIndicadores)), StandardCharsets.UTF_8);
		ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.ACCEPT_EMPTY_ARRAY_AS_NULL_OBJECT, true);
		
		List<Indicador> listaALevantar = new ArrayList<Indicador>();
		TypeReference<List<Indicador>> mapIndicadoresList = new TypeReference<List<Indicador>>(){};
		listaALevantar = objectMapper.readValue(json, mapIndicadoresList);
		Indicador indicadorDeLista = listaALevantar.get(0);
		Assert.assertEquals((Integer)25007, indicadorDeLista.calcular(empresaPrueba, "20170100"));
	}
	
	@Test
	public void instanciarUnIndicadorCompuesto() throws Exception{
		
		ExpresionCompuesta expCompuesta2 = new ExpresionCompuesta(new ExpresionIndicador(indicador1), Operacion.operacionSuma(), expCuentaEBITDA);		
		
		Indicador indicador2 = new Indicador("Indicador2", expCompuesta2);
		
		Integer resultado = indicador2.calcular(empresaPrueba, "20170100");
		
		Assert.assertEquals((Integer)50007, resultado);
	}
	
	@Test
	public void mostrarLaExpresionDeUnIndicador(){
		ExpresionCompuesta expCompuesta2 = new ExpresionCompuesta(new ExpresionIndicador(indicador1), Operacion.operacionSuma(), expCuentaEBITDA);
		
		Indicador indicador2 = new Indicador("Indicador2", expCompuesta2);
		
		Assert.assertEquals("Indicador2 = Indicador1+EBITDA", indicador2.toString());
	}
	
	@Test
	public void mostrarLaListaDeIndicadoresDeUnIndicador(){
		
		ExpresionCompuesta expCompuesta2 = new ExpresionCompuesta(new ExpresionIndicador(indicador1), Operacion.operacionSuma(), expCuentaEBITDA);		
		
		Indicador indicador2 = new Indicador("Indicador2", expCompuesta2);

		Assert.assertEquals("[Indicador1 = EBITDA+7]", indicador2.listaIndicadores().toString());
	}
	
	@Test
	public void validarYActualizarIndicadoresInternosDeUnIndicador(){
		
		ExpresionCompuesta expCompuesta2 = new ExpresionCompuesta(new ExpresionIndicador(indicador1), Operacion.operacionSuma(), expCuentaEBITDA);
		
		Indicador indicador2 = new Indicador("Indicador2", expCompuesta2);
		
		List<Indicador> indicadoresActualizados = new ArrayList<Indicador>();
		//Creamos un un indicador con el mismo nombre como si lo hubieramos cargado del archivo
		//Este indicador nuevo tiene una formula diferente al anterior, pero el mismo nombre,
		//por lo que deberia actualizarse cuando llamamos a validarYActualizar
		Indicador indicadorCargado = new Indicador("Indicador1", expConstante);
		indicadoresActualizados.add(indicadorCargado);
		
		indicador2.validarYActualizarVariables(indicadoresActualizados);
		Assert.assertEquals("[Indicador1 = 7]", indicador2.listaIndicadores().toString());
		
	}
	
	@Test
	public void indicadorConIndicadoresQueNoEstanEnElArchivoTieneSonCorruptos() throws Exception{
		
		ExpresionCompuesta expCompuesta2 = new ExpresionCompuesta(new ExpresionIndicador(indicador1), Operacion.operacionSuma(), expCuentaEBITDA);
		
		Indicador indicador2 = new Indicador("Indicador2", expCompuesta2);
		
		List<Indicador> indicadoresActualizados = new ArrayList<Indicador>();
		//Creamos una lista vacia que seria como el archivo que leimos si estuviera vacio,
		//Por ende, nuestro indicador2, que contiene al indicador1, deberia detectar que �ste
		//no est� en la lista, y deberia romper al tratar de calcularse
		
		indicador2.validarYActualizarVariables(indicadoresActualizados);
		
		Assert.assertTrue(indicador2.tieneIndicadoresCorruptos());
	}
	
	@Test(expected = Exception.class)
	public void indicadorConIndicadoresQueNoEstanEnElArchivoDeberiaTirarExcepcion() throws Exception{
		
		ExpresionCompuesta expCompuesta2 = new ExpresionCompuesta(new ExpresionIndicador(indicador1), Operacion.operacionSuma(), expCuentaEBITDA);
		
		Indicador indicador2 = new Indicador("Indicador2", expCompuesta2);
		
		List<Indicador> indicadoresActualizados = new ArrayList<Indicador>();
		//Creamos una lista vacia que seria como el archivo que leimos si estuviera vacio,
		//Por ende, nuestro indicador2, que contiene al indicador1, deberia detectar que �ste
		//no est� en la lista, y deberia romper al tratar de calcularse
		
		indicador2.validarYActualizarVariables(indicadoresActualizados);
		
		indicador2.calcular(empresaPrueba, "20170100");
	}
	
	
	
	@Test
	public void indicadoresPersistidosDeberianSerLosMismosQueLosLeidosDelArchivo() throws JsonGenerationException, JsonMappingException, IOException{
		
		//Creamos indicadores
		ExpresionCompuesta expCompuesta2 = new ExpresionCompuesta(expCompuesta1, Operacion.operacionSuma(), expCuentaEBITDA);	
		
		ExpresionCompuesta expCompuesta3 = new ExpresionCompuesta(expCompuesta2, Operacion.operacionResta(), expCompuesta1);
		
		indicador1 = new Indicador("INDICADOR1", expCompuesta3);
		
		Indicador indicador2 = new Indicador("INDICADOR2", new ExpresionIndicador(indicador1));
		
		listaAGuardar.add(indicador2);
		
		//Guardamos en el archivo
		String archivoDePrueba = "probando.json";
		mapper.writeValue(new File(archivoDePrueba), listaAGuardar);
		
		
		//Levantamos del archivo
		String json = new String(Files.readAllBytes(Paths.get(archivoDePrueba)), StandardCharsets.UTF_8);
		ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.ACCEPT_EMPTY_ARRAY_AS_NULL_OBJECT, true);
		
		List<Indicador> listaALevantar = new ArrayList<Indicador>();
		
		TypeReference<List<Indicador>> mapIndicadoresList = new TypeReference<List<Indicador>>(){};
		listaALevantar = objectMapper.readValue(json, mapIndicadoresList);
		
		Files.delete(Paths.get(archivoDePrueba));
		
		Assert.assertEquals(listaAGuardar, listaALevantar);
		
	}
	
}
