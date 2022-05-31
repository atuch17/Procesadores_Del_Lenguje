package Pr4.Procesamientos;

import java.util.Stack;

import Pr4.AST.TinyASint.*;

public class Codificacion extends ProcesamientoPorDefecto {
/*global procs = pila_vacia()*/
    private Stack<Dec_proc> procs;

    public Codificacion() {
        procs = new Stack<Dec_proc>();
    }

/*gen_cod(prog_con_decs(Decs, Insts)) =
gen_cod(Insts)
recolecta_procs(Decs)
mientras(!es_vacia(procs)))
proc = pop(procs)
gen_cod(proc)*/
    public void procesa(Prog_con_decs prog) {
        prog.insts().procesa(this);
        prog.decs().recolecta_procs(this);
        while (!procs.empty()) {
            Dec_proc proc = procs.pop();
            proc.procesa(this);
        }
    }

/*gen_cod(prog_sin_decs(Insts)) =
gen_cod(Insts)*/
    public void procesa(Prog_sin_decs prog) {
        prog.insts().procesa(this);
    }

/*gen_cod(insts_ninguna()) =
skip*/

/*gen_cod(insts_una(Inst)) =
gen_cod(Inst)*/
    public void procesa(Insts_una insts) {
        insts.inst().procesa(this);
    }

/*gen_cod(insts_muchas(Insts, Inst)) =
gen_cod(Is)
gen_cod(I)*/
    public void procesa(Insts_muchas insts) {
        insts.insts().procesa(this);
        insts.inst().procesa(this);
    }

/*gen_cod(inst_asig(E0,E1)) =
gen_cod(E0)
gen_cod(E1)
gen_ins_asig(E1)*/
    public void procesa(Inst_asig inst) {
        inst.exp1().procesa(this);
        inst.exp2().procesa(this);
        //TODO gen_ins_asig(inst.exp2());
    }

/*gen_cod(inst_ifthen(E0, Insts)) =
gen_cod(E0)
gen_ins(irf($.sig))
gen_cod(Insts)*/

/*gen_cod(inst_ifthenelse(E0, Insts0, Insts1)) =
gen_cod(E0)
gen_ins(irf(Insts1.inicio))
gen_cod(Insts0)
gen_ins(ira($.sig))
gen_cod(Insts1)*/

/*gen_cod(inst_while(E0, Insts)) =
gen_cod(E0)
gen_ins(irf($.sig))
gen_cod(Insts)
gen_ins(ira(E0.inicio))*/

/*gen_cod(inst_lectura(E0)) =
gen_cod(E0)*/

/*gen_cod(inst_escritura(E0)) =
gen_cod(E0)*/

/*gen_cod(inst_new_line()) =
skip*/

/*gen_cod(inst_reserv_mem(E0)) =
gen_cod(E0)
gen_ins(alloc(E0.tipo.tam))*/

/*gen_cod(inst_lib_mem(E0)) =
gen_cod(E0)
gen_ins(dealloc(E0.tipo.tam))*/

/*gen_cod(inst_invoc_proc(id, Exprs)) =
gen_ins(activa($.vinculo.nivel,$.vinculo.tam_datos,$.dir_sig))
sea $.vinculo = dec_proc(id, Params, Bloque) en
gen_cod_params(Exprs, Params)
fin sea
gen_ins(desapilad($.vinculo.nivel))
gen_ins(ir_a($.vinculo.dir_inic))*/

/*gen_cod_params(exprs_ninguna(), params_ninguno()) =
skip*/

/*gen_cod_params(exprs_una(E), params_uno_f(Param)) = 
gen_cod_paso(E, Param)*/

/*gen_cod_params(exprs_muchas(Exprs, E), params_muchos_f(Params, Param)) =
gen_cod_params(Exprs, Params)
gen_cod_paso(E, Param)*/

/*gen_cod_paso(E, Param) =
gen_ins(dup())
gen_ins(apilaint(Param.dir))
gen_ins(suma())
gen_cod(E)
gen_ins_paso(E, Param)*/

/*gen_cod(num_int(n)) =
gen_ins(apilaint(n))*/

/*gen_cod(num_real(n)) =
gen_ins(apilareal(n))*/

/*gen_cod(true()) =
gen_ins(apilabool(true))*/

/*gen_cod(false()) =
gen_ins(apilabool(false))*/

/*gen_cod(cadena(str)) =
gen_ins(apilastring(str))*/

/*gen_cod(id(id)) =
si $.vinculo.nivel = 0
gen_ins(apilaint($.vinculo.dir))
si no
gen_ins(apilad($.vinculo.nivel))
gen_ins(apilaint($.vinculo.dir))
gen_ins(suma())
si $.vinculo = dec_var(T, id)
gen_ins(apilaind())*/

/*gen_cod(suma(E0, E1)) =
gen_cod(E0)
si es_designador(E0) entonces
gen_ins(apilaind())
gen_cod(E1)
si es_designador(E1) entonces
gen_ins(apilaind())
gen_ins(suma())*/

/*gen_cod(resta(E0, E1)) =
gen_cod(E0)
si es_designador(E0) entonces
gen_ins(apilaind())
gen_cod(E1)
si es_designador(E1) entonces
gen_ins(apilaind())
gen_ins(resta())*/

/*gen_cod(mul(E0, E1)) =
gen_cod(E0)
si es_designador(E0) entonces
gen_ins(apilaind())
gen_cod(E1)
si es_designador(E1) entonces
gen_ins(apilaind())
gen_ins(mul())*/

/*gen_cod(div(E0, E1)) =
gen_cod(E0)
si es_designador(E0) entonces
gen_ins(apilaind())
gen_cod(E1)
si es_designador(E1) entonces
gen_ins(apilaind())
gen_ins(div())*/

/*gen_cod(menos(E)) =
gen_cod(E)
si es_designador(E) entonces
gen_ins(apilaind())
gen_ins(menos())*/

/*gen_cod(and(E0, E1)) =
gen_cod(E0)
si es_designador(E0) entonces
gen_ins(apilaind())
gen_cod(E1)
si es_designador(E1) entonces
gen_ins(apilaind())
gen_ins(and())*/

/*gen_cod(or(E0, E1)) =
gen_cod(E0)
si es_designador(E0) entonces
gen_ins(apilaind())
gen_cod(E1)
si es_designador(E1) entonces
gen_ins(apilaind())
gen_ins(or())*/

/*gen_cod(not(E)) =
gen_cod(E)
si es_designador(E) entonces
gen_ins(apilaind())
gen_ins(not())*/

/*gen_cod(mayor(E0, E1)) =
gen_cod(E0)
si es_designador(E0) entonces
gen_ins(apilaind())
gen_cod(E1)
si es_designador(E1) entonces
gen_ins(apilaind())
gen_ins(mayor())*/

/*gen_cod(mayor_eq(E0, E1)) =
gen_cod(E0)
si es_designador(E0) entonces
gen_ins(apilaind())
gen_cod(E1)
si es_designador(E1) entonces
gen_ins(apilaind())
gen_ins(mayor_eq())*/

/*gen_cod(menor(E0, E1)) =
gen_cod(E0)
si es_designador(E0) entonces
gen_ins(apilaind())
gen_cod(E1)
si es_designador(E1) entonces
gen_ins(apilaind())
gen_ins(menor())*/

/*gen_cod(menor_eq(E0, E1)) =
gen_cod(E0)
si es_designador(E0) entonces
gen_ins(apilaind())
gen_cod(E1)
si es_designador(E1) entonces
gen_ins(apilaind())
gen_ins(menor_eq())*/

/*gen_cod(comp(E0, E1)) =
gen_cod(E0)
si es_designador(E0) entonces
gen_ins(apilaind())
gen_cod(E1)
si es_designador(E1) entonces
gen_ins(apilaind())
gen_ins(comp())*/

/*gen_cod(dist(E0, E1)) =
gen_cod(E0)
si es_designador(E0) entonces
gen_ins(apilaind())
gen_cod(E1)
si es_designador(E1) entonces
gen_ins(apilaind())
gen_ins(dist())*/

/*gen_cod(corch(E0, E1)) =
gen_cod(E0)
gen_cod(E1)
si es_designador(E1) entonces
gen_ins(apilaind())
gen_ins(mul())
gen_ins(suma())*/

/*gen_cod(punto(E0, id)) =
gen_cod(E0)
gen_ins(apilaint(id.desp))
gen_ins(suma())*/

/*gen_cod(flecha(E0, id)) =
gen_cod(E0)
gen_ins(apilaind())
gen_ins(apilaint(id.desp))
gen_ins(suma())*/

/*gen_cod(asterix(E0)) =
gen_cod(E0)
gen_ins(apilaind())*/

/*gen_cod(proc(id, Params, Bloque)) =
gen_cod(Bloque) 
gen_ins(desactiva($.nivel,$.tam)) 
gen_ins(irind())
recolecta_procs(Bloque)*/

/*gen_cod(bloque_lleno(prog_con_decs(Decs, Insts))) =
gen_cod(Insts)*/

/*gen_cod(bloque_lleno(prog_sin_decs(Insts))) =
gen_cod(Insts)*/

/*gen_cod(bloque_vacio()) =
skip*/

/*recolecta_procs(bloque_lleno(Prog)) =
	recolecta_procs(Prog)*/

/*recolecta_procs(bloque_vacio()) =
	skip*/

/*recolecta_procs(prog_con_decs(Decs, Insts)) =
	recolecta_procs(Decs)*/

/*recolecta_procs(prog_sin_decs(Insts)) =
	skip*/

/*recolecta_procs(decs_una(Dec)) = 
recolecta_procs(Dec)*/

/*recolecta_procs(decs_muchas(Decs,Dec)) =
recolecta_procs(Decs)
recolecta_procs(Dec)*/

/*recolecta_procs(dec_var(T, id)) = 
skip*/

/*recolecta_procs(dec_tipo(T, id)) = 
skip*/

/*recolecta_procs(dec_proc(id, Params, Bloque)) =
push($, procs)*/
}
