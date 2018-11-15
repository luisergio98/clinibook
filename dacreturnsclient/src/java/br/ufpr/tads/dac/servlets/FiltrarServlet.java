/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufpr.tads.dac.servlets;

import br.ufpr.tads.dac.beans.ClienteBean;
import br.ufpr.tads.dac.beans.ClinicaBean;
import br.ufpr.tads.dac.beans.MedicoBean;
import br.ufpr.tads.dac.beans.TipoBean;
import br.ufpr.tads.dac.beans.VinculoBean;
import com.google.gson.Gson;
import com.google.gson.JsonParser;
import java.io.IOException;
import java.util.ArrayList;
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
import java.util.List;
import org.json.*;
import java.text.Normalizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import jdk.nashorn.internal.parser.JSONParser;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.DefaultBHttpClientConnection;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

/**
 *
 * @author luis_
 */
@WebServlet(name = "FiltrarServlet", urlPatterns = {"/filtrar"})
public class FiltrarServlet extends HttpServlet {

    public static String deAccent(String str) {
        String nfdNormalizedString = Normalizer.normalize(str, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        return pattern.matcher(nfdNormalizedString).replaceAll("");
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
        ClienteBean user = (ClienteBean) session.getAttribute("login");

        if (session == null || ((ClienteBean) session.getAttribute("login") == null)) {
            RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");
            request.setAttribute("msg", "Usuário deve se autenticar para acessar o sistema");
            rd.forward(request, response);
        }

        String action = request.getParameter("action");
        String url = "consultarclinicas_medinder.jsp";

        if (action == null || action.isEmpty() || action.equals("list")) {
            Client client = ClientBuilder.newClient();
            Response resp = client
                    .target("http://localhost:8080/dacreturns/webresources/service/tipos/")
                    .request(MediaType.APPLICATION_JSON)
                    .get();
            List<TipoBean> tipos
                    = resp.readEntity(
                            new GenericType<List<TipoBean>>() {
                    }
                    );

            request.setAttribute("tipos", tipos);

            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
        } else if (action.equals("buscar")) {
            int idTipo = Integer.parseInt(request.getParameter("tipo"));

            String enderecoUsuario = user.getEndereco().getLogradouro() + " " + user.getEndereco().getNumero();
            enderecoUsuario = deAccent(enderecoUsuario);
            enderecoUsuario = enderecoUsuario.replace(" ", "+");
            double distanciaUsuario = user.getDistanciaMaxima();

            if (request.getParameter("menordistancia") == null) {
                Client client = ClientBuilder.newClient();
                Response resp = client
                        .target("http://localhost:8080/dacreturns/webresources/service/vinculos/")
                        .path("/plano/" + user.getPlano().getId())
                        .request(MediaType.APPLICATION_JSON)
                        .get();
                List<VinculoBean> vinculos
                        = resp.readEntity(
                                new GenericType<List<VinculoBean>>() {
                        }
                        );

                for (VinculoBean v : vinculos) {
                    Client client1 = ClientBuilder.newClient();
                    Response resp1 = client1
                            .target("http://localhost:8080/dacreturns/webresources/service/medicos/integracao/")
                            .path("/" + v.getClinica().getId() + "/" + idTipo)
                            .request(MediaType.APPLICATION_JSON)
                            .get();
                    List<MedicoBean> medicos
                            = resp1.readEntity(
                                    new GenericType<List<MedicoBean>>() {
                            }
                            );

                    ClinicaBean clinica = v.getClinica();
                    clinica.setMedicos(medicos);
                    v.setClinica(clinica);
                }

                request.setAttribute("vinculos", vinculos);

                url = "listaclinicas_medinder.jsp";
                RequestDispatcher rd = request.getRequestDispatcher(url);
                rd.forward(request, response);
            } else {

                Client client = ClientBuilder.newClient();
                Response resp = client
                        .target("http://localhost:8080/dacreturns/webresources/service/vinculos/")
                        .path("/plano/" + user.getPlano().getId())
                        .request(MediaType.APPLICATION_JSON)
                        .get();
                List<VinculoBean> vinculos
                        = resp.readEntity(
                                new GenericType<List<VinculoBean>>() {
                        }
                        );

                for (VinculoBean v : vinculos) {
                    Client client1 = ClientBuilder.newClient();
                    Response resp1 = client1
                            .target("http://localhost:8080/dacreturns/webresources/service/medicos/integracao/")
                            .path("/" + v.getClinica().getId() + "/" + idTipo)
                            .request(MediaType.APPLICATION_JSON)
                            .get();
                    List<MedicoBean> medicos
                            = resp1.readEntity(
                                    new GenericType<List<MedicoBean>>() {
                            }
                            );

                    ClinicaBean clinica = v.getClinica();
                    clinica.setMedicos(medicos);
                    v.setClinica(clinica);
                }

                List<VinculoBean> vinculosPerto = new ArrayList<VinculoBean>();

                System.out.println(enderecoUsuario);

                for (VinculoBean v : vinculos) {
                    
                    try {
                    
                    String enderecoClinica = v.getClinica().getEndereco().getLogradouro() + " " + v.getClinica().getEndereco().getNumero();
                    enderecoClinica = deAccent(enderecoClinica);
                    enderecoClinica = enderecoClinica.replace(" ", "+");
                    System.out.println(enderecoClinica);

                    Client client1 = ClientBuilder.newClient();
                    Response resp1 = client1
                            .target("https://maps.googleapis.com/maps/api/directions/json?origin=" + enderecoUsuario + "&destination=" + enderecoClinica + "&key=AIzaSyBcOSxeEK7FVX5uUfcPpdfaUqLmAyMqR5g")
                            .request(MediaType.APPLICATION_JSON)
                            .get();
                    String json = resp1.readEntity(
                            new GenericType<String>() {
                    }
                    );
                    

                    System.out.println("json: " + json);
                    
                    Gson g = new Gson();
                    

                    
                    
                    JSONObject obj = new JSONObject(json);
                    String distanciaJson = obj.getString("value");
                    System.out.println("Distancia: " + distanciaJson);

                    double distancia = Double.parseDouble(distanciaJson);
                    distancia = distancia/1000;
                    System.out.println("Distancia: " + distancia);

                    if (distancia <= distanciaUsuario) {
                        vinculosPerto.add(v);
                    }
                    
                    }
                    catch(JSONException jsonEx){
                        Logger.getLogger(FiltrarServlet.class.getName()).log(Level.SEVERE, null, jsonEx);
                    }
                    
                    
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(FiltrarServlet.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

//                request.setAttribute("vinculos", vinculosPerto);
                url = "listaclinicas_medinder.jsp";
                RequestDispatcher rd = request.getRequestDispatcher(url);
                rd.forward(request, response);
            }
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
