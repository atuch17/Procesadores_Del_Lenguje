package Pr4.AST;

public class SemOps extends TinyASint {
    public Exp exp(String op, Exp arg0, Exp arg1) {
        switch (op) {
            case "+":
                return csuma(arg0, arg1);
            case "-":
                return cresta(arg0, arg1);
            case "*":
                return cmul(arg0, arg1);
            case "/":
                return cdiv(arg0, arg1);
            case "%":
                return cmod(arg0, arg1);
            case "<":
                return cmenor(arg0, arg1);
            case ">":
                return cmayor(arg0, arg1);
            case "<=":
                return cmenor_eq(arg0, arg1);
            case ">=":
                return cmayor_eq(arg0, arg1);
            case "==":
                return ccomp(arg0, arg1);
            case "!=":
                return cdist(arg0, arg1);
            case "and":
                return cand(arg0, arg1);
            case "or":
                return cor(arg0, arg1);
            case "[]":
                return ccorch(arg0, arg1);
        }
        throw new UnsupportedOperationException("exp " + op);
    }

    public Exp exp(String op, Exp arg) {
        switch (op) {
            case "-":
                return cmenos(arg);
            case "not":
                return cnot(arg);
        }
        throw new UnsupportedOperationException("exp " + op);
    }

    public Exp exp(String op, Exp arg, StringLocalizado id) {
        switch (op) {
            case ".":
                return cpunto(arg, id);
            case "->":
                return cflecha(arg, id);
        }
        throw new UnsupportedOperationException("exp " + op);
    }

    public Exp exp(Exp arg) {
        return casterix(arg);
    }

    public Prog prog_con_decs(Decs decs, Insts insts) {
        return cprog_con_decs(decs, insts);
    }

    public Prog prog_sin_decs(Insts insts) {
        return cprog_sin_decs(insts);
    }

    public Decs decs_muchas(Decs decs, Dec dec) {
        return cdecs_muchas(decs, dec);
    }

    public Decs decs_una(Dec dec) {
        return cdecs_una(dec);
    }

    public Dec_var dec_var(Tipo tipo, StringLocalizado id) {
        return cdec_var(tipo, id);
    }

    public Dec_tipo dec_tipo(Tipo tipo, StringLocalizado id) {
        return cdec_tipo(tipo, id);
    }

    public Dec_proc dec_proc(StringLocalizado id, ParamsF params, Bloque bloque) {
        return cdec_proc(id, params, bloque);
    }

    public ParamsF params_uno_f(ParamF param) {
    	return cparams_uno_f(param);
    }
    public ParamsF params_muchos_f(ParamsF params, ParamF param) {
    	return cparams_muchos_f(params, param);
    }
    public ParamF param_f_sin_amp(Tipo tipo, StringLocalizado id) {
    	return cparam_f_sin_amp(tipo, id);
    }
    public ParamF param_f_con_amp(Tipo tipo, StringLocalizado id) {
    	return cparam_f_con_amp(tipo, id);
    }
    
    public Insts insts_muchas(Insts insts, Inst inst) {
        return cinsts_muchas(insts, inst);
    }

    public Insts insts_una(Inst inst) {
        return cinsts_una(inst);
    }
    
    /* TERMINAL */
    public Inst inst_asig(Exp exp1, Exp exp2) {
        return cinst_asig(exp1, exp2);
    }
    
    public InstsOpc insts_opc_sin_insts() {
    	return cinsts_opc_sin_insts();
    }
    
    public InstsOpc insts_opc_con_insts(Insts insts) {
    	return cinsts_opc_con_insts(insts);
    }
    
    public Inst inst_ifthen(Exp exp, InstsOpc insts) {
    	return cinst_ifthen(exp, insts);
    }
    
    public Inst inst_ifthenelse (Exp exp, InstsOpc insts1, InstsOpc insts2) {
    	return cinst_ifthenelse(exp, insts1, insts2);
    }
    
    public Inst_ifthen inst_if_then (Exp exp, InstsOpc insts1) {
    	return new Inst_ifthen(exp,insts1);
    }
    
    public Inst inst_while(Exp exp, InstsOpc insts) {
    	return cinst_while(exp, insts);
    }
    
    public Inst inst_lectura(Exp exp) {
    	return cinst_lectura(exp);
    }
    
    public Inst inst_escritura(Exp exp) {
    	return cinst_escritura(exp);
    }
    
    public Inst inst_new_line() {
    	return cinst_new_line();
    }
    
    public Inst inst_reserv_mem(Exp exp) {
    	return cinst_reserv_mem(exp);
    }
    
    public Inst inst_lib_mem(Exp exp) {
    	return cinst_lib_mem(exp);
    }
    
    public Inst inst_invoc_proc(StringLocalizado id, ParamsR params) {
    	return cinst_invoc_proc(id, params);
    }
    
    public Inst inst_comp(Bloque b) {
    	return cinst_comp(b);
    }
    
    public ParamsR params_vacio() {
    	return cparams_vacio();
    }
    
    public ParamsR params_lleno(Expresiones e) {
    	return cparams_lleno(e);
    }
    
    public Expresiones exprs_una(Exp exp) {
    	return cexprs_una(exp);
    }
    
    public Expresiones exprs_muchas(Expresiones e, Exp exp) {
    	return cexprs_muchas(e, exp);
    }
    
    public Id id(StringLocalizado id) {
    	return cid(id);
    }
    
    public Tipo tipo_id(StringLocalizado id) {
    	return ctipo_id(id);
    }

    public Tipo t_int() {
        return ctipo_int();
    }

    public Tipo t_real() {
        return ctipo_real();
    }

    public Tipo t_bool() {
        return ctipo_bool();
    }

    public Tipo t_cadena() {
        return ctipo_cadena();
    }

    public Exp cadena(StringLocalizado s) {
        return ccadena(s);
    }
    
    public Exp none() { // Null
    	return cnone();
    }
    
    public Tipo tipo_array(StringLocalizado s, Tipo t) { 
    	return ctipo_array(s, t);
    }
    
    public Tipo tipo_registro(Campos c) { 
    	return ctipo_registro(c);
    }
    
    public Campos campos_uno(Campo c) { 
    	return ccampos_uno(c);
    }
    
    public Campos campos_muchos(Campos cs, Campo c) { 
    	return ccampos_muchos(cs, c);
    }
    
    public Campo campo(Tipo t, StringLocalizado id) { 
    	return ccampo(t, id);
    }
    
    public Tipo tipo_puntero(Tipo t) { 
    	return ctipo_puntero(t);
    }
    
    public Bloque bloque_vacio() { 
    	return cbloque_vacio();
    }
    
    public Bloque bloque_lleno(Prog p) { 
    	return cbloque_lleno(p);
    }
    
    public Exp num_int(StringLocalizado s) { 
    	return cnum_int(s);
    }
    
    public Exp num_real(StringLocalizado s) {
    	return cnum_real(s);
    }    
    
    public Exp verdad() { 
    	return ctrue_bool();
    }
    
    public Exp falso() {
    	return cfalse_bool();
    }
}
