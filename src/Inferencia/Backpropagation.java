package Inferencia;

import java.util.ArrayList;

public class Backpropagation {
    
    ArrayList antecedentes;
    ArrayList reglas;
    ArrayList consecuentes;
    int noAntecedentes,noReglas,noConsecuentes;
    public double errorSalida[] = new double[5] ;
        public double pesos[];//Pesos 
        public double thetas[];//Theta
        double sigmoidalM[];//Sigmoidal
        double sigmoidalF[];//Sigmoidal
        double deltaUmbral[];//Delta Umbrales
        double deltaReglas[];//Error Gradiante
        double deltaPesos[];//Delta Pesos
        final double alpha = 0.9; //Coeficiente de aprendizaje
        final double betha = 1; //Betha Momento
        int epoca=1;
        double semilla=5;
    public void setAntecedentes(ArrayList n){
        this.antecedentes = n ;
        noAntecedentes = n.size();
    }
    public void setReglas(ArrayList m){
        this.reglas = m;
        noReglas = m.size();
    }
    public void setConsecuentes(ArrayList x){
        this.consecuentes = x;
        noConsecuentes = x.size();
        //asbdjkashdjkh
    }
    public  double random(){
        semilla = ((81*semilla)+89)%100/10;
        return semilla;
    }
    public double Sigmoidal(double ex){
        return (1.0/(1 + Math.pow(Math.E, (-1) * ex)));
    }
    public void iniciaPesos()
    {
        
        pesos = new double[(noAntecedentes*noReglas)+(noReglas*noConsecuentes)];
        deltaPesos = new double[pesos.length];
        
        for (int i = 0; i < pesos.length; i++){
            pesos[i] = Math.random();
        }
                
    }
    public void iniciaThetas()
    {
        thetas = new double[noConsecuentes+noReglas];
        deltaUmbral = new double[thetas.length];
        for (int i = 0; i < thetas.length; i++) 
        {
            //thetas[i]= random();
            thetas[i]= Math.random();
        }
        
    }
    public void inicia()
    {
        sigmoidalM = new double[noReglas];
        sigmoidalF = new double[noConsecuentes];
        errorSalida = new double[noConsecuentes];
        deltaReglas = new double[noReglas+noConsecuentes];
    }
    public void calSalidas(int entradas[], int salEsperada[])
    {
        //Calculo de X para cada Neurona en la capa oculta
        double X[]= new double[noReglas];
        int con=0;
        for (int i = 0; i < noReglas; i++){
            for (int j = 0; j < noAntecedentes; j++) 
                X[i] += entradas[j] * pesos[noReglas*j+i];
            X[i] = X[i]-thetas[i];
        }
        //Calculo de la salida REAL de la capa Oculta
        for (int i = 0; i < noReglas; i++) 
            sigmoidalM[i] = Sigmoidal(X[i]);

        //Calculo de Y para cada neurona en la capa de Salida
        double[] Y=  new double[noConsecuentes];
        for (int i = 0; i < noConsecuentes; i++){
            for (int j = 0; j < noReglas; j++)
            {
                Y[i] += sigmoidalM[j]*pesos[(noAntecedentes*noReglas)+(noConsecuentes*j+i)];
            }
        Y[i] = Y[i]-thetas[noReglas+i];
        }
            
        //Calculo de la salida REAL de la capa oculta
        for (int i = 0; i < noConsecuentes; i++) 
        {
            sigmoidalF[i] = Sigmoidal(Y[i]);
            errorSalida[i] = salEsperada[i] - sigmoidalF[i];
        }
        Entrenamiento(entradas);
    }

    public void Entrenamiento(int entradas[])
    {

       
        //CALCULO DE ERROR  Y DELTAS EN LA CAPA DE SALIDA 
        for (int i = 0; i < noConsecuentes; i++)
            deltaReglas[noReglas+i] = sigmoidalF[i] * ( 1 - sigmoidalF[i])*errorSalida[i];

        //CALCULO DE DELTAS PESOS ENTRE LA CAPA DE SALIDA Y LA CAPA OCULTA (REGLAS)
        for (int i = 0; i < noReglas; i++) 
            for (int j = 0; j < noConsecuentes; j++)
            {
                //SIN MOMENTO
                //deltaPesos[(noAntecedentes*noReglas)+i] = alpha * sigmoidalM[i] * deltaReglas[noReglas+j];
                //CON MOMENTO
                deltaPesos[(noAntecedentes*noReglas)+i] = alpha * sigmoidalM[i] * deltaReglas[noReglas+j] + (betha*deltaPesos[(noAntecedentes*noReglas)+i]);
             
            }
        for (int i = 0; i < noConsecuentes; i++) 
            deltaUmbral[noReglas+i] = alpha * (-1) * deltaReglas[noReglas+i];

        //CALCULO DE ERROR GRADIANTE EN LA CAPA OCULTA (REGLAS)
        double aux=0;
        for (int i = 0; i < noReglas; i++){
            for (int j = 0; j < noConsecuentes; j++)
                aux += deltaReglas[noReglas+j]*pesos[(noAntecedentes*noReglas)+(noConsecuentes*j+i)];
            deltaReglas[i] = sigmoidalM[i] *(1 - sigmoidalM[i]) * aux;
        }
       //Calculo de Delta Pesos entre Entrada y Oculta
        int con=0;
        for (int i = 0; i < noAntecedentes; i++) 
            for (int j = 0; j < noReglas; j++){
                //SIN MOMENTO
                //deltaPesos[con] = alpha * entradas[i] * deltaReglas[j];
                //CON MOMENTO
                deltaPesos[con] = alpha * entradas[i] * deltaReglas[j] + (betha*deltaPesos[con]);
                con++;
            }
        //Calculo de Delta Umbrales para la capa Oculta (Reglas)
        for (int i = 0; i < noReglas; i++)
            deltaUmbral[i] = alpha * (-1) * deltaReglas[i]; 

        //Actualizacion de Pesos y Umbrales 
        for (int i = 0; i < pesos.length; i++) 
            pesos[i] = pesos[i]+deltaPesos[i];
        
        for (int i = 0; i < thetas.length; i++) 
            thetas[i]= thetas[i]+deltaUmbral[i];
    }
}
