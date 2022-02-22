package Pr1b;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

public class Main {
   public static void main(String[] args) throws FileNotFoundException, IOException {
     Reader input = new InputStreamReader(new FileInputStream("E:/Users/alexc/Documents/GitHub/Procesadores_Del_Lenguje/PL/src/Pr1b/adri.txt"));
     AnalizadorLexicoTiny al = new AnalizadorLexicoTiny(input);
     UnidadLexica unidad;
     do {
       unidad = al.yylex();
       System.out.println(unidad);
     }
     while (unidad.clase() != ClaseLexica.END);
    }        
} 
