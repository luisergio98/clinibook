<%-- 
    Document   : atualizadados_medinder
    Created on : 30/08/2018, 22:06:26
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

	<title>Dados Pessoais</title>
        
        <script type="text/javascript" >
        
        
        $(document).ready(function() {
                            
        <c:choose>
                    <c:when test = "${formType == 1}">
                            getCidades();
                    </c:when>
                    <c:otherwise>
                
                    </c:otherwise>         
        </c:choose>
            

                            $( "#estado" ).change(function() {
                              getCidades();

                            });
                        });
                    
                

            function getCidades(){
                var estadoId = $("#estado").val();
                console.log(estadoId)
                var url = "AJAXServlet";
                $.ajax({
                        url : url, // URL da sua Servlet
                        data : {
                            estadoId : estadoId
                        }, // Parâmetro passado para a Servlet
                        dataType : 'json',
                        success : function(data) {
                            // Se sucesso, limpa e preenche a combo de cidade
                            // alert(JSON.stringify(data));
                            $("#cidade").empty();
                            $.each(data, function(i, obj) {
                                $("#cidade").append('<option value=' + obj.id + '>' + obj.nome + '</option>');
                            });
                        },
                        error : function(request, textStatus, errorThrown) {
                            alert(request.status + ', Error: ' + request.statusText);
                             // Erro
                        }
                    });
            }
            
           
        </script>
        
</head>
    <style>
        .modal-backdrop.fade.in {

            display: none;  

        }
    </style>
<body>
    <c:import url = "menu.jsp" />
                <c:choose>
                    <c:when test = "${formType == 1}">
                        <div class="container" id="formulario1">
                            <h2>Cadastrar Dados</h2>
                            <form action="cliente?action=new" method="POST">
                                    <div class="row">
                                            <div class="col-sm-6">
                                                            <div class="form-group">
                                                            <label for="FormControlInput1">Nome:</label>
                                                            <input type="text" class="form-control" name="nome" id="nome" required> 
                                                            </div>
                                                    </div>
                                                    <div class="col-sm-6">
                                                            <div class="form-group">
                                                            <label for="FormControlInput2">Email:</label>
                                                            <input type="email" class="form-control" name="email" id="email" required>
                                                            </div>
                                                    </div>
                                            </div>
                                            <div class="form-group">
                                            <div class="row">
                                                    <div class="col-sm-4">
                                                                    <div class="form-group">
                                                                    <label for="FormControlInput3">Senha:</label>
                                                                    <input  class="form-control" type="password" name="senha" id="senha" required>
                                                                    </div>
                                                            </div>
                                                            <div class="col-sm-2">
                                                                    <div class="form-group">
                                                                    <label for="FormControlInput6">Telefone:</label>
                                                                    <input type="text" class="form-control" name="telefone" id="telefone" required>
                                                                    </div>
                                                            </div>
                                                            <div class="col-sm-2">
                                                                    <div class="form-group">
                                                                    <label for="FormControlInput6">Idade:</label>
                                                                    <input type="number" class="form-control" name="idade" id="idade" required>
                                                                    </div>
                                                            </div>
                                                            <div class="col-sm-4">
                                                                    <div class="form-group">
                                                                    <label for="FormControlInput6">Distância Máxima para Busca em Km:</label>
                                                                    <input type="number" class="form-control" name="distancia" id="distancia" required>
                                                                    </div>
                                                            </div>
                                                    </div>
                                                    <div class="row">
                                                    <div class="col-sm-3">
                                                                    <div class="form-group">
                                                                    <label for="FormControlInput3">CEP:</label>
                                                                    <input  type="text" class="form-control" name="cep" id="cep" required>
                                                                    </div>
                                                            </div>
                                                            <div class="col-sm-4">
                                                                    <div class="form-group">
                                                                    <label for="FormControlInput6">Rua:</label>
                                                                    <input type="text" class="form-control" name="rua" id="rua" required>
                                                                    </div>
                                                            </div>
                                                            <div class="col-sm-2">
                                                                    <div class="form-group">
                                                                    <label for="FormControlInput3">Número:</label>
                                                                    <input  type="text" class="form-control" name="numero" id="numero" required>
                                                                    </div>
                                                            </div>
                                                            <div class="col-sm-3">
                                                                    <div class="form-group">
                                                                    <label for="FormControlInput3">Bairro:</label>
                                                                    <input type="text" class="form-control" name="bairro" id="bairro" required>
                                                                    </div>
                                                            </div>
                                                    </div>
                                                    <div class="row">
                                                    <div class="col-sm-4">
                                                                    <div class="form-group">
                                                                    <label for="sel2">Cidade:</label>
                                                                        <select  class="form-control" id="cidade" name="cidade">

                                                                        </select>
                                                                    </div>
                                                            </div>
                                                            <div class="col-sm-4">
                                                                    <div class="form-group">
                                                                    <div class="form-group">
                                                                            <label for="sel2">Estado:</label>
                                                                                 <select  class="form-control" id="estado" name="estado">
                                                                                    <c:forEach items="${estados}" var="x">
                                                                                            <option value="${x.id}">${x.uf}</option>
                                                                                    </c:forEach>
                                                                                 </select>
                                                                            </div>
                                                                    </div>
                                                            </div>
                                                            <div class="col-sm-4">
                                                                    <div class="form-group">
                                                                    <div class="form-group">
                                                                            <label for="sel2">Plano:</label>
                                                                            <select  class="form-control" id="plano" name="plano">
                                                                                    <c:forEach items="${planos}" var="x">
                                                                                            <option value="${x.id}">${x.nome}</option>
                                                                                    </c:forEach>
                                                                                  </select>
                                                                            </div>
                                                                    </div>
                                                            </div>
                                                   
                                                            </div>
                                                    </div>
                                                    <div class="row">
                                                            <div class="col-sm-4"></div>
                                                            <div class="col-sm-2">
                                                                    <button id="botao" class="btn btn-primary" type="submit">Cadastrar</button>
                                                            </div>
                                                            <div class="col-sm-2">
                                                                    <a href="filtrar" id="botaoback" class="btn btn-primary">Voltar</a>
                                                            </div>
                                                            <div class="col-sm-4"></div>
                                                     </div>
                                            </div>
                                    </form>
                        </div>
            </c:when>
                    <c:otherwise>
                        <div class="container" id="formulario1">
                            <h2>Atualizar Dados</h2>
                            <form action="cliente?action=update" method="POST">
                                    <div class="row">
                                            <div class="col-sm-6">
                                                            <div class="form-group">
                                                            <label for="FormControlInput1">Nome:</label>
                                                            <input type="text" class="form-control" name="nome" id="nome" value="<c:out value="${login.nome}"/>" required> 
                                                            </div>
                                                    </div>
                                                    <div class="col-sm-6">
                                                            <div class="form-group">
                                                            <label for="FormControlInput2">Email:</label>
                                                            <input type="email" class="form-control" name="email" id="email" value="<c:out value="${login.email}"/>" required>
                                                            </div>
                                                    </div>
                                            </div>
                                            <div class="form-group">
                                            <div class="row">
                                                    <div class="col-sm-4">
                                                                    <div class="form-group">
                                                                    <label for="FormControlInput3">Senha:</label>
                                                                    <input  class="form-control" type="password" name="senha" id="senha" value="<c:out value="${login.senha}"/>" required>
                                                                    </div>
                                                            </div>
                                                            <div class="col-sm-2">
                                                                    <div class="form-group">
                                                                    <label for="FormControlInput6">Telefone:</label>
                                                                    <input type="text" class="form-control" name="telefone" id="telefone" value="<c:out value="${login.telefone}"/>" required>
                                                                    </div>
                                                            </div>
                                                            <div class="col-sm-2">
                                                                    <div class="form-group">
                                                                    <label for="FormControlInput6">Idade:</label>
                                                                    <input type="number" class="form-control" name="idade" id="idade" value="<c:out value="${login.idade}"/>" required>
                                                                    </div>
                                                            </div>
                                                            <div class="col-sm-4">
                                                                    <div class="form-group">
                                                                    <label for="FormControlInput6">Distância Máxima para Busca em Km:</label>
                                                                    <input type="number" class="form-control" name="distancia" id="distancia" value="<c:out value="${login.distanciaMaxima}"/>" required>
                                                                    </div>
                                                            </div>
                                                    </div>
                                                    <div class="row">
                                                    <div class="col-sm-3">
                                                                    <div class="form-group">
                                                                    <label for="FormControlInput3">CEP:</label>
                                                                    <input  type="text" class="form-control" name="cep" id="cep" value="<c:out value="${login.endereco.cep}"/>" required>
                                                                    </div>
                                                            </div>
                                                            <div class="col-sm-4">
                                                                    <div class="form-group">
                                                                    <label for="FormControlInput6">Rua:</label>
                                                                    <input type="text" class="form-control" name="rua" id="rua" value="<c:out value="${login.endereco.logradouro}"/>" required>
                                                                    </div>
                                                            </div>
                                                            <div class="col-sm-2">
                                                                    <div class="form-group">
                                                                    <label for="FormControlInput3">Número:</label>
                                                                    <input  type="text" class="form-control" name="numero" id="numero" value="<c:out value="${login.endereco.numero}"/>" required>
                                                                    </div>
                                                            </div>
                                                            <div class="col-sm-3">
                                                                    <div class="form-group">
                                                                    <label for="FormControlInput3">Bairro:</label>
                                                                    <input type="text" class="form-control" name="bairro" id="bairro" value="<c:out value="${login.endereco.bairro}"/>" required>
                                                                    </div>
                                                            </div>
                                                    </div>
                                                    <div class="row">
                                                    <div class="col-sm-3">
                                                                    <div class="form-group">
                                                                    <label for="sel2">Cidade:</label>
                                                                        <select  class="form-control" id="cidade" name="cidade">
                                                                        <c:forEach items="${cidades}" var="x">
                                                                        <c:if test = "${x.id == login.endereco.cidade.id}">
                                                                            <option value="${x.id}" selected>${x.nome}</option>                                            
                                                                        </c:if>
                                                                        <c:if test = "${x.id != login.endereco.cidade.id}">
                                                                            <option value="${x.id}">${x.nome}</option>
                                                                        </c:if>
                                                                       </c:forEach>
                                                                        </select>
                                                                    </div>
                                                            </div>
                                                            <div class="col-sm-3">
                                                                    <div class="form-group">
                                                                    <div class="form-group">
                                                                            <label for="sel2">Estado:</label>
                                                                                 <select  class="form-control" id="estado" name="estado">
                                                                                    <c:forEach items="${estados}" var="x">
                                                                                        <c:if test = "${x.id == login.endereco.estado.id}">
                                                                                            <option value="${x.id}" selected>${x.uf}</option>
                                                                                            getCidades();                                                 
                                                                                        </c:if>
                                                                                        <c:if test = "${x.id != login.endereco.estado.id}">
                                                                                            <option value="${x.id}">${x.uf}</option>
                                                                                        </c:if>
                                                                                   </c:forEach>
                                                                                 </select>
                                                                            </div>
                                                                    </div>
                                                            </div>
                                                            <div class="col-sm-4">
                                                                    <div class="form-group">
                                                                    <div class="form-group">
                                                                            <label for="sel2">Plano:</label>
                                                                            <select  class="form-control" id="plano" name="plano">
                                                                                    <c:forEach items="${planos}" var="x">
                                                                                    <c:if test = "${x.id == login.plano.id}">
                                                                                        <option value="${x.id}" selected>${x.nome}</option>                                            
                                                                                    </c:if>
                                                                                    <c:if test = "${x.id != login.plano.id}">
                                                                                        <option value="${x.id}">${x.nome}</option>
                                                                                    </c:if>
                                                                                   </c:forEach>
                                                                                  </select>
                                                                            </div>
                                                                    </div>
                                                            </div>
                                                        
                                                                <input name="id" type="hidden" value="<c:out value="${login.id}"/>">
                                                                <input name="enderecoid" type="hidden" value="<c:out value="${login.endereco.id}"/>">
                                                        
                                                            <div class="col-sm-1">
                                                                    <button type="button" class="btn btn-default btn-sm"  data-toggle="modal" data-target="#excluirconta">
                                                                    <span class="glyphicon glyphicon-trash"><h5>Deletar-Conta</h5></span> 
                                                            </button>
                                                    <div class="modal fade" id="excluirconta" role="dialog">
                                                            <div class="modal-dialog">
                          <!-- Modal content-->
                                                                  <div class="modal-content">
                                                                    <div class="modal-header">
                                                                      <button type="button" class="close" data-dismiss="modal">&times;</button>
                                                                    </div>
                                                                    <div class="modal-body">
                                                                            <h4 class="modal-title"style="text-align: center;" > <span class="glyphicon glyphicon-bullhorn"></span> Aviso do Sistema</h4>
                                                                            <h5 style="text-align: center;">Voce tem certeza que deseja excluir essa conta?</h5>
                                                                    </div>
                                                                    <div class="modal-footer">
                                                                      <a href="cliente?action=remove&id=<c:out value="${login.id}"/>" class="btn btn-default">Sim!</a>
                                                                      <button type="button" class="btn btn-default" data-dismiss="modal">Cancelar</button>
                                                                    </div>
                                                                  </div>

                                                                    </div>
                                                            </div>
                                                            </div>
                                                    </div>
                                                    <div class="row">
                                                            <div class="col-sm-4"></div>
                                                            <div class="col-sm-2">
                                                                    <button id="botao" class="btn btn-primary" type="submit">Atualizar</button>
                                                            </div>
                                                            <div class="col-sm-2">
                                                                    <a href="filtrar" id="botaoback" class="btn btn-primary">Voltar</a>
                                                            </div>
                                                            <div class="col-sm-4"></div>
                                                     </div>
                                            </div>
                                    </form>
                        </div>
                    </c:otherwise>
            </c:choose>
    
</body>
</html>

