/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package espol.edu.ec.registro;

/**
 *
 * @author SSAM
 */
public class RegistroMigrante {
    private String tip_mov;
    private String tipo_nacionalidad;
    private String via_transporte;
    private int anio_movi;
    private String mes_movi;
    private int dia_movi;
    private String motivo_viaje;
    private String pais_procedencia;
    private String pais_residencia;
    private String lugar_proveniente;
    private String cont_procedencia;
    private String cont_residencia;
    private String subcont_procedencia;
    private Persona persona;
    private Canton canton;

    public RegistroMigrante(String tip_mov, String tipo_nacionalidad, String via_transporte, int anio_movi, String mes_movi, int dia_movi, String motivo_viaje, String pais_procedencia, String pais_residencia, String lugar_proveniente, String cont_procedencia, String cont_residencia, String subcont_procedencia, Persona persona, Canton canton) {
        this.tip_mov = tip_mov;
        this.tipo_nacionalidad = tipo_nacionalidad;
        this.via_transporte = via_transporte;
        this.anio_movi = anio_movi;
        this.mes_movi = mes_movi;
        this.dia_movi = dia_movi;
        this.motivo_viaje = motivo_viaje;
        this.pais_procedencia = pais_procedencia;
        this.pais_residencia = pais_residencia;
        this.lugar_proveniente = lugar_proveniente;
        this.cont_procedencia = cont_procedencia;
        this.cont_residencia = cont_residencia;
        this.subcont_procedencia = subcont_procedencia;
        this.persona = persona;
        this.canton = canton;
    }

    public String getTip_mov() {
        return tip_mov;
    }

    public void setTip_mov(String tip_mov) {
        this.tip_mov = tip_mov;
    }

    public String getTipo_nacionalidad() {
        return tipo_nacionalidad;
    }

    public void setTipo_nacionalidad(String tipo_nacionalidad) {
        this.tipo_nacionalidad = tipo_nacionalidad;
    }

    public String getVia_transporte() {
        return via_transporte;
    }

    public void setVia_transporte(String via_transporte) {
        this.via_transporte = via_transporte;
    }

    public int getAnio_movi() {
        return anio_movi;
    }

    public void setAnio_movi(int anio_movi) {
        this.anio_movi = anio_movi;
    }

    public String getMes_movi() {
        return mes_movi;
    }

    public void setMes_movi(String mes_movi) {
        this.mes_movi = mes_movi;
    }

    public int getDia_movi() {
        return dia_movi;
    }

    public void setDia_movi(int dia_movi) {
        this.dia_movi = dia_movi;
    }

    public String getMotivo_viaje() {
        return motivo_viaje;
    }

    public void setMotivo_viaje(String motivo_viaje) {
        this.motivo_viaje = motivo_viaje;
    }

    public String getPais_procedencia() {
        return pais_procedencia;
    }

    public void setPais_procedencia(String pais_procedencia) {
        this.pais_procedencia = pais_procedencia;
    }

    public String getPais_residencia() {
        return pais_residencia;
    }

    public void setPais_residencia(String pais_residencia) {
        this.pais_residencia = pais_residencia;
    }

    public String getLugar_proveniente() {
        return lugar_proveniente;
    }

    public void setLugar_proveniente(String lugar_proveniente) {
        this.lugar_proveniente = lugar_proveniente;
    }

    public String getCont_procedencia() {
        return cont_procedencia;
    }

    public void setCont_procedencia(String cont_procedencia) {
        this.cont_procedencia = cont_procedencia;
    }

    public String getCont_residencia() {
        return cont_residencia;
    }

    public void setCont_residencia(String cont_residencia) {
        this.cont_residencia = cont_residencia;
    }

    public String getSubcont_procedencia() {
        return subcont_procedencia;
    }

    public void setSubcont_procedencia(String subcont_procedencia) {
        this.subcont_procedencia = subcont_procedencia;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public Canton getCanton() {
        return canton;
    }

    public void setCanton(Canton canton) {
        this.canton = canton;
    }

    @Override
    public String toString() {
        return "RegistroMigrante[" + "tip_mov=" + tip_mov + ", tipo_nacionalidad=" + tipo_nacionalidad + ", via_transporte=" + via_transporte + ", anio_movi=" + anio_movi + ", mes_movi=" + mes_movi + ", dia_movi=" + dia_movi + ", motivo_viaje=" + motivo_viaje + ", pais_procedencia=" + pais_procedencia + ", pais_residencia=" + pais_residencia + ", lugar_proveniente=" + lugar_proveniente + ", cont_procedencia=" + cont_procedencia + ", cont_residencia=" + cont_residencia + ", subcont_procedencia=" + subcont_procedencia +" "+ persona + " " + canton +']';
    }
    
}