<%@ page contentType="text/html;charset=UTF-8" language="java"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Home</title>
</head>
<body>
	Home!

	<div>
		<a href="<c:url value='/helloWorld'/>">Hello world</a>
	</div>

	<div>
		<a href="<c:url value='/simpleForm'/>">Simple Form (With validation)</a>
	</div>
	


</body>
</html>