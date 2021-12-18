<%--
  Created by IntelliJ IDEA.
  User: nadiiadamrina
  Date: 11/18/21
  Time: 1:51 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<t:genericpage title="Find Users">
    <jsp:attribute name="header">
        <h1>Search for a User by Last Name</h1>
    </jsp:attribute>
    <jsp:body>
        <div class="row justify-content-start">
            <div class="col-4">
                <form action="findusers" method="post">
                    <div class="mb-3">
                        <label for="lastname" class="form-label">Last name</label>
                        <input id="lastname" name="lastname" value="" class="form-control">
                    </div>
                    <button type="submit" class="btn btn-primary">Submit</button>
                    <br/><br/><br/>
                    <span id="successMessage"><b>${messages.success}</b></span>
                </form>
            </div>
        </div>

        <br/>
        <div id="finduser"><a href="finduser">Find User by Username</a></div>
        <div id="createuser"><a href="createuser">Create a User</a></div>
        <br/>

        <h1>Matching Users</h1>
        <table border="1" class="table table-striped">
            <tr>
                <th>UserName</th>
                <th>Password</th>
                <th>FirstName</th>
                <th>LastName</th>
                <th>Email</th>
                <th>Phone</th>
                <th>UpdateUser</th>
                <th>DeleteUser</th>
                <th>AlreadyRead</th>
                <th>Recommendation</th>
            </tr>

            <c:forEach items="${users}" var="user">
                <tr>
                    <td><c:out value="${user.getUserName()}"/></td>
                    <td><c:out value="${user.getPassword()}"/></td>
                    <td><c:out value="${user.getFirstName()}"/></td>
                    <td><c:out value="${user.getLastName()}"/></td>
                    <td><c:out value="${user.getEmail()}"/></td>
                    <td><c:out value="${user.getPhone()}"/></td>
                    <td><a href="updateuser?username=<c:out value="${user.getUserName()}"/>">UpdateUser</a>
                    </td>
                    <td><a href="deleteuser?username=${user.getUserName()}">DeleteUser</a></td>
                    <td><a href="alreadyread?username=<c:out value="${user.getUserName()}"/>">AlreadyRead</a>
                    </td>
                    <td><a href="recommendation?username=<c:out value="${user.getUserName()}"/>">Recommendation</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </jsp:body>
</t:genericpage>

