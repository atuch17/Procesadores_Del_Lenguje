package Pr3a;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import Pr3a.TinyASint.Prog;

public class Main {
	public static void main(String[] args) throws Exception {
		Reader input = new InputStreamReader(new FileInputStream(args[0]));
		ConstructorAST constructorast = new ConstructorAST(input);
		Prog prog = constructorast.Init();
		prog.procesa(new Impresion());
		//prog.procesa(new Evaluacion());
	}
}
