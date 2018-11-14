/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufpr.tads.dac.facade;

import br.ufpr.tads.dac.beans.VinculoBean;
import java.util.List;

/**
 *
 * @author luis_
 */
public interface VinculoFacade {
    
    public List<VinculoBean> listar ();
    public void remover (int id);
    public void inserir (VinculoBean vinculo);
    public VinculoBean buscar (int id);
    public void alterar (VinculoBean vinculo);
    public List<VinculoBean> listarPorClinica (int id);
    public List<VinculoBean> listarPorPlano (int id);
    
}
