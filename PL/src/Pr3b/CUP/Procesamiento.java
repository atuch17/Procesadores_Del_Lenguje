package Pr3b.CUP;

import Pr3b.TinyASint.And;
import Pr3b.TinyASint.Asterix;
import Pr3b.TinyASint.Comp;
import Pr3b.TinyASint.Corch;
import Pr3b.TinyASint.Dec;
import Pr3b.TinyASint.Dec_proc;
import Pr3b.TinyASint.Dec_tipo;
import Pr3b.TinyASint.Dec_var;
import Pr3b.TinyASint.Decs_muchas;
import Pr3b.TinyASint.Decs_una;
import Pr3b.TinyASint.Dist;
import Pr3b.TinyASint.Div;
import Pr3b.TinyASint.False;
import Pr3b.TinyASint.Flecha;
import Pr3b.TinyASint.Id;
import Pr3b.TinyASint.Inst;
import Pr3b.TinyASint.Insts_muchas;
import Pr3b.TinyASint.Insts_una;
import Pr3b.TinyASint.Mayor;
import Pr3b.TinyASint.Mayor_eq;
import Pr3b.TinyASint.Menor;
import Pr3b.TinyASint.Menor_eq;
import Pr3b.TinyASint.Menos;
import Pr3b.TinyASint.Mod;
import Pr3b.TinyASint.Mul;
import Pr3b.TinyASint.Not;
import Pr3b.TinyASint.Num;
import Pr3b.TinyASint.Or;
import Pr3b.TinyASint.Prog_con_decs;
import Pr3b.TinyASint.Prog_sin_decs;
import Pr3b.TinyASint.Punto;
import Pr3b.TinyASint.Resta;
import Pr3b.TinyASint.Suma;
import Pr3b.TinyASint.Tipo;
import Pr3b.TinyASint.True;

public interface Procesamiento {
	void procesa(Prog_con_decs prog);
	void procesa(Prog_sin_decs prog);
	void procesa(Decs_una decs);
	void procesa(Decs_muchas decs);
	void procesa(Dec dec);
	void procesa(Dec_var dec);
	void procesa(Dec_tipo dec);
	void procesa(Dec_proc dec);
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
    void procesa(Mod exp);
    
    void procesa(Corch exp);
    void procesa(Punto exp);
    void procesa(Flecha exp);
    
    void procesa(Menos exp);
    void procesa(Not exp);
    
    void procesa(Asterix exp);
}