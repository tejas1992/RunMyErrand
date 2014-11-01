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

 <c:choose>
	 <c:when test="${fn:length(unassigned) > 0}">
    	 <c:forEach var="tasksleft" items= "${unassigned}">
    	 <table>
    	 <form action="/RunMyErrand/Assigntask.do" method="post">
    	   <tr>
           	<td><input type="hidden" value="${tasksleft.taskDescription}" name="task">${tasksleft.taskDescription}</td>
           	<td>${tasksleft.points}</td>
           	<td>${tasksleft.start_date}</td>
           	<td>${tasksleft.end_date}</td>
           
           	<td><select name="assigned">
               <option value="${user.email}">${user.firstName}</option>
           		<c:forEach var="roomy" items= "${roomies}">
           			<option value="${roomy.email}">${roomy.firstName}</option>      
           		</c:forEach>
          		</select></td>
          	</tr>
          </table>
          	<button type="submit">AssignTasks</button>
        </form>
        </c:forEach>
       </c:when>
       <c:otherwise>
         	No Tasks to display
       </c:otherwise>
      </c:choose>
</body>
</html>