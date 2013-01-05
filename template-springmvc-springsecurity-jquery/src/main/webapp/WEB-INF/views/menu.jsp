<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<title>Menu</title>
<link href="css/pepper-grinder/jquery-ui-1.9.1.custom.css" rel="stylesheet">
<script src="js/jquery-1.8.2.js"></script>
<script src="js/jquery-ui-1.9.1.custom.js"></script>

<style type="text/css">
.ui-menu {
	overflow: hidden;
}

.ui-menu .ui-menu {
	overflow: visible !important;
}

.ui-menu>li {
	float: left;
	display: block;
	width: auto !important;
}

.ui-menu>li {
	margin: 5px 5px !important;
	padding: 0 0 !important;
}

.ui-menu>li>a {
	float: left;
	display: block;
	clear: both;
	overflow: hidden;
}

.ui-menu .ui-menu-icon {
	margin-top: 0.3em !important;
}

.ui-menu .ui-menu .ui-menu li {
	float: left;
	display: block;
}
</style>

<script type="text/javascript">
	$(function() {

		$("#menu").menu({
			position : {
				at : "left bottom"
			}
		});
	});
</script>

</head>
<body>

   <div>
      <ul id="menu">
         <li><a href="#">Item 1</a></li>
         <sec:authorize access="hasRole('ROLE_SERG')">
            <li><a href="#">Item 2</a></li>
         </sec:authorize>
         <li><a href="#">Item 3</a>
            <ul>
               <li><a href="#">Item 3-1</a></li>
               <li><a href="#">Item 3-2</a></li>
               <sec:authorize access="hasRole('ROLE_SERG')">
                  <li><a href="#">Item 3-3</a></li>
               </sec:authorize>

               <li><a href="#">Item 3-4</a></li>
               <li><a href="#">Item 3-5</a></li>
            </ul></li>
         <li><a href="#">Item 4</a></li>
         <sec:authorize access="hasRole('ROLE_SERG')">
            <li><a href="#">Item 5</a></li>
         </sec:authorize>
      </ul>
   </div>

   <div>

      The purpose of this page is to show the menu...
      <p />
      Different entries will be shown dependending on the user used when loggin in.
      <p />
      Use 'serg' as an user to see more entries. <br /> Use anyother user to see less entries. <br />
      <p />
      Entries are filtered using spring security tags.
   </div>
</body>
</html>