package main.java.ar.edu.utn.frba.dds.vista;

import org.apache.commons.collections15.Transformer;

import com.ibm.icu.text.DecimalFormat;


public class DoubleStringTransformer implements Transformer<Double, String> {

	@Override
	public String transform(Double unValor) {
		//Esto lo hago porque sino aparece un numero del estilo "275E10" (con exponencial)
		DecimalFormat formatter = new DecimalFormat("##0.00");
		String s = formatter.format(unValor); 
		return "$ " + s;
	}

}
