package Pr1b;

public class ALexOperations {
	private AnalizadorLexicoTiny alex;

	public ALexOperations(AnalizadorLexicoTiny alex) {
		this.alex = alex;
	}

	public UnidadLexica unidadId() {
		return new UnidadLexicaMultivaluada(alex.fila(), alex.columna(), ClaseLexica.ID, alex.lexema());
	}
	
	public UnidadLexica unidadCadena() {
		return new UnidadLexicaMultivaluada(alex.fila(), alex.columna(), ClaseLexica.CADENA, alex.lexema());
	}
	
	public UnidadLexica unidadNumInt() {
		return new UnidadLexicaMultivaluada(alex.fila(), alex.columna(), ClaseLexica.NUM_INT, alex.lexema());
	}
	
	public UnidadLexica unidadNumReal() {
		return new UnidadLexicaMultivaluada(alex.fila(), alex.columna(), ClaseLexica.NUM_REAL, alex.lexema());
	}

	public UnidadLexica unidadSepSec() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.SEP_SEC);
	}

	public UnidadLexica unidadSepIns() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.SEP_INS);
	}
	
	public UnidadLexica unidadAsig() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.OP_ASIG);
	}
	
	public UnidadLexica unidadPAp() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.PAP);
	}
	
	public UnidadLexica unidadPCierre() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.PCIERRE);
	}
	
	public UnidadLexica unidadSuma() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.OP_SUMA);
	}
	
	public UnidadLexica unidadResta() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.OP_RESTA);
	}
	
	public UnidadLexica unidadMult() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.OP_MULT);
	}
	
	public UnidadLexica unidadDiv() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.OP_DIV);
	}
	
	public UnidadLexica unidadMenor() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.OP_MENOR);
	}
	
	public UnidadLexica unidadMayor() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.OP_MAYOR);
	}
	
	public UnidadLexica unidadMenorIgual() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.OP_MEN_IG);
	}
	
	public UnidadLexica unidadMayorIgual() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.OP_MAY_IG);
	}
	
	public UnidadLexica unidadComparador() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.OP_COMP);
	}
	
	public UnidadLexica unidadDistinto() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.OP_DIST);
	}
	
	public UnidadLexica unidadPorcentaje() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.OP_100);
	}
	
	public UnidadLexica unidadCorcheteAp() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.CORCHETE_AP);
	}
	
	public UnidadLexica unidadCorcheteCierre() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.CORCHETE_CIERRE);
	}
	
	public UnidadLexica unidadLlaveAp() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.LLAVE_AP);
	}
	
	public UnidadLexica unidadLlaveCierre() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.LLAVE_CIERRE);
	}
	
	public UnidadLexica unidadPunto() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.PUNTO);
	}
	
	public UnidadLexica unidadFlecha() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.OP_FLECHA);
	}
	
	public UnidadLexica unidadComa() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.COMA);
	}
	
	public UnidadLexica unidadAmp() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.OP_AMP);
	}
	
	public UnidadLexica unidadEof() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.END);
	}
	
	public UnidadLexica unidadInt() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.INT);
	}
	
	public UnidadLexica unidadReal() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.REAL);
	}
	
	public UnidadLexica unidadBool() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.BOOL);
	}
	
	public UnidadLexica unidadTrue() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.TRUE);
	}
	
	public UnidadLexica unidadFalse() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.FALSE);
	}
	
	public UnidadLexica unidadAnd() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.AND);
	}
	
	public UnidadLexica unidadOr() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.OR);
	}
	
	public UnidadLexica unidadNot() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.NOT);
	}
	
	public UnidadLexica unidadString() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.STRING);
	}
	
	public UnidadLexica unidadNull() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.NULL);
	}
	
	public UnidadLexica unidadProc() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.PROC);
	}
	
	public UnidadLexica unidadIf() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.IF);
	}
	
	public UnidadLexica unidadThen() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.THEN);
	}
	
	public UnidadLexica unidadElse() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.ELSE);
	}
	
	public UnidadLexica unidadEndif() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.ENDIF);
	}
	
	public UnidadLexica unidadWhile() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.WHILE);
	}
	
	public UnidadLexica unidadDo() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.DO);
	}
	
	public UnidadLexica unidadEndwhile() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.ENDWHILE);
	}
	
	public UnidadLexica unidadCall() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.CALL);
	}
	
	public UnidadLexica unidadRecord() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.RECORD);
	}
	
	public UnidadLexica unidadArray() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.ARRAY);
	}
	
	public UnidadLexica unidadOf() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.OF);
	}
	
	public UnidadLexica unidadPointer() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.POINTER);
	}
	
	public UnidadLexica unidadNew() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.NEW);
	}
	
	public UnidadLexica unidadDelete() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.DELETE);
	}
	
	public UnidadLexica unidadRead() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.READ);
	}
	
	public UnidadLexica unidadWrite() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.WRITE);
	}
	
	public UnidadLexica unidadNl() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.NL);
	}
	
	public UnidadLexica unidadVar() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.VAR);
	}
	
	public UnidadLexica unidadType() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.TYPE);
	}

	public void error() {
		System.err.println("***" + alex.fila() + " Caracter inexperado: " + alex.lexema());
	}
}
