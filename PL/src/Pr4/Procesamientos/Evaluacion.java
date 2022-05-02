package Pr4.Procesamientos;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

//class Valores extends HashMap<String, Double> {
//}

public class Evaluacion extends ProcesamientoPorDefecto {
	/*private Set<String> variables;
	private double resulD;
	private int resulI;
	private boolean resulB;
	
	public Evaluacion() {
		variables = new HashSet<>();
	}

	public void procesa(Prog prog) {
		prog.decs().procesa(this);
		prog.insts().procesa(this);
		System.out.println(">>>>" + resul);
	}

	public void procesa(Decs_muchas decs) {
		decs.decs().procesa(this);
		decs.dec().procesa(this);
	}

	public void procesa(Decs_una decs) {
		decs.dec().procesa(this);
	}

	public void procesa(Dec dec) {
		if (variables.contains(dec.id().toString())) {
			throw new RuntimeException(
					"Variable ya definida " + dec.id() + ".Fila: " + dec.id().fila() + ", col: " + dec.id().col());
		} else {
			variables.add(dec.id().toString());
		}
	}
	
	public void procesa(Insts_muchas insts) {
		insts.insts().procesa(this);
		insts.inst().procesa(this);
	}
	
	public void procesa(Insts_una insts) {
		insts.inst().procesa(this);
	}
	
	public void procesa(Inst inst) {
		if (variables.contains(inst.id().toString())) {
			inst.exp().procesa(this);
		} else {
			throw new RuntimeException(
					"Variable no existente " + inst.id() + ".Fila: " + inst.id().fila() + ", col: " + inst.id().col());
		}
	}

	public void procesa(Suma exp) {
		exp.arg0().procesa(this);
		double v0 = resul;
		exp.arg1().procesa(this);
		resul += v0;
	}

	public void procesa(Resta exp) {
		exp.arg0().procesa(this);
		double v0 = resul;
		exp.arg1().procesa(this);
		resul = v0 - resul;
	}

	public void procesa(Mul exp) {
		exp.arg0().procesa(this);
		double v0 = resul;
		exp.arg1().procesa(this);
		resul *= v0;
	}

	public void procesa(Div exp) {
		exp.arg0().procesa(this);
		double v0 = resul;
		exp.arg1().procesa(this);
		resul = v0 / resul;
	}
	
	public void procesa(Mayor exp) {
		exp.arg0().procesa(this);
		double v0 = resul;
		exp.arg1().procesa(this);
		resul = v0 > resul;
	}
	
	public void procesa(Div exp) {
		exp.arg0().procesa(this);
		double v0 = resul;
		exp.arg1().procesa(this);
		resul = v0 / resul;
	}
	
	public void procesa(Div exp) {
		exp.arg0().procesa(this);
		double v0 = resul;
		exp.arg1().procesa(this);
		resul = v0 / resul;
	}
	
	

	public void procesa(Id exp) {
		Double val = valores.get(exp.id().toString());
		if (val == null)
			throw new RuntimeException("Constante no definida:" + exp.id().toString() + ". Fila: " + exp.id().fila()
					+ ", col: " + exp.id().col());
		else
			resul = val;
	}

	public void procesa(Num exp) {
		resul = Double.valueOf(exp.num().toString()).doubleValue();
	}*/
}