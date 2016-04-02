<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<script>function validateEmail(emailField){
	    var reg = /^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/;
	
	    if (reg.test(emailField.value) == false) 
	    {
	        document.getElementsByName("loginButton")[0].disabled = true;
	        return false;
	    }
	    document.getElementsByName("loginButton")[0].disabled = false;
	
	}</script>
<title>Insert title here</title>
</head>
<body>
<h1>Welcome! Please log in or register</h1>
<form action = "login" method = "post">
<input type = "text" name = "email" onblur = "validateEmail(this);">
<input type = "password" name = "pass">
<input type = "submit" value = "Log in" name = "loginButton"> 
</form>
<br><h4 style = color:red> ${invalidUser}</h4>
</body>
</html>