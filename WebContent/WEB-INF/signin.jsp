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
                    <form class="form-horizontal" role="form">
                        <div class="form-group">
                            <label for="inputEmail3" class="col-sm-3 control-label">Email</label>
                            <div class="col-sm-9">
                                <input class="form-control" id="inputEmail3" placeholder="Email" type="email">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="inputPassword3" class="col-sm-3 control-label">Password</label>
                            <div class="col-sm-9">
                                <input class="form-control" id="inputPassword3" placeholder="Password" type="password">
                            </div>
                        </div>
                        <div class="form-group last">
                            <div class="col-sm-offset-3 col-sm-9">
                                <button type="submit" class="btn btn-success btn-sm" onClick="dashboard.html">Sign in</button>
                                <button type="reset" class="btn btn-default btn-sm">Reset</button>
                            </div>
                        </div>
                    </form>
                </div>
                <div class="panel-footer">Not Registered? <a id="signupLink">Sign Up here</a>
                </div>
                <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                  <div class="modal-dialog">
                    <div class="modal-content">
                      <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                        <h4 class="modal-title" id="myModalLabel">Sign Up</h4>
                      </div>
                      <div class="modal-body">
                            <form role="form">
                                <div class="form-group">
                                  <label for="firstname">
                                    First Name:
                                  </label>
                                  <div>
                                    <input type="text" class="form-control" id="firstname" placeholder="Enter First Name">
                                    <p class="help-block">
                                    </p>
                                  </div>
                                </div>
                             
                                <div class="form-group">
                                  <label for="lastname">
                                    Last Name:
                                  </label>
                                  <div>
                                    <input type="text" class="form-control" id="lastname" placeholder="Enter Last Name">
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
                                    <input  type="text" class="form-control" placeholder="Click to select date of birth"   id="dob">
                                    <p class="help-block">
                                    </p>
                                  </div>
                                </div>


                                <div class="form-group">
                                  <label for="emailaddress">
                                    Email address:
                                  </label>
                                  <div>
                                    <input type="email" class="form-control" id="emailaddress" placeholder="Enter email address">
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
                                    <input type="text" class="form-control" id="phonenumber" placeholder="Enter Phone Number">
                                    <p class="help-block">
                                    </p>
                                  </div>
                                </div>
                             
                                <div class="form-group">
                                  <label for="password">
                                    Password:
                                  </label>
                                  <div>
                                    <input type="password" class="form-control" id="password" placeholder="Enter Password">
                                    <p class="help-block">
                                    </p>
                                  </div>
                                </div>
                             
                                <div class="form-group">
                                  <label for="repassword">
                                    Re- enter Password:
                                  </label>
                                  <div>
                                    <input type="password" class="form-control" id="repassword" placeholder="Re-Enter Password">
                                  </div>
                                    <p class="help-block">
                                    </p>
                                </div>
                              </form>
                        </div>
                      <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                        <button type="button" class="btn btn-success">Register</button>
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
