<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    
<%@ page import="java.util.ArrayList" %>   
<%@ page import="data.Candidate" %>   

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" lang="fi">
	<head>
		<meta http-equiv="content-type" content="application/xhtml+xml" charset="UTF-8">
		<title>Vaalikone Admin - Lisää ehdokas</title>
	</head>
	<body>
	
	<form action="/lisaaEhdokasVahvistus" method="GET">
    	Sukunimi:<br> <input type="text" name="sukunimi"><br>
    	Etunimi:<br> <input type="text" name="etunimi"><br>
    	Puolue:<br> <input type="text" name="puolue"><br>
    	Kotipaikkakunta:<br> <input type="text" name="kotipaikkakunta"><br>
    	Ika:<br> <input type="number" name="ika"><br>
    	Miksi eduskuntaan:<br> <textarea name="miksi_eduskuntaan" rows="6" cols="50"></textarea><br>
    	Mitä asioita haluat edistää:<br> <textarea name="mita_asioita_haluat_edistaa" rows="6" cols="50"></textarea><br>
    	Ammatti:<br> <input type="text" name="ammatti"><br>
    	
    	<input type="submit" value="Lisää ehdokas">
    </form>
		
	</body>
	</html>