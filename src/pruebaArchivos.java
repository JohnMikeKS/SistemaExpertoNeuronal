
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
          

        obj.crearArchivoReglas("reglas.dat", "h^j>r");
        ArrayList<ArrayList<String>> antecesores = obj.recuperaAntecesores("reglas.dat");
        ArrayList<String> sucesores = obj.recuperaSucesores("reglas.dat");
        ArrayList<String> chido = new ArrayList<>();
                
        for(int i = 0;i<antecesores.size();i++)
            chido.add(antecesores.get(i).get(i));
        for(int i = 0;i<sucesores.size();i++)
            System.out.println("sucesores " + sucesores.get(i));
        for(int i = 0;i<chido.size();i++)
            System.out.println("antecesores "+chido.get(i));
       
    }
}
