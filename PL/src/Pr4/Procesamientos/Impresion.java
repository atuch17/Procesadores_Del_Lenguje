package Pr4.Procesamientos;

import Pr4.AST.TinyASint.*;

public class Impresion extends ProcesamientoPorDefecto {
	
    public void procesa(Prog_con_decs prog) {
        prog.decs().procesa(this);
        System.out.println();
        System.out.println("&&");
        prog.insts().procesa(this);
        System.out.println();
    }

	public void procesa(Prog_sin_decs prog) {
        prog.insts().procesa(this);
        System.out.println();
    }

	public void procesa(Decs_una decs) {
        decs.dec().procesa(this);
    }

	public void procesa(Decs_muchas decs) {
        decs.decs().procesa(this);
        System.out.println(";");
        decs.dec().procesa(this);
    }

    public void procesa(Dec_var dec) {
        System.out.print("var ");
        dec.tipo().procesa(this);
        System.out.print(dec.id());
    }

	public void procesa(Dec_tipo dec) {
        System.out.print("type ");
        dec.tipo().procesa(this);
        System.out.print(dec.id());
    }

	public void procesa(Dec_proc dec) {
        System.out.print("proc ");
        System.out.print(dec.id());
        System.out.print("(");
        if (dec.params() != null)
        	dec.params().procesa(this);
        System.out.print(") ");
        dec.bloque().procesa(this);
    }

	public void procesa(Params_uno_f p) {
        p.param().procesa(this);
    }

	public void procesa(Params_muchos_f p) {
        p.params().procesa(this);
        System.out.print(", ");
        p.param().procesa(this);
    }

	public void procesa(Param_f_sin_amp p) {
        p.tipo().procesa(this);
        System.out.print(p.id());
    }

	public void procesa(Param_f_con_amp p) {
        p.tipo().procesa(this);
        System.out.print("& ");
        System.out.print(p.id());
    }

	public void procesa(Insts_una insts) {
        insts.inst().procesa(this);
    }

	public void procesa(Insts_muchas insts) {
        insts.insts().procesa(this);
        System.out.println(";");
        insts.inst().procesa(this);
    }

	public void procesa(Inst_asig inst) {
        inst.exp1().procesa(this);
        System.out.print(" = ");
        inst.exp2().procesa(this);
    }

	public void procesa(Inst_ifthen inst) {
        System.out.print("if ");
        inst.exp1().procesa(this);
        System.out.println(" then");
        inst.insts().procesa(this);
        System.out.println();
        System.out.print("endif");
    }

	public void procesa(Inst_ifthenelse inst) {
        System.out.print("if ");
        inst.exp1().procesa(this);
        System.out.println(" then");
        inst.bloque1().procesa(this);
        System.out.println();
        System.out.println("else");
        inst.bloque2().procesa(this);
        System.out.println();
        System.out.print("endif");
    }

	public void procesa(Inst_while inst) {
        System.out.print("while ");
        inst.exp1().procesa(this);
        System.out.println(" do");
        inst.insts().procesa(this);
        System.out.println();
        System.out.print("endwhile");
    }

	public void procesa(Inst_lectura inst) {
        System.out.print("read ");
        inst.exp1().procesa(this);
    }

	public void procesa(Inst_escritura inst) {
        System.out.print("write ");
        inst.exp1().procesa(this);
    }

	public void procesa(Inst_new_line inst) {
        System.out.print("nl");
    }

	public void procesa(Inst_reserv_mem inst) {
        System.out.print("new ");
        inst.exp1().procesa(this);
    }

	public void procesa(Inst_lib_mem inst) {
        System.out.print("delete ");
        inst.exp1().procesa(this);
    }

	public void procesa(Inst_invoc_proc inst) {
        System.out.print("call ");
        System.out.print(inst.id());
		System.out.print("(");
        inst.params().procesa(this);
		System.out.print(")");
    }

	public void procesa(Inst_comp inst) {
        inst.b().procesa(this);
    }

	public void procesa(Exprs_una e) {
        e.exp().procesa(this);
    }

	public void procesa(Exprs_muchas e) {
        e.expresiones().procesa(this);
        System.out.print(", ");
        e.exp().procesa(this);
    }

	public void procesa(Tipo_id tipo) {
		System.out.print(tipo.id() + " ");
	};

	public void procesa(Tipo_int tipo) {
		System.out.print("int ");
	};

	public void procesa(Tipo_real tipo) {
		System.out.print("real ");
	};

	public void procesa(Tipo_bool tipo) {
		System.out.print("bool ");
	};

	public void procesa(Tipo_cadena tipo) {
		System.out.print("string ");
	};

	public void procesa(Tipo_array tipo){
        System.out.print("array [");
		System.out.print(tipo.valor() + "] of ");
		tipo.tipo().procesa(this);
	};
	public void procesa(Tipo_registro tipo) {
        System.out.println("record {");
		tipo.campos().procesa(this);
		System.out.println();
        System.out.print("} ");
	};
	public void procesa(Tipo_puntero tipo) {
        System.out.print("pointer ");
		tipo.tipo().procesa(this);
	};

	public void procesa(Campos_uno c) {
		c.campo().procesa(this);
	};

	public void procesa(Campos_muchos c) {
		c.campos().procesa(this);
		System.out.println(";");	
		c.campo().procesa(this);		
	};
	public void procesa(Campo c) {
		c.tipo().procesa(this);		
		System.out.print(c.id());
	};

	public void procesa(Bloque_vacio b){
		System.out.println("{");
        System.out.print("}");
	}
	public void procesa(Bloque_lleno b) {
        System.out.println("{");
		b.prog().procesa(this);
        System.out.print("}");
	}

	public void procesa(Id exp) {
		System.out.print(exp.id());
	}

	public void procesa(True exp) {
		System.out.print("true");
	}

	public void procesa(False exp) {
		System.out.print("false");
	}

	public void procesa(Sstring exp) {
		System.out.print(exp.string());
	}

	public void procesa(None exp) {
		System.out.print("null");
	}

	public void procesa(Num_int exp) {
		System.out.print(exp.s());
	}

	public void procesa(Num_real exp) {
		System.out.print(exp.s());
	}

	public void procesa(Suma exp) {
		imprime_arg(exp.arg0(), exp.arg0().prioridad());
		System.out.print(" + ");
		imprime_arg(exp.arg1(), exp.arg1().prioridad());
	}

	public void procesa(Resta exp) {
		imprime_arg(exp.arg0(), exp.arg0().prioridad());
		System.out.print(" - ");
		imprime_arg(exp.arg1(), exp.arg1().prioridad());
	}

	public void procesa(And exp) {
		imprime_arg(exp.arg0(), exp.arg0().prioridad());
		System.out.print(" and ");
		imprime_arg(exp.arg1(), exp.arg1().prioridad());
	}

	public void procesa(Or exp) {
		imprime_arg(exp.arg0(), exp.arg0().prioridad());
		System.out.print(" or ");
		imprime_arg(exp.arg1(), exp.arg1().prioridad());
	}

	public void procesa(Mayor exp) {
		imprime_arg(exp.arg0(), exp.arg0().prioridad());
		System.out.print(" > ");
		imprime_arg(exp.arg1(), exp.arg1().prioridad());
	}

	public void procesa(Menor exp) {
		imprime_arg(exp.arg0(), exp.arg0().prioridad());
		System.out.print(" < ");
		imprime_arg(exp.arg1(), exp.arg1().prioridad());
	}

	public void procesa(Mayor_eq exp) {
		imprime_arg(exp.arg0(), exp.arg0().prioridad());
		System.out.print(" >= ");
		imprime_arg(exp.arg1(), exp.arg1().prioridad());
	}

	public void procesa(Menor_eq exp) {
		imprime_arg(exp.arg0(), exp.arg0().prioridad());
		System.out.print(" <= ");
		imprime_arg(exp.arg1(), exp.arg1().prioridad());
	}

	public void procesa(Comp exp) {
		imprime_arg(exp.arg0(), exp.arg0().prioridad());
		System.out.print(" == ");
		imprime_arg(exp.arg1(), exp.arg1().prioridad());
	}

	public void procesa(Dist exp) {
		imprime_arg(exp.arg0(), exp.arg0().prioridad());
		System.out.print(" != ");
		imprime_arg(exp.arg1(), exp.arg1().prioridad());
	}

	public void procesa(Mul exp) {
		imprime_arg(exp.arg0(), exp.arg0().prioridad());
		System.out.print(" * ");
		imprime_arg(exp.arg1(), exp.arg1().prioridad());
	}

	public void procesa(Div exp) {
		imprime_arg(exp.arg0(), exp.arg0().prioridad());
		System.out.print(" / ");
		imprime_arg(exp.arg1(), exp.arg1().prioridad());
	}

	public void procesa(Mod exp) {
		imprime_arg(exp.arg0(), exp.arg0().prioridad());
		System.out.print(" % ");
		imprime_arg(exp.arg1(), exp.arg1().prioridad());
	}

	public void procesa(Corch exp) {
		imprime_arg(exp.arg0(), exp.arg0().prioridad());
		System.out.print("[");
		imprime_arg(exp.arg1(), exp.arg1().prioridad());
		System.out.print("]");
	}

	public void procesa(Punto exp) {
		imprime_arg(exp.arg(), exp.arg().prioridad());
		System.out.print(".");
        System.out.print(exp.id());
	}

	public void procesa(Flecha exp) {
		imprime_arg(exp.arg(), exp.arg().prioridad());
		System.out.print("->");
		System.out.print(exp.id());
	}

	public void procesa(Menos exp) {
		System.out.print("-");
		imprime_arg(exp.arg(), exp.arg().prioridad());
	}

	public void procesa(Not exp) {
		System.out.print("not ");
		imprime_arg(exp.arg(), exp.arg().prioridad());
	}

	public void procesa(Asterix exp) {
		imprime_arg(exp.arg(), exp.arg().prioridad());
		System.out.print(" * ");
	}

    private void imprime_arg(Exp arg, int p) {
        if (arg.prioridad() < p) {
            System.out.print("(");
            arg.procesa(this);
            System.out.print(")");
        } else {
            arg.procesa(this);
        }
    }
}
