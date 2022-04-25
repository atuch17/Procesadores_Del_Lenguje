package Pr3b;

import Pr3b.TinyASint.*;

public class Impresion extends ProcesamientoPorDefecto {
    public Impresion() {
    }

    public void procesa(Prog_con_decs prog);
	public void procesa(Prog_sin_decs prog);
	public void procesa(Decs_una decs);
	public void procesa(Decs_muchas decs);

    public void procesa(Dec_var dec);
	public void procesa(Dec_tipo dec);
	public void procesa(Dec_proc dec);
	public void procesa(Params_uno_f p);
	public void procesa(Params_muchos_f p);
	public void procesa(Param_f_sin_amp p);
	public void procesa(Param_f_con_amp p);
	public void procesa(Insts_una insts);
	public void procesa(Insts_muchas intss);
	public void procesa(Inst_asig inst);
	public void procesa(Insts_opc_sin_insts inst);
	public void procesa(Insts_opc_con_insts inst);
	public void procesa(Inst_ifthen inst);
	public void procesa(Inst_ifthenelse inst);
	public void procesa(Inst_while inst);
	public void procesa(Inst_lectura inst);
	public void procesa(Inst_escritura inst);
	public void procesa(Inst_new_line inst);
	public void procesa(Inst_reserv_mem inst);
	public void procesa(Inst_lib_mem inst);
	public void procesa(Inst_invoc_proc inst);
	public void procesa(Inst_comp inst);
	public void procesa(Params_vacio p);
	public void procesa(Params_lleno p);
	public void procesa(Exprs_una e);
	public void procesa(Exprs_muchas e);
	public void procesa(ProgramaAux p);
    
	public void procesa(Tipo_basico tipo);
	public void procesa(Tipo_id tipo);
	public void procesa(Tipo_array tipo);
	public void procesa(Tipo_registro tipo);
	public void procesa(Tipo_puntero tipo);
	public void procesa(Campos_uno c);
	public void procesa(Campos_muchos c);
	public void procesa(Campo c);
	public void procesa(Bloque_vacio b);
	public void procesa(Bloque_lleno b);
	 
	public void procesa(Id exp);
	 
	public void procesa(True exp);
	public void procesa(False exp);
	 
	public void procesa(Sstring exp);
	public void procesa(None exp);
	public void procesa(Num_int exp);
	public void procesa(Num_real exp);
	 
    public void procesa(Suma exp);
    public void procesa(Resta exp);
     
    public void procesa(And exp);
    public void procesa(Or exp);
     
    public void procesa(Mayor exp);    
    public void procesa(Menor exp);    
    public void procesa(Mayor_eq exp);    
    public void procesa(Menor_eq exp);
    public void procesa(Comp exp);    
    public void procesa(Dist exp);    
     
    public void procesa(Mul exp);
    public void procesa(Div exp);
    public void procesa(Mod exp);
	 
    public void procesa(Corch exp);
    public void procesa(Punto exp);
    public void procesa(Flecha exp);

    public void procesa(Menos exp);
    public void procesa(Not exp);

    public void procesa(Asterix exp);

    //-----------------------------------------------

    public void procesa(Prog_con_decs prog) {
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
        System.out.print("  " + dec.tipo() + " " + dec.id());
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

    public void procesa(Menos exp) { //TODO preguntar
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

    public void procesa(Num exp) {
        System.out.print(exp.num());
    }
}
