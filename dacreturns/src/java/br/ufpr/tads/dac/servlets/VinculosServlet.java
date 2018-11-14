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
import br.ufpr.tads.dac.beans.PlanoBean;
import br.ufpr.tads.dac.beans.VinculoBean;
import br.ufpr.tads.dac.facade.CidadeEstadoFacade;
import br.ufpr.tads.dac.facade.ClinicaFacade;
import br.ufpr.tads.dac.facade.EnderecoFacade;
import br.ufpr.tads.dac.facade.PlanoFacade;
import br.ufpr.tads.dac.facade.VinculoFacade;
import br.ufpr.tads.dac.facade.impl.CidadeEstadoFacadeImpl;
import br.ufpr.tads.dac.facade.impl.ClinicaFacadeImpl;
import br.ufpr.tads.dac.facade.impl.EnderecoFacadeImpl;
import br.ufpr.tads.dac.facade.impl.PlanoFacadeImpl;
import br.ufpr.tads.dac.facade.impl.VinculoFacadeImpl;
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
@WebServlet(name = "VinculosServlet", urlPatterns = {"/vinculos"})
public class VinculosServlet extends HttpServlet {
    
     private VinculoFacade vinculoFacade;
     private EnderecoFacade enderecoFacade;
     private CidadeEstadoFacade cidadeEstadoFacade;
     private ClinicaFacade clinicaFacade;
     private PlanoFacade planoFacade;
    
    public VinculosServlet(){
            this.vinculoFacade = new VinculoFacadeImpl();
            this.enderecoFacade = new EnderecoFacadeImpl();
            this.cidadeEstadoFacade = new CidadeEstadoFacadeImpl();
            this.clinicaFacade = new ClinicaFacadeImpl();
            this.planoFacade = new PlanoFacadeImpl();
    }  
    
    
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
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
           String tipoLista = "";
           if (request.getParameterMap().containsKey("tipolista")){
               tipoLista = request.getParameter("tipolista");
           }
           else
           {
               tipoLista = "";
           }
           String url = "/vinculosListar.jsp";
           
            int formType = 0;
           
            if (action == null || action.isEmpty() || action.equals("list")){
                
                List<VinculoBean> vinculos = new ArrayList<VinculoBean>();
                
                if (tipoLista.equals("clinica") ){
                    vinculos = this.vinculoFacade.listarPorClinica(Integer.parseInt(request.getParameter("clinica")));
                }
                else if (tipoLista.equals("plano")){
                    vinculos = this.vinculoFacade.listarPorPlano(Integer.parseInt(request.getParameter("plano")));
                }
                else {
                    vinculos = this.vinculoFacade.listar();
                }
                
                for (VinculoBean v : vinculos){
                    
                    ClinicaBean c = clinicaFacade.buscar(v.getClinica().getId());
                    
                    EnderecoBean endereco = new EnderecoBean();
                    CidadeBean cidade = new CidadeBean();
                    EstadoBean estado =  new EstadoBean();
                    endereco = enderecoFacade.buscar(c.getEndereco().getId());
                    cidade = cidadeEstadoFacade.buscarCidade(endereco.getCidade().getId());
                    estado = cidadeEstadoFacade.buscarEstado(endereco.getEstado().getId());
                    endereco.setCidade(cidade);
                    endereco.setEstado(estado);
                    c.setEndereco(endereco);
                    
                    PlanoBean p = planoFacade.buscar(v.getPlano().getId());
                    
                    v.setClinica(c);
                    v.setPlano(p);
                    
                }
                
                List<ClinicaBean> clinicas = clinicaFacade.listar();
                List<PlanoBean> planos = planoFacade.listar();
                request.setAttribute("clinicas", clinicas);
                request.setAttribute("planos", planos);

                request.setAttribute("vinculos", vinculos);
                url = "/vinculosListar.jsp";  
                RequestDispatcher rd = request.getRequestDispatcher(url);
                rd.forward(request, response);
            }
            else if (action.equals("remove")){
                final int id = Integer.parseInt(request.getParameter("id"));
                this.vinculoFacade.remover(id);
                response.sendRedirect("vinculos"); 
            }
            else if (action.equals("formNew")){
                url = "/formVinculo.jsp";
                formType = 1;
                List<ClinicaBean> clinicas = clinicaFacade.listar();
                List<PlanoBean> planos = planoFacade.listar();
                RequestDispatcher rd = request.getRequestDispatcher(url);
                request.setAttribute("formType", formType);
                request.setAttribute("clinicas", clinicas);
                request.setAttribute("planos", planos);
                rd.forward(request, response);
            }
            else if (action.equals("new")){
                VinculoBean vinculo = new VinculoBean();
                
                ClinicaBean clinica = clinicaFacade.buscar(Integer.parseInt(request.getParameter("clinica")));
                PlanoBean plano = planoFacade.buscar(Integer.parseInt(request.getParameter("plano")));
                
                vinculo.setClinica(clinica);
                vinculo.setPlano(plano);
                
                this.vinculoFacade.inserir(vinculo);
                
                response.sendRedirect("vinculos");                
            }
            else if (action.equals("formUpdate")){
                final int id = Integer.parseInt(request.getParameter("id"));
                url = "/formVinculo.jsp";
                formType = 2;
                VinculoBean vinculo = vinculoFacade.buscar(id);
                
                ClinicaBean clinica = clinicaFacade.buscar(vinculo.getClinica().getId());
                PlanoBean plano = planoFacade.buscar(vinculo.getPlano().getId());
                
                vinculo.setClinica(clinica);
                vinculo.setPlano(plano);
                
                List<ClinicaBean> clinicas = clinicaFacade.listar();
                List<PlanoBean> planos = planoFacade.listar();
                
                RequestDispatcher rd = request.getRequestDispatcher(url);
                request.setAttribute("vinculo", vinculo);
                request.setAttribute("formType", formType);
                request.setAttribute("clinicas", clinicas);
                request.setAttribute("planos", planos);
                rd.forward(request, response);
            }
            else if (action.equals("update")){
                
                VinculoBean vinculo = new VinculoBean();
                
                vinculo.setId(Integer.parseInt(request.getParameter("id")));
                
                ClinicaBean clinica = clinicaFacade.buscar(Integer.parseInt(request.getParameter("clinica")));
                PlanoBean plano = planoFacade.buscar(Integer.parseInt(request.getParameter("plano")));
                
                vinculo.setClinica(clinica);
                vinculo.setPlano(plano);
                
                this.vinculoFacade.alterar(vinculo);
                
                response.sendRedirect("vinculos");                
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
