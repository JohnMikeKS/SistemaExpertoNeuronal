package Inferencia;

import java.util.ArrayList;

public class Backpropagation {
    
    ArrayList antecedentes;
    ArrayList reglas;
    ArrayList consecuentes;
    int noAntecedentes,noReglas,noConsecuentes;
    
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
    }
    public  double random(){
        semilla = ((81*semilla)+89)%100/100;
        return semilla;
    }
    
    public void iniciaPesos()
    {
        pesos = new double[50];
        deltaPesos = new double[50];
        for (int i = 0; i < 50; i++) 
        {
            pesos[i]= random();
            //pesos[i] = Math.random();
        }
    }
    public void iniciaThetas()
    {
        thetas = new double[10];
        deltaUmbral = new double[10];
        for (int i = 0; i < 10; i++) 
        {
            thetas[i]= random();
            //thetas[i]= Math.random();
        }
    }
    public void inicia()
    {
        sigmoidalM = new double[5];
        sigmoidalF = new double[5];
        error = new double[5];
        deltaGradiante = new double[50];
    }


    public void calSalidas(int entradas[], int salEsperada[])
    {
        //entradas[] deben ser 5
        //salEsperada[] deben ser 5
        double sumaM[]= new double[5];
        int con=0;
        for (int i = 0; i < 5; i++) 
        {
            for (int j = 0; j < 5; j++) {
                sumaM[i] += entradas[j] * pesos[i*5];

            }
            con++;
        }

        for (int i = 0; i < 5; i++) 
            sigmoidalM[i] = Sigmoidal(sumaM[i]-thetas[i]);

        double sumaF=0;
        for (int i = 0; i < 5; i++)
            for (int j = 0; j < 5; j++)
                sumaF += sigmoidalM[i]*pesos[25+i];

        for (int i = 0; i < 5; i++) 
            sigmoidalF[i] = Sigmoidal(sumaF-thetas[5+i]);

        for (int i = 0; i < 5; i++) 
            error[i] = salEsperada[i] - sigmoidalF[i];

        Entrenamiento(entradas);
    }

    public void Entrenamiento(int entradas[])
    {

        //CALCULO DE ERROR  Y DELTAS EN LA CAPA DE SALIDA 
        for (int i = 0; i < 5; i++)
            deltaGradiante[5+i] = sigmoidalF[i] * ( 1 - sigmoidalF[i])*error[i];

            int con = 0;
            for (int i = 0; i < 5; i++) 
                for (int j = 0; j < 5; j++) 
                {
                    deltaPesos[25+con] = alpha * sigmoidalF[i] * deltaGradiante[j];
                    con++;
                }
            con=0;

            for (int i = 0; i < 5; i++) 
                deltaUmbral[5+i] = alpha * (-1) * deltaGradiante[i];

        //CALCULO DE ERROR GRADIANTE Y DELTAS EN LA CAPA OCULTA
        double aux=0;


            for (int i = 0; i < 5; i++)
            {
                for (int j = 0; j < 5; j++)
                {
                    aux += deltaGradiante[j]*pesos[con];
                    con++;
                }
                    deltaGradiante[i] = sigmoidalM[i] *(1 - sigmoidalM[i]) * aux;

            }
            con=0;
        /*  
        deltaK1 = H1Sigmoidal * (1 - H1Sigmoidal) * deltaJ2 * Wh1s1;
        deltaJ1 = H2Sigmoidal * (1 - H2Sigmoidal) * deltaJ2 * Wh2s1;
        */
        for (int i = 0; i < 5; i++) 
            for (int j = 0; j < 5; j++)
            {
                deltaPesos[con] = alpha * entradas[i] * deltaGradiante[i];
                con++;
            }

        for (int i = 0; i < 5; i++) 
            {
                deltaUmbral[i] = alpha * (-1) * deltaGradiante[i]; 

            }
        /*
        dtH1 = alpha * (-1) * deltaK1;
        dtH2 = alpha * (-1) * deltaJ1;
        */

        for (int i = 0; i < 50; i++) 
        {
            pesos[i] = pesos[i]+deltaPesos[i];
        }
        for (int i = 0; i < 10; i++) 
        {
            thetas[i]= thetas[i]+deltaUmbral[i];
        }

    }
    /*
    static void calSalidasMo(int x1, int x2, int gd5)
    {
        H1Sigmoidal = Sigmoidal(x1*Wx1h1 + x2*Wx2h1 - thetaH1);
        H2Sigmoidal = Sigmoidal(x1*Wx1h2 + x2*Wx2h2 - thetaH2);
        S1Sigmoidal = Sigmoidal(H1Sigmoidal*Wh1s1 + H2Sigmoidal*Wh2s1 - thetaS1);
        error = gd5 - S1Sigmoidal;
        EnMomentos(x1, x2);
    }

    static void EnMomentos(int x1, int x2)
    {
        deltaJ2 = S1Sigmoidal * (1 - S1Sigmoidal) * error;

        dWh1s1 = alpha * H1Sigmoidal * deltaJ2 + ( betha * dWh1s1 );
        dWh2s1 = alpha * H2Sigmoidal * deltaJ2 + ( betha * dWh2s1 );
        dtS1 = alpha * (-1) * deltaJ2;


        deltaK1 = H1Sigmoidal * (1 - H1Sigmoidal) * deltaJ2 * Wh1s1;
        deltaJ1 = H2Sigmoidal * (1 - H2Sigmoidal) * deltaJ2 * Wh2s1;

        dWx1h1 = alpha * x1 * deltaK1 + ( betha * dWx1h1 );
        dWx2h1 = alpha * x2 * deltaK1 + ( betha * dWx2h1 );
        dtH1 = alpha * (-1) * deltaK1;

        dWx1h2 = alpha * x1 * deltaJ1 + ( betha * dWx1h2 );
        dWx2h2 = alpha * x2 * deltaJ1 + ( betha * dWx2h2 );
        dtH2 = alpha * (-1) * deltaJ1;

        Wx1h1 = Wx1h1 + dWx1h1;
        Wx1h2 = Wx1h2 + dWx1h2;
        Wx2h1 = Wx2h1 + dWx2h1;
        Wx2h2 = Wx2h2 + dWx2h2;
        Wh1s1 = Wh1s1 + dWh1s1;
        Wh2s1 = Wh2s1 + dWh2s1;

        thetaH1 = thetaH1 + dtH1;
        thetaH2 = thetaH2 + dtH2;
        thetaS1 = thetaS1 + dtS1;
    }
    */
    public double Sigmoidal(double ex)
    {
        return (1.0/(1 + Math.pow(Math.E, (-1) * ex)));
    }
        public double error[] = new double[5] ;
        double pesos[];//Pesos 
        double thetas[];//Theta
        double sigmoidalM[];//Sigmoidal
        double sigmoidalF[];//Sigmoidal
        double deltaUmbral[];//Delta Umbrales
        double deltaGradiante[];//Error Gradiante
        double deltaPesos[];//Delta Pesos

        final double alpha = 0.1; //Coeficiente de aprendizaje
        final double betha = 1; //Betha Momento

        int epoca=1;
        double semilla=5;

    }
