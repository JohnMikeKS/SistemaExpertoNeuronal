
import Inferencia.ManejoArchivos;
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author joses
 */
public class pruebaArchivos {
    public static void main(String[] args) {
        ManejoArchivos obj = new ManejoArchivos();
        
        obj.limpiar();
        //obj.elminarArchivoReglas("reglas.dat");
          

       // obj.crearArchivoReglas("reglas.dat", "h^j>r");
        ArrayList<ArrayList<String>> antecesores = obj.recuperaAntecesores("reglas.dat");
        ArrayList<String> sucesores = obj.recuperaSucesores("reglas.dat");
        ArrayList<String> chido = new ArrayList<>();
        ArrayList<String> premisas = new ArrayList<>(); //Este array list contendra todo alv :v
                
        for(int i = 0;i<antecesores.size()/2;i++)
            for(int j=0;j<antecesores.get(i).size();j++){
                chido.add(antecesores.get(i).get(j));
                premisas.add(antecesores.get(i).get(j));
            }
        
       
        
        for(int i = 0;i<sucesores.size();i++){
            System.out.println("sucesores " + sucesores.get(i));
            premisas.add(sucesores.get(i));
        }
        
        //for(int i = 0;i<antecesores.size();i++)
        //  System.out.println("antecesores "+antecesores.get(i));
        
        for(int c=0;c<chido.size();c++)
            
            System.out.println("antecesores chidos "+chido.get(c));
        
         for(int c=0;c<premisas.size();c++)
            
            System.out.println("premisas "+premisas.get(c));
       
    }
}
