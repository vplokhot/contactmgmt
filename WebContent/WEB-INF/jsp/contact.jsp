<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
    <title>Spring MVC Form Handling</title>
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
</head>
<body>

<h2>Contact Information</h2>
<form:form method="POST" action="/contactmgmt/addContact" role="form">
   <table class="table table-striped">
    <tr>
        <td><form:label path="firstName" class="form-control">Fist Name</form:label></td>
        <td><form:input path="firstName" class="form-control"/></td>
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