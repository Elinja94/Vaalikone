<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    
<%@ page import="java.util.ArrayList" %>   
<%@ page import="data.Candidate" %>   
<%@ page import="data.Question" %>   
<%@ page import="data.Vastaukset" %>  


<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" lang="fi">
	<head>
		<meta http-equiv="content-type" content="application/xhtml+xml" charset="UTF-8">
		<link rel="stylesheet" href="style.css">
		<link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Marck+Script&display=swap">
		<link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Kanit:wght@300&family=Marck+Script&display=swap">
		<link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=ABeeZee&family=Kanit:wght@300&family=Marck+Script&display=swap">
		<link rel="icon" type="image/x-icon" href="/images/favicon.png">
		
		<title>EhdokasInfo</title>
	</head>
	<body>
		<header>
			<a href="http://localhost:8080/Admin" class="links"><img class="logo" src="/images/logo.png" alt="Vaalikone logo"></a>
			<a href="/kysymykset" class="links">Lista kysymyksistä</a>
			<a href="/ehdokkaat" class="links">Lista ehdokkaista</a>
			<a href="/Logout" class="logout">Kirjaudu ulos</a>
		</header>
		
		<div class="content">
		
		<input type="hidden" name="id" value="${requestScope.candidate.id}" readonly><br> 
		
			<h2>Ehdokkaan tiedot:</h2>
			
			<h3>${candidate.id} </h3>
			Sukunimi:<br> <input name="sukunimi" value="${requestScope.candidate.sukunimi}"><br>
			<h3>Ehdokkaan nimi: value =  ${candidate.etunimi} </h3> <input> value ="${requestScope.candidate.sukunimi}" 
			<h3>Puolue: ${candidate.puolue} </h3> 
			<h3>Kotipaikkakunta: ${candidate.kotipaikkakunta}</h3>
			<h3>Ikä: ${candidate.ika}</h3>
			<h3>Miksi eduskuntaan: ${candidate.miksi_eduskuntaan}</h3>
			<h3>Mitä asioita haluat edistää: ${candidate.mita_asioita_haluat_edistaa}</h3>
			<h3>Ammatti: ${candidate.ammatti}</h3>
			

	<h2>Ehdokkaan vastaukset:</h2>
	
				<ul>
				<c:forEach var="question" items="${requestScope.questionsList}" >
				
				
					<li>${question.id}: ${question.question} ${answer.answer} </li><br>
				</c:forEach>
			</ul>

		  
		</div>
	</body>
	</html>