<%-- 
    Document   : formMedico
    Created on : 19/08/2018, 13:53:45
    Author     : luis_
--%>

<%@page import="br.ufpr.tads.dac.beans.AdministradorBean"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page errorPage="erro.jsp" %>
<!DOCTYPE html>
<html>
    
    <c:import url = "head.html" />
    
       
    <c:import url = "header.html" />
    <body>
        
    <section class="contentpage">
    
        <div class="container">
          
                            <form action="clientes?action=new" method="POST">
                            <div class="row">


                                <div class="col-md-12"><h1> Cadastrar Médico</h1><br></div>

                                <div class="col-md-6">                    
                                    <div class="form-group">
                                        <label>Nome:</label><br>
                                        <input class="form-control" type="text" name="nome" placeholder="Nome" required>
                                    </div>
                                </div>
                                    
                                <div class="col-md-12">  
                                </div>
                                    
                                
                                <div class="col-md-6">                    
                                    <div class="form-group">
                                        <label>Clínica:</label><br>
                                        <select class="form-group" name="clinica" required>
                                            <option value="1">ICS</option>
                                        </select>
                                    </div>
                                </div>
                                
                                <div class="col-md-12">  
                                </div>

                                <div class="col-md-6">                    
                                    <div class="form-group">
                                        <label>Especialidade:</label><br>
                                        <select class="form-group" name="clinica" required>
                                            <option value="1">Ortopedista</option>
                                        </select>
                                    </div>
                                </div>

                                <div class="col-md-6">    
                                    <br>
                                </div>
                                <br>
                                
                                <div class="col-md-12"><br></div>
                                <div class="col-md-6">
                                    <a href="clientes" class="btn btn-block btn-danger">Cancelar</a>
                                </div>
                                <div class="col-md-6">
                                    <button class="btn btn-block btn-success" type="submit">Cadastrar</button>
                                </div>
                                <div class="col-md-12"><br></div>


                            </div>
                            </form>
                                    
    </section>
    </body>
</html>
