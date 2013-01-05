<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Simple ajax form</title>
<link href="css/pepper-grinder/jquery-ui-1.9.1.custom.css" rel="stylesheet">
<style>
.error {
	color: #ff0000;
}
</style>

<script src="js/jquery-1.8.2.js"></script>
<script src="js/jquery-ui-1.9.1.custom.js"></script>

<script type="text/javascript">
	
	$(function() {
		
		$("#button").button();

        var $form = $('#simpleAjaxForm');

        $form.bind('submit', function(e) {
            $('#results').hide();

            $form.find('.control-group').removeClass('error');
            $form.find('.help-inline').empty();

            $.ajax({
                type : 'POST',
                url : "postForm.json",
                data : $form.serialize(),
                async : false,
                success : function(data) {

                    if (data.status == "SUCCESS") {

                        $("#resultText").html(data.status);
                        $('#results').show();
                    }

                    if (data.status = "VALIDATION_ERROR") {
                        for ( var i = 0; i < data.errorMessages.length; i++) {
                            var item = data.errorMessages[i];
                            var $controlGroup = $('#' + item.fieldName + 'ControlGroup');
                            $controlGroup.addClass('error');
                            $controlGroup.find('.help-inline').html(item.message);
                        }
                    }
                }
            });
            
            e.preventDefault();
            return false;

        });
		
	});
	
</script>



</head>
<body>
   <h2>Simple Spring's form tags example</h2>

   <form:form method="POST" commandName="customer" id="simpleAjaxForm">


      <div class="control-group" id="nameControlGroup">
         Customer Name :
         <form:input path="name" />
         <span class="help-inline"></span>
      </div>

      <div class="control-group" id="addressControlGroup">
         Address :
         <form:password path="address" />
         <span class="help-inline"></span>
      </div>
      <div>
         <button id="button">Save record</button>
      </div>



   </form:form>


   <div class="ui-widget">
      <div class="ui-state-highlight ui-corner-all" style="margin-top: 20px; padding: 0 .7em;" id="results">

         <span class="ui-icon ui-icon-info" style="float: left; margin-right: .3em;"></span>
         <p id="resultText"></p>
      </div>
   </div>



</body>
</html>