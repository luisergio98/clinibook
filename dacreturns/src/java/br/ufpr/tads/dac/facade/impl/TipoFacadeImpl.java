/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufpr.tads.dac.facade.impl;

import br.ufpr.tads.dac.beans.TipoBean;
import br.ufpr.tads.dac.connection.ConnectionFactory;
import br.ufpr.tads.dac.dao.TipoDao;
import br.ufpr.tads.dac.dao.impl.TipoDaoImpl;
import br.ufpr.tads.dac.facade.TipoFacade;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author luis_
 */
public class TipoFacadeImpl implements TipoFacade{

    private TipoDao tipoDAO;
    
    public TipoFacadeImpl(){
        try {
            Connection con = new ConnectionFactory().getConnection();
            this.tipoDAO = new TipoDaoImpl(con);
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(TipoFacadeImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(TipoFacadeImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public TipoDao getTipoDAO() {
        return tipoDAO;
    }

    public void setTipoDAO(TipoDao tipoDAO) {
        this.tipoDAO = tipoDAO;
    }

    @Override
    public List<TipoBean> listar() {
        return getTipoDAO().listar();
    }

    @Override
    public void remover(int id) {
        getTipoDAO().remover(id);
    }

    @Override
    public void inserir(TipoBean tipo) {
        getTipoDAO().inserir(tipo);
    }

    @Override
    public TipoBean buscar(int id) {
        return getTipoDAO().buscar(id);
    }

    @Override
    public void alterar(TipoBean tipo) {
        getTipoDAO().alterar(tipo);
    }

    

   
    
    
}
