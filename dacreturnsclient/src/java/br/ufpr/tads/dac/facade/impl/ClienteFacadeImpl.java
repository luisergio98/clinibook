/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufpr.tads.dac.facade.impl;

import br.ufpr.tads.dac.beans.ClienteBean;
import br.ufpr.tads.dac.connection.ConnectionFactory;
import br.ufpr.tads.dac.dao.ClienteDao;
import br.ufpr.tads.dac.dao.UserDao;
import br.ufpr.tads.dac.dao.impl.ClienteDaoImpl;
import br.ufpr.tads.dac.dao.impl.UserDaoImpl;
import br.ufpr.tads.dac.facade.ClienteFacade;
import br.ufpr.tads.dac.facade.UserFacade;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author luis_
 */
public class ClienteFacadeImpl implements ClienteFacade{

    private ClienteDao clienteDAO;
    
    public ClienteFacadeImpl(){
        try {
            Connection con = new ConnectionFactory().getConnection();
            this.clienteDAO = new ClienteDaoImpl(con);
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ClienteFacadeImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ClienteFacadeImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ClienteDao getUserDAO() {
        return clienteDAO;
    }

    public void setUserDAO(ClienteDao clienteDAO) {
        this.clienteDAO = clienteDAO;
    }

    @Override
    public ClienteBean buscar(int id) {
        return this.clienteDAO.buscar(id);
    }

    @Override
    public void remover(int id) {
        this.clienteDAO.remover(id);
    }

    @Override
    public void inserir(ClienteBean cliente) {
        this.clienteDAO.inserir(cliente);
    }

    @Override
    public void alterar(ClienteBean cliente) {
        this.clienteDAO.alterar(cliente);
    }
    
    
    
    
    
}
