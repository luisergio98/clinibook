/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufpr.tads.dac.dao.impl;

import br.ufpr.tads.dac.beans.CidadeBean;
import br.ufpr.tads.dac.beans.EnderecoBean;
import br.ufpr.tads.dac.beans.EstadoBean;
import br.ufpr.tads.dac.dao.EnderecoDao;
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
public class EnderecoDaoImpl extends GenericDAOImpl implements EnderecoDao{

    public EnderecoDaoImpl(Connection con) {
        super(con);
    }

    @Override
    public List<EnderecoBean> listar() {
        PreparedStatement st = null;
        ResultSet rs = null;
        List<EnderecoBean> enderecos = new ArrayList<EnderecoBean>();
        try {
            st = con.prepareStatement("select * from tb_endereco");
            rs = st.executeQuery();
            while (rs.next()) {
                EnderecoBean endereco = new EnderecoBean();
                endereco.setId(rs.getInt("id"));
                endereco.setBairro(rs.getString("bairro"));
                endereco.setCep(rs.getString("cep"));
                endereco.setLogradouro(rs.getString("logradouro"));
                endereco.setNumero(rs.getInt("numero"));
                EstadoBean estado = new EstadoBean();
                estado.setId(rs.getInt("id_estado"));
                endereco.setEstado(estado);
                CidadeBean cidade = new CidadeBean();
                cidade.setId(rs.getInt("id_cidade"));
                enderecos.add(endereco);
            }
            return enderecos;
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
            st = con.prepareStatement("DELETE from tb_endereco where id = ?");
            st.setInt(1, id);
            st.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(EnderecoDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void inserir(EnderecoBean endereco) {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = con.prepareStatement("INSERT INTO tb_endereco (cep, logradouro, bairro, numero, id_estado, id_cidade)"
                                        + " VALUES (?, ?, ?, ?, ?, ?)");
            st.setString(1, endereco.getCep());
            st.setString(2, endereco.getLogradouro());
            st.setString(3, endereco.getBairro());
            st.setInt(4, endereco.getNumero());
            st.setInt(5, endereco.getEstado().getId());
            st.setInt(6, endereco.getCidade().getId());
            st.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(EnderecoDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }

    @Override
    public EnderecoBean buscar(int id) {
        PreparedStatement st = null;
        ResultSet rs = null;
        EnderecoBean endereco = new EnderecoBean();
        try {
            st = con.prepareStatement("SELECT * FROM tb_endereco WHERE id = ?");
            st.setInt(1, id);
            rs = st.executeQuery();
            while (rs.next()) {
                endereco.setId(rs.getInt("id"));
                endereco.setBairro(rs.getString("bairro"));
                endereco.setCep(rs.getString("cep"));
                endereco.setLogradouro(rs.getString("logradouro"));
                endereco.setNumero(rs.getInt("numero"));
                EstadoBean estado = new EstadoBean();
                estado.setId(rs.getInt("id_estado"));
                endereco.setEstado(estado);
                CidadeBean cidade = new CidadeBean();
                cidade.setId(rs.getInt("id_cidade"));
                endereco.setCidade(cidade);
                break;
            }
            return endereco;
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
    public void alterar(EnderecoBean endereco) {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = con.prepareStatement("UPDATE tb_endereco SET cep = ?, logradouro = ?, bairro = ?, numero = ?, id_estado = ?, id_cidade = ?  WHERE id = ?");
            st.setString(1, endereco.getCep());
            st.setString(2, endereco.getLogradouro());
            st.setString(3, endereco.getBairro());
            st.setInt(4, endereco.getNumero());
            st.setInt(5, endereco.getEstado().getId());
            st.setInt(6, endereco.getCidade().getId());
            st.setInt(7, endereco.getId());
            st.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(EnderecoDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public EnderecoBean buscarUltimoInserido() {
        
        PreparedStatement st = null;
        ResultSet rs = null;
        EnderecoBean endereco = new EnderecoBean();
        try {
            st = con.prepareStatement("SELECT * FROM tb_endereco WHERE id = (SELECT MAX(id) FROM tb_endereco)");
            rs = st.executeQuery();
            while (rs.next()) {
                endereco.setId(rs.getInt("id"));
                endereco.setBairro(rs.getString("bairro"));
                endereco.setCep(rs.getString("cep"));
                endereco.setLogradouro(rs.getString("logradouro"));
                endereco.setNumero(rs.getInt("numero"));
                EstadoBean estado = new EstadoBean();
                estado.setId(rs.getInt("id_estado"));
                endereco.setEstado(estado);
                CidadeBean cidade = new CidadeBean();
                cidade.setId(rs.getInt("id_cidade"));
            }
            return endereco;
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
