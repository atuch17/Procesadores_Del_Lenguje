package Pr3a;

import Pr3a.TinyASint.And;
import Pr3a.TinyASint.Comp;
import Pr3a.TinyASint.Dec;
import Pr3a.TinyASint.Decs_muchas;
import Pr3a.TinyASint.Decs_una;
import Pr3a.TinyASint.Dist;
import Pr3a.TinyASint.Div;
import Pr3a.TinyASint.Exp;
import Pr3a.TinyASint.False;
import Pr3a.TinyASint.Id;
import Pr3a.TinyASint.Inst;
import Pr3a.TinyASint.Insts_muchas;
import Pr3a.TinyASint.Insts_una;
import Pr3a.TinyASint.Mayor;
import Pr3a.TinyASint.Mayor_eq;
import Pr3a.TinyASint.Menor;
import Pr3a.TinyASint.Menor_eq;
import Pr3a.TinyASint.Menos;
import Pr3a.TinyASint.Mul;
import Pr3a.TinyASint.Not;
import Pr3a.TinyASint.Num;
import Pr3a.TinyASint.Or;
import Pr3a.TinyASint.Prog;
import Pr3a.TinyASint.Resta;
import Pr3a.TinyASint.Suma;
import Pr3a.TinyASint.True;

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
       System.out.print("  "+dec.tipo() + " " + dec.id());
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
	   System.out.print("  "+inst.id()+"="+inst.exp());
   }
   
   public void procesa(Suma exp) {
      imprime_arg(exp.arg0(),1); 
      System.out.print("+");
      imprime_arg(exp.arg1(),0);       
   }
   
   public void procesa(Resta exp) {
      imprime_arg(exp.arg0(),1); 
      System.out.print("-");
      imprime_arg(exp.arg1(),1);       
   }
   
   public void procesa(Mul exp) {
      imprime_arg(exp.arg0(),4); 
      System.out.print("*");
      imprime_arg(exp.arg1(),4);       
   }
   
   public void procesa(Div exp) {
      imprime_arg(exp.arg0(),4); 
      System.out.print("/");
      imprime_arg(exp.arg1(),4);       
   }
   
   public void procesa(Mayor exp) {
	   imprime_arg(exp.arg0(),2); 
	   System.out.print(">");
	   imprime_arg(exp.arg1(),3);       
   }
   
   public void procesa(Menor exp) {
	   imprime_arg(exp.arg0(),2); 
	   System.out.print("<");
	   imprime_arg(exp.arg1(),3);       
   }
   
   public void procesa(Mayor_eq exp) {
	   imprime_arg(exp.arg0(),2); 
	   System.out.print(">=");
	   imprime_arg(exp.arg1(),3);       
   }
   
   public void procesa(Menor_eq exp) {
	   imprime_arg(exp.arg0(),2); 
	   System.out.print("<=");
	   imprime_arg(exp.arg1(),3);       
   }
   
   public void procesa(Comp exp) {
	   imprime_arg(exp.arg0(),2); 
	   System.out.print("==");
	   imprime_arg(exp.arg1(),3);       
   }
   
   public void procesa(Dist exp) {
	   imprime_arg(exp.arg0(),2); 
	   System.out.print("!=");
	   imprime_arg(exp.arg1(),3);       
   }
   
   public void procesa(And exp) {
	   imprime_arg(exp.arg0(),1); 
	   System.out.print("and");
	   imprime_arg(exp.arg1(),2);       
   }
   
   public void procesa(Or exp) {
	   imprime_arg(exp.arg0(),1); 
	   System.out.print("or");
	   imprime_arg(exp.arg1(),2);       
   }
   
   public void procesa(Not exp) {
	   System.out.print("not");
	   imprime_arg(exp.arg(),4);   // TODO
   }
   
   public void procesa(Menos exp) {
	   System.out.print("-");
	   imprime_arg(exp.arg(),5);   // TODO 
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
       }
       else {
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

            