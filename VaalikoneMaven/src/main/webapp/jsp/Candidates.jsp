<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
 
<%@ page import="java.util.List" %>   
<%@ page import="data.Ehdokkaat" %>   

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" lang="fi">
	<head>
	    <meta http-equiv="content-type" content="application/xhtml+xml;" charset="UTF-8" />
	    <link rel="stylesheet" href="http://localhost:8080/style.css">
	    <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Marck+Script&display=swap">
	    <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Kanit:wght@300&family=Marck+Script&display=swap">
	    <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=ABeeZee&family=Kanit:wght@300&family=Marck+Script&display=swap">
	    <link rel="icon" type="image/x-icon" href="http://localhost:8080/images/favicon.png">
	    <title>Vaalikone - Ehdokkaat</title>
  	</head>

  	<body>
  		<header>
		  	<a href="http://localhost:8080/" class="links"><img class="logo" src="http://localhost:8080/images/logo.png" alt="Vaalikone logo"></a>
			<a href="/vastaakysymyksiin" class="links">Vastaa kysymyksiin</a>
			<a href="/candidates" class="links">Ehdokkaat</a>
			<a href="http://localhost:8080/admin" class="logout">Kirjaudu sisään</a>
		</header>
    <%
    List<Ehdokkaat> candidatelist=(List<Ehdokkaat>)request.getAttribute("candidate");
	%>
		<div class="content">
			 <c:forEach var="candidate" items="${requestScope.candidatelist}" >
			 <a href='/ehdokas?id=${candidate.getId()}' style="text-decoration: none; color: #262742;">
			 	<div class="candidateBox">
			 		<span class="candidateNumber">${candidate.getId()}</span>
					<h3> ${candidate.getSukunimi()} ${candidate.getEtunimi()}</h3>
					<h3 style="-webkit-text-stroke: 0px;">${candidate.getPuolue()}</h3>
				</div> 
			</a>
			</c:forEach>
		</div>
  </body>
</html>