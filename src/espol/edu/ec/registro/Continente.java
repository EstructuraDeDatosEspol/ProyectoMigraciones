/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package espol.edu.ec.registro;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashMap;

/**
 *
 * @author SSAM
 */
public class Continente {
    private int codigo;
    private String continente;
    private HashMap<Integer,Pais> mapaPaises;
    
    public Continente(String continente) {
        this.continente = continente;
    }

    public Continente(int codigo, String continente) {
        this.codigo = codigo;
        this.continente = continente;
        //listapaises = cargarPaises("Paises.txt", codigo);
        mapaPaises = cargarPaisesM("Paises.txt", codigo);
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getContinente() {
        return continente;
    }

    public void setContinente(String continente) {
        this.continente = continente;
    }

    public HashMap<Integer, Pais> getMapaPaises() {
        return mapaPaises;
    }

    public void setMapaPaises(HashMap<Integer, Pais> mapaPaises) {
        this.mapaPaises = mapaPaises;
    }

    @Override
    public String toString() {
        return continente;
    }
    
    public static HashMap<Integer,Pais> cargarPaisesM(String nombre, int codigo){
        HashMap<Integer,Pais> mapa = new HashMap<>();
        File f = new File("");
        String dire = f.getAbsolutePath();
        int indiceDeProyecto = dire.indexOf("migraciones");
        String direccion = dire.substring(0,indiceDeProyecto+11)+"\\src\\espol\\edu\\ec\\recursos\\files\\"+nombre;
        try (BufferedReader br = new BufferedReader(new FileReader(new File(direccion)));){
            String linea;
            br.readLine();
            while ((linea = br.readLine()) != null) {//linea leida
                String[] separa = linea.split("[\\,]");
                //guardamos la linea
                if(Integer.valueOf(separa[1]).equals(codigo))mapa.put(Integer.valueOf(separa[0]), new Pais(Integer.valueOf(separa[0]),codigo,separa[3]));
            }
        } catch (Exception ex) {
            ex.getStackTrace();
        }
        return mapa;
    }
}