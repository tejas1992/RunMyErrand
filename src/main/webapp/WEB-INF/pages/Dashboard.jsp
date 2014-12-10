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
          <h1>My Tasks:</h1>
        </div>
             <c:choose>
               <c:when test="${fn:length(mytasks) > 0}">
                   <table class="table table-bordered table-striped">
                    <thead>
                      <tr>
                          <th>Task Description</th>
                          <th>Task points</th>    
                          <th>Start Date</th>
                          <th>Due Date</th>    
                          <th>Recurring</th>                          
                          <th>Status</th>
                          <th>Update Status</th>
                          <th>Action</th>
                      </tr>
                    </thead>
                    <tbody>               
                  <c:forEach var="mytasks" items= "${mytasks}">
                     <form action="/RunMyErrand/edittask" method="post">
                       <tr>
                        <td>${mytasks.taskDescription}</td>
                        <td>${mytasks.points}</td>
                        <td>${mytasks.start_date}</td>
                        <td>${mytasks.end_date}</td>
                        <c:choose>
                            <c:when test = "${mytasks.recurrence eq 'monthly'}">
                              <td>Monthly</td>
                            </c:when>
                            <c:when test = "${mytasks.recurrence eq 'weekly'}">
                              <td>Weekly</td>
                            </c:when>
                            <c:otherwise>
                              <td>No</td>
                            </c:otherwise>
                        </c:choose>
                        <c:choose>
                            <c:when test = "${mytasks.completed == 1}">
                              <td><span class="label label-success">DONE</span></td>
                            </c:when>
                            <c:otherwise>
                              <td><span class="label label-default ">TODO</span></td>
                            </c:otherwise>
                        </c:choose>
                       <td>
                          <select class="form-control" name = "completed">
                            <option value = "TODO">TODO</option>
                            <option value = "DONE">DONE</option>
                          </select>
                        </td>
                        <td>
                        <button type="submit" class="btn btn-primary btn-sm">Edit</button>
                        </td>
                        </tr>
                        <input type="hidden" value="${mytasks.taskid}" name="taskid">
                    </form>
                  </c:forEach>
                </tbody>
                </table>
               </c:when>
                 <c:otherwise>
                      No Tasks to display
                 </c:otherwise>
            </c:choose>
          </tbody>
        </table>

</div>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
        <script src="bootstrap/js/bootstrap-datepicker.js"></script>
        <script src="bootstrap/js/bootstrap.min.js"></script>
        <script src="bootstrap/js/myscript.js"></script>	
        </body>
</html>
