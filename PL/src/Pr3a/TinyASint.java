package Pr3a;

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
			return 5;
		}
	}

	public static class True extends Exp {
		public True() {}
		
		public void procesa(Procesamiento p) {
			p.procesa(this);
		}

		@Override
		public int prioridad() {
			return 5;
		}
	}
	
	public static class False extends Exp {
		public False() {}
		
		public void procesa(Procesamiento p) {
			p.procesa(this);
		}

		@Override
		public int prioridad() {
			return 5;
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
			return 7;
		}

		@Override
		public void procesa(Procesamiento p) {
			p.procesa(this);
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
		public void procesa(Procesamiento p) {
			p.procesa(this);
		}
	}
	
	
	public static abstract class Tipo {
		public Tipo() {
		}

		public abstract void procesa(Procesamiento p);
	}

	public static class Tipo_int extends Tipo {
		public Tipo_int() {
			super();
		}

		public void procesa(Procesamiento p) {
			p.procesa(this);
		}
	}

	public static class Tipo_real extends Tipo {
		public Tipo_real() {
			super();
		}

		public void procesa(Procesamiento p) {
			p.procesa(this);
		}
	}

	public static class Tipo_bool extends Tipo {
		public Tipo_bool() {
			super();
		}

		public void procesa(Procesamiento p) {
			p.procesa(this);
		}
	}

	public static class Dec {
		private Tipo tipo;
		private StringLocalizado id;

		public Dec(Tipo tipo, StringLocalizado id) {
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
	
	public static class Inst {
		private StringLocalizado id;
		private Exp exp;
		
		public Inst(StringLocalizado id, Exp exp) {
			this.id = id;
			this.exp = exp;
		}
		
		public StringLocalizado id() {
			return id;
		}
		
		public Exp exp() {
			return exp;
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

	public static class Prog {
		private Decs decs;
		private Insts insts;

		public Prog(Decs decs, Insts insts) {
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

	// Constructoras
	public Prog cprog(Decs decs, Insts insts) {
		return new Prog(decs, insts);
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
	
	public Exp cnot(Exp arg) {
		return new Not(arg);
	}
	
	public Exp cmenos(Exp arg) {
		return new Menos(arg);
	}
	
	public Exp cid(StringLocalizado num) {
		return new Id(num);
	}
	
	public Exp ctrue_bool() {
		return new True();
	}
	
	public Exp cfalse_bool() {
		return new False();
	}
	
	public Tipo ctipo_int() {
		return new Tipo_int();
	}

	public Tipo ctipo_real() {
		return new Tipo_real();
	}

	public Tipo ctipo_bool() {
		return new Tipo_bool();
	}
	
	public Dec cdec(Tipo tipo, StringLocalizado id) {
		return new Dec(tipo, id);
	}

	public Decs cdecs_una(Dec dec) {
		return new Decs_una(dec);
	}

	public Decs cdecs_muchas(Decs decs, Dec dec) {
		return new Decs_muchas(decs, dec);
	}
	
	public Inst cinst(StringLocalizado id, Exp exp) {
		return new Inst(id, exp);
	}
	
	public Insts cinsts_una(Inst inst) {
		return new Insts_una(inst);
	}
	
	public Insts cinsts_muchas(Insts insts, Inst inst) {
		return new Insts_muchas(insts, inst);
	}

	public StringLocalizado str(String s, int fila, int col) {
		return new StringLocalizado(s, fila, col);
	}
	
	// --- NEW --
	public Exp cnum_int(StringLocalizado s) {
		return new Num_int(s);
	}	
	
	public Exp cnum_real(StringLocalizado s) {
		return new Num_real(s);
	}
	
}
