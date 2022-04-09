<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    
<%@ page import="java.util.ArrayList" %>   
<%@ page import="data.Question" %>   

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" lang="fi">
	<head>
		<meta http-equiv="content-type" content="application/xhtml+xml" charset="UTF-8">
		<title>Vaalikone</title>

	</head>
	<body>
		<h2>Kaikki kysymykset</h2>
	<ol>
	<c:forEach var="question" items="${requestScope.questionsList}" >
		<li>${question.question}</li>
	</c:forEach>
	</ol>
	<a href="/lisaaKysymys">Lisää kysmys</a>
	</body>
</html>