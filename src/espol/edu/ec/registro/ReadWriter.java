package espol.edu.ec.registro;

import espol.edu.ec.tda.Puesto;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

//Declaracion de la clase
 
/**
 * Clase ReadWriter 
 * @author Araujo Steven
 * @author Banchon Melanie
 * @author Guerrero Darly
 * @version 13/01/18
 */
public class ReadWriter{
    private final File f = new File("");
    private final String dire = f.getAbsolutePath();
    private final int indiceDeProyecto = dire.indexOf("migraciones");
    private final String ubicacion = "\\src\\espol\\edu\\ec\\recursos\\files\\";
    private final String separar = "[\\,]";
    
    public String cargarTitulos(String nombre){
        String direccion = dire.substring(0,indiceDeProyecto+11)+ubicacion+nombre;
        try (BufferedReader br = new BufferedReader(new FileReader(new File(direccion)));){
            String linea;            
            // Leemos linea a linea el fichero
            while ((linea = br.readLine()) != null) {//linea leida
                return linea;          //guardamos la linea
            }
        } catch (Exception ex) {
            ex.getStackTrace();
        }
        return null;
    }
    
    public LinkedList<RegistroMigrante> cargarRegistro(String nombre ){
        LinkedList<RegistroMigrante> listLineas = new LinkedList<>();

        String direccion = dire.substring(0,indiceDeProyecto+11)+ubicacion+nombre;
        try (BufferedReader br = new BufferedReader(new FileReader(new File(direccion)));){
            String linea;
            br.readLine();
            while ((linea = br.readLine()) != null) {//linea leida
                String[] separa = linea.split(";");
                listLineas.addLast(new RegistroMigrante(
                separa[0],separa[1],separa[2],Integer.valueOf(separa[5]),separa[6],Integer.valueOf(separa[7]),
                separa[14],separa[16],separa[17],separa[18],separa[20],separa[21],separa[23],
                new Persona(Integer.parseInt(separa[8]),separa[9],separa[10],separa[11],Integer.valueOf(separa[12]),separa[13],Integer.valueOf(separa[19]),separa[15],separa[22],separa[24]),
                new Canton(separa[4]),separa[3]));//guardamos la linea
            }
        } catch (Exception ex) {
            ex.getStackTrace();
        }
        return listLineas;
    }
    
    public void eliminarRegistro(LinkedList<RegistroMigrante> lineas, String nombre){
        String direccion = dire.substring(0,indiceDeProyecto+11)+ubicacion+nombre;
        String titulo = cargarTitulos(nombre);
	try (BufferedWriter bw = new BufferedWriter(new FileWriter(direccion))){
            bw.write(titulo);
            bw.newLine();
            for(RegistroMigrante reg: lineas){
                if(reg.getTip_mov() != null){
                    bw.write(reg.texto());
                    bw.newLine();
                }                
            }
        } catch (IOException ex) {
            ex.getStackTrace();
        }
    }
    
    public void agregarRegistro(List<String> linea, String nombre){
        String direccion = dire.substring(0,indiceDeProyecto+11)+ubicacion+nombre;
	try (BufferedWriter bw = new BufferedWriter(new FileWriter(direccion,true))){
            int a = linea.size();
            int n=0;
            
            StringBuilder bld = new StringBuilder();
            for (String palabras : linea) {
                n++;
                if(n<a){
                    bld.append(palabras);
                    bld.append(";");
                }else bld.append(palabras);
            }
            bw.write(bld.toString());
            bw.newLine();
        } catch (IOException ex) {
            ex.getStackTrace();
        }
    }
    
    public LinkedList<Persona> cargarPersonas(String nombre ){
        LinkedList<Persona> listLineas = new LinkedList<>();

        String direccion = dire.substring(0,indiceDeProyecto+11)+ubicacion+nombre;
        try (BufferedReader br = new BufferedReader(new FileReader(new File(direccion)));){
            String linea;
            br.readLine();
            while ((linea = br.readLine()) != null) {//linea leida
                String[] separa = linea.split(separar);
                listLineas.addLast(new Persona(
                Integer.parseInt(separa[0]),separa[1],separa[2],separa[3],Integer.valueOf(separa[4]),separa[5],Integer.valueOf(separa[6]),separa[7],separa[8],separa[9]));//guardamos la linea
            }
        } catch (Exception ex) {
            ex.getStackTrace();
        }
        return listLineas;
    }
    
    public void agregarPersonas(List<String> linea, String nombre){
        String direccion = dire.substring(0,indiceDeProyecto+11)+ubicacion+nombre;
	try (BufferedWriter bw = new BufferedWriter(new FileWriter(direccion,true))){
            int a = linea.size();
            int n=0;
            
            StringBuilder bld = new StringBuilder();
            for (String palabras : linea) {
                n++;
                if(n<a){
                    bld.append(palabras);
                    bld.append(",");
                }else bld.append(palabras);
            }
            bw.write(bld.toString());
            bw.newLine();
        } catch (IOException ex) {
            ex.getStackTrace();
        }
    }
    
    public HashMap<Integer,Continente> cargarContinentes(String nombre ){
        HashMap<Integer,Continente> listLineas = new HashMap<>();

        String direccion = dire.substring(0,indiceDeProyecto+11)+ubicacion+nombre;
        try (BufferedReader br = new BufferedReader(new FileReader(new File(direccion)));){
            String linea;
            br.readLine();
            while ((linea = br.readLine()) != null) {//linea leida
                String[] separa = linea.split(separar);
                listLineas.put(Integer.valueOf(separa[0]),new Continente(Integer.valueOf(separa[0]),separa[1]));//guardamos la linea
            }
        } catch (Exception ex) {
            ex.getStackTrace();
        }
        return listLineas;
    }
    
    public void agregarCanton(List<String> linea, String nombre){
        String direccion = dire.substring(0,indiceDeProyecto+11)+ubicacion+nombre;
	try (BufferedWriter bw = new BufferedWriter(new FileWriter(direccion,true))){
            int a = linea.size();
            int n=0;
            
            StringBuilder bld = new StringBuilder();
            for (String palabras : linea) {
                n++;
                if(n<a){
                    bld.append(palabras);
                    bld.append(";");
                }else bld.append(palabras);
            }
            bw.write(bld.toString());
            bw.newLine();
        } catch (IOException ex) {
            ex.getStackTrace();
        }
    }
    
    public static Map<String, Persona> loadEmpleados() {
        HashMap<String, Persona> mapa = new HashMap<>();
        String file = new File("").getAbsolutePath();
        file = Paths.get(file, "src", "espol", "edu", "ec", "recursos", "files", "empleados.txt").toString();
        
        try(BufferedReader br = new BufferedReader(new FileReader(file))){
            String line;
            while((line=br.readLine()) != null){
                String[] data = line.split(",");
                mapa.put(data[0], 
                        new Persona(Integer.parseInt(data[0]), data[1], data[2], data[3], Integer.parseInt(data[4]))); 
            }
        }catch(IOException ex){
            Logger.getLogger(Puesto.class.getName()).log(Level.SEVERE, null, ex);
        }
        return mapa;
    }
}