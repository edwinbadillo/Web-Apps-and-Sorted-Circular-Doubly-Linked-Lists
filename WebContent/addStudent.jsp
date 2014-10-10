<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add Student</title>
</head>
<body>
	<h2>New Student Information</h2>
	<div>
		<form id="addStudent" action="AddStudent" method = "POST">
			Student Id : <input type="text" name="studentId"> <p>
			First Name : <input type="text" name="firstName"> <p>
			Last Name : <input type="text" name="lastName"> <p>
			Phone : <input type="text" name="phone"> <p>
			Age : <input type="text" name="age"> <p>
			GPA : <input type="text" name="gpa"> <p>
			<input type="submit" value="Submit" >
		</form>
	</div>
</body>
</html>