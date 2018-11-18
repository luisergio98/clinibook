/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufpr.tads.dac.facade.impl;

import br.ufpr.tads.dac.beans.ClienteBean;
import br.ufpr.tads.dac.connection.ConnectionFactory;
import br.ufpr.tads.dac.dao.UserDao;
import br.ufpr.tads.dac.dao.impl.UserDaoImpl;
import br.ufpr.tads.dac.facade.UserFacade;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author luis_
 */
public class UserFacadeImpl implements UserFacade{

    private UserDao userDAO;
    
    public UserFacadeImpl(){
        try {
            Connection con = new ConnectionFactory().getConnection();
            this.userDAO = new UserDaoImpl(con);
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UserFacadeImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(UserFacadeImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public UserDao getUserDAO() {
        return userDAO;
    }

    public void setUserDAO(UserDao userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public ClienteBean login(ClienteBean user) {
        return this.userDAO.login(user);
    }
    
    
    
    
    
}
