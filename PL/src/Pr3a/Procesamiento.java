package Pr3a;

import asint.TinyASint.Suma;
import asint.TinyASint.Resta;
import asint.TinyASint.Mul;
import asint.TinyASint.Div;
import asint.TinyASint.Id;
import asint.TinyASint.Num;
import asint.TinyASint.Dec;
import asint.TinyASint.Decs_muchas;
import asint.TinyASint.Decs_una;
import asint.TinyASint.Prog_sin_decs;
import asint.TinyASint.Prog_con_decs;

public interface Procesamiento {
	void procesa(Prog exp);
	void procesa(Decs_una decs);
	void procesa(Decs_muchas decs);
	void procesa(Dec dec);
	void procesa(Insts_una decs);
	void procesa(Insts_muchas decs);
	void procesa(Inst exp);
	
	void procesa(Id exp);
	void procesa(Var exp);
	void procesa(Tipo exp);
	
	void procesa(NumInt exp);
	void procesa(NumReal exp);
	
	void procesa(True exp);
	void procesa(False exp);
	
    void procesa(Suma exp);
    void procesa(Resta exp);
    void procesa(Mul exp);
    void procesa(Div exp);
    void procesa(Menos exp);
    
    void procesa(And exp);
    void procesa(Or exp);
    void procesa(Not exp);
    
    void procesa(Mayor prog);    
    void procesa(Menor prog);    
    void procesa(Mayor_eq prog);    
    void procesa(Menor_eq prog);    
    
    void procesa(Comp prog);    
    void procesa(Dist prog);    
}