<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
   <head>
	<meta charset=”utf-8”> 
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>Sign in page</title>
	<link rel="stylesheet" type="text/css" href="bootstrap/css/bootstrap.css">
    <link rel="stylesheet" href="bootstrap/css/datepicker.css">
    <link rel="stylesheet" href="bootstrap/css/mystyle.css">		
   </head>
<body>
	<c:url value="/j_spring_security_logout" var="logoutUrl" />  
  <div class="container">
    <div class = "row">
      <div class = "col-md-11">
         <h1>Dashboard of ${user.firstName}</h1>  
      </div>
      <div class = "col-md-1">
          <h3><a href = "<c:url value="j_spring_security_logout" />">Logout</a></h3>
      </div>
    </div>  
  <div class="panel-group" id="accordion">
    <div class="panel panel-default">
      <div class="panel-heading">
        <h4 class="panel-title"> <a data-toggle="collapse" data-parent="#accordion" 
                 href="#collapseOne"> Your Roomates are: </a> </h4>
      </div>
      <div id="collapseOne" class="panel-collapse collapse in">
        <div class="panel-body"> 
          <ul>
            <c:forEach var="roomy" items= "${roomies}">
              <li> ${roomy} </li>              
            </c:forEach>
          </ul>
        </div>
      </div>
    </div>
    <div class="panel panel-success">
      <div class="panel-heading">
        <h4 class="panel-title"> <a data-toggle="collapse" data-parent="#accordion" 
                 href="#collapseTwo">The Lists of Tasks are:</a> </h4>
      </div>
      <div id="collapseTwo" class="panel-collapse collapse">
        <div class="panel-body"> 
          <table style = "width:200px; border:1px" class = "table table-striped table-responsive">
            <tr>
              <td> Task Description</td> 
              <td> Tasks points</td>            
            </tr>              
            <c:forEach var="tasks" items= "${tasks}">
            <tr>
              <td> ${tasks.taskDescription} </td> 
              <td> ${tasks.points}</td>            
            </tr>  
            </c:forEach>
          </table>            
        </div>
      </div>
    </div>
    <div class="panel panel-info">
      <div class="panel-heading">
        <h4 class="panel-title"> <a data-toggle="collapse" data-parent="#accordion" href="#collapseThree">Current Tasks assigned to you:</a></h4>
      </div>
      <div id="collapseThree" class="panel-collapse collapse">
        <div class="panel-body">
            <ul>
              <c:forEach var="mytasks" items= "${mytasks}">
                <li> ${mytasks.taskDescription} </li>              
              </c:forEach>
            </ul>
        </div>
      </div>
    </div>
    <div class="panel panel-danger">
      <div class="panel-heading">
        <h4 class="panel-title"> <a data-toggle="collapse" data-parent="#accordion" href="#collapsefour"> Assign Tasks </a> </h4>
      </div>
    <div id="collapsefour" class="panel-collapse collapse">
      <div class="panel-body"> 
        <form action="/RunMyErrand/Assigntask.do" method="post">
           <select name="task">
              <c:forEach var="tasksleft" items= "${unassigned}">
                <option value="${tasksleft.taskDescription}">${tasksleft.taskDescription}</option>      
              </c:forEach>
          </select>
          <select name="assigned">
            <option value="${user.firstName}">${user.firstName}</option>
            <c:forEach var="roomy" items= "${roomies}">
              <option value="${roomy}">${roomy}</option>      
            </c:forEach>
          </select>
          <input type ="hidden" name="email" value="${user.email}" /> 
          <button type="submit">AssignTasks</button>
        </form>
      </div>
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
