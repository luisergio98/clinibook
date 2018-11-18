/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufpr.tads.dac.dao.impl;

import br.ufpr.tads.dac.beans.AdministradorBean;
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
    public AdministradorBean loginAdmin(AdministradorBean admin) {
        PreparedStatement st = null;
        ResultSet rs = null;
        AdministradorBean user = new AdministradorBean();
        try {
            st = con.prepareStatement("select login from tb_admin where login = ? AND senha = ?");
            st.setString(1, admin.getLogin());
            st.setString(2, admin.getSenha());
            rs = st.executeQuery();
            while (rs.next()) {
                user.setLogin(rs.getString("login"));
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
