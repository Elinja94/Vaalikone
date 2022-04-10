<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    
<%@ page import="java.util.ArrayList" %>   
<%@ page import="data.Question" %>   

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" lang="fi">
	<head>
		<meta http-equiv="content-type" content="application/xhtml+xml" charset="UTF-8">
		<title>Vaalikone Admin - Lisää kysymys</title>
	</head>
	<body>
	
	<form action="/lisaaKysymysVahvistus" method="GET">
    	Kysymys:<br> <textarea name="question" rows="6" cols="50"></textarea><br>
    	<input type="submit" value="Lisää kysymys">
    </form>
		
	</body>
	</html>