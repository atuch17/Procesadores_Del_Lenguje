package Pr4.Procesamientos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import Pr4.AST.TinyASint.*;

public class Vinculacion extends ProcesamientoPorDefecto {
	List<HashMap<StringLocalizado, Dec>> ts;
	boolean algun_error;
	
	public Vinculacion() {
		ts = new ArrayList<HashMap<StringLocalizado, Dec>>();
		ts.add(new HashMap<StringLocalizado, Dec>());
		algun_error = false;
	}

	public boolean algun_error() {
		return algun_error;
	}
	
	private void recolecta(StringLocalizado id, Dec dec) {
		if (id_duplicado(id)){
			System.out.println("Error: id duplicado");
			algun_error = true;
		}
		else
			ts.get(ts.size()-1).put(id, dec);
	}
	private boolean id_duplicado(StringLocalizado id) {
		if (ts.get(ts.size()-1).containsKey(id))
			return true;
		return false;
	}
	private boolean existe_id(StringLocalizado id) {
		for (int i = ts.size()-1; i >= 0; i--) {
			HashMap<StringLocalizado, Dec> hm = ts.get(i);
			if (hm.containsKey(id))
				return true;
		}
		return false;
	}
	private Dec valorDe(StringLocalizado id) {
		for (int i = ts.size()-1; i >= 0; i--) {
			HashMap<StringLocalizado, Dec> hm = ts.get(i);
			if (hm.containsKey(id))
				return hm.get(id);
		}
		return null;
	}

	public void procesa(Prog_con_decs prog) {
		prog.decs().procesa(this);
		prog.decs().vincula_decs_fase2(this);
		prog.decs().vincula_procs(this);
		prog.insts().procesa(this);
	}
	public void vincula_decs_fase2(Prog_con_decs prog) {
		prog.decs().vincula_decs_fase2(this);
	}
	public void vincula_procs(Prog_con_decs prog) {
		prog.decs().vincula_procs(this);
	}

	public void procesa(Prog_sin_decs prog) {
		prog.insts().procesa(this);
	}

	public void procesa(Decs_una decs) {
		decs.dec().procesa(this);
	}
	public void vincula_decs_fase2(Decs_una decs) {
		decs.dec().vincula_decs_fase2(this);
	}
	public void vincula_procs(Decs_una decs) {
		decs.dec().vincula_procs(this);
	}

	public void procesa(Decs_muchas decs) {
		decs.decs().procesa(this);
		decs.dec().procesa(this);
	}
	public void vincula_decs_fase2(Decs_muchas decs) {
		decs.decs().vincula_decs_fase2(this);
		decs.dec().vincula_decs_fase2(this);
	}
	public void vincula_procs(Decs_muchas decs) {
		decs.decs().vincula_procs(this);
		decs.dec().vincula_procs(this);
	}

	public void procesa(Dec_var dec) {
		dec.tipo().procesa(this);
		recolecta(dec.id(), dec);
	}
	public void vincula_decs_fase2(Dec_var dec) {
		dec.tipo().vincula_decs_fase2(this);
	}

	public void procesa(Dec_tipo dec) {
		dec.tipo().procesa(this);
		recolecta(dec.id(), dec);
	}
	public void vincula_decs_fase2(Dec_tipo dec) {
		dec.tipo().vincula_decs_fase2(this);
	}

	public void procesa(Dec_proc dec) {
		recolecta(dec.id(), dec);
	}
	public void vincula_procs(Dec_proc dec) {
		ts.add(new HashMap<StringLocalizado, Dec>());
		dec.params().procesa(this);
		Bloque b = dec.bloque();
		if (b.esta_lleno()) {
			Prog p = ((Bloque_lleno) b).prog();
			if (p.tiene_decs()) {
				Prog_con_decs prog = (Prog_con_decs) p;
				prog.decs().procesa(this);
				dec.params().vincula_decs_fase2(this);
				prog.decs().vincula_decs_fase2(this);
				prog.decs().vincula_procs(this);
				prog.insts().procesa(this);
			}
			else {
				Prog_sin_decs prog = (Prog_sin_decs) p;
				dec.params().vincula_decs_fase2(this);
				prog.insts().procesa(this);
			}
		}
		else
			dec.params().vincula_decs_fase2(this);
		ts.remove(ts.size() - 1);
	}

	public void procesa(Params_uno_f p) {
		p.param().procesa(this);
	}
	public void vincula_decs_fase2(Params_uno_f p) {
		p.param().vincula_decs_fase2(this);
	}
	public void procesa(Params_muchos_f p) {
		p.params().procesa(this);
		p.param().procesa(this);
	}
	public void vincula_decs_fase2(Params_muchos_f p) {
		p.params().vincula_decs_fase2(this);
		p.param().vincula_decs_fase2(this);
	}
	public void procesa(Param_f_sin_amp p) {
		p.tipo().procesa(this);
		recolecta(p.id(), p);
	}
	public void vincula_decs_fase2(Param_f_sin_amp p) {
		p.tipo().vincula_decs_fase2(this);
	}
	public void procesa(Param_f_con_amp p) {
		p.tipo().procesa(this);
		recolecta(p.id(), p);
	}
	public void vincula_decs_fase2(Param_f_con_amp p) {
		p.tipo().vincula_decs_fase2(this);
	}

	public void procesa(Insts_una insts) {
		insts.inst().procesa(this);
	}
	public void procesa(Insts_muchas insts) {
		insts.insts().procesa(this);
		insts.inst().procesa(this);
	}
	public void procesa(Inst_asig inst) {
		inst.exp1().procesa(this);
		inst.exp2().procesa(this);
	}
	public void procesa(Inst_ifthen inst) {
		inst.exp1().procesa(this);
		inst.insts().procesa(this);
	}
	public void procesa(Inst_ifthenelse inst) {
		inst.exp1().procesa(this);
		inst.bloque1().procesa(this);
		inst.bloque2().procesa(this);
	}
	public void procesa(Inst_while inst) {
		inst.exp1().procesa(this);
		inst.insts().procesa(this);
	}
	public void procesa(Inst_lectura inst) {
		inst.exp1().procesa(this);
	}
	public void procesa(Inst_escritura inst) {
		inst.exp1().procesa(this);
	}
	public void procesa(Inst_reserv_mem inst) {
		inst.exp1().procesa(this);
	}
	public void procesa(Inst_lib_mem inst) {
		inst.exp1().procesa(this);
	}
	public void procesa(Inst_invoc_proc inst) {
		if (existe_id(inst.id()))
			inst.vinculo(valorDe(inst.id()));
		else{
			System.out.println("Error: procedimiento no declarado");
			algun_error = true;
		}
		inst.params().procesa(this);
	}
	public void procesa(Inst_comp inst) {
		ts.add(new HashMap<StringLocalizado, Dec>());
		inst.b().procesa(this);
		ts.remove(ts.size() - 1);
	}
	public void procesa(Exprs_una e) {
		e.exp().procesa(this);
	}
	public void procesa(Exprs_muchas e) {
		e.expresiones().procesa(this);
		e.exp().procesa(this);
	}

	public void procesa(Tipo_id tipo) {
		if (existe_id(tipo.id()))
			tipo.vinculo(valorDe(tipo.id()));
		else {
			System.out.println("Error: id no declarado");
			algun_error = true;
		}
	}
	public void procesa(Tipo_array tipo) {
		tipo.tipo().procesa(this);
	}
	public void vincula_decs_fase2(Tipo_array tipo) {
		tipo.tipo().vincula_decs_fase2(this);
	}
	public void procesa(Tipo_registro tipo) {
		tipo.campos().procesa(this);
	}
	public void vincula_decs_fase2(Tipo_registro tipo) {
		tipo.campos().vincula_decs_fase2(this);
	}
	public void procesa(Tipo_puntero tipo) {
		if (!tipo.tipo().es_id())
			tipo.tipo().procesa(this);
	}
	public void vincula_decs_fase2(Tipo_puntero tipo) {
		if (tipo.tipo().es_id())
			tipo.tipo().procesa(this);
		else
			tipo.tipo().vincula_decs_fase2(this);
	}
	public void procesa(Campos_uno c) {
		c.campo().procesa(this);
	}
	public void vincula_decs_fase2(Campos_uno c) {
		c.campo().vincula_decs_fase2(this);
	}
	public void procesa(Campos_muchos c) {
		c.campos().procesa(this);
		c.campo().procesa(this);
	}
	public void vincula_decs_fase2(Campos_muchos c) {
		c.campos().vincula_decs_fase2(this);
		c.campo().vincula_decs_fase2(this);
	}
	public void procesa(Campo c) {
		c.tipo().procesa(this);
	}
	public void vincula_decs_fase2(Campo c) {
		c.tipo().vincula_decs_fase2(this);
	}
	public void procesa(Bloque_lleno b) {
		b.prog().procesa(this);
	}
	public void vincula_decs_fase2(Bloque_lleno b) {
		b.prog().vincula_decs_fase2(this);
	}
	public void procesa(Id exp) {
		if (existe_id(exp.id()))
			exp.vinculo(valorDe(exp.id()));
		else {
			System.out.println("Error: id no declarado");
			algun_error = true;
		}
	}
	public void procesa(Suma exp) {
		exp.arg0().procesa(this);
		exp.arg1().procesa(this);
	}
	public void procesa(Resta exp) {
		exp.arg0().procesa(this);
		exp.arg1().procesa(this);
	}
	public void procesa(And exp) {
		exp.arg0().procesa(this);
		exp.arg1().procesa(this);
	}
	public void procesa(Or exp) {
		exp.arg0().procesa(this);
		exp.arg1().procesa(this);
	}
	public void procesa(Mayor exp) {
		exp.arg0().procesa(this);
		exp.arg1().procesa(this);
	}
	public void procesa(Menor exp) {
		exp.arg0().procesa(this);
		exp.arg1().procesa(this);
	}
	public void procesa(Mayor_eq exp) {
		exp.arg0().procesa(this);
		exp.arg1().procesa(this);
	}
	public void procesa(Menor_eq exp) {
		exp.arg0().procesa(this);
		exp.arg1().procesa(this);
	}
	public void procesa(Comp exp) {
		exp.arg0().procesa(this);
		exp.arg1().procesa(this);
	}
	public void procesa(Dist exp) {
		exp.arg0().procesa(this);
		exp.arg1().procesa(this);
	}
	public void procesa(Mul exp) {
		exp.arg0().procesa(this);
		exp.arg1().procesa(this);
	}
	public void procesa(Div exp) {
		exp.arg0().procesa(this);
		exp.arg1().procesa(this);
	}
	public void procesa(Mod exp) {
		exp.arg0().procesa(this);
		exp.arg1().procesa(this);
	}
	public void procesa(Corch exp) {
		exp.arg0().procesa(this);
		exp.arg1().procesa(this);
	}
	public void procesa(Punto exp) {
		exp.arg().procesa(this);
	}
	public void procesa(Flecha exp) {
		exp.arg().procesa(this);
	}
	public void procesa(Menos exp) {
		exp.arg().procesa(this);
	}
	public void procesa(Not exp) {
		exp.arg().procesa(this);
	}
	public void procesa(Asterix exp) {
		exp.arg().procesa(this);
	}
}
