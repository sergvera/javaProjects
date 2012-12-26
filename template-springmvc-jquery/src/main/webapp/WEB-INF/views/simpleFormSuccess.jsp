<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<title>Simple form success</title>


</head>
<body>
	<h2>Simple Spring's form tags example</h2>

	<div>Correct password: 'mypass'</div>

	
		<div>
			UserName :
			${customer.name}

		</div>

		<div>
			Password :
			${customer.password}
		</div>



</body>
</html>