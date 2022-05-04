package Pr3b;

import Pr3b.TinyASint.*;

public interface Procesamiento {
	void procesa(Prog_con_decs prog);
	void procesa(Prog_sin_decs prog);
	void procesa(Decs_una decs);
	void procesa(Decs_muchas decs);
	//void procesa(Dec dec);
	void procesa(Dec_var dec);
	void procesa(Dec_tipo dec);
	void procesa(Dec_proc dec);
	void procesa(Params_ninguno p);
	void procesa(Params_uno_f p);
	void procesa(Params_muchos_f p);
	void procesa(Param_f_sin_amp p);
	void procesa(Param_f_con_amp p);
	void procesa(Insts_ninguna insts);
	void procesa(Insts_una insts);
	void procesa(Insts_muchas insts);
	void procesa(Inst_asig inst);
	void procesa(Inst_ifthen inst);
	void procesa(Inst_ifthenelse inst);
	void procesa(Inst_while inst);
	void procesa(Inst_lectura inst);
	void procesa(Inst_escritura inst);
	void procesa(Inst_new_line inst);
	void procesa(Inst_reserv_mem inst);
	void procesa(Inst_lib_mem inst);
	void procesa(Inst_invoc_proc inst);
	void procesa(Inst_comp inst);
	void procesa(Exprs_ninguna e);
	void procesa(Exprs_una e);
	void procesa(Exprs_muchas e);
	void procesa(ProgramaAux p);
	//void procesa(Var exp);
	//void procesa(Tipo tipo);
	void procesa(Tipo_id tipo);
	void procesa(Tipo_int tipo);
	void procesa(Tipo_real tipo);
	void procesa(Tipo_bool tipo);
	void procesa(Tipo_cadena tipo);
	void procesa(Tipo_array tipo);
	void procesa(Tipo_registro tipo);
	void procesa(Tipo_puntero tipo);
	void procesa(Campos_uno c);
	void procesa(Campos_muchos c);
	void procesa(Campo c);
	void procesa(Bloque_vacio b);
	void procesa(Bloque_lleno b);
	
	//void procesa(Num exp);
	void procesa(Id exp);
	
	void procesa(True exp);
	void procesa(False exp);
	
	void procesa(Sstring exp);
	void procesa(None exp);
	void procesa(Num_int exp);
	void procesa(Num_real exp);
	
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