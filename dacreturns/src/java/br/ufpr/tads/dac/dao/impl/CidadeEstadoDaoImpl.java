/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufpr.tads.dac.dao.impl;

import br.ufpr.tads.dac.beans.CidadeBean;
import br.ufpr.tads.dac.beans.EstadoBean;
import br.ufpr.tads.dac.dao.CidadeEstadoDao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author luis_
 */
public class CidadeEstadoDaoImpl extends GenericDAOImpl implements CidadeEstadoDao{

    public CidadeEstadoDaoImpl(Connection con) {
        super(con);
    }

    @Override
    public List<CidadeBean> listarCidades() {
        PreparedStatement st = null;
        ResultSet rs = null;
        List<CidadeBean> cidades = new ArrayList<CidadeBean>();
        try {
            st = con.prepareStatement("select * from tb_cidade WHERE id > -1");
            rs = st.executeQuery();
            while (rs.next()) {
                CidadeBean cidade = new CidadeBean();
                cidade.setId(rs.getInt("id"));
                cidade.setNome(rs.getString("nome"));
                EstadoBean estado = new EstadoBean();
                estado = buscarEstado(rs.getInt("id_estado"));
                cidade.setEstado(estado);
                cidades.add(cidade);
            }
            return cidades;
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
    public List<EstadoBean> listarEstados() {
        PreparedStatement st = null;
        ResultSet rs = null;
        List<EstadoBean> estados = new ArrayList<EstadoBean>();
        try {
            st = con.prepareStatement("select * from tb_estado WHERE id > -1");
            rs = st.executeQuery();
            while (rs.next()) {
                EstadoBean estado = new EstadoBean();
                estado.setId(rs.getInt("id"));
                estado.setNome(rs.getString("nome"));
                estado.setUf(rs.getString("uf"));
                estados.add(estado);
            }
            return estados;
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
    public CidadeBean buscarCidade(int id) {
        PreparedStatement st = null;
        ResultSet rs = null;
        CidadeBean cidade = new CidadeBean();
        try {
            st = con.prepareStatement("SELECT * FROM tb_cidade WHERE id = ?");
            st.setInt(1, id);
            rs = st.executeQuery();
            while (rs.next()) {
                cidade.setId(rs.getInt("id"));
                cidade.setNome(rs.getString("nome"));
                EstadoBean estado = new EstadoBean();
                estado = buscarEstado(rs.getInt("id_estado"));
                cidade.setEstado(estado);
                break;
            }
            return cidade;
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
    
    public EstadoBean buscarEstado(int id) {
        PreparedStatement st = null;
        ResultSet rs = null;
        EstadoBean estado = new EstadoBean();
        try {
            st = con.prepareStatement("SELECT * FROM tb_estado WHERE id = ?");
            st.setInt(1, id);
            rs = st.executeQuery();
            while (rs.next()) {
                estado.setId(rs.getInt("id"));
                estado.setNome(rs.getString("nome"));
                estado.setUf(rs.getString("uf"));
                break;
            }
            return estado;
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
    public List<CidadeBean> listarCidadesPorEstado(int id) {
        PreparedStatement st = null;
        ResultSet rs = null;
        List<CidadeBean> cidades = new ArrayList<CidadeBean>();
        try {
            st = con.prepareStatement("select * from tb_cidade where id_estado = ?");
            st.setInt(1, id);
            rs = st.executeQuery();
            while (rs.next()) {
                CidadeBean cidade = new CidadeBean();
                cidade.setId(rs.getInt("id"));
                cidade.setNome(rs.getString("nome"));
                EstadoBean estado = new EstadoBean();
                estado = buscarEstado(rs.getInt("id_estado"));
                cidade.setEstado(estado);
                cidades.add(cidade);
            }
            return cidades;
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
