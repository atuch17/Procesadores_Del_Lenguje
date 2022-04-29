package Pr3a;

import Pr3a.TinyASint.*;

public class ProcesamientoPorDefecto implements Procesamiento {
	public void procesa(Suma exp) {}
	public void procesa(Resta exp) {}
	public void procesa(Mul exp) {}
	public void procesa(Div exp) {}
	public void procesa(Id exp) {}
	public void procesa(Dec dec) {}
	public void procesa(Decs_muchas decs) {}
	public void procesa(Decs_una decs) {}
	public void procesa(Prog prog) {}
	public void procesa(Insts_una insts) {}
	public void procesa(Insts_muchas intss) {}
	public void procesa(Inst inst) {}
	public void procesa(True exp) {}
	public void procesa(False exp) {}
	public void procesa(And exp) {}
	public void procesa(Or exp) {}
	public void procesa(Mayor exp) {}
	public void procesa(Menor exp) {}
	public void procesa(Mayor_eq exp) {}
	public void procesa(Menor_eq exp) {}
	public void procesa(Comp exp) {}
	public void procesa(Dist exp) {}
	public void procesa(Menos exp) {}
	public void procesa(Not exp) {}	
	public void procesa(Tipo_int tipo) {}
	public void procesa(Tipo_real tipo) {}
	public void procesa(Tipo_bool tipo) {}
	public void procesa(Num_int exp) {}
	public void procesa(Num_real exp) {}
}
