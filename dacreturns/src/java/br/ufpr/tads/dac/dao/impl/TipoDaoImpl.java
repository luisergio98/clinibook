/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufpr.tads.dac.dao.impl;


import br.ufpr.tads.dac.beans.TipoBean;
import br.ufpr.tads.dac.dao.TipoDao;
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
public class TipoDaoImpl extends GenericDAOImpl implements TipoDao{

    public TipoDaoImpl(Connection con) {
        super(con);
    }

    @Override
    public List<TipoBean> listar() {
        PreparedStatement st = null;
        ResultSet rs = null;
        List<TipoBean> tipos = new ArrayList<TipoBean>();
        try {
            st = con.prepareStatement("select * from tb_tipo WHERE id > -1");
            rs = st.executeQuery();
            while (rs.next()) {
                TipoBean tipo = new TipoBean();
                tipo.setId(rs.getInt("id"));
                tipo.setNome(rs.getString("nome"));
                tipos.add(tipo);
            }
            return tipos;
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
            st = con.prepareStatement("DELETE from tb_tipo where id = ?");
            st.setInt(1, id);
            st.executeUpdate();
            
            st = con.prepareStatement("UPDATE tb_medico SET id_tipo = -1 WHERE id_tipo = ?");
            st.setInt(1, id);
            st.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(TipoDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void inserir(TipoBean tipo) {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = con.prepareStatement("INSERT INTO tb_tipo (nome)"
                                        + " VALUES (?)");
            st.setString(1, tipo.getNome());
            st.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(TipoDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }

    @Override
    public TipoBean buscar(int id) {
        PreparedStatement st = null;
        ResultSet rs = null;
        TipoBean tipo = new TipoBean();
        try {
            st = con.prepareStatement("SELECT * FROM tb_tipo WHERE id = ?");
            st.setInt(1, id);
            rs = st.executeQuery();
            while (rs.next()) {
                tipo.setId(rs.getInt("id"));
                tipo.setNome(rs.getString("nome"));
                break;
            }
            return tipo;
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
    public void alterar(TipoBean tipo) {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = con.prepareStatement("UPDATE tb_tipo SET nome = ? WHERE id = ?");
            st.setString(1, tipo.getNome());
            st.setInt(2, tipo.getId());
            st.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(TipoDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
}
