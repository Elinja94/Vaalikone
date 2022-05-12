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
	    <title>Vaalikone kysymykset</title>
	</head>

  <body>
    <%
    List<Kysymykset> questionlist=(List<Kysymykset>)request.getAttribute("question");
	%>
		<div class="content">
			<h2>Kysymykset</h2>
			<form action=/questions method="POST">
				<c:forEach var="question" items="${requestScope.questionlist }">
					<h4><c:out value = "${question.getId()}"/>. <c:out value = "${question.getQuestion()}"/></h4>
					<input type="radio" name=<c:out value = "${question.getId()}"/> value="1">Vahvasti eri mieltä</input>
					<input type="radio" name=<c:out value = "${question.getId()}"/> value="2">Eri mieltä</input>
					<input type="radio" name=<c:out value = "${question.getId()}"/> value="3">Neutraali</input>
					<input type="radio" name=<c:out value = "${question.getId()}"/> value="4">Samaa mieltä</input>
					<input type="radio" name=<c:out value = "${question.getId()}"/> value="5">Vahvasti samaa mieltä</input>
			      </c:forEach>
		        <div>
	          	<button type="submit" class="button">Lähetä vastaukset</button>
		      	</div>	
		    </form>
		   </div>
  </body>
</html>