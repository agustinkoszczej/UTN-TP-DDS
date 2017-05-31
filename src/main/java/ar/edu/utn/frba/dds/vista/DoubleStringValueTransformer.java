package ar.edu.utn.frba.dds.vista;

import org.uqbar.arena.bindings.ValueTransformer;

import com.ibm.icu.text.DecimalFormat;

public class DoubleStringValueTransformer implements ValueTransformer<Double, String> {

	@Override
	public Class<Double> getModelType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Class<String> getViewType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String modelToView(Double unValor) {
		//Esto lo hago porque sino aparece un numero del estilo "275E10" (con exponencial)
		DecimalFormat formatter = new DecimalFormat("##0.000");
		String s = formatter.format(unValor); 
		return "$ " + s;
	}

	@Override
	public Double viewToModel(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

}
