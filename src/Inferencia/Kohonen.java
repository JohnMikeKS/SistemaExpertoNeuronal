/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Inferencia;

/**
 *
 * @author danie
 */
public class Kohonen {
    public static void main(String[] args) {
        
         Double[][] Entradas = {         {1.0,1.0,0.0,1.0,0.0,0.0,0.0},
                                         {1.0,1.0,1.0,0.0,0.0,0.0,0.0},//0 //0 //1
                                         {1.0,0.0,0.0,0.0,0.0,1.0,0.0},
                                         {0.0,0.0,1.0,1.0,1.0,0.0,0.0},
                                         {0.0,0.0,1.0,0.0,0.0,0.0,1.0},
                                         {0.0,0.0,0.0,0.0,0.0,0.0,0.0}
                                         
                                         };
    Double t=1.0;
    Double[][] Pesos = {              {0.4,0.4,0.5,0.4,0.7,0.5,0.5},
                                      {0.4,0.5,0.6,0.4,0.5,0.6,0.5},
                                      {0.5,0.6,0.5,0.4,0.8,0.3,0.5},
                                      {0.5,0.6,0.5,0.4,0.7,0.3,0.5},
                                      {0.5,0.6,0.1,0.1,0.5,0.4,0.5},
                                      {0.5,0.1,0.2,0.1,0.4,0.5,0.5},
                                      
                                     };
    Double[] Deltas = new Double[Pesos.length];
    int neurona_ganadora=0;
    double aux_neurona;
        for (int tt = 1;tt <= 50000; tt++) 
        {
           
        aux_neurona=-1;
        neurona_ganadora=-1;
        
        //Generacion de Epoca
        for (int i = 0; i < Entradas.length; i++) 
        {
            for (int j = 0; j < Pesos.length; j++) {
                Deltas[j]=0.0;
            }
            //Calculo de Deltas
            for (int j = 0; j < Pesos.length; j++) 
            {
                for (int k = 0; k < Entradas[i].length; k++) 
                {
                    Deltas[j]+= Math.pow(Entradas[i][k]-Pesos[j][k],2);
                    
                }
                //System.out.print(String.format("%.2f",Deltas[j])+"\t");
                if(j==0)
                {
                    //Se obtiene la neurona vencedora
                    aux_neurona=Deltas[j];
                    neurona_ganadora=j;
                }
                else if(aux_neurona>Deltas[j])
                {
                    //Se obtiene la neurona vencedora
                    aux_neurona=Deltas[j];
                    neurona_ganadora=j;
                }
                
            }
            for (int j = 0; j < Entradas[0].length; j++) {
                //Actualizar pesos
                Pesos[neurona_ganadora][j]+=(1/t)*(Entradas[i][j]-Pesos[neurona_ganadora][j]);
            }
                
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
        }
        
        t++;
        }
    }
   
}

