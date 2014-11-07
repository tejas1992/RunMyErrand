<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>RunMyErrand:${user.firstName}</title>
	<link rel="stylesheet" type="text/css" href="bootstrap/css/bootstrap.css">
	<link rel="stylesheet" href="bootstrap/css/datepicker.css">
	<link rel="stylesheet" href="bootstrap/css/mystyle.css">
</head>
<body>
<jsp:include page="base.jsp"/>
<div class = "col-md-9">
    <div class = "well">
	    <div class = "page-header">
  			<h1>${roomyName}'s Tasks:</h1>
		</div>
    <table class="table table-bordered table-striped">
    	<thead>
		<tr>
			<td>Task Description</td>
			<td>Task Points</td>
			<td>Start Date</td>
			<td>End Date</td>
			<td>Status</td>
		</tr>
		</thead>
		<tbody>
		<c:forEach var="roomytask" items= "${roomytask}">
  	   <tr>
    	 <td> ${roomytask.taskDescription} </td> 
    	 <td> ${roomytask.points}</td> 
      	 <td> ${roomytask.start_date}</td>
      	 <td> ${roomytask.end_date}</td>
       	 <c:choose>
       		<c:when test="${roomytask.completed == 0 }">
       			<td><h4><span class="label label-danger">Pending</span></h4></td>
       		</c:when>
       		<c:otherwise>
       			<td><h4><span class="label label-success">Completed</span></h4></td>
       		</c:otherwise>
      	 </c:choose>           
    	</tr>  
		 </c:forEach>
		 </tbody>
	</table>
    </div>
 </div>
 </div>
 </div>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<script src="bootstrap/js/bootstrap-datepicker.js"></script>
<script src="bootstrap/js/bootstrap.min.js"></script>
<script src="bootstrap/js/myscript.js"></script>
</body>
</html>