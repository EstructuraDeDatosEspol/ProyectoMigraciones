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
public class SubContinente {
    private int codigo;
    private int codContinente;
    private String subcontinente;

    public SubContinente(int codigo, int codContinente, String subcontinente) {
        this.codigo = codigo;
        this.codContinente = codContinente;
        this.subcontinente = subcontinente;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public int getCodContinente() {
        return codContinente;
    }

    public void setCodContinente(int codContinente) {
        this.codContinente = codContinente;
    }

    public String getSubcontinente() {
        return subcontinente;
    }

    public void setSubcontinente(String subcontinente) {
        this.subcontinente = subcontinente;
    }
    
    
}
