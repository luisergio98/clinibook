/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufpr.tads.dac.beans;

import java.util.List;

/**
 *
 * @author luis_
 */
public class ClinicaBean {
        private int id;
        private String nome;
        private String telefone;
        private String email;
        private int minIdade;
        private int maxIdade;
        private EnderecoBean endereco;
        private List<MedicoBean> medicos;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getMinIdade() {
        return minIdade;
    }

    public void setMinIdade(int minIdade) {
        this.minIdade = minIdade;
    }

    public int getMaxIdade() {
        return maxIdade;
    }

    public void setMaxIdade(int maxIdade) {
        this.maxIdade = maxIdade;
    }

    public EnderecoBean getEndereco() {
        return endereco;
    }

    public void setEndereco(EnderecoBean endereco) {
        this.endereco = endereco;
    }

    public List<MedicoBean> getMedicos() {
        return medicos;
    }

    public void setMedicos(List<MedicoBean> medicos) {
        this.medicos = medicos;
    }

    
}
