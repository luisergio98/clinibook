/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufpr.tads.dac.facade.impl;

import br.ufpr.tads.dac.beans.AdministradorBean;
import br.ufpr.tads.dac.beans.PlanoBean;
import br.ufpr.tads.dac.connection.ConnectionFactory;
import br.ufpr.tads.dac.dao.PlanoDao;
import br.ufpr.tads.dac.dao.UserDao;
import br.ufpr.tads.dac.dao.impl.PlanoDaoImpl;
import br.ufpr.tads.dac.dao.impl.UserDaoImpl;
import br.ufpr.tads.dac.facade.PlanoFacade;
import br.ufpr.tads.dac.facade.UserFacade;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author luis_
 */
public class PlanoFacadeImpl implements PlanoFacade{

    private PlanoDao planoDAO;
    
    public PlanoFacadeImpl(){
        try {
            Connection con = new ConnectionFactory().getConnection();
            this.planoDAO = new PlanoDaoImpl(con);
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(PlanoFacadeImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(PlanoFacadeImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public PlanoDao getPlanoDAO() {
        return planoDAO;
    }

    public void setPlanoDAO(PlanoDao planoDAO) {
        this.planoDAO = planoDAO;
    }

    @Override
    public List<PlanoBean> listar() {
        return getPlanoDAO().listar();
    }

    @Override
    public void remover(int id) {
        getPlanoDAO().remover(id);
    }

    @Override
    public void inserir(PlanoBean plano) {
        getPlanoDAO().inserir(plano);
    }

    @Override
    public PlanoBean buscar(int id) {
        return getPlanoDAO().buscar(id);
    }

    @Override
    public void alterar(PlanoBean plano) {
        getPlanoDAO().alterar(plano);
    }

    

   
    
    
}
