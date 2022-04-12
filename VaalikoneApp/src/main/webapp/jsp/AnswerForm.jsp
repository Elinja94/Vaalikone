<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 
<%@ page import="java.util.ArrayList" %>   
<%@ page import="data.Question" %>   
<%@ page import="data.Answer" %>   

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" lang="fi">
  <head>
    <meta http-equiv="content-type" content="application/xhtml+xml; charset=UTF-8" />
    <title>Vaalikone kysymykset</title>
  </head>

  <body>
    <h1>Kysymykset</h1>
    <%
	Question question=(Question)request.getAttribute("question");
    String method=(String)request.getAttribute("method");
    
    int nextQuestion = question.getId() + 1;
    if (request.getAttribute("nextQuestion") != null) {
    	nextQuestion = ((Question)request.getAttribute("nextQuestion")).getId();
    }
	%>
	    <form action=/ehdokkaankysymykset?question=<%= nextQuestion %> method="POST">
			<h3><%= question.getId() %>. <%= question.getQuestion() %></h3>
			<input type="hidden" name="questionId" value=<%= question.getId() %>></input>
			<input type="hidden" name="method" value=<%= method %>></input>
	      	<div>
	      	<input type="radio" name="answer" value="1">Vahvasti eri mieltä</input>
          	<input type="radio" name="answer" value="2">Eri mieltä</input>
          	<input type="radio" name="answer" value="3">Neutraali</input>
          	<input type="radio" name="answer" value="4">Samaa mieltä</input>
          	<input type="radio" name="answer" value="5">Vahvasti samaa mieltä</input>
	      	</div>
	        <div>
          	<button type="submit">Seuraava</button>
	      	</div>
	    </form>
  </body>
</html>