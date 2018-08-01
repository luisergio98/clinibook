/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufpr.tads.dac.dao.impl;

import java.sql.Connection;

/**
 *
 * @author luis_
 */
public class GenericDAOImpl {
    
    protected Connection con;
    
    public GenericDAOImpl(Connection con) {
        this.con = con;
    }
    
}
