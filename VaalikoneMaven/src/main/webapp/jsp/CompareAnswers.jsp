<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
 
<%@ page import="java.util.List" %> 
<%@ page import="java.util.HashMap" %>   
<%@ page import="data.Kysymykset" %>   
<%@ page import="data.Vastaukset" %>   

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
    HashMap<Integer, String> userAnswers=(HashMap<Integer, String>)request.getAttribute("userAnswers");
    HashMap<Integer, Integer> sameAnswers=(HashMap<Integer, Integer>)request.getAttribute("sameAnswers");
	%>
		<div class="content">
			<h2>Tulokset</h2>
			    <%
			    out.println("<h3>Samojen vastauksien määrä</h3>");
				for (Integer i : sameAnswers.keySet()) {
					out.println("<p>" + "Kandidaatti " + i + ": " + sameAnswers.get(i) + "</p>");
				}
				%>
		   </div>
  </body>
</html>