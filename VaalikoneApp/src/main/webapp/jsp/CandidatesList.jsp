<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    
<%@ page import="java.util.ArrayList" %>   
<%@ page import="data.Candidate" %>   

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" lang="fi">
	<head>
		<meta http-equiv="content-type" content="application/xhtml+xml" charset="UTF-8">
		<title>Vaalikone</title>

	</head>
	<body>
		<h1>Kaikki ehdokkaat</h1>
	<c:forEach var="candidate" items="${requestScope.candidatesList}" >
		${candidate.id}: ${candidate.sukunimi} ${candidate.etunimi} ${candidate.puolue} ${candidate.kotipaikkakunta} ${candidate.ika} ${candidate.miksi_eduskuntaan} ${candidate.mita_asioita_haluat_edistaa} ${candidate.ammatti} <a href='/poistaEhdokas?id=${candidate.id}' onclick='return confirm("Haluatko varmasti poistaa kysymyksen: ${candidate.sukunimi} ${candidate.etunimi}?");'>Poista</a> <a href='/muokkaaEhdokas?id=${candidate.id}'>Muokkaa</a> <a href='/ehdokkaankysymykset?candidate=${candidate.id}'>Muokkaa vastauksia</a><br>
	</c:forEach>
	<a href="/lisaaEhdokas">Lisää ehdokas</a>
	</body>
</html>