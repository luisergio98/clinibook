/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufpr.tads.dac.facade.impl;

import br.ufpr.tads.dac.beans.VinculoBean;
import br.ufpr.tads.dac.connection.ConnectionFactory;
import br.ufpr.tads.dac.dao.VinculoDao;
import br.ufpr.tads.dac.dao.impl.VinculoDaoImpl;
import br.ufpr.tads.dac.facade.VinculoFacade;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author luis_
 */
public class VinculoFacadeImpl implements VinculoFacade{

    private VinculoDao vinculoDAO;
    
    public VinculoFacadeImpl(){
        try {
            Connection con = new ConnectionFactory().getConnection();
            this.vinculoDAO = new VinculoDaoImpl(con);
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(VinculoFacadeImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(VinculoFacadeImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public VinculoDao getVinculoDAO() {
        return vinculoDAO;
    }

    public void setVinculoDAO(VinculoDao vinculoDAO) {
        this.vinculoDAO = vinculoDAO;
    }

    @Override
    public List<VinculoBean> listar() {
        return this.vinculoDAO.listar();
    }

    @Override
    public void remover(int id) {
        this.vinculoDAO.remover(id);
    }

    @Override
    public void inserir(VinculoBean vinculo) {
        this.vinculoDAO.inserir(vinculo);
    }

    @Override
    public VinculoBean buscar(int id) {
        return this.vinculoDAO.buscar(id);
    }

    @Override
    public void alterar(VinculoBean vinculo) {
        this.vinculoDAO.alterar(vinculo);
    }

    @Override
    public List<VinculoBean> listarPorClinica(int id) {         
        return this.vinculoDAO.listarPorClinica(id);
    }

    @Override
    public List<VinculoBean> listarPorPlano(int id) {
        return this.vinculoDAO.listarPorPlano(id);
    }

    

   
    
    
}
