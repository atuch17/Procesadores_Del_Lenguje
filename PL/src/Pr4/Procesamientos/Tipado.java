package Pr4.Procesamientos;

import Pr4.AST.TinyASint.*;

public class Tipado extends ProcesamientoPorDefecto {
	
    public void procesa(Prog_con_decs prog) {
        prog.decs().procesa(this);
        prog.insts().procesa(this);
        prog.type(ambos_ok(prog.decs().type(), prog.insts().type()));
    }

	public void procesa(Prog_sin_decs prog) {
        prog.insts().procesa(this);
		prog.type(prog.insts().type());
	}

	public void procesa(Decs_una decs) {
		decs.dec().procesa(this);
		decs.type(decs.dec().type());
	}

	public void procesa(Decs_muchas decs) {
		decs.decs().procesa(this);
		decs.dec().procesa(this);
		decs.type(ambos_ok(decs.decs().type(), decs.dec().type()));
	}

	public void procesa(Dec_var dec) {
		dec.tipo().procesa(this);
		dec.type(dec.tipo().type());
	}

	public void procesa(Dec_tipo dec) {
		dec.tipo().procesa(this);
		dec.type(dec.tipo().type());
	}

	public void procesa(Dec_proc dec) {
		dec.params().procesa(this);
		dec.bloque().procesa(this);
		dec.type(ambos_ok(dec.params().type(), dec.bloque().type()));
	}

	public void procesa(Params_ninguno params) {
		params.type(Tipos.OK);
	}

	public void procesa(Params_uno_f params) {
		params.param().procesa(this);
		params.type(params.param().type());
	}

	public void procesa(Params_muchos_f params) {
		params.params().procesa(this);
		params.param().procesa(this);
		params.type(ambos_ok(params.params().type(), params.param().type()));
	}

	public void procesa(Param_f_sin_amp param) {
		param.tipo().procesa(this);
		param.type(param.tipo().type());
	}

	public void procesa(Param_f_con_amp param) {
		param.tipo().procesa(this);
		param.type(param.tipo().type());
	}

	public void procesa(Tipo_id tipo) {
		if (tipo.vinculo() instanceof Dec_tipo) { //TODO ver si esto es legal
			tipo.type(Tipos.OK);
		} else {
			tipo.type(Tipos.ERROR);
		}
	}

	public void procesa(Tipo_array tipo) {
		try {
			int n = Integer.parseInt(tipo.valor().toString());
			if (n > 0) {
				tipo.tipo().procesa(this);
				tipo.type(tipo.tipo().type());
			} else {
				//TODO mostrar error
				tipo.type(Tipos.ERROR);
			}
		} catch (Exception e) {
			//TODO mostrar error
			tipo.type(Tipos.ERROR);
		}		
	}

	public void procesa(Tipo_registro tipo) {
		tipo.campos().procesa(this);
		tipo.type(tipo.campos().type());
	}

	public void procesa(Tipo_puntero tipo) {
		tipo.tipo().procesa(this);
		tipo.type(tipo.tipo().type());
	}

	public void procesa(Insts_muchas insts) {
		insts.insts().procesa(this);
		insts.inst().procesa(this);
		insts.type(ambos_ok(insts.insts().type(), insts.inst().type()));
	}

	public void procesa(Insts_una insts) {
		insts.inst().procesa(this);
		insts.type(insts.inst().type());
	}

	public void procesa(Insts_ninguna insts) {
		insts.type(Tipos.OK);
	}

	public void procesa(Inst_asig inst) {
		inst.exp1().procesa(this);
		inst.exp2().procesa(this);
		if (es_designador(inst.exp1()) && 
			son_compatibles(inst.exp1().type(), inst.exp2().type())) {
			inst.type(Tipos.OK);
		} else {
			//TODO mostrar error
			inst.type(Tipos.ERROR);
		}
	}

	public void procesa(Inst_ifthen inst) {
		inst.exp1().procesa(this);
		if (refEx(inst.exp1()) == Tipos.BOOL) {
			inst.insts().procesa(this);
			inst.type(inst.insts().type());
		} else {
			//TODO mostrar error
			inst.type(Tipos.ERROR);
		}
	}

	public void procesa(Inst_ifthenelse inst) {
		inst.exp1().procesa(this);
		if (refEx(inst.exp1()) == Tipos.BOOL) {
			inst.bloque1().procesa(this);
			inst.bloque2().procesa(this);
			inst.type(ambos_ok(inst.bloque1().type(), inst.bloque2().type()));
		} else {
			//TODO mostrar error
			inst.type(Tipos.ERROR);
		}
	}

	public void procesa(Inst_while inst) {
		inst.exp1().procesa(this);
		if (refEx(inst.exp1()) == Tipos.BOOL) {
			inst.insts().procesa(this);
			inst.type(inst.insts().type());
		} else {
			//TODO mostrar error
			inst.type(Tipos.ERROR);
		}
	}

	public void procesa(Inst_lectura inst) {
		inst.exp1().procesa(this);
		if (((refEx(inst.exp1()) == Tipos.INT) || (refEx(inst.exp1()) == Tipos.REAL) || 
			(refEx(inst.exp1()) == Tipos.STRING)) && es_designador(inst.exp1())) {
				inst.type(Tipos.OK);
		} else {
			//TODO mostrar error
			inst.type(Tipos.ERROR);
		}
	}

	public void procesa(Inst_escritura inst) {
		inst.exp1().procesa(this);
		if (((refEx(inst.exp1()) == Tipos.INT) || (refEx(inst.exp1()) == Tipos.REAL) || 
			(refEx(inst.exp1()) == Tipos.BOOL) || (refEx(inst.exp1()) == Tipos.STRING))) {
				inst.type(Tipos.OK);
		} else {
			//TODO mostrar error
			inst.type(Tipos.ERROR);
		}
	}

	public void procesa(Inst_new_line inst) {
		inst.type(Tipos.OK);
	}

	public void procesa(Inst_reserv_mem inst) {
		inst.exp1().procesa(this);
		if (refEx(inst.exp1()) == Tipos.POINTER) {
			inst.type(Tipos.OK);
		} else {
			//TODO mostrar error
			inst.type(Tipos.ERROR);
		}
	}

	public void procesa(Inst_lib_mem inst) {
		inst.exp1().procesa(this);
		if (refEx(inst.exp1()) == Tipos.POINTER) {
			inst.type(Tipos.OK);
		} else {
			//TODO mostrar error
			inst.type(Tipos.ERROR);
		}
	}

	public void procesa(Inst_invoc_proc inst) {
		if (inst.vinculo() instanceof Dec_proc) {
			Dec_proc dec = (Dec_proc) inst.vinculo();
			if (dec.params().size() != inst.params().size()) {
				//TODO mostrar error
				inst.type(Tipos.ERROR);
			} else {
				inst.type(chequeo_params(inst.params(), dec.params())); //TODO tipos mal
			}
		} else {
			//TODO mostrar error
			inst.type(Tipos.ERROR);
		}
	}

	public void procesa(Inst_comp inst) {
		inst.b().procesa(this);
		inst.type(inst.b().type());
	}

	public void procesa(Bloque_lleno inst) {
		inst.prog().procesa(this);
		inst.type(inst.prog().type());
	}

	public Tipos chequeo_params(Exprs_ninguna exprs, Params_ninguno params) {
		return Tipos.OK;
	}

	public Tipos chequeo_params(Exprs_una exprs, Params_uno_f params) {
		return chequeo_param(exprs.e(), params.param());
	}

	public Tipos chequeo_params(Exprs_muchas exprs, Params_muchos_f params) {
		return ambos_ok(chequeo_params(exprs.expresiones(), params.params()), chequeo_param(exprs.exp(), params.param()));
	}

	public Tipos chequeo_param(Exp e, Param_f_sin_amp param) {
		e.procesa(this);
		if (son_compatibles(param.type(), e.type())) {
			return Tipos.OK;
		} else {
			//TODO mostrar error
			return Tipos.ERROR;
		}
	}

	public Tipos chequeo_param(Exp e, Param_f_con_amp param) {
		e.procesa(this);
		if (es_designador(e) && son_compatibles(param.type(), e.type())) {
			return Tipos.OK;
		} else {
			//TODO mostrar error
			return Tipos.ERROR;
		}
	}

	public void procesa(Id id) {
		Dec dec = id.vinculo();
		if (dec instanceof Dec_var || dec instanceof Param_f_sin_amp || dec instanceof Param_f_con_amp) {
			id.type(dec.type());
		} else {
			//TODO mostrar error
			id.type(Tipos.ERROR);
		}
	}

	public void procesa(Sstring id) {
		id.type(Tipos.STRING);
	}

	public void procesa(Num_int num) {
		num.type(Tipos.INT);
	}

	public void procesa(Num_real num) {
		num.type(Tipos.REAL);
	}

	public void procesa(True t) {
		t.type(Tipos.BOOL);
	}

	public void procesa(False f) {
		f.type(Tipos.BOOL);
	}

	public void procesa(None n) {
		n.type(Tipos.NULL);
	}

/*chequeo_tipo(suma(E0, E1)) =
chequeo_tipo(E0)
chequeo_tipo(E1)
si ref!(E0.tipo) = int() && ref!(E1.tipo) = int() entonces
$.tipo = int() 
si no
	si (ref!(E0.tipo) = real() && (ref!(E1.tipo) = int() || ref!(E1.tipo) = real())) ||
(ref!(E1.tipo) = real() && (ref!(E0.tipo) = int() || ref!(E0.tipo) = real())) entonces
$.tipo = real()
si no
error
$.tipo = error() */
	public void procesa(Suma suma) {
		suma.arg0().procesa(this);
		suma.arg1().procesa(this);
		if (refEx(suma.arg0()) == Tipos.INT && refEx(suma.arg1()) == Tipos.INT) {
			suma.type(Tipos.INT);
		} else if ((refEx(suma.arg0()) == Tipos.REAL && (refEx(suma.arg1()) == Tipos.INT || refEx(suma.arg1()) == Tipos.REAL))
			 || (refEx(suma.arg1()) == Tipos.REAL && (refEx(suma.arg0()) == Tipos.INT || refEx(suma.arg0()) == Tipos.REAL))) {
			suma.type(Tipos.REAL);
		} else {
			//TODO mostrar error
			suma.type(Tipos.ERROR);
		}
	}

/*chequeo_tipo(resta(E0, E1)) =
chequeo_tipo(E0)
chequeo_tipo(E1)
si ref!(E0.tipo) = int() && ref!(E1.tipo) = int() entonces
$.tipo = int() 
si no
	si (ref!(E0.tipo) = real() && (ref!(E1.tipo) = int() || ref!(E1.tipo) = real())) ||
(ref!(E1.tipo) = real() && (ref!(E0.tipo) = int() || ref!(E0.tipo) = real())) entonces
$.tipo = real()
si no
error
$.tipo = error()*/
	public void procesa(Resta resta) {
		resta.arg0().procesa(this);
		resta.arg1().procesa(this);
		if (refEx(resta.arg0()) == Tipos.INT && refEx(resta.arg1()) == Tipos.INT) {
			resta.type(Tipos.INT);
		} else if ((refEx(resta.arg0()) == Tipos.REAL && (refEx(resta.arg1()) == Tipos.INT || refEx(resta.arg1()) == Tipos.REAL))
			 || (refEx(resta.arg1()) == Tipos.REAL && (refEx(resta.arg0()) == Tipos.INT || refEx(resta.arg0()) == Tipos.REAL))) {
			resta.type(Tipos.REAL);
		} else {
			//TODO mostrar error
			resta.type(Tipos.ERROR);
		}
	}

/*chequeo_tipo(mul(E0, E1)) =
chequeo_tipo(E0)
chequeo_tipo(E1)
si ref!(E0.tipo) = int() && ref!(E1.tipo) = int() entonces
$.tipo = int() 
si no
	si (ref!(E0.tipo) = real() && (ref!(E1.tipo) = int() || ref!(E1.tipo) = real())) ||
(ref!(E1.tipo) = real() && (ref!(E0.tipo) = int() || ref!(E0.tipo) = real())) entonces
$.tipo = real()
si no
error
$.tipo = error()*/
	public void procesa(Mul mul) {
		mul.arg0().procesa(this);
		mul.arg1().procesa(this);
		if (refEx(mul.arg0()) == Tipos.INT && refEx(mul.arg1()) == Tipos.INT) {
			mul.type(Tipos.INT);
		} else if ((refEx(mul.arg0()) == Tipos.REAL && (refEx(mul.arg1()) == Tipos.INT || refEx(mul.arg1()) == Tipos.REAL))
			 || (refEx(mul.arg1()) == Tipos.REAL && (refEx(mul.arg0()) == Tipos.INT || refEx(mul.arg0()) == Tipos.REAL))) {
			mul.type(Tipos.REAL);
		} else {
			//TODO mostrar error
			mul.type(Tipos.ERROR);
		}
	}

/*chequeo_tipo(div(E0, E1)) =
chequeo_tipo(E0)
chequeo_tipo(E1)
si ref!(E0.tipo) = int() && ref!(E1.tipo) = int() entonces
$.tipo = int() 
si no
	si (ref!(E0.tipo) = real() && (ref!(E1.tipo) = int() || ref!(E1.tipo) = real())) ||
(ref!(E1.tipo) = real() && (ref!(E0.tipo) = int() || ref!(E0.tipo) = real())) entonces
$.tipo = real()
si no
error
$.tipo = error()*/
	public void procesa(Div div) {
		div.arg0().procesa(this);
		div.arg1().procesa(this);
		if (refEx(div.arg0()) == Tipos.INT && refEx(div.arg1()) == Tipos.INT) {
			div.type(Tipos.INT);
		} else if ((refEx(div.arg0()) == Tipos.REAL && (refEx(div.arg1()) == Tipos.INT || refEx(div.arg1()) == Tipos.REAL))
			 || (refEx(div.arg1()) == Tipos.REAL && (refEx(div.arg0()) == Tipos.INT || refEx(div.arg0()) == Tipos.REAL))) {
			div.type(Tipos.REAL);
		} else {
			//TODO mostrar error
			div.type(Tipos.ERROR);
		}
	}

/*chequeo_tipo(mod(E0, E1)) =
chequeo_tipo(E0)
chequeo_tipo(E1)
si ref!(E0.tipo) = int() && ref!(E1.tipo) = int() entonces
$.tipo = int() 
si no
error
$.tipo = error()*/
	public void procesa(Mod mod) {
		mod.arg0().procesa(this);
		mod.arg1().procesa(this);
		if (refEx(mod.arg0()) == Tipos.INT && refEx(mod.arg1()) == Tipos.INT) {
			mod.type(Tipos.INT);
		} else {
			//TODO mostrar error
			mod.type(Tipos.ERROR);
		}
	}

/*chequeo_tipo(and(E0, E1)) =
chequeo_tipo(E0)
chequeo_tipo(E1)
si ref!(E0.tipo) = bool() && ref!(E1.tipo) = bool() entonces
$.tipo = bool() 
si no
error
$.tipo = error()*/
	public void procesa(And and) {
		and.arg0().procesa(this);
		and.arg1().procesa(this);
		if (refEx(and.arg0()) == Tipos.BOOL && refEx(and.arg1()) == Tipos.BOOL) {
			and.type(Tipos.BOOL);
		} else {
			//TODO mostrar error
			and.type(Tipos.ERROR);
		}
	}

/*chequeo_tipo(and(E0,E1)) =
chequeo_tipo(E0)
chequeo_tipo(E1)
si ref!(E0.tipo) = bool() && ref!(E1.tipo) = bool() entonces
$.tipo = bool() 
si no
error
$.tipo = error()*/
	public void procesa(Or or) {
		or.arg0().procesa(this);
		or.arg1().procesa(this);
		if (refEx(or.arg0()) == Tipos.BOOL && refEx(or.arg1()) == Tipos.BOOL) {
			or.type(Tipos.BOOL);
		} else {
			//TODO mostrar error
			or.type(Tipos.ERROR);
		}
	}

/*chequeo_tipo(menor(E0, E1)) =
chequeo_tipo(E0)
chequeo_tipo(E1)
si (ref!(E0.tipo) = int() || ref!(E0.tipo) = real()) && 
(ref!(E1.tipo) = int() || ref!(E1.tipo) = real()) entonces
$.tipo = bool() 
si no
	si ref!(E0.tipo) = bool() && ref!(E1.tipo) = bool() entonces
$.tipo = bool()
si no
	si ref!(E0.tipo) = string() && ref!(E1.tipo) = string() entonces
$.tipo = bool()
	si no
error
$.tipo = error()*/
	public void procesa(Menor menor) {
		menor.arg0().procesa(this);
		menor.arg1().procesa(this);
		if ((refEx(menor.arg0()) == Tipos.INT || refEx(menor.arg0()) == Tipos.REAL) && 
			(refEx(menor.arg1()) == Tipos.INT || refEx(menor.arg1()) == Tipos.REAL)) {
			menor.type(Tipos.BOOL);
		} else if (refEx(menor.arg0()) == Tipos.BOOL && refEx(menor.arg1()) == Tipos.BOOL) {
			menor.type(Tipos.BOOL);
		} else if (refEx(menor.arg0()) == Tipos.STRING && refEx(menor.arg1()) == Tipos.STRING) {
			menor.type(Tipos.BOOL);
		} else {
			//TODO mostrar error
			menor.type(Tipos.ERROR);
		}
	}

/*chequeo_tipo(mayor(E0, E1)) =
chequeo_tipo(E0)
chequeo_tipo(E1)
si (ref!(E0.tipo) = int() || ref!(E0.tipo) = real()) && 
(ref!(E1.tipo) = int() || ref!(E1.tipo) = real()) entonces
$.tipo = bool() 
si no
	si ref!(E0.tipo) = bool() && ref!(E1.tipo) = bool() entonces
$.tipo = bool()
si no
	si ref!(E0.tipo) = string() && ref!(E1.tipo) = string() entonces
$.tipo = bool()
	si no
error
$.tipo = error()*/
	public void procesa(Mayor mayor) {
		mayor.arg0().procesa(this);
		mayor.arg1().procesa(this);
		if ((refEx(mayor.arg0()) == Tipos.INT || refEx(mayor.arg0()) == Tipos.REAL) && 
			(refEx(mayor.arg1()) == Tipos.INT || refEx(mayor.arg1()) == Tipos.REAL)) {
			mayor.type(Tipos.BOOL);
		} else if (refEx(mayor.arg0()) == Tipos.BOOL && refEx(mayor.arg1()) == Tipos.BOOL) {
			mayor.type(Tipos.BOOL);
		} else if (refEx(mayor.arg0()) == Tipos.STRING && refEx(mayor.arg1()) == Tipos.STRING) {
			mayor.type(Tipos.BOOL);
		} else {
			//TODO mostrar error
			mayor.type(Tipos.ERROR);
		}
	}

/*chequeo_tipo(menor_eq(E0, E1)) =
chequeo_tipo(E0)
chequeo_tipo(E1)
si (ref!(E0.tipo) = int() || ref!(E0.tipo) = real()) && 
(ref!(E1.tipo) = int() || ref!(E1.tipo) = real()) entonces
$.tipo = bool() 
si no
	si ref!(E0.tipo) = bool() && ref!(E1.tipo) = bool() entonces
$.tipo = bool()
si no
	si ref!(E0.tipo) = string() && ref!(E1.tipo) = string() entonces
$.tipo = bool()
	si no
error
$.tipo = error()*/
	public void procesa(Menor_eq menor_eq) {
		menor_eq.arg0().procesa(this);
		menor_eq.arg1().procesa(this);
		if ((refEx(menor_eq.arg0()) == Tipos.INT || refEx(menor_eq.arg0()) == Tipos.REAL) && 
			(refEx(menor_eq.arg1()) == Tipos.INT || refEx(menor_eq.arg1()) == Tipos.REAL)) {
			menor_eq.type(Tipos.BOOL);
		} else if (refEx(menor_eq.arg0()) == Tipos.BOOL && refEx(menor_eq.arg1()) == Tipos.BOOL) {
			menor_eq.type(Tipos.BOOL);
		} else if (refEx(menor_eq.arg0()) == Tipos.STRING && refEx(menor_eq.arg1()) == Tipos.STRING) {
			menor_eq.type(Tipos.BOOL);
		} else {
			//TODO mostrar error
			menor_eq.type(Tipos.ERROR);
		}
	}

/*chequeo_tipo(mayor_eq(E0, E1)) =
chequeo_tipo(E0)
chequeo_tipo(E1)
si (ref!(E0.tipo) = int() || ref!(E0.tipo) = real()) && 
(ref!(E1.tipo) = int() || ref!(E1.tipo) = real()) entonces
$.tipo = bool() 
si no
	si ref!(E0.tipo) = bool() && ref!(E1.tipo) = bool() entonces
$.tipo = bool()
si no
	si ref!(E0.tipo) = string() && ref!(E1.tipo) = string() entonces
$.tipo = bool()
	si no
error
$.tipo = error()*/
	public void procesa(Mayor_eq mayor_eq) {
		mayor_eq.arg0().procesa(this);
		mayor_eq.arg1().procesa(this);
		if ((refEx(mayor_eq.arg0()) == Tipos.INT || refEx(mayor_eq.arg0()) == Tipos.REAL) && 
			(refEx(mayor_eq.arg1()) == Tipos.INT || refEx(mayor_eq.arg1()) == Tipos.REAL)) {
			mayor_eq.type(Tipos.BOOL);
		} else if (refEx(mayor_eq.arg0()) == Tipos.BOOL && refEx(mayor_eq.arg1()) == Tipos.BOOL) {
			mayor_eq.type(Tipos.BOOL);
		} else if (refEx(mayor_eq.arg0()) == Tipos.STRING && refEx(mayor_eq.arg1()) == Tipos.STRING) {
			mayor_eq.type(Tipos.BOOL);
		} else {
			//TODO mostrar error
			mayor_eq.type(Tipos.ERROR);
		}
	}

/*chequeo_tipo(comp(E0, E1)) =
chequeo_tipo(E0)
chequeo_tipo(E1)
si (ref!(E0.tipo) = int() || ref!(E0.tipo) = real()) && 
(ref!(E1.tipo) = int() || ref!(E1.tipo) = real()) entonces
$.tipo = bool() 
si no
	si ref!(E0.tipo) = bool() && ref!(E1.tipo) = bool() entonces
$.tipo = bool()
si no
	si ref!(E0.tipo) = string() && ref!(E1.tipo) = string() entonces
$.tipo = bool()
	si no
		si (ref!(E0.tipo) = tipo_puntero() || ref!(E0.tipo) = null()) && 
(ref!(E1.tipo) = tipo_puntero() || ref!(E1.tipo) = null()) entonces
$.tipo = bool()
si no
error
$.tipo = error()*/
	public void procesa(Comp comp) {
		comp.arg0().procesa(this);
		comp.arg1().procesa(this);
		if ((refEx(comp.arg0()) == Tipos.INT || refEx(comp.arg0()) == Tipos.REAL) && 
			(refEx(comp.arg1()) == Tipos.INT || refEx(comp.arg1()) == Tipos.REAL)) {
			comp.type(Tipos.BOOL);
		} else if (refEx(comp.arg0()) == Tipos.BOOL && refEx(comp.arg1()) == Tipos.BOOL) {
			comp.type(Tipos.BOOL);
		} else if (refEx(comp.arg0()) == Tipos.STRING && refEx(comp.arg1()) == Tipos.STRING) {
			comp.type(Tipos.BOOL);
		} else if ((refEx(comp.arg0()) == Tipos.POINTER || refEx(comp.arg0()) == Tipos.NULL) && 
			(refEx(comp.arg1()) == Tipos.POINTER || refEx(comp.arg1()) == Tipos.NULL)) {
			comp.type(Tipos.BOOL);
		} else {
			//TODO mostrar error
			comp.type(Tipos.ERROR);
		}
	}

/*chequeo_tipo(dist(E0, E1)) =
chequeo_tipo(E0)
chequeo_tipo(E1)
si (ref!(E0.tipo) = int() || ref!(E0.tipo) = real()) && 
(ref!(E1.tipo) = int() || ref!(E1.tipo) = real()) entonces
$.tipo = bool() 
si no
	si ref!(E0.tipo) = bool() && ref!(E1.tipo) = bool() entonces
$.tipo = bool()
si no
	si ref!(E0.tipo) = string() && ref!(E1.tipo) = string() entonces
$.tipo = bool()
	si no
		si (ref!(E0.tipo) = tipo_puntero() || ref!(E0.tipo) = null()) && 
(ref!(E1.tipo) = tipo_puntero() || ref!(E1.tipo) = null()) entonces
$.tipo = bool()
si no
error
$.tipo = error()*/
	public void procesa(Dist dist) {
		dist.arg0().procesa(this);
		dist.arg1().procesa(this);
		if ((refEx(dist.arg0()) == Tipos.INT || refEx(dist.arg0()) == Tipos.REAL) && 
			(refEx(dist.arg1()) == Tipos.INT || refEx(dist.arg1()) == Tipos.REAL)) {
			dist.type(Tipos.BOOL);
		} else if (refEx(dist.arg0()) == Tipos.BOOL && refEx(dist.arg1()) == Tipos.BOOL) {
			dist.type(Tipos.BOOL);
		} else if (refEx(dist.arg0()) == Tipos.STRING && refEx(dist.arg1()) == Tipos.STRING) {
			dist.type(Tipos.BOOL);
		} else if ((refEx(dist.arg0()) == Tipos.POINTER || refEx(dist.arg0()) == Tipos.NULL) && 
			(refEx(dist.arg1()) == Tipos.POINTER || refEx(dist.arg1()) == Tipos.NULL)) {
			dist.type(Tipos.BOOL);
		} else {
			//TODO mostrar error
			dist.type(Tipos.ERROR);
		}
	}

/*chequeo_tipo(menos(E)) =
chequeo_tipo(E)
si ref!(E.tipo) = int() entonces
$.tipo = int() 
si no
	si ref!(E.tipo) = real() entonces
$.tipo = real()
si no
error
$.tipo = error()*/
	public void procesa(Menos menos) {
		menos.arg().procesa(this);
		if (refEx(menos.arg()) == Tipos.INT) {
			menos.type(Tipos.INT);
		} else if (refEx(menos.arg()) == Tipos.REAL) {
			menos.type(Tipos.REAL);
		} else {
			//TODO mostrar error
			menos.type(Tipos.ERROR);
		}
	}

/*chequeo_tipo(not(E)) =
chequeo_tipo(E)
si ref!(E.tipo) = bool() entonces
$.tipo = bool() 
si no
error
$.tipo = error()*/
	public void procesa(Not not) {
		not.arg().procesa(this);
		if (refEx(not.arg()) == Tipos.BOOL) {
			not.type(Tipos.BOOL);
		} else {
			//TODO mostrar error
			not.type(Tipos.ERROR);
		}
	}

/*chequeo_tipo(asterix(E)) =
chequeo_tipo(E)
si ref!(E.tipo) = tipo_puntero(T) entonces
$.tipo = T 
si no
error
$.tipo = error()*/
	public void procesa(Asterix asterix) {
		asterix.arg().procesa(this);
		if (refEx(asterix.arg()) == Tipos.POINTER) {
			asterix.type(Tipos.POINTER); //TODO arreglar pointers
		} else {
			//TODO mostrar error
			asterix.type(Tipos.ERROR);
		}
	}

/*chequeo_tipo(corch(E0,E1)) =
chequeo_tipo(E0)
chequeo_tipo(E1)
si ref!(E0.tipo) = tipo_array(_, T) && ref!(E1.tipo) = int() entonces
$.tipo = T
si no
error
$.tipo = error()*/
	public void procesa(Corch corch) {
		corch.arg0().procesa(this);
		corch.arg1().procesa(this);
		if (refEx(corch.arg0()) == Tipos.ARRAY && refEx(corch.arg1()) == Tipos.INT) {
			corch.type(Tipos.ARRAY); //TODO arreglar arrays
		} else {
			//TODO mostrar error
			corch.type(Tipos.ERROR);
		}
	}

/*chequeo_tipo(punto(E, id)) =
chequeo_tipo(E)
si ref!(E.tipo) = tipo_registro(Campos) && existeCampo(Campos, id) entonces
$.tipo = tipoDeCampo(Campos, id)
si no
error
$.tipo = error()*/
	public void procesa(Punto punto) {
		punto.arg().procesa(this);
		if (refEx(punto.arg()) == Tipos.RECORD && existeCampo(punto.arg().type().campos(), punto.id())) {
			punto.type(tipoDeCampo(punto.arg().type().campos(), punto.id())); //TODO arreglar campos
		} else {
			//TODO mostrar error
			punto.type(Tipos.ERROR);
		}
	}

/*chequeo_tipo(flecha(E, id)) =
chequeo_tipo(E)
si ref!(E.tipo) = tipo_puntero(T) && ref!(T.tipo) = tipo_registro(Campos)
&& existeCampo(Campos, id) entonces
$.tipo = tipoDeCampo(Campos, id)
si no
error
$.tipo = error()*/
	public void procesa(Flecha flecha) {
		flecha.arg().procesa(this);
		if (refEx(flecha.arg()) == Tipos.POINTER && refEx(flecha.arg().type()) == Tipos.RECORD && 
			existeCampo(flecha.arg().type().campos(), flecha.id())) {
			flecha.type(tipoDeCampo(flecha.arg().type().campos(), flecha.id())); //TODO arreglar campos
		} else {
			//TODO mostrar error
			flecha.type(Tipos.ERROR);
		}
	}



/*ambos_ok(t0, t1) =
si t0 = ok() && t1 = ok()
return ok()
else
return error()*/
	private Tipos ambos_ok(Tipos t0, Tipos t1) {
		if (t0 == Tipos.OK && t1 == Tipos.OK)
			return Tipos.OK;
		else
			return Tipos.ERROR;
	}

	private Tipos refEx(Exp e) { //TODO implementar y cambiar por tipo
		return Tipos.NULL;
	}

	private boolean son_compatibles(Tipos t1, Tipos t2) {
		return true; //TODO implementar
	}

	private boolean es_designador(Exp e) {
		return true; //TODO implementar
	}
}
