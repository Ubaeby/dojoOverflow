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

<h1>What is your question?</h1>
<a href="/">Home</a>

<form:form action="/questions/new" method="post" modelAttribute="newQuestion">

	<form:hidden path="id" value="${id}"/>
	<form:label path="text">Question: </form:label>
	<form:errors path="text"/>
	<form:textarea path="text"/>
	
	<label for="tags">Tag: </label>
	<input type="text" name="allTags" id="allTags"/>
	<p><c:out value="${error}"/></p>
	
	<button>Submit</button>
</form:form>

</body>
</html>