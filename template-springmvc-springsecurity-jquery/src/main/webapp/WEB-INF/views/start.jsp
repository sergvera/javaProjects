<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<title>Start</title>
<link href="css/pepper-grinder/jquery-ui-1.9.1.custom.css" rel="stylesheet">
<script src="js/jquery-1.8.2.js"></script>
<script src="js/jquery-ui-1.9.1.custom.js"></script>

</head>
<body>
   Home!
   <div>
      <a href='<c:url value="/simpleAjaxForm"  />'> A simple ajax form </a>
   </div>

   <div>
      <a href='<c:url value="/menu"  />'> A page displaying a menu. If you used 'serg' as an user, you'll see more option. Any other user will see less
         options. </a>
   </div>




</body>
</html>