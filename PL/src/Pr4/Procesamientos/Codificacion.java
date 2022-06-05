package Pr4.Procesamientos;

import java.util.Stack;

import Pr4.AST.TinyASint.*;
import Pr4.maquinaP.MaquinaP;

public class Codificacion extends ProcesamientoPorDefecto {
    private Stack<Dec_proc> procs;
    private MaquinaP m;
    private boolean primero;

    public Codificacion() {
        procs = new Stack<Dec_proc>();
        m = new MaquinaP(55,100,100,4);
        primero = true;
    }

    public MaquinaP getMaquina() {
        return m;
    }

    public void procesa(Prog_con_decs prog) {
        prog.insts().procesa(this);
        if (primero) {
            m.ponInstruccion(m.stop());
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
        if (inst.exp2().type().es_entero() && inst.exp1().type().es_real()) {
            if (inst.exp2().es_designador()) 
                m.ponInstruccion(m.apilaInd());
            m.ponInstruccion(m.desapilaInd());
        } else {
            if (inst.exp2().es_designador())
                m.ponInstruccion(m.mueve(inst.exp1().tam()));
            else
                m.ponInstruccion(m.desapilaInd());
        }
    }

    public void procesa(Inst_ifthen inst) {
        inst.exp1().procesa(this);
        if (inst.exp1().es_designador())
            m.ponInstruccion(m.apilaInd());
        m.ponInstruccion(m.irF(inst.sig()));
        inst.insts().procesa(this);
    }

    public void procesa(Inst_ifthenelse inst) {
        inst.exp1().procesa(this);
        if (inst.exp1().es_designador())
            m.ponInstruccion(m.apilaInd());
        m.ponInstruccion(m.irF(inst.bloque2().inicio()));
        inst.bloque1().procesa(this);
        m.ponInstruccion(m.irA(inst.sig()));
        inst.bloque2().procesa(this);
    }

    public void procesa(Inst_while inst) {
        inst.exp1().procesa(this);
        if (inst.exp1().es_designador())
            m.ponInstruccion(m.apilaInd());
        m.ponInstruccion(m.irF(inst.sig()));
        inst.insts().procesa(this);
        m.ponInstruccion(m.irA(inst.exp1().inicio()));
    }

    public void procesa(Inst_lectura inst) {
        inst.exp1().procesa(this);
        m.ponInstruccion(m.lee(inst.exp1().type()));
        m.ponInstruccion(m.desapilaInd());
    }

    public void procesa(Inst_escritura inst) {
        inst.exp1().procesa(this);
        if (inst.exp1().es_designador())
            m.ponInstruccion(m.apilaInd());
        m.ponInstruccion(m.imprime());
    }

    public void procesa(Inst_new_line inst) {
        m.ponInstruccion(m.newline());
    }

    public void procesa(Inst_reserv_mem inst) {
        inst.exp1().procesa(this);
        m.ponInstruccion(m.alloc(((Tipo_puntero) inst.exp1().type().refEx()).tipo().refEx().tam()));
        m.ponInstruccion(m.desapilaInd());
    }

    public void procesa(Inst_lib_mem inst) {
        inst.exp1().procesa(this);
        m.ponInstruccion(m.apilaInd());
        m.ponInstruccion(m.dealloc(((Tipo_puntero) inst.exp1().type().refEx()).tipo().refEx().tam()));
    }

    public void procesa(Inst_invoc_proc inst) {
        m.ponInstruccion(m.activa(inst.vinculo().nivel(), inst.vinculo().tam_datos(), inst.dir_sig()));
        gen_cod_params(inst.params(), ((Dec_proc) inst.vinculo()).params());
        m.ponInstruccion(m.desapilad(inst.vinculo().nivel()));
        m.ponInstruccion(m.irA(inst.vinculo().dir_inic()));
    }

    public void procesa(Inst_comp inst) {
        m.ponInstruccion(m.activa(inst.nivel(), inst.tam(), inst.sig()));
        m.ponInstruccion(m.desapilad(inst.nivel()));
        inst.b().procesa(this);
        m.ponInstruccion(m.desactiva(inst.nivel(), inst.tam()));
        m.ponInstruccion(m.irInd());
        //m.ponInstruccion(m.irA(inst.inicio()));
    }

    private void gen_cod_params(Expresiones exprs, ParamsF params) {
        if (exprs.hay_muchas() && params.hay_muchas()) {
            gen_cod_params(((Exprs_muchas) exprs).expresiones(), ((Params_muchos_f) params).params());
            gen_cod_paso(((Exprs_muchas) exprs).exp(), ((Params_muchos_f) params).param());
        } else if (exprs.hay_una() && params.hay_una()) {
            gen_cod_paso(((Exprs_una) exprs).exp(), ((Params_uno_f) params).param());
        }
    }

    private void gen_cod_paso(Exp exp, ParamF param) {
        m.ponInstruccion(m.dup());
        m.ponInstruccion(m.apilaInt(param.dir()));
        m.ponInstruccion(m.sumai());
        exp.procesa(this);
        if (exp.es_designador() && param.por_valor())
            m.ponInstruccion(m.mueve(param.tam()));
        else
            m.ponInstruccion(m.desapilaInd());
    }

    public void procesa(Num_int num) {
        m.ponInstruccion(m.apilaInt(Integer.valueOf(num.s().toString())));
    }

    public void procesa(Num_real num) {
        m.ponInstruccion(m.apilaReal(Double.valueOf(num.s().toString())));
    }

    public void procesa(True t) {
        m.ponInstruccion(m.apilaBool(true));
    }

    public void procesa(False f) {
        m.ponInstruccion(m.apilaBool(false));
    }

    public void procesa(Sstring str) {
        m.ponInstruccion(m.apilaString(str.string().toString()));
    }

    public void procesa(None n) {
        m.ponInstruccion(m.apilaPuntero(-1));
    }

    public void procesa(Id id) {
        Dec vinculo = id.vinculo();
        if (vinculo.nivel() == 0)
            m.ponInstruccion(m.apilaInt(vinculo.dir()));
        else {
            m.ponInstruccion(m.apilad(vinculo.nivel()));
            m.ponInstruccion(m.apilaInt(vinculo.dir()));
            m.ponInstruccion(m.sumai());
            if (vinculo.es_param_ref())
                m.ponInstruccion(m.apilaInd());
        }
    }

    public void procesa(Suma suma) {
        Exp E0 = suma.arg0();
        E0.procesa(this);
        if (E0.es_designador())
            m.ponInstruccion(m.apilaInd());
        Exp E1 = suma.arg1();
        E1.procesa(this);
        if (E1.es_designador())
            m.ponInstruccion(m.apilaInd());
        
        if (E0.type().refEx().es_entero() && E1.type().refEx().es_entero())
            m.ponInstruccion(m.sumai());
        else
            m.ponInstruccion(m.sumar());
    }

    public void procesa(Resta resta) {
        Exp E0 = resta.arg0();
        E0.procesa(this);
        if (E0.es_designador())
            m.ponInstruccion(m.apilaInd());
        Exp E1 = resta.arg1();
        E1.procesa(this);
        if (E1.es_designador())
            m.ponInstruccion(m.apilaInd());
        
        if (E0.type().refEx().es_entero() && E1.type().refEx().es_entero())
            m.ponInstruccion(m.restai());
        else
            m.ponInstruccion(m.restar());
    }

    public void procesa(Mul mul) {
        Exp E0 = mul.arg0();
        E0.procesa(this);
        if (E0.es_designador())
            m.ponInstruccion(m.apilaInd());
        Exp E1 = mul.arg1();
        E1.procesa(this);
        if (E1.es_designador())
            m.ponInstruccion(m.apilaInd());

        if (E0.type().refEx().es_entero() && E1.type().refEx().es_entero())
            m.ponInstruccion(m.muli());
        else
            m.ponInstruccion(m.mulr());
    }

    public void procesa(Div div) {
        Exp E0 = div.arg0();
        E0.procesa(this);
        if (E0.es_designador())
            m.ponInstruccion(m.apilaInd());
        Exp E1 = div.arg1();
        E1.procesa(this);
        if (E1.es_designador())
            m.ponInstruccion(m.apilaInd());
        
        if (E0.type().refEx().es_entero() && E1.type().refEx().es_entero())
            m.ponInstruccion(m.divi());
        else
            m.ponInstruccion(m.divr());
    }

    public void procesa(Menos menos) {
        Exp E = menos.arg();
        E.procesa(this);
        if (E.es_designador())
            m.ponInstruccion(m.apilaInd());

        if (E.type().refEx().es_entero())
            m.ponInstruccion(m.menosi());
        else
            m.ponInstruccion(m.menosr());
    }

    public void procesa(And and) {
        Exp E0 = and.arg0();
        E0.procesa(this);
        if (E0.es_designador())
            m.ponInstruccion(m.apilaInd());
        Exp E1 = and.arg1();
        E1.procesa(this);
        if (E1.es_designador())
            m.ponInstruccion(m.apilaInd());

        m.ponInstruccion(m.and());
    }

    public void procesa(Or or) {
        Exp E0 = or.arg0();
        E0.procesa(this);
        if (E0.es_designador())
            m.ponInstruccion(m.apilaInd());
        Exp E1 = or.arg1();
        E1.procesa(this);
        if (E1.es_designador())
            m.ponInstruccion(m.apilaInd());

        m.ponInstruccion(m.or());
    }

    public void procesa(Not not) {
        Exp E = not.arg();
        E.procesa(this);
        if (E.es_designador())
            m.ponInstruccion(m.apilaInd());

        m.ponInstruccion(m.not());
    }

    public void procesa(Mayor mayor) {
        Exp E0 = mayor.arg0();
        E0.procesa(this);
        if (E0.es_designador())
            m.ponInstruccion(m.apilaInd());
        Exp E1 = mayor.arg1();
        E1.procesa(this);
        if (E1.es_designador())
            m.ponInstruccion(m.apilaInd());

        Tipo t = E0.type().refEx();
        if (t.es_entero() || t.es_real())
            m.ponInstruccion(m.mayorr());
        else if (t.es_booleano())
            m.ponInstruccion(m.mayorb());
        else
            m.ponInstruccion(m.mayors());
    }

    public void procesa(Mayor_eq mayor_igual) {
        Exp E0 = mayor_igual.arg0();
        E0.procesa(this);
        if (E0.es_designador())
            m.ponInstruccion(m.apilaInd());
        Exp E1 = mayor_igual.arg1();
        E1.procesa(this);
        if (E1.es_designador())
            m.ponInstruccion(m.apilaInd());
        
        Tipo t = E0.type().refEx();
        if (t.es_entero() || t.es_real())
            m.ponInstruccion(m.mayorigr());
        else if (t.es_booleano())
            m.ponInstruccion(m.mayorigb());
        else
            m.ponInstruccion(m.mayorigs());
    }

    public void procesa(Menor menor) {
        Exp E0 = menor.arg0();
        E0.procesa(this);
        if (E0.es_designador())
            m.ponInstruccion(m.apilaInd());
        Exp E1 = menor.arg1();
        E1.procesa(this);
        if (E1.es_designador())
            m.ponInstruccion(m.apilaInd());
        
        Tipo t = E0.type().refEx();
        if (t.es_entero() || t.es_real())
            m.ponInstruccion(m.menorr());
        else if (t.es_booleano())
            m.ponInstruccion(m.menorb());
        else
            m.ponInstruccion(m.menors());
    }

    public void procesa(Menor_eq menor_igual) {
        Exp E0 = menor_igual.arg0();
        E0.procesa(this);
        if (E0.es_designador())
            m.ponInstruccion(m.apilaInd());
        Exp E1 = menor_igual.arg1();
        E1.procesa(this);
        if (E1.es_designador())
            m.ponInstruccion(m.apilaInd());
        
        Tipo t = E0.type().refEx();
        if (t.es_entero() || t.es_real())
            m.ponInstruccion(m.menorigr());
        else if (t.es_booleano())
            m.ponInstruccion(m.menorigb());
        else
            m.ponInstruccion(m.menorigs());
    }

    public void procesa(Comp comp) {
        Exp E0 = comp.arg0();
        E0.procesa(this);
        if (E0.es_designador())
            m.ponInstruccion(m.apilaInd());
        Exp E1 = comp.arg1();
        E1.procesa(this);
        if (E1.es_designador())
            m.ponInstruccion(m.apilaInd());
        
        Tipo t = E0.type().refEx();
        if (t.es_entero() || t.es_real())
            m.ponInstruccion(m.compr());
        else if (t.es_booleano())
            m.ponInstruccion(m.compb());
        else if (t.es_cadena())
            m.ponInstruccion(m.comps());
        else
            m.ponInstruccion(m.compp());
    }

    public void procesa(Dist dist) {
        Exp E0 = dist.arg0();
        E0.procesa(this);
        if (E0.es_designador())
            m.ponInstruccion(m.apilaInd());
        Exp E1 = dist.arg1();
        E1.procesa(this);
        if (E1.es_designador())
            m.ponInstruccion(m.apilaInd());
        
        Tipo t = E0.type().refEx();
        if (t.es_entero() || t.es_real())
            m.ponInstruccion(m.distr());
        else if (t.es_booleano())
            m.ponInstruccion(m.distb());
        else if (t.es_cadena())
            m.ponInstruccion(m.dists());
        else
            m.ponInstruccion(m.distp());
    }

    public void procesa(Corch corch) {
        corch.arg0().procesa(this);
        Exp E1 = corch.arg1();
        E1.procesa(this);
        if (E1.es_designador())
            m.ponInstruccion(m.apilaInd());
        m.ponInstruccion(m.apilaInt(corch.type().tam()));
        m.ponInstruccion(m.muli());
        m.ponInstruccion(m.sumai());
    }

    public void procesa(Punto punto) {
        punto.arg().procesa(this);
        m.ponInstruccion(m.apilaInt(punto.arg().type().refEx().despDeCampo(punto.id())));
        m.ponInstruccion(m.sumai());
    }

    public void procesa(Flecha flecha) {
        flecha.arg().procesa(this);
        m.ponInstruccion(m.apilaInd());
        m.ponInstruccion(m.apilaInt(((Tipo_puntero) flecha.arg().type().refEx()).tipo().refEx().despDeCampo(flecha.id())));
        m.ponInstruccion(m.sumai());
    }

    public void procesa(Asterix asterix) {
        asterix.arg().procesa(this);
        m.ponInstruccion(m.apilaInd());
    }

    public void procesa(Dec_proc proc) {
        proc.bloque().procesa(this);
        m.ponInstruccion(m.desactiva(proc.nivel(), proc.tam_datos()));
        m.ponInstruccion(m.irInd());
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
