/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufpr.tads.dac.facade;

import br.ufpr.tads.dac.beans.AdministradorBean;
import br.ufpr.tads.dac.beans.MedicoBean;
import java.util.List;

/**
 *
 * @author luis_
 */
public interface MedicoFacade {
    
    public List<MedicoBean> listar ();
    public void remover (int id);
    public void inserir (MedicoBean medico);
    public MedicoBean buscar (int id);
    public void alterar (MedicoBean medico);
    public List<MedicoBean> buscarPorClinicaTipo (int idClinica, int idTipo);
    
}
