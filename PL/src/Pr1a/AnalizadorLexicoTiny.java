package Pr1a;

import java.io.FileInputStream;
import java.io.Reader;
import java.io.InputStreamReader;
import java.io.IOException;

public class AnalizadorLexicoTiny {

	private Reader input;
	private StringBuffer lex;
	private int sigCar;
	private int filaInicio;
	private int columnaInicio;
	private int filaActual;
	private int columnaActual;
	private static String NL = System.getProperty("line.separator");

	private static enum Estado {
		INICIO, ID, NUM_CERO, NUM_INT, NUM_REAL, TRAMPA, SEP_SEC, SEP_INS, OP_ASIG, PAP, PCIERRE, OP_SUMA, OP_RESTA, OP_MULT, OP_DIV,
		OP_MENOR, OP_MAYOR, OP_MEN_IG, OP_MAY_IG, OP_COMP, OP_DIST, END, OP_EXCLA, OP_AMP, PUNTO_DEC, P_DEC, DEC_INV,
		P_EXP, P_EXP_NEG, P_EXP_POS, NUM_REAL_CERO
	}

	private Estado estado;

	public AnalizadorLexicoTiny(Reader input) throws IOException {
		this.input = input;
		lex = new StringBuffer();
		sigCar = input.read();
		filaActual = 1;
		columnaActual = 1;
	}

	public UnidadLexica sigToken() throws IOException {
		estado = Estado.INICIO;
		filaInicio = filaActual;
		columnaInicio = columnaActual;
		lex.delete(0, lex.length());
		while (true) {
			switch (estado) {
			case INICIO:
				if (haySep())
					transitaIgnorando(Estado.INICIO);
				else if (hayMas())
					transita(Estado.OP_SUMA);
				else if (hayMenor())
					transita(Estado.OP_MENOR);
				else if (hayMayor())
					transita(Estado.OP_MAYOR);
				else if (hayIgual())
					transita(Estado.OP_ASIG);
				else if (hayExcla())
					transita(Estado.OP_EXCLA);
				else if (hayAmp())
					transita(Estado.OP_AMP);
				else if (hayPuntoComa())
					transita(Estado.SEP_INS);
				else if (hayPCierre())
					transita(Estado.PCIERRE);
				else if (hayPAp())
					transita(Estado.PAP);
				else if (hayEOF())
					transita(Estado.END);
				else if (hayMul())
					transita(Estado.OP_MULT);
				else if (hayDiv())
					transita(Estado.OP_DIV);
				else if (hayLetra())
					transita(Estado.ID);
				else if (hayMenos())
					transita(Estado.OP_RESTA);
				else if (hayDigitoPos())
					transita(Estado.NUM_INT);
				else if (hayCero())
					transita(Estado.NUM_CERO);
				else
					error();
				break;
			case OP_MENOR:
				if (hayIgual())
					transita(Estado.OP_MEN_IG);
				else
					return unidadMenor();
				break;
			case OP_MEN_IG:
				return unidadMenorIgual();
			case OP_MAYOR:
				if (hayIgual())
					transita(Estado.OP_MAY_IG);
				else
					return unidadMayor();
				break;
			case OP_MAY_IG:
				return unidadMayorIgual();
			case OP_ASIG:
				if (hayIgual())
					transita(Estado.OP_COMP);
				else
					return unidadIgual();
				break;
			case OP_COMP:
				return unidadComparacion();
			case OP_EXCLA:
				if (hayIgual())
					transita(Estado.OP_DIST);
				else
					error();
				break;
			case OP_DIST:
				return unidadDistinto();
			case OP_AMP:
				if (hayAmp())
					transita(Estado.SEP_SEC);
				else
					error();
				break;
			case SEP_SEC:
				return unidadDobleAmp();
			case OP_SUMA:
				if (hayDigitoPos())
					transita(Estado.NUM_INT);
				else if (hayCero())
					transita(Estado.NUM_CERO);
				else
					return unidadMas();
				break;
			case SEP_INS:
				return unidadPuntoComa();
			case PAP:
				return unidadPAp();
			case PCIERRE:
				return unidadPCierre();
			case END:	
				return unidadEof();
			case OP_MULT:
				return unidadPor();
			case OP_DIV:
				return unidadDiv();
			case ID:
				if (hayLetra() || hayDigito() || hayBarraBaja())
					transita(Estado.ID);
				else
					return unidadId();
				break;
			case OP_RESTA:
				if (hayDigitoPos())
					transita(Estado.NUM_INT);
				else if (hayCero())
					transita(Estado.NUM_CERO);
				else
					return unidadMenos();
				break;
			case NUM_INT:
				if (hayPunto())
					transita(Estado.PUNTO_DEC);
				else if (hayE())
					transita(Estado.P_EXP);
				else if (hayDigito())
					transita(Estado.NUM_INT);
				else
					return unidadEnt();
				break;
			case NUM_CERO:
				if (hayPunto())
					transita(Estado.PUNTO_DEC);
				else if (hayE())
					transita(Estado.P_EXP);
				else if (hayDigito())
					transita(Estado.TRAMPA);
				else
					return unidadEnt();
				break;
			case PUNTO_DEC:
				if (hayDigito())
					transita(Estado.P_DEC);
				else
					error();
				break;
			case P_DEC:
				if (hayCero())
					transita(Estado.DEC_INV);
				else if (hayDigitoPos())
					transita(Estado.P_DEC);
				else if (hayE())
					transita(Estado.P_EXP);
				else
					return unidadReal();
				break;
			case DEC_INV:
				if (hayDigitoPos())
					transita(Estado.P_DEC);
				else if (hayCero())
					transita(Estado.DEC_INV);
				else
					error();
				break;
			case P_EXP: 
				if (hayDigitoPos())
					transita(Estado.NUM_REAL);
				else if (hayCero())
					transita(Estado.NUM_REAL_CERO);
				else if (hayMenos())
					transita(Estado.P_EXP_NEG);
				else if (hayMas())
					transita(Estado.P_EXP_POS);
				else
					error();
				break;
			case P_EXP_NEG:
				if (hayDigitoPos())
					transita(Estado.NUM_REAL);
				else if (hayCero())
					transita(Estado.NUM_REAL_CERO);
				else
					error();
				break;
			case P_EXP_POS:
				if (hayDigitoPos())
					transita(Estado.NUM_REAL);
				else if (hayCero())
					transita(Estado.NUM_REAL_CERO);
				else
					error();
				break;
			case NUM_REAL:
				if (hayDigito())
					transita(Estado.NUM_REAL);
				else
					return unidadReal();
				break;
			case NUM_REAL_CERO:
				if (hayDigito())
					transita(Estado.TRAMPA);
				else
					return unidadReal();
				break;
			default: 
				error();
				break;
			}
		}
	}

	private void transita(Estado sig) throws IOException {
		lex.append((char) sigCar);
		sigCar();
		estado = sig;
	}

	private void transitaIgnorando(Estado sig) throws IOException {
		sigCar();
		filaInicio = filaActual;
		columnaInicio = columnaActual;
		estado = sig;
	}

	private void sigCar() throws IOException {
		sigCar = input.read();
		if (sigCar == NL.charAt(0))
			saltaFinDeLinea();
		if (sigCar == '\n') {
			filaActual++;
			columnaActual = 0;
		} else {
			columnaActual++;
		}
	}

	private void saltaFinDeLinea() throws IOException {
		for (int i = 1; i < NL.length(); i++) {
			sigCar = input.read();
			if (sigCar != NL.charAt(i))
				error();
		}
		sigCar = '\n';
	}

	private boolean hayBarraBaja() {
		return sigCar == '_';
	}

	private boolean hayLetra() {
		return sigCar >= 'a' && sigCar <= 'z' || sigCar >= 'A' && sigCar <= 'Z';
	}

	private boolean hayDigitoPos() {
		return sigCar >= '1' && sigCar <= '9';
	}

	private boolean hayCero() {
		return sigCar == '0';
	}

	private boolean hayDigito() {
		return hayDigitoPos() || hayCero();
	}

	private boolean hayMas() {
		return sigCar == '+';
	}

	private boolean hayMenos() {
		return sigCar == '-';
	}

	private boolean hayMul() {
		return sigCar == '*';
	}

	private boolean hayDiv() {
		return sigCar == '/';
	}

	private boolean hayPAp() {
		return sigCar == '(';
	}

	private boolean hayPCierre() {
		return sigCar == ')';
	}

	private boolean hayIgual() {
		return sigCar == '=';
	}

	private boolean hayAmp() {
		return sigCar == '&';
	}

	private boolean hayPunto() {
		return sigCar == '.';
	}

	private boolean hayPuntoComa() {
		return sigCar == ';';
	}

	private boolean hayExcla() {
		return sigCar == '!';
	}

	private boolean haySep() {
		return sigCar == ' ' || sigCar == '\t' || sigCar == '\r' || sigCar == '\b' || sigCar == '\n';
	}

	private boolean hayEOF() {
		return sigCar == -1;
	}

	private boolean hayMayor() {
		return sigCar == '>';
	}

	private boolean hayMenor() {
		return sigCar == '<';
	}

	private boolean hayE() {
		return sigCar == 'e' || sigCar == 'E';
	}

	private UnidadLexica unidadId() {
		switch (lex.toString()) {
		case "int":
			return new UnidadLexicaUnivaluada(filaInicio, columnaInicio, ClaseLexica.INT);
		case "real":
			return new UnidadLexicaUnivaluada(filaInicio, columnaInicio, ClaseLexica.REAL);
		case "bool":
			return new UnidadLexicaUnivaluada(filaInicio, columnaInicio, ClaseLexica.BOOL);
		case "true":
			return new UnidadLexicaUnivaluada(filaInicio, columnaInicio, ClaseLexica.TRUE);
		case "false":
			return new UnidadLexicaUnivaluada(filaInicio, columnaInicio, ClaseLexica.FALSE);
		case "and":
			return new UnidadLexicaUnivaluada(filaInicio, columnaInicio, ClaseLexica.AND);
		case "or":
			return new UnidadLexicaUnivaluada(filaInicio, columnaInicio, ClaseLexica.OR);
		case "not":
			return new UnidadLexicaUnivaluada(filaInicio, columnaInicio, ClaseLexica.NOT);
		default:
			return new UnidadLexicaMultivaluada(filaInicio, columnaInicio, ClaseLexica.ID, lex.toString());
		}
	}

	private UnidadLexica unidadEnt() {
		return new UnidadLexicaMultivaluada(filaInicio, columnaInicio, ClaseLexica.NUM_INT, lex.toString());
	}

	private UnidadLexica unidadReal() {
		return new UnidadLexicaMultivaluada(filaInicio, columnaInicio, ClaseLexica.NUM_REAL, lex.toString());
	}

	private UnidadLexica unidadMas() {
		return new UnidadLexicaUnivaluada(filaInicio, columnaInicio, ClaseLexica.OP_SUMA);
	}

	private UnidadLexica unidadMenos() {
		return new UnidadLexicaUnivaluada(filaInicio, columnaInicio, ClaseLexica.OP_RESTA);
	}

	private UnidadLexica unidadPor() {
		return new UnidadLexicaUnivaluada(filaInicio, columnaInicio, ClaseLexica.OP_MULT);
	}

	private UnidadLexica unidadDiv() {
		return new UnidadLexicaUnivaluada(filaInicio, columnaInicio, ClaseLexica.OP_DIV);
	}

	private UnidadLexica unidadPAp() {
		return new UnidadLexicaUnivaluada(filaInicio, columnaInicio, ClaseLexica.PAP);
	}

	private UnidadLexica unidadPCierre() {
		return new UnidadLexicaUnivaluada(filaInicio, columnaInicio, ClaseLexica.PCIERRE);
	}

	private UnidadLexica unidadIgual() {
		return new UnidadLexicaUnivaluada(filaInicio, columnaInicio, ClaseLexica.OP_ASIG);
	}

	private UnidadLexica unidadPuntoComa() {
		return new UnidadLexicaUnivaluada(filaInicio, columnaInicio, ClaseLexica.SEP_INS);
	}
	
	private UnidadLexica unidadDobleAmp() {
		return new UnidadLexicaUnivaluada(filaInicio, columnaInicio, ClaseLexica.SEP_SEC);
	}
	
	private UnidadLexica unidadDistinto() {
		return new UnidadLexicaUnivaluada(filaInicio, columnaInicio, ClaseLexica.OP_DIST);
	}
	
	private UnidadLexica unidadComparacion() {
		return new UnidadLexicaUnivaluada(filaInicio, columnaInicio, ClaseLexica.OP_COMP);
	}
	
	private UnidadLexica unidadMayor() {
		return new UnidadLexicaUnivaluada(filaInicio, columnaInicio, ClaseLexica.OP_MAYOR);
	}
	
	private UnidadLexica unidadMenor() {
		return new UnidadLexicaUnivaluada(filaInicio, columnaInicio, ClaseLexica.OP_MENOR);
	}
	
	private UnidadLexica unidadMayorIgual() {
		return new UnidadLexicaUnivaluada(filaInicio, columnaInicio, ClaseLexica.OP_MAY_IG);
	}
	
	private UnidadLexica unidadMenorIgual() {
		return new UnidadLexicaUnivaluada(filaInicio, columnaInicio, ClaseLexica.OP_MEN_IG);
	}

	private UnidadLexica unidadEof() {
		return new UnidadLexicaUnivaluada(filaInicio, columnaInicio, ClaseLexica.END);
	}

	private void error() { 
		System.err.println("(" + filaActual + ',' + columnaActual + "):Caracter inexperado");
		System.exit(1);
	}

	public static void main(String arg[]) throws IOException {
		Reader input = new InputStreamReader(new FileInputStream("Tiny0_prueba2.txt"));
		AnalizadorLexicoTiny al = new AnalizadorLexicoTiny(input);
		UnidadLexica unidad;
		do {
			unidad = al.sigToken();
			System.out.println(unidad);
		} while (unidad.clase() != ClaseLexica.END);
	}
}