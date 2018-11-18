/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufpr.tads.dac.dao.impl;


import br.ufpr.tads.dac.beans.ClinicaBean;
import br.ufpr.tads.dac.beans.MedicoBean;
import br.ufpr.tads.dac.beans.TipoBean;
import br.ufpr.tads.dac.dao.MedicoDao;
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
public class MedicoDaoImpl extends GenericDAOImpl implements MedicoDao{

    public MedicoDaoImpl(Connection con) {
        super(con);
    }

    @Override
    public List<MedicoBean> listar() {
        PreparedStatement st = null;
        ResultSet rs = null;
        List<MedicoBean> medicos = new ArrayList<MedicoBean>();
        try {
            st = con.prepareStatement("select * from tb_medico");
            rs = st.executeQuery();
            while (rs.next()) {
                MedicoBean medico = new MedicoBean();
                medico.setId(rs.getInt("id"));
                medico.setNome(rs.getString("nome"));
                medico.setCrm(rs.getString("crm"));
                ClinicaBean clinica = new ClinicaBean();
                clinica.setId(rs.getInt("id_clinica"));
                medico.setClinica(clinica);
                TipoBean tipo = new TipoBean();
                tipo.setId(rs.getInt("id_tipo"));
                medico.setTipo(tipo);
                medicos.add(medico);
            }
            return medicos;
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
            st = con.prepareStatement("DELETE from tb_medico where id = ?");
            st.setInt(1, id);
            st.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(MedicoDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void inserir(MedicoBean medico) {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = con.prepareStatement("INSERT INTO tb_medico (nome, crm, id_clinica, id_tipo)"
                                        + " VALUES (?, ?, ?, ?)");
            st.setString(1, medico.getNome());
            st.setString(2, medico.getCrm());
            st.setInt(3, medico.getClinica().getId());
            st.setInt(4, medico.getTipo().getId());
            st.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(MedicoDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }

    @Override
    public MedicoBean buscar(int id) {
        PreparedStatement st = null;
        ResultSet rs = null;
        MedicoBean medico = new MedicoBean();
        try {
            st = con.prepareStatement("SELECT * FROM tb_medico WHERE id = ?");
            st.setInt(1, id);
            rs = st.executeQuery();
            while (rs.next()) {
                medico.setId(rs.getInt("id"));
                medico.setNome(rs.getString("nome"));
                medico.setCrm(rs.getString("crm"));
                ClinicaBean clinica = new ClinicaBean();
                clinica.setId(rs.getInt("id_clinica"));
                medico.setClinica(clinica);
                TipoBean tipo = new TipoBean();
                tipo.setId(rs.getInt("id_tipo"));
                medico.setTipo(tipo);
                break;
            }
            return medico;
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
    public void alterar(MedicoBean medico) {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = con.prepareStatement("UPDATE tb_medico SET nome = ?, crm = ?, id_clinica = ?, id_tipo = ? WHERE id = ?");
            st.setString(1, medico.getNome());
            st.setString(2, medico.getCrm());
            st.setInt(3, medico.getClinica().getId());
            st.setInt(4, medico.getTipo().getId());
            st.setInt(5, medico.getId());
            st.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(MedicoDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<MedicoBean> buscarPorClinicaTipo(int idClinica, int idTipo) {
        PreparedStatement st = null;
        ResultSet rs = null;
        List<MedicoBean> medicos = new ArrayList<MedicoBean>();
        try {
            st = con.prepareStatement("select * from tb_medico where id_clinica = ? and id_tipo = ?");
            st.setInt(1, idClinica);
            st.setInt(2, idTipo);
            rs = st.executeQuery();
            while (rs.next()) {
                MedicoBean medico = new MedicoBean();
                medico.setId(rs.getInt("id"));
                medico.setNome(rs.getString("nome"));
                medico.setCrm(rs.getString("crm"));
                ClinicaBean clinica = new ClinicaBean();
                clinica.setId(rs.getInt("id_clinica"));
                medico.setClinica(clinica);
                TipoBean tipo = new TipoBean();
                tipo.setId(rs.getInt("id_tipo"));
                medico.setTipo(tipo);
                medicos.add(medico);
            }
            return medicos;
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
    
    
    
}
