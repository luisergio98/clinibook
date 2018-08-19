<%-- 
    Document   : erro
    Created on : 22/03/2018, 17:58:26
    Author     : luis_
--%>

<%@ page buffer="none"  %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page errorPage="erro.jsp" %>
<!DOCTYPE html>
<html>
    
    <c:import url = "head.html" />
    
       
    <c:import url = "header.html" />
    <body>
        
       
        <section class="contentpage">
        
        <div class="container">
            
        <div class="row">
            <div class="col-md-4"> </div>
            <div class="col-md-4">
                <h1 class="text-center">Login</h1>
                <form action="login" method="POST">
                    
                    <div class="form-group">
                        <label>Login:</label><br>
                        <input class="form-control" type="text" name="login" placeholder="Login">
                    </div>
                    
                    <div class="form-group">
                        <label>Senha:</label><br>
                        <input class="form-control" type="password" name="pass" placeholder="Password">
                    </div>
                    <br>
                    <button class="btn btn-success" type="submit"> Entrar </button>
                </form>
                <div style="color: red; text-align: center;">
                        <br>
                        <br>
                        <c:out value="${msg}"/>
                        <c:out value="${param.msg}"/>
                </div>
            </div>
            <div class="col-md-4"> </div>
        </div>
        
        
        </div>
                
        </section>
        
        
    </body>
</html>
