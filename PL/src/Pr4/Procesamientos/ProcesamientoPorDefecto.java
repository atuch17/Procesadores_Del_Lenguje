package Pr4.Procesamientos;

import Pr4.AST.TinyASint.*;

public class ProcesamientoPorDefecto implements Procesamiento {
	public void procesa(Prog_con_decs prog) {}
	public void procesa(Prog_sin_decs prog) {}
	public void procesa(Decs_una decs) {}
	public void procesa(Decs_muchas decs) {}
	public void procesa(Dec_var dec) {}
	public void procesa(Dec_tipo dec) {}
	public void procesa(Dec_proc dec) {}
	public void procesa(Params_ninguno p) {}
	public void procesa(Params_uno_f p) {}
	public void procesa(Params_muchos_f p) {}
	public void procesa(Param_f_sin_amp p) {}
	public void procesa(Param_f_con_amp p) {}
	public void procesa(Insts_ninguna insts) {}
	public void procesa(Insts_una insts) {}
	public void procesa(Insts_muchas insts) {}
	public void procesa(Inst_asig inst) {}
	public void procesa(Inst_ifthen inst) {}
	public void procesa(Inst_ifthenelse inst) {}
	public void procesa(Inst_while inst) {}
	public void procesa(Inst_lectura inst) {}
	public void procesa(Inst_escritura inst) {}
	public void procesa(Inst_new_line inst) {}
	public void procesa(Inst_reserv_mem inst) {}
	public void procesa(Inst_lib_mem inst) {}
	public void procesa(Inst_invoc_proc inst) {}
	public void procesa(Inst_comp inst) {}
	public void procesa(Exprs_ninguna e) {}
	public void procesa(Exprs_una e) {}
	public void procesa(Exprs_muchas e) {}
	public void procesa(Tipo_id tipo) {}
	public void procesa(Tipo_int tipo) {}
	public void procesa(Tipo_real tipo) {}
	public void procesa(Tipo_bool tipo) {}
	public void procesa(Tipo_cadena tipo) {}
	public void procesa(Tipo_array tipo) {}
	public void procesa(Tipo_registro tipo) {}
	public void procesa(Tipo_puntero tipo) {}
	public void procesa(Campos_uno c) {}
	public void procesa(Campos_muchos c) {}
	public void procesa(Campo c) {}
	public void procesa(Bloque_vacio b) {}
	public void procesa(Bloque_lleno b) {}
	public void procesa(Id exp) {}
	public void procesa(True exp) {}
	public void procesa(False exp) {}
	public void procesa(Sstring exp) {}
	public void procesa(None exp) {}
	public void procesa(Num_int exp) {}
	public void procesa(Num_real exp) {}
	public void procesa(Suma exp) {}
	public void procesa(Resta exp) {}
	public void procesa(And exp) {}
	public void procesa(Or exp) {}
	public void procesa(Mayor exp) {}
	public void procesa(Menor exp) {}
	public void procesa(Mayor_eq exp) {}
	public void procesa(Menor_eq exp) {}
	public void procesa(Comp exp) {}
	public void procesa(Dist exp) {}
	public void procesa(Mul exp) {}
	public void procesa(Div exp) {}
	public void procesa(Mod exp) {}
	public void procesa(Corch exp) {}
	public void procesa(Punto exp) {}
	public void procesa(Flecha exp) {}
	public void procesa(Menos exp) {}
	public void procesa(Not exp) {}
	public void procesa(Asterix exp) {}
	public void procesa(ProgramaAux aux) {}
	public void vincula_decs_fase2(Prog_con_decs prog) {}
	public void vincula_procs(Prog_con_decs prog) {}
	public void vincula_decs_fase2(Prog_sin_decs prog) {}
	public void vincula_decs_fase2(Decs_una decs) {}
	public void vincula_procs(Decs_una decs) {}
	public void vincula_decs_fase2(Decs_muchas decs) {}
	public void vincula_procs(Decs_muchas decs) {}
	public void vincula_decs_fase2(Dec_var dec) {}
	public void vincula_procs(Dec_var dec) {}
	public void vincula_decs_fase2(Dec_tipo dec) {}
	public void vincula_procs(Dec_tipo dec) {}
	public void vincula_decs_fase2(Dec_proc dec) {}
	public void vincula_procs(Dec_proc dec) {}
	public void vincula_decs_fase2(Params_ninguno p) {}
	public void vincula_decs_fase2(Params_uno_f p) {}
	public void vincula_decs_fase2(Params_muchos_f p) {}
	public void vincula_decs_fase2(Param_f_sin_amp p) {}
	public void vincula_decs_fase2(Param_f_con_amp p) {}
	public void vincula_decs_fase2(ProgramaAux p) {}
	public void vincula_decs_fase2(Tipo_id tipo) {}
	public void vincula_decs_fase2(Tipo_int tipo) {}
	public void vincula_decs_fase2(Tipo_real tipo) {}
	public void vincula_decs_fase2(Tipo_bool tipo) {}
	public void vincula_decs_fase2(Tipo_cadena tipo) {}
	public void vincula_decs_fase2(Tipo_array tipo) {}
	public void vincula_decs_fase2(Tipo_registro tipo) {}
	public void vincula_decs_fase2(Tipo_puntero tipo) {}
	public void vincula_decs_fase2(Campos_uno c) {}
	public void vincula_decs_fase2(Campos_muchos c) {}
	public void vincula_decs_fase2(Campo c) {}
	public void vincula_decs_fase2(Bloque_vacio b) {}
	public void vincula_decs_fase2(Bloque_lleno b) {}
	public void recolecta_procs(Bloque_vacio b) {}
	public void recolecta_procs(Bloque_lleno b) {}
	public void recolecta_procs(ProgramaAux p) {}
	public void recolecta_procs(Prog_con_decs prog) {}
	public void recolecta_procs(Prog_sin_decs prog) {}
	public void recolecta_procs(Decs_una decs) {}
	public void recolecta_procs(Decs_muchas decs) {}
	public void recolecta_procs(Dec_var dec) {}
	public void recolecta_procs(Dec_tipo dec) {}
	public void recolecta_procs(Dec_proc dec) {}
}
