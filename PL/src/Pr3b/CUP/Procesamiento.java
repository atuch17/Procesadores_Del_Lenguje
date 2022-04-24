package Pr3b.CUP;

import Pr3b.CUP.TinyASint.And;
import Pr3b.CUP.TinyASint.Asterix;
import Pr3b.CUP.TinyASint.Comp;
import Pr3b.CUP.TinyASint.Corch;
import Pr3b.CUP.TinyASint.Dec;
import Pr3b.CUP.TinyASint.Dec_proc;
import Pr3b.CUP.TinyASint.Dec_tipo;
import Pr3b.CUP.TinyASint.Dec_var;
import Pr3b.CUP.TinyASint.Decs_muchas;
import Pr3b.CUP.TinyASint.Decs_una;
import Pr3b.CUP.TinyASint.Dist;
import Pr3b.CUP.TinyASint.Div;
import Pr3b.CUP.TinyASint.False;
import Pr3b.CUP.TinyASint.Flecha;
import Pr3b.CUP.TinyASint.Id;
import Pr3b.CUP.TinyASint.Inst;
import Pr3b.CUP.TinyASint.Insts_muchas;
import Pr3b.CUP.TinyASint.Insts_una;
import Pr3b.CUP.TinyASint.Mayor;
import Pr3b.CUP.TinyASint.Mayor_eq;
import Pr3b.CUP.TinyASint.Menor;
import Pr3b.CUP.TinyASint.Menor_eq;
import Pr3b.CUP.TinyASint.Menos;
import Pr3b.CUP.TinyASint.Mod;
import Pr3b.CUP.TinyASint.Mul;
import Pr3b.CUP.TinyASint.Not;
import Pr3b.CUP.TinyASint.Num;
import Pr3b.CUP.TinyASint.Or;
import Pr3b.CUP.TinyASint.Prog_con_decs;
import Pr3b.CUP.TinyASint.Prog_sin_decs;
import Pr3b.CUP.TinyASint.Punto;
import Pr3b.CUP.TinyASint.Resta;
import Pr3b.CUP.TinyASint.Suma;
import Pr3b.CUP.TinyASint.Tipo;
import Pr3b.CUP.TinyASint.True;

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