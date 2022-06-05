package Pr3a;

import Pr3a.TinyASint.*;

public class Impresion extends ProcesamientoPorDefecto {
    public Impresion() {
    }

    public void procesa(Prog prog) {
        prog.decs().procesa(this);
        System.out.println();
        System.out.println("&&");
        prog.insts().procesa(this);
        System.out.println();
    }

    public void procesa(Decs_muchas decs) {
        decs.decs().procesa(this);
        System.out.println(";");
        decs.dec().procesa(this);
    }

    public void procesa(Decs_una decs) {
        decs.dec().procesa(this);
    }

    public void procesa(Dec dec) {
    	System.out.print("  ");
    	dec.tipo().procesa(this);
    	System.out.print(" " + dec.id());
    }

    public void procesa(Insts_muchas insts) {
        insts.insts().procesa(this);
        System.out.println(";");
        insts.inst().procesa(this);
    }

    public void procesa(Insts_una insts) {
        insts.inst().procesa(this);
    }

    public void procesa(Inst inst) {
        System.out.print("  " + inst.id() + " = ");
        inst.exp().procesa(this);
    }

    public void procesa(Suma exp) {
        imprime_arg(exp.arg0(), 1);
        System.out.print(" + ");
        imprime_arg(exp.arg1(), 0);
    }

    public void procesa(Resta exp) {
        imprime_arg(exp.arg0(), 1);
        System.out.print(" - ");
        imprime_arg(exp.arg1(), 1);
    }

    public void procesa(Mul exp) {
        imprime_arg(exp.arg0(), 4);
        System.out.print(" * ");
        imprime_arg(exp.arg1(), 4);
    }

    public void procesa(Div exp) {
        imprime_arg(exp.arg0(), 4);
        System.out.print(" / ");
        imprime_arg(exp.arg1(), 4);
    }

    public void procesa(Mayor exp) {
        imprime_arg(exp.arg0(), 2);
        System.out.print(" > ");
        imprime_arg(exp.arg1(), 3);
    }

    public void procesa(Menor exp) {
        imprime_arg(exp.arg0(), 2);
        System.out.print(" < ");
        imprime_arg(exp.arg1(), 3);
    }

    public void procesa(Mayor_eq exp) {
        imprime_arg(exp.arg0(), 2);
        System.out.print(" >= ");
        imprime_arg(exp.arg1(), 3);
    }

    public void procesa(Menor_eq exp) {
        imprime_arg(exp.arg0(), 2);
        System.out.print(" <= ");
        imprime_arg(exp.arg1(), 3);
    }

    public void procesa(Comp exp) {
        imprime_arg(exp.arg0(), 2);
        System.out.print(" == ");
        imprime_arg(exp.arg1(), 3);
    }

    public void procesa(Dist exp) {
        imprime_arg(exp.arg0(), 2);
        System.out.print(" != ");
        imprime_arg(exp.arg1(), 3);
    }

    public void procesa(And exp) {
        imprime_arg(exp.arg0(), 1);
        System.out.print(" and ");
        imprime_arg(exp.arg1(), 2);
    }

    public void procesa(Or exp) {
        imprime_arg(exp.arg0(), 1);
        System.out.print(" or ");
        imprime_arg(exp.arg1(), 2);
    }

    public void procesa(Not exp) {
        System.out.print("not ");
        imprime_arg(exp.arg(), 4);
    }

    public void procesa(Menos exp) {
        System.out.print("- ");
        imprime_arg(exp.arg(), 5);
    }

    public void procesa(True exp) {
        System.out.print("true");
    }

    public void procesa(False exp) {
        System.out.print("false");
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

    public void procesa(Id exp) {
        System.out.print(exp.id());
    }

    public void procesa(Num_int exp) {
		System.out.print(exp.s());
	}

	public void procesa(Num_real exp) {
		System.out.print(exp.s());
	}

	public void procesa(Tipo_int tipo) {
		System.out.print("int");
	};

	public void procesa(Tipo_real tipo) {
		System.out.print("real");
	};

	public void procesa(Tipo_bool tipo) {
		System.out.print("bool");
	};

}
