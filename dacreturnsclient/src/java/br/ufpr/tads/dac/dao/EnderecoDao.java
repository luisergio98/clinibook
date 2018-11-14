/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufpr.tads.dac.dao;

import br.ufpr.tads.dac.beans.EnderecoBean;
import java.util.List;

/**
 *
 * @author luis_
 */
public interface EnderecoDao {
    
    public List<EnderecoBean> listar ();
    public void remover (int id);
    public void inserir (EnderecoBean endereco);
    public EnderecoBean buscar (int id);
    public EnderecoBean buscarUltimoInserido();
    public void alterar (EnderecoBean endereco);
    
}
