package Pr3b.CUP;

import Pr3a.TinyASint.StringLocalizado;
import java_cup.runtime.Symbol;

public class UnidadLexica extends Symbol {
  public UnidadLexica(int fila, int col, int clase, String lexema) {
    super(clase, null);
    value = new StringLocalizado(lexema, fila, col);
  }

  public int clase() {
    return sym;
  }

  public StringLocalizado lexema() {
    return (StringLocalizado) value;
  }

  public String toString() {
    StringLocalizado lexema = (StringLocalizado) value;
    return "[clase=" + clase2string(sym) + ",lexema=" + lexema + ",fila=" + lexema.fila() + ",col=" + lexema.col()
        + "]";
  }

  private String clase2string(int clase) {
    switch (clase) {
      case ClaseLexica.NUM_INT:
        return "NUM_INT";
      case ClaseLexica.NUM_REAL:
        return "NUM_REAL";
      case ClaseLexica.SEP_SEC:
        return "SEP_SEC";
      case ClaseLexica.SEP_INS:
        return "SEP_INS";
      case ClaseLexica.ASIG:
        return "ASIG";
      case ClaseLexica.P_AP:
        return "P_AP";
      case ClaseLexica.P_CIERRE:
        return "P_CIERRE";
      case ClaseLexica.OP_SUMA:
        return "OP_SUMA";
      case ClaseLexica.OP_RESTA:
        return "OP_RESTA";
      case ClaseLexica.OP_MULT:
        return "OP_MULT";
      case ClaseLexica.OP_DIV:
        return "OP_DIV";
      case ClaseLexica.OP_MENOR:
        return "OP_MENOR";
      case ClaseLexica.OP_MAYOR:
        return "OP_MAYOR";
      case ClaseLexica.OP_MEN_IG:
        return "OP_MEN_IG";
      case ClaseLexica.OP_MAY_IG:
        return "OP_MAY_IG";
      case ClaseLexica.OP_COMP:
        return "OP_COMP";
      case ClaseLexica.OP_DIST:
        return "OP_DIST";
      case ClaseLexica.OP_100:
        return "OP_100";
      case ClaseLexica.CORCHETE_AP:
        return "CORCHETE_AP";
      case ClaseLexica.CORCHETE_CIERRE:
        return "CORCHETE_CIERRE";
      case ClaseLexica.LLAVE_AP:
        return "LLAVE_AP";
      case ClaseLexica.LLAVE_CIERRE:
        return "LLAVE_CIERRE";
      case ClaseLexica.PUNTO:
        return "PUNTO";
      case ClaseLexica.OP_FLECHA:
        return "OP_FLECHA";
      case ClaseLexica.COMA:
        return "COMA";
      case ClaseLexica.OP_AMP:
        return "OP_AMP";
      case ClaseLexica.INT:
        return "INT";
      case ClaseLexica.REAL:
        return "REAL";
      case ClaseLexica.BOOL:
        return "BOOL";
      case ClaseLexica.TRUE:
        return "TRUE";
      case ClaseLexica.FALSE:
        return "FALSE";
      case ClaseLexica.AND:
        return "AND";
      case ClaseLexica.OR:
        return "OR";
      case ClaseLexica.NOT:
        return "NOT";
      case ClaseLexica.STRING:
        return "STRING";
      case ClaseLexica.NULL:
        return "NULL";
      case ClaseLexica.PROC:
        return "PROC";
      case ClaseLexica.IF:
        return "IF";
      case ClaseLexica.THEN:
        return "THEN";
      case ClaseLexica.ELSE:
        return "ELSE";
      case ClaseLexica.ENDIF:
        return "ENDIF";
      case ClaseLexica.WHILE:
        return "WHILE";
      case ClaseLexica.DO:
        return "DO";
      case ClaseLexica.ENDWHILE:
        return "ENDWHILE";
      case ClaseLexica.CALL:
        return "CALL";
      case ClaseLexica.RECORD:
        return "RECORD";
      case ClaseLexica.ARRAY:
        return "ARRAY";
      case ClaseLexica.OF:
        return "OF";
      case ClaseLexica.POINTER:
        return "POINTER";
      case ClaseLexica.NEW:
        return "NEW";
      case ClaseLexica.DELETE:
        return "DELETE";
      case ClaseLexica.READ:
        return "READ";
      case ClaseLexica.WRITE:
        return "WRITE";
      case ClaseLexica.NL:
        return "NL";
      case ClaseLexica.VAR:
        return "VAR";
      case ClaseLexica.TYPE:
        return "TYPE";
      case ClaseLexica.CADENA:
        return "CADENA";
      case ClaseLexica.ID:
        return "ID";
      default:
        return "?";
    }
  }
}
