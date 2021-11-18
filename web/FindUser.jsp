<%--
  Created by IntelliJ IDEA.
  User: nadiiadamrina
  Date: 11/18/21
  Time: 10:29 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Find User</title>
</head>
<body>
    <form action="finduser" method="post">
        <h1>Search for a User by Username</h1>
        <p>
            <label for="username">Username</label>
            <input id="username" name="username" value="">
        </p>
        <p>
            <input type="submit">
            <br/><br/><br/>
            <span id="successMessage"><b>${messages.success}</b></span>
        </p>
    </form>

    <br/>
    <h1>Matching User</h1>

    <table border="1px">
        <tr>
            <th>UserName</th>
            <th>Password</th>
            <th>FirstName</th>
            <th>LastName</th>
            <th>Email</th>
            <th>Phone</th>

        </tr>
        <tr>
            <td>${user.getUserName()}</td>
            <td>${user.getPassword()}</td>
            <td>${user.getFirstName()}</td>
            <td>${user.getLastName()}</td>
            <td>${user.getEmail()}</td>
            <td>${user.getPhone()}</td>
        </tr>
    </table>
</body>
</html>
