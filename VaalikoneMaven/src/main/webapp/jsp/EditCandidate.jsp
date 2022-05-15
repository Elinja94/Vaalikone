<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    
<%@ page import="java.util.ArrayList" %>   
<%@ page import="data.Candidate" %>   

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
		<title>Vaalikone Admin - Päivitä ehdokas</title>
	</head>
	<body>
		<header>
			<a href="http://localhost:8080/admin" class="links"><img class="logo" src="http://localhost:8080/images/logo.png" alt="Vaalikone logo"></a>
			<a href="/admin/kysymykset" class="links">Lista kysymyksistä</a>
			<a href="/admin/ehdokkaat" class="links">Lista ehdokkaista</a>
			<a href="/Logout" class="logout">Kirjaudu ulos</a>
		</header>
		<div class="content">
			<h2>Ehdokkaan tiedot:</h2>
			<form action="/admin/muokkaaEhdokasVahvistus" method="GET">
			<input type="hidden" name="id" value="${requestScope.candidate.id}" readonly><br> 
			
		    	Sukunimi:<br> <input type="text" name="sukunimi" value="${requestScope.candidate.sukunimi}"><br>
		    	Etunimi:<br> <input type="text" name="etunimi" value="${requestScope.candidate.etunimi}"><br>
		    	Puolue:<br> <input type="text" name="puolue" value="${requestScope.candidate.puolue}"><br>
		    	Kotipaikkakunta:<br> <input type="text" name="kotipaikkakunta" value="${requestScope.candidate.kotipaikkakunta}"><br>
		    	Ika:<br> <input type="number" name="ika" value="${requestScope.candidate.ika}"><br>
		    	Miksi eduskuntaan:<br> <textarea name="miksi_eduskuntaan" rows="6" cols="50" >${requestScope.candidate.miksi_eduskuntaan}</textarea><br>
		    	Mitä asioita haluat edistää:<br> <textarea name="mita_asioita_haluat_edistaa" rows="6" cols="50">${requestScope.candidate.mita_asioita_haluat_edistaa}</textarea><br>
		    	Ammatti:<br> <input type="text" name="ammatti" value="${requestScope.candidate.ammatti}"><br>
		    	
		    	<input type="submit" value="Päivitä" class="button">
		    </form>
		</div>
	</body>
	</html>