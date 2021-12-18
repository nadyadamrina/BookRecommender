<%--
  Created by IntelliJ IDEA.
  User: nadiiadamrina
  Date: 12/16/21
  Time: 9:33 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<t:genericpage title="Create Preferences">
    <jsp:attribute name="header">
        <h1>Create Preferences</h1>
    </jsp:attribute>
    <jsp:body>
        <div class="row justify-content-start">
            <div class="col-4">
                <form action="createpreferences" method="post">
                    <div class="mb-3">
                        <label for="username" class="form-label">UserName</label>
                        <input id="username" name="username" value="" class="form-control">
                    </div>

                    <div class="mb-3">
                        <label for="primary" class="form-label">Choose a primary genre:</label>
                        <select name="primary" id="primary" class="form-select">
                            <c:forEach items="${genres}" var="genre" >
                                <option value=<c:out value="${genre}" />><c:out value="${genre}" /></option>
                            </c:forEach>
                        </select>
                    </div>

                    <div class="mb-3">
                        <label for="secondary" class="form-label">Choose a secondary genre:</label>
                        <select name="secondary" id="secondary" class="form-select">
                            <c:forEach items="${genres}" var="genre" >
                                <option value=<c:out value="${genre}" />><c:out value="${genre}" /></option>
                            </c:forEach>
                        </select>
                    </div>

                    <button type="submit" class="btn btn-primary">Submit</button>
                </form>
                <p>
                    <span id="successMessage"><b>${messages.success}</b></span>
                </p>
            </div>
        </div>
    </jsp:body>
</t:genericpage>
