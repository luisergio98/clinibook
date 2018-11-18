<%-- 
    Document   : listaclinicas_medinder
    Created on : 30/08/2018, 22:24:20
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
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<script src="http://code.jquery.com/jquery-1.11.3.min.js"></script>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>	

	<title>Lista Clínicas</title>
</head>
<style>
        .modal-backdrop.fade.in {

            display: none;  

        }
</style>
<body>
   <c:import url = "menu.jsp" />
   <jsp:useBean id="login" scope="session" class="br.ufpr.tads.dac.beans.ClienteBean" />
    <c:choose>
    <c:when test = "${not empty login.nome}">
    <div class="container" id="buscaclin">
        <div class="text-center"><h4>Plano de Saúde: <strong><c:out value="${login.plano.nome}"/></strong></h4></div>
        <hr />
    	<table class="table table-striped">
		  <thead>
		    <tr>
		      <th scope="col" id="detalhesconsult">Clínica</th>
		      <th scope="col" id="detalhesconsult">Endereço</th>
		      <th scope="col" id="detalhesconsult">Detalhes </th>
		    </tr>
		  </thead>
		  <tbody>
                      
                    <c:forEach items="${vinculos}" var="x">
		    <tr>
                      <td id="detalhesconsult"><c:out value="${x.clinica.nome}"/></td>
		      <td id="detalhesconsult" ><c:out value="${x.clinica.endereco.logradouro}"/>, <c:out value="${x.clinica.endereco.numero}"/></td> 
		      <td id="detalhesconsult"><button type="button" class="btn btn-default" data-toggle="modal" data-target="#myModal<c:out value="${x.id}"/>"  style="font-size:20px"> <i class="fa fa-hospital-o"></i></button></td>
		    </tr>
                    
                    
                    <!-- Modal1 -->
		  <div class="modal fade" id="myModal<c:out value="${x.id}"/>" role="dialog">
		    <div class="modal-dialog">
		    
		      <!-- Modal content-->
		      <div class="modal-content">
		        <div class="modal-header">
		          <button type="button" class="close" data-dismiss="modal">&times;</button>
		        </div>
		        <div class="modal-body">
		          <h4 class="modal-title">Médicos</h4>
		          <br>
                          <c:choose>
                            <c:when test = "${not empty x.clinica.medicos}">
                              <c:forEach items="${x.clinica.medicos}" var="y">
                                  <p>CRM: <c:out value="${y.crm}"/></p>
                                  <p>Nome: <c:out value="${y.nome}"/></p>
                                  <p>Especialidade: <c:out value="${y.tipo.nome}"/></p>
                                  <p>Clínica: <c:out value="${x.clinica.nome}"/></p>
                                  <br>
                              </c:forEach>
                            </c:when>
                            <c:otherwise>
                                <p>Não existem médicos com a especialidade desejada nesta clínica.</p>
                            </c:otherwise>
                          </c:choose>
		        </div>
		        <div class="modal-footer">
		          <button type="button" class="btn btn-default" data-dismiss="modal">Voltar</button>
		        </div>
		      </div>
		      
		    </div>
		  </div>
                  <!-- Fim Modal1 -->
                  
                  </c:forEach>
                    
		  </tbody>
		</table>
		

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

