<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">

<title>Insert title here</title>

<script src="http://maps.google.com/maps/api/js?sensor=false"></script>


</head>
<body>


<form:form method="POST" action="/contactmgmt/processSearch" commandName="search">


		<table class="table table-striped">
			<tr>
				<td>Enter search text</td>
				<td><form:input path="query"/>
			</tr>
			<tr>
				<td>Search by:</td>
 				<td><form:select path="field">
					  <form:option value="" label="...." />
					  <form:options items="${fields}" />
				       </form:select></td>
				<td><form:hidden id="lon" path="currentLon" value=""/></td>
				<td><form:hidden id="lat" path="currentLat" value=""/></td>
			</tr>
        <tr>
            <td><input type="submit" value="Submit"/></td>
             <td><a href="/contactmgmt">Cancel</a></td>
            
        </tr>
		</table>

	</form:form>

	<script>

	var x = document.getElementById("lon");
	var y = document.getElementById("lat");
	
	window.onload=function() { 
	    if (navigator.geolocation) {
	        navigator.geolocation.watchPosition(showPosition);
	    } else {
	        x.innerHTML = "Geolocation is not supported by this browser.";
	    }
		
		
	}; 
		

	function showPosition(position) {
	    x.value = position.coords.latitude;
	    y.value = position.coords.longitude;
	}


	</script>
<a href="/contactmgmt">Home</a>
</body>
</html>