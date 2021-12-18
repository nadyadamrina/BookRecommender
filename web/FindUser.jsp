<%--
  Created by IntelliJ IDEA.
  User: nadiiadamrina
  Date: 11/18/21
  Time: 10:29 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:genericpage title="Find a User">
    <jsp:attribute name="header">
        <h1>Search for a User by Username</h1>
    </jsp:attribute>
    <jsp:body>
        <div class="row justify-content-start">
            <div class="col-4">
                <form action="finduser" method="post">
                    <div class="mb-3">
                        <label for="username" class="form-label">Username</label>
                        <input id="username" name="username" value="" class="form-control">
                    </div>
                    <button type="submit" class="btn btn-primary">Submit</button>
                    <br/><br/><br/>
                    <span id="successMessage"><b>${messages.success}</b></span>
                </form>
            </div>
        </div>

        <br/>
        <div id="findusers"><a href="findusers">Find Users by Last Name</a></div>
        <div id="createuser"><a href="createuser">Create a User</a></div>
        <br/>

        <br/>
        <h1>Matching User</h1>

        <table border="1px" class="table table-striped">
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
            
            <tr>
                <td>${user.getUserName()}</td>
                <td>${user.getPassword()}</td>
                <td>${user.getFirstName()}</td>
                <td>${user.getLastName()}</td>
                <td>${user.getEmail()}</td>
                <td>${user.getPhone()}</td>
                <td><a href="updateuser?username=${user.getUserName()}">UpdateUser</a></td>
                <td><a href="deleteuser?username=${user.getUserName()}">DeleteUser</a></td>
                <td><a href="alreadyread?username=${user.getUserName()}">AlreadyRead</a></td>
                <td><a href="recommendation?username=${user.getUserName()}">Recommendation</a></td>
            </tr>
        </table>
    </jsp:body>
</t:genericpage>
