/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package espol.edu.ec.tda.entidades;

import espol.edu.ec.moduloDeRegistro.RegistroMigrante;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author SSAM
 */
public class Canton {
    private int codigo;
    private int codigoProv;
    private int codPais;
    private String codSubContinente;
    private int codContinente;
    private String nombre;
    private List<RegistroMigrante> list;

    public Canton(int codigo, int codigoProv, int codPais, String codSubContinente, int codContinente, String nombre) {
        this.codigo = codigo;
        this.codigoProv = codigoProv;
        this.codPais = codPais;
        this.codSubContinente = codSubContinente;
        this.codContinente = codContinente;
        this.nombre = nombre;
        list = new LinkedList<>();
    }

    public Canton(int codigo, String nombre) {
        this.codigo = codigo;
        this.nombre = nombre;
        list = new LinkedList<>();
    }

    public Canton(String nombre) {
        this.nombre = nombre;
        list = new LinkedList<>();
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
    
    public LinkedList<RegistroMigrante> getList() {
        return (LinkedList<RegistroMigrante>) list;
    }

    public void setList(List<RegistroMigrante> list) {
        this.list = (LinkedList<RegistroMigrante>) list;
    }

    public int getCodigoProv() {
        return codigoProv;
    }

    public void setCodigoProv(int codigoProv) {
        this.codigoProv = codigoProv;
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

    @Override
    public String toString() {
        return nombre;
    }
    
    @Override
    public boolean equals(Object o){
        if(o == null || !(o instanceof Canton) )return false;
        Canton canton = (Canton)o;
        return nombre.equals(canton.nombre);
    }
}