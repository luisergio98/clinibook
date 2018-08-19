<%-- 
    Document   : planosListar
    Created on : 19/08/2018, 12:55:28
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
                            <h1>Lista de Planos de Saúde</h1>
                            <a href="clientes?action=formNew" class="btn btn-success"> Adicionar </a> 
                            <a href="portal.jsp" class="btn btn-danger"> Voltar </a>
                            <br>
                            <br>
                            <table class="table table-hover">
                                <th>Nome</th>
                                <th> </th>
                                <th> </th>
                                <th> </th>

                            
                                    <tr>
                                    <td>ICS</td>
                                    <td><a href="clientes?action=show&id=1"><i class="fa fa-eye"> </i></a></td>
                                    <td><a href="clientes?action=formUpdate&id=1"><i class="fa fa-pencil-square"> </i></a></td>
                                    <td><a id="1" href=""><i class="fa fa-trash"> </i></a></td>
                                    </tr>
                                    
                                    <tr>
                                    <td>Unimed</td>
                                    <td><a href="clientes?action=show&id=1"><i class="fa fa-eye"> </i></a></td>
                                    <td><a href="clientes?action=formUpdate&id=1"><i class="fa fa-pencil-square"> </i></a></td>
                                    <td><a id="1" href=""><i class="fa fa-trash"> </i></a></td>
                                    </tr>
                                    
                                    <tr>
                                    <td>Amil</td>
                                    <td><a href="clientes?action=show&id=1"><i class="fa fa-eye"> </i></a></td>
                                    <td><a href="clientes?action=formUpdate&id=1"><i class="fa fa-pencil-square"> </i></a></td>
                                    <td><a id="1" href=""><i class="fa fa-trash"> </i></a></td>
                                    </tr>
                            

                            </table>
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
