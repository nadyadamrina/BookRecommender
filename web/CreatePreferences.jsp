<%--
  Created by IntelliJ IDEA.
  User: nadiiadamrina
  Date: 12/16/21
  Time: 9:33 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create a Preference</title>
</head>
<body>
<form action="createpreferences" method="post">
    <p>
        <label for="username">UserName</label>
        <input id="username" name="username" value="">
    </p>

    <label for="primary">Choose a primary genre:</label>
    <select name="primary" id="primary">
        <c:forEach items="${genres}" var="genre" >
            <option value=<c:out value="${genre}" />><c:out value="${genre}" /></option>
        </c:forEach>
    </select>

    <label for="secondary">Choose a secondary genre:</label>
    <select name="secondary" id="secondary">
        <c:forEach items="${genres}" var="genre" >
            <option value=<c:out value="${genre}" />><c:out value="${genre}" /></option>
        </c:forEach>
    </select>

    <p>
        <input type="submit">
    </p>
</form>
<br/><br/>
<p>
    <span id="successMessage"><b>${messages.success}</b></span>
</p>
</body>
</html>
