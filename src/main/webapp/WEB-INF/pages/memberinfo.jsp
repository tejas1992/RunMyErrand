<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<table>
<tr>
	<td>Task Description</td>
	<td>Task Points</td>
	<td>Start Date</td>
	<td>End Date</td>
	<td>Status</td>
</tr>
 <c:forEach var="roomytask" items= "${roomytask}">
     <tr>
       <td> ${roomytask.taskDescription} </td> 
       <td> ${roomytask.points}</td> 
       <td> ${roomytask.start_date}</td>
       <td> ${roomytask.end_date}</td>
       <c:choose>
       	<c:when test="${roomytask.completed == 0 }">
       		<td>Pending</td>
       	</c:when>
       	<c:otherwise>
       		<td>Completed</td>
       	</c:otherwise>
       </c:choose>           
    </tr>  
 </c:forEach>
</table>
</body>
</html>