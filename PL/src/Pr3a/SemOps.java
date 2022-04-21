package Pr3a;

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

    public Prog prog(Decs decs, Insts insts) {
        return cprog(decs, insts);
    }

    public Dec dec(StringLocalizado tipo, StringLocalizado id) {
        return cdec(tipo, id);
    }

    public Decs decs_muchas(Decs decs, Dec dec) {
        return cdecs_muchas(decs, dec);
    }

    public Decs decs_una(Dec dec) {
        return cdecs_una(dec);
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
