<%-- 
    Document   : portal
    Created on : 22/03/2018, 17:23:37
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
            
        <jsp:useBean id="login" scope="session" class="br.ufpr.tads.dac.beans.AdministradorBean" />
                <c:choose>
                    <c:when test = "${not empty login.login}">
                    <div class="container-fluid">
                        <div class="row">
                            <div class="col-md-12"><br></div>
                            <div class="col-md-2"> </div>
                            <div class="col-md-8 text-center">

                                <i style="font-size: 78px;" class="fa fa-user"></i><br>

                            Nome:   ${login.login} <br/>

                            <br>
                            
                            <a href="medicosListar.jsp"><button class="btn btn-info">Médicos</button></a>
                            <a href="clinicasListar.jsp"><button class="btn btn-info">Clínicas</button></a>
                            <a href="tiposListar.jsp"><button class="btn btn-info">Especialidades</button></a>
                            <a href="planosListar.jsp"><button class="btn btn-info">Planos de Saúde</button></a>
                            <br>
                            <br>
                            <a href="logout"><button class="btn btn-danger">Sair</button></a>
                            </div>
                            <div class="col-md-2"> </div>
                        </div>
                    </div>
                    </c:when>
                    <c:otherwise>
                        <jsp:forward page="index.jsp">
                            <jsp:param name='msg' value='Usuário deve se autenticar para acessar o sistema!'/> 
                        </jsp:forward>
                    </c:otherwise>
                </c:choose>
    
        </section>
    </body>
</html>