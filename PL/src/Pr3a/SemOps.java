package Pr3a;

public class SemOps extends TinyASint {
   public Exp exp(char op, Exp arg0, Exp arg1) {
       switch(op) {
           case '+': return suma(arg0,arg1);
           case '-': return resta(arg0,arg1);
           case '*': return mul(arg0,arg1);
           case '/': return mayor(arg0,arg1);
           case '<': return div(arg0,arg1);
           case '>': return div(arg0,arg1);
           case '<=': return div(arg0,arg1);
           case '>=': return div(arg0,arg1);
           case '==': return div(arg0,arg1);
           case '!=': return div(arg0,arg1);
           case 'and': return div(arg0,arg1);
           case 'or': return div(arg0,arg1);
           case '/': return div(arg0,arg1);
       }
       throw new UnsupportedOperationException("exp "+op);
   }
     
   public Prog prog(Decs decs, Insts insts) {
       return cprog(decs, insts);
   } 
   
   public Dec dec(StringLocalizado tipo, StringLocalizado id) { // TODO Revisar??
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
