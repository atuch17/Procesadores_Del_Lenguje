package Pr3a;

import java.io.IOException;
import java.io.Reader;

import Pr3a.TinyASint.*;

public class ConstructorAST {
	private UnidadLexica anticipo;
	private AnalizadorLexicoTiny alex;
	private GestionErroresTiny errores;
	private SemOps sem;

	public ConstructorAST(Reader input) throws IOException {
		errores = new GestionErroresTiny();
		alex = new AnalizadorLexicoTiny(input);
		alex.fijaGestionErrores(errores);
		sigToken();
		sem = new SemOps();
	}

	public Prog Init() {
		Prog prog = Programa();
		empareja(ClaseLexica.END);
		return prog;
	}

	private Prog Programa() {
		switch (anticipo.clase()) {
		case INT:
		case REAL:
		case BOOL:
			Decs decs = Declaraciones();
			empareja(ClaseLexica.SEP_SEC);
			Insts insts =Instrucciones();
			return sem.prog(decs, insts);
		default:
			errores.errorSintactico(anticipo.fila(), anticipo.columna(), anticipo.clase(), ClaseLexica.INT,
					ClaseLexica.REAL, ClaseLexica.BOOL);
			return null;
		}
	}

	private Decs Declaraciones() {
		switch (anticipo.clase()) {
		case INT:
		case REAL:
		case BOOL:
			Dec dec = Declaracion();
			return RD(sem.decs_una(dec));
		default:
			errores.errorSintactico(anticipo.fila(), anticipo.columna(), anticipo.clase(), ClaseLexica.INT,
					ClaseLexica.REAL, ClaseLexica.BOOL);
			return null;
		}
	}

	private Decs RD(Decs decsh) {
		switch (anticipo.clase()) {
		case SEP_INS:
			empareja(ClaseLexica.SEP_INS);
			Dec dec = Declaracion();
			return RD(sem.decs_muchas(decsh, dec));
		case SEP_SEC:
			return decsh;
		default:
			errores.errorSintactico(anticipo.fila(), anticipo.columna(), anticipo.clase(), ClaseLexica.SEP_INS,
					ClaseLexica.SEP_SEC);
			return null;
		}
	}

	private Dec Declaracion() {
		switch (anticipo.clase()) {
		case INT:
		case REAL:
		case BOOL:
			Tipo tipo = Tipo();
			UnidadLexica id = anticipo;
			empareja(ClaseLexica.ID);
			return sem.dec(tipo, sem.str(id.lexema(), id.fila(), id.columna()));
		default:
			errores.errorSintactico(anticipo.fila(), anticipo.columna(), anticipo.clase(), ClaseLexica.INT,
					ClaseLexica.REAL, ClaseLexica.BOOL);
			return null;
		}
	}

	private Tipo Tipo() {
		switch (anticipo.clase()) {
		case INT:
			empareja(ClaseLexica.INT);
			return sem.ctipo_int();
		case REAL:
			empareja(ClaseLexica.REAL);
			return sem.ctipo_real( );
		case BOOL:
			empareja(ClaseLexica.BOOL);
			return sem.ctipo_bool();
		default:
			errores.errorSintactico(anticipo.fila(), anticipo.columna(), anticipo.clase(), ClaseLexica.INT,
					ClaseLexica.REAL, ClaseLexica.BOOL);
			return null;
		}
	}

	private Insts Instrucciones() {
		switch (anticipo.clase()) {
		case ID:
			Inst inst = Instruccion();
			return RI(sem.insts_una(inst));
		default:
			errores.errorSintactico(anticipo.fila(), anticipo.columna(), anticipo.clase(), ClaseLexica.ID);
			return null;
		}
	}

	private Insts RI(Insts instsh) {
		switch (anticipo.clase()) {
		case SEP_INS:
			empareja(ClaseLexica.SEP_INS);
			Inst inst = Instruccion();
			return RI(sem.insts_muchas(instsh, inst));
		case END:
			return instsh;
		default:
			errores.errorSintactico(anticipo.fila(), anticipo.columna(), anticipo.clase(), ClaseLexica.SEP_INS,
					ClaseLexica.END);
			return null;
		}
	}

	private Inst Instruccion() {
		switch (anticipo.clase()) {
		case ID:
			UnidadLexica id = anticipo;
			empareja(ClaseLexica.ID);
			empareja(ClaseLexica.OP_ASIG);
			Exp exp = E0();
			return sem.inst(sem.str(id.lexema(), id.fila(), id.columna()), exp);
		default:
			errores.errorSintactico(anticipo.fila(), anticipo.columna(), anticipo.clase(), ClaseLexica.ID);
			return null;
		}
	}

	private Exp E0() {
		switch (anticipo.clase()) {
		case PAP:
		case OP_RESTA:
		case ID:
		case NUM_INT:
		case NUM_REAL:
		case TRUE:
		case FALSE:
		case NOT:
			Exp exp = E1();
			return RE0(exp);
		default:
			errores.errorSintactico(anticipo.fila(), anticipo.columna(), anticipo.clase(), ClaseLexica.PAP,
					ClaseLexica.OP_RESTA, ClaseLexica.ID, ClaseLexica.NUM_INT, ClaseLexica.NUM_REAL,
					ClaseLexica.TRUE, ClaseLexica.FALSE, ClaseLexica.NOT);
			return null;
		}
	}
			
	private Exp RE0(Exp exph) {
		switch (anticipo.clase()) {
		case OP_SUMA:
			empareja(ClaseLexica.OP_SUMA);
			Exp exp1 = E1();
			return RE0(sem.exp("+", exph, exp1));
		case OP_RESTA:
			empareja(ClaseLexica.OP_RESTA);
			Exp exp2 = E1();
			return RE0(sem.exp("-", exph, exp2));
		case PCIERRE:
		case SEP_INS:
		case END:
			return exph;
		default:
			errores.errorSintactico(anticipo.fila(), anticipo.columna(), anticipo.clase(), ClaseLexica.OP_SUMA,
					ClaseLexica.OP_RESTA);
			return null;
		}
	}
	
	public Exp E1() {
		switch (anticipo.clase()) {
		case PAP:
		case OP_RESTA:
		case ID:
		case NUM_INT:
		case NUM_REAL:
		case TRUE:
		case FALSE:
		case NOT:
			Exp exp = E2();
			return RE1(exp);
		default:
			errores.errorSintactico(anticipo.fila(), anticipo.columna(), anticipo.clase(), ClaseLexica.PAP,
					ClaseLexica.OP_RESTA, ClaseLexica.ID, ClaseLexica.NUM_INT, ClaseLexica.NUM_REAL, ClaseLexica.TRUE,
					ClaseLexica.FALSE, ClaseLexica.NOT);
			return null;
		}
	}

	public Exp RE1(Exp exph) {
		switch (anticipo.clase()) {
		case AND:
		case OR:
			String op = op1();
			Exp exp = E2();
			return RE1(sem.exp(op, exph, exp));
		case PCIERRE:
		case OP_SUMA:
		case OP_RESTA:
		case SEP_INS:
		case END:
			return exph;
		default:
			errores.errorSintactico(anticipo.fila(), anticipo.columna(), anticipo.clase(), ClaseLexica.AND,
					ClaseLexica.OR, ClaseLexica.OP_SUMA, ClaseLexica.OP_RESTA);
			return null;
		}
	}

	public Exp E2() {
		switch (anticipo.clase()) {
		case PAP:
		case OP_RESTA:
		case ID:
		case NUM_INT:
		case NUM_REAL:
		case TRUE:
		case FALSE:
		case NOT:
			Exp exp = E3();
			return RE2(exp);
		default:
			errores.errorSintactico(anticipo.fila(), anticipo.columna(), anticipo.clase(), ClaseLexica.PAP,
					ClaseLexica.OP_RESTA, ClaseLexica.ID, ClaseLexica.NUM_INT, ClaseLexica.NUM_REAL, ClaseLexica.TRUE,
					ClaseLexica.FALSE, ClaseLexica.NOT);
			return null;
		}
	}

	public Exp RE2(Exp exph) {
		switch (anticipo.clase()) {
		case OP_MENOR:
		case OP_MAYOR:
		case OP_MEN_IG:
		case OP_MAY_IG:
		case OP_COMP:
		case OP_DIST:
			String op = op2();
			Exp exp = E3();
			return RE2(sem.exp(op, exph, exp));
		case PCIERRE:
		case OP_SUMA:
		case OP_RESTA:
		case SEP_INS:
		case AND:
		case OR:
		case END:
			return exph;
		default:
			errores.errorSintactico(anticipo.fila(), anticipo.columna(), anticipo.clase(), ClaseLexica.OP_MENOR,
					ClaseLexica.OP_MAYOR, ClaseLexica.OP_MEN_IG, ClaseLexica.OP_MAY_IG, ClaseLexica.OP_COMP,
					ClaseLexica.OP_DIST, ClaseLexica.OP_SUMA, ClaseLexica.OP_RESTA, ClaseLexica.AND, ClaseLexica.OR);
			return null;
		}
	}

	public Exp E3() {
		switch (anticipo.clase()) {
		case PAP:
		case OP_RESTA:
		case ID:
		case NUM_INT:
		case NUM_REAL:
		case TRUE:
		case FALSE:
		case NOT:
			Exp exp = E4();
			return RE3(exp);
		default:
			errores.errorSintactico(anticipo.fila(), anticipo.columna(), anticipo.clase(), ClaseLexica.PAP,
					ClaseLexica.OP_RESTA, ClaseLexica.ID, ClaseLexica.NUM_INT, ClaseLexica.NUM_REAL, ClaseLexica.TRUE,
					ClaseLexica.FALSE, ClaseLexica.NOT);
			return null;
		}
	}

	public Exp RE3(Exp exph) {
		switch (anticipo.clase()) {
		case OP_MULT:
		case OP_DIV:
			String op = op3();
			Exp exp = E4();
			return sem.exp(op, exph, exp);
		case PCIERRE:
		case OP_SUMA:
		case OP_RESTA:
		case SEP_INS:
		case OP_MENOR:
		case OP_MAYOR:
		case OP_MEN_IG:
		case OP_MAY_IG:
		case OP_COMP:
		case OP_DIST:
		case AND:
		case OR:
		case END:
			return exph;
		default:
			errores.errorSintactico(anticipo.fila(), anticipo.columna(), anticipo.clase(), ClaseLexica.OP_MENOR,
					ClaseLexica.OP_MAYOR, ClaseLexica.OP_MEN_IG, ClaseLexica.OP_MAY_IG, ClaseLexica.OP_COMP,
					ClaseLexica.OP_DIST, ClaseLexica.OP_SUMA, ClaseLexica.OP_RESTA, ClaseLexica.AND, ClaseLexica.OR,
					ClaseLexica.OP_MULT, ClaseLexica.OP_DIV);
			return null;
		}
	}

	public Exp E4() {
		switch (anticipo.clase()) {
		case OP_RESTA:
			empareja(ClaseLexica.OP_RESTA);
			Exp exp1 = E5();
			return sem.exp("-", exp1);
		case NOT:
			empareja(ClaseLexica.NOT);
			Exp exp2 = E4();
			return sem.exp("not", exp2);
		case PAP:
		case ID:
		case NUM_INT:
		case NUM_REAL:
		case TRUE:
		case FALSE:
			return E5();
		default:
			errores.errorSintactico(anticipo.fila(), anticipo.columna(), anticipo.clase(), ClaseLexica.PAP,
					ClaseLexica.OP_RESTA, ClaseLexica.ID, ClaseLexica.NUM_INT, ClaseLexica.NUM_REAL, ClaseLexica.TRUE,
					ClaseLexica.FALSE, ClaseLexica.NOT);
			return null;
		}
	}

	public Exp E5() {
		switch (anticipo.clase()) {
		case NUM_INT:
			UnidadLexica num_int = anticipo;
			empareja(ClaseLexica.NUM_INT);
			return sem.num_int(sem.str(num_int.lexema(), num_int.fila(), num_int.columna()));
		case NUM_REAL:
			UnidadLexica num_real = anticipo;
			empareja(ClaseLexica.NUM_REAL);
			return sem.num_real(sem.str(num_real.lexema(), num_real.fila(), num_real.columna()));
		case ID:
			UnidadLexica id = anticipo;
			empareja(ClaseLexica.ID);
			return sem.id(sem.str(id.lexema(), id.fila(), id.columna()));
		case PAP:
			empareja(ClaseLexica.PAP);
			Exp exp = E0();
			empareja(ClaseLexica.PCIERRE);
			return exp;
		case TRUE:
			empareja(ClaseLexica.TRUE);
			return sem.verdad();
		case FALSE:
			empareja(ClaseLexica.FALSE);
			return sem.falso();
		default:
			errores.errorSintactico(anticipo.fila(), anticipo.columna(), anticipo.clase(), ClaseLexica.PAP,
					ClaseLexica.ID, ClaseLexica.NUM_INT, ClaseLexica.NUM_REAL, ClaseLexica.TRUE, ClaseLexica.FALSE);
			return null;
		}
	}

	public String op1() {
		switch (anticipo.clase()) {
		case AND:
			empareja(ClaseLexica.AND);
			return "and";
		case OR:
			empareja(ClaseLexica.OR);
			return "or";
		default:
			errores.errorSintactico(anticipo.fila(), anticipo.columna(), anticipo.clase(), ClaseLexica.AND,
					ClaseLexica.OR);
			return null;
		}
	}

	public String op2() {
		switch (anticipo.clase()) {
		case OP_MENOR:
			empareja(ClaseLexica.OP_MENOR);
			return "<";
		case OP_MAYOR:
			empareja(ClaseLexica.OP_MAYOR);
			return ">";
		case OP_MEN_IG:
			empareja(ClaseLexica.OP_MEN_IG);
			return "<=";
		case OP_MAY_IG:
			empareja(ClaseLexica.OP_MAY_IG);
			return ">=";
		case OP_COMP:
			empareja(ClaseLexica.OP_COMP);
			return "==";
		case OP_DIST:
			empareja(ClaseLexica.OP_DIST);
			return "!=";
		default:
			errores.errorSintactico(anticipo.fila(), anticipo.columna(), anticipo.clase(), ClaseLexica.OP_MENOR,
					ClaseLexica.OP_MAYOR, ClaseLexica.OP_MEN_IG, ClaseLexica.OP_MAY_IG, ClaseLexica.OP_COMP,
					ClaseLexica.OP_DIST);
			return null;
		}
	}

	public String op3() {
		switch (anticipo.clase()) {
		case OP_MULT:
			empareja(ClaseLexica.OP_MULT);
			return "*";
		case OP_DIV:
			empareja(ClaseLexica.OP_DIV);
			return "/";
		default:
			errores.errorSintactico(anticipo.fila(), anticipo.columna(), anticipo.clase(), ClaseLexica.OP_MULT,
					ClaseLexica.OP_DIV);
			return null;
		}
	}

	private void empareja(ClaseLexica claseEsperada) {
		if (anticipo.clase() == claseEsperada)
			sigToken();
		else
			errores.errorSintactico(anticipo.fila(), anticipo.columna(), anticipo.clase(), claseEsperada);
	}

	private void sigToken() {
		try {
			anticipo = alex.sigToken();
		} catch (IOException e) {
			errores.errorFatal(e);
		}
	}

}
