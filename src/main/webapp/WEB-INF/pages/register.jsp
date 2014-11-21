<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

  <form role="form" action="/RunMyErrand/Register.do" method="post">
                                <div class="form-group">
                                  <label for="firstname">
                                    First Name:
                                  </label>
                                  <div>
                                    <input type="text" class="form-control" id="firstname" placeholder="Enter First Name" name="firstName">
                                    <p class="help-block">
                                    </p>
                                  </div>
                                </div>
                             
                                <div class="form-group">
                                  <label for="lastname">
                                    Last Name:
                                  </label>
                                  <div>
                                    <input type="text" class="form-control" id="lastname" placeholder="Enter Last Name" name="lastName">
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
                                    <input  type="text" class="form-control" placeholder="Click to select date of birth"   id="dob" name="dob">
                                    <p class="help-block">
                                    </p>
                                  </div>
                                </div>


                                <div class="form-group">
                                  <label for="emailaddress">
                                    Email address:
                                  </label>
                                  <div>
                                    <input type="email" class="form-control" id="emailaddress" placeholder="Enter email address" name="email">
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
                                    <input type="text" class="form-control" id="phonenumber" placeholder="Enter Phone Number" name="phoneNo">
                                    <p class="help-block">
                                    </p>
                                  </div>
                                </div>
                                <label for="room">
                                    Room
                                  </label>
                                <input type="text" class="form-control" id="password" placeholder="Enter Password" name="room">
                                <div class="form-group">
                                  <label for="password">
                                    Password:
                                  </label>
                                  <div>
                                    <input type="password" class="form-control" id="password" placeholder="Enter Password" name="password">
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
                                <button type="submit" class="btn btn-success btn-sm">Register</button>
                              </form>


</body>
</html>