<%--
  Created by IntelliJ IDEA.
  User: nadiiadamrina
  Date: 11/18/21
  Time: 6:43 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create a User</title>
</head>
<body>
    <form action="createuser" method="post">
        <p>
            <label for="username">UserName</label>
            <input id="username" name="username" value="">
        </p>
        <p>
            <label for="password">Password</label>
            <input id="password" name="password" value="">
        </p>
        <p>
            <label for="firstname">FirstName</label>
            <input id="firstname" name="firstname" value="">
        </p>
        <p>
            <label for="lastname">LastName</label>
            <input id="lastname" name="lastname" value="">
        </p>
        <p>
            <label for="email">Email</label>
            <input id="email" name="email" value="">
        </p>
        <p>
            <label for="phone">Phone</label>
            <input id="phone" name="phone" value="">
        </p>
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
