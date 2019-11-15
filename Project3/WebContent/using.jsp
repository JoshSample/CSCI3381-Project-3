<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Patient Manager</title>
</head>
<body>
<form action="http://localhost:8080/Project3/MainServlet" method="get"> 
	<input type="submit" value="Add Patient" name="addPat"><br><br>
	<input type="submit" value="View Patient" name="viewPat">
	<% String patients=(String)request.getAttribute("selectionList"); %> 
	ID:<%=patients%><br><br><br>
 	<input type="submit" value="Logout" name="doLogout">  
</form> 
</body>
</html>