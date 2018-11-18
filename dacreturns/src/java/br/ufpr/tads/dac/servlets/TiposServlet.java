/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufpr.tads.dac.servlets;

import br.ufpr.tads.dac.beans.AdministradorBean;
import br.ufpr.tads.dac.beans.TipoBean;
import br.ufpr.tads.dac.facade.TipoFacade;
import br.ufpr.tads.dac.facade.impl.TipoFacadeImpl;
import java.io.IOException;
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
@WebServlet(name = "TiposServlet", urlPatterns = {"/tipos"})
public class TiposServlet extends HttpServlet {
    
    private TipoFacade tipoFacade;
    
    public TiposServlet(){
            this.tipoFacade = new TipoFacadeImpl();
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
           String url = "/tiposListar.jsp";
           
           int formType = 0;
                                   
            if (action == null || action.isEmpty() || action.equals("list")){    
                request.setAttribute("tipos", this.tipoFacade.listar());
                url = "/tiposListar.jsp";  
                RequestDispatcher rd = request.getRequestDispatcher(url);
                rd.forward(request, response);
            }
            else if (action.equals("remove")){
                final int id = Integer.parseInt(request.getParameter("id"));
                this.tipoFacade.remover(id);
                response.sendRedirect("tipos"); 
            }
            else if (action.equals("formNew")){
                url = "/formTipo.jsp";
                formType = 1;
                RequestDispatcher rd = request.getRequestDispatcher(url);
                request.setAttribute("formType", formType);
                rd.forward(request, response);
            }
            else if (action.equals("new")){
                TipoBean tipo = new TipoBean();
                    
                tipo.setNome(request.getParameter("nome"));
                
                this.tipoFacade.inserir(tipo);
                
                response.sendRedirect("tipos");                
            }
            else if (action.equals("formUpdate")){
                final int id = Integer.parseInt(request.getParameter("id"));
                TipoBean t = this.tipoFacade.buscar(id);
                
                request.setAttribute("tipo", t);
                
                url = "/formTipo.jsp";
                formType = 2;
                RequestDispatcher rd = request.getRequestDispatcher(url);
                request.setAttribute("formType", formType);
                rd.forward(request, response);
            }
            else if (action.equals("update")){
                
                TipoBean tipo = new TipoBean();
                
                tipo.setId(Integer.parseInt(request.getParameter("id")));
                tipo.setNome(request.getParameter("nome"));
                
                this.tipoFacade.alterar(tipo);
                
                response.sendRedirect("tipos");                
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
