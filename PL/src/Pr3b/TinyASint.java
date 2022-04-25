package Pr3b;

public class TinyASint {

	public static abstract class Exp {
		public Exp() {
		}

		public abstract int prioridad();

		public abstract void procesa(Procesamiento procesamiento);
	}

	public static class StringLocalizado {
		private String s;
		private int fila;
		private int col;

		public StringLocalizado(String s, int fila, int col) {
			this.s = s;
			this.fila = fila;
			this.col = col;
		}

		public int fila() {
			return fila;
		}

		public int col() {
			return col;
		}

		public String toString() {
			return s;
		}

		public boolean equals(Object o) {
			return (o == this) || ((o instanceof StringLocalizado) && (((StringLocalizado) o).s.equals(s)));
		}

		public int hashCode() {
			return s.hashCode();
		}
	}

	private static abstract class ExpBin extends Exp {
		private Exp arg0;
		private Exp arg1;

		public Exp arg0() {
			return arg0;
		}

		public Exp arg1() {
			return arg1;
		}

		public ExpBin(Exp arg0, Exp arg1) {
			super();
			this.arg0 = arg0;
			this.arg1 = arg1;
		}
	}

	private static abstract class ExpBin2 extends Exp { // TODO revisar
		private Exp arg0;
		private Exp arg1;
		private StringLocalizado id;

		public Exp arg0() {
			return arg0;
		}

		public Exp arg1() {
			return arg1;
		}

		public StringLocalizado id() {
			return id;
		}

		public ExpBin2(Exp arg0, StringLocalizado id) {
			super();
			this.arg0 = arg0;
			this.id = id;
		}

		public ExpBin2(Exp arg0, Exp arg1) {
			super();
			this.arg0 = arg0;
			this.arg1 = arg1;
		}
	}

	/* ---0--- */
	private static abstract class ExpAditiva extends ExpBin {
		public ExpAditiva(Exp arg0, Exp arg1) {
			super(arg0, arg1);
		}

		public final int prioridad() {
			return 0;
		}
	}

	public static class Suma extends ExpAditiva {
		public Suma(Exp arg0, Exp arg1) {
			super(arg0, arg1);
		}

		public void procesa(Procesamiento p) {
			p.procesa(this);
		}
	}

	public static class Resta extends ExpAditiva {
		public Resta(Exp arg0, Exp arg1) {
			super(arg0, arg1);
		}

		public void procesa(Procesamiento p) {
			p.procesa(this);
		}
	}

	/* ---1--- */
	private static abstract class ExpLogica extends ExpBin {
		public ExpLogica(Exp arg0, Exp arg1) {
			super(arg0, arg1);
		}

		public final int prioridad() {
			return 1;
		}
	}

	public static class And extends ExpLogica {

		public And(Exp arg0, Exp arg1) {
			super(arg0, arg1);
		}

		public void procesa(Procesamiento p) {
			p.procesa(this);
		}
	}

	public static class Or extends ExpLogica {
		public Or(Exp arg0, Exp arg1) {
			super(arg0, arg1);
		}

		public void procesa(Procesamiento p) {
			p.procesa(this);
		}
	}

	/* ---2--- */
	private static abstract class ExpRelacional extends ExpBin {
		public ExpRelacional(Exp arg0, Exp arg1) {
			super(arg0, arg1);
		}

		public final int prioridad() {
			return 2;
		}
	}

	public static class Mayor extends ExpRelacional {
		public Mayor(Exp arg0, Exp arg1) {
			super(arg0, arg1);
		}

		public void procesa(Procesamiento p) {
			p.procesa(this);
		}
	}

	public static class Menor extends ExpRelacional {
		public Menor(Exp arg0, Exp arg1) {
			super(arg0, arg1);
		}

		public void procesa(Procesamiento p) {
			p.procesa(this);
		}
	}

	public static class Mayor_eq extends ExpRelacional {
		public Mayor_eq(Exp arg0, Exp arg1) {
			super(arg0, arg1);
		}

		public void procesa(Procesamiento p) {
			p.procesa(this);
		}
	}

	public static class Menor_eq extends ExpRelacional {
		public Menor_eq(Exp arg0, Exp arg1) {
			super(arg0, arg1);
		}

		public void procesa(Procesamiento p) {
			p.procesa(this);
		}
	}

	public static class Comp extends ExpRelacional {
		public Comp(Exp arg0, Exp arg1) {
			super(arg0, arg1);
		}

		public void procesa(Procesamiento p) {
			p.procesa(this);
		}
	}

	public static class Dist extends ExpRelacional {
		public Dist(Exp arg0, Exp arg1) {
			super(arg0, arg1);
		}

		public void procesa(Procesamiento p) {
			p.procesa(this);
		}
	}

	/* ---3--- */
	private static abstract class ExpMultiplicativa extends ExpBin {
		public ExpMultiplicativa(Exp arg0, Exp arg1) {
			super(arg0, arg1);
		}

		public final int prioridad() {
			return 3;
		}
	}

	public static class Mul extends ExpMultiplicativa {

		public Mul(Exp arg0, Exp arg1) {
			super(arg0, arg1);
		}

		public void procesa(Procesamiento p) {
			p.procesa(this);
		}
	}

	public static class Div extends ExpMultiplicativa {
		public Div(Exp arg0, Exp arg1) {
			super(arg0, arg1);
		}

		public void procesa(Procesamiento p) {
			p.procesa(this);
		}
	}

	public static class Mod extends ExpMultiplicativa {
		public Mod(Exp arg0, Exp arg1) {
			super(arg0, arg1);
		}

		public void procesa(Procesamiento p) {
			p.procesa(this);
		}
	}

	/* ---4--- */
	private static abstract class ExpUnaria extends Exp {
		private Exp arg;

		public Exp arg() {
			return arg;
		}

		public ExpUnaria(Exp arg) {
			super();
			this.arg = arg;
		}

		public final int prioridad() {
			return 4;
		}
	}

	public static class Menos extends ExpUnaria {
		public Menos(Exp arg) {
			super(arg);
		}

		public void procesa(Procesamiento p) {
			p.procesa(this);
		}
	}

	public static class Not extends ExpUnaria {
		public Not(Exp arg) {
			super(arg);
		}

		public void procesa(Procesamiento p) {
			p.procesa(this);
		}
	}

	/* ---5--- */
	private static abstract class ExpOperadores extends ExpBin2 {
		public ExpOperadores(Exp arg0, StringLocalizado id) {
			super(arg0, id);
		}

		public ExpOperadores(Exp arg0, Exp arg1) {
			super(arg0, arg1);
		}

		public final int prioridad() {
			return 5;
		}
	}

	public static class Corch extends ExpOperadores {
		public Corch(Exp arg0, Exp arg1) {
			super(arg0, arg1);
		}

		public void procesa(Procesamiento p) {
			p.procesa(this);
		}
	}

	public static class Punto extends ExpOperadores {
		public Punto(Exp arg0, StringLocalizado id) {
			super(arg0, id);
		}

		public void procesa(Procesamiento p) {
			p.procesa(this);
		}
	}

	public static class Flecha extends ExpOperadores {
		public Flecha(Exp arg0, StringLocalizado id) {
			super(arg0, id);
		}

		public void procesa(Procesamiento p) {
			p.procesa(this);
		}
	}

	/* ---6--- */
	private static abstract class ExpAsterix extends Exp {
		private Exp arg;

		public ExpAsterix(Exp arg) {
			super();
			this.arg = arg;
		}

		public Exp arg() {
			return arg;
		}

		public final int prioridad() {
			return 6;
		}
	}

	public static class Asterix extends ExpAsterix {
		public Asterix(Exp arg0) {
			super(arg0);
		}

		public void procesa(Procesamiento p) {
			p.procesa(this);
		}
	}

	/* ---CLASES--- */
	public static abstract class Prog {
		public Prog() {
		}

		public abstract void procesa(Procesamiento p);
	}

	public static class Prog_con_decs extends Prog {
		private Decs decs;
		private Insts insts;

		public Prog_con_decs(Decs decs, Insts insts) {
			super();
			this.decs = decs;
			this.insts = insts;
		}

		public Decs decs() {
			return decs;
		}

		public Insts insts() {
			return insts;
		}

		public void procesa(Procesamiento p) {
			p.procesa(this);
		}
	}

	public static class Prog_sin_decs extends Prog {
		private Insts insts;

		public Prog_sin_decs(Insts insts) {
			super();
			this.insts = insts;
		}

		public Insts insts() {
			return insts;
		}

		public void procesa(Procesamiento p) {
			p.procesa(this);
		}
	}

	public static abstract class Decs {
		public Decs() {
		}

		public abstract void procesa(Procesamiento p);
	}

	public static class Decs_una extends Decs {
		private Dec dec;

		public Decs_una(Dec dec) {
			super();
			this.dec = dec;
		}

		public Dec dec() {
			return dec;
		}

		public void procesa(Procesamiento p) {
			p.procesa(this);
		}
	}

	public static class Decs_muchas extends Decs {
		private Dec dec;
		private Decs decs;

		public Decs_muchas(Decs decs, Dec dec) {
			super();
			this.dec = dec;
			this.decs = decs;
		}

		public Dec dec() {
			return dec;
		}

		public Decs decs() {
			return decs;
		}

		public void procesa(Procesamiento p) {
			p.procesa(this);
		}
	}

	public static class Dec {
		public Dec() {
		}

		public void procesa(Procesamiento p) {
		}
	}

	public static class Dec_var extends Dec {
		private Tipo tipo;
		private StringLocalizado id;

		public Dec_var(Tipo tipo, StringLocalizado id) {
			this.tipo = tipo;
			this.id = id;
		}

		public Tipo tipo() {
			return tipo;
		}

		public StringLocalizado id() {
			return id;
		}

		public void procesa(Procesamiento p) {
			p.procesa(this);
		}
	}

	public static class Dec_tipo extends Dec {
		private Tipo tipo;
		private StringLocalizado id;

		public Dec_tipo(Tipo tipo, StringLocalizado id) {
			this.tipo = tipo;
			this.id = id;
		}

		public Tipo tipo() {
			return tipo;
		}

		public StringLocalizado id() {
			return id;
		}

		public void procesa(Procesamiento p) {
			p.procesa(this);
		}
	}

	public static class Dec_proc extends Dec {
		private StringLocalizado id;
		private ParamsF params;
		private Bloque bloque;

		public Dec_proc(StringLocalizado id, ParamsF params, Bloque bloque) {
			this.id = id;
			this.params = params;
			this.bloque = bloque;
		}

		public StringLocalizado id() {
			return id;
		}

		public ParamsF params() {
			return params;
		}

		public Bloque Bloque() {
			return bloque;
		}

		public void procesa(Procesamiento p) {
			p.procesa(this);
		}
	}

	public static abstract class ParamsF {

		public ParamsF() {
		}

		public void procesa(Procesamiento p) {
		}
	}

	public static class Params_uno_f extends ParamsF {
		private ParamF param;

		public Params_uno_f(ParamF param) {
			this.param = param;
		}

		public ParamF param() {
			return param;
		}

		public void procesa(Procesamiento p) {
			p.procesa(this);
		}
	}

	public static class Params_muchos_f extends ParamsF {
		private ParamsF ps;
		private ParamF s;

		public Params_muchos_f(ParamsF ps, ParamF s) {
			this.ps = ps;
			this.s = s;
		}

		public ParamsF ps() {
			return ps;
		}

		public ParamF s() {
			return s;
		}

		public void procesa(Procesamiento p) {
			p.procesa(this);
		}
	}

	public static abstract class ParamF {
		public ParamF() {
		}

		public void procesa(Procesamiento p) {
		}
	}

	public static class Param_f_sin_amp extends ParamF {
		private Tipo tipo;
		private StringLocalizado id;

		public Param_f_sin_amp(Tipo tipo, StringLocalizado id) {
			this.tipo = tipo;
			this.id = id;
		}

		public Tipo tipo() {
			return tipo;
		}

		public StringLocalizado id() {
			return id;
		}

		public void procesa(Procesamiento p) {
			p.procesa(this);
		}
	}

	public static class Param_f_con_amp extends ParamF {
		private Tipo tipo;
		private StringLocalizado id;

		public Param_f_con_amp(Tipo tipo, StringLocalizado id) {
			this.tipo = tipo;
			this.id = id;
		}

		public Tipo tipo() {
			return tipo;
		}

		public StringLocalizado id() {
			return id;
		}

		public void procesa(Procesamiento p) {
			p.procesa(this);
		}
	}

	public static abstract class Insts {
		public Insts() {
		}

		public abstract void procesa(Procesamiento p);
	}

	public static class Insts_una extends Insts {
		private Inst inst;

		public Insts_una(Inst inst) {
			super();
			this.inst = inst;
		}

		public Inst inst() {
			return inst;
		}

		public void procesa(Procesamiento p) {
			p.procesa(this);
		}
	}

	public static class Insts_muchas extends Insts {
		private Inst inst;
		private Insts insts;

		public Insts_muchas(Insts insts, Inst inst) {
			super();
			this.inst = inst;
			this.insts = insts;
		}

		public Inst inst() {
			return inst;
		}

		public Insts insts() {
			return insts;
		}

		public void procesa(Procesamiento p) {
			p.procesa(this);
		}
	}

	public static abstract class Inst {
		public Inst() {
		}

		public abstract void procesa(Procesamiento p);
	}

	public static class Inst_asig extends Inst {
		private Exp exp1;
		private Exp exp2;

		public Inst_asig(Exp exp1, Exp exp2) {
			super();
			this.exp1 = exp1;
			this.exp2 = exp2;
		}

		public Exp exp1() {
			return exp1;
		}

		public Exp exp2() {
			return exp2;
		}

		public void procesa(Procesamiento p) {
			p.procesa(this);
		}
	}

	public static abstract class InstsOpc {
		public InstsOpc() {
		}

		public abstract void procesa(Procesamiento p);
	}

	public static class Insts_opc_sin_insts extends InstsOpc {

		public Insts_opc_sin_insts() {
			super();
		}

		public void procesa(Procesamiento p) {
			p.procesa(this);
		}
	}

	public static class Insts_opc_con_insts extends InstsOpc {
		private Insts insts;

		public Insts_opc_con_insts(Insts insts) {
			super();
			this.insts = insts;
		}

		public Insts insts() {
			return insts;
		}

		public void procesa(Procesamiento p) {
			p.procesa(this);
		}
	}

	public static class Inst_ifthen extends Inst {
		private InstsOpc insts;
		private Exp exp1;

		public Inst_ifthen(Exp exp, InstsOpc insts) {
			super();
			this.exp1 = exp;
			this.insts = insts;
		}

		public InstsOpc inst() {
			return insts;
		}

		public Exp exp1() {
			return exp1;
		}

		public void procesa(Procesamiento p) {
			p.procesa(this);
		}
	}

	public static class Inst_ifthenelse extends Inst {
		private InstsOpc bloque1;
		private InstsOpc bloque2;
		private Exp exp1;

		public Inst_ifthenelse(Exp exp, InstsOpc bloque1, InstsOpc bloque2) {
			super();
			this.exp1 = exp;
			this.bloque1 = bloque1;
			this.bloque2 = bloque2;
		}

		public InstsOpc bloque1() {
			return bloque1;
		}

		public InstsOpc bloque2() {
			return bloque2;
		}

		public Exp exp1() {
			return exp1;
		}

		public void procesa(Procesamiento p) {
			p.procesa(this);
		}
	}

	public static class Inst_while extends Inst {
		private InstsOpc insts;
		private Exp exp1;

		public Inst_while(Exp exp, InstsOpc insts) {
			super();
			this.exp1 = exp;
			this.insts = insts;
		}

		public InstsOpc inst() {
			return insts;
		}

		public Exp exp1() {
			return exp1;
		}

		public void procesa(Procesamiento p) {
			p.procesa(this);
		}
	}

	public static class Inst_lectura extends Inst {
		private Exp exp1;

		public Inst_lectura(Exp exp1) {
			super();
			this.exp1 = exp1;
		}

		public Exp exp1() {
			return exp1;
		}

		public void procesa(Procesamiento p) {
			p.procesa(this);
		}
	}

	public static class Inst_escritura extends Inst {
		private Exp exp1;

		public Inst_escritura(Exp exp1) {
			super();
			this.exp1 = exp1;
		}

		public Exp exp1() {
			return exp1;
		}

		public void procesa(Procesamiento p) {
			p.procesa(this);
		}
	}

	public static class Inst_new_line extends Inst { // TODO revisar

		public Inst_new_line() {
			super();
		}

		public void procesa(Procesamiento p) {
			p.procesa(this);
		}
	}

	public static class Inst_reserv_mem extends Inst {
		private Exp exp1;

		public Inst_reserv_mem(Exp exp1) {
			super();
			this.exp1 = exp1;
		}

		public Exp exp1() {
			return exp1;
		}

		public void procesa(Procesamiento p) {
			p.procesa(this);
		}
	}

	public static class Inst_lib_mem extends Inst {
		private Exp exp1;

		public Inst_lib_mem(Exp exp1) {
			super();
			this.exp1 = exp1;
		}

		public Exp exp1() {
			return exp1;
		}

		public void procesa(Procesamiento p) {
			p.procesa(this);
		}
	}

	public static class Inst_invoc_proc extends Inst {
		private StringLocalizado id;
		private ParamsR params;

		public Inst_invoc_proc(StringLocalizado id, ParamsR params) {
			super();
			this.id = id;
			this.params = params;
		}

		public StringLocalizado id() {
			return id;
		}

		public ParamsR params() {
			return params;
		}

		public void procesa(Procesamiento p) {
			p.procesa(this);
		}
	}

	public static class Inst_comp extends Inst {
		private Bloque b;

		public Inst_comp(Bloque b) {
			super();
			this.b = b;
		}

		public Bloque b() {
			return b;
		}

		public void procesa(Procesamiento p) {
			p.procesa(this);
		}
	}

	public static abstract class ParamsR {
		public ParamsR() {
		}

		public abstract void procesa(Procesamiento p);
	}

	public static class Params_vacio extends ParamsR {
		public Params_vacio() {
			super();
		}

		public void procesa(Procesamiento p) {
			p.procesa(this);
		}
	}

	public static class Params_lleno extends ParamsR {
		private Expresiones e;

		public Params_lleno(Expresiones e) {
			super();
			this.e = e;
		}

		public Expresiones expresiones() {
			return e;
		}

		public void procesa(Procesamiento p) {
			p.procesa(this);
		}
	}

	public static abstract class Expresiones {
		public Expresiones() {
		}

		public abstract void procesa(Procesamiento p);
	}

	public static class Exprs_una extends Expresiones {
		private Exp e;

		public Exprs_una(Exp e) {
			super();
			this.e = e;
		}

		public Exp e() {
			return e;
		}

		public void procesa(Procesamiento p) {
			p.procesa(this);
		}
	}

	public static class Exprs_muchas extends Expresiones {
		private Exp exp;
		private Expresiones e;

		public Exprs_muchas(Expresiones e, Exp exp) {
			super();
			this.e = e;
			this.exp = exp;
		}

		public Exp exp() {
			return exp;
		}

		public Expresiones expresiones() {
			return e;
		}

		public void procesa(Procesamiento p) {
			p.procesa(this);
		}
	}

	public static abstract class Tipo {
		public Tipo() {
		}

		public abstract void procesa(Procesamiento p);
	}

	public static class Tipo_basico extends Tipo {
		private StringLocalizado tipo;

		public Tipo_basico(StringLocalizado tipo) {
			super();
			this.tipo = tipo;
		}

		public StringLocalizado tipo() {
			return tipo;
		}

		public void procesa(Procesamiento p) {
			p.procesa(this);
		}
	}

	public static class Tipo_id extends Tipo {
		private StringLocalizado id;

		public Tipo_id(StringLocalizado id) {
			super();
			this.id = id;
		}

		public StringLocalizado id() {
			return id;
		}

		public void procesa(Procesamiento p) {
			p.procesa(this);
		}
	}

	public static class Tipo_array extends Tipo {
		private StringLocalizado valor;
		private Tipo tipo;

		public Tipo_array(StringLocalizado valor, Tipo tipo) {
			super();
			this.valor = valor;
			this.tipo = tipo;
		}

		public StringLocalizado valor() {
			return valor;
		}

		public Tipo tipo() {
			return tipo;
		}

		public void procesa(Procesamiento p) {
			p.procesa(this);
		}
	}

	public static class Tipo_registro extends Tipo {
		private Campos campos;

		public Tipo_registro(Campos campos) {
			super();
			this.campos = campos;
		}

		public Campos campos() {
			return campos;
		}

		public void procesa(Procesamiento p) {
			p.procesa(this);
		}
	}

	public static class Tipo_puntero extends Tipo {
		private Tipo tipo;

		public Tipo_puntero(Tipo tipo) {
			super();
			this.tipo = tipo;
		}

		public Tipo tipo() {
			return tipo;
		}

		public void procesa(Procesamiento p) {
			p.procesa(this);
		}
	}

	public static abstract class Campos {
		public Campos() {
		}

		public abstract void procesa(Procesamiento p);
	}

	public static class Campos_uno extends Campos {
		private Campo campo;

		public Campos_uno(Campo campo) {
			super();
			this.campo = campo;
		}

		public Campo campo() {
			return campo;
		}

		public void procesa(Procesamiento p) {
			p.procesa(this);
		}
	}

	public static class Campos_muchos extends Campos {
		private Campo campo;
		private Campos campos;

		public Campos_muchos(Campos campos, Campo campo) {
			super();
			this.campos = campos;
			this.campo = campo;
		}

		public Campo campo() {
			return campo;
		}

		public Campos campos() {
			return campos;
		}

		public void procesa(Procesamiento p) {
			p.procesa(this);
		}
	}

	public static class Campo {
		private Tipo tipo;
		private StringLocalizado id;

		public Campo(Tipo tipo, StringLocalizado id) {
			super();
			this.tipo = tipo;
			this.id = id;
		}

		public Tipo tipo() {
			return tipo;
		}

		public StringLocalizado id() {
			return id;
		}

		public void procesa(Procesamiento p) {
			p.procesa(this);
		}
	}

	public static abstract class Bloque {
		public Bloque() {
		}

		public abstract void procesa(Procesamiento p);
	}

	public static class Bloque_vacio extends Bloque {
		public Bloque_vacio() {
			super();
		}

		public void procesa(Procesamiento p) {
			p.procesa(this);
		}
	}

	public static class Bloque_lleno extends Bloque {
		private Prog prog;

		public Bloque_lleno(Prog prog) {
			super();
			this.prog = prog;
		}

		public Prog prog() {
			return prog;
		}

		public void procesa(Procesamiento p) {
			p.procesa(this);
		}
	}
	
	public static class ProgramaAux extends Bloque {
		private Prog prog;
		
		public ProgramaAux(Prog prog) {
			super();
			this.prog = prog;
		}
		
		public Prog prog() {
			return prog;
		}
		
		public void procesa(Procesamiento p) {
			p.procesa(this);
		}
	}
	
	

	/*public static class Num extends Exp {
		private StringLocalizado num;

		public Num(StringLocalizado num) {
			super();
			this.num = num;
		}

		public StringLocalizado num() {
			return num;
		}

		public void procesa(Procesamiento p) {
			p.procesa(this);
		}

		public final int prioridad() {
			return 7;
		}
	}*/

	public static class Id extends Exp {
		private StringLocalizado id;

		public Id(StringLocalizado id) {
			super();
			this.id = id;
		}

		public StringLocalizado id() {
			return id;
		}

		public void procesa(Procesamiento p) {
			p.procesa(this);
		}

		public final int prioridad() {
			return 7;
		}
	}

	public static class True extends Exp {
		public True() {
		}

		public void procesa(Procesamiento p) {
			p.procesa(this);
		}

		@Override
		public int prioridad() {
			return 7;
		}
	}

	public static class False extends Exp {
		public False() {
		}

		public void procesa(Procesamiento p) {
			p.procesa(this);
		}

		@Override
		public int prioridad() {
			return 7;
		}
	}

	public static class Sstring extends Exp {
		private StringLocalizado string;

		public Sstring(StringLocalizado string) {
			super();
			this.string = string;
		}

		public StringLocalizado string() {
			return string;
		}

		public void procesa(Procesamiento p) {
			p.procesa(this);
		}

		public int prioridad() {
			return 7;
		}
	}

	public static class None extends Exp {
		public None() {
		}

		public void procesa(Procesamiento p) {
			p.procesa(this);
		}

		@Override
		public int prioridad() {
			return 7;
		}
	}

	public static class Num_int extends Exp {
		private StringLocalizado s;

		public Num_int(StringLocalizado s) {
			super();
			this.s = s;
		}

		public StringLocalizado s() {
			return s;
		}

		@Override
		public int prioridad() {
			// TODO Auto-generated method stub
			return 7;
		}

		@Override
		public void procesa(Procesamiento procesamiento) {
		}
	}

	public static class Num_real extends Exp {
		private StringLocalizado s;

		public Num_real(StringLocalizado s) {
			super();
			this.s = s;
		}

		public StringLocalizado s() {
			return s;
		}

		@Override
		public int prioridad() {
			return 7;
		}

		@Override
		public void procesa(Procesamiento procesamiento) {
		}
	}
	
	/* ---CONSTRUCTORAS--- */
	public Prog cprog_con_decs(Decs decs, Insts insts) {
		return new Prog_con_decs(decs, insts);
	}

	public Prog cprog_sin_decs(Insts insts) {
		return new Prog_sin_decs(insts);
	}

	public Exp csuma(Exp arg0, Exp arg1) {
		return new Suma(arg0, arg1);
	}

	public Exp cresta(Exp arg0, Exp arg1) {
		return new Resta(arg0, arg1);
	}

	public Exp cmul(Exp arg0, Exp arg1) {
		return new Mul(arg0, arg1);
	}

	public Exp cdiv(Exp arg0, Exp arg1) {
		return new Div(arg0, arg1);
	}

	public Exp cmod(Exp arg0, Exp arg1) {
		return new Mod(arg0, arg1);
	}

	public Exp cmayor(Exp arg0, Exp arg1) {
		return new Mayor(arg0, arg1);
	}

	public Exp cmenor(Exp arg0, Exp arg1) {
		return new Menor(arg0, arg1);
	}

	public Exp cmayor_eq(Exp arg0, Exp arg1) {
		return new Mayor_eq(arg0, arg1);
	}

	public Exp cmenor_eq(Exp arg0, Exp arg1) {
		return new Menor_eq(arg0, arg1);
	}

	public Exp ccomp(Exp arg0, Exp arg1) {
		return new Comp(arg0, arg1);
	}

	public Exp cdist(Exp arg0, Exp arg1) {
		return new Dist(arg0, arg1);
	}

	public Exp cand(Exp arg0, Exp arg1) {
		return new And(arg0, arg1);
	}

	public Exp cor(Exp arg0, Exp arg1) {
		return new Or(arg0, arg1);
	}

	public Exp ccorch(Exp arg0, Exp arg1) {
		return new Corch(arg0, arg1);
	}

	public Exp cnot(Exp arg) {
		return new Not(arg);
	}

	public Exp cmenos(Exp arg) {
		return new Menos(arg);
	}

	public Exp cpunto(Exp arg, StringLocalizado id) {
		return new Punto(arg, id);
	}

	public Exp cflecha(Exp arg, StringLocalizado id) {
		return new Flecha(arg, id);
	}

	public Exp casterix(Exp arg) {
		return new Asterix(arg);
	}

	/*public Exp cnum(StringLocalizado num) {
		return new Num(num);
	}*/

	public Id cid(StringLocalizado num) {
		return new Id(num);
	}

	public Exp ctrue_bool() {
		return new True();
	}

	public Exp cfalse_bool() {
		return new False();
	}

	/*public Tipo ctipo(StringLocalizado tipo) {
		return new Tipo(tipo);
	}*/

	public Decs cdecs_una(Dec dec) {
		return new Decs_una(dec);
	}

	public Decs cdecs_muchas(Decs decs, Dec dec) {
		return new Decs_muchas(decs, dec);
	}

	public Dec_var cdec_var(Tipo tipo, StringLocalizado id) {
		return new Dec_var(tipo, id);
	}

	public Dec_tipo cdec_tipo(Tipo tipo, StringLocalizado id) {
		return new Dec_tipo(tipo, id);
	}

	public Dec_proc cdec_proc(StringLocalizado id, ParamsF params, Bloque bloque) {
		return new Dec_proc(id, params, bloque);
	}

	public Insts cinsts_una(Inst inst) {
		return new Insts_una(inst);
	}

	public Insts cinsts_muchas(Insts insts, Inst inst) {
		return new Insts_muchas(insts, inst);
	}

	public ParamsF cparams_uno_f(ParamF param) {
		return new Params_uno_f(param);
	}

	public ParamsF cparams_muchos_f(ParamsF params, ParamF param) {
		return new Params_muchos_f(params, param);
	}

	public ParamF cparam_f_sin_amp(Tipo tipo, StringLocalizado id) {
		return new Param_f_sin_amp(tipo, id);
	}

	public ParamF cparam_f_con_amp(Tipo tipo, StringLocalizado id) {
		return new Param_f_con_amp(tipo, id);
	}

	public StringLocalizado str(String s, int fila, int col) {
		return new StringLocalizado(s, fila, col);
	}

	/* TERMINAL */
	public Inst cinst_asig(Exp exp1, Exp exp2) {
        return new Inst_asig(exp1, exp2);
    }

	public InstsOpc cinsts_opc_sin_insts() {
		return new Insts_opc_sin_insts();
	}

	public InstsOpc cinsts_opc_con_insts(Insts insts) {
		return new Insts_opc_con_insts(insts);
	}

	public Inst cinst_ifthen(Exp exp, InstsOpc insts) {
		return new Inst_ifthen(exp, insts);
	}

	public Inst cinst_ifthenelse(Exp exp, InstsOpc insts1, InstsOpc insts2) {
		return new Inst_ifthenelse(exp, insts1, insts2);
	}

	public Inst cinst_while(Exp exp, InstsOpc insts) {
		return new Inst_while(exp, insts);
	}

	public Inst cinst_lectura(Exp exp) {
		return new Inst_lectura(exp);
	}

	public Inst cinst_escritura(Exp exp) {
		return new Inst_escritura(exp);
	}

	public Inst cinst_new_line() {
		return new Inst_new_line();
	}

	public Inst cinst_reserv_mem(Exp exp) {
		return new Inst_reserv_mem(exp);
	}

	public Inst cinst_lib_mem(Exp exp) {
		return new Inst_lib_mem(exp);
	}

	public Inst cinst_invoc_proc(StringLocalizado id, ParamsR params) {
		return new Inst_invoc_proc(id, params);
	}

	public Inst cinst_comp(Bloque b) {
		return new Inst_comp(b);
	}

	public ParamsR cparams_vacio() {
		return new Params_vacio();
	}

	public ParamsR cparams_lleno(Expresiones e) {
		return new Params_lleno(e);
	}

	public Expresiones cexprs_una(Exp exp) {
		return new Exprs_una(exp);
	}

	public Expresiones cexprs_muchas(Expresiones e, Exp exp) {
		return new Exprs_muchas(e, exp);
	}

	public Tipo ctipo_basico(StringLocalizado tipo) {
		return new Tipo_basico(tipo);
	}

	public Tipo ctipo_id(StringLocalizado id) {
		return new Tipo_id(id);
	}

	public Exp cstring(StringLocalizado s) {
		return new Sstring(s);
	}

	public Exp cnone() { // Null
		return new None();
	}

	public Tipo ctipo_array(StringLocalizado s, Tipo t) {
		return new Tipo_array(s, t);
	}

	public Tipo ctipo_registro(Campos c) {
		return new Tipo_registro(c);
	}

	public Campos ccampos_uno(Campo c) {
		return new Campos_uno(c);
	}

	public Campos ccampos_muchos(Campos cs, Campo c) {
		return new Campos_muchos(cs, c);
	}

	public Campo ccampo(Tipo t, StringLocalizado id) {
		return new Campo(t, id);
	}

	public Tipo ctipo_puntero(Tipo t) {
		return new Tipo_puntero(t);
	}

	public Bloque cbloque_vacio() {
		return new Bloque_vacio();
	}

	public Bloque cbloque_lleno(Prog p) {
		return new Bloque_lleno(p);
	}

	public Exp cnum_int(StringLocalizado s) {
		return new Num_int(s);
	}	
	
	public Exp cnum_real(StringLocalizado s) {
		return new Num_real(s);
	}
	
	public Exp cverdad(StringLocalizado s) {
		return new Num_int(s);
	}	
	
	public Exp cfalso(StringLocalizado s) {
		return new Num_real(s);
	}

}
