<%--
  Created by IntelliJ IDEA.
  User: nadiiadamrina
  Date: 11/18/21
  Time: 10:01 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h1>Update User</h1>
    <form action="updateuser" method="post">
        <p>
            <label for="username">UserName</label>
            <input id="username" name="username" value="${fn:escapeXml(param.username)}">
        </p>

        <p>
            <label for="firstname">New First Name</label>
            <input id="firstname" name="firstname" value="">
        </p>

        <p>
            <label for="lastname">New Last Name</label>
            <input id="lastname" name="lastname" value="">
        </p>
        <p>
            <input type="submit">
        </p>
    </form>

    <br/>
        <div id="finduser"><a href="finduser?username=${user.getUserName()}">Find User by Username</a></div>
        <div id="findusers"><a href="findusers?lastname=${user.getLastName()}">Find Users by Last Name</a></div>
    <br/>
    <p>
        <span id="successMessage"><b>${messages.success}</b></span>
    </p>
</body>
</html>
