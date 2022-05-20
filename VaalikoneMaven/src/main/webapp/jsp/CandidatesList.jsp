<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    
<%@ page import="java.util.ArrayList" %>   
<%@ page import="data.Candidate" %>   

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" lang="fi">
	<head>
		<meta http-equiv="content-type" content="application/xhtml+xml" charset="UTF-8">
	    <link rel="stylesheet" href="http://localhost:8080/style.css">
		<link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Marck+Script&display=swap">
		<link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Kanit:wght@300&family=Marck+Script&display=swap">
		<link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=ABeeZee&family=Kanit:wght@300&family=Marck+Script&display=swap">
		<link rel="icon" type="image/x-icon" href="http://localhost:8080/images/favicon.png">
		<title>Vaalikone Admin - Ehdokkaat</title>
	</head>
	<body>
		<header>
			<a href="http://localhost:8080/admin" class="links"><img class="logo" src="http://localhost:8080/images/logo.png" alt="Vaalikone logo"></a>
			<a href="/admin/kysymykset" class="links">Lista kysymyksistä</a>
			<a href="/admin/ehdokkaat" class="links">Lista ehdokkaista</a>
			<a href="/Logout" class="logout">Kirjaudu ulos</a>
		</header>
		<div class="content">
			<h2>Kaikki ehdokkaat</h2>
			<a href="/admin/lisaaEhdokas" style="text-decoration: none; color: #171616;">Lisää ehdokas &#62;</a>
			<ul>
				<c:forEach var="candidate" items="${requestScope.candidatesList}" >
					<li>${candidate.id}: ${candidate.sukunimi} ${candidate.etunimi} ${candidate.ika} ${candidate.puolue} ${candidate.kotipaikkakunta}  <a href='/admin/poistaEhdokas?id=${candidate.id}' onclick='return confirm("Haluatko varmasti poistaa ehdokkaan: ${candidate.sukunimi} ${candidate.etunimi}?");'><button class="button" style="font-size: 20px;">Poista</button></a> <a href='/admin/muokkaaEhdokas?id=${candidate.id}'><button class="button" style="font-size: 20px;">Muokkaa</button></a> <a href='/admin/ehdokkaankysymykset?candidate=${candidate.id}'><button class="button" style="font-size: 20px;">Muokkaa vastauksia</button></a></li><br>
				</c:forEach>
			</ul>
		</div>
	</body>
</html>