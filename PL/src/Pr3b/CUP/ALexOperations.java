package Pr3b.CUP;

public class ALexOperations {
  private AnalizadorLexico alex;
  public ALexOperations(AnalizadorLexico alex) {
   this.alex = alex;   
  }
  public UnidadLexica token(int clase) {
     UnidadLexica t = new UnidadLexica(alex.fila(), alex.col(), clase, alex.lexema());
     alex.incCol();
     return t;     
  }
}
