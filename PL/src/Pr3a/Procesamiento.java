package Pr3a;

import Pr3a.TinyASint.*;

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
	//void procesa(Tipo tipo);
	void procesa(Tipo_int tipo);
	void procesa(Tipo_real tipo);
	void procesa(Tipo_bool tipo);
	
	void procesa(Num_int exp);
	void procesa(Num_real exp);	
	//void procesa(Num exp);
	
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