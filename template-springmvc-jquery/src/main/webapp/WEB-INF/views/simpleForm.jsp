<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Simple form</title>

<style>
.error {
	color: #ff0000;
}

.errorblock {
	color: #000;
	background-color: #ffEEEE;
	border: 3px solid #ff0000;
	padding: 8px;
	margin: 16px;
}
</style>

</head>
<body>
	<h2>Simple Spring's form tags example</h2>

	<div>Correct password: 'mypass'</div>

	<form:form method="POST" commandName="customer">
		<form:errors path="*" cssClass="errorblock" element="div" />

		<div>
			UserName :
			<form:input path="name" />
			<form:errors path="name" cssClass="error" />
		</div>

		<div>
			Password :
			<form:password path="password" />
			<form:errors path="password" cssClass="error" />
		</div>

		<div>
			<input type="submit" />
		</div>


	</form:form>


</body>
</html>