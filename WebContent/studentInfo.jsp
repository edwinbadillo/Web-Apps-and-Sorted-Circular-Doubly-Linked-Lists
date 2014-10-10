<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Student Info</title>
</head>
<body>
<jsp:useBean id="student" class="edu.uprm.ece.icom4035.student.Student" scope="session"/> 

	<div> 
		<h2>Student Information</h2>
		Student id: <%= student.getStudentId() %><br>
		Name: <%= student.getFirstName() + " " + student.getLastName() %><br>
		Phone: <%= student.getPhone() %><br>
		Age: <%= student.getAge() %><br>
		GPA: <%= student.getGpa() %><br>
	</div>
	<p>
	<div>
		<form id="backMain" action="main.jsp" method="GET">
		  	Back to main: <input type="submit" value="Go"/>
		 </form>
		
	</div>
</body>
</html>