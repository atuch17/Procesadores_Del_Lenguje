package Pr4.Procesamientos;

import java.util.Stack;

import Pr4.AST.TinyASint.*;

public class Etiquetado extends ProcesamientoPorDefecto {

    private int etq;
    private Stack<Dec_proc> procs;

    public Etiquetado() {
        etq = 0;
        procs = new Stack<Dec_proc>();
    }
    
    public void procesa(Prog_con_decs prog) {
        prog.insts().procesa(this);
        prog.decs().recolecta_procs(this);
        while (!procs.empty()) {
            Dec_proc proc = procs.pop();
            proc.procesa(this);
        }
    }

    public void procesa(Prog_sin_decs prog) {
        prog.insts().procesa(this);
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
        etq++;
    }

    public void procesa(Inst_ifthen inst) {
        inst.inicio(etq);
        inst.exp1().procesa(this);
        etq++;
        inst.insts().procesa(this);
        inst.sig(etq);
    }

    public void procesa(Inst_ifthenelse inst) {
        inst.inicio(etq);
        inst.exp1().procesa(this);
        etq++;
        inst.bloque1().procesa(this);
        etq++;
        inst.bloque2().procesa(this);
        inst.sig(etq);
    }

    public void procesa(Inst_while inst) {
        inst.inicio(etq);
        inst.exp1().procesa(this);
        etq++;
        inst.insts().procesa(this);
        etq++;
        inst.sig(etq);
    }

    public void procesa(Inst_lectura inst) {
        inst.exp1().procesa(this);
    }

    public void procesa(Inst_escritura inst) {
        inst.exp1().procesa(this);
    }

    public void procesa(Inst_reserv_mem inst) {
        inst.exp1().procesa(this);
        etq++;
    }

    public void procesa(Inst_lib_mem inst) {
        inst.exp1().procesa(this);
        etq++;
    }

    public void procesa(Inst_invoc_proc inst) {
        etq++;
        Dec_proc proc = (Dec_proc) inst.vinculo();
        //etiqueta_params(inst.params(), proc.params()); //TODO como en espaciado
        etq += 2;
        inst.sig(etq);
    }

    public void etiqueta_params(Exprs_ninguna exprs, Params_ninguno params) {}

    public void etiqueta_params(Exprs_una exprs, Params_uno_f params) {
        etiqueta_paso(exprs.e(), params.param());
    }

    public void etiqueta_params(Exprs_muchas exprs, Params_muchos_f params) {
        //etiqueta_params(exprs.expresiones(), params.params()); //TODO error tipo como en espaciado
        etiqueta_paso(exprs.exp(), params.param());
    }

    public void etiqueta_paso(Exp e, ParamF param) {
        etq += 3;
        e.procesa(this);
        etq++;
    }

    public void procesa(Num_int num) {
        etq++;
    }

    public void procesa(Num_real num) {
        etq++;
    }

    public void procesa(True t) {
        etq++;
    }

    public void procesa(False f) {
        etq++;
    }

    public void procesa(Sstring cad) {
        etq++;
    }

    public void procesa(Id id) {
        if (((Dec_proc) id.vinculo()).nivel() == 0)
            etq++;
        else {
            etq += 3;
            if (id.vinculo() instanceof Dec_var)
                etq++;
        }
    }

    public void procesa(Suma suma) {
        suma.arg0().procesa(this);
        if (es_designador(suma.arg0()))
            etq++;
        suma.arg1().procesa(this);
        if (es_designador(suma.arg1()))
            etq++;
        etq++;
    }

    public void procesa(Resta resta) {
        resta.arg0().procesa(this);
        if (es_designador(resta.arg0()))
            etq++;
        resta.arg1().procesa(this);
        if (es_designador(resta.arg1()))
            etq++;
        etq++;
    }

    public void procesa(Mul mul) {
        mul.arg0().procesa(this);
        if (es_designador(mul.arg0()))
            etq++;
        mul.arg1().procesa(this);
        if (es_designador(mul.arg1()))
            etq++;
        etq++;
    }

    public void procesa(Div div) {
        div.arg0().procesa(this);
        if (es_designador(div.arg0()))
            etq++;
        div.arg1().procesa(this);
        if (es_designador(div.arg1()))
            etq++;
        etq++;
    }

    public void procesa(Menos menos) {
        menos.arg().procesa(this);
        if (es_designador(menos.arg()))
            etq++;
        etq++;
    }

    public void procesa(And and) {
        and.arg0().procesa(this);
        if (es_designador(and.arg0()))
            etq++;
        and.arg1().procesa(this);
        if (es_designador(and.arg1()))
            etq++;
        etq++;
    }

    public void procesa(Or or) {
        or.arg0().procesa(this);
        if (es_designador(or.arg0()))
            etq++;
        or.arg1().procesa(this);
        if (es_designador(or.arg1()))
            etq++;
        etq++;
    }

    public void procesa(Not not) {
        not.arg().procesa(this);
        if (es_designador(not.arg()))
            etq++;
        etq++;
    }

    public void procesa(Mayor mayor) {
        mayor.arg0().procesa(this);
        if (es_designador(mayor.arg0()))
            etq++;
        mayor.arg1().procesa(this);
        if (es_designador(mayor.arg1()))
            etq++;
        etq++;
    }

    public void procesa(Mayor_eq mayor_eq) {
        mayor_eq.arg0().procesa(this);
        if (es_designador(mayor_eq.arg0()))
            etq++;
        mayor_eq.arg1().procesa(this);
        if (es_designador(mayor_eq.arg1()))
            etq++;
        etq++;
    }

    public void procesa(Menor menor) {
        menor.arg0().procesa(this);
        if (es_designador(menor.arg0()))
            etq++;
        menor.arg1().procesa(this);
        if (es_designador(menor.arg1()))
            etq++;
        etq++;
    }

    public void procesa(Menor_eq menor_eq) {
        menor_eq.arg0().procesa(this);
        if (es_designador(menor_eq.arg0()))
            etq++;
        menor_eq.arg1().procesa(this);
        if (es_designador(menor_eq.arg1()))
            etq++;
        etq++;
    }

    public void procesa(Comp comp) {
        comp.arg0().procesa(this);
        if (es_designador(comp.arg0()))
            etq++;
        comp.arg1().procesa(this);
        if (es_designador(comp.arg1()))
            etq++;
        etq++;
    }

    public void procesa(Dist dist) {
        dist.arg0().procesa(this);
        if (es_designador(dist.arg0()))
            etq++;
        dist.arg1().procesa(this);
        if (es_designador(dist.arg1()))
            etq++;
        etq++;
    }

    public void procesa(Corch corch) {
        corch.arg0().procesa(this);
        corch.arg1().procesa(this);
        if (es_designador(corch.arg1()))
            etq++;
        etq += 2;
    }

    public void procesa(Punto punto) {
        punto.arg().procesa(this);
        etq += 2;
    }

    public void procesa(Flecha flecha) {
        flecha.arg().procesa(this);
        etq += 3;
    }

    public void procesa(Asterix asterix) {
        asterix.arg().procesa(this);
        etq++;
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

    private boolean es_designador(Exp e) {
        return true; //TODO implementar
    }
}
