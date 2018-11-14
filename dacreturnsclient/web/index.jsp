<%-- 
    Document   : index
    Created on : 30/08/2018, 22:25:17
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

	<title>Login</title>
</head>
<body id="LoginForm">
   <nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
      <div class="container">
        <h4 id="title1">Me<h4 id="title2">Dinder</h4></h4>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
          <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarResponsive">
        </div>
      </div>
    </nav>
    <div class="container" style="padding-top: 5%">
		<div class="login-form">
			<div class="main-div">
			    <div class="panel">
			   <h2>Login</h2>
			   <p>Insira seu Email e Senha</p>
                           <div style="color: red;">
                               <c:out value="${msg}"/>
                               <c:out value="${param.msg}"/>
                           </div>
			   </div>
                           <form id="Login" action="login" method="POST">
			        <div class="form-group">
			            <input type="email" name="email" class="form-control" id="inputEmail" placeholder="Email"  required">
			        </div>
			        <div class="form-group">
			            <input type="password" name="password" class="form-control" id="inputPassword" placeholder="Senha" required>
			        </div>
			        <div class="forgot">
					</div>
			        <button type="submit" class="btn btn-primary">Login</button>
                                <br><br>
                                <a href="cliente?action=formNew" class="btn btn-success">Cadastre-se!</a>
			    </form>
                           
                           
		    </div>
		</div>
	</div>

</body>
</html>

