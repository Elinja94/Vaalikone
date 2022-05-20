<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    
<%@ page import="java.util.ArrayList" %>   
<%@ page import="data.Ehdokkaat" %>   
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
		
	<div class="content"><br>
		<a href="/candidates" style="text-decoration: none; color: #171616;">&#60; Takaisin ehdokas listaan</a>
	<input type="hidden" name="id" value="${requestScope.ehdokas.getId()}" readonly>
		<h2>Ehdokkaan tiedot:</h2>
		<h3 style="-webkit-text-stroke: 0px;">N:o ${ehdokas.getId()}<br>${ehdokas.getEtunimi()} ${ehdokas.getSukunimi()} ${ehdokas.getIka()}</h3>
		<p style="font-size: 25px; -webkit-text-stroke: 0.5px;">${ehdokas.getPuolue()}</p>
		<p>${ehdokas.getKotipaikkakunta()}</p>
		<p><b>Ammatti:</b> ${ehdokas.getAmmatti()}</p>
		<h3 style="-webkit-text-stroke: 0px;">Miksi eduskuntaan:</h3>
		<p>${ehdokas.getMiksi_eduskuntaan()}</p>
		<h3 style="-webkit-text-stroke: 0px;">Mitä asioita haluat edistää: </h3>
		<p>${ehdokas.getMita_asioita_haluat_edistaa()}</p>

		<h2>Ehdokkaan vastaukset:</h2>
				<%
				List<Question> questionsList=(List<Question>)request.getAttribute("questionsList");
				List<Vastaukset> candidateAnswers=(List<Vastaukset>)request.getAttribute("candidateAnswers");
				
				for (Question question : questionsList) {
					String candidateAnswer = "Ehdokas ei vastannut kysymykseen";
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
					<p><b><%=question.getId()%>: <%=question.getQuestion()%></b><br>
					<%=candidateAnswer%></p>
				<%
				}
				%>	  
		</div>
	</body>
	</html>