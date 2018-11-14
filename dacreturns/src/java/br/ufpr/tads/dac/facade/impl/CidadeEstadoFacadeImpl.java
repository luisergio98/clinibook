/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufpr.tads.dac.facade.impl;

import br.ufpr.tads.dac.facade.CidadeEstadoFacade;
import br.ufpr.tads.dac.beans.CidadeBean;
import br.ufpr.tads.dac.beans.EstadoBean;
import br.ufpr.tads.dac.connection.ConnectionFactory;
import br.ufpr.tads.dac.dao.CidadeEstadoDao;
import br.ufpr.tads.dac.dao.impl.CidadeEstadoDaoImpl;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author luis_
 */
public class CidadeEstadoFacadeImpl implements CidadeEstadoFacade{

    private CidadeEstadoDao cidadeEstadoDAO;
    
    public CidadeEstadoFacadeImpl(){
        try {
            Connection con = new ConnectionFactory().getConnection();
            this.cidadeEstadoDAO = new CidadeEstadoDaoImpl(con);
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CidadeEstadoFacadeImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(CidadeEstadoFacadeImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public CidadeEstadoDao getCidadeEstadoDAO() {
        return cidadeEstadoDAO;
    }

    public void setCidadeEstadoDAO(CidadeEstadoDao cidadadeEstadoDAO) {
        this.cidadeEstadoDAO = cidadeEstadoDAO;
    }

    @Override
    public List<CidadeBean> listarCidades() {
        return this.cidadeEstadoDAO.listarCidades();
    }

    @Override
    public List<EstadoBean> listarEstados() {
        return this.cidadeEstadoDAO.listarEstados();
    }

    @Override
    public CidadeBean buscarCidade(int id) {
        return this.cidadeEstadoDAO.buscarCidade(id);
    }

    @Override
    public EstadoBean buscarEstado(int id) {
        return this.cidadeEstadoDAO.buscarEstado(id);
    }

    @Override
    public List<CidadeBean> listarCidadesPorEstado(int id) {
        return this.cidadeEstadoDAO.listarCidadesPorEstado(id);
    }

    

    

   
    
    
}
