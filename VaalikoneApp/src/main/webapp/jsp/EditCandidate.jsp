<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    
<%@ page import="java.util.ArrayList" %>   
<%@ page import="data.Candidate" %>   

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" lang="fi">
	<head>
		<meta http-equiv="content-type" content="application/xhtml+xml" charset="UTF-8">
		<title>Vaalikone Admin - Päivitä ehdokas</title>
	</head>
	<body>
	
	<form action="/muokkaaEhdokasVahvistus" method="GET">
	<input type="hidden" name="id" value="${requestScope.candidate.id}" readonly><br> 
    	Sukunimi:<br> <input type="text" name="sukunimi" value="${requestScope.candidate.sukunimi}"><br>
    	Etunimi:<br> <input type="text" name="etunimi" value="${requestScope.candidate.etunimi}"><br>
    	Puolue:<br> <input type="text" name="puolue" value="${requestScope.candidate.puolue}"><br>
    	Kotipaikkakunta:<br> <input type="text" name="kotipaikkakunta" value="${requestScope.candidate.kotipaikkakunta}"><br>
    	Ika:<br> <input type="number" name="ika" value="${requestScope.candidate.ika}"><br>
    	Miksi eduskuntaan:<br> <textarea name="miksi_eduskuntaan" rows="6" cols="50" >${requestScope.candidate.miksi_eduskuntaan}</textarea><br>
    	Mitä asioita haluat edistää:<br> <textarea name="mita_asioita_haluat_edistaa" rows="6" cols="50">${requestScope.candidate.mita_asioita_haluat_edistaa}</textarea><br>
    	Ammatti:<br> <input type="text" name="ammatti" value="${requestScope.candidate.ammatti}"><br>
    	
    	<input type="submit" value="Päivitä">
    </form>
		
	</body>
	</html>