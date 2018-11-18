/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufpr.tads.dac.servlets;

import br.ufpr.tads.dac.beans.CidadeBean;
import br.ufpr.tads.dac.facade.CidadeEstadoFacade;
import br.ufpr.tads.dac.facade.impl.CidadeEstadoFacadeImpl;
import com.google.gson.Gson;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author luis_
 */
@WebServlet(name = "AJAXServlet", urlPatterns = {"/AJAXServlet"})
public class AJAXServlet extends HttpServlet {
    
    private CidadeEstadoFacade cidadeEstadoFacade;
    
    public AJAXServlet(){
            this.cidadeEstadoFacade = new CidadeEstadoFacadeImpl();
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
        int estado = Integer.parseInt(request.getParameter("estadoId"));
        
        // Vai no BD buscar todas as cidades deste estado, em uma lista        
        List<CidadeBean> lista = cidadeEstadoFacade.listarCidadesPorEstado(estado);
              
        // transforma o MAP em JSON
        String json = new Gson().toJson(lista);   

        // retorna o JSON
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);       
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

}
