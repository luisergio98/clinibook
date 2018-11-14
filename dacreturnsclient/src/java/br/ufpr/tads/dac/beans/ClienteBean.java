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
public class ClienteBean {
    private int id;
    private String nome;
    private String telefone;
    private String email;
    private String senha;
    private double distanciaMaxima;
    private int idade;
    private PlanoBean plano;
    private EnderecoBean endereco;

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

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public double getDistanciaMaxima() {
        return distanciaMaxima;
    }

    public void setDistanciaMaxima(double distanciaMaxima) {
        this.distanciaMaxima = distanciaMaxima;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public PlanoBean getPlano() {
        return plano;
    }

    public void setPlano(PlanoBean plano) {
        this.plano = plano;
    }

    public EnderecoBean getEndereco() {
        return endereco;
    }

    public void setEndereco(EnderecoBean endereco) {
        this.endereco = endereco;
    }
}
