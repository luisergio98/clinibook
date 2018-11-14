/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufpr.tads.dac.dao;

import br.ufpr.tads.dac.beans.CidadeBean;
import br.ufpr.tads.dac.beans.EstadoBean;
import java.util.List;

/**
 *
 * @author luis_
 */
public interface CidadeEstadoDao {
    
    public List<CidadeBean> listarCidades ();
    public List<CidadeBean> listarCidadesPorEstado(int id);
    public List<EstadoBean> listarEstados ();
    public CidadeBean buscarCidade (int id);
    public EstadoBean buscarEstado (int id);
    
    
}
