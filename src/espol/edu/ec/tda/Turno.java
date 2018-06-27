/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package espol.edu.ec.tda;

/**
 *
 * @author Kenny Camba
 */
public class Turno {
    
    private String numero;
    private Estado estado;
    private Atencion tipoAtencion;
    private Puesto puesto;
    
    public Turno(String numero, Atencion tipoAtencion, Puesto puesto) {
        this.numero = numero;
        this.tipoAtencion = tipoAtencion;
        this.puesto = puesto;
        estado = Estado.EN_ESPERA;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public Atencion getTipoAtencion() {
        return tipoAtencion;
    }

    public void setTipoAtencion(Atencion tipoAtencion) {
        this.tipoAtencion = tipoAtencion;
    }

    public Puesto getPuesto() {
        return puesto;
    }

    public void setPuesto(Puesto puesto) {
        this.puesto = puesto;
    }
    
    @Override
    public String toString() {
        return "Numero: "+ numero + ", Puesto: "+ puesto.getPuesto();
    }
}
