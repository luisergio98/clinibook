<%-- 
    Document   : tiposListar
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
    
    <script>
            function myFunction(x) {
                var r = confirm("Tem certeza?");
                if (r == true) {
                    var link = document.getElementById(x);
                    link.setAttribute('href', "tipos?action=remove&id="+x);
                } else {
                    return false;
                } 
            }
            
            
    </script>
    
    <body>
        
        <section class="contentpage">
        <jsp:useBean id="login" scope="session" class="br.ufpr.tads.dac.beans.AdministradorBean" />
                <c:choose>
                    <c:when test = "${not empty login.login}">
			<div class="container-fluid">
                            <h1>Lista de Especialidades</h1>
                            <a href="tipos?action=formNew" class="btn btn-success"> Adicionar </a> 
                            <a href="portal" class="btn btn-danger"> Voltar </a>
                            <br>
                            <br>
                            <table class="table table-hover">
                                <th>Nome</th>
                                <th> </th>
                                <th> </th>
                                <th> </th>

                            
                                    <c:forEach items="${tipos}" var="x">
                                    <tr>
                                    <td>${x.nome}</td>
                                    <td></td>
                                    <td></td>
                                    <td><a href="" role="button" data-toggle="modal" data-target="#Modal<c:out value='${x.id}'/>" <i class="fa fa-eye"> </i></a></td>
                                    
                                    <!-- Modal -->
                                    <div class="modal fade" id="Modal<c:out value="${x.id}"/>" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                                      <div class="modal-dialog" role="document">
                                        <div class="modal-content">
                                          <div class="modal-header">
                                            <h5 class="modal-title" id="exampleModalLabel">Plano de Saúde</h5>
                                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                              <span aria-hidden="true">&times;</span>
                                            </button>
                                          </div>
                                          <div class="modal-body">
                                              <p>
                                                  <strong>Nome:</strong>&nbsp;<c:out value="${x.nome}"/>
                                              </p>
                                          </div>
                                          <div class="modal-footer">
                                            <button type="button" class="btn btn-secondary" data-dismiss="modal">Fechar</button>
                                          </div>
                                        </div>
                                      </div>
                                    </div>
                                    <!-- Fim Modal -->
                                    
                                    <td><a href="tipos?action=formUpdate&id=${x.id}"><i class="fa fa-pencil-square"> </i></a></td>
                                    <td><a id="${x.id}" onclick="myFunction(${x.id})" href=""><i class="fa fa-trash"> </i></a></td>
                                    </tr>
                                    </c:forEach>
                            

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
