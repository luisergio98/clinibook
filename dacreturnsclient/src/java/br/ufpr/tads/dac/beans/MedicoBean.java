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
public class MedicoBean {
    private int id;
    private String crm;
    private String nome;
    private TipoBean tipo;
    private ClinicaBean clinica;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCrm() {
        return crm;
    }

    public void setCrm(String crm) {
        this.crm = crm;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public TipoBean getTipo() {
        return tipo;
    }

    public void setTipo(TipoBean tipo) {
        this.tipo = tipo;
    }

    public ClinicaBean getClinica() {
        return clinica;
    }

    public void setClinica(ClinicaBean clinica) {
        this.clinica = clinica;
    }
}
