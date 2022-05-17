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
	    <title>Vaalikone - Vertailu</title>
	</head>

  <body>
    <%
    HashMap<Integer, String> userAnswers=(HashMap<Integer, String>)request.getAttribute("userAnswers");
    HashMap<Integer, Integer> sameAnswers=(HashMap<Integer, Integer>)request.getAttribute("sameAnswers");
	%>
	<header>
	  	<a href="http://localhost:8080/" class="links"><img class="logo" src="http://localhost:8080/images/logo.png" alt="Vaalikone logo"></a>
		<a href="/vastaakysymyksiin" class="links">Vastaa kysymyksiin</a>
		<a href="/candidates" class="links">Ehdokkaat</a>
		<a href="http://localhost:8080/admin" class="logout">Kirjaudu sisään</a>
	</header>
    <div class="content"><br>
    <a href="/" style="text-decoration: none; color: #171616;">&#60; Palaa etusivulle</a>
    <h2>Ehdokkaat jotka vastasivat sinun vastauksia parhaiten:</h2>
        <%
      for (Integer i : sameAnswers.keySet()) {
        String link = "/candidateInfo?id=" + i;
        out.println("<p>" + "<b><a href=" + link + " style='text-decoration: none; color: #171616;'>" + "Kandidaatti " + i +  "<a/>:</b> " + sameAnswers.get(i) + "</p>");
      }
      %>
      <br>
      <a href="/vastaakysymyksiin" style="text-decoration: none; color: #171616;">Tee kysely uudestaan</a>
     </div>
  </body>
</html>