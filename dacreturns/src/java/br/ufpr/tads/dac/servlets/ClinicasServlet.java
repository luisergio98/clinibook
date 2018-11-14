/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufpr.tads.dac.servlets;

import br.ufpr.tads.dac.beans.AdministradorBean;
import br.ufpr.tads.dac.beans.CidadeBean;
import br.ufpr.tads.dac.beans.ClinicaBean;
import br.ufpr.tads.dac.beans.EnderecoBean;
import br.ufpr.tads.dac.beans.EstadoBean;
import br.ufpr.tads.dac.facade.ClinicaFacade;
import br.ufpr.tads.dac.facade.EnderecoFacade;
import br.ufpr.tads.dac.facade.CidadeEstadoFacade;
import br.ufpr.tads.dac.facade.impl.CidadeEstadoFacadeImpl;
import br.ufpr.tads.dac.facade.impl.ClinicaFacadeImpl;
import br.ufpr.tads.dac.facade.impl.EnderecoFacadeImpl;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author luis_
 */
@WebServlet(name = "ClinicasServlet", urlPatterns = {"/clinicas"})
public class ClinicasServlet extends HttpServlet {
    
    private ClinicaFacade clinicaFacade;
    private EnderecoFacade enderecoFacade;
    private CidadeEstadoFacade cidadeEstadoFacade;
    
    public ClinicasServlet(){
            this.clinicaFacade = new ClinicaFacadeImpl();
            this.enderecoFacade = new EnderecoFacadeImpl();
            this.cidadeEstadoFacade = new CidadeEstadoFacadeImpl();
    }  
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession(false);
        
           if (session == null || ((AdministradorBean) session.getAttribute("login") == null)) {
                RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");
                request.setAttribute("msg", "Usuário deve se autenticar para acessar o sistema");
                rd.forward(request, response);
            }
           
           String action = request.getParameter("action");
           String url = "/clinicasListar.jsp";
           
           int formType = 0;
                                   
            if (action == null || action.isEmpty() || action.equals("list")){    

                List<ClinicaBean> clinicas = new ArrayList<ClinicaBean>();
                clinicas = this.clinicaFacade.listar();
                
                for (ClinicaBean c : clinicas){
                    EnderecoBean endereco = new EnderecoBean();
                    CidadeBean cidade = new CidadeBean();
                    EstadoBean estado =  new EstadoBean();
                    endereco = enderecoFacade.buscar(c.getEndereco().getId());
                    cidade = cidadeEstadoFacade.buscarCidade(endereco.getCidade().getId());
                    estado = cidadeEstadoFacade.buscarEstado(endereco.getEstado().getId());
                    endereco.setCidade(cidade);
                    endereco.setEstado(estado);
                    c.setEndereco(endereco);
                }
                
                request.setAttribute("clinicas", clinicas);
                
                url = "/clinicasListar.jsp";  
                
                RequestDispatcher rd = request.getRequestDispatcher(url);
                rd.forward(request, response);
            }
            else if (action.equals("remove")){
                final int id = Integer.parseInt(request.getParameter("id"));
                ClinicaBean clinica = clinicaFacade.buscar(id);
                this.clinicaFacade.remover(id);
                this.enderecoFacade.remover(clinica.getEndereco().getId());
                
                response.sendRedirect("clinicas"); 
            }
            else if (action.equals("formNew")){
                url = "/formClinica.jsp";
                formType = 1;
                List<EstadoBean> estados = cidadeEstadoFacade.listarEstados();
                request.setAttribute("estados", estados);
                RequestDispatcher rd = request.getRequestDispatcher(url);
                request.setAttribute("formType", formType);
                rd.forward(request, response);
            }
            else if (action.equals("new")){
                EnderecoBean endereco = new EnderecoBean();
                CidadeBean cidade = new CidadeBean();
                EstadoBean estado = new EstadoBean();
                
                cidade = cidadeEstadoFacade.buscarCidade(Integer.parseInt(request.getParameter("cidade")));
                estado = cidadeEstadoFacade.buscarEstado(Integer.parseInt(request.getParameter("estado")));
                
                endereco.setLogradouro(request.getParameter("rua"));
                endereco.setNumero(Integer.parseInt(request.getParameter("numero")));
                endereco.setBairro(request.getParameter("bairro"));
                endereco.setCep(request.getParameter("cep"));
                endereco.setCidade(cidade);
                endereco.setEstado(estado);
                
                enderecoFacade.inserir(endereco);
                
                endereco =  enderecoFacade.buscarUltimoInserido();
                
                ClinicaBean clinica = new ClinicaBean();
                
                clinica.setEndereco(endereco);
                
                clinica.setNome(request.getParameter("nome"));
                clinica.setEmail(request.getParameter("email"));
                clinica.setTelefone(request.getParameter("telefone"));
                clinica.setMinIdade(Integer.parseInt(request.getParameter("idadede")));
                clinica.setMaxIdade(Integer.parseInt(request.getParameter("idadeate")));
                
                clinicaFacade.inserir(clinica);
                
                response.sendRedirect("clinicas");                
            }
            else if (action.equals("formUpdate")){
                final int id = Integer.parseInt(request.getParameter("id"));
                ClinicaBean c = this.clinicaFacade.buscar(id);
                
                EnderecoBean endereco = enderecoFacade.buscar(c.getEndereco().getId());
                
                c.setEndereco(endereco);                
                
                List<CidadeBean> cidades = this.cidadeEstadoFacade.listarCidades();
                List<EstadoBean> estados = this.cidadeEstadoFacade.listarEstados();
                
                
                request.setAttribute("clinica", c);
                request.setAttribute("cidades", cidades);
                request.setAttribute("estados", estados);
                
                url = "/formClinica.jsp";
                formType = 2;
                RequestDispatcher rd = request.getRequestDispatcher(url);
                request.setAttribute("formType", formType);
                rd.forward(request, response);
            }
            else if (action.equals("update")){
                
                EnderecoBean endereco = new EnderecoBean();
                CidadeBean cidade = new CidadeBean();
                EstadoBean estado = new EstadoBean();
                
                cidade = cidadeEstadoFacade.buscarCidade(Integer.parseInt(request.getParameter("cidade")));
                estado = cidadeEstadoFacade.buscarEstado(Integer.parseInt(request.getParameter("estado")));
                
                
                endereco.setId(Integer.parseInt(request.getParameter("enderecoid")));
                endereco.setLogradouro(request.getParameter("rua"));
                endereco.setNumero(Integer.parseInt(request.getParameter("numero")));
                endereco.setBairro(request.getParameter("bairro"));
                endereco.setCep(request.getParameter("cep"));
                endereco.setCidade(cidade);
                endereco.setEstado(estado);
                
                enderecoFacade.alterar(endereco);
                
                ClinicaBean clinica = new ClinicaBean();
                
                clinica.setEndereco(endereco);
                
                clinica.setId(Integer.parseInt(request.getParameter("id")));
                clinica.setNome(request.getParameter("nome"));
                clinica.setEmail(request.getParameter("email"));
                clinica.setTelefone(request.getParameter("telefone"));
                clinica.setMinIdade(Integer.parseInt(request.getParameter("idadede")));
                clinica.setMaxIdade(Integer.parseInt(request.getParameter("idadeate")));
                
                clinicaFacade.alterar(clinica);
                
                response.sendRedirect("clinicas");                
            } 
                
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
