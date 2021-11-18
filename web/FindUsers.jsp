<%--
  Created by IntelliJ IDEA.
  User: nadiiadamrina
  Date: 11/18/21
  Time: 1:51 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Find Users</title>
</head>
<body>
  <form action="findusers" method="post">
    <h1>Search for a BlogUser by FirstName</h1>
    <p>
      <label for="lastname">Last name</label>
      <input id="lastname" name="lastname" value="">
    </p>
    <p>
      <input type="submit">
      <br/><br/><br/>
      <span id="successMessage"><b>${messages.success}</b></span>
    </p>
  </form>

  <br/>
  <div id="finduser"><a href="finduser">Find User by Username</a></div>
  <br/>

  <h1>Matching Users</h1>
  <table border="1">
    <tr>
      <th>UserName</th>
      <th>Password</th>
      <th>FirstName</th>
      <th>LastName</th>
      <th>Email</th>
      <th>Phone</th>
    </tr>

    <c:forEach items="${users}" var="user" >
      <tr>
        <td><c:out value="${user.getUserName()}" /></td>
        <td><c:out value="${user.getPassword()}" /></td>
        <td><c:out value="${user.getFirstName()}" /></td>
        <td><c:out value="${user.getLastName()}" /></td>
        <td><c:out value="${user.getEmail()}" /></td>
        <td><c:out value="${user.getPhone()}" /></td>
      </tr>
    </c:forEach>
  </table>

</body>
</html>
