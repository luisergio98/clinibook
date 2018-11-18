/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufpr.tads.dac.servlets;

import br.ufpr.tads.dac.beans.AdministradorBean;
import br.ufpr.tads.dac.beans.PlanoBean;
import br.ufpr.tads.dac.facade.PlanoFacade;
import br.ufpr.tads.dac.facade.impl.PlanoFacadeImpl;
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
@WebServlet(name = "PlanosServlet", urlPatterns = {"/planos"})
public class PlanosServlet extends HttpServlet {
    
    private PlanoFacade planoFacade;
    
    public PlanosServlet(){
            this.planoFacade = new PlanoFacadeImpl();
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
           String url = "/planosListar.jsp";
           
           int formType = 0;
                                   
            if (action == null || action.isEmpty() || action.equals("list")){    
                request.setAttribute("planos", this.planoFacade.listar());
                url = "/planosListar.jsp";  
                RequestDispatcher rd = request.getRequestDispatcher(url);
                rd.forward(request, response);
            }
            else if (action.equals("remove")){
                final int id = Integer.parseInt(request.getParameter("id"));
                this.planoFacade.remover(id);
                response.sendRedirect("planos"); 
            }
            else if (action.equals("formNew")){
                url = "/formPlano.jsp";
                formType = 1;
                RequestDispatcher rd = request.getRequestDispatcher(url);
                request.setAttribute("formType", formType);
                rd.forward(request, response);
            }
            else if (action.equals("new")){
                PlanoBean plano = new PlanoBean();
                    
                plano.setNome(request.getParameter("nome"));
                
                this.planoFacade.inserir(plano);
                
                response.sendRedirect("planos");                
            }
            else if (action.equals("formUpdate")){
                final int id = Integer.parseInt(request.getParameter("id"));
                PlanoBean p = this.planoFacade.buscar(id);
                
                request.setAttribute("plano", p);
                
                url = "/formPlano.jsp";
                formType = 2;
                RequestDispatcher rd = request.getRequestDispatcher(url);
                request.setAttribute("formType", formType);
                rd.forward(request, response);
            }
            else if (action.equals("update")){
                
                PlanoBean plano = new PlanoBean();
                
                plano.setId(Integer.parseInt(request.getParameter("id")));
                plano.setNome(request.getParameter("nome"));
                
                this.planoFacade.alterar(plano);
                
                response.sendRedirect("planos");                
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
