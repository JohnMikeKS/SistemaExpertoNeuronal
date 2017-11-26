import Inferencia.Backpropagation;
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author danie
 */
public class NewClass {
    
        public static void main(String[] args)
{
    
    
    Backpropagation p = new Backpropagation();
    
    ArrayList<String> antecedentes = new ArrayList();
    antecedentes.add(0,"X");
    antecedentes.add(1,"Y");
    antecedentes.add(2,"X");
    antecedentes.add(3,"X");
    antecedentes.add(4,"Y");
    p.setAntecedentes(antecedentes);
    
    ArrayList<String> reglas = new ArrayList();
    reglas.add(0,"H1");
    reglas.add(1,"H2");
    reglas.add(2,"H3");
    p.setReglas(reglas);
    
    ArrayList<String> consecuentes =  new ArrayList();
    consecuentes.add(0,"S1");
    consecuentes.add(1,"S2");
    consecuentes.add(2,"S3");
    p.setConsecuentes(consecuentes);
    
    
    /*
    //Definir el numero de premisas (A,B,C,D,E) Antecedentes
    ArrayList<String> antecedentes = new ArrayList();
    antecedentes.add(0,"Portero");
    antecedentes.add(1,"Balon");
    antecedentes.add(2,"Campo");
    antecedentes.add(3,"Arbitro");
    antecedentes.add(4,"Eq Proteccion");
    p.setAntecedentes(antecedentes);
    
    //Definir los consecuentes
    ArrayList<String> consecuentes =  new ArrayList();
    consecuentes.add(0,"Futbol");
    consecuentes.add(1,"Basquetbol");
    consecuentes.add(2,"Americano");
    consecuentes.add(3,"Boxeo");
    consecuentes.add(4,"Hokey");
    p.setConsecuentes(consecuentes);
    
    //Definir las reglas (Patrones)
    ArrayList<String> reglas = new ArrayList();
    reglas.add("Portero ^ Balon ^ Campo ^ Arbitro > Futbol");
    reglas.add("Balon ^ Arbitro  > Basquetbol");
    reglas.add("Balon ^ Campo ^ Arbitro ^ Eq Proteccion > Americano");
    reglas.add("Arbitro ^ Eq Proteccion > Boxeo");
    reglas.add("Portero ^ Arbitro ^ Eq Proteccion > Hokey");
    p.setReglas(reglas);
    //Si A:B:C:D entonces E
   */
    
    p.iniciaPesos();
    p.iniciaThetas();
    p.inicia();
        int epoca=0;
        double sumSqrError=0;
        
        int F[] = {1,1,1,-1,1};
        int sF[] = {1,-1,-2,-1,-1};
        
        int Bx[] = {-1,-1,1,1,0};
        int sBx[] = {-1,1,-1,-1,-1};
        
        int A[] = {1,1,1,1,-1};
        int sA[] = {-1,-1,1,-1,-1};
        
        int H[] = {-1,-1,1,1,1};
        int sH[] = {-1,-1,-1,1,-1};
        
        int B[] = {1,-1,1,-1,-1};
        int sB[] = {-1,-1,-1,-1,1};
        
        
        //ACTIVAR PARA EL EJERCICIO DE EXCEL EN EL LIBRO
        /*
        int F[] = {1,0,1,1,0};
        int sF[] = {1,0,0};
        
        int Bx[] = {1,0,0,0,1};
        int sBx[] = {0,0,1};
        
        int A[] = {0,1};
        int sA[] = {1};
        
        int H[] = {0,0};
        int sH[] = {0};
        */
    do
    {
        //SIN MOMENTOS
        //62463
        //CON MOMENTOS 0.99
        //6911
        p.calSalidas(F,sF);
        for (int i = 0; i < p.errorSalida.length; i++) {
            sumSqrError = p.errorSalida[0]*p.errorSalida[0];
        }
        
        p.calSalidas(Bx,sBx);
        for (int i = 0; i < p.errorSalida.length; i++) {
            sumSqrError += p.errorSalida[0]*p.errorSalida[0];
        }
        p.calSalidas(A,sA);
        for (int i = 0; i < p.errorSalida.length; i++) {
            sumSqrError += p.errorSalida[0]*p.errorSalida[0];
        }
        p.calSalidas(H,sH);
        for (int i = 0; i < p.errorSalida.length; i++) {
            sumSqrError += p.errorSalida[0]*p.errorSalida[0];
        }
        
        p.calSalidas(B,sB);
        for (int i = 0; i < p.errorSalida.length; i++) {
            sumSqrError += p.errorSalida[i]*p.errorSalida[i];
        }
        
        sumSqrError = sumSqrError/2;
        epoca++;
        System.out.println(sumSqrError+"\t"+epoca);
    }while(sumSqrError >0.001);
    for(Double peso: p.pesos)
    {
        System.out.println(peso);
    }
    System.out.println("");
    for(Double theta: p.thetas)
    {
        System.out.println(theta);
    }
    
}
    }
   
