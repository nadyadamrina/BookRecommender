<%--
  Created by IntelliJ IDEA.
  User: nadiiadamrina
  Date: 11/18/21
  Time: 3:51 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Already Read</title>
</head>
<body>
    <h1>Already Read for User with Username: ${fn:escapeXml(param.username)}</h1>
    <table border="1">
        <tr>
            <th>Completed</th>
            <th>ISBN</th>
            <th>Title</th>
            <th>Authors</th>
        </tr>

        <c:forEach items="${alreadyread}" var="read" varStatus="status">

            <tr>
                <td><c:out value="${read.getCompleted()}" /></td>
                <td><c:out value="${read.getIsbn()}" /></td>
                <td>${books[status.index].getTitle()}</td>
                <td>${authors[status.index].getFirstName().concat(" ").concat(authors[status.index].getLastName())}</td>
            </tr>

        </c:forEach>

    </table>
</body>
</html>
