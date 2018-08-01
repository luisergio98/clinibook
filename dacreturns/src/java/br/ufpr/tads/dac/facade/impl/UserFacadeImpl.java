/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufpr.tads.dac.facade.impl;

import br.ufpr.tads.dac.beans.AdministradorBean;
import br.ufpr.tads.dac.dao.UserDao;
import br.ufpr.tads.dac.facade.UserFacade;

/**
 *
 * @author luis_
 */
public class UserFacadeImpl implements UserFacade{
    
    public UserDao getUserDAO() {
        return UserDAO;
    }

    public void setUserDAO(UserDao UserDAO) {
        this.UserDAO = UserDAO;
    }

    private UserDao UserDAO;
    
    @Override
    public AdministradorBean loginAdmin(AdministradorBean admin) {
        return getUserDAO().loginAdmin(admin);
    }
    
    
    
}
