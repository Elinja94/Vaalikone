<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    
<%@ page import="java.util.ArrayList" %>   
<%@ page import="data.Question" %>   

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" lang="fi">
	<head>
		<meta http-equiv="content-type" content="application/xhtml+xml" charset="UTF-8">
	    <link rel="stylesheet" href="style.css">
		<link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Marck+Script&display=swap">
		<link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Kanit:wght@300&family=Marck+Script&display=swap">
		<link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=ABeeZee&family=Kanit:wght@300&family=Marck+Script&display=swap">
		<link rel="icon" type="image/x-icon" href="/images/favicon.png">
		<title>Vaalikone Admin</title>
	</head>
	<body>
		<header>
			<a href="http://localhost:8080/Admin" class="links"><img class="logo" src="/images/logo.png" alt="Vaalikone logo"></a>
			<a href="/kysymykset" class="links">Lista kysymyksistä</a>
			<a href="/ehdokkaat" class="links">Lista ehdokkaista</a>
			<a href="/Logout" class="logout">Kirjaudu ulos</a>
		</header>
		<div class="content">
		<h1>Vaalikone - Admin</h1>
		<p>Valitse mitä haluat tehdä ylhäältä löytyvästä valikosta.</p>
		</div>
	</body>
</html>