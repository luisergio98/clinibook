/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufpr.tads.dac.dao;

import br.ufpr.tads.dac.beans.ClienteBean;


/**
 *
 * @author luis_
 */
public interface UserDao {
    
    public ClienteBean login(ClienteBean cliente);
    
}
