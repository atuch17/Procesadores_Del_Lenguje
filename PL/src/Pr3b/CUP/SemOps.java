package Pr3b.CUP;

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

    public Exp exp(String op, Exp arg, Id id) {
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
        return cprog(decs, insts);
    }

    public Prog prog_sin_decs(Insts insts) {
        return cprog(insts);
    }

    public Decs decs_muchas(Decs decs, Dec dec) {
        return cdecs_muchas(decs, dec);
    }

    public Decs decs_una(Dec dec) {
        return cdecs_una(dec);
    }

    public Dec dec_var(StringLocalizado tipo, StringLocalizado id) {
        return cdec_var(tipo, id);
    }

    public Dec dec_tipo(StringLocalizado tipo, StringLocalizado id) {
        return cdec_tipo(tipo, id);
    }

    public Dec dec_proc(StringLocalizado id, ParamsF params, Insts insts) {
        return cdec_proc(id, params, insts);
    }

    public Inst inst(StringLocalizado id, Exp exp) {
        return cinst(id, exp);
    }

    public Insts insts_muchas(Insts insts, Inst inst) {
        return cinsts_muchas(insts, inst);
    }

    public Insts insts_una(Inst inst) {
        return cinsts_una(inst);
    }
}
