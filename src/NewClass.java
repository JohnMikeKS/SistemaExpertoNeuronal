
import Inferencia.Kohonen;
import Inferencia.ManejoArchivos;
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
public class NewClass {

//PRUEBA KOHONEN
public  void main(String[] args) {
        /*
        entradas =   {        {1.0,1.0,0.0,1.0,0.0,0.0,0.0},//Futbol
                                         {1.0,1.0,1.0,0.0,0.0,0.0,0.0},//Americano
                                         {1.0,0.0,0.0,0.0,0.0,1.0,0.0},//Basquetbol
                                         {0.0,0.0,1.0,1.0,1.0,0.0,0.0},//Hokey
                                         {0.0,0.0,1.0,0.0,0.0,0.0,1.0},//Boxeo
                                         {0.0,0.0,0.0,0.0,0.0,0.0,0.0}//Ninguno
                                };
        */
        double[][] pesos =      {     {0.4,0.4,0.5,0.4,0.7,0.5,0.5},
                                      {0.4,0.5,0.6,0.4,0.5,0.6,0.5},
                                      {0.5,0.6,0.5,0.4,0.8,0.3,0.5},
                                      {0.5,0.6,0.5,0.4,0.7,0.3,0.5},
                                      {0.5,0.6,0.1,0.1,0.5,0.4,0.5},
                                      {0.5,0.1,0.2,0.1,0.4,0.5,0.5},
                                };
        /*  Pesos y Entradas */
        Kohonen K = new Kohonen(pesos, entradas);
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
        double[] patron = {1.0,0.0,0.0,0.0,0.0,0.0,1.0};
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
        HashMap<Integer,String> Mapeo = new HashMap();
        /*  Se debe tener otro array donde esten indexados los consecuentes. */
        int i=0;
        int[] idDeporte;
        
        for(double[] regla: entradas)
        {
            idDeporte = K.Inferencia(regla);
            Mapeo.put(idDeporte[0],sucesores.get(i)); 
            i++;
        }
        /*  Nuevamente comprobamos que todos los patrones ya se encuentran 
            sincronizados. */
        
            idDeporte = K.Inferencia(patron);
            for(int deporte:idDeporte)
                if(deporte!=0)
                    System.out.println("El deporte es: "+Mapeo.get(deporte));
            
}

    ManejoArchivos mA = new ManejoArchivos();
    ArrayList<ArrayList<String>> antecesores;
    ArrayList<String> sucesores;
    ArrayList<String> chido = new ArrayList<>();
    double[][] entradas;
    public void parseoArchivoToKohonen()
    {
        antecesores = mA.recuperaAntecesores("reglas.dat");
        sucesores = mA.recuperaSucesores("reglas.dat");
        
        //Trasladar los sucesores a un Arreglo Bidimensional
        entradas = new double[antecesores.size()][antecesores.get(0).size()];
        for(Array)
        chido = new ArrayList<>();
    }

}
   
