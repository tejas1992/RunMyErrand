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
  <link rel="stylesheet" type="text/css" href="css/bootstrap.css">
    <link rel="stylesheet" href="css/datepicker.css">
    <link rel="stylesheet" href="css/mystyle.css">    
   </head>
<body>
      <nav class="navbar navbar-inverse navbar-default" role="navigation">
        <div class="container-fluid">
          <div class="container">
          <div class="navbar-header">
            <a class="navbar-brand" href="<c:url value="/dashboard" />">RunMyErrand</a>
          </div>
          <div id="navbar" class="navbar-collapse collapse">
            <ul class="nav navbar-nav navbar-right">
              <li class="dropdown">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown">${user.firstName} <span class="caret"></span></a>
                <ul class="dropdown-menu" role="menu">
                  <li><a href="<c:url value="j_spring_security_logout" />"><span class="glyphicon glyphicon-log-out"></span> Logout</a></li>
                  <li><a href="#"><span class="glyphicon glyphicon-cog"></span> Settings</a></li>
                </ul>
              </li>
            </ul>
          </div><!--/.nav-collapse -->
        </div><!--/.container-fluid -->
      </nav>
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
                                    <input type="text" class="form-control" id="taskDescription" placeholder="Enter Task Description" name="taskDescription" required = "required">
                                    <p class="help-block">
                                    </p>
                                  </div>
                                </div>
                             
                                <div class="form-group">
                                  <label for="points">
                                    Points:
                                  </label>
                                  <div>
                                    <input type="text" class="form-control" id="points" placeholder="Enter Task points" name="points" required = "required">
                                    <p class="help-block">
                                    </p>
                                  </div>
                                </div>
                             
                                <div class="form-group">
                                  <label for="startDate">
                                    Start Date:
                                  </label>
                                  <div>
                                    <input  type="text" class="form-control" placeholder="Click to select Start Date"   id="startDate" name="start_date" required = "required">
                                    <p class="help-block">
                                    </p>
                                  </div>
                                </div>

                                <div class="form-group">
                                  <label for="endDate">
                                    End Date:
                                  </label>
                                  <div>
                                    <input  type="text" class="form-control" placeholder="Click to select date of birth"   id="endDate" name="end_date" required = "required">
                                    <p class="help-block">
                                    </p>
                                  </div>
                                </div>
                                
                               <div class="form-group">
                                  <label for="recurrence">
                                    Recurrence:
                                  </label>
                                  <div>
                                  	<select class="form-control" name = "recurrence">
                                  		<option value = "no">No</option>
                                  		<option value = "weekly">Weekly</option>
                                  		<option value = "monthly">Monthly</option>
                                  	</select>
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
      <div class="container">
        <div class="row">
          <div class = "col-md-3">
               <div class="well sidebar-nav">
                <h3>Welcome ${user.firstName}</h3>
                <h3>Your Points:${user.score}</h3>
                <h4>Pending Score:${user.pendingscore}</h4>
               </div>
                <div class="well sidebar-nav">
                <ul class="nav nav-list">
                  
                  <li><a href="<c:url value="/dashboard" />"><span class="glyphicon glyphicon-list"></span> Dashboard</a></li>
                  <li><a data-toggle="modal" data-target="#myModal2"><span class="glyphicon glyphicon-plus"></span> Add task</a></li>
                  <li><a href="#"><span class="glyphicon glyphicon-tasks"></span> All tasks</a></li>
                  <li><a href="#"><span class="glyphicon glyphicon-ok"></span> Completed Tasks</a></li>
                  <li><a href="<c:url value="/unassignedtask" />"><span class="glyphicon glyphicon-star"></span> Assign Tasks</a></li>
               </ul>
               </div>
                <div class="well sidebar-nav">
                <h3><span class="glyphicon glyphicon-user"></span>  My Roomates</li></h3>
                  <ul>
                    <c:forEach var="roomy" items= "${roomies}">
                  <li><a href="/RunMyErrand/roomyinfo?email=${roomy.email}&&name=${roomy.firstName}" > ${roomy.firstName}  ${roomy.lastName}</a> </li>              
            </c:forEach>
          </ul>
               </div>
          </div>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
        <script src="js/bootstrap-datepicker.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/myscript.js"></script>  
  </body>
</html>
