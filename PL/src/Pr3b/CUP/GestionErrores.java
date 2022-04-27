package Pr3b.CUP;


public class GestionErrores {
   public void errorLexico(int fila, String lexema) {
     System.out.println("ERROR fila "+fila+": Caracter inesperado: "+lexema); 
     System.exit(1);
   }  
   public void errorSintactico(UnidadLexica unidadLexica) {
     System.out.print("ERROR fila "+unidadLexica.lexema().fila()+","+unidadLexica.lexema().col()+
                              ": Elemento inesperado "+unidadLexica.value);
     System.exit(1);
   }
}
