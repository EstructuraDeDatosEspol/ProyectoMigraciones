/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package espol.edu.ec.moduloDeRegistro;

import espol.edu.ec.tda.entidades.Canton;
import espol.edu.ec.tda.entidades.Persona;

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
    private String provincia_movi;
    

    public RegistroMigrante(String tip_mov, String tipo_nacionalidad, String via_transporte, int anio_movi, String mes_movi, int dia_movi, String motivo_viaje, String pais_procedencia, String pais_residencia, String lugar_proveniente, String cont_procedencia, String cont_residencia, String subcont_procedencia, Persona persona, Canton canton, String provincia_movi) {
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
        this.provincia_movi = provincia_movi;
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

    public String getProvincia_movi() {
        return provincia_movi;
    }

    public void setProvincia_movi(String provincia_movi) {
        this.provincia_movi = provincia_movi;
    }
    
    public void volverNull(){
        this.tip_mov = null;
        this.tipo_nacionalidad = null;
        this.via_transporte = null;
        this.anio_movi = 0;
        this.mes_movi = null;
        this.dia_movi = 0;
        this.motivo_viaje = null;
        this.pais_procedencia = null;
        this.pais_residencia = null;
        this.lugar_proveniente = null;
        this.cont_procedencia = null;
        this.cont_residencia = null;
        this.subcont_procedencia = null;
        this.persona = null;
        this.canton = null;
        this.provincia_movi = null;
    }
    
    public String texto(){
        StringBuilder bld = new StringBuilder();
        bld.append(tip_mov);
        bld.append(";");
        bld.append(tipo_nacionalidad);
        bld.append(";");
        bld.append(via_transporte);
        bld.append(";");
        bld.append(provincia_movi);
        bld.append(";");
        bld.append(canton.toString());
        bld.append(";");
        bld.append(anio_movi);
        bld.append(";");
        bld.append(mes_movi);
        bld.append(";");
        bld.append(dia_movi);
        bld.append(";");
        bld.append(persona.getCedula());
        bld.append(";");
        bld.append(persona.getNombre());
        bld.append(";");
        bld.append(persona.getApellido());
        bld.append(";");   
        bld.append(persona.getSexo());
        bld.append(";");
        bld.append(persona.getAnioNacimiento());
        bld.append(";");
        bld.append(persona.getOcupacion());
        bld.append(";");
        bld.append(motivo_viaje);
        bld.append(";");
        bld.append(persona.getNacionalidad());
        bld.append(";");
        bld.append(pais_procedencia);
        bld.append(";");
        bld.append(pais_residencia);
        bld.append(";");
        bld.append(lugar_proveniente);
        bld.append(";");
        bld.append(persona.getEdad());
        bld.append(";");
        bld.append(cont_procedencia);
        bld.append(";");
        bld.append(cont_residencia);
        bld.append(";");
        bld.append(persona.getContinenteNacimiento());
        bld.append(";");
        bld.append(subcont_procedencia);
        bld.append(";");
        bld.append(persona.getSubcontnacionalidad());
        return bld.toString();
    }

    @Override
    public String toString() {
        if(tip_mov == null)return "borrado";
        return "tip_mov=" + tip_mov + ", pais_Mov=" + tipo_nacionalidad +", canton_Mov=" + canton.getNombre() + ", via_transporte=" + via_transporte + ", anio=" + anio_movi + ", mes=" + mes_movi + ", dia=" + dia_movi + ", motivo=" + motivo_viaje ;
    }
    
    @Override
    public boolean equals(Object o){
        if(o == null || !(o instanceof RegistroMigrante))return false;
        RegistroMigrante p = (RegistroMigrante)o;
        //if()
        return persona.equals(p.persona) && canton.equals(p.canton) && tip_mov.equals(p.tip_mov) && anio_movi == p.anio_movi && mes_movi.equals(p.mes_movi) && dia_movi == p.dia_movi && motivo_viaje.equals(p.motivo_viaje);
    }
}