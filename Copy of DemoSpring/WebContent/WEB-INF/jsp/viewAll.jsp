<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>All Records</title>
</head>

<body>
	<form id="showAll">
		<table align="center" border="1">
			<tr>

				<th>Name</th>
				<th>City</th>
				<th>Age</th>
				<th>Country</th>
				<th>Language</th>
				<th>Contact</th>
				<th>Description</th>
				<th>EDIT</th>
				<th>DELETE</th>
			</tr>
			<c:forEach var="row" items="${records}">
				<tr>
					<td>${row.name}</td>
					<td>${row.city}</td>
					<td>${row.age}</td>
					<td>${row.country}</td>
					<td>${row.language}</td>
					<td>${row.contact}</td>
					<td>${row.description}</td>
					<td><a href="editUser?id=${row.id}">Edit</a></td>
					<td><a href="deleteUser?id=${row.id}">Delete</a></td>
				</tr>
			</c:forEach>
		</table>
	</form>
</body>
</html>