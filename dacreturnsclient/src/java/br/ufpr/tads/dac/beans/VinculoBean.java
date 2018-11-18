/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufpr.tads.dac.beans;

/**
 *
 * @author luis_
 */
public class VinculoBean {
    private int id;
    private ClinicaBean clinica;
    private PlanoBean plano;

    public ClinicaBean getClinica() {
        return clinica;
    }

    public void setClinica(ClinicaBean clinica) {
        this.clinica = clinica;
    }

    public PlanoBean getPlano() {
        return plano;
    }

    public void setPlano(PlanoBean plano) {
        this.plano = plano;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
