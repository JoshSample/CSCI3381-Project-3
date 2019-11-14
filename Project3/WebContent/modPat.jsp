<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>View Patient</title>
</head>
<body>
<%@page import="java.util.ArrayList"%>
<form action="http://localhost:8080/Project3/MainServlet" method="get"> 
  <% String id=(String)request.getAttribute("id"); %>
  <% String name=(String)request.getAttribute("name"); %> 
  <% String aces=(String)request.getAttribute("aces"); %> 
  <% ArrayList<String> risk=(ArrayList<String>)request.getAttribute("risk"); %>
    ID:<%=id %><br>
 	Name:<%=name%><br>
 	ACEs:<br><%=aces%>
 	Risk Factors:<%=risk%><br>
 	<input type="submit" value="Add ACES" onclick="alert('ACEs added, returning to Patient Manager.')" name="aceButton">  
 	<input type="submit" value="Back" name="back">
 	</form> 
</body>
</html>