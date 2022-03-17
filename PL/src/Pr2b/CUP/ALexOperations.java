package Pr2b.CUP;

public class ALexOperations {
	private AnalizadorLexicoTiny alex;

	public ALexOperations(AnalizadorLexicoTiny alex) {
		this.alex = alex;
	}

	public UnidadLexica unidadId() {
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.ID, alex.lexema());
	}
	
	public UnidadLexica unidadCadena() {
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.CADENA, alex.lexema());
	}
	
	public UnidadLexica unidadNumInt() {
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.NUM_INT, alex.lexema());
	}
	
	public UnidadLexica unidadNumReal() {
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.NUM_REAL, alex.lexema());
	}

	public UnidadLexica unidadSepSec() {
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.SEP_SEC, "&&");
	}

	public UnidadLexica unidadSepIns() {
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.SEP_INS, ";");
	}
	
	public UnidadLexica unidadAsig() {
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.OP_ASIG, "=");
	}
	
	public UnidadLexica unidadPAp() {
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.PAP, "(");
	}
	
	public UnidadLexica unidadPCierre() {
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.PCIERRE, ")");
	}
	
	public UnidadLexica unidadSuma() {
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.OP_SUMA, "+");
	}
	
	public UnidadLexica unidadResta() {
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.OP_RESTA, "-");
	}
	
	public UnidadLexica unidadMult() {
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.OP_MULT, "*");
	}
	
	public UnidadLexica unidadDiv() {
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.OP_DIV, "/");
	}
	
	public UnidadLexica unidadMenor() {
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.OP_MENOR, "<");
	}
	
	public UnidadLexica unidadMayor() {
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.OP_MAYOR, ">");
	}
	
	public UnidadLexica unidadMenorIgual() {
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.OP_MEN_IG, "<=");
	}
	
	public UnidadLexica unidadMayorIgual() {
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.OP_MAY_IG, ">=");
	}
	
	public UnidadLexica unidadComparador() {
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.OP_COMP, "==");
	}
	
	public UnidadLexica unidadDistinto() {
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.OP_DIST, "!=");
	}
	
	public UnidadLexica unidadPorcentaje() {
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.OP_100, "%");
	}
	
	public UnidadLexica unidadCorcheteAp() {
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.CORCHETE_AP, "[");
	}
	
	public UnidadLexica unidadCorcheteCierre() {
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.CORCHETE_CIERRE, "]");
	}
	
	public UnidadLexica unidadLlaveAp() {
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.LLAVE_AP, "{");
	}
	
	public UnidadLexica unidadLlaveCierre() {
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.LLAVE_CIERRE, "}");
	}
	
	public UnidadLexica unidadPunto() {
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.PUNTO, ".");
	}
	
	public UnidadLexica unidadFlecha() {
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.OP_FLECHA, "->");
	}
	
	public UnidadLexica unidadComa() {
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.COMA, ",");
	}
	
	public UnidadLexica unidadAmp() {
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.OP_AMP, "&");
	}
	
	public UnidadLexica unidadEof() {
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.EOF, "<EOF>");
	}
	
	public UnidadLexica unidadInt() {
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.INT, "int");
	}
	
	public UnidadLexica unidadReal() {
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.REAL, "real");
	}
	
	public UnidadLexica unidadBool() {
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.BOOL, "bool");
	}
	
	public UnidadLexica unidadTrue() {
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.TRUE, "true");
	}
	
	public UnidadLexica unidadFalse() {
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.FALSE, "false");
	}
	
	public UnidadLexica unidadAnd() {
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.AND, "and");
	}
	
	public UnidadLexica unidadOr() {
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.OR, "or");
	}
	
	public UnidadLexica unidadNot() {
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.NOT, "not");
	}
	
	public UnidadLexica unidadString() {
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.STRING, "string");
	}
	
	public UnidadLexica unidadNull() {
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.NULL, "null");
	}
	
	public UnidadLexica unidadProc() {
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.PROC, "proc");
	}
	
	public UnidadLexica unidadIf() {
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.IF, "if");
	}
	
	public UnidadLexica unidadThen() {
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.THEN, "then");
	}
	
	public UnidadLexica unidadElse() {
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.ELSE, "else");
	}
	
	public UnidadLexica unidadEndif() {
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.ENDIF, "endif");
	}
	
	public UnidadLexica unidadWhile() {
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.WHILE, "while");
	}
	
	public UnidadLexica unidadDo() {
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.DO, "do");
	}
	
	public UnidadLexica unidadEndwhile() {
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.ENDWHILE, "endwhile");
	}
	
	public UnidadLexica unidadCall() {
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.CALL, "call");
	}
	
	public UnidadLexica unidadRecord() {
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.RECORD, "record");
	}
	
	public UnidadLexica unidadArray() {
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.ARRAY, "array");
	}
	
	public UnidadLexica unidadOf() {
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.OF, "of");
	}
	
	public UnidadLexica unidadPointer() {
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.POINTER, "pointer");
	}
	
	public UnidadLexica unidadNew() {
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.NEW, "new");
	}
	
	public UnidadLexica unidadDelete() {
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.DELETE, "delete");
	}
	
	public UnidadLexica unidadRead() {
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.READ, "read");
	}
	
	public UnidadLexica unidadWrite() {
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.WRITE, "write");
	}
	
	public UnidadLexica unidadNl() {
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.NL, "nl");
	}
	
	public UnidadLexica unidadVar() {
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.VAR, "var");
	}
	
	public UnidadLexica unidadType() {
		return new UnidadLexica(alex.fila(), alex.columna(), ClaseLexica.TYPE, "type");
	}
}
