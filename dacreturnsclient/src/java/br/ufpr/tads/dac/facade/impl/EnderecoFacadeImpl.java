/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufpr.tads.dac.facade.impl;


import br.ufpr.tads.dac.beans.EnderecoBean;
import br.ufpr.tads.dac.connection.ConnectionFactory;
import br.ufpr.tads.dac.dao.EnderecoDao;
import br.ufpr.tads.dac.dao.impl.EnderecoDaoImpl;
import br.ufpr.tads.dac.facade.EnderecoFacade;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author luis_
 */
public class EnderecoFacadeImpl implements EnderecoFacade{

    private EnderecoDao enderecoDAO;
    
    public EnderecoFacadeImpl(){
        try {
            Connection con = new ConnectionFactory().getConnection();
            this.enderecoDAO = new EnderecoDaoImpl(con);
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(EnderecoFacadeImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(EnderecoFacadeImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public EnderecoDao getEnderecoDAO() {
        return enderecoDAO;
    }

    public void setEnderecoDAO(EnderecoDao enderecoDAO) {
        this.enderecoDAO = enderecoDAO;
    }

    @Override
    public List<EnderecoBean> listar() {
        return getEnderecoDAO().listar();
    }

    @Override
    public void remover(int id) {
        getEnderecoDAO().remover(id);
    }

    @Override
    public void inserir(EnderecoBean endereco) {
        getEnderecoDAO().inserir(endereco);
    }

    @Override
    public EnderecoBean buscar(int id) {
        return getEnderecoDAO().buscar(id);
    }

    @Override
    public void alterar(EnderecoBean endereco) {
        getEnderecoDAO().alterar(endereco);
    }

    @Override
    public EnderecoBean buscarUltimoInserido() {
        return getEnderecoDAO().buscarUltimoInserido();
    }

    

   
    
    
}
