<%--
  Created by IntelliJ IDEA.
  User: nadiiadamrina
  Date: 12/17/21
  Time: 4:04 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Find Preferences</title>
</head>
<body>
<table border="1">
    <tr>
        <th>Primary</th>
        <th>Secondary</th>
    </tr>

    <form action="findpreferences" method="post">
        <h1>Preferences for a User by Username</h1>
        <p>
            <label for="username">Username</label>
            <input id="username" name="username" value="${fn:escapeXml(param.username)}">
        </p>
        <p>
            <input type="submit">
            <br/><br/><br/>
            <span id="successMessage"><b>${messages.success}</b></span>
        </p>
    </form>

    <c:forEach items="${preferences}" var="pref">
        <tr>
            <td><c:out value="${pref.getPrimaryGenre()}" /></td>
            <td><c:out value="${pref.getSecondaryGenre()}" /></td>
        </tr>
    </c:forEach>

</table>
</body>
</html>
