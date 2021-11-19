<%--
  Created by IntelliJ IDEA.
  User: nadiiadamrina
  Date: 11/18/21
  Time: 10:23 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Delete a User<</title>
</head>
<body>
  <h1>${messages.title}</h1>
  <form action="deleteuser" method="post">
    <p>
    <div <c:if test="${messages.disableSubmit}">style="display:none"</c:if>>
      <label for="username">UserName</label>
      <input id="username" name="username" value="${fn:escapeXml(param.username)}">
    </div>
    </p>
    <p>
      <span id="submitButton" <c:if test="${messages.disableSubmit}">style="display:none"</c:if>>
        <input type="submit">
      </span>
    </p>
  </form>
  <br/><br/>
</body>
</html>
