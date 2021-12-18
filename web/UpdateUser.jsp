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
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<t:genericpage title="Update User">
    <jsp:attribute name="header">
        <h1>Update User</h1>
    </jsp:attribute>
    <jsp:body>
        <div class="row justify-content-start">
            <div class="col-4">
                <form action="updateuser" method="post">
                    <div class="mb-3">
                        <label for="username" class="form-label">UserName</label>
                        <input id="username" name="username" value="${fn:escapeXml(param.username)}"
                               class="form-control">
                    </div>

                    <div class="mb-3">
                        <label for="firstname" class="form-label">New First Name</label>
                        <input id="firstname" name="firstname" value="" class="form-control">
                    </div>

                    <div class="mb-3">
                        <label for="lastname" class="form-label">New Last Name</label>
                        <input id="lastname" name="lastname" value="" class="form-control">
                    </div>
                    <button type="submit" class="btn btn-primary">Submit</button>
                </form>
            </div>
        </div>

        <br/>
        <div id="finduser"><a href="finduser?username=${user.getUserName()}">Find User by
            Username</a></div>
        <div id="findusers"><a href="findusers?lastname=${user.getLastName()}">Find Users by Last
            Name</a></div>
        <br/>
        <p>
            <span id="successMessage"><b>${messages.success}</b></span>
        </p>
    </jsp:body>
</t:genericpage>
