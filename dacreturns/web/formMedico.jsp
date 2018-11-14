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
        
        <jsp:useBean id="login" scope="session" class="br.ufpr.tads.dac.beans.AdministradorBean" />
        <c:choose>
            <c:when test = "${not empty login.login}">
                <c:choose>
                    <c:when test = "${formType == 1}">
                    <div class="container">

                                        <form action="medicos?action=new" method="POST">
                                        <div class="row">


                                            <div class="col-md-12"><h1> Cadastrar Médico</h1><br></div>

                                            <div class="col-md-6">                    
                                                <div class="form-group">
                                                    <label>Nome:</label><br>
                                                    <input class="form-control" type="text" name="nome" placeholder="Nome" required>
                                                </div>
                                            </div>

                                            <div class="col-md-6">                    
                                                <div class="form-group">
                                                    <label>CRM:</label><br>
                                                    <input class="form-control" type="text" name="crm" placeholder="CRM" required>
                                                </div>
                                            </div>

                                            <div class="col-md-12">  
                                            </div>


                                            <div class="col-md-6">                    
                                                <div class="form-group">
                                                    <label>Especialidade:</label><br>
                                                    <select class="form-group" name="tipo" required>
                                                    <c:forEach items="${tipos}" var="x">
                                                        <option value="<c:out value="${x.id}"/>"><c:out value="${x.nome}"/></option>
                                                    </c:forEach>
                                                    </select>
                                                </div>
                                            </div>

                                            <div class="col-md-6">                    
                                                <div class="form-group">
                                                    <label>Clínica:</label><br>
                                                    <select class="form-group" name="clinica" required>
                                                    <c:forEach items="${clinicas}" var="x">
                                                        <option value="<c:out value="${x.id}"/>"><c:out value="${x.nome}"/></option>
                                                    </c:forEach>
                                                    </select>
                                                </div>
                                            </div>

                                            <div class="col-md-6">    
                                                <br>
                                            </div>
                                            <br>

                                            <div class="col-md-12"><br></div>
                                            <div class="col-md-6">
                                                <a href="medicos" class="btn btn-block btn-danger">Cancelar</a>
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

                                        <form action="medicos?action=update" method="POST">
                                        <div class="row">


                                            <div class="col-md-12"><h1> Cadastrar Médico</h1><br></div>

                                            <div class="col-md-6">                    
                                                <div class="form-group">
                                                    <label>Nome:</label><br>
                                                    <input class="form-control" type="text" name="nome" placeholder="Nome" value="<c:out value="${medico.nome}"/>" required>
                                                </div>
                                            </div>

                                            <div class="col-md-6">                    
                                                <div class="form-group">
                                                    <label>CRM:</label><br>
                                                    <input class="form-control" type="text" name="crm" placeholder="CRM"  value="<c:out value="${medico.crm}"/>"required>
                                                </div>
                                            </div>

                                            <div class="col-md-12">  
                                            </div>


                                            <div class="col-md-6">                    
                                                <div class="form-group">
                                                    <label>Especialidade:</label><br>
                                                    <select class="form-group" name="tipo" required>
                                                    <c:forEach items="${tipos}" var="x">
                                                        <c:if test = "${x.id == medico.tipo.id}">
                                                            <option value="${x.id}" selected>${x.nome}</option>                                            
                                                        </c:if>
                                                        <c:if test = "${x.id != medico.tipo.id}">
                                                            <option value="${x.id}">${x.nome}</option>
                                                        </c:if>
                                                    </c:forEach>
                                                    </select>
                                                </div>
                                            </div>

                                            <div class="col-md-6">                    
                                                <div class="form-group">
                                                    <label>Clínica:</label><br>
                                                    <select class="form-group" name="clinica" required>
                                                    <c:forEach items="${clinicas}" var="x">
                                                        <c:if test = "${x.id == medico.clinica.id}">
                                                            <option value="${x.id}" selected>${x.nome}</option>                                            
                                                        </c:if>
                                                        <c:if test = "${x.id != medico.clinica.id}">
                                                            <option value="${x.id}">${x.nome}</option>
                                                        </c:if>
                                                    </c:forEach>
                                                    </select>
                                                </div>
                                            </div>

                                            <div class="col-md-6">    
                                                <input type="hidden" name="id" value="<c:out value="${medico.id}"/>">
                                                <br>
                                            </div>
                                            <br>

                                            <div class="col-md-12"><br></div>
                                            <div class="col-md-6">
                                                <a href="medicos" class="btn btn-block btn-danger">Cancelar</a>
                                            </div>
                                            <div class="col-md-6">
                                                <button class="btn btn-block btn-success" type="submit">Salvar</button>
                                            </div>
                                            <div class="col-md-12"><br></div>


                                        </div>
                                        </form>
                            </div>
                        </c:otherwise>
                    
                </c:choose>:

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
