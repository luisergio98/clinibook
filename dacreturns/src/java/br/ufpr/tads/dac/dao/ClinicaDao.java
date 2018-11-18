/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufpr.tads.dac.dao;

import br.ufpr.tads.dac.beans.AdministradorBean;
import br.ufpr.tads.dac.beans.ClinicaBean;
import java.util.List;

/**
 *
 * @author luis_
 */
public interface ClinicaDao {
    
    public List<ClinicaBean> listar ();
    public void remover (int id);
    public void inserir (ClinicaBean clinica);
    public ClinicaBean buscar (int id);
    public void alterar (ClinicaBean clinica);
    
}
