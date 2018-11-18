/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufpr.tads.dac.facade.impl;

import br.ufpr.tads.dac.beans.ClinicaBean;
import br.ufpr.tads.dac.connection.ConnectionFactory;
import br.ufpr.tads.dac.dao.ClinicaDao;
import br.ufpr.tads.dac.dao.impl.ClinicaDaoImpl;
import br.ufpr.tads.dac.facade.ClinicaFacade;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author luis_
 */
public class ClinicaFacadeImpl implements ClinicaFacade{

    private ClinicaDao ClinicaDAO;
    
    public ClinicaFacadeImpl(){
        try {
            Connection con = new ConnectionFactory().getConnection();
            this.ClinicaDAO = new ClinicaDaoImpl(con);
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ClinicaFacadeImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ClinicaFacadeImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ClinicaDao getClinicaDAO() {
        return ClinicaDAO;
    }

    public void setClinicaDAO(ClinicaDao ClinicaDAO) {
        this.ClinicaDAO = ClinicaDAO;
    }

    @Override
    public List<ClinicaBean> listar() {
        return getClinicaDAO().listar();
    }

    @Override
    public void remover(int id) {
        getClinicaDAO().remover(id);
    }

    @Override
    public void inserir(ClinicaBean clinica) {
        getClinicaDAO().inserir(clinica);
    }

    @Override
    public ClinicaBean buscar(int id) {
        return getClinicaDAO().buscar(id);
    }

    @Override
    public void alterar(ClinicaBean clinica) {
        getClinicaDAO().alterar(clinica);
    }

    

   
    
    
}
