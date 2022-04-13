package Pr3a;

import java.io.IOException;
import java.io.Reader;

import Pr2a.ClaseLexica;
import Pr3a.TinyASint.Dec;
import Pr3a.TinyASint.Decs;
import Pr3a.TinyASint.Exp;
import Pr3a.TinyASint.Insts;
import Pr3a.TinyASint.Prog;

public class ConstructorAST {
	private UnidadLexica anticipo;
	private AnalizadorLexicoTiny alex;
	private GestionErroresTiny errores;
	private SemOps sem;

	public ConstructorAST(Reader input) throws IOException {
		errores = new GestionErroresTiny();
		alex = new AnalizadorLexicoTiny(input, errores);
		sigToken();
		sem = new SemOps();
	}

	public Prog Init() {
		Prog prog = Programa();
		empareja(ClaseLexica.END);
		return prog;
	}

	public Prog Programa() {
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
		}
	}

	public Decs Declaraciones() {
		switch (anticipo.clase()) {
		case INT:
		case REAL:
		case BOOL:
			Dec dec = Declaracion();
			Decs decs = RD();
			return sem.decs_una(dec, decs);
		default:
			errores.errorSintactico(anticipo.fila(), anticipo.columna(), anticipo.clase(), ClaseLexica.INT,
					ClaseLexica.REAL, ClaseLexica.BOOL);
		}
	}

	public Decs RD() {
		switch (anticipo.clase()) {
		case SEP_INS:
			empareja(ClaseLexica.SEP_INS);
			Declaracion();
			RD();
			break;
		case SEP_SEC:
			break;
		default:
			errores.errorSintactico(anticipo.fila(), anticipo.columna(), anticipo.clase(), ClaseLexica.SEP_INS,
					ClaseLexica.SEP_SEC);
		}
	}

	public Dec Declaracion() {
		switch (anticipo.clase()) {
		case INT:
		case REAL:
		case BOOL:
			Tipo();
			empareja(ClaseLexica.ID);
			break;
		default:
			errores.errorSintactico(anticipo.fila(), anticipo.columna(), anticipo.clase(), ClaseLexica.INT,
					ClaseLexica.REAL, ClaseLexica.BOOL);
		}
	}

	public void Tipo() {
		switch (anticipo.clase()) {
		case INT:
			empareja(ClaseLexica.INT);
			break;
		case REAL:
			empareja(ClaseLexica.REAL);
			break;
		case BOOL:
			empareja(ClaseLexica.BOOL);
			break;
		default:
			errores.errorSintactico(anticipo.fila(), anticipo.columna(), anticipo.clase(), ClaseLexica.INT,
					ClaseLexica.REAL, ClaseLexica.BOOL);
		}
	}

	public Insts Instrucciones() {
		switch (anticipo.clase()) {
		case ID:
			Instruccion();
			RI();
			break;
		default:
			errores.errorSintactico(anticipo.fila(), anticipo.columna(), anticipo.clase(), ClaseLexica.ID);
		}
	}

	public void RI() {
		switch (anticipo.clase()) {
		case SEP_INS:
			empareja(ClaseLexica.SEP_INS);
			Instruccion();
			RI();
			break;
		case END:
			break;
		default:
			errores.errorSintactico(anticipo.fila(), anticipo.columna(), anticipo.clase(), ClaseLexica.SEP_INS,
					ClaseLexica.END);
		}
	}

	public void Instruccion() {
		switch (anticipo.clase()) {
		case ID:
			empareja(ClaseLexica.ID);
			empareja(ClaseLexica.OP_ASIG);
			E0();
			break;
		default:
			errores.errorSintactico(anticipo.fila(), anticipo.columna(), anticipo.clase(), ClaseLexica.ID);
		}
	}

	public void E0() {
		switch (anticipo.clase()) {
		case PAP:
		case OP_RESTA:
		case ID:
		case NUM_INT:
		case NUM_REAL:
		case TRUE:
		case FALSE:
		case NOT:
			E1();
			RE0();
			break;
		default:
			errores.errorSintactico(anticipo.fila(), anticipo.columna(), anticipo.clase(), ClaseLexica.PAP,
					ClaseLexica.OP_RESTA, ClaseLexica.ID, ClaseLexica.NUM_INT, ClaseLexica.NUM_REAL, ClaseLexica.TRUE,
					ClaseLexica.FALSE, ClaseLexica.NOT);
		}
	}

	public void RE0() {
		switch (anticipo.clase()) {
		case OP_SUMA:
			empareja(ClaseLexica.OP_SUMA);
			E0();
			break;
		case OP_RESTA:
			empareja(ClaseLexica.OP_RESTA);
			E1();
			break;
		case PCIERRE:
		case SEP_INS:
		case END:
			break;
		default:
			errores.errorSintactico(anticipo.fila(), anticipo.columna(), anticipo.clase(), ClaseLexica.OP_SUMA,
					ClaseLexica.OP_RESTA);
		}
	}

	public void E1() {
		switch (anticipo.clase()) {
		case PAP:
		case OP_RESTA:
		case ID:
		case NUM_INT:
		case NUM_REAL:
		case TRUE:
		case FALSE:
		case NOT:
			E2();
			RE1();
			break;
		default:
			errores.errorSintactico(anticipo.fila(), anticipo.columna(), anticipo.clase(), ClaseLexica.PAP,
					ClaseLexica.OP_RESTA, ClaseLexica.ID, ClaseLexica.NUM_INT, ClaseLexica.NUM_REAL, ClaseLexica.TRUE,
					ClaseLexica.FALSE, ClaseLexica.NOT);
		}
	}

	public void RE1() {
		switch (anticipo.clase()) {
		case AND:
		case OR:
			op1();
			E2();
			RE1();
			break;
		case PCIERRE:
		case OP_SUMA:
		case OP_RESTA:
		case SEP_INS:
		case END:
			break;
		default:
			errores.errorSintactico(anticipo.fila(), anticipo.columna(), anticipo.clase(), ClaseLexica.AND,
					ClaseLexica.OR, ClaseLexica.OP_SUMA, ClaseLexica.OP_RESTA);
		}
	}

	public void E2() {
		switch (anticipo.clase()) {
		case PAP:
		case OP_RESTA:
		case ID:
		case NUM_INT:
		case NUM_REAL:
		case TRUE:
		case FALSE:
		case NOT:
			E3();
			RE2();
			break;
		default:
			errores.errorSintactico(anticipo.fila(), anticipo.columna(), anticipo.clase(), ClaseLexica.PAP,
					ClaseLexica.OP_RESTA, ClaseLexica.ID, ClaseLexica.NUM_INT, ClaseLexica.NUM_REAL, ClaseLexica.TRUE,
					ClaseLexica.FALSE, ClaseLexica.NOT);
		}
	}

	public void RE2() {
		switch (anticipo.clase()) {
		case OP_MENOR:
		case OP_MAYOR:
		case OP_MEN_IG:
		case OP_MAY_IG:
		case OP_COMP:
		case OP_DIST:
			op2();
			E3();
			RE2();
			break;
		case PCIERRE:
		case OP_SUMA:
		case OP_RESTA:
		case SEP_INS:
		case AND:
		case OR:
		case END:
			break;
		default:
			errores.errorSintactico(anticipo.fila(), anticipo.columna(), anticipo.clase(), ClaseLexica.OP_MENOR,
					ClaseLexica.OP_MAYOR, ClaseLexica.OP_MEN_IG, ClaseLexica.OP_MAY_IG, ClaseLexica.OP_COMP,
					ClaseLexica.OP_DIST, ClaseLexica.OP_SUMA, ClaseLexica.OP_RESTA, ClaseLexica.AND, ClaseLexica.OR);
		}
	}

	public void E3() {
		switch (anticipo.clase()) {
		case PAP:
		case OP_RESTA:
		case ID:
		case NUM_INT:
		case NUM_REAL:
		case TRUE:
		case FALSE:
		case NOT:
			E4();
			RE3();
			break;
		default:
			errores.errorSintactico(anticipo.fila(), anticipo.columna(), anticipo.clase(), ClaseLexica.PAP,
					ClaseLexica.OP_RESTA, ClaseLexica.ID, ClaseLexica.NUM_INT, ClaseLexica.NUM_REAL, ClaseLexica.TRUE,
					ClaseLexica.FALSE, ClaseLexica.NOT);
		}
	}

	public void RE3() {
		switch (anticipo.clase()) {
		case OP_MULT:
		case OP_DIV:
			op3();
			E4();
			break;
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
			break;
		default:
			errores.errorSintactico(anticipo.fila(), anticipo.columna(), anticipo.clase(), ClaseLexica.OP_MENOR,
					ClaseLexica.OP_MAYOR, ClaseLexica.OP_MEN_IG, ClaseLexica.OP_MAY_IG, ClaseLexica.OP_COMP,
					ClaseLexica.OP_DIST, ClaseLexica.OP_SUMA, ClaseLexica.OP_RESTA, ClaseLexica.AND, ClaseLexica.OR,
					ClaseLexica.OP_MULT, ClaseLexica.OP_DIV);
		}
	}

	public void E4() {
		switch (anticipo.clase()) {
		case OP_RESTA:
			empareja(ClaseLexica.OP_RESTA);
			E5();
			break;
		case NOT:
			empareja(ClaseLexica.NOT);
			E4();
			break;
		case PAP:
		case ID:
		case NUM_INT:
		case NUM_REAL:
		case TRUE:
		case FALSE:
			E5();
			break;
		default:
			errores.errorSintactico(anticipo.fila(), anticipo.columna(), anticipo.clase(), ClaseLexica.PAP,
					ClaseLexica.OP_RESTA, ClaseLexica.ID, ClaseLexica.NUM_INT, ClaseLexica.NUM_REAL, ClaseLexica.TRUE,
					ClaseLexica.FALSE, ClaseLexica.NOT);
		}
	}

	public void E5() {
		switch (anticipo.clase()) {
		case NUM_INT:
			empareja(ClaseLexica.NUM_INT);
			break;
		case NUM_REAL:
			empareja(ClaseLexica.NUM_REAL);
			break;
		case ID:
			empareja(ClaseLexica.ID);
			break;
		case PAP:
			empareja(ClaseLexica.PAP);
			E0();
			empareja(ClaseLexica.PCIERRE);
			break;
		case TRUE:
			empareja(ClaseLexica.TRUE);
			break;
		case FALSE:
			empareja(ClaseLexica.FALSE);
			break;
		default:
			errores.errorSintactico(anticipo.fila(), anticipo.columna(), anticipo.clase(), ClaseLexica.PAP,
					ClaseLexica.ID, ClaseLexica.NUM_INT, ClaseLexica.NUM_REAL, ClaseLexica.TRUE, ClaseLexica.FALSE);
		}
	}

	public void op1() {
		switch (anticipo.clase()) {
		case AND:
			empareja(ClaseLexica.AND);
			break;
		case OR:
			empareja(ClaseLexica.OR);
			break;
		default:
			errores.errorSintactico(anticipo.fila(), anticipo.columna(), anticipo.clase(), ClaseLexica.AND,
					ClaseLexica.OR);
		}
	}

	public void op2() {
		switch (anticipo.clase()) {
		case OP_MENOR:
			empareja(ClaseLexica.OP_MENOR);
			break;
		case OP_MAYOR:
			empareja(ClaseLexica.OP_MAYOR);
			break;
		case OP_MEN_IG:
			empareja(ClaseLexica.OP_MEN_IG);
			break;
		case OP_MAY_IG:
			empareja(ClaseLexica.OP_MAY_IG);
			break;
		case OP_COMP:
			empareja(ClaseLexica.OP_COMP);
			break;
		case OP_DIST:
			empareja(ClaseLexica.OP_DIST);
			break;
		default:
			errores.errorSintactico(anticipo.fila(), anticipo.columna(), anticipo.clase(), ClaseLexica.OP_MENOR,
					ClaseLexica.OP_MAYOR, ClaseLexica.OP_MEN_IG, ClaseLexica.OP_MAY_IG, ClaseLexica.OP_COMP,
					ClaseLexica.OP_DIST);

		}
	}

	public void op3() {
		switch (anticipo.clase()) {
		case OP_MULT:
			empareja(ClaseLexica.OP_MULT);
			break;
		case OP_DIV:
			empareja(ClaseLexica.OP_DIV);
			break;
		default:
			errores.errorSintactico(anticipo.fila(), anticipo.columna(), anticipo.clase(), ClaseLexica.OP_MULT,
					ClaseLexica.OP_DIV);
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
