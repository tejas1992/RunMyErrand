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
<nav class="navbar navbar-inverse navbar-default" role="navigation">
        <div class="container-fluid">
          <div class="container">
          <div class="navbar-header">
            <a class="navbar-brand" href="<c:url value="/dashboard" />">RunMyErrand</a>
          </div>
          <div id="navbar" class="navbar-collapse collapse">
            <ul class="nav navbar-nav navbar-right">
              <li class="dropdown">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown">Admin <span class="caret"></span></a>
                <ul class="dropdown-menu" role="menu">
                  <li><a href="<c:url value="j_spring_security_logout" />"><span class="glyphicon glyphicon-log-out"></span> Logout</a></li>
                  <li><a href="#"><span class="glyphicon glyphicon-cog"></span> Settings</a></li>
                </ul>
              </li>
            </ul>
          </div><!--/.nav-collapse -->
        </div><!--/.container-fluid -->
      </nav>
    <div class="container" id = "loginwidget">
    <div class="row">
        <div class="col-md-5 col-md-offset-3">
            <div class="panel panel-default">
                <div class="panel-heading"> <strong class="">Login</strong>
                </div>
                <div class="panel-body">
                        <h4>Current System Date: ${currentdate}</h4>
                        <h4>TimeBox Start Date :${timeboxstartdate}</h4>
                        <h4>Timebox End Date: ${timeboxenddate}</h4>                        
                        <form action="/RunMyErrand/fastforward" method ="post">
                            Enter a Date to see the future:<input type="text" name="date" id = "dob" />
                            <input type="submit" ></input>
                        </form>
                </div>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
        <script src="bootstrap/js/bootstrap-datepicker.js"></script>
        <script src="bootstrap/js/bootstrap.min.js"></script>
        <script src="bootstrap/js/myscript.js"></script>    
        </body>
</html>
