<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
 
<%@ page import="java.util.List" %>   
<%@ page import="data.Candidate" %>   

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" lang="fi">
  	<head>
		<meta http-equiv="content-type" content="application/xhtml+xml" charset="UTF-8">
		<link rel="stylesheet" href="style.css">
		<link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Marck+Script&display=swap">
		<link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Kanit:wght@300&family=Marck+Script&display=swap">
		<link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=ABeeZee&family=Kanit:wght@300&family=Marck+Script&display=swap">
		<link rel="icon" type="image/x-icon" href="/images/favicon.png">
		
	    <title>Vaalikone Ehdokkaat</title>
	</head>

  <body>
  		<header>
			<a href="http://localhost:8080/Admin" class="links"><img class="logo" src="/images/logo.png" alt="Vaalikone logo"></a>
			<a href="/kysymykset" class="links">Lista kysymyksistä</a>
			<a href="/ehdokkaat" class="links">Lista ehdokkaista</a>
			<a href="/Logout" class="logout">Kirjaudu ulos</a>
		</header>
    <%
    List<Candidate> candidatelist=(List<Candidate>)request.getAttribute("candidate");
	%>
		<div class="content">
		
			<h2>Ehdokkaat</h2>
			
		
			
				 <c:forEach var="candidate" items="${requestScope.candidatesList}" >
				 <h3>Puolue: </h3> <h4> ${candidate.puolue} </h4>
				 <h3>Ehdokkaan nimi: </h3> <h4> ${candidate.sukunimi} ${candidate.etunimi}  </h4>
				 <h3>${candidate.id} </h3>
				 <h4><a href='/candidateInfo?id=${candidate.id}'>Tutustu ehdokkaaseen</a></h4> 
				</c:forEach>
		        <div>
		       
		       
	          	
		      	</div>	
		
		   </div>
  </body>
</html>