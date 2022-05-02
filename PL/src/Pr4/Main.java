package Pr4;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import Pr4.AST.AnalizadorLexico;
import Pr4.AST.ConstructorAST;
import Pr4.AST.TinyASint.Prog;
import Pr4.Procesamientos.Codificacion;
import Pr4.Procesamientos.Espaciado;
import Pr4.Procesamientos.Etiquetado;
import Pr4.Procesamientos.Evaluacion;
import Pr4.Procesamientos.Impresion;
import Pr4.Procesamientos.Tipado;
import Pr4.Procesamientos.Vinculacion;

public class Main {
	public static void main(String[] args) throws Exception {
		Reader input = new InputStreamReader(new FileInputStream(args[0]));
		AnalizadorLexico alex = new AnalizadorLexico(input);
		ConstructorAST constructorast = new ConstructorAST(alex);
		Prog prog = (Prog) constructorast.parse().value;
		prog.procesa(new Impresion());
		prog.procesa(new Evaluacion());
		prog.procesa(new Vinculacion());
		prog.procesa(new Tipado());
		prog.procesa(new Espaciado());
		prog.procesa(new Etiquetado());
		prog.procesa(new Codificacion());
	}
}
