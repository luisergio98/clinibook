/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufpr.tads.dac.dao.impl;

import br.ufpr.tads.dac.beans.PlanoBean;
import br.ufpr.tads.dac.dao.PlanoDao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author luis_
 */
public class PlanoDaoImpl extends GenericDAOImpl implements PlanoDao{

    public PlanoDaoImpl(Connection con) {
        super(con);
    }

    @Override
    public List<PlanoBean> listar() {
        PreparedStatement st = null;
        ResultSet rs = null;
        List<PlanoBean> planos = new ArrayList<PlanoBean>();
        try {
            st = con.prepareStatement("select * from tb_plano");
            rs = st.executeQuery();
            while (rs.next()) {
                PlanoBean plano = new PlanoBean();
                plano.setId(rs.getInt("id"));
                plano.setNome(rs.getString("nome"));
                planos.add(plano);
            }
            return planos;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (Exception ex) {
                }
            }
            if (st != null) {
                try {
                    st.close();
                } catch (Exception ex) {
                }
            }
        }  
    }

    @Override
    public void remover(int id) {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = con.prepareStatement("DELETE from tb_plano where id = ?");
            st.setInt(1, id);
            st.executeUpdate();
            
            st = con.prepareStatement("DELETE from tb_clinica_plano where id_plano = ?");
            st.setInt(1, id);
            st.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(PlanoDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void inserir(PlanoBean plano) {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = con.prepareStatement("INSERT INTO tb_plano (nome)"
                                        + " VALUES (?)");
            st.setString(1, plano.getNome());
            st.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(PlanoDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }

    @Override
    public PlanoBean buscar(int id) {
        PreparedStatement st = null;
        ResultSet rs = null;
        PlanoBean plano = new PlanoBean();
        try {
            st = con.prepareStatement("SELECT * FROM tb_plano WHERE id = ?");
            st.setInt(1, id);
            rs = st.executeQuery();
            while (rs.next()) {
                plano.setId(rs.getInt("id"));
                plano.setNome(rs.getString("nome"));
                break;
            }
            return plano;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (Exception ex) {
                }
            }
            if (st != null) {
                try {
                    st.close();
                } catch (Exception ex) {
                }
            }
        }
    }

    @Override
    public void alterar(PlanoBean plano) {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = con.prepareStatement("UPDATE tb_plano SET nome = ? WHERE id = ?");
            st.setString(1, plano.getNome());
            st.setInt(2, plano.getId());
            st.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(PlanoDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
}
