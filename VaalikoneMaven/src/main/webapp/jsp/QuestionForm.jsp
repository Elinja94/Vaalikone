<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
 
<%@ page import="java.util.List" %>   
<%@ page import="data.Kysymykset" %>   

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" lang="fi">
  	<head>
		<meta http-equiv="content-type" content="application/xhtml+xml" charset="UTF-8">
		<link rel="stylesheet" href="style.css">
		<link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Marck+Script&display=swap">
		<link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Kanit:wght@300&family=Marck+Script&display=swap">
		<link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=ABeeZee&family=Kanit:wght@300&family=Marck+Script&display=swap">
		<link rel="icon" type="image/x-icon" href="/images/favicon.png">
	    <title>Vaalikone - Kysymykset</title>
	</head>

  <body>
    <%
    List<Kysymykset> questionlist=(List<Kysymykset>)request.getAttribute("question");
	%>
	<header>
	  	<a href="http://localhost:8080/" class="links"><img class="logo" src="http://localhost:8080/images/logo.png" alt="Vaalikone logo"></a>
		<a href="/vastaakysymyksiin" class="links">Vastaa kysymyksiin</a>
		<a href="/candidates" class="links">Ehdokkaat</a>
		<a href="http://localhost:8080/admin" class="logout">Kirjaudu sisään</a>
	</header>
	<div class="content">
		<form action=/vastaakysymyksiin method="POST">
			<c:forEach var="question" items="${requestScope.questionlist }">
				<div class="questionBox">
					<h3 class="questionHead"><c:out value = "${question.getId()}"/>. <c:out value = "${question.getQuestion()}"/></h3>
					<label class="radioAnswer" style="margin-left: 10%; color: #de1f8b; -webkit-text-stroke: 0.5px #de1f8b;"><input type="radio" name=<c:out value = "${question.getId()}"/> value="1">Vahvasti eri mieltä</label>
					<label class="radioAnswer" style="color: #de31b3; -webkit-text-stroke: 0.5px #de31b3;"><input type="radio" name=<c:out value = "${question.getId()}"/> value="2">Eri mieltä</label>
					<label class="radioAnswer" style="color: #ab2dc2; -webkit-text-stroke: 0.5px #ab2dc2;"><input type="radio" name=<c:out value = "${question.getId()}"/> value="3">Neutraali</label>
					<label class="radioAnswer" style="color: #7811a8; -webkit-text-stroke: 0.5px #7811a8;"><input type="radio" name=<c:out value = "${question.getId()}"/> value="4">Samaa mieltä</label>
					<label class="radioAnswer" style="color: #5d1a9c; -webkit-text-stroke: 0.5px #5d1a9c;"><input type="radio" name=<c:out value = "${question.getId()}"/> value="5">Vahvasti samaa mieltä</label>
		      	</div>
		      </c:forEach>
	        <div>
	        <br>
	        <div style="display: grid;">
          		<button type="submit" class="button" style="margin:auto; height: 50px; font-size: 20px;">Lähetä vastaukset</button>
          	</div>
	      	</div>	
	    </form>
	   </div>
  </body>
</html>