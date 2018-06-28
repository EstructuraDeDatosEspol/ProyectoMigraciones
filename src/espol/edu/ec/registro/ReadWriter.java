package espol.edu.ec.registro;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

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
    
    public ArrayList<ArrayList<String>> cargarTitulos(String nombre ){
        ArrayList<ArrayList<String>> listLineas = new ArrayList<>();

        String direccion = dire.substring(0,indiceDeProyecto+11)+ubicacion+nombre;
        try (BufferedReader br = new BufferedReader(new FileReader(new File(direccion)));){
            String linea;            
            // Leemos linea a linea el fichero
            while ((linea = br.readLine()) != null) {       //linea leida
                ArrayList<String> listPalabras = new ArrayList<>();
                
                //linea separada
                String[] separa = linea.split(separar);
                listPalabras.addAll(Arrays.asList(separa));
                listLineas.add(listPalabras);             //guardamos la linea
            }
        } catch (Exception ex) {
            ex.getStackTrace();
        }
        return listLineas;
    }
    
    public void agregarAlArchivo(List<String> linea, String nombre){
        String direccion = dire.substring(0,indiceDeProyecto+11)+"src/espol/edu/ec/recursos/files"+nombre;
       
	try (BufferedWriter bw = new BufferedWriter(new FileWriter(direccion,true))){
            int a = linea.size();
            int n=0;
            
            StringBuilder bld = new StringBuilder();
            for (String palabras : linea) {
                n++;
                if(n<a){
                    bld.append(palabras);
                    bld.append(",");
                }else{
                    bld.append(palabras);
                }
            }
            bw.write(bld.toString());
            bw.newLine();
        } catch (IOException ex) {
            ex.getStackTrace();
        }
    }
    
    public LinkedList<RegistroMigrante> cargarRegistro(String nombre ){
        LinkedList<RegistroMigrante> listLineas = new LinkedList<>();

        String direccion = dire.substring(0,indiceDeProyecto+11)+ubicacion+nombre;
        try (BufferedReader br = new BufferedReader(new FileReader(new File(direccion)));){
            String linea;
            br.readLine();
            while ((linea = br.readLine()) != null) {//linea leida
                String[] separa = linea.split(separar);
                listLineas.addLast(new RegistroMigrante(
                separa[0],separa[1],separa[2],Integer.valueOf(separa[5]),separa[6],Integer.valueOf(separa[7]),
                separa[14],separa[16],separa[17],separa[18],separa[20],separa[21],separa[23],
                new Persona(Integer.valueOf(separa[8]),separa[9],separa[10],separa[11],Integer.valueOf(separa[12]),separa[13],Integer.valueOf(separa[19]),separa[15],separa[22],separa[24]),
                new Canton(separa[4])
                        ));//guardamos la linea
            }
        } catch (Exception ex) {
            ex.getStackTrace();
        }
        return listLineas;
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
                Integer.valueOf(separa[0]),separa[1],separa[2],separa[3],Integer.valueOf(separa[4]),separa[5],Integer.valueOf(separa[6]),separa[7],separa[8],separa[9]));//guardamos la linea
            }
        } catch (Exception ex) {
            ex.getStackTrace();
        }
        return listLineas;
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
}