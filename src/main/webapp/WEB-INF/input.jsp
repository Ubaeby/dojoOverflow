<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!-- for rendering errors on PUT routes -->
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h1><c:out value="${questions.text}"/></h1>
<a href="/">Home</a>

<div>
<p>Tags:</p>
<c:forEach var="t" items="${aTags}">
	<button>
	<c:out value="${t.subject}"/>
	</button>
</c:forEach>
</div>


<div>
<table>
	<thead>
		<tr>
			<td>Answers:</td>
		</tr>
	</thead>
	<tbody>
		<c:forEach var ="a" items="${questions.answers}">
			<tr>
				<td><c:out value="${a.text}"/></td>
			</tr>
		</c:forEach>
	</tbody>
</table>
		
</div>

<div>
	
	<h3>Add your answer:</h3>
	
	<form:form action="/questions/${questions.id}" method="post" modelAttribute="newAnswer">
		<form:label path="text">Answer: </form:label>
		<form:errors path="text"/>
		<form:textarea path="text"/>
			
		<button>Answer It!</button>
	</form:form>
	
</div>

	
</body>
</html>