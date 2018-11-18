/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufpr.tads.dac.dao.impl;

import br.ufpr.tads.dac.beans.ClienteBean;
import br.ufpr.tads.dac.beans.EnderecoBean;
import br.ufpr.tads.dac.beans.PlanoBean;
import br.ufpr.tads.dac.dao.ClienteDao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author luis_
 */
public class ClienteDaoImpl extends GenericDAOImpl implements ClienteDao{

    public ClienteDaoImpl(Connection con) {
        super(con);
    }

    @Override
    public ClienteBean buscar(int id) {
        PreparedStatement st = null;
        ResultSet rs = null;
        ClienteBean user = new ClienteBean();
        try {
            st = con.prepareStatement("SELECT * FROM tb_cliente WHERE id = ?");
            st.setInt(1, id);
            rs = st.executeQuery();
            while (rs.next()) {
                user.setId(rs.getInt("id"));
                user.setNome(rs.getString("nome"));
                user.setEmail(rs.getString("email"));
                user.setIdade(rs.getInt("idade"));
                user.setTelefone(rs.getString("telefone"));
                user.setSenha(rs.getString("senha"));
                PlanoBean plano = new PlanoBean();
                plano.setId(rs.getInt("id_plano"));
                user.setPlano(plano);
                EnderecoBean endereco = new EnderecoBean();
                endereco.setId(rs.getInt("id_endereco"));
                user.setEndereco(endereco);
                System.out.println("Email: " + user.getEmail());
                break;
            }
            return user;
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
            st = con.prepareStatement("DELETE from tb_cliente WHERE id = ?");
            st.setInt(1, id);
            st.executeUpdate();
            
            st = con.prepareStatement("DELETE from tb_clinica_plano where id_plano = ?");
            st.setInt(1, id);
            st.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void inserir(ClienteBean cliente) {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = con.prepareStatement("INSERT INTO tb_cliente (nome, telefone, email, senha, idade, distanciamaxima, id_plano, id_endereco) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
            st.setString(1, cliente.getNome());
            st.setString(2, cliente.getTelefone());
            st.setString(3, cliente.getEmail());
            st.setString(4, cliente.getSenha());
            st.setInt(5, cliente.getIdade());
            st.setDouble(6, cliente.getDistanciaMaxima());
            st.setInt(7, cliente.getPlano().getId());
            st.setInt(8, cliente.getEndereco().getId());
            st.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }

    @Override
    public void alterar(ClienteBean cliente) {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = con.prepareStatement("UPDATE tb_cliente SET nome = ?, telefone = ?, email = ?, senha = ?, idade = ?, distanciamaxima = ?, id_plano = ?, id_endereco = ? WHERE id = ?");
            st.setString(1, cliente.getNome());
            st.setString(2, cliente.getTelefone());
            st.setString(3, cliente.getEmail());
            st.setString(4, cliente.getSenha());
            st.setInt(5, cliente.getIdade());
            st.setDouble(6, cliente.getDistanciaMaxima());
            st.setInt(7, cliente.getPlano().getId());
            st.setInt(8, cliente.getEndereco().getId());
            st.setInt(9, cliente.getId());
            st.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }

    
    
}
