<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
    <title>Spring MVC Form Handling</title>
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
    
</head>
<body>

<h2>Submitted Contact Information</h2>
   <table class="table table-striped">
    <tr>
        <td>First Name</td>
        <td>${firstName}</td>
    </tr>
    <tr>
        <td>Last Name</td>
        <td>${lastName}</td>
    </tr>
    <tr>
        <td>Address</td>
        <td>${address}</td>
    </tr>
    <tr>
        <td>Email</td>
        <td>${email}</td>
    </tr>
    

</table>  
    <a href="/contactmgmt">Home</a>
</body>
</html>