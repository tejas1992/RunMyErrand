<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<a href="<c:url value="j_spring_security_logout" />"><span class="glyphicon glyphicon-log-out"></span> Logout</a>
Yeah done dona done done
		Current System Date: ${currentdate}
		TimeBox Start Date : ${timeboxstartdate}
		Timebox End Date   : ${timeboxenddate}
		${error}
		<form action="/RunMyErrand/fastforward" method ="post">
			Enter a Date to see the future:<input type="text" name="date" />
			<input type="submit" />Fastforward
		</form>
</body>
</html>