<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    
<%@ page import="java.util.ArrayList" %>   
<%@ page import="data.Candidate" %>   
<%@ page import="data.Question" %>   
<%@ page import="data.Vastaukset" %>
<%@ page import="java.util.List" %>   


<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" lang="fi">
	<head>
		   <meta http-equiv="content-type" content="application/xhtml+xml;" charset="UTF-8" />
		   <link rel="stylesheet" href="http://localhost:8080/style.css">
		   <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Marck+Script&display=swap">
		   <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Kanit:wght@300&family=Marck+Script&display=swap">
		   <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=ABeeZee&family=Kanit:wght@300&family=Marck+Script&display=swap">
		   <link rel="icon" type="image/x-icon" href="http://localhost:8080/images/favicon.png">
		   <title>Vaalikone - Ehdokkaan tiedot</title>
	</head>
	<body>
	 	<header>
	  	<a href="http://localhost:8080/" class="links"><img class="logo" src="http://localhost:8080/images/logo.png" alt="Vaalikone logo"></a>
		<a href="/vastaakysymyksiin" class="links">Vastaa kysymyksiin</a>
		<a href="/candidates" class="links">Ehdokkaat</a>
		<a href="http://localhost:8080/admin" class="logout">Kirjaudu sisään</a>
	</header>
		
	<div class="content">
		
	<input type="hidden" name="id" value="${requestScope.candidate.id}" readonly><br> 
		
		<h2>Ehdokkaan tiedot:</h2>
		
		<h3>${candidate.id}. </h3>
		<h3>Ehdokkaan nimi:</h3> <h4> ${candidate.etunimi} ${candidate.sukunimi} </h4>
		<h3>Puolue: </h3> <h4>${candidate.puolue} </h4>
		<h3>Kotipaikkakunta: </h3> <h4> ${candidate.kotipaikkakunta} </h4>
		<h3>Ikä: </h3> <h4>${candidate.ika} </h4>
		<h3>Miksi eduskuntaan: </h3> <h4>${candidate.miksi_eduskuntaan} </h4>
		<h3>Mitä asioita haluat edistää: </h3> <h4>${candidate.mita_asioita_haluat_edistaa} </h4>
		<h3>Ammatti: </h3> <h4>${candidate.ammatti} </h4>
			

	<h2>Ehdokkaan vastaukset:</h2>
				<%
				List<Question> questionsList=(List<Question>)request.getAttribute("questionsList");
				List<Vastaukset> candidateAnswers=(List<Vastaukset>)request.getAttribute("candidateAnswers");
				
				for (Question question : questionsList) {
					String candidateAnswer = null;
				    for (Vastaukset a : candidateAnswers) {
				        if (a.getQuestionId() == question.getId()) {
				            Integer answer = Integer.parseInt(a.getAnswer());
				            if (answer == 1) candidateAnswer = "Vahvasti eri mieltä";
				            if (answer == 2) candidateAnswer = "Eri mieltä";
				            if (answer == 3) candidateAnswer = "Neutraali";
				            if (answer == 4) candidateAnswer = "Samaa mieltä";
				            if (answer == 5) candidateAnswer = "Vahvasti samaa mieltä";
				        }
				    }
				%>
					<%=question.getId()%>: <%=question.getQuestion()%><br>
					<%=candidateAnswer%><br>
					<br>
				<%
				}
				%>	  
		</div>
	</body>
	</html>