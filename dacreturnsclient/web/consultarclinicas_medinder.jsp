<%-- 
    Document   : consultarclinicas_medinder
    Created on : 30/08/2018, 22:15:40
    Author     : thiag
--%>

<%@page import="br.ufpr.tads.dac.beans.ClienteBean"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<link rel="stylesheet" type="text/css" href="style.css">
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  	 <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <title>Filtrar</title>
</head>
<body id="consultaclinicas">
    <c:import url = "menu.jsp" />
    <style>
        .modal-backdrop.fade.in {

            display: none;  

        }
    </style>
    
     <jsp:useBean id="login" scope="session" class="br.ufpr.tads.dac.beans.ClienteBean" />
                <c:choose>
                    <c:when test = "${not empty login.nome}">
                        <div class="container" id="consulta">
                            <form action="filtrar?action=buscar" method="post">
                                    <div class="form-group">
                                                     <label for="Especialidade">Especialidade:</label>
                                                    <select class="form-control" id="tipo" name="tipo" required>
                                                      <c:forEach items="${tipos}" var="x">
                                                          <option value="${x.id}">${x.nome}</option>
                                                      </c:forEach>
                                                         
                                                    </select>
                                            </div>
                                            <div class="form-check">
                                            <input type="checkbox" class="form-check-input" id="menordistancia" name="menordistancia">&nbsp;&nbsp;&nbsp;
                                            <label class="form-check-label" for="distancia">Procurar Menor Distância</label>
                                             </div>
                                               <button type="submit" id="buscarconsulta" class="btn btn-primary">Buscar Consulta!</button>
                            </form>
                        </div>
                    </c:when>
                        
                    <c:otherwise>
                        <jsp:forward page="index.jsp">
                            <jsp:param name='msg' value='Usuário deve se autenticar para acessar o sistema!'/> 
                        </jsp:forward>
                    </c:otherwise>
                </c:choose>
</body>
</html>
