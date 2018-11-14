/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufpr.tads.dac.servlets;


import br.ufpr.tads.dac.beans.CidadeBean;
import br.ufpr.tads.dac.beans.ClienteBean;
import br.ufpr.tads.dac.beans.EnderecoBean;
import br.ufpr.tads.dac.beans.EstadoBean;
import br.ufpr.tads.dac.beans.PlanoBean;
import br.ufpr.tads.dac.facade.CidadeEstadoFacade;
import br.ufpr.tads.dac.facade.EnderecoFacade;
import br.ufpr.tads.dac.facade.UserFacade;
import br.ufpr.tads.dac.facade.impl.CidadeEstadoFacadeImpl;
import br.ufpr.tads.dac.facade.impl.EnderecoFacadeImpl;
import br.ufpr.tads.dac.facade.impl.UserFacadeImpl;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author luis_
 */
@WebServlet(name = "LoginServlet", urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private UserFacade userFacade;
    private CidadeEstadoFacade cidadeEstadoFacade;
    private EnderecoFacade enderecoFacade;
    
    public LoginServlet(){
        this.userFacade = new UserFacadeImpl();
        this.cidadeEstadoFacade = new CidadeEstadoFacadeImpl();
        this.enderecoFacade = new EnderecoFacadeImpl();
    }  

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       

            ClienteBean userForm = new ClienteBean();
            
            userForm.setEmail(request.getParameter("email"));
            userForm.setSenha(request.getParameter("password"));

            ClienteBean user = userFacade.login(userForm);
            
            

            if (user.getEmail()== null) 
            {                
                RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");
                request.setAttribute("msg", "Usuário/Senha inválidos.");
                rd.forward(request, response);    
            }
            else {
            
            Client client = ClientBuilder.newClient();
            Response resp = client.
                    target("http://localhost:8080/dacreturns/webresources/service/plano/").
                    path("/" + user.getPlano().getId()).
                    request(MediaType.APPLICATION_JSON).get();

            PlanoBean plano = resp.readEntity(new GenericType<PlanoBean>() {
            });
            
            user.setPlano(plano);
            
            EnderecoBean endereco = enderecoFacade.buscar(user.getEndereco().getId());  
            CidadeBean cidade = cidadeEstadoFacade.buscarCidade(endereco.getCidade().getId());
            EstadoBean estado = cidadeEstadoFacade.buscarEstado(endereco.getEstado().getId());
            
            endereco.setCidade(cidade);
            endereco.setEstado(estado);
            
            user.setEndereco(endereco);
                        
            HttpSession session = request.getSession();
            session.setAttribute("login", user);
            
            RequestDispatcher rd = request.
                            getRequestDispatcher("/filtrar");
                    request.setAttribute("user", user);
                    rd.forward(request, response);
                    
                    
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


