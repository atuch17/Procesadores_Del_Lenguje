package Pr4.Procesamientos;

import java.util.Stack;

import Pr4.AST.TinyASint.*;

public class Etiquetado extends ProcesamientoPorDefecto {
    private int etq;
    private Stack<Dec_proc> procs;
    private boolean primero;

    public Etiquetado() {
        etq = 0;
        procs = new Stack<Dec_proc>();
        primero = true;
    }
    
    public void procesa(Prog_con_decs prog) {
        prog.insts().procesa(this);
        if (primero) {
            etq++;
            primero = false;
        }
        prog.decs().recolecta_procs(this);
        while (!procs.empty()) {
            Dec_proc proc = procs.pop();
            proc.procesa(this);
        }
    }

    public void procesa(Prog_sin_decs prog) {
        prog.insts().procesa(this);
    }

    public void procesa(Insts_ninguna insts) {
        insts.inicio(etq);
        insts.sig(etq);
    }

    public void procesa(Insts_una insts) {
        insts.inicio(etq);
        insts.inst().procesa(this);
        insts.sig(etq);
    }

    public void procesa(Insts_muchas insts) {
        insts.inicio(etq);
        insts.insts().procesa(this);
        insts.inst().procesa(this);
        insts.sig(etq);
    }

    public void procesa(Inst_asig inst) {
        inst.exp1().procesa(this);
        inst.exp2().procesa(this);
        if (inst.exp2().type().es_entero() && inst.exp1().type().es_real()
             && inst.exp2().es_designador())
            etq++;
        etq++;
    }

    public void procesa(Inst_ifthen inst) {
        inst.inicio(etq);
        inst.exp1().procesa(this);
        if (inst.exp1().es_designador())
            etq++;
        etq++;
        inst.insts().procesa(this);
        inst.sig(etq);
    }

    public void procesa(Inst_ifthenelse inst) {
        inst.inicio(etq);
        inst.exp1().procesa(this);
        if (inst.exp1().es_designador())
            etq++;
        etq++;
        inst.bloque1().procesa(this);
        etq++;
        inst.bloque2().procesa(this);
        inst.sig(etq);
    }

    public void procesa(Inst_while inst) {
        inst.inicio(etq);
        inst.exp1().procesa(this);
        if (inst.exp1().es_designador())
            etq++;
        etq++;
        inst.insts().procesa(this);
        etq++;
        inst.sig(etq);
    }

    public void procesa(Inst_lectura inst) {
        inst.exp1().procesa(this);
        etq += 2;
    }

    public void procesa(Inst_escritura inst) {
        inst.exp1().procesa(this);
        if (inst.exp1().es_designador())
            etq++;
        etq++;
    }

    public void procesa(Inst_new_line inst) {
        etq++;
    }

    public void procesa(Inst_reserv_mem inst) {
        inst.exp1().procesa(this);
        etq += 2;
    }

    public void procesa(Inst_lib_mem inst) {
        inst.exp1().procesa(this);
        etq += 2;
    }

    public void procesa(Inst_invoc_proc inst) {
        etq++;
        etiqueta_params(inst.params(), ((Dec_proc) inst.vinculo()).params());
        etq += 2;
        inst.dir_sig(etq);
    }

    public void procesa(Inst_comp inst) {
        etq += 2;
        inst.b().procesa(this);
        etq += 2;
        inst.sig(etq);
    }

    private void etiqueta_params(Expresiones exprs, ParamsF params) {
        if (exprs.hay_muchas() && params.hay_muchas()) {
            etiqueta_params(((Exprs_muchas) exprs).expresiones(), ((Params_muchos_f) params).params());
            etiqueta_paso(((Exprs_muchas) exprs).exp(), ((Params_muchos_f) params).param());
        } else if (exprs.hay_una() && params.hay_una()) {
            etiqueta_paso(((Exprs_una) exprs).exp(), ((Params_uno_f) params).param());
        }
    }

    public void etiqueta_paso(Exp e, ParamF param) {
        etq += 3;
        e.procesa(this);
        etq++;
    }

    public void procesa(Num_int num) {
        num.inicio(etq);
        etq++;
        num.sig(etq);
    }

    public void procesa(Num_real num) {
        num.inicio(etq);
        etq++;
        num.sig(etq);
    }

    public void procesa(True t) {
        t.inicio(etq);
        etq++;
        t.sig(etq);
    }

    public void procesa(False f) {
        f.inicio(etq);
        etq++;
        f.sig(etq);
    }

    public void procesa(Sstring cad) {
        cad.inicio(etq);
        etq++;
        cad.sig(etq);
    }

    public void procesa(None n) {
        n.inicio(etq);
        etq++;
        n.sig(etq);
    }

    public void procesa(Id id) {
        id.inicio(etq);
        Dec vinculo = id.vinculo();
        if (vinculo.nivel() == 0)
            etq++;
        else {
            etq += 3;
            if (vinculo.es_param_ref())
                etq++;
        }
        id.sig(etq);
    }

    public void procesa(Suma suma) {
        suma.inicio(etq);
        suma.arg0().procesa(this);
        if (suma.arg0().es_designador())
            etq++;
        suma.arg1().procesa(this);
        if (suma.arg1().es_designador())
            etq++;
        etq++;
        suma.sig(etq);
    }

    public void procesa(Resta resta) {
        resta.inicio(etq);
        resta.arg0().procesa(this);
        if (resta.arg0().es_designador())
            etq++;
        resta.arg1().procesa(this);
        if (resta.arg1().es_designador())
            etq++;
        etq++;
        resta.sig(etq);
    }

    public void procesa(Mul mul) {
        mul.inicio(etq);
        mul.arg0().procesa(this);
        if (mul.arg0().es_designador())
            etq++;
        mul.arg1().procesa(this);
        if (mul.arg1().es_designador())
            etq++;
        etq++;
        mul.sig(etq);
    }

    public void procesa(Div div) {
        div.inicio(etq);
        div.arg0().procesa(this);
        if (div.arg0().es_designador())
            etq++;
        div.arg1().procesa(this);
        if (div.arg1().es_designador())
            etq++;
        etq++;
        div.sig(etq);
    }

    public void procesa(Mod mod) {
        mod.inicio(etq);
        mod.arg0().procesa(this);
        if (mod.arg0().es_designador())
            etq++;
        mod.arg1().procesa(this);
        if (mod.arg1().es_designador())
            etq++;
        etq++;
        mod.sig(etq);
    }

    public void procesa(Menos menos) {
        menos.inicio(etq);
        menos.arg().procesa(this);
        if (menos.arg().es_designador())
            etq++;
        etq++;
        menos.sig(etq);
    }

    public void procesa(And and) {
        and.inicio(etq);
        and.arg0().procesa(this);
        if (and.arg0().es_designador())
            etq++;
        and.arg1().procesa(this);
        if (and.arg1().es_designador())
            etq++;
        etq++;
        and.sig(etq);
    }

    public void procesa(Or or) {
        or.inicio(etq);
        or.arg0().procesa(this);
        if (or.arg0().es_designador())
            etq++;
        or.arg1().procesa(this);
        if (or.arg1().es_designador())
            etq++;
        etq++;
        or.sig(etq);
    }

    public void procesa(Not not) {
        not.inicio(etq);
        not.arg().procesa(this);
        if (not.arg().es_designador())
            etq++;
        etq++;
        not.sig(etq);
    }

    public void procesa(Mayor mayor) {
        mayor.inicio(etq);
        mayor.arg0().procesa(this);
        if (mayor.arg0().es_designador())
            etq++;
        mayor.arg1().procesa(this);
        if (mayor.arg1().es_designador())
            etq++;
        etq++;
        mayor.sig(etq);
    }

    public void procesa(Mayor_eq mayor_eq) {
        mayor_eq.inicio(etq);
        mayor_eq.arg0().procesa(this);
        if (mayor_eq.arg0().es_designador())
            etq++;
        mayor_eq.arg1().procesa(this);
        if (mayor_eq.arg1().es_designador())
            etq++;
        etq++;
        mayor_eq.sig(etq);
    }

    public void procesa(Menor menor) {
        menor.inicio(etq);
        menor.arg0().procesa(this);
        if (menor.arg0().es_designador())
            etq++;
        menor.arg1().procesa(this);
        if (menor.arg1().es_designador())
            etq++;
        etq++;
        menor.sig(etq);
    }

    public void procesa(Menor_eq menor_eq) {
        menor_eq.inicio(etq);
        menor_eq.arg0().procesa(this);
        if (menor_eq.arg0().es_designador())
            etq++;
        menor_eq.arg1().procesa(this);
        if (menor_eq.arg1().es_designador())
            etq++;
        etq++;
        menor_eq.sig(etq);
    }

    public void procesa(Comp comp) {
        comp.inicio(etq);
        comp.arg0().procesa(this);
        if (comp.arg0().es_designador())
            etq++;
        comp.arg1().procesa(this);
        if (comp.arg1().es_designador())
            etq++;
        etq++;
        comp.sig(etq);
    }

    public void procesa(Dist dist) {
        dist.inicio(etq);
        dist.arg0().procesa(this);
        if (dist.arg0().es_designador())
            etq++;
        dist.arg1().procesa(this);
        if (dist.arg1().es_designador())
            etq++;
        etq++;
        dist.sig(etq);
    }

    public void procesa(Corch corch) {
        corch.inicio(etq);
        corch.arg0().procesa(this);
        corch.arg1().procesa(this);
        if (corch.arg1().es_designador())
            etq++;
        etq += 3;
        corch.sig(etq);
    }

    public void procesa(Punto punto) {
        punto.inicio(etq);
        punto.arg().procesa(this);
        etq += 2;
        punto.sig(etq);
    }

    public void procesa(Flecha flecha) {
        flecha.inicio(etq);
        flecha.arg().procesa(this);
        etq += 3;
        flecha.sig(etq);
    }

    public void procesa(Asterix asterix) {
        asterix.inicio(etq);
        asterix.arg().procesa(this);
        etq++;
        asterix.sig(etq);
    }

    public void procesa(Dec_proc proc) {
        proc.dir_inic(etq);
        proc.bloque().procesa(this);
        etq += 2;
        proc.bloque().recolecta_procs(this);
    }

    public void procesa(Bloque_lleno b) {
        b.prog().procesa(this);
    }

    public void recolecta_procs(Bloque_lleno b) {
        b.prog().recolecta_procs(this);
    }

    public void recolecta_procs(Prog_con_decs p) {
        p.decs().recolecta_procs(this);
    }

    public void recolecta_procs(Decs_una d) {
        d.dec().recolecta_procs(this);
    }

    public void recolecta_procs(Decs_muchas d) {
        d.decs().recolecta_procs(this);
        d.dec().recolecta_procs(this);
    }

    public void recolecta_procs(Dec_proc proc) {
        procs.push(proc);
    }
}
