package Pr4.Procesamientos;

import Pr4.AST.TinyASint.*;

public class Tipado extends ProcesamientoPorDefecto {
	private Tipo tipoError;
	private Tipo tipoOK;
	private Tipo tipoString;
	private Tipo tipoInt;
	private Tipo tipoReal;
	private Tipo tipoBool;
	private Tipo tipoNull;
	private boolean algun_error;

	public Tipado() {
		tipoError = new Tipo_error();
		tipoOK = new Tipo_ok();
		tipoString = new Tipo_cadena();
		tipoInt = new Tipo_int();
		tipoReal = new Tipo_real();
		tipoBool = new Tipo_bool();
		tipoNull = new Tipo_null();
		algun_error = false;
	}

	public boolean algun_error() {
		return algun_error;
	}

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
		params.type(tipoOK);
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
		if (tipo.vinculo().es_dec_tipo()) {
			tipo.type(tipoOK);
		} else {
			System.out.println("Error: tipo no declarado: " + tipo.id());
			algun_error = true;
			tipo.type(tipoError);
		}
	}

	public void procesa(Tipo_int tipo) {
		tipo.type(tipoOK);
	}

	public void procesa(Tipo_real tipo) {
		tipo.type(tipoOK);
	}

	public void procesa(Tipo_bool tipo) {
		tipo.type(tipoOK);
	}

	public void procesa(Tipo_cadena tipo) {
		tipo.type(tipoOK);
	}

	public void procesa(Tipo_array tipo) {
		try {
			int n = Integer.parseInt(tipo.valor().toString());
			if (n > 0) {
				tipo.tipo().procesa(this);
				tipo.type(tipo.tipo());
			} else {
				System.out.println("Error: tamano de array no valido: " + tipo.valor());
				algun_error = true;
				tipo.type(tipoError);
			}
		} catch (Exception e) {
			System.out.println("Error: tipo de array no valido: " + tipo.valor());
			algun_error = true;
			tipo.type(tipoError);
		}		
	}

	public void procesa(Tipo_registro tipo) {
		tipo.campos().procesa(this);
		tipo.type(tipo.campos().type());
	}

	public void procesa(Campos_uno c) {
		c.campo().procesa(this);
		c.type(c.campo().type());
	}

	public void procesa(Campos_muchos c) {
		c.campos().procesa(this);
		c.campo().procesa(this);
		c.type(ambos_ok(c.campos().type(), c.campo().type()));
	}

	public void procesa(Campo c) {
		c.tipo().procesa(this);
		c.type(c.tipo().type());
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
		insts.type(tipoOK);
	}

	public void procesa(Inst_asig inst) {
		inst.exp1().procesa(this);
		inst.exp2().procesa(this);
		if (inst.exp1().es_designador() && son_compatibles(inst.exp1().type(), inst.exp2().type())) {
			inst.type(tipoOK);
		} else {
			System.out.println("Error: asignacion de tipos incompatibles");
			algun_error = true;
			inst.type(tipoError);
		}
	}

	public void procesa(Inst_ifthen inst) {
		inst.exp1().procesa(this);
		if (inst.exp1().type().refEx().es_booleano()) {
			inst.insts().procesa(this);
			inst.type(inst.insts().type());
		} else {
			System.out.println("Error: condicion no booleana");
			algun_error = true;
			inst.type(tipoError);
		}
	}

	public void procesa(Inst_ifthenelse inst) {
		inst.exp1().procesa(this);
		if (inst.exp1().type().refEx().es_booleano()) {
			inst.bloque1().procesa(this);
			inst.bloque2().procesa(this);
			inst.type(ambos_ok(inst.bloque1().type(), inst.bloque2().type()));
		} else {
			System.out.println("Error: condicion no booleana");
			algun_error = true;
			inst.type(tipoError);
		}
	}

	public void procesa(Inst_while inst) {
		inst.exp1().procesa(this);
		if (inst.exp1().type().refEx().es_booleano()) {
			inst.insts().procesa(this);
			inst.type(inst.insts().type());
		} else {
			System.out.println("Error: condicion no booleana");
			algun_error = true;
			inst.type(tipoError);
		}
	}

	public void procesa(Inst_lectura inst) {
		inst.exp1().procesa(this);
		if ((inst.exp1().type().refEx().es_entero() || inst.exp1().type().refEx().es_real() || 
				inst.exp1().type().refEx().es_cadena()) && inst.exp1().es_designador()) {
			inst.type(tipoOK);
		} else {
			System.out.println("Error: lectura de tipo no valido");
			algun_error = true;
			inst.type(tipoError);
		}
	}

	public void procesa(Inst_escritura inst) {
		inst.exp1().procesa(this);
		if (((inst.exp1().type().refEx().es_entero()) || (inst.exp1().type().refEx().es_real()) || 
				(inst.exp1().type().refEx().es_booleano()) || (inst.exp1().type().refEx().es_cadena()))) {
			inst.type(tipoOK);
		} else {
			System.out.println("Error: escritura de tipo no valido");
			algun_error = true;
			inst.type(tipoError);
		}
	}

	public void procesa(Inst_new_line inst) {
		inst.type(tipoOK);
	}

	public void procesa(Inst_reserv_mem inst) {
		inst.exp1().procesa(this);
		if (inst.exp1().type().refEx().es_puntero()) {
			inst.type(tipoOK);
		} else {
			System.out.println("Error: reserva de memoria no valida");
			algun_error = true;
			inst.type(tipoError);
		}
	}

	public void procesa(Inst_lib_mem inst) {
		inst.exp1().procesa(this);
		if (inst.exp1().type().refEx().es_puntero()) {
			inst.type(tipoOK);
		} else {
			System.out.println("Error: liberacion de memoria no valida");
			algun_error = true;
			inst.type(tipoError);
		}
	}

	public void procesa(Inst_invoc_proc inst) {
		if (inst.vinculo().es_dec_proc()) {
			Dec_proc dec = (Dec_proc) inst.vinculo();
			if (dec.params().size() != inst.params().size()) {
				System.out.println("Error: numero de parametros incorrecto");
				algun_error = true;
				inst.type(tipoError);
			} else {
				inst.type(chequeo_params(inst.params(), dec.params()));
			}
		} else {
			System.out.println("Error: invocacion de procedimiento no valida");
			algun_error = true;
			inst.type(tipoError);
		}
	}

	public void procesa(Inst_comp inst) {
		inst.b().procesa(this);
		inst.type(inst.b().type());
	}

	public void procesa(Bloque_lleno b) {
		b.prog().procesa(this);
		b.type(b.prog().type());
	}

	public void procesa(Bloque_vacio b) {
		b.type(tipoOK);
	}

	private Tipo chequeo_params(Expresiones exprs, ParamsF params) {
        if (exprs.hay_muchas() && params.hay_muchas()) {
            Tipo t0 = chequeo_params(((Exprs_muchas) exprs).expresiones(), ((Params_muchos_f) params).params());
            Tipo t1 = chequeo_param(((Exprs_muchas) exprs).exp(), ((Params_muchos_f) params).param());
			return ambos_ok(t0, t1);
        } else if (exprs.hay_una() && params.hay_una()) {
            return chequeo_param(((Exprs_una) exprs).exp(), ((Params_uno_f) params).param());
        }
		return tipoOK;
    }

    public Tipo chequeo_param(Exp e, ParamF param) {
		e.procesa(this);
		if (param.por_valor()) {
			if (son_compatibles(e.type(), param.tipo())) {
				return tipoOK;
			} else {
				System.out.println("Error: parametro no compatible");
				algun_error = true;
				return tipoError;
			}
		} else {
			if (e.es_designador() && son_compatibles(param.tipo(), e.type())) {
				return tipoOK;
			} else {
				System.out.println("Error: parametro no compatible");
				algun_error = true;
				return tipoError;
			}
		}
    }

	public void procesa(Id id) {
		Dec dec = id.vinculo();
		if (!dec.es_dec_tipo() && !dec.es_dec_proc())
			id.type(((Dec_var) dec).tipo());
		else {
			System.out.println("Error: identificador no valido");
			algun_error = true;
			id.type(tipoError);
		}
	}

	public void procesa(Sstring id) {
		id.type(tipoString);
	}

	public void procesa(Num_int num) {
		num.type(tipoInt);
	}

	public void procesa(Num_real num) {
		num.type(tipoReal);
	}

	public void procesa(True t) {
		t.type(tipoBool);
	}

	public void procesa(False f) {
		f.type(tipoBool);
	}

	public void procesa(None n) {
		n.type(tipoNull);
	}

	public void procesa(Suma suma) {
		suma.arg0().procesa(this);
		suma.arg1().procesa(this);
		Tipo t0 = suma.arg0().type().refEx();
		Tipo t1 = suma.arg1().type().refEx();
		if (t0.es_entero() && t1.es_entero()) {
			suma.type(tipoInt);
		} else if ((t0.es_real() && (t1.es_entero() || t1.es_real()))
			 || (t1.es_real() && (t0.es_entero() || t0.es_real()))) {
			suma.type(tipoReal);
		} else {
			System.out.println("Error: tipos incompatibles en suma");
			algun_error = true;
			suma.type(tipoError);
		}
	}

	public void procesa(Resta resta) {
		resta.arg0().procesa(this);
		resta.arg1().procesa(this);
		Tipo t0 = resta.arg0().type().refEx();
		Tipo t1 = resta.arg1().type().refEx();
		if (t0.es_entero() && t1.es_entero()) {
			resta.type(tipoInt);
		} else if ((t0.es_real() && (t1.es_entero() || t1.es_real()))
			 || (t1.es_real() && (t0.es_entero() || t0.es_real()))) {
			resta.type(tipoReal);
		} else {
			System.out.println("Error: tipos incompatibles en resta");
			algun_error = true;
			resta.type(tipoError);
		}
	}

	public void procesa(Mul mul) {
		mul.arg0().procesa(this);
		mul.arg1().procesa(this);
		Tipo t0 = mul.arg0().type().refEx();
		Tipo t1 = mul.arg1().type().refEx();
		if (t0.es_entero() && t1.es_entero()) {
			mul.type(tipoInt);
		} else if ((t0.es_real() && (t1.es_entero() || t1.es_real()))
			 || (t1.es_real() && (t0.es_entero() || t0.es_real()))) {
			mul.type(tipoReal);
		} else {
			System.out.println("Error: tipos incompatibles en multiplicacion");
			algun_error = true;
			mul.type(tipoError);
		}
	}

	public void procesa(Div div) {
		div.arg0().procesa(this);
		div.arg1().procesa(this);
		Tipo t0 = div.arg0().type().refEx();
		Tipo t1 = div.arg1().type().refEx();
		if (t0.es_entero() && t1.es_entero()) {
			div.type(tipoInt);
		} else if ((t0.es_real() && (t1.es_entero() || t1.es_real()))
			 || (t1.es_real() && (t0.es_entero() || t0.es_real()))) {
			div.type(tipoReal);
		} else {
			System.out.println("Error: tipos incompatibles en division");
			algun_error = true;
			div.type(tipoError);
		}
	}

	public void procesa(Mod mod) {
		mod.arg0().procesa(this);
		mod.arg1().procesa(this);
		if (mod.arg0().type().refEx().es_entero() && mod.arg1().type().refEx().es_entero()) {
			mod.type(tipoInt);
		} else {
			System.out.println("Error: tipos incompatibles en modulo");
			algun_error = true;
			mod.type(tipoError);
		}
	}

	public void procesa(And and) {
		and.arg0().procesa(this);
		and.arg1().procesa(this);
		if (and.arg0().type().refEx().es_booleano() && and.arg1().type().refEx().es_booleano()) {
			and.type(tipoBool);
		} else {
			System.out.println("Error: tipos incompatibles en and");
			algun_error = true;
			and.type(tipoError);
		}
	}

	public void procesa(Or or) {
		or.arg0().procesa(this);
		or.arg1().procesa(this);
		if (or.arg0().type().refEx().es_booleano() && or.arg1().type().refEx().es_booleano()) {
			or.type(tipoBool);
		} else {
			System.out.println("Error: tipos incompatibles en or");
			algun_error = true;
			or.type(tipoError);
		}
	}

	public void procesa(Menor menor) {
		menor.arg0().procesa(this);
		menor.arg1().procesa(this);
		Tipo t0 = menor.arg0().type().refEx();
		Tipo t1 = menor.arg1().type().refEx();
		if ((t0.es_entero() || t0.es_real()) && (t1.es_entero() || t1.es_real())) {
			menor.type(tipoBool);
		} else if (t0.es_booleano() && t1.es_booleano()) {
			menor.type(tipoBool);
		} else if (t0.es_cadena() && t1.es_cadena()) {
			menor.type(tipoBool);
		} else {
			System.out.println("Error: tipos incompatibles en menor");
			algun_error = true;
			menor.type(tipoError);
		}
	}

	public void procesa(Mayor mayor) {
		mayor.arg0().procesa(this);
		mayor.arg1().procesa(this);
		Tipo t0 = mayor.arg0().type().refEx();
		Tipo t1 = mayor.arg1().type().refEx();
		if ((t0.es_entero() || t0.es_real()) && (t1.es_entero() || t1.es_real())) {
			mayor.type(tipoBool);
		} else if (t0.es_booleano() && t1.es_booleano()) {
			mayor.type(tipoBool);
		} else if (t0.es_cadena() && t1.es_cadena()) {
			mayor.type(tipoBool);
		} else {
			System.out.println("Error: tipos incompatibles en mayor");
			algun_error = true;
			mayor.type(tipoError);
		}
	}

	public void procesa(Menor_eq menor_eq) {
		menor_eq.arg0().procesa(this);
		menor_eq.arg1().procesa(this);
		Tipo t0 = menor_eq.arg0().type().refEx();
		Tipo t1 = menor_eq.arg1().type().refEx();
		if ((t0.es_entero() || t0.es_real()) && (t1.es_entero() || t1.es_real())) {
			menor_eq.type(tipoBool);
		} else if (t0.es_booleano() && t1.es_booleano()) {
			menor_eq.type(tipoBool);
		} else if (t0.es_cadena() && t1.es_cadena()) {
			menor_eq.type(tipoBool);
		} else {
			System.out.println("Error: tipos incompatibles en menor o igual");
			algun_error = true;
			menor_eq.type(tipoError);
		}
	}

	public void procesa(Mayor_eq mayor_eq) {
		mayor_eq.arg0().procesa(this);
		mayor_eq.arg1().procesa(this);
		Tipo t0 = mayor_eq.arg0().type().refEx();
		Tipo t1 = mayor_eq.arg1().type().refEx();
		if ((t0.es_entero() || t0.es_real()) && (t1.es_entero() || t1.es_real())) {
			mayor_eq.type(tipoBool);
		} else if (t0.es_booleano() && t1.es_booleano()) {
			mayor_eq.type(tipoBool);
		} else if (t0.es_cadena() && t1.es_cadena()) {
			mayor_eq.type(tipoBool);
		} else {
			System.out.println("Error: tipos incompatibles en mayor o igual");
			algun_error = true;
			mayor_eq.type(tipoError);
		}
	}

	public void procesa(Comp comp) {
		comp.arg0().procesa(this);
		comp.arg1().procesa(this);
		Tipo t0 = comp.arg0().type().refEx();
		Tipo t1 = comp.arg1().type().refEx();
		if ((t0.es_entero() || t0.es_real()) && (t1.es_entero() || t1.es_real())) {
			comp.type(tipoBool);
		} else if (t0.es_booleano() && t1.es_booleano()) {
			comp.type(tipoBool);
		} else if (t0.es_cadena() && t1.es_cadena()) {
			comp.type(tipoBool);
		} else if ((t0.es_puntero() || t0.es_null()) && (t1.es_puntero() || t1.es_null())) {
			comp.type(tipoBool);
		} else {
			System.out.println("Error: tipos incompatibles en comparacion");
			algun_error = true;
			comp.type(tipoError);
		}
	}

	public void procesa(Dist dist) {
		dist.arg0().procesa(this);
		dist.arg1().procesa(this);
		Tipo t0 = dist.arg0().type().refEx();
		Tipo t1 = dist.arg1().type().refEx();
		if ((t0.es_entero() || t0.es_real()) && (t1.es_entero() || t1.es_real())) {
			dist.type(tipoBool);
		} else if (t0.es_booleano() && t1.es_booleano()) {
			dist.type(tipoBool);
		} else if (t0.es_cadena() && t1.es_cadena()) {
			dist.type(tipoBool);
		} else if ((t0.es_puntero() || t0.es_null()) && (t1.es_puntero() || t1.es_null())) {
			dist.type(tipoBool);
		} else {
			System.out.println("Error: tipos incompatibles en distinto");
			algun_error = true;
			dist.type(tipoError);
		}
	}

	public void procesa(Menos menos) {
		menos.arg().procesa(this);
		Tipo t = menos.arg().type().refEx();
		if (t.es_entero()) {
			menos.type(tipoInt);
		} else if (t.es_real()) {
			menos.type(tipoReal);
		} else {
			System.out.println("Error: tipo incompatible para menos");
			algun_error = true;
			menos.type(tipoError);
		}
	}

	public void procesa(Not not) {
		not.arg().procesa(this);
		if (not.arg().type().refEx().es_booleano()) {
			not.type(tipoBool);
		} else {
			System.out.println("Error: tipo incompatible para negacion");
			algun_error = true;
			not.type(tipoError);
		}
	}

	public void procesa(Asterix asterix) {
		asterix.arg().procesa(this);
		Tipo tipo = asterix.arg().type().refEx();
		if (tipo.es_puntero()) {
			asterix.type(tipo.type());
		} else {
			System.out.println("Error: tipo puntero no valido");
			algun_error = true;
			asterix.type(tipoError);
		}
	}

	public void procesa(Corch corch) {
		corch.arg0().procesa(this);
		corch.arg1().procesa(this);
		Tipo tipo = corch.arg0().type().refEx();
		if (tipo.es_array() && corch.arg1().type().refEx().es_entero()) {
			corch.type(((Tipo_array) tipo).tipo());
		} else {
			System.out.println("Error: tipos incompatibles en indexacion");
			algun_error = true;
			corch.type(tipoError);
		}
	}

	public void procesa(Punto punto) {
		punto.arg().procesa(this);
		Tipo tipo = punto.arg().type().refEx();
		Tipo tipoCampo = tipo.tipoDeCampo(punto.id());
		if (tipo.es_registro() && tipoCampo != null) {
			punto.type(tipoCampo);
		} else {
			System.out.println("Error: tipos incompatibles en acceso a registro (.)");
			algun_error = true;
			punto.type(tipoError);
		}
	}

	public void procesa(Flecha flecha) {
		flecha.arg().procesa(this);
		Tipo tipop = flecha.arg().type().refEx();
		if (tipop.es_puntero()){
			Tipo tipopp = ((Tipo_puntero) tipop).tipo().refEx();
			Tipo tipoCampo = tipopp.tipoDeCampo(flecha.id());
			if (tipopp.es_registro() && tipoCampo != null)
				flecha.type(tipoCampo);
		} else {
			System.out.println("Error: tipos incompatibles en acceso a registro (->)");
			flecha.type(tipoError);
		}
	}


	private Tipo ambos_ok(Tipo t0, Tipo t1) {
		if (t0.equals(tipoOK) && t1.equals(tipoOK))
			return tipoOK;
		else
			return tipoError;
	}

	private boolean son_compatibles(Tipo t1, Tipo t2) {
		Tipo t = t1.refEx();
		Tipo tp = t2.refEx();
		if (t.es_entero() && tp.es_entero())
			return true;
		else if (t.es_real() && (tp.es_entero() || tp.es_real()))
			return true;
		else if (t.es_booleano() && tp.es_booleano())
			return true;
		else if (t.es_cadena() && tp.es_cadena())
			return true;
		else if (t.es_array() && tp.es_array()) {
			Tipo tpp = ((Tipo_array) t).tipo();
			Tipo tppp = ((Tipo_array) tp).tipo();
			int npp = Integer.parseInt(((Tipo_array) t).valor().toString());
			int nppp = Integer.parseInt(((Tipo_array) tp).valor().toString());
			return (npp == nppp) && son_compatibles(tpp, tppp, t);
		} else if (t.es_registro() && tp.es_registro())
			return registros_compatibles((Tipo_registro)t, (Tipo_registro)tp, t);
		else if (t.es_puntero() && tp.es_null())
			return true;
		else if (t.es_puntero() && tp.es_puntero()) {
			Tipo tpp = ((Tipo_puntero) t).tipo();
			Tipo tppp = ((Tipo_puntero) tp).tipo();
			return son_compatibles(tpp, tppp, t);
		} else
			return false;
	}

	private boolean son_compatibles(Tipo t1, Tipo t2, Tipo tOrig) {
		Tipo t = t1.refEx();
		Tipo tp = t2.refEx();
		if (t.es_entero() && tp.es_entero())
			return true;
		else if (t.es_real() && (tp.es_entero() || tp.es_real()))
			return true;
		else if (t.es_booleano() && tp.es_booleano())
			return true;
		else if (t.es_cadena() && tp.es_cadena())
			return true;
		else if (t.es_array() && tp.es_array()) {
			Tipo tpp = ((Tipo_array) t).tipo();
			Tipo tppp = ((Tipo_array) tp).tipo();
			int npp = Integer.parseInt(((Tipo_array) t).valor().toString());
			int nppp = Integer.parseInt(((Tipo_array) tp).valor().toString());
			return (t == tOrig) || ((npp == nppp) && son_compatibles(tpp, tppp, tOrig));
		} else if (t.es_registro() && tp.es_registro())
			return registros_compatibles((Tipo_registro)t, (Tipo_registro)tp, tOrig);
		else if (t.es_puntero() && tp.es_null())
			return true;
		else if (t.es_puntero() && tp.es_puntero()) {
			Tipo tpp = ((Tipo_puntero) t).tipo();
			Tipo tppp = ((Tipo_puntero) tp).tipo();
			return (t == tOrig) || son_compatibles(tpp, tppp, tOrig);
		} else
			return false;
	}

	private boolean registros_compatibles(Tipo_registro t, Tipo_registro tp, Tipo tOrig) {
		if (t.campos().size() == tp.campos().size()) {
			Campos campos1 = t.campos();
			Campos campos2 = tp.campos();
			while(campos1.size() > 1) {
				if (!son_compatibles(campos1.campo().tipo().refEx(), campos2.campo().tipo().refEx(), tOrig))
					return false;
				campos1 = campos1.campos();
				campos2 = campos2.campos();
			}
			return son_compatibles(campos1.campo().tipo().refEx(), campos2.campo().tipo().refEx(), tOrig);
		} else
			return false;
	}
}
