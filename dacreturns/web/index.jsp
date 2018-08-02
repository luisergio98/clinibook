<%-- 
    Document   : erro
    Created on : 22/03/2018, 17:58:26
    Author     : luis_
--%>

<%@ page buffer="none"   %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page errorPage="erro.jsp" %>
<!DOCTYPE html>
<html>
    
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
    
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
    </head>
    <body>
        
        
        <div class="container">
            
        <div class="row">
            <div class="col-md-4"> </div>
            <div class="col-md-4">
                <h1 class="text-center">Login</h1>
                <form class="form-control" action="login" method="POST">
                    
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
    </body>
</html>
