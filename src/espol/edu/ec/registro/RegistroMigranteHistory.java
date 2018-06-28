/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package espol.edu.ec.registro;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author SSAM
 */
public class RegistroMigranteHistory {
    private SimpleStringProperty tip_mov;
    private SimpleStringProperty tipo_nacionalidad;
    private SimpleStringProperty via_transporte;
    private SimpleStringProperty provincia_movi;
    private SimpleStringProperty canton_movi;
    private SimpleIntegerProperty anio_movi;
    private SimpleStringProperty mes_movi;
    private SimpleIntegerProperty dia_movi;
    private SimpleIntegerProperty cedula;
    private SimpleStringProperty nombre;
    private SimpleStringProperty apellido;
    private SimpleStringProperty sexo;
    private SimpleIntegerProperty anioNac;
    private SimpleStringProperty ocupacion;
    private SimpleStringProperty motivo_viaje;
    private SimpleStringProperty nacionalidad_migr;
    private SimpleStringProperty pais_procedencia;
    private SimpleStringProperty pais_residencia;
    private SimpleStringProperty lugar_proveniente;

    public RegistroMigranteHistory(String tip_mov, String tipo_nacionalidad, String via_transporte, String provincia_movi, String canton_movi, int anio_movi, String mes_movi, int dia_movi, int cedula, String nombre, String apellido, String sexo, int anioNac, String ocupacion, String motivo_viaje, String nacionalidad_migr, String pais_procedencia, String pais_residencia, String lugar_proveniente) {
        this.tip_mov = new SimpleStringProperty(tip_mov);
        this.tipo_nacionalidad = new SimpleStringProperty(tipo_nacionalidad);
        this.via_transporte = new SimpleStringProperty(via_transporte);
        this.provincia_movi = new SimpleStringProperty(provincia_movi);
        this.canton_movi = new SimpleStringProperty(canton_movi);
        this.anio_movi = new SimpleIntegerProperty(anio_movi);
        this.mes_movi = new SimpleStringProperty(mes_movi);
        this.dia_movi = new SimpleIntegerProperty(dia_movi);
        this.cedula = new SimpleIntegerProperty(cedula);
        this.nombre = new SimpleStringProperty(nombre);
        this.apellido = new SimpleStringProperty(apellido);
        this.sexo = new SimpleStringProperty(sexo);
        this.anioNac = new SimpleIntegerProperty(anioNac);
        this.ocupacion = new SimpleStringProperty(ocupacion);
        this.motivo_viaje = new SimpleStringProperty(motivo_viaje);
        this.nacionalidad_migr = new SimpleStringProperty(nacionalidad_migr);
        this.pais_procedencia = new SimpleStringProperty(pais_procedencia);
        this.pais_residencia = new SimpleStringProperty(pais_residencia);
        this.lugar_proveniente = new SimpleStringProperty(lugar_proveniente);
    }

    public SimpleStringProperty getTip_mov() {
        return tip_mov;
    }

    public void setTip_mov(SimpleStringProperty tip_mov) {
        this.tip_mov = tip_mov;
    }

    public SimpleStringProperty getTipo_nacionalidad() {
        return tipo_nacionalidad;
    }

    public void setTipo_nacionalidad(SimpleStringProperty tipo_nacionalidad) {
        this.tipo_nacionalidad = tipo_nacionalidad;
    }

    public SimpleStringProperty getVia_transporte() {
        return via_transporte;
    }

    public void setVia_transporte(SimpleStringProperty via_transporte) {
        this.via_transporte = via_transporte;
    }

    public SimpleStringProperty getProvincia_movi() {
        return provincia_movi;
    }

    public void setProvincia_movi(SimpleStringProperty provincia_movi) {
        this.provincia_movi = provincia_movi;
    }

    public SimpleStringProperty getCanton_movi() {
        return canton_movi;
    }

    public void setCanton_movi(SimpleStringProperty canton_movi) {
        this.canton_movi = canton_movi;
    }

    public SimpleIntegerProperty getAnio_movi() {
        return anio_movi;
    }

    public void setAnio_movi(SimpleIntegerProperty anio_movi) {
        this.anio_movi = anio_movi;
    }

    public SimpleStringProperty getMes_movi() {
        return mes_movi;
    }

    public void setMes_movi(SimpleStringProperty mes_movi) {
        this.mes_movi = mes_movi;
    }

    public SimpleIntegerProperty getDia_movi() {
        return dia_movi;
    }

    public void setDia_movi(SimpleIntegerProperty dia_movi) {
        this.dia_movi = dia_movi;
    }

    public SimpleIntegerProperty getCedula() {
        return cedula;
    }

    public void setCedula(SimpleIntegerProperty cedula) {
        this.cedula = cedula;
    }

    public SimpleStringProperty getNombre() {
        return nombre;
    }

    public void setNombre(SimpleStringProperty nombre) {
        this.nombre = nombre;
    }

    public SimpleStringProperty getApellido() {
        return apellido;
    }

    public void setApellido(SimpleStringProperty apellido) {
        this.apellido = apellido;
    }

    public SimpleStringProperty getSexo() {
        return sexo;
    }

    public void setSexo(SimpleStringProperty sexo) {
        this.sexo = sexo;
    }

    public SimpleIntegerProperty getAnioNac() {
        return anioNac;
    }

    public void setAnioNac(SimpleIntegerProperty anioNac) {
        this.anioNac = anioNac;
    }

    public SimpleStringProperty getOcupacion() {
        return ocupacion;
    }

    public void setOcupacion(SimpleStringProperty ocupacion) {
        this.ocupacion = ocupacion;
    }

    public SimpleStringProperty getMotivo_viaje() {
        return motivo_viaje;
    }

    public void setMotivo_viaje(SimpleStringProperty motivo_viaje) {
        this.motivo_viaje = motivo_viaje;
    }

    public SimpleStringProperty getNacionalidad_migr() {
        return nacionalidad_migr;
    }

    public void setNacionalidad_migr(SimpleStringProperty nacionalidad_migr) {
        this.nacionalidad_migr = nacionalidad_migr;
    }

    public SimpleStringProperty getPais_procedencia() {
        return pais_procedencia;
    }

    public void setPais_procedencia(SimpleStringProperty pais_procedencia) {
        this.pais_procedencia = pais_procedencia;
    }

    public SimpleStringProperty getPais_residencia() {
        return pais_residencia;
    }

    public void setPais_residencia(SimpleStringProperty pais_residencia) {
        this.pais_residencia = pais_residencia;
    }

    public SimpleStringProperty getLugar_proveniente() {
        return lugar_proveniente;
    }

    public void setLugar_proveniente(SimpleStringProperty lugar_proveniente) {
        this.lugar_proveniente = lugar_proveniente;
    }
    
    public Integer getAnio_movii() {
        return anio_movi.get();
    }
    
    public Integer getDia_movii() {
        return dia_movi.get();
    }
    
    public Integer getCedulai() {
        return cedula.get();
    }
    
    public Integer getAnioNaci() {
        return anioNac.get();
    }
}
