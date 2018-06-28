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
        
    
    public RegistroMigrante(Persona a, Canton p) {
        this.persona = a;
        this.canton = p;
    }

    public Persona getA() {
        return persona;
    }

    public void setA(Persona a) {
        this.persona = a;
    }

    public Canton getP() {
        return canton;
    }

    public void setP(Canton p) {
        this.canton = p;
    }

    @Override
    public String toString() {
        return "RegistroMigrante{" + "tip_mov=" + tip_mov + ", tipo_nacionalidad=" + tipo_nacionalidad + ", via_transporte=" + via_transporte + ", anio_movi=" + anio_movi + ", mes_movi=" + mes_movi + ", dia_movi=" + dia_movi + ", motivo_viaje=" + motivo_viaje + ", pais_procedencia=" + pais_procedencia + ", pais_residencia=" + pais_residencia + ", lugar_proveniente=" + lugar_proveniente + ", cont_procedencia=" + cont_procedencia + ", cont_residencia=" + cont_residencia + ", subcont_procedencia=" + subcont_procedencia + ", persona=" + persona + ", canton=" + canton + '}';
    }
    
}