<%-- 
    Document   : cadastraconta_medinder
    Created on : 30/08/2018, 22:15:40
    Author     : thiag
--%>

<%@page import="br.ufpr.tads.dac.beans.ClienteBean"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<link rel="stylesheet" type="text/css" href="style.css">
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  	 <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <title>Filtrar</title>
</head>

<body id="consultaclinicas">
    <c:import url = "menu.jsp" />
    <div class="container" id="consulta">
        
    	<form action="" method="post">
    		<div class="form-group">
   				 <label for="Especialidade">Especialidade:</label>
    				<select class="form-control" id="Especialidade" required>
    				  <option value="">Escolha uma Especialidade</option>
				      <option value="">Fisioterapeuta</option>
				      <option value="">Psicológo</option>
				      <option value="">Psiquiatra</option>
				      <option value="">Cardiologista</option>
    				</select>
  			</div>
  			<div class="form-check">
    			<input type="checkbox" class="form-check-input" id="distancia">
    			<label class="form-check-label" for="distancia">Procurar Menor Distância</label>
 			 </div>
 			   <button type="submit" id="buscarconsulta" class="btn btn-primary">Buscar Consulta!</button>
		</form>
    </div>
</body>
</html>
