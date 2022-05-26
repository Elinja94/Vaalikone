<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    
<%@ page import="java.util.ArrayList" %>   
<%@ page import="data.Question" %>   
<%@ page import="data.Kysymykset" %>   

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
		<title>Vaalikone Admin - Lisää kysymys</title>
		<script>
		function sendData(){
			var question=new Object();
			question.question=document.getElementById("question").value;
			
			var jsonQuestion=JSON.stringify(question);
			var xhttp = new XMLHttpRequest();
			
			xhttp.onreadystatechange = function() {
			  if (this.readyState == 4 && this.status == 200) {
			   var returned=JSON.parse(this.responseText);
			   location.href = '/admin/lisaaKysymysVahvistus?question=' + returned.question;
			  }
			};
			
			xhttp.open("POST","/vaalikone/questions/addquestion",true);
			xhttp.setRequestHeader("Content-type","application/json");
			xhttp.send(jsonQuestion);
		}
		</script>
	</head>
	<body>
		<header>
			<a href="http://localhost:8080/admin" class="links"><img class="logo" src="http://localhost:8080/images/logo.png" alt="Vaalikone logo"></a>
			<a href="/admin/kysymykset" class="links">Lista kysymyksistä</a>
			<a href="/admin/ehdokkaat" class="links">Lista ehdokkaista</a>
			<a href="/Logout" class="logout">Kirjaudu ulos</a>
		</header>
		<div class="content"><br>
			<a href="/admin/kysymykset" style="text-decoration: none; color: #171616;">&#60; Takaisin kysymyksiin</a>
			<h2>Kysymyksen tiedot:</h2>
			<form action="/admin/lisaaKysymysVahvistus" onsubmit="return sendData()">
		    	Kysymys:<br> <textarea id="question" name="question" rows="6" cols="50" required></textarea><br>
		    	<input type="submit" value="Lisää kysymys" class="button">
		    </form>
    	</div>
	</body>
</html>