/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufpr.tads.dac.dao.impl;

import br.ufpr.tads.dac.beans.ClienteBean;
import br.ufpr.tads.dac.beans.EnderecoBean;
import br.ufpr.tads.dac.beans.PlanoBean;
import br.ufpr.tads.dac.dao.UserDao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author luis_
 */
public class UserDaoImpl extends GenericDAOImpl implements UserDao{

    public UserDaoImpl(Connection con) {
        super(con);
    }

    @Override
    public ClienteBean login(ClienteBean cliente) {
        PreparedStatement st = null;
        ResultSet rs = null;
        ClienteBean user = new ClienteBean();
        try {
            st = con.prepareStatement("SELECT * FROM tb_cliente WHERE email = ? AND senha = ?");
            st.setString(1, cliente.getEmail());
            st.setString(2, cliente.getSenha());
            rs = st.executeQuery();
            while (rs.next()) {
                user.setId(rs.getInt("id"));
                user.setNome(rs.getString("nome"));
                user.setEmail(rs.getString("email"));
                user.setIdade(rs.getInt("idade"));
                user.setTelefone(rs.getString("telefone"));
                user.setSenha(rs.getString("senha"));
                user.setDistanciaMaxima(rs.getDouble("distanciamaxima"));
                PlanoBean plano = new PlanoBean();
                plano.setId(rs.getInt("id_plano"));
                user.setPlano(plano);
                EnderecoBean endereco = new EnderecoBean();
                endereco.setId(rs.getInt("id_endereco"));
                user.setEndereco(endereco);
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
    
}
