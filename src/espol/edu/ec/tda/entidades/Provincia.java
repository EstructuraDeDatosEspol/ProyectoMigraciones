/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package espol.edu.ec.tda.entidades;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashMap;

/**
 *
 * @author SSAM
 */
public class Provincia {
    private int codigo;
    private int codPais;
    private String codSubContinente;
    private int codContinente;
    private String nombre;
    private HashMap<Integer,Canton> mapaCantones;

    public Provincia(int codigo, int codPais, String codSubContinente, int codContinente, String nombre) {
        this.codigo = codigo;
        this.codPais = codPais;
        this.codSubContinente = codSubContinente;
        this.codContinente = codContinente;
        this.nombre = nombre;
        mapaCantones = cargarCantonesM("Cantones.txt", codigo, codPais, codContinente, codSubContinente);
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public int getCodPais() {
        return codPais;
    }

    public void setCodPais(int codPais) {
        this.codPais = codPais;
    }

    public String getCodSubContinente() {
        return codSubContinente;
    }

    public void setCodSubContinente(String codSubContinente) {
        this.codSubContinente = codSubContinente;
    }

    public int getCodContinente() {
        return codContinente;
    }

    public void setCodContinente(int codContinente) {
        this.codContinente = codContinente;
    }

    public HashMap<Integer, Canton> getMapaCantones() {
        return mapaCantones;
    }

    public void setMapaCantones(HashMap<Integer, Canton> mapaCantones) {
        this.mapaCantones = mapaCantones;
    }
    
    @Override
    public String toString() {
        return "Provincia= " + nombre;
    }
    
    public static HashMap<Integer, Canton> cargarCantonesM(String nombre, int codigo, int codPais, int codContinente, String codSub){
        HashMap<Integer,Canton> mapa = new HashMap<>();
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
                if(Integer.valueOf(separa[1]).equals(codigo))mapa.put(Integer.valueOf(separa[0]), new Canton(Integer.valueOf(separa[0]),codigo,codPais,codSub,codContinente,separa[2]));
            }
        } catch (Exception ex) {
            ex.getStackTrace();
        }
        return mapa;
    }
}