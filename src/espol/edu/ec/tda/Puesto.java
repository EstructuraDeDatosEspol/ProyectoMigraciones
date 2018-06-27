/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package espol.edu.ec.tda;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Kenny Camba
 */
public class Puesto {
    private String puesto;
    
    public Puesto(String puesto){
        this.puesto = puesto;
    }
    
    public String getPuesto(){
        return puesto;
    }
    
    @Override
    public String toString() {
        return puesto;
    }
    
    public static List<Puesto> cargarPuestos() {
        List<Puesto> puestos = new LinkedList<>();
        String file = new File("").getAbsolutePath();
        int index = file.indexOf("migraciones");
        file = Paths.get(file.substring(0, index + 11), "src", "espol", "edu", "ec", "recursos", "files", "puestos.txt").toString();
        
        try(BufferedReader br = new BufferedReader(new FileReader(file))){
            String line;
            while((line=br.readLine()) != null)
                puestos.add(new Puesto(line));
        }catch(IOException ex){
            Logger.getLogger(Puesto.class.getName()).log(Level.SEVERE, null, ex);
        }
        return puestos;
    }
}
