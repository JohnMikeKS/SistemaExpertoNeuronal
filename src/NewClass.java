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
    reglas.add(0,"Portero ^ Balon ^ Campo ^ Arbitro > Futbol");
    reglas.add(1,"Balon ^ Arbitro  > Basquetbol");
    reglas.add(2,"Balon ^ Campo ^ Arbitro ^ Eq Proteccion > Americano");
    reglas.add(3,"Arbitro ^ Eq Proteccion > Boxeo");
    reglas.add(4,"Portero ^ Arbitro ^ Eq Proteccion > Hokey");
    p.setReglas(reglas);
    //Si A:B:C:D entonces E
   
    
    p.iniciaPesos();
    p.iniciaThetas();
    p.inicia();
        int epoca=0;
        double sumSqrError=0;
        int F[] = {1,1,1,0,1};
        int sF[] = {1,0,0,0,0};
        int Bx[] = {0,0,1,1,0};
        int sBx[] = {0,1,0,0,0};
        int A[] = {1,1,1,1,0};
        int sA[] = {0,0,1,0,0};
        int H[] = {0,0,1,1,1};
        int sH[] = {0,0,0,1,0};
        int B[] = {1,0,1,0,0};
        int sB[] = {0,0,0,0,1};
        
    do
    {
        System.out.println("");
        p.calSalidas(F,sF);
        for (int i = 0; i < 5; i++) {
            sumSqrError += p.error[i]*p.error[i];
        }
        p.calSalidas(Bx,sBx);
        for (int i = 0; i < 5; i++) {
            sumSqrError += p.error[i]*p.error[i];
        }
        p.calSalidas(A,sA);
        for (int i = 0; i < 5; i++) {
            sumSqrError += p.error[i]*p.error[i];
        }
        p.calSalidas(H,sH);
        for (int i = 0; i < 5; i++) {
            sumSqrError += p.error[i]*p.error[i];
        }
        p.calSalidas(B,sB);
        for (int i = 0; i < 5; i++) {
            sumSqrError += p.error[i]*p.error[i];
        }
        
        sumSqrError = sumSqrError/2;
        epoca++;
        System.out.println(sumSqrError);
    }while(sumSqrError > 0.001);

}

    }
   
