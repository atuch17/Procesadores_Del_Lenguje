package Pr3b;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import Pr3b.CUP.AnalizadorLexico;
import Pr3b.CUP.ConstructorAST;
import Pr3b.TinyASint.Prog;

public class Main {
	public static void main(String[] args) throws Exception {
		Prog prog = null;
		if (args[1].equals("asc"))
			prog = ejecuta_ascendente(args[0]);
		else if (args[1].equals("desc"))
			prog = ejecuta_descendente(args[0]);
		else {
			System.out.println("Error en los argumentos");
			System.exit(1);
		}
		prog.procesa(new Impresion());
	}

	private static Prog ejecuta_ascendente(String in) throws Exception {       
		Reader input = new InputStreamReader(new FileInputStream(in));
		AnalizadorLexico alex = new AnalizadorLexico(input);
		ConstructorAST constructorast = new ConstructorAST(alex);
		return (Prog) constructorast.parse().value;
	}
	
	private static Prog ejecuta_descendente(String in) throws Exception {
		Reader input = new InputStreamReader(new FileInputStream(in));
		Pr3b.JavaCC.ConstructorAST constructorast = new Pr3b.JavaCC.ConstructorAST(input);
		return constructorast.ProgramaP(); //Init();
	}
}
