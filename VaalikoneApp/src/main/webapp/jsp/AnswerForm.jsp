<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 
<%@ page import="java.util.ArrayList" %>   
<%@ page import="data.Question" %>   
<%@ page import="data.Answer" %>   

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
	Question question=(Question)request.getAttribute("question");
    String method=(String)request.getAttribute("method");
    
    int nextQuestion = question.getId() + 1;
    if (request.getAttribute("nextQuestion") != null) {
    	nextQuestion = ((Question)request.getAttribute("nextQuestion")).getId();
    }
	%>
		<header>
			<a href="http://localhost:8080/Admin" class="links"><img class="logo" src="/images/logo.png" alt="Vaalikone logo"></a>
			<a href="/kysymykset" class="links">Lista kysymyksistä</a>
			<a href="/ehdokkaat" class="links">Lista ehdokkaista</a>
			<a href="/Logout" class="logout">Kirjaudu ulos</a>
		</header>
		<div class="content">
		    <form action=/ehdokkaankysymykset?question=<%= nextQuestion %> method="POST">
				<h2><%= question.getId() %>. <%= question.getQuestion() %></h2>
				<input type="hidden" name="questionId" value=<%= question.getId() %>></input>
				<input type="hidden" name="method" value=<%= method %>></input>
		      	<div>
		      	<input type="radio" name="answer" value="1">Vahvasti eri mieltä</input>
	          	<input type="radio" name="answer" value="2">Eri mieltä</input>
	          	<input type="radio" name="answer" value="3">Neutraali</input>
	          	<input type="radio" name="answer" value="4">Samaa mieltä</input>
	          	<input type="radio" name="answer" value="5">Vahvasti samaa mieltä</input>
		      	</div>
		        <div>
	          	<button type="submit" class="button">Seuraava</button>
		      	</div>
		    </form>
		   </div>
  </body>
</html>