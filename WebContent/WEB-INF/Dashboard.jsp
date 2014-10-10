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
<tr>
<c:forEach var="tasks" items= "${tasks}">
	<td> ${tasks.taskDescription} </td> 
	<td> ${tasks.points}</td>            
</c:forEach></tr></p>
<p>
Your tasks are:
<ul>
<c:forEach var="mytasks" items= "${mytasks}">
	<li> ${mytasks.taskDescription} </li>              
</c:forEach></ul></p>
<form action="/RunMyErrand/Assigntask.do" method="post">
<select name="task">
<c:forEach var="tasksleft" items= "${unassigned}">
	<option value="${tasksleft.taskDescription}">${tasksleft.taskDescription}</option>      
</c:forEach></ul></p>
</select>
<select name="assigned">
	<option value="${user.firstName}">${user.firstName}</option>
<c:forEach var="roomy" items= "${roomies}">
	<option value="${roomy}">${roomy}</option>      
</c:forEach></ul></p>
</select>
<input type ="hidden" name="email" value="${user.email}" /> 
<button type="submit">AssignTasks</button>
</form>

</body>
</html>