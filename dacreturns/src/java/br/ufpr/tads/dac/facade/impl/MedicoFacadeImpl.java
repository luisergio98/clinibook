/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufpr.tads.dac.facade.impl;

import br.ufpr.tads.dac.beans.MedicoBean;
import br.ufpr.tads.dac.connection.ConnectionFactory;
import br.ufpr.tads.dac.dao.MedicoDao;
import br.ufpr.tads.dac.dao.impl.MedicoDaoImpl;
import br.ufpr.tads.dac.facade.MedicoFacade;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author luis_
 */
public class MedicoFacadeImpl implements MedicoFacade{

    private MedicoDao medicoDAO;
    
    public MedicoFacadeImpl(){
        try {
            Connection con = new ConnectionFactory().getConnection();
            this.medicoDAO = new MedicoDaoImpl(con);
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MedicoFacadeImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(MedicoFacadeImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public MedicoDao getMedicoDAO() {
        return medicoDAO;
    }

    public void setMedicoDAO(MedicoDao medicoDAO) {
        this.medicoDAO = medicoDAO;
    }

    @Override
    public List<MedicoBean> listar() {
        return getMedicoDAO().listar();
    }

    @Override
    public void remover(int id) {
        getMedicoDAO().remover(id);
    }

    @Override
    public void inserir(MedicoBean medico) {
        getMedicoDAO().inserir(medico);
    }

    @Override
    public MedicoBean buscar(int id) {
        return getMedicoDAO().buscar(id);
    }

    @Override
    public void alterar(MedicoBean medico) {
        getMedicoDAO().alterar(medico);
    }

    @Override
    public List<MedicoBean> buscarPorClinicaTipo(int idClinica, int idTipo) {
        return this.medicoDAO.buscarPorClinicaTipo(idClinica, idTipo);
    }

    

   
    
    
}
