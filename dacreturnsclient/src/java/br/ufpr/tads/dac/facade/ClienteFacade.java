/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufpr.tads.dac.facade;

import br.ufpr.tads.dac.beans.ClienteBean;


/**
 *
 * @author luis_
 */
public interface ClienteFacade {
    
    public ClienteBean buscar(int id);
    public void remover (int id);
    public void inserir (ClienteBean cliente);
    public void alterar (ClienteBean cliente);
    
}
