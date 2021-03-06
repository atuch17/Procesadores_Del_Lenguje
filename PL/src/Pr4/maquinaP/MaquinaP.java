package Pr4.maquinaP;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

import Pr4.AST.TinyASint.Tipo;


public class MaquinaP {
   public static class EAccesoIlegitimo extends RuntimeException {} 
   public static class EAccesoAMemoriaNoInicializada extends RuntimeException {
      public EAccesoAMemoriaNoInicializada(int pc,int dir) {
         super("pinst:"+pc+" dir:"+dir); 
      } 
   } 
   public static class EAccesoFueraDeRango extends RuntimeException {} 
   
   private GestorMemoriaDinamica gestorMemoriaDinamica;
   private GestorPilaActivaciones gestorPilaActivaciones;
    
   private class Valor {
      public int valorInt() {throw new EAccesoIlegitimo();}
      public double valorReal() {throw new EAccesoIlegitimo();}
      public boolean valorBool() {throw new EAccesoIlegitimo();} 
      public String valorString() {throw new EAccesoIlegitimo();}
   } 
   private class ValorInt extends Valor {
      private int valor;
      public ValorInt(int valor) {
         this.valor = valor; 
      }
      public double valorReal() {return valor;}
      public int valorInt() {return valor;}
      public String toString() {
         return String.valueOf(valor);
      }
   }
   private class ValorReal extends Valor {
      private double valor;
      public ValorReal(double valor) {
         this.valor = valor; 
      }
      public double valorReal() {return valor;}
      public String toString() {
         return String.valueOf(valor);
      }
   }
   private class ValorBool extends Valor {
      private boolean valor;
      public ValorBool(boolean valor) {
         this.valor = valor; 
      }
      public boolean valorBool() {return valor;}
      public String toString() {
         return String.valueOf(valor);
      }
   }
   private class ValorString extends Valor {
      private String valor;
      public ValorString(String valor) {
         this.valor = valor; 
      }
      public String valorString() {return valor;}
      public String toString() {
         return valor;
      }
   }
   private class ValorPuntero extends Valor {
      private int valor;
      public ValorPuntero(int valor) {
         this.valor = valor;
      }
      public int valorInt() {return valor;}
      public String toString() {
         return String.valueOf(valor);
      }
   }

   private List<Instruccion> codigoP;
   private Stack<Valor> pilaEvaluacion;
   private Valor[] datos; 
   private int pc;

   public interface Instruccion {
      void ejecuta();  
   }
   private ISumaI ISUMAI;
   private class ISumaI implements Instruccion {
      public void ejecuta() {
         Valor opnd2 = pilaEvaluacion.pop(); 
         Valor opnd1 = pilaEvaluacion.pop();
         pilaEvaluacion.push(new ValorInt(opnd1.valorInt()+opnd2.valorInt()));
         pc++;
      } 
      public String toString() {return "suma";};
   }
   private ISumaR ISUMAR;
   private class ISumaR implements Instruccion {
      public void ejecuta() {
         Valor opnd2 = pilaEvaluacion.pop(); 
         Valor opnd1 = pilaEvaluacion.pop();
         pilaEvaluacion.push(new ValorReal(opnd1.valorReal()+opnd2.valorReal()));
         pc++;
      } 
      public String toString() {return "suma";};
   }
   private IRestaI IRESTAI;
   private class IRestaI implements Instruccion {
      public void ejecuta() {
         Valor opnd2 = pilaEvaluacion.pop(); 
         Valor opnd1 = pilaEvaluacion.pop();
         pilaEvaluacion.push(new ValorInt(opnd1.valorInt()-opnd2.valorInt()));
         pc++;
      }
      public String toString() {return "resta";};
   }
   private IRestaR IRESTAR;
   private class IRestaR implements Instruccion {
      public void ejecuta() {
         Valor opnd2 = pilaEvaluacion.pop(); 
         Valor opnd1 = pilaEvaluacion.pop();
         pilaEvaluacion.push(new ValorReal(opnd1.valorReal()-opnd2.valorReal()));
         pc++;
      }
      public String toString() {return "resta";};
   }
   private IMulI IMULI;
   private class IMulI implements Instruccion {
      public void ejecuta() {
         Valor opnd2 = pilaEvaluacion.pop(); 
         Valor opnd1 = pilaEvaluacion.pop();
         pilaEvaluacion.push(new ValorInt(opnd1.valorInt()*opnd2.valorInt()));
         pc++;
      } 
      public String toString() {return "mul";};
   }
   private IMulR IMULR;
   private class IMulR implements Instruccion {
      public void ejecuta() {
         Valor opnd2 = pilaEvaluacion.pop(); 
         Valor opnd1 = pilaEvaluacion.pop();
         pilaEvaluacion.push(new ValorReal(opnd1.valorReal()*opnd2.valorReal()));
         pc++;
      } 
      public String toString() {return "mul";};
   }
   private IDivI IDIVI;
   private class IDivI implements Instruccion {
      public void ejecuta() {
         Valor opnd2 = pilaEvaluacion.pop(); 
         Valor opnd1 = pilaEvaluacion.pop();
         pilaEvaluacion.push(new ValorInt(opnd1.valorInt()/opnd2.valorInt()));
         pc++;
      } 
      public String toString() {return "div";};
   }
   private IDivR IDIVR;
   private class IDivR implements Instruccion {
      public void ejecuta() {
         Valor opnd2 = pilaEvaluacion.pop(); 
         Valor opnd1 = pilaEvaluacion.pop();
         pilaEvaluacion.push(new ValorReal(opnd1.valorReal()/opnd2.valorReal()));
         pc++;
      } 
      public String toString() {return "div";};
   }
   private IMod IMOD;
   private class IMod implements Instruccion {
      public void ejecuta() {
         Valor opnd2 = pilaEvaluacion.pop(); 
         Valor opnd1 = pilaEvaluacion.pop();
         pilaEvaluacion.push(new ValorInt(opnd1.valorInt()%opnd2.valorInt()));
         pc++;
      } 
      public String toString() {return "mod";};
   }
   private IMenosI IMENOSI;
   private class IMenosI implements Instruccion {
      public void ejecuta() {
         Valor opnd1 = pilaEvaluacion.pop();
         pilaEvaluacion.push(new ValorInt(-opnd1.valorInt()));
         pc++;
      }
      public String toString() {return "menos";};
   }
   private IMenosR IMENOSR;
   private class IMenosR implements Instruccion {
      public void ejecuta() {
         Valor opnd1 = pilaEvaluacion.pop();
         pilaEvaluacion.push(new ValorReal(-opnd1.valorReal()));
         pc++;
      }
      public String toString() {return "menos";};
   }
   private IAnd IAND;
   private class IAnd implements Instruccion {
      public void ejecuta() {
         Valor opnd2 = pilaEvaluacion.pop(); 
         Valor opnd1 = pilaEvaluacion.pop();
         pilaEvaluacion.push(new ValorBool(opnd1.valorBool()&&opnd2.valorBool()));
         pc++;
      } 
      public String toString() {return "and";};
   }
   private IOr IOR;
   private class IOr implements Instruccion {
      public void ejecuta() {
         Valor opnd2 = pilaEvaluacion.pop(); 
         Valor opnd1 = pilaEvaluacion.pop();
         pilaEvaluacion.push(new ValorBool(opnd1.valorBool()||opnd2.valorBool()));
         pc++;
      } 
      public String toString() {return "or";};
   }
   private INot INOT;
   private class INot implements Instruccion {
      public void ejecuta() {
         Valor opnd1 = pilaEvaluacion.pop();
         pilaEvaluacion.push(new ValorBool(!opnd1.valorBool()));
         pc++;
      } 
      public String toString() {return "not";};
   }
   private IMayorR IMAYORR;
   private class IMayorR implements Instruccion {
      public void ejecuta() {
         Valor opnd2 = pilaEvaluacion.pop(); 
         Valor opnd1 = pilaEvaluacion.pop();
         pilaEvaluacion.push(new ValorBool(opnd1.valorReal()>opnd2.valorReal()));
         pc++;
      } 
      public String toString() {return "mayor";};
   }
   private IMayorB IMAYORB;
   private class IMayorB implements Instruccion {
      public void ejecuta() {
         Valor opnd2 = pilaEvaluacion.pop(); 
         Valor opnd1 = pilaEvaluacion.pop();
         pilaEvaluacion.push(new ValorBool(Boolean.compare(opnd1.valorBool(), opnd2.valorBool())>0));
         pc++;
      } 
      public String toString() {return "mayor";};
   }
   private IMayorS IMAYORS;
   private class IMayorS implements Instruccion {
      public void ejecuta() {
         Valor opnd2 = pilaEvaluacion.pop(); 
         Valor opnd1 = pilaEvaluacion.pop();
         pilaEvaluacion.push(new ValorBool(opnd1.valorString().compareTo(opnd2.valorString())>0));
         pc++;
      } 
      public String toString() {return "mayor";};
   }
   private IMayorIgR IMAYORIGR;
   private class IMayorIgR implements Instruccion {
      public void ejecuta() {
         Valor opnd2 = pilaEvaluacion.pop(); 
         Valor opnd1 = pilaEvaluacion.pop();
         pilaEvaluacion.push(new ValorBool(opnd1.valorReal()>=opnd2.valorReal()));
         pc++;
      } 
      public String toString() {return "mayorig";};
   }
   private IMayorIgB IMAYORIGB;
   private class IMayorIgB implements Instruccion {
      public void ejecuta() {
         Valor opnd2 = pilaEvaluacion.pop(); 
         Valor opnd1 = pilaEvaluacion.pop();
         pilaEvaluacion.push(new ValorBool(Boolean.compare(opnd1.valorBool(), opnd2.valorBool())>=0));
         pc++;
      } 
      public String toString() {return "mayorig";};
   }
   private IMayorIgS IMAYORIGS;
   private class IMayorIgS implements Instruccion {
      public void ejecuta() {
         Valor opnd2 = pilaEvaluacion.pop(); 
         Valor opnd1 = pilaEvaluacion.pop();
         pilaEvaluacion.push(new ValorBool(opnd1.valorString().compareTo(opnd2.valorString())>=0));
         pc++;
      } 
      public String toString() {return "mayorig";};
   }
   private IMenorR IMENORR;
   private class IMenorR implements Instruccion {
      public void ejecuta() {
         Valor opnd2 = pilaEvaluacion.pop(); 
         Valor opnd1 = pilaEvaluacion.pop();
         pilaEvaluacion.push(new ValorBool(opnd1.valorReal()<opnd2.valorReal()));
         pc++;
      }
      public String toString() {return "menor";};
   }
   private IMenorB IMENORB;
   private class IMenorB implements Instruccion {
      public void ejecuta() {
         Valor opnd2 = pilaEvaluacion.pop(); 
         Valor opnd1 = pilaEvaluacion.pop();
         pilaEvaluacion.push(new ValorBool(Boolean.compare(opnd1.valorBool(), opnd2.valorBool())<0));
         pc++;
      } 
      public String toString() {return "menor";};
   }
   private IMenorS IMENORS;
   private class IMenorS implements Instruccion {
      public void ejecuta() {
         Valor opnd2 = pilaEvaluacion.pop(); 
         Valor opnd1 = pilaEvaluacion.pop();
         pilaEvaluacion.push(new ValorBool(opnd1.valorString().compareTo(opnd2.valorString())<0));
         pc++;
      } 
      public String toString() {return "menor";};
   }
   private IMenorIgR IMENORIGR;
   private class IMenorIgR implements Instruccion {
      public void ejecuta() {
         Valor opnd2 = pilaEvaluacion.pop(); 
         Valor opnd1 = pilaEvaluacion.pop();
         pilaEvaluacion.push(new ValorBool(opnd1.valorReal()<=opnd2.valorReal()));
         pc++;
      } 
      public String toString() {return "menorig";};
   }
   private IMenorIgB IMENORIGB;
   private class IMenorIgB implements Instruccion {
      public void ejecuta() {
         Valor opnd2 = pilaEvaluacion.pop(); 
         Valor opnd1 = pilaEvaluacion.pop();
         pilaEvaluacion.push(new ValorBool(Boolean.compare(opnd1.valorBool(), opnd2.valorBool())<=0));
         pc++;
      } 
      public String toString() {return "menorig";};
   }
   private IMenorIgS IMENORIGS;
   private class IMenorIgS implements Instruccion {
      public void ejecuta() {
         Valor opnd2 = pilaEvaluacion.pop(); 
         Valor opnd1 = pilaEvaluacion.pop();
         pilaEvaluacion.push(new ValorBool(opnd1.valorString().compareTo(opnd2.valorString())<=0));
         pc++;
      } 
      public String toString() {return "menorig";};
   }
   private ICompR ICOMPR;
   private class ICompR implements Instruccion {
      public void ejecuta() {
         Valor opnd2 = pilaEvaluacion.pop(); 
         Valor opnd1 = pilaEvaluacion.pop();
         pilaEvaluacion.push(new ValorBool(opnd1.valorReal()==opnd2.valorReal()));
         pc++;
      } 
      public String toString() {return "comp";};
   }
   private ICompB ICOMPB;
   private class ICompB implements Instruccion {
      public void ejecuta() {
         Valor opnd2 = pilaEvaluacion.pop(); 
         Valor opnd1 = pilaEvaluacion.pop();
         pilaEvaluacion.push(new ValorBool(Boolean.compare(opnd1.valorBool(), opnd2.valorBool())==0));
         pc++;
      } 
      public String toString() {return "comp";};
   }
   private ICompS ICOMPS;
   private class ICompS implements Instruccion {
      public void ejecuta() {
         Valor opnd2 = pilaEvaluacion.pop(); 
         Valor opnd1 = pilaEvaluacion.pop();
         pilaEvaluacion.push(new ValorBool(opnd1.valorString().compareTo(opnd2.valorString())==0));
         pc++;
      } 
      public String toString() {return "comp";};
   }
   private ICompP ICOMPP;
   private class ICompP implements Instruccion {
      public void ejecuta() {
         Valor opnd2 = pilaEvaluacion.pop(); 
         Valor opnd1 = pilaEvaluacion.pop();
         pilaEvaluacion.push(new ValorBool(opnd1.valorInt()==opnd2.valorInt()));
         pc++;
      } 
      public String toString() {return "comp";};
   }
   private IDistR IDISTR;
   private class IDistR implements Instruccion {
      public void ejecuta() {
         Valor opnd2 = pilaEvaluacion.pop(); 
         Valor opnd1 = pilaEvaluacion.pop();
         pilaEvaluacion.push(new ValorBool(opnd1.valorInt()!=opnd2.valorInt()));
         pc++;
      } 
      public String toString() {return "dist";};
   }
   private IDistB IDISTB;
   private class IDistB implements Instruccion {
      public void ejecuta() {
         Valor opnd2 = pilaEvaluacion.pop(); 
         Valor opnd1 = pilaEvaluacion.pop();
         pilaEvaluacion.push(new ValorBool(Boolean.compare(opnd1.valorBool(), opnd2.valorBool())!=0));
         pc++;
      } 
      public String toString() {return "dist";};
   }
   private IDistS IDISTS;
   private class IDistS implements Instruccion {
      public void ejecuta() {
         Valor opnd2 = pilaEvaluacion.pop(); 
         Valor opnd1 = pilaEvaluacion.pop();
         pilaEvaluacion.push(new ValorBool(opnd1.valorString().compareTo(opnd2.valorString())!=0));
         pc++;
      } 
      public String toString() {return "dist";};
   }
   private IDistP IDISTP;
   private class IDistP implements Instruccion {
      public void ejecuta() {
         Valor opnd2 = pilaEvaluacion.pop(); 
         Valor opnd1 = pilaEvaluacion.pop();
         pilaEvaluacion.push(new ValorBool(opnd1.valorInt()!=opnd2.valorInt()));
         pc++;
      } 
      public String toString() {return "dist";};
   }

   private class IApilaInt implements Instruccion {
      private int valor;
      public IApilaInt(int valor) {
        this.valor = valor;  
      }
      public void ejecuta() {
         pilaEvaluacion.push(new ValorInt(valor)); 
         pc++;
      } 
      public String toString() {return "apilaInt("+valor+")";};
   }

   private class IApilaReal implements Instruccion {
      private double valor;
      public IApilaReal(double valor) {
        this.valor = valor;  
      }
      public void ejecuta() {
         pilaEvaluacion.push(new ValorReal(valor)); 
         pc++;
      } 
      public String toString() {return "apilaReal("+valor+")";};
   }

   private class IApilaBool implements Instruccion {
      private boolean valor;
      public IApilaBool(boolean valor) {
        this.valor = valor;  
      }
      public void ejecuta() {
         pilaEvaluacion.push(new ValorBool(valor)); 
         pc++;
      } 
      public String toString() {return "apilaBool("+valor+")";};
   }

   private class IApilaString implements Instruccion {
      private String valor;
      public IApilaString(String valor) {
        this.valor = valor;  
      }
      public void ejecuta() {
         pilaEvaluacion.push(new ValorString(valor)); 
         pc++;
      } 
      public String toString() {return "apilaString("+valor+")";};
   }

   private class IApilaPuntero implements Instruccion {
      private int valor;
      public IApilaPuntero(int valor) {
        this.valor = valor;  
      }
      public void ejecuta() {
         pilaEvaluacion.push(new ValorPuntero(valor)); 
         pc++;
      } 
      public String toString() {return "apilaPuntero("+valor+")";};
   }

   private class IIrA implements Instruccion {
      private int dir;
      public IIrA(int dir) {
        this.dir = dir;  
      }
      public void ejecuta() {
            pc=dir;
      } 
      public String toString() {return "ira("+dir+")";};
   }

   private class IIrF implements Instruccion {
      private int dir;
      public IIrF(int dir) {
        this.dir = dir;  
      }
      public void ejecuta() {
         if(! pilaEvaluacion.pop().valorBool()) { 
            pc=dir;
         }   
         else {
            pc++; 
         }
      } 
      public String toString() {return "irf("+dir+")";};
   }
   
   private class IMueve implements Instruccion {
      private int tam;
      public IMueve(int tam) {
        this.tam = tam;  
      }
      public void ejecuta() {
            int dirOrigen = pilaEvaluacion.pop().valorInt();
            int dirDestino = pilaEvaluacion.pop().valorInt();
            if ((dirOrigen + (tam-1)) >= datos.length)
                throw new EAccesoFueraDeRango();
            if ((dirDestino + (tam-1)) >= datos.length)
                throw new EAccesoFueraDeRango();
            for (int i=0; i < tam; i++) 
                datos[dirDestino+i] = datos[dirOrigen+i]; 
            pc++;
      } 
      public String toString() {return "mueve("+tam+")";};
   }
   
   private IApilaind IAPILAIND;
   private class IApilaind implements Instruccion {
      public void ejecuta() {
        int dir = pilaEvaluacion.pop().valorInt();
        if (dir >= datos.length) throw new EAccesoFueraDeRango();
        if (dir == -1) throw new EAccesoFueraDeRango();
        if (datos[dir] == null)  throw new EAccesoAMemoriaNoInicializada(pc,dir);
        pilaEvaluacion.push(datos[dir]);
        pc++;
      } 
      public String toString() {return "apilaind";};
   }

   private IDesapilaind IDESAPILAIND;
   private class IDesapilaind implements Instruccion {
      public void ejecuta() {
        Valor valor = pilaEvaluacion.pop();
        int dir = pilaEvaluacion.pop().valorInt();
        if (dir >= datos.length) throw new EAccesoFueraDeRango();
        datos[dir] = valor;
        pc++;
      } 
      public String toString() {return "desapilaind";};
   }

   private class IAlloc implements Instruccion {
      private int tam;
      public IAlloc(int tam) {
        this.tam = tam;  
      }
      public void ejecuta() {
        int inicio = gestorMemoriaDinamica.alloc(tam);
        pilaEvaluacion.push(new ValorPuntero(inicio));
        pc++;
      } 
      public String toString() {return "alloc("+tam+")";};
   }

   private class IDealloc implements Instruccion {
      private int tam;
      public IDealloc(int tam) {
        this.tam = tam;  
      }
      public void ejecuta() {
        int inicio = pilaEvaluacion.pop().valorInt();
        gestorMemoriaDinamica.free(inicio,tam);
        pc++;
      } 
      public String toString() {return "dealloc("+tam+")";};
   }
   
   private class IActiva implements Instruccion {
       private int nivel;
       private int tamdatos;
       private int dirretorno;
       public IActiva(int nivel, int tamdatos, int dirretorno) {
           this.nivel = nivel;
           this.tamdatos = tamdatos;
           this.dirretorno = dirretorno;
       }
       public void ejecuta() {
          int base = gestorPilaActivaciones.creaRegistroActivacion(tamdatos);
          datos[base] = new ValorInt(dirretorno);
          datos[base+1] = new ValorInt(gestorPilaActivaciones.display(nivel));
          pilaEvaluacion.push(new ValorInt(base+2));
          pc++;
       }
       public String toString() {
          return "activa("+nivel+","+tamdatos+","+dirretorno+")";                 
       }
   }
   
   private class IDesactiva implements Instruccion {
       private int nivel;
       private int tamdatos;
       public IDesactiva(int nivel, int tamdatos) {
           this.nivel = nivel;
           this.tamdatos = tamdatos;
       }
       public void ejecuta() {
          int base = gestorPilaActivaciones.liberaRegistroActivacion(tamdatos);
          gestorPilaActivaciones.fijaDisplay(nivel,datos[base+1].valorInt());
          pilaEvaluacion.push(datos[base]); 
          pc++;
       }
       public String toString() {
          return "desactiva("+nivel+","+tamdatos+")";                 
       }

   }
   
   private class IDesapilad implements Instruccion {
       private int nivel;
       public IDesapilad(int nivel) {
         this.nivel = nivel;  
       }
       public void ejecuta() {
         gestorPilaActivaciones.fijaDisplay(nivel,pilaEvaluacion.pop().valorInt());  
         pc++;
       }
       public String toString() {
          return "desapilad("+nivel+")";                 
       }
   }
   private IDup IDUP;
   private class IDup implements Instruccion {
       public void ejecuta() {
           pilaEvaluacion.push(pilaEvaluacion.peek());
           pc++;
       }
       public String toString() {
          return "dup";                 
       }
   }
   private Instruccion ISTOP;
   private class IStop implements Instruccion {
       public void ejecuta() {
           pc = codigoP.size();
       }
       public String toString() {
          return "stop";                 
       }
   }
   
   
   private class IApilad implements Instruccion {
      private int nivel;
      public IApilad(int nivel) {
         this.nivel = nivel;
      }
      public void ejecuta() {
         pilaEvaluacion.push(new ValorInt(gestorPilaActivaciones.display(nivel)));
         pc++;
      }
      public String toString() {
         return "apilad("+nivel+")";                 
      }

   }
   
   private Instruccion IIRIND;
   private class IIrind implements Instruccion {
       public void ejecuta() {
          pc = pilaEvaluacion.pop().valorInt();  
       }
       public String toString() {
          return "irind";                 
       }
   }

   private class ILee implements Instruccion {
      private Tipo tipo;
      public ILee(Tipo tipo) {
         this.tipo = tipo;  
      }
      public void ejecuta() {
         if (tipo.es_entero()) {
            int i = in.nextInt();
            pilaEvaluacion.push(new ValorInt(i));
         } else if (tipo.es_real()) {
            double d = in.nextDouble();
            pilaEvaluacion.push(new ValorReal(d));
         } else {
            String s = in.nextLine();
            while(s.equals("")){
               s = in.nextLine();
            }
            pilaEvaluacion.push(new ValorString(s));
         }
         pc++;
      }
      public String toString() {
         return "lee";
      }
   }

   private Instruccion IIMPRIME;
   private class IImprime implements Instruccion {
      public void ejecuta() {
         System.out.println(pilaEvaluacion.pop());
         pc++;
      }
      public String toString() {
         return "imprime";
      }
   }

   private Instruccion INEWLINE;
   private class INewLine implements Instruccion {
      public void ejecuta() {
         System.out.println();
         pc++;
      }
      public String toString() {
         return "newline";
      }
   }

   public Instruccion sumai() {return ISUMAI;}
   public Instruccion sumar() {return ISUMAR;}
   public Instruccion restai() {return IRESTAI;}
   public Instruccion restar() {return IRESTAR;}
   public Instruccion muli() {return IMULI;}
   public Instruccion mulr() {return IMULR;}
   public Instruccion divi() {return IDIVI;}
   public Instruccion divr() {return IDIVR;}
   public Instruccion mod() {return IMOD;}
   public Instruccion menosi() {return IMENOSI;}
   public Instruccion menosr() {return IMENOSR;}
   public Instruccion and() {return IAND;}
   public Instruccion or() {return IOR;}
   public Instruccion not() {return INOT;}
   public Instruccion mayorr() {return IMAYORR;}
   public Instruccion mayorb() {return IMAYORB;}
   public Instruccion mayors() {return IMAYORS;}
   public Instruccion mayorigr() {return IMAYORIGR;}
   public Instruccion mayorigb() {return IMAYORIGB;}
   public Instruccion mayorigs() {return IMAYORIGS;}
   public Instruccion menorr() {return IMENORR;}
   public Instruccion menorb() {return IMENORB;}
   public Instruccion menors() {return IMENORS;}
   public Instruccion menorigr() {return IMENORIGR;}
   public Instruccion menorigb() {return IMENORIGB;}
   public Instruccion menorigs() {return IMENORIGS;}
   public Instruccion compr() {return ICOMPR;}
   public Instruccion compb() {return ICOMPB;}
   public Instruccion comps() {return ICOMPS;}
   public Instruccion compp() {return ICOMPP;}
   public Instruccion distr() {return IDISTR;}
   public Instruccion distb() {return IDISTB;}
   public Instruccion dists() {return IDISTS;}
   public Instruccion distp() {return IDISTP;}
   public Instruccion apilaInt(int val) {return new IApilaInt(val);}
   public Instruccion apilaReal(double val) {return new IApilaReal(val);}
   public Instruccion apilaBool(boolean val) {return new IApilaBool(val);}
   public Instruccion apilaString(String val) {return new IApilaString(val);}
   public Instruccion apilaPuntero(int val) {return new IApilaPuntero(val);}
   public Instruccion apilad(int nivel) {return new IApilad(nivel);}
   public Instruccion apilaInd() {return IAPILAIND;}
   public Instruccion desapilaInd() {return IDESAPILAIND;}
   public Instruccion mueve(int tam) {return new IMueve(tam);}
   public Instruccion irA(int dir) {return new IIrA(dir);}
   public Instruccion irF(int dir) {return new IIrF(dir);}
   public Instruccion irInd() {return IIRIND;}
   public Instruccion alloc(int tam) {return new IAlloc(tam);} 
   public Instruccion dealloc(int tam) {return new IDealloc(tam);} 
   public Instruccion activa(int nivel,int tam, int dirretorno) {return new IActiva(nivel,tam,dirretorno);}
   public Instruccion desactiva(int nivel, int tam) {return new IDesactiva(nivel,tam);}
   public Instruccion desapilad(int nivel) {return new IDesapilad(nivel);}
   public Instruccion dup() {return IDUP;}
   public Instruccion stop() {return ISTOP;}
   public Instruccion lee(Tipo tipo) {return new ILee(tipo);}
   public Instruccion imprime() {return IIMPRIME;}
   public Instruccion newline() {return INEWLINE;}
   public void ponInstruccion(Instruccion i) {
      codigoP.add(i); 
   }

   private int tamdatos;
   private int tamheap;
   private int ndisplays;
   private Scanner in;
   public MaquinaP(int tamdatos, int tampila, int tamheap, int ndisplays) {
      this.tamdatos = tamdatos;
      this.tamheap = tamheap;
      this.ndisplays = ndisplays;
      this.codigoP = new ArrayList<>();  
      pilaEvaluacion = new Stack<>();
      datos = new Valor[tamdatos+tampila+tamheap];
      this.pc = 0;

      ISUMAI = new ISumaI();
      ISUMAR = new ISumaR();
      IRESTAI = new IRestaI();
      IRESTAR = new IRestaR();
      IMULI = new IMulI();
      IMULR = new IMulR();
      IDIVI = new IDivI();
      IDIVR = new IDivR();
      IMOD = new IMod();
      IMENOSI = new IMenosI();
      IMENOSR = new IMenosR();
      IAND = new IAnd();
      IOR = new IOr();
      INOT = new INot();
      IMAYORR = new IMayorR();
      IMAYORB = new IMayorB();
      IMAYORS = new IMayorS();
      IMAYORIGR = new IMayorIgR();
      IMAYORIGB = new IMayorIgB();
      IMAYORIGS = new IMayorIgS();
      IMENORR = new IMenorR();
      IMENORB = new IMenorB();
      IMENORS = new IMenorS();
      IMENORIGR = new IMenorIgR();
      IMENORIGB = new IMenorIgB();
      IMENORIGS = new IMenorIgS();
      ICOMPR = new ICompR();
      ICOMPB = new ICompB();
      ICOMPS = new ICompS();
      ICOMPP = new ICompP();
      IDISTR = new IDistR();
      IDISTB = new IDistB();
      IDISTS = new IDistS();
      IDISTP = new IDistP();

      IAPILAIND = new IApilaind();
      IDESAPILAIND = new IDesapilaind();
      IIRIND = new IIrind();
      IDUP = new IDup();
      ISTOP = new IStop();
      IIMPRIME = new IImprime();
      INEWLINE = new INewLine();
      
      gestorPilaActivaciones = new GestorPilaActivaciones(tamdatos,(tamdatos+tampila)-1,ndisplays); 
      gestorMemoriaDinamica = new GestorMemoriaDinamica(tamdatos+tampila,(tamdatos+tampila+tamheap)-1);
   }
   public void ejecuta() {
      in = new Scanner(System.in);
      while(pc != codigoP.size()) {
          codigoP.get(pc).ejecuta();
      }
      in.close();
   }
   public void muestraCodigo() {
     System.out.println("CodigoP");
     for(int i=0; i < codigoP.size(); i++) {
        System.out.println(" "+i+":"+codigoP.get(i));
     }
   }
   public void muestraEstado() {
     System.out.println("Tam datos:"+tamdatos);  
     System.out.println("Tam heap:"+tamheap); 
     System.out.println("PP:"+gestorPilaActivaciones.pp());      
     System.out.print("Displays:");
     for (int i=1; i <= ndisplays; i++)
         System.out.print(i+":"+gestorPilaActivaciones.display(i)+" ");
     System.out.println();
     System.out.println("Pila de evaluacion");
     for(int i=0; i < pilaEvaluacion.size(); i++) {
        System.out.println(" "+i+":"+pilaEvaluacion.get(i));
     }
     System.out.println("Datos");
     for(int i=0; i < datos.length; i++) {
        System.out.println(" "+i+":"+datos[i]);
     }
     System.out.println("PC:"+pc);
   }
   
   public static void main(String[] args) {
       MaquinaP m = new MaquinaP(5,10,10,2);
        
          /*
            int x;
            proc store(int v) {
             x = v
            }
           &&
            call store(5)
          */
            
       
       m.ponInstruccion(m.activa(1,1,8));
       m.ponInstruccion(m.dup());
       m.ponInstruccion(m.apilaInt(0));
       m.ponInstruccion(m.sumai());
       m.ponInstruccion(m.apilaInt(5));
       m.ponInstruccion(m.desapilaInd());
       m.ponInstruccion(m.desapilad(1));
       m.ponInstruccion(m.irA(9));
       m.ponInstruccion(m.stop());
       m.ponInstruccion(m.apilaInt(0));
       m.ponInstruccion(m.apilad(1));
       m.ponInstruccion(m.apilaInt(0));
       m.ponInstruccion(m.sumai());
       m.ponInstruccion(m.mueve(1));
       m.ponInstruccion(m.desactiva(1,1));
       m.ponInstruccion(m.irInd());       
       m.ejecuta();
       m.muestraEstado();
   }
}
