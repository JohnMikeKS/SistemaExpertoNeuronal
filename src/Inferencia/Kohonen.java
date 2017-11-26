package Inferencia;

import java.util.ArrayList;
import java.util.HashMap;

public class Kohonen {
    
    double t = 1.0;
    public double[][] pesos;
    double[][] entradas;
    double[] deltas; 
    int clases;
    ManejoArchivos mA = new ManejoArchivos();
    ArrayList<ArrayList<String>> antecesores;
    ArrayList<String> sucesores;
    ArrayList<String> chido = new ArrayList<>();
    Object[] antecesoresC;
    HashMap<String,String> antecesor = new HashMap();
   
    public void Entrenamiento()
    {
    
    int neurona_ganadora=0;
    double aux_neurona;
        for (int tt = 1;tt <= 50000; tt++) 
        {
           
        aux_neurona=-1;
        neurona_ganadora=-1;
        
        //Generacion de Epoca
        for (int i = 0; i < entradas.length; i++) 
        {
            for (int j = 0; j < pesos.length; j++) {
                deltas[j]=0.0;
            }
            //Calculo de Deltas
            for (int j = 0; j < pesos.length; j++) 
            {
                for (int k = 0; k < entradas[i].length; k++) 
                {
                    deltas[j]+= Math.pow(entradas[i][k]-pesos[j][k],2);
                    
                }
                //System.out.print(String.format("%.2f",Deltas[j])+"\t");
                if(j==0)
                {
                    //Se obtiene la neurona vencedora
                    aux_neurona=deltas[j];
                    neurona_ganadora=j;
                }
                else if(aux_neurona>deltas[j])
                {
                    //Se obtiene la neurona vencedora
                    aux_neurona=deltas[j];
                    neurona_ganadora=j;
                }
                
            }
            for (int j = 0; j < entradas[0].length; j++) {
                //Actualizar pesos
                pesos[neurona_ganadora][j]+=(1/t)*(entradas[i][j]-pesos[neurona_ganadora][j]);
            }
                
            //IMPRESION EN CONSOLA DE LOS PESOS FINALES
            /*
                if(i==4)
                    switch(tt)
                {
                    
                    case 50000:
                        System.out.println("Epoca: "+(t));
                    for (Double [] peso : Pesos)
                    {
                        for (int j = 0; j < peso.length; j++) 
                            System.out.print(peso[j]+"\t");
                        System.out.println("");
                    }
                        
                    
                    break;
                }
            */
        }
        
        t++;
        }
    }
    public int[] Inferencia(double[] Patron)
    {
        //Calculamos la distancia entre el Patron de Entrada
        //y cada vector en los Pesos
        //Aquel que tenga la menor distancia sera el valor 
        //de la clase a la que pertence
        double[] distancias = new double[entradas.length];
        for (int i = 0; i < entradas.length; i++) {
            for (int j = 0; j < pesos[i].length; j++) {
                distancias[i] += Math.pow(Patron[j]-pesos[i][j],2);
            }
        }
        /* En esta seccion se localizan MULTIPLES NEURONAS GANADORAS 
           recuperamos el (los) valor(es) mas pequeño(s) de las distancias */
        double aux=-1;
        int[] neuronas = new int[distancias.length];
        int con=0;
        for (int i = 0; i < distancias.length; i++) {
            if(i==0){
                aux = distancias[i];
                neuronas[0]=i+1;
                con=1;
            }
            else if(distancias[i]<=aux){
                if(distancias[i]<aux){
                    aux = distancias[i];
                    for (int j = 0; j < neuronas.length; j++) {
                        neuronas[j]=0;
                    }
                    neuronas[0]=i+1;
                    con=1;
                }else if(distancias[i]==aux){
                    neuronas[con] = i+1;
                    con++;
                }
            }
        }
        return neuronas;
    }
    public Kohonen()
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
        clases = entradas.length;
        deltas = new double[pesos.length];
    }
    public void sincronizacion()
    {
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
            idDeporte = Inferencia(regla);
            Mapeo.put(idDeporte[0],sucesores.get(i)); 
            i++;
        } 
    }
}
