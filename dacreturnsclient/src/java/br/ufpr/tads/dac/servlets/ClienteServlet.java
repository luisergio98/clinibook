/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufpr.tads.dac.servlets;

import br.ufpr.tads.dac.beans.CidadeBean;
import br.ufpr.tads.dac.beans.ClienteBean;
import br.ufpr.tads.dac.beans.ClinicaBean;
import br.ufpr.tads.dac.beans.EnderecoBean;
import br.ufpr.tads.dac.beans.EstadoBean;
import br.ufpr.tads.dac.beans.PlanoBean;
import br.ufpr.tads.dac.facade.CidadeEstadoFacade;
import br.ufpr.tads.dac.facade.ClienteFacade;
import br.ufpr.tads.dac.facade.EnderecoFacade;
import br.ufpr.tads.dac.facade.impl.CidadeEstadoFacadeImpl;
import br.ufpr.tads.dac.facade.impl.ClienteFacadeImpl;
import br.ufpr.tads.dac.facade.impl.EnderecoFacadeImpl;
import java.io.IOException;
import java.util.List;
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
@WebServlet(name = "ClienteServlet", urlPatterns = {"/cliente"})
public class ClienteServlet extends HttpServlet {
    
    private ClienteFacade clienteFacade;
    private EnderecoFacade enderecoFacade;
    private CidadeEstadoFacade cidadeEstadoFacade;
    
    public ClienteServlet(){
            this.clienteFacade = new ClienteFacadeImpl();
            this.enderecoFacade = new EnderecoFacadeImpl();
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
        
        HttpSession session = request.getSession(false);
        request.setCharacterEncoding("UTF8");
        
        String action = request.getParameter("action");
        String url = "";
           
        int formType = 0;
           
        if (action.equals("formNew") || action.isEmpty()){
            
            Client client = ClientBuilder.newClient();
            Response resp = client
                    .target("http://localhost:8080/dacreturns/webresources/service/planos/")
                    .request(MediaType.APPLICATION_JSON)
                    .get();
            List<PlanoBean> planos
                    = resp.readEntity(
                            new GenericType<List<PlanoBean>>() {
                    }
                    );
                
                request.setAttribute("planos", planos);    
            
                List<EstadoBean> estados = cidadeEstadoFacade.listarEstados();
                request.setAttribute("estados", estados);
                url = "/atualizadados_medinder.jsp";
                formType = 1;
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
                
                ClienteBean cliente = new ClienteBean();
                
                cliente.setEndereco(endereco);
                    
                cliente.setNome(request.getParameter("nome"));
                cliente.setEmail(request.getParameter("email"));
                cliente.setIdade(Integer.parseInt(request.getParameter("idade")));
                cliente.setSenha(request.getParameter("senha"));
                cliente.setTelefone(request.getParameter("telefone"));
                cliente.setDistanciaMaxima(Double.parseDouble(request.getParameter("distancia")));
                
                PlanoBean plano = new PlanoBean();
                plano.setId(Integer.parseInt(request.getParameter("plano")));
                
                cliente.setPlano(plano );
                
                this.clienteFacade.inserir(cliente);
                
                request.setAttribute("msg", "Usuário cadastrado com sucesso");
                response.sendRedirect("index.jsp");                
            }
            else if (session == null || ((ClienteBean) session.getAttribute("login") == null)) {
                RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");
                request.setAttribute("msg", "Usuário deve se autenticar para acessar o sistema");
                rd.forward(request, response);
            }
            else if (action.equals("formUpdate")){
                
                List<CidadeBean> cidades = this.cidadeEstadoFacade.listarCidades();
                List<EstadoBean> estados = this.cidadeEstadoFacade.listarEstados();

                request.setAttribute("cidades", cidades);
                request.setAttribute("estados", estados);
                
                Client client = ClientBuilder.newClient();
            Response resp = client
                    .target("http://localhost:8080/dacreturns/webresources/service/planos/")
                    .request(MediaType.APPLICATION_JSON)
                    .get();
            List<PlanoBean> planos
                    = resp.readEntity(
                            new GenericType<List<PlanoBean>>() {
                    }
                    );
                
                request.setAttribute("planos", planos);    
                
                url = "/atualizadados_medinder.jsp";
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
                
                
                ClienteBean cliente = new ClienteBean();
                
                cliente.setEndereco(endereco);
                    
                cliente.setId(Integer.parseInt(request.getParameter("id")));
                cliente.setNome(request.getParameter("nome"));
                cliente.setEmail(request.getParameter("email"));
                cliente.setIdade(Integer.parseInt(request.getParameter("idade")));
                cliente.setSenha(request.getParameter("senha"));
                cliente.setTelefone(request.getParameter("telefone"));
                cliente.setDistanciaMaxima(Double.parseDouble(request.getParameter("distancia")));
                
                Client client = ClientBuilder.newClient();
                Response resp = client.
                    target("http://localhost:8080/dacreturns/webresources/service/plano/").
                    path("/" + Integer.parseInt(request.getParameter("plano"))).
                    request(MediaType.APPLICATION_JSON).get();

                PlanoBean plano = resp.readEntity(new GenericType<PlanoBean>() {
                });
                
                
                cliente.setPlano(plano);
                
                this.clienteFacade.alterar(cliente);

                session.setAttribute("login", cliente);
            
                
                response.sendRedirect("filtrar");                
            } 
            else if (action.equals("remove")){
                final int id = Integer.parseInt(request.getParameter("id"));
                ClienteBean cliente = clienteFacade.buscar(id);
                this.clienteFacade.remover(id);
                this.enderecoFacade.remover(cliente.getEndereco().getId());
                
                response.sendRedirect("index.jsp"); 
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
