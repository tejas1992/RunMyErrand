<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title> </title>
</head>
<body>
Dashboard of ${user.firstName}
<p>
Your Roomates are:
<ul>
<c:forEach var="roomy" items= "${roomies}">
	<li> ${roomy} </li>              
</c:forEach></ul></p>
<p>
The Lists of Tasks are:
<ul>
<c:forEach var="tasks" items= "${tasks}">
	<li> ${tasks.taskDescription} </li>              
</c:forEach></ul></p>


</body>
</html>