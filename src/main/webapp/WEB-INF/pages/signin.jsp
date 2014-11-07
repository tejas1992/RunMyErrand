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
  <div class="container" id = "loginwidget">
    <div class="row">
        <div class="col-md-5 col-md-offset-3">
            <div class="panel panel-default">
                <div class="panel-heading"> <strong class="">Login</strong>
                </div>
                <div class="panel-body">
                    <form action="<c:url value='/j_spring_security_check' />" class="form-horizontal" role="form" method="POST">
                    	${error}
                    	${message}
                        <div class="form-group">
                            <label for="inputEmail3" class="col-sm-3 control-label">Email</label>
                            <div class="col-sm-9">
                                <input class="form-control" id="inputEmail3" placeholder="Email" type="email" name="username" required="required">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="inputPassword3" class="col-sm-3 control-label">Password</label>
                            <div class="col-sm-9">
                                <input class="form-control" id="inputPassword3" placeholder="Password" type="password" name="password" required="required">
                            </div>
                        </div>
                        <div class="form-group last">
                            <div class="col-sm-offset-3 col-sm-9">
                                <button type="submit" class="btn btn-success btn-sm">Sign in</button>
                                <button type="reset" class="btn btn-default btn-sm">Reset</button>
                            </div>
                        </div>
                       
                    </form>
                </div>
                <div class="panel-footer">Not Registered? <a id="signupLink" data-toggle="modal" data-target="#myModal">Sign Up here</a>
                </div>
                <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                  <div class="modal-dialog">
                    <div class="modal-content">
                      <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                        <h4 class="modal-title" id="myModalLabel">Sign Up</h4>
                      </div>
                      <div class="modal-body">
  						<form role="form" action="/RunMyErrand/Register.do" method="post">
                                <div class="form-group">
                                  <label for="firstname">
                                    First Name:
                                  </label>
                                  <div>
                                    <input type="text" class="form-control" id="firstname" placeholder="Enter First Name" name="firstName" required="required">
                                    <p class="help-block">
                                    </p>
                                  </div>
                                </div>
                             
                                <div class="form-group">
                                  <label for="lastname">
                                    Last Name:
                                  </label>
                                  <div>
                                    <input type="text" class="form-control" id="lastname" placeholder="Enter Last Name" name="lastName" required="required">
                                    <p class="help-block">
                                    </p>
                                  </div>
                                </div>

                                <div class="form-group">
                                  <label for="sex">
                                    Sex:
                                  </label>
                                  <div>
                                    <label class="radio">
                                      <input type="radio" name="sex" id="sex" value="male" checked>
                                      Male
                                    </label>
                                    <label class="radio">
                                      <input type="radio" name="sex" id="sex" value="female">
                                      Female
                                    </label>
                                  </div>
                                </div>
                             
                              <div class="form-group">
                                  <label for="dob">
                                    Date of Birth:
                                  </label>
                                  <div>
                                    <input  type="text" class="form-control" placeholder="Click to select date of birth"   id="dob" name="dob" required="required">
                                    <p class="help-block">
                                    </p>
                                  </div>
                                </div>


                                <div class="form-group">
                                  <label for="emailaddress">
                                    Email address:
                                  </label>
                                  <div>
                                    <input type="email" class="form-control" id="emailaddress" placeholder="Enter email address" name="email" required="required">
                                    <p class="help-block">
                                      Example: abc@xyz.com
                                    </p>
                                    <p class="help-block">
                                    </p>
                                  </div>
                                </div>

                                <div class="form-group">
                                  <label for="phonenumber">
                                    Phone Number:
                                  </label>
                                  <div>
                                    <input type="text" class="form-control" id="phonenumber" placeholder="Enter Phone Number" name="phoneNo" required="required">
                                    <p class="help-block">
                                    </p>
                                  </div>
                                </div>
                                <label for="room">
                                    Room
                                  </label>
                                <input type="text" class="form-control" id="password" placeholder="Enter Password" name="room" required="required">
                                <div class="form-group">
                                  <label for="password">
                                    Password:
                                  </label>
                                  <div>
                                    <input type="password" class="form-control" id="password" placeholder="Enter Password" name="password" required="required" size="10">
                                    <p class="help-block">
                                    </p>
                                  </div>
                                </div>
                      <div class="modal-footer">
                                <button type="submit" class="btn btn-success btn-sm">Register</button>
                      </div>                                
                              </form>
                        </div>
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
