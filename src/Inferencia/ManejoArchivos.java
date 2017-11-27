/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Inferencia;


import java.io.File;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.StringTokenizer;
import javax.swing.JOptionPane;


/**
 *
 * @author joses
 */
public class ManejoArchivos {

    //ArraysList necesarios para las reglas
    ArrayList<ArrayList<String>> antecedentes = new ArrayList();
    ArrayList consecuentes = new ArrayList();
    //Metodo para limpiar los array
    public void limpiar(){
     antecedentes.clear();
     consecuentes.clear();
    }
    
    //Beto was here
    public ArrayList<String> recuperarReglas(String path)
    {
        ArrayList <String> array = new ArrayList();
        try
        {
            RandomAccessFile file = new RandomAccessFile(new File(path),"r");
            while(file.getFilePointer() != file.length()){
            array.add(file.readUTF());
            }
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, "Trono el archivo");
        }
        return array;
    }
    
    //And here too 
    public void sobreescribir(String path,String reglas)
    {
        try
        {
            ArrayList <String> array=new ArrayList();
            StringTokenizer st=new StringTokenizer(reglas,"\n");
            while(st.hasMoreElements())
                crearArchivoReglas(path,st.nextToken());
                //System.out.println(st.nextToken());
            
            
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, "Trono el archivo");
        }
        
    }
    
    public void crearArchivoReglas(String path,String reglas){
        try {
            RandomAccessFile file = new RandomAccessFile(path,"rw");
            file.seek(file.length());
            file.writeUTF(reglas); //Aqui se ingresaria todo un string que se recupere de una caja de texto
            file.close();
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
    
    public boolean elminarArchivoReglas(String path){
        File f = new File(path);
    	   return f.delete();
    }
    
    public ArrayList<ArrayList<String>> recuperaAntecesores(String path){
        String contenido;
        ArrayList<String> antecesoresAuxiliar;
        
        try {
            RandomAccessFile file = new RandomAccessFile(new File(path),"r");
            while(file.getFilePointer() != file.length()){
                antecesoresAuxiliar = new ArrayList();
                antecesoresAuxiliar.clear();
                contenido = file.readUTF();
                //Separadaor de antecedentes ^
                StringTokenizer st = new StringTokenizer(contenido, ">");
	           String sucesores = st.nextToken();
               //Separador de consecuentes >
	            StringTokenizer st2 = new StringTokenizer(sucesores, "^");
	            while(st2.hasMoreElements()) {
					String ante = st2.nextToken();
					antecesoresAuxiliar.add(ante);
				}
                antecedentes.add(antecesoresAuxiliar);
            }
            
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return  antecedentes;
    }
    
    public ArrayList<String> recuperaSucesores(String path){
        String contenido;
        ArrayList<String> antecesoresAuxiliar;
        
        try {
            RandomAccessFile file = new RandomAccessFile(new File(path),"r");
            while(file.getFilePointer() != file.length()){
                
                contenido = file.readUTF();
               
                //Separadaor de antecedentes ^
                StringTokenizer st = new StringTokenizer(contenido, ">");
	           String sucesores = st.nextToken();
                  
                //Separador de consecuentes >
	            StringTokenizer st2 = new StringTokenizer(sucesores, "^");
	            while(st2.hasMoreElements()) {
					String ante = st2.nextToken();
                                        
				}
                
                sucesores = st.nextToken();
			  consecuentes.add(sucesores);
            }
            
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return  consecuentes;
    }
}
