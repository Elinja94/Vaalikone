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
	    <title>Vaalikone kysymykset</title>
	</head>

  <body>
    <%
    List<Candidate> candidateslist=(List<Candidate>)request.getAttribute("candidate");
	%>
		<div class="content">
			<h2>Candidates</h2>
			<form action=/candidates method="POST">
			
			      
			    <c:forEach var="candidate" items="${requestScope.candidatesList}" >
					<li>${candidate.id}: ${candidate.sukunimi} ${candidate.etunimi} ${candidate.puolue} ${candidate.kotipaikkakunta} ${candidate.ika} ${candidate.miksi_eduskuntaan} ${candidate.mita_asioita_haluat_edistaa} ${candidate.ammatti} <a href='/poistaEhdokas?id=${candidate.id}' onclick='return confirm("Haluatko varmasti poistaa kysymyksen: ${candidate.sukunimi} ${candidate.etunimi}?");'>Poista</a> <a href='/muokkaaEhdokas?id=${candidate.id}'>Muokkaa</a> <a href='/ehdokkaankysymykset?candidate=${candidate.id}'>Muokkaa vastauksia</a></li><br>
				</c:forEach>
				
		        <div>
	          	<button type="submit" class="button">Lähetä vastaukset</button>
		      	</div>	
		    </form>
		   </div>
  </body>
</html>