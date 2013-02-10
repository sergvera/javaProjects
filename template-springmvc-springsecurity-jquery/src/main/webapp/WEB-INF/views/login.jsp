<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login page</title>
<link href="css/pepper-grinder/jquery-ui-1.9.1.custom.css" rel="stylesheet">
<script src="js/jquery-1.8.2.js"></script>
<script src="js/jquery-ui-1.9.1.custom.js"></script>

<script type="text/javascript">
	$(function() {

		$("#submit").button();

	});
</script>


</head>
<body>
   <div>
      <h1>Login</h1>
   </div>



   <div id="login-error">${error}</div>

   <div>Use any user, use pass: dummyPass</div>

   <form action="j_spring_security_check" method="post">


      <div>
         <label for="j_username">Username</label> <input id="j_username" name="j_username" type="text" />

      </div>

      <div>
         <label for="j_password">Password</label> <input id="j_password" name="j_password" type="password" />

      </div>
      <div>
         <input id="submit" type="submit" value="Login" />

      </div>

   </form>
</body>
</html>