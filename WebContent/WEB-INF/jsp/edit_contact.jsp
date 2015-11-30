<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">

<title>Insert title here</title>
</head>
<body>

<form:form method="POST" action="/contactmgmt/updateContact" modelAttribute="contact">
   <table class="table table-striped">
    <tr>
        <td><form:hidden path="id" /></td>
    </tr>  
    <tr>
        <td><form:label path="firstName" class="form-control">Fist Name</form:label></td>
        <td><form:input path="firstName" class="form-control" /></td>
    </tr>
    <tr>
        <td><form:label path="lastName" class="form-control">Last Name</form:label></td>
        <td><form:input path="lastName" class="form-control"/></td>
    </tr>
    <tr>
        <td><form:label path="address" class="form-control">Address</form:label></td>
        <td><form:input path="address" class="form-control"/></td>
    </tr>
        <tr>
        <td><form:label path="email" class="form-control">Email</form:label></td>
        <td><form:input path="email" class="form-control"/></td>
    </tr>
    <tr>
        <td colspan="2">
            <input type="submit" value="Submit"/>
            <a href="/contactmgmt">Cancel</a>
            
        </td>
    </tr>
</table>  
</form:form>


</body>
</html>