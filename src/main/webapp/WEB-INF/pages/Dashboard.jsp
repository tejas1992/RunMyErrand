<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
  <div class="container">
    <div class = "row">
      <div class = "col-md-11">
         <h1>Dashboard of ${user.firstName}</h1>  
      </div>
      <div class = "col-md-1">
               <a href = "<c:url value="/dashboard" />">Dashboard</a>
          <h3><a href = "<c:url value="j_spring_security_logout" />">Logout</a></h3>
      </div>
    </div>  
            <button class="btn btn-primary btn-sm" data-toggle="modal" data-target="#myModal2">
              Add a new task
            </button>
                
                 <div class="modal fade" id="myModal2" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                  <div class="modal-dialog">
                    <div class="modal-content">
                      <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                        <h4 class="modal-title" id="myModalLabel">Add a new task</h4>
                      </div>
                      <div class="modal-body">
                     <form role="form" action="/RunMyErrand/addtask.do" method="post">
                                <div class="form-group">
                                  <label for="taskDescription">
                                    Description:
                                  </label>
                                  <div>
                                    <input type="text" class="form-control" id="taskDescription" placeholder="Enter Task Description" name="taskDescription">
                                    <p class="help-block">
                                    </p>
                                  </div>
                                </div>
                             
                                <div class="form-group">
                                  <label for="points">
                                    Points:
                                  </label>
                                  <div>
                                    <input type="text" class="form-control" id="points" placeholder="Enter Task points" name="points">
                                    <p class="help-block">
                                    </p>
                                  </div>
                                </div>
                             
                                <div class="form-group">
                                  <label for="startDate">
                                    Start Date:
                                  </label>
                                  <div>
                                    <input  type="text" class="form-control" placeholder="Click to select Start Date"   id="startDate" name="start_date">
                                    <p class="help-block">
                                    </p>
                                  </div>
                                </div>

                                <div class="form-group">
                                  <label for="endDate">
                                    End Date:
                                  </label>
                                  <div>
                                    <input  type="text" class="form-control" placeholder="Click to select date of birth"   id="endDate" name="end_date">
                                    <p class="help-block">
                                    </p>
                                  </div>
                                </div>
                                <input type ="hidden" name="email" value="${user.email}" /> 
                      <div class="modal-footer">
                                <button type="submit" class="btn btn-success btn-sm">Add Task</button>
                      </div>                                
                              </form>
                        </div>
                    </div>
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
              <li><a href ="<c:url value="/roomyinfo/${roomy.email}"/>" > ${roomy.firstName}	${roomy.lastName} ${roomy.email}</a> </li>              
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
        <c:choose>
         <c:when test="${fn:length(mytasks) > 0 }">
        	<ul>
              <c:forEach var="mytasks" items= "${mytasks}">
                <li> ${mytasks.taskDescription} </li>              
              </c:forEach>
            </ul>
          </c:when>
          <c:otherwise>
          	No Tasks for You!!
          </c:otherwise>
        </c:choose>
        </div>
      </div>
    </div>
    
    
    <div class="panel panel-danger">
      <div class="panel-heading">
        <h4 class="panel-title"> <a data-toggle="collapse" data-parent="#accordion" href="#collapsefour"> Assign Tasks </a> </h4>
      </div>
    <div id="collapsefour" class="panel-collapse collapse">
      <div class="panel-body"> 
      
          <input type ="hidden" name="email" value="${user.email}" /> 
         <a href="<c:url value="/unassignedtask" />">Unassigned Tasks</a>
      </div>
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
