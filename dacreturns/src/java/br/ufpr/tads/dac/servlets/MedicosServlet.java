/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufpr.tads.dac.servlets;

import br.ufpr.tads.dac.beans.AdministradorBean;
import br.ufpr.tads.dac.beans.ClinicaBean;
import br.ufpr.tads.dac.beans.MedicoBean;
import br.ufpr.tads.dac.beans.TipoBean;
import br.ufpr.tads.dac.facade.ClinicaFacade;
import br.ufpr.tads.dac.facade.MedicoFacade;
import br.ufpr.tads.dac.facade.TipoFacade;
import br.ufpr.tads.dac.facade.impl.ClinicaFacadeImpl;
import br.ufpr.tads.dac.facade.impl.MedicoFacadeImpl;
import br.ufpr.tads.dac.facade.impl.TipoFacadeImpl;
import java.io.IOException;
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
@WebServlet(name = "MedicosServlet", urlPatterns = {"/medicos"})
public class MedicosServlet extends HttpServlet {

    private MedicoFacade medicoFacade;
    private ClinicaFacade clinicaFacade;
    private TipoFacade tipoFacade;
    
    public MedicosServlet(){
            this.medicoFacade = new MedicoFacadeImpl();
            this.clinicaFacade = new ClinicaFacadeImpl();
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
           String url = "/medicosListar.jsp";
           
           int formType = 0;
                               
           if (action == null || action.isEmpty() || action.equals("list")){    
                
               List<MedicoBean> medicos = this.medicoFacade.listar();
               
               for (MedicoBean m : medicos){
                   TipoBean tipo = tipoFacade.buscar(m.getTipo().getId());
                   ClinicaBean clinica = clinicaFacade.buscar(m.getClinica().getId());
                   
                   m.setClinica(clinica);
                   m.setTipo(tipo);
               }
               
                request.setAttribute("medicos", medicos );
                url = "/medicosListar.jsp";  
                RequestDispatcher rd = request.getRequestDispatcher(url);
                rd.forward(request, response);

            }
           else if (action.equals("remove")){
                final int id = Integer.parseInt(request.getParameter("id"));
                this.medicoFacade.remover(id);
                response.sendRedirect("medicos"); 
            }
           else if (action.equals("formNew")){
                url = "/formMedico.jsp";
                List<TipoBean> tipos = tipoFacade.listar();
                List<ClinicaBean> clinicas = clinicaFacade.listar();
                formType = 1;
                RequestDispatcher rd = request.getRequestDispatcher(url);
                request.setAttribute("formType", formType);
                request.setAttribute("clinicas", clinicas );
                request.setAttribute("tipos", tipos );
                rd.forward(request, response);
            }
           else if (action.equals("new")){
               
               MedicoBean medico = new MedicoBean();
               
               medico.setNome(request.getParameter("nome"));
               medico.setCrm(request.getParameter("crm"));
               
               TipoBean tipo = new TipoBean();
               
               tipo.setId(Integer.parseInt(request.getParameter("tipo")));
               
               ClinicaBean clinica = new ClinicaBean();
               
               clinica.setId(Integer.parseInt(request.getParameter("clinica")));
               
               medico.setTipo(tipo);
               medico.setClinica(clinica);
               
               medicoFacade.inserir(medico);
                
               response.sendRedirect("medicos");                
            }
           else if (action.equals("formUpdate")){
                final int id = Integer.parseInt(request.getParameter("id"));
                MedicoBean m = this.medicoFacade.buscar(id);
                
                request.setAttribute("medico", m);
                
                List<TipoBean> tipos = tipoFacade.listar();
                List<ClinicaBean> clinicas = clinicaFacade.listar();
                
                request.setAttribute("tipos", tipos);
                request.setAttribute("clinicas", clinicas);
                
                url = "/formMedico.jsp";
                formType = 2;
                RequestDispatcher rd = request.getRequestDispatcher(url);
                request.setAttribute("formType", formType);
                rd.forward(request, response);
            }
            else if (action.equals("update")){
                
                MedicoBean medico = new MedicoBean();
                
                medico.setId(Integer.parseInt(request.getParameter("id")));
                medico.setNome(request.getParameter("nome"));
                medico.setCrm(request.getParameter("crm"));
               
                TipoBean tipo = new TipoBean();
               
                tipo.setId(Integer.parseInt(request.getParameter("tipo")));
               
                ClinicaBean clinica = new ClinicaBean();
               
                clinica.setId(Integer.parseInt(request.getParameter("clinica")));
               
                medico.setTipo(tipo);
                medico.setClinica(clinica);
                
                this.medicoFacade.alterar(medico);
                
                response.sendRedirect("medicos");                
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
