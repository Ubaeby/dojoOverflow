<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<h1>Questions Dashboard</h1>

	<table>
		<thead>
			<tr>
				<td>Question</td>
				<td>Tags</td>
			</tr>
		</thead>

		<tbody>
			<c:forEach var="q" items="${questions}">
				<tr>

					<td><a href="/questions/${q.id}"><c:out value="${q.text}" /></a>

					</td>
			<c:forEach var="t" items="${q.tags}">
					<td><c:out value="${t.subject}" /></td>
			</c:forEach>
				</tr>

			</c:forEach>



		</tbody>
	</table>

	<a href="/questions/new">New Question</a>
</body>
</html>