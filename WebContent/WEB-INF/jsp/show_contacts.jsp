<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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


<h1>Showing contacts:</h1>
<table class="table table-striped">
<tr>
	<td>Id</td>
	<td>First Name</td>
	<td>Last Name</td>
	<td>Address</td>
	<td>Email</td>
	<td colspan="2">Actions</td>
</tr>
<c:forEach var="x" items="${contacts}">
<tr>
	<td><c:out value="${x.id}"/></td>
	<td><c:out value="${x.firstName}"/></td>
	<td><c:out value="${x.lastName}"/></td>
	<td><c:out value="${x.address}"/></td>
	<td><c:out value="${x.email}"/></td>
<%-- 	<td><c:out value="${x.lon}"/></td>
	<td><c:out value="${x.lat}"/></td>
	<td><c:out value="${x.distance}"/></td> --%>
	<td><a href="editContact?id=${x.id}">Edit</a></td>
    <td><a href="deleteContact?id=${x.id}">Delete</a></td>
</tr>
</c:forEach>
</table>
<a href="contact">Create a new contact</a><br />
<a href="searchContact">Search contact</a><br />
<a href="/contactmgmt">Home</a>

</body>
</html>
