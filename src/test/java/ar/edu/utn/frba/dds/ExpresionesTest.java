package ar.edu.utn.frba.dds;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

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
import ar.edu.utn.frba.dds.expresion.Operacion;
import ar.edu.utn.frba.dds.modelo.Balance;
import ar.edu.utn.frba.dds.modelo.TipoDeCuenta;
import ar.edu.utn.frba.dds.modelo.Empresa;
import ar.edu.utn.frba.dds.modelo.Indicador;
import ar.edu.utn.frba.dds.modelo.RepositorioIndicadores;

public class ExpresionesTest {

	Empresa empresaprueba;
	
	@Before
	public void init() {
		empresaprueba = new Empresa();
		Balance balance = new Balance();
		balance.setPeriodo("20170100");
		balance.setTipoCuenta(TipoDeCuenta.EBITDA);
		balance.setValor(new Double(25000));
		
		List<Balance> listaBalances = new ArrayList<Balance>();
		listaBalances.add(balance);
		empresaprueba.setBalances(listaBalances);
	}
	
	
	@Test
	public void instanciarYCalcularUnaExpresionConstante(){
		Integer constante = 7;
		ExpresionConstante exp = new ExpresionConstante(constante);
		Integer resultado = exp.calculate(new Empresa(), "cualquiercosatotalnoimporta");
		Assert.assertEquals(constante,  resultado);
	}
	
	@Test
	public void instanciarYCalcularUnaExpresionCuenta(){
		ExpresionCuenta exp = new ExpresionCuenta(TipoDeCuenta.EBITDA);
		Integer resultado = exp.calculate(empresaprueba, "20170100");
		Assert.assertEquals((Integer)25000,  resultado);
	}
	
	@Test
	public void instanciarYCalcularUnaExpresionCompuesta(){
		ExpresionCuenta expCuentaEBITDA = new ExpresionCuenta(TipoDeCuenta.EBITDA);
		ExpresionConstante expConstante = new ExpresionConstante(7);
		
		ExpresionCompuesta exp = new ExpresionCompuesta(expCuentaEBITDA, Operacion.operacionSuma(), expConstante);
				
		Integer resultado = exp.calculate(empresaprueba, "20170100");
		Assert.assertEquals((Integer)25007,  resultado);
	}
	
	@Test
	public void instanciarYCalcularUnaExpresionCompuestaConExpresionesCompuestas(){
		ExpresionCuenta expCuentaEBITDA = new ExpresionCuenta(TipoDeCuenta.EBITDA);
		ExpresionConstante expConstante = new ExpresionConstante(7);
		
		ExpresionCompuesta expCompuesta1 = new ExpresionCompuesta(expCuentaEBITDA, Operacion.operacionSuma(), expConstante); // da 25007
		ExpresionCompuesta expCompuesta2 = new ExpresionCompuesta(expCompuesta1, Operacion.operacionSuma(), expCuentaEBITDA);		
		
		Integer resultado = expCompuesta2.calculate(empresaprueba, "20170100");
		
		Assert.assertEquals((Integer)50007, resultado);
	}
	
	@Test
	public void instanciarYCalcularUnaExpresionCompuestaConExpresionesCompuestasMasComplicada(){
		ExpresionCuenta expCuentaEBITDA = new ExpresionCuenta(TipoDeCuenta.EBITDA);
		ExpresionConstante expConstante = new ExpresionConstante(7);
		
		ExpresionCompuesta expCompuesta1 = new ExpresionCompuesta(expCuentaEBITDA, Operacion.operacionSuma(), expConstante); // da 25007
		ExpresionCompuesta expCompuesta2 = new ExpresionCompuesta(expCompuesta1, Operacion.operacionSuma(), expCuentaEBITDA); // da 50007		
		
		ExpresionCompuesta expCompuesta3 = new ExpresionCompuesta(expCompuesta2, Operacion.operacionResta(), expCompuesta1);
		
		Integer resultado = expCompuesta3.calculate(empresaprueba, "20170100");
		
		Assert.assertEquals((Integer)25000, resultado);
	}
	
	@Test
	public void transformarExpresionAString(){
		ExpresionCuenta expCuentaEBITDA = new ExpresionCuenta(TipoDeCuenta.EBITDA);
		ExpresionConstante expConstante = new ExpresionConstante(7);
		
		ExpresionCompuesta expCompuesta1 = new ExpresionCompuesta(expCuentaEBITDA, Operacion.operacionSuma(), expConstante); // da 25007
		ExpresionCompuesta expCompuesta2 = new ExpresionCompuesta(expCompuesta1, Operacion.operacionSuma(), expCuentaEBITDA); // da 50007		
		
		ExpresionCompuesta expCompuesta3 = new ExpresionCompuesta(expCompuesta2, Operacion.operacionResta(), expCompuesta1);
		
		String expCompuesta3EnString = expCompuesta3.toString();
		Assert.assertEquals("EBITDA+7+EBITDA-EBITDA+7", expCompuesta3EnString);
	}
	
	@Test
	public void listarLosElementosDeUnaExpresionConstante(){
		Integer constante = 7;
		ExpresionConstante exp = new ExpresionConstante(constante);
		
		List<Object> listaDeElementos = exp.listaDeElementos();
		Integer elemento = (Integer) listaDeElementos.get(0);
		Assert.assertEquals(constante, elemento);
	}
	
	@Test
	public void listarLosElementosDeUnaExpresionCuenta(){
		ExpresionCuenta exp = new ExpresionCuenta(TipoDeCuenta.EBITDA);
		
		List<Object> listaDeElementos = exp.listaDeElementos();
		TipoDeCuenta cuenta = (TipoDeCuenta) listaDeElementos.get(0);
		
		Assert.assertEquals(TipoDeCuenta.EBITDA, cuenta);
	}
	
	@Test
	public void listarLosElementosDeUnaExpresionCompuesta(){
		ExpresionCuenta expCuentaEBITDA = new ExpresionCuenta(TipoDeCuenta.EBITDA);
		ExpresionConstante expConstante = new ExpresionConstante(7);
		
		ExpresionCompuesta expCompuesta1 = new ExpresionCompuesta(expCuentaEBITDA, Operacion.operacionSuma(), expConstante); 
		ExpresionCompuesta expCompuesta2 = new ExpresionCompuesta(expCompuesta1, Operacion.operacionSuma(), expCuentaEBITDA);	
		
		ExpresionCompuesta expCompuesta3 = new ExpresionCompuesta(expCompuesta2, Operacion.operacionResta(), expCompuesta1);
		
		List<Object> listaHardcodeada = new ArrayList<Object>();
		listaHardcodeada.add(TipoDeCuenta.EBITDA);
		listaHardcodeada.add(Operacion.operacionSuma());
		listaHardcodeada.add(7);
		listaHardcodeada.add(Operacion.operacionSuma());
		listaHardcodeada.add(TipoDeCuenta.EBITDA);
		listaHardcodeada.add(Operacion.operacionResta());
		listaHardcodeada.add(TipoDeCuenta.EBITDA);
		listaHardcodeada.add(Operacion.operacionSuma());
		listaHardcodeada.add(7);
		
		List<Object> listaDeElementos = expCompuesta3.listaDeElementos();

		Assert.assertEquals(listaHardcodeada, listaDeElementos);
	}
	
	@Test
	public void probandoPersistencia() throws JsonGenerationException, JsonMappingException, IOException{
		ObjectMapper mapper = new ObjectMapper();
		
		ExpresionCuenta expCuentaEBITDA = new ExpresionCuenta(TipoDeCuenta.EBITDA);
		ExpresionConstante expConstante = new ExpresionConstante(7);
		
		ExpresionCompuesta expCompuesta1 = new ExpresionCompuesta(expCuentaEBITDA, Operacion.operacionSuma(), expConstante); 
		ExpresionCompuesta expCompuesta2 = new ExpresionCompuesta(expCompuesta1, Operacion.operacionSuma(), expCuentaEBITDA);	
		
		ExpresionCompuesta expCompuesta3 = new ExpresionCompuesta(expCompuesta2, Operacion.operacionResta(), expCompuesta1);
		

		
		Indicador indicador = new Indicador("numeroUno", expCompuesta3);
		
		RepositorioIndicadores.CargarYValidarIndicadores();
		System.out.println(RepositorioIndicadores.indicadores);
		
		
		
		
		
		
		
		
		/*
		System.out.println(indicador.formula());
		List<Indicador> listaaaa = new ArrayList<Indicador>();
		listaaaa.add(indicador);
		listaaaa.add(indicador);
		//guardarlos
		mapper.writeValue(new File("probando.json"), listaaaa);
		
		//traerlo
		String json = new String(Files.readAllBytes(Paths.get("probando.json")), StandardCharsets.UTF_8);
		ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.ACCEPT_EMPTY_ARRAY_AS_NULL_OBJECT, true);
		
		
		List<Indicador> list = new ArrayList<Indicador>();
		
		TypeReference<List<Indicador>> mapIndicadoresList = new TypeReference<List<Indicador>>(){};
		list = objectMapper.readValue(json, mapIndicadoresList);
		
		Indicador indicadorLevantado = list.get(0);
		
		/*
		Indicador indicadorLevantado = objectMapper.readValue(json, new TypeReference<Indicador>(){});
		*/
		
	}
	
}
