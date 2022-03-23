package Pr2b;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.Reader;

import Pr2b.JavaCC.AnalizadorSintacticoTiny;
import Pr2b.CUP.AnalizadorLexicoTiny;
import Pr2b.CUP.AnalizadorSintacticoTinyCUP;

public class Main {

	public static void main(String[] args) throws Exception {
		Reader input = new InputStreamReader(new FileInputStream(args[0]));
		if (args[1].equalsIgnoreCase("desc")) {
			AnalizadorSintacticoTiny asint = new AnalizadorSintacticoTiny(new FileReader(args[0]));
			asint.ProgramaP();
			System.out.println("OK");
		} else if (args[1].equalsIgnoreCase("asc")) {
			AnalizadorLexicoTiny alex = new AnalizadorLexicoTiny(input);
			AnalizadorSintacticoTinyCUP asint = new AnalizadorSintacticoTinyCUP(alex);
			asint.parse();
			System.out.println("OK");
		}
	}
}
