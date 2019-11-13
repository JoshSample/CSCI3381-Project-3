<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>View Patient</title>
</head>
<body>
<form action="http://localhost:8080/Project3/MainServlet" method="get"> 
  <% String id=(String)request.getAttribute("id"); %>
  <% String name=(String)request.getAttribute("name"); %> 
  <% String aces=(String)request.getAttribute("aces"); %> 
  <% String[] risk=(String[])request.getAttribute("risk"); %>
    ID:<%=id %><br>
 	Name:<%=name%><br> 
 	ACES:<%=aces%><br>
 	Risk Factors:<%=risk%><br>
 	<input type="submit" value="Process" name="processButton">  
 	</form> 
</body>
</html>