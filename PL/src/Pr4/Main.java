package Pr4;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import Pr4.AST.CUP.AnalizadorLexico;
import Pr4.AST.CUP.ConstructorAST;
import Pr4.AST.TinyASint.Prog;
import Pr4.Procesamientos.Codificacion;
import Pr4.Procesamientos.Espaciado;
import Pr4.Procesamientos.Etiquetado;
import Pr4.Procesamientos.Tipado;
import Pr4.Procesamientos.Vinculacion;
import Pr4.maquinaP.MaquinaP;

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
		Vinculacion v = new Vinculacion();
		prog.procesa(v);
		if (v.algun_error())
			return;
		Tipado t = new Tipado();
		prog.procesa(t);
		if (t.algun_error())
			return;
		prog.procesa(new Espaciado());
		prog.procesa(new Etiquetado());
		Codificacion cod = new Codificacion();
		prog.procesa(cod);
		MaquinaP m = cod.getMaquina();
		m.muestraCodigo();
		m.ejecuta();
		m.muestraEstado();
	}

	private static Prog ejecuta_ascendente(String in) throws Exception {       
		Reader input = new InputStreamReader(new FileInputStream(in));
		AnalizadorLexico alex = new AnalizadorLexico(input);
		ConstructorAST constructorast = new ConstructorAST(alex);
		return (Prog) constructorast.parse().value;
	}
	
	private static Prog ejecuta_descendente(String in) throws Exception {
		Reader input = new InputStreamReader(new FileInputStream(in));
		Pr4.AST.JavaCC.ConstructorAST constructorast = new Pr4.AST.JavaCC.ConstructorAST(input);
		return constructorast.ProgramaP();
	}
}
