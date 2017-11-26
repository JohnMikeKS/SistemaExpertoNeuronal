package Inferencia;

import java.util.ArrayList;
import java.util.HashMap;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author danie
 */
public class Controlador {

public static void main(String[] args) {
    Controlador c = new Controlador();
    c.parseoArchivoToKohonen();
        /*
        entradas =   {                   {1.0,1.0,0.0,1.0,0.0,0.0,0.0},//Futbol
                                         {1.0,1.0,1.0,0.0,0.0,0.0,0.0},//Americano
                                         {1.0,0.0,0.0,0.0,0.0,1.0,0.0},//Basquetbol
                                         {0.0,0.0,1.0,1.0,1.0,0.0,0.0},//Hokey
                                         {0.0,0.0,1.0,0.0,0.0,0.0,1.0},//Boxeo
                                         {0.0,0.0,0.0,0.0,0.0,0.0,0.0}//Ninguno
                                };
        
       pesos =      {                 {0.4,0.4,0.5,0.4,0.7,0.5,0.5},
                                      {0.4,0.5,0.6,0.4,0.5,0.6,0.5},
                                      {0.5,0.6,0.5,0.4,0.8,0.3,0.5},
                                      {0.5,0.6,0.5,0.4,0.7,0.3,0.5},
                                      {0.5,0.6,0.1,0.1,0.5,0.4,0.5},
                                      {0.5,0.1,0.2,0.1,0.4,0.5,0.5},
                                };
        /*  Pesos y Entradas */
        /*Kohonen K = new Kohonen(pesos, entradas);
        K.Entrenamiento();
        /*  Posterior al Entrenamiento Recuperar los Pesos.     */
        /*
        for (double [] peso : K.pesos)
        {
            for (int j = 0; j < peso.length; j++) 
                System.out.print(peso[j]+"\t");
            System.out.println("");
        }
        */
        /*  Comprobacion de Inferencia.*/
        /*double[] patron = {1.0,0.0,0.0,0.0,0.0,0.0,1.0};
        int clases[] = K.Inferencia(patron);
        for(int clase: clases)
        {
            if(clase!=0)
            System.out.println("Clase: "+clase);
        }
        /*  Fase de Sincronizacion
            **Debido a que la Red crea diferentes clases para cada 
            **patron que se le entrego, es necesario comprobar a
            **que clase esta perteneciendo nuestras reglas.*/
       /* HashMap<Integer,String> Mapeo = new HashMap();
        /*  Se debe tener otro array donde esten indexados los consecuentes. */
        /*int i=0;
        int[] idDeporte;
        
        for(double[] regla: entradas)
        {
            idDeporte = K.Inferencia(regla);
            Mapeo.put(idDeporte[0],sucesores.get(i)); 
            i++;
        } 
        /*  Nuevamente comprobamos que todos los patrones ya se encuentran 
            sincronizados. */
        
            /*idDeporte = K.Inferencia(patron);
            for(int deporte:idDeporte)
                if(deporte!=0)
                    System.out.println("El deporte es: "+Mapeo.get(deporte));
           */ 
}

    ManejoArchivos mA = new ManejoArchivos();
    ArrayList<ArrayList<String>> antecesores;
    ArrayList<String> sucesores;
    ArrayList<String> chido = new ArrayList<>();
    double[][] entradas;
    double[][] pesos;
    Object[] antecesoresC;
    HashMap<String,String> antecesor = new HashMap();
    public void parseoArchivoToKohonen()
    {
        antecesores = mA.recuperaAntecesores("reglas.dat");
        sucesores = mA.recuperaSucesores("reglas.dat");
        
        /*  Recuperar todos los antecesores que se emplearon en las reglas 
            para definir las reglas en patrones de entrada */
        for(int i = 0;i<antecesores.size();i++)
            for(int j=0;j<antecesores.get(i).size();j++)
               antecesor.put(antecesores.get(i).get(j),antecesores.get(i).get(j));
        /*  Estas son las entradas que se entrenaran dentro de la red puesto
            que son la reglas equivalentes que el usario ingreso */
        entradas = new double[antecesores.size()+1][antecesor.size()];
        antecesoresC = antecesor.values().toArray();
        int pos=0;
        for(ArrayList entrada:antecesores){   
            for(Object premisa:entrada){
                for (int i = 0; i < antecesoresC.length; i++) {
                    if(premisa.equals(antecesoresC[i]))
                        entradas[pos][i]=1.0;
                }
            }
            pos++;
        }
        /* Se asignan los pesos de manera aleatoria */
        pesos= new double[entradas.length][entradas[0].length];
        for (int i = 0; i < pesos.length; i++) {
            for (int j = 0; j < pesos[0].length; j++) {
                pesos[i][j]=Math.random();
            }
        }
        System.out.println("");
    }

}
   
