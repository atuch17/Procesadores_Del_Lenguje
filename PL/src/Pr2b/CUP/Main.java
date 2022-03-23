package Pr2b.CUP;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.Reader;

public class Main {
   public static void main(String[] args) throws Exception {
     Reader input = new InputStreamReader(new FileInputStream(args[0]));
	 AnalizadorLexicoTiny alex = new AnalizadorLexicoTiny(input);
	 AnalizadorSintacticoTinyCUP asint = new AnalizadorSintacticoTinyCUP(alex);
	 asint.parse(); //asint.debug_parse();
 }
}   
   
