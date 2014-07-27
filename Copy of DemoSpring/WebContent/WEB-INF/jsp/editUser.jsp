<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>User Page</title>
</head>
<body>
	<form:form id="editUserPage" action="updateUser" method="post"
		modelAttribute="user">
		<table>
			<tr>
				<td style="visibility: hidden;">id</td>
				<td style="visibility: hidden;"><form:input path="id"
						id="userId" /></td>
			</tr>
			<tr>
				<td>NAME</td>
				<td><form:input path="name" id="name" /></td>
			</tr>
			<tr>
				<td>CITY</td>
				<td><form:input path="city" id="city" /></td>
			</tr>
			<tr>
				<td>AGE</td>
				<td><form:input path="age" id="age" /></td>
			</tr>
			<tr>
				<td>COUNTRY</td>
				<td><form:input path="country" id="country" /></td>
			</tr>
			<tr>
				<td>LANGUAGE</td>
				<td><form:input path="language" id="language" /></td>
			</tr>
			<tr>
				<td>CONTACT</td>
				<td><form:input path="contact" id="contact" /></td>
			</tr>
			<tr>
				<td>DESCRIPTION</td>
				<td><form:input path="description" id="description" /></td>
			</tr>
			<tr>
				<td colspan="2" align="center"><button type="submit"
						value="Details">Update</button></td>
			</tr>

		</table>

	</form:form>
</body>
</html>