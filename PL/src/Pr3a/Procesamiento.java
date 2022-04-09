package Pr3a;

import Pr3a.TinyASint.And;
import Pr3a.TinyASint.Comp;
import Pr3a.TinyASint.Dec;
import Pr3a.TinyASint.Decs_muchas;
import Pr3a.TinyASint.Decs_una;
import Pr3a.TinyASint.Dist;
import Pr3a.TinyASint.Div;
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
import Pr3a.TinyASint.Tipo;
import Pr3a.TinyASint.True;

public interface Procesamiento {
	void procesa(Prog prog);
	void procesa(Decs_una decs);
	void procesa(Decs_muchas decs);
	void procesa(Dec dec);
	void procesa(Insts_una insts);
	void procesa(Insts_muchas intss);
	void procesa(Inst inst);
	
	void procesa(Id exp);
	//void procesa(Var exp);
	void procesa(Tipo tipo);
	
	//void procesa(NumInt exp);
	//void procesa(NumReal exp);
	void procesa(Num exp);
	
	void procesa(True exp);
	void procesa(False exp);
	
    void procesa(Suma exp);
    void procesa(Resta exp);
    
    void procesa(And exp);
    void procesa(Or exp);
    
    void procesa(Mayor exp);    
    void procesa(Menor exp);    
    void procesa(Mayor_eq exp);    
    void procesa(Menor_eq exp);
    void procesa(Comp exp);    
    void procesa(Dist exp);    
    
    void procesa(Mul exp);
    void procesa(Div exp);
    
    void procesa(Menos exp);
    void procesa(Not exp);
}