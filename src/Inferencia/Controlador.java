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
      
        /*  Pesos y Entradas */
        Kohonen K = new Kohonen();
        K.Entrenamiento();
        K.sincronizacion();
        
        /*  Posterior al Entrenamiento Recuperar los Pesos.     */
        for (double [] peso : K.pesos){
            for (int j = 0; j < peso.length; j++) 
                System.out.print(peso[j]+"\t");
            System.out.println("");
        }
        
        /*  Comprobacion de Inferencia.*/
        for(double[] patron: K.entradas){
        int clases[] = K.Inferencia(patron);
        for(int clase: clases){
            if(clase!=0)
            {
                System.out.println("Clase: "+clase);
                if(K.Mapeo.get(clase)==null)
                    System.out.println("No se ha encontrado una similitud");
                else
                    System.out.println("El deporte es: "+K.Mapeo.get(clase));
            }
        }
}
}
}
   
