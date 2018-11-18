/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufpr.tads.dac.dao.impl;

import br.ufpr.tads.dac.beans.ClinicaBean;
import br.ufpr.tads.dac.beans.PlanoBean;
import br.ufpr.tads.dac.beans.VinculoBean;
import br.ufpr.tads.dac.dao.VinculoDao;
import com.sun.imageio.plugins.common.I18N;
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
public class VinculoDaoImpl extends GenericDAOImpl implements VinculoDao{

    public VinculoDaoImpl(Connection con) {
        super(con);
    }

    @Override
    public List<VinculoBean> listar() {
        PreparedStatement st = null;
        ResultSet rs = null;
        List<VinculoBean> vinculos = new ArrayList<VinculoBean>();
        try {
            st = con.prepareStatement("select * from tb_clinica_plano");
            rs = st.executeQuery();
            while (rs.next()) {
                VinculoBean vinculo = new VinculoBean();
                vinculo.setId(rs.getInt("id"));
                ClinicaBean clinica = new ClinicaBean();
                PlanoBean plano = new PlanoBean();
                clinica.setId(rs.getInt("id_clinica"));
                vinculo.setClinica(clinica);
                plano.setId(rs.getInt("id_plano"));
                vinculo.setPlano(plano);
                vinculos.add(vinculo);
            }
            return vinculos;
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
            st = con.prepareStatement("DELETE from tb_clinica_plano where id = ?");
            st.setInt(1, id);
            st.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(VinculoDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void inserir(VinculoBean vinculo) {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = con.prepareStatement("INSERT INTO tb_clinica_plano (id_clinica, id_plano)"
                                        + " VALUES (?, ?)");
            st.setInt(1, vinculo.getClinica().getId());
            st.setInt(2, vinculo.getPlano().getId());
            st.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(VinculoDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }

    @Override
    public VinculoBean buscar(int id) {
        PreparedStatement st = null;
        ResultSet rs = null;
        VinculoBean vinculo = new VinculoBean();
        try {
            st = con.prepareStatement("SELECT * FROM tb_clinica_plano WHERE id = ?");
            st.setInt(1, id);
            rs = st.executeQuery();
            while (rs.next()) {
                vinculo.setId(rs.getInt("id"));
                ClinicaBean clinica = new ClinicaBean();
                PlanoBean plano = new PlanoBean();
                clinica.setId(rs.getInt("id_clinica"));
                vinculo.setClinica(clinica);
                plano.setId(rs.getInt("id_plano"));
                vinculo.setPlano(plano);
                break;
            }
            return vinculo;
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
    public void alterar(VinculoBean vinculo) {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = con.prepareStatement("UPDATE tb_clinica_plano SET id_clinica = ? , id_plano = ? WHERE id = ?");
            st.setInt(1, vinculo.getClinica().getId());
            st.setInt(2, vinculo.getPlano().getId());
            st.setInt(3, vinculo.getId());
            st.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(VinculoDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<VinculoBean> listarPorClinica(int id) {
        PreparedStatement st = null;
        ResultSet rs = null;
        List<VinculoBean> planos = new ArrayList<VinculoBean>();
        try {
            st = con.prepareStatement("select * from tb_clinica_plano where id_clinica = ?");
            st.setInt(1, id);
            rs = st.executeQuery();
            while (rs.next()) {
                VinculoBean vinculo = new VinculoBean();
                vinculo.setId(rs.getInt("id"));
                ClinicaBean clinica = new ClinicaBean();
                PlanoBean plano = new PlanoBean();
                clinica.setId(rs.getInt("id_clinica"));
                vinculo.setClinica(clinica);
                plano.setId(rs.getInt("id_plano"));
                vinculo.setPlano(plano);
                planos.add(vinculo);
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
    public List<VinculoBean> listarPorPlano(int id) {
        PreparedStatement st = null;
        ResultSet rs = null;
        List<VinculoBean> planos = new ArrayList<VinculoBean>();
        try {
            st = con.prepareStatement("select * from tb_clinica_plano where id_plano = ?");
            st.setInt(1, id);
            rs = st.executeQuery();
            while (rs.next()) {
                VinculoBean vinculo = new VinculoBean();
                vinculo.setId(rs.getInt("id"));
                ClinicaBean clinica = new ClinicaBean();
                PlanoBean plano = new PlanoBean();
                clinica.setId(rs.getInt("id_clinica"));
                vinculo.setClinica(clinica);
                plano.setId(rs.getInt("id_plano"));
                vinculo.setPlano(plano);
                planos.add(vinculo);
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
    
    
    
}
