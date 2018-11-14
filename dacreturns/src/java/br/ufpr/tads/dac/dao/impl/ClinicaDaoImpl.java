/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufpr.tads.dac.dao.impl;

import br.ufpr.tads.dac.beans.ClinicaBean;
import br.ufpr.tads.dac.beans.EnderecoBean;
import br.ufpr.tads.dac.dao.ClinicaDao;
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
public class ClinicaDaoImpl extends GenericDAOImpl implements ClinicaDao{

    public ClinicaDaoImpl(Connection con) {
        super(con);
    }

    @Override
    public List<ClinicaBean> listar() {
        PreparedStatement st = null;
        ResultSet rs = null;
        List<ClinicaBean> clinicas = new ArrayList<ClinicaBean>();
        try {
            st = con.prepareStatement("select * from tb_clinica WHERE id > -1");
            rs = st.executeQuery();
            while (rs.next()) {
                ClinicaBean clinica = new ClinicaBean();
                clinica.setId(rs.getInt("id"));
                clinica.setNome(rs.getString("nome"));
                clinica.setTelefone(rs.getString("telefone"));
                clinica.setEmail(rs.getString("email"));
                clinica.setMinIdade(rs.getInt("idade_de"));
                clinica.setMaxIdade(rs.getInt("idade_ate"));
                EnderecoBean endereco =  new EnderecoBean();
                endereco.setId(rs.getInt("id_endereco"));
                clinica.setEndereco(endereco);
                clinicas.add(clinica);
            }
            return clinicas;
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
            st = con.prepareStatement("DELETE from tb_clinica where id = ?");
            st.setInt(1, id);
            st.executeUpdate();
            
            st = con.prepareStatement("DELETE from tb_clinica_plano where id_clinica = ?");
            st.setInt(1, id);
            st.executeUpdate();
            
            st = con.prepareStatement("UPDATE tb_medico SET id_clinica = -1 WHERE id_clinica = ?");
            st.setInt(1, id);
            st.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ClinicaDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void inserir(ClinicaBean clinica) {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = con.prepareStatement("INSERT INTO tb_clinica (nome, telefone, email, idade_de, idade_ate, id_endereco)"
                                        + " VALUES (?, ?, ?, ?, ?, ?)");
            st.setString(1, clinica.getNome());
            st.setString(2, clinica.getTelefone());
            st.setString(3, clinica.getEmail());
            st.setInt(4, clinica.getMinIdade());
            st.setInt(5, clinica.getMaxIdade());
            st.setInt(6, clinica.getEndereco().getId());
            st.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ClinicaDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }

    @Override
    public ClinicaBean buscar(int id) {
        PreparedStatement st = null;
        ResultSet rs = null;
        ClinicaBean clinica = new ClinicaBean();
        try {
            st = con.prepareStatement("SELECT * FROM tb_clinica WHERE id = ?");
            st.setInt(1, id);
            rs = st.executeQuery();
            while (rs.next()) {
                clinica.setId(rs.getInt("id"));
                clinica.setNome(rs.getString("nome"));
                clinica.setTelefone(rs.getString("telefone"));
                clinica.setEmail(rs.getString("email"));
                clinica.setMinIdade(rs.getInt("idade_de"));
                clinica.setMaxIdade(rs.getInt("idade_ate"));
                EnderecoBean endereco =  new EnderecoBean();
                endereco.setId(rs.getInt("id_endereco"));
                clinica.setEndereco(endereco);
                break;
            }
            return clinica;
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
    public void alterar(ClinicaBean clinica) {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = con.prepareStatement("UPDATE tb_clinica SET nome = ?, telefone = ?, email = ?, idade_de = ?, idade_ate = ?, id_endereco = ?  WHERE id = ?");
            st.setString(1, clinica.getNome());
            st.setString(2, clinica.getTelefone());
            st.setString(3, clinica.getEmail());
            st.setInt(4, clinica.getMinIdade());
            st.setInt(5, clinica.getMaxIdade());
            st.setInt(6, clinica.getEndereco().getId());
            st.setInt(7, clinica.getId());
            st.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ClinicaDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
}
