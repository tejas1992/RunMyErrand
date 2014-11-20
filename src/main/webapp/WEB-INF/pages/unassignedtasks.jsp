<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
   <head>
	<meta charset=”utf-8”> 
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
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
          <h1>Unassigned Tasks: </h1>
        </div>  
        <c:choose>
           <c:when test="${fn:length(unassigned) > 0}">
              <table class="table table-bordered table-striped">
                    <thead>
                      <tr>
                          <th>Task Description</th>
                          <th>Task points</th>    
                          <th>Start Date</th>
                          <th>Due Date</th>    
                          <th>Roommate</th>
                          <th>Assign to </th>
                      </tr>
                    </thead>
                    <tbody>
                     <c:forEach var="tasksleft" items= "${unassigned}">
                     <form action="/RunMyErrand/Assigntask.do" method="post">
                       <tr>
                          <td>${tasksleft.taskDescription}</td>
                          <td>${tasksleft.points}</td>
                          <td>${tasksleft.start_date}</td>
                          <td>${tasksleft.end_date}</td>
                         
                          <td><select name="assigned" class="form-control">
                             <option value="${user.email}">${user.firstName}</option>
                            <c:forEach var="roomy" items= "${roomies}">
                              <option value="${roomy.email}">${roomy.firstName}</option>      
                            </c:forEach>
                            </select></td>
                            <input type="hidden" value="${tasksleft.taskid}" name="taskid">
                            <input type="hidden" value="${fn:length(unassigned)}" name="length">
                          <td>
                          <button type="submit" class="btn btn-primary">AssignTasks</button>
                          </td>
                        </tr>
                  
                      </form>
                      </c:forEach>
                    </tbody>
                </table>
               </c:when>
               <c:otherwise>
                 <h3> No Tasks to display </h3>
               </c:otherwise>
      </c:choose>      
    </div>
</div>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
        <script src="bootstrap/js/bootstrap-datepicker.js"></script>
        <script src="bootstrap/js/bootstrap.min.js"></script>
        <script src="bootstrap/js/myscript.js"></script>	
        </body>
</html>
