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
public class Pais {
    private HashMap<Integer,Provincia> mapaProvincias;
    private String nombre;
    private int codContinente;
    private int codPais;

    public Pais(int codPais, int codContinente, String nombre) {
        this.nombre = nombre;
        this.codContinente = codContinente;
        this.codPais = codPais;
        mapaProvincias = cargarProvinciasM("Provincias.txt", codPais, codContinente);
    }

    public int getCodPais() {
        return codPais;
    }

    public void setCodPais(int codPais) {
        this.codPais = codPais;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCodContinente() {
        return codContinente;
    }

    public void setCodContinente(int codContinente) {
        this.codContinente = codContinente;
    }

    public HashMap<Integer, Provincia> getMapaProvincias() {
        return mapaProvincias;
    }

    public void setMapaProvincias(HashMap<Integer, Provincia> mapaProvincias) {
        this.mapaProvincias = mapaProvincias;
    }

    @Override
    public String toString() {
        return "Pais= "+nombre ;
    }
    
    public static HashMap<Integer, Provincia> cargarProvinciasM(String nombre, int codigo, int codContinente){
        HashMap<Integer,Provincia> mapa = new HashMap<>();
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
                if(Integer.valueOf(separa[1]).equals(codigo))mapa.put(Integer.valueOf(separa[0]), new Provincia(Integer.valueOf(separa[0]),codigo,codContinente,separa[2]));
            }
        } catch (Exception ex) {
            ex.getStackTrace();
        }
        return mapa;
    }
}