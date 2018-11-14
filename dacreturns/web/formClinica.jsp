<%-- 
    Document   : formClinica
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
    <script src="http://code.jquery.com/jquery-1.8.2.js"></script>
    <script src="http://code.jquery.com/ui/1.9.0/jquery-ui.js"></script>
    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jquery.mask/1.14.0/jquery.mask.js"></script>
   
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
    
    <body>
        
    <section class="contentpage">
    
    <jsp:useBean id="login" scope="session" class="br.ufpr.tads.dac.beans.AdministradorBean" />
        <c:choose>
            <c:when test = "${not empty login.login}">
                <c:choose>
                    <c:when test = "${formType == 1}">
                    <div class="container">
          
                            <form action="clinicas?action=new" method="POST">
                            <div class="row">


                                <div class="col-md-12"><h1> Cadastrar Clínica</h1><br></div>

                                <div class="col-md-6">                    
                                    <div class="form-group">
                                        <label>Nome:</label><br>
                                        <input class="form-control" type="text" name="nome" placeholder="Nome" required>
                                    </div>
                                </div>
                                
                                <div class="col-md-6">                    
                                    <div class="form-group">
                                        <div class="form-group">
                                            <label>Telefone:</label><br>
                                            <input class="form-control" type="text" name="telefone" placeholder="Telefone" required>
                                        </div>
                                    </div>
                                </div>

                                <div class="col-md-6">                    
                                    <div class="form-group">
                                        <div class="form-group">
                                            <label>E-mail:</label><br>
                                            <input class="form-control" type="email" name="email" placeholder="Email" required>
                                        </div>
                                    </div>
                                </div>
                                
                                <div class="col-md-6">                    
                                    <div class="form-group">
                                        <div class="form-group">
                                            <label>Rua:</label><br>
                                            <input class="form-control" type="text" name="rua" placeholder="Rua" required>
                                        </div>
                                    </div>
                                </div>
                                
                                <div class="col-md-6">                    
                                    <div class="form-group">
                                        <div class="form-group">
                                            <label>Número:</label><br>
                                            <input class="form-control" type="number" name="numero" placeholder="0000" required>
                                        </div>
                                    </div>
                                </div>
                                
                                <div class="col-md-6">                    
                                    <div class="form-group">
                                        <div class="form-group">
                                            <label>Bairro:</label><br>
                                            <input class="form-control" type="text" name="bairro" placeholder="Bairro" required>
                                        </div>
                                    </div>
                                </div>
                                
                                <div class="col-md-6">                    
                                    <div class="form-group">
                                        <div class="form-group">
                                            <label>CEP:</label><br>
                                            <input class="form-control" type="text" name="cep" placeholder="00000000000" required>
                                        </div>
                                    </div>
                                </div>
                                
                                <div class="col-md-4">                    
                                    <div class="form-group">
                                        <label>Cidade:</label><br>
                                        <select id="cidade" name="cidade" required>
                                            
                                         </select>
                                    </div>
                                </div>

                                <div class="col-md-2">                    
                                    <div class="form-group">
                                        <label>UF</label><br>
                                        <select id="estado" name="estado" required>
                                         <c:forEach items="${estados}" var="x">
                                                <option value="${x.id}">${x.uf}</option>
                                        </c:forEach>
                                        </select>
                                    </div>
                                </div>
                                
                                <div class="col-md-12">  
                                </div>

                                <div class="col-md-6">                    
                                    <div class="form-group">
                                        <div class="form-group">
                                            <label>Idade mínima:</label><br>
                                            <input class="form-control" type="number" name="idadede" placeholder="Idade" required>
                                        </div>
                                    </div>
                                </div>
                                
                               

                                <div class="col-md-6">                    
                                    <div class="form-group">
                                        <div class="form-group">
                                            <label>Idade máxima:</label><br>
                                            <input class="form-control" type="number" name="idadeate" placeholder="Idade" required>
                                        </div>
                                    </div>
                                </div>
                                
                                
                                <div class="col-md-12">  
                                </div>


                                <div class="col-md-6">    
                                    <br>
                                </div>
                                <br>
                                
                                <div class="col-md-12"><br></div>
                                <div class="col-md-6">
                                    <a href="clinicas" class="btn btn-block btn-danger">Cancelar</a>
                                </div>
                                <div class="col-md-6">
                                    <button class="btn btn-block btn-success" type="submit">Cadastrar</button>
                                </div>
                                <div class="col-md-12"><br></div>


                            </div>
                            </form>
                    </div>
            </c:when>
            <c:otherwise>
                    <div class="container">
          
                            <form action="clinicas?action=update" method="POST">
                            <div class="row">


                                <div class="col-md-12"><h1> Editar Clínica</h1><br></div>

                                <div class="col-md-6">                    
                                    <div class="form-group">
                                        <label>Nome:</label><br>
                                        <input class="form-control" type="text" name="nome" placeholder="Nome" value="<c:out value="${clinica.nome}"/>" required>
                                    </div>
                                </div>
                                
                                <div class="col-md-6">                    
                                    <div class="form-group">
                                        <div class="form-group">
                                            <label>Telefone:</label><br>
                                            <input class="form-control" type="text" name="telefone" placeholder="Telefone" value="<c:out value="${clinica.telefone}"/>" required>
                                        </div>
                                    </div>
                                </div>

                                <div class="col-md-6">                    
                                    <div class="form-group">
                                        <div class="form-group">
                                            <label>E-mail:</label><br>
                                            <input class="form-control" type="email" name="email" placeholder="Email" value="<c:out value="${clinica.email}"/>" required>
                                        </div>
                                    </div>
                                </div>
                                
                                <div class="col-md-6">                    
                                    <div class="form-group">
                                        <div class="form-group">
                                            <label>Rua:</label><br>
                                            <input class="form-control" type="text" name="rua" placeholder="Rua" value="<c:out value="${clinica.endereco.logradouro}"/>" required>
                                        </div>
                                    </div>
                                </div>
                                
                                <div class="col-md-6">                    
                                    <div class="form-group">
                                        <div class="form-group">
                                            <label>Número:</label><br>
                                            <input class="form-control" type="number" name="numero" placeholder="0000" value="<c:out value="${clinica.endereco.numero}"/>" required>
                                        </div>
                                    </div>
                                </div>
                                
                                <div class="col-md-6">                    
                                    <div class="form-group">
                                        <div class="form-group">
                                            <label>Bairro:</label><br>
                                            <input class="form-control" type="text" name="bairro" placeholder="Bairro" value="<c:out value="${clinica.endereco.bairro}"/>" required>
                                        </div>
                                    </div>
                                </div>
                                
                                <div class="col-md-6">                    
                                    <div class="form-group">
                                        <div class="form-group">
                                            <label>CEP:</label><br>
                                            <input class="form-control" type="text" name="cep" placeholder="00000000000" value="<c:out value="${clinica.endereco.cep}"/>" required>
                                        </div>
                                    </div>
                                </div>
                                
                                <div class="col-md-4">                    
                                    <div class="form-group">
                                        <label>Cidade:</label><br>
                                        <select id="cidade" name="cidade" required>
                                            <c:forEach items="${cidades}" var="x">
                                             <c:if test = "${x.id == clinica.endereco.cidade.id}">
                                                 <option value="${x.id}" selected>${x.nome}</option>                                            
                                             </c:if>
                                             <c:if test = "${x.id != clinica.endereco.cidade.id}">
                                                 <option value="${x.id}">${x.nome}</option>
                                             </c:if>
                                            </c:forEach>
                                         </select>
                                    </div>
                                </div>

                                <div class="col-md-2">                    
                                    <div class="form-group">
                                        <label>UF</label><br>
                                        <select id="estado" name="estado" required>
                                         <c:forEach items="${estados}" var="x">
                                             <c:if test = "${x.id == clinica.endereco.estado.id}">
                                                 <option value="${x.id}" selected>${x.uf}</option>
                                                 getCidades();                                                 
                                             </c:if>
                                             <c:if test = "${x.id != clinica.endereco.estado.id}">
                                                 <option value="${x.id}">${x.uf}</option>
                                             </c:if>
                                        </c:forEach>
                                        </select>
                                    </div>
                                </div>
                                
                                <div class="col-md-12">  
                                </div>

                                <div class="col-md-6">                    
                                    <div class="form-group">
                                        <div class="form-group">
                                            <label>Idade mínima:</label><br>
                                            <input class="form-control" type="number" name="idadede" placeholder="Idade" value="<c:out value="${clinica.minIdade}"/>" required>
                                        </div>
                                    </div>
                                </div>
                                
                               

                                <div class="col-md-6">                    
                                    <div class="form-group">
                                        <div class="form-group">
                                            <label>Idade máxima:</label><br>
                                            <input class="form-control" type="number" name="idadeate" placeholder="Idade" value="<c:out value="${clinica.maxIdade}"/>" required>
                                        </div>
                                    </div>
                                </div>
                                
                                        <input name="id" type="hidden" value="<c:out value="${clinica.id}"/>">
                                        <input name="enderecoid" type="hidden" value="<c:out value="${clinica.endereco.id}"/>">
                                        
                                <div class="col-md-12">  
                                </div>


                                <div class="col-md-6">    
                                    <br>
                                </div>
                                <br>
                                
                                <div class="col-md-12"><br></div>
                                <div class="col-md-6">
                                    <a href="clinicas" class="btn btn-block btn-danger">Cancelar</a>
                                </div>
                                <div class="col-md-6">
                                    <button class="btn btn-block btn-success" type="submit">Salvar</button>
                                </div>
                                <div class="col-md-12"><br></div>


                            </div>
                            </form>
                    </div>
            </c:otherwise>
        </c:choose>
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
