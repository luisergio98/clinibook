<%-- 
    Document   : vinculosListar
    Created on : 19/08/2018, 12:55:28
    Author     : luis_
--%>

<%@page import="br.ufpr.tads.dac.beans.AdministradorBean"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <c:import url = "head.html" />
    
       
    <c:import url = "header.html" />
    
    <script>
            function myFunction(x) {
                var r = confirm("Tem certeza?");
                if (r == true) {
                    var link = document.getElementById(x);
                    link.setAttribute('href', "vinculos?action=remove&id="+x);
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
                            <h1>Lista de Vínculos</h1>
                            <div class="col-md-6" style="padding: 0 !important; margin: 0 !important;">
                            <a href="vinculos?action=formNew" class="btn btn-success"> Adicionar </a> 
                            <a role="button" data-toggle="modal" data-target="#ModalPesquisa" class="btn btn-dark" style="color: #fff !important"> Pesquisar </a> 
                            
                                <!-- Modal -->
                                    <div class="modal fade" id="ModalPesquisa" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                                      <div class="modal-dialog" role="document">
                                        <div class="modal-content">
                                          <div class="modal-header">
                                            <h5 class="modal-title" id="exampleModalLabel">Pesquisar</h5>
                                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                              <span aria-hidden="true">&times;</span>
                                            </button>
                                          </div>
                                          <div class="modal-body">
                                              
                                              <form action="vinculos?action=list&tipolista=clinica" method="POST">
                                                <div>                    
                                                <div class="form-group">
                                                    <label>Por Clínica:</label><br>
                                                    <select class="form-group" name="clinica" required>
                                                    <c:forEach items="${clinicas}" var="x">
                                                        <option value="<c:out value="${x.id}"/>"><c:out value="${x.nome}"/></option>
                                                    </c:forEach>
                                                    </select> &nbsp;
                                                    <button type="submit" class="btn btn-dark" >Pesquisar</button>
                                                </div>
                                                </div>
                                              </form>
                                              
                                              <form action="vinculos?action=list&tipolista=plano" method="POST">
                                                <div>                    
                                                <div class="form-group">
                                                    <label>Por Plano:</label><br>
                                                    <select class="form-group" name="plano" required>
                                                    <c:forEach items="${planos}" var="x">
                                                        <option value="<c:out value="${x.id}"/>"><c:out value="${x.nome}"/></option>
                                                    </c:forEach>
                                                    </select> &nbsp;
                                                    <button type="submit" class="btn btn-dark">Pesquisar</button>
                                                </div>
                                                </div>
                                              </form>
                                              
                                          </div>
                                          <div class="modal-footer">
                                            <button type="button" class="btn btn-secondary" data-dismiss="modal">Fechar</button>
                                          </div>
                                        </div>
                                      </div>
                                    </div>
                                    <!-- Fim Modal -->
                            
                            <a href="portal" class="btn btn-danger"> Voltar </a>
                            </div>
                            <br>
                            <br>
                            <table class="table table-hover">
                                <th>Clínica</th>
                                <th>Plano</th>
                                <th> </th>
                                <th> </th>
                                <th> </th>
                                <th> </th>
                                <th> </th>
                                    <c:forEach items="${vinculos}" var="x">
                                    <tr>
                                    <td>${x.clinica.nome}</td>
                                    <td>${x.plano.nome}</td>
                                    <td></td>
                                    <td></td>
                                    <td><a href="" role="button" data-toggle="modal" data-target="#Modal<c:out value='${x.id}'/>" ><i class="fa fa-eye"> </i></a></td>
                                    
                                    <!-- Modal -->
                                    <div class="modal fade" id="Modal<c:out value="${x.id}"/>" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                                      <div class="modal-dialog" role="document">
                                        <div class="modal-content">
                                          <div class="modal-header">
                                            <h5 class="modal-title" id="exampleModalLabel">Vínculo</h5>
                                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                              <span aria-hidden="true">&times;</span>
                                            </button>
                                          </div>
                                          <div class="modal-body">
                                              <p>
                                                <p><strong>Plano: </strong> <c:out value="${x.plano.nome}"/> </p>
                                                <br>
                                                <p><strong>Clinica: </strong> <c:out value="${x.clinica.nome}"/> </p>
                                                <p><strong>Telefone: </strong> <c:out value='${x.clinica.telefone}'/> </p>
                                                <p><strong>E-mail: </strong> <c:out value='${x.clinica.email}'/> </p>
                                                <p><strong>Idade Mínima: </strong> <c:out value='${x.clinica.minIdade}'/> Ano(s)</p>
                                                <p><strong>Idade Máxima: </strong> <c:out value='${x.clinica.maxIdade}'/> Ano(s)</p>
                                                <br>
                                                <p><strong>Rua: </strong> <c:out value='${x.clinica.endereco.logradouro}'/></p>
                                                <p><strong>Bairro:  </strong> <c:out value='${x.clinica.endereco.bairro}'/></p>
                                                <p><strong>Número: </strong> <c:out value='${x.clinica.endereco.numero}'/></p>
                                                <p><strong>CEP: </strong> <c:out value='${x.clinica.endereco.cep}'/></p>
                                                <p><strong>Cidade: </strong> <c:out value='${x.clinica.endereco.cidade.nome}'/></p>
                                                <p><strong>Estado: </strong> <c:out value='${x.clinica.endereco.estado.nome}'/></p>
                                              </p>
                                          </div>
                                          <div class="modal-footer">
                                            <button type="button" class="btn btn-secondary" data-dismiss="modal">Fechar</button>
                                          </div>
                                        </div>
                                      </div>
                                    </div>
                                    <!-- Fim Modal -->
                                    
                                    <td><a href="vinculos?action=formUpdate&id=${x.id}"><i class="fa fa-pencil-square"> </i></a></td>
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
