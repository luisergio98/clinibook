<%-- 
    Document   : menu
    Created on : 10/11/2018, 13:35:52
    Author     : luis_
--%>

<%@page import="br.ufpr.tads.dac.beans.ClienteBean"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
      <div class="container">
       <h4 id="title1">Me</h4><h4 id="title2">Dinder</h4>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
          <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarResponsive">
          <ul class="navbar-nav ml-auto">
            <li class="nav-item active">
              <a class="nav-link" href="filtrar">Home
              </a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="filtrar">Procurar Clínica</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" role="button" data-toggle="modal" data-target="#ModalUser">Meus Dados</a>
            </li>
            
                                    <!-- Modal -->
                                    <div class="modal fade" id="ModalUser" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                                      <div class="modal-dialog" role="document">
                                        <div class="modal-content">
                                          <div class="modal-header">
                                            <h5 class="modal-title" id="exampleModalLabel">Meus Dados</h5>
                                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                              <span aria-hidden="true">&times;</span>
                                            </button>
                                          </div>
                                          <div class="modal-body">
                                              <p>
                                                  <strong>Nome:</strong>&nbsp;<c:out value="${login.nome}"/><br>
                                                  <strong>Email:</strong>&nbsp;<c:out value="${login.email}"/><br>
                                                  <strong>Telefone:</strong>&nbsp;<c:out value="${login.telefone}"/><br>
                                                  <strong>Idade:</strong>&nbsp;<c:out value="${login.idade}"/><br>
                                                  <strong>Distância Máxima para Busca:</strong>&nbsp;<c:out value="${login.distanciaMaxima}"/><br>
                                                  <c:choose>
                                                    <c:when test = "${not empty login.plano.nome}">
                                                        <strong>Plano:</strong>&nbsp;<c:out value="${login.plano.nome}"/><br>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <strong>Plano:</strong>&nbsp;Sem Plano Definido<br>
                                                    </c:otherwise>
                                                  </c:choose>
                                                  <strong>Endereco:</strong>&nbsp;<c:out value="${login.endereco.logradouro}"/>, <c:out value="${login.endereco.numero}"/><br>
                                                  <strong>Cidade / Estado:</strong>&nbsp;<c:out value="${login.endereco.cidade.nome}"/> - <c:out value="${login.endereco.estado.uf}"/><br>
                                                  
                                              </p>
                                          </div>
                                          <div class="modal-footer">
                                            <a href="cliente?action=formUpdate" class="btn btn-info">Atualizar Dados</a>
                                            <button type="button" class="btn btn-secondary" data-dismiss="modal">Fechar</button>
                                          </div>
                                        </div>
                                      </div>
                                    </div>
                                    <!-- Fim Modal -->
            
            <li class="nav-item">
              <a class="nav-link" href="logout">Sair</a>
            </li>
          </ul>
        </div>
      </div>
    </nav>
