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
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>


<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<t:genericpage title="Create a User">
    <jsp:attribute name="header">
        <h1>Create User</h1>
    </jsp:attribute>
    <jsp:body>
        <div class="row justify-content-start">
            <div class="col-4">
                <form action="createuser" method="post">
                    <div class="mb-3">
                        <label for="username" class="form-label">UserName</label>
                        <input id="username" name="username" value="" class="form-control">
                    </div>
                    <div class="mb-3">
                        <label for="password" class="form-label">Password</label>
                        <input id="password" name="password" value="" class="form-control">
                    </div>
                    <div class="mb-3">
                        <label for="firstname" class="form-label">FirstName</label>
                        <input id="firstname" name="firstname" value="" class="form-control">
                    </div>
                    <div class="mb-3">
                        <label for="lastname" class="form-label">LastName</label>
                        <input id="lastname" name="lastname" value="" class="form-control">
                    </div>
                    <div class="mb-3">
                        <label for="email" class="form-label">Email</label>
                        <input id="email" name="email" value="" class="form-control">
                    </div>
                    <div class="mb-3">
                        <label for="phone" class="form-label">Phone</label>
                        <input id="phone" name="phone" value="" class="form-control">
                    </div>
                    <button type="submit" class="btn btn-primary">Submit</button>
                </form>
                <br/><br/>
                <p>
                    <span id="successMessage"><b>${messages.success}</b></span>
                </p>
            </div>
        </div>
    </jsp:body>
</t:genericpage>