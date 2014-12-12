<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
   <head>
  <meta charset=”utf-8”> 
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Overdue tasks</title>
  <link rel="stylesheet" type="text/css" href="bootstrap/css/bootstrap.css">
    <link rel="stylesheet" href="bootstrap/css/datepicker.css">
    <link rel="stylesheet" href="bootstrap/css/mystyle.css">   
   </head>
<body>
      <jsp:include page="base.jsp"/>
<div class = "col-md-9">
    <div class = "well">
        <div class = "page-header">
            <h1>Overdue tasks of room:${user.room} </h1>
        </div>
    <table class="table table-bordered table-striped">
        <thead>
        <tr>
            <th>Task Description</th>
            <th>Task Points</th>
            <th>Start Date</th>
            <th>Due Date</th>
            
        
        </tr>
        </thead>
        <tbody>
        <c:forEach var="list_comp" items= "${list_comp}" varStatus="status">
       <tr>
         <td> ${list_comp.taskDescription} </td> 
         <td> ${list_comp.points}</td> 
         <td> ${list_comp.start_date}</td>
         <td> ${list_comp.end_date}</td>
         </tr>  
         </c:forEach>
         </tbody>
    </table>
    </div>
 </div>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<script src="bootstrap/js/bootstrap-datepicker.js"></script>
<script src="bootstrap/js/bootstrap.min.js"></script>
<script src="bootstrap/js/myscript.js"></script>
</body>
</html>
