/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package espol.edu.ec.tda;

import espol.edu.ec.registro.Persona;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Kenny Camba
 */
public class Puesto {
    private String puesto;
    private Persona empleado;
    
    public Puesto(String puesto){
        this.puesto = puesto;
    }
    
    public Puesto(String puesto, Persona empleado) {
        this(puesto);
        this.empleado = empleado;
    }
    
    public String getPuesto(){
        return puesto;
    }
    
    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }
    
    public Persona getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Persona empleado) {
        this.empleado = empleado;
    }
    
    @Override
    public String toString() {
        return puesto;
    }
    
    @Override
    public boolean equals(Object o){
        if(!(o instanceof Puesto))
            return false;
        return o.hashCode() == this.hashCode();
    }
    
    @Override
    public int hashCode() {
        int hash;
        try{
            hash = Integer.parseInt(puesto);
        }catch(NumberFormatException ex){
            hash = puesto.hashCode();
        }
        return hash;
    }
    
    public static List<Puesto> cargarPuestos(Map<String, Persona> empleados) {
        List<Puesto> puestos = new LinkedList<>();
        String file = new File("").getAbsolutePath();
        file = Paths.get(file, "src", "espol", "edu", "ec", "recursos", "files", "puestos.txt").toString();
        
        try(BufferedReader br = new BufferedReader(new FileReader(file))){
            String line;
            while((line=br.readLine()) != null){
                String data[] = line.split(",");
                if(data.length == 1)
                    puestos.add(new Puesto(data[0]));
                else
                    puestos.add(new Puesto(data[0], empleados.get(data[1]))); 
            }
        }catch(IOException ex){
            Logger.getLogger(Puesto.class.getName()).log(Level.SEVERE, null, ex);
        }
        return puestos;
    }
}
