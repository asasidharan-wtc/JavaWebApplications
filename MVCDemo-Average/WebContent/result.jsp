<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Result</title>
</head>

<body>
<% int result = (Integer)(request.getAttribute("result"));
   out.print("<h1> SAMPLE PROGRAM TO DEMONSTRATE THE MVC DESIGN PATTERN</h1></b>");
   out.print("The average of the two given numbers = " + result);		
%>
</body>
</html>