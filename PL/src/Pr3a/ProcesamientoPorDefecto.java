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

public class ProcesamientoPorDefecto implements Procesamiento {
	public void procesa(Suma exp) {}
	public void procesa(Resta exp) {}
	public void procesa(Mul exp) {}
	public void procesa(Div exp) {}
	public void procesa(Id exp) {}
	public void procesa(Num exp) {}
	public void procesa(Dec dec) {}
	public void procesa(Decs_muchas decs) {}
	public void procesa(Decs_una decs) {}
	public void procesa(Prog prog) {}
	public void procesa(Insts_una insts) {}
	public void procesa(Insts_muchas intss) {}
	public void procesa(Inst inst) {}
	public void procesa(Tipo tipo) {}
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
}
