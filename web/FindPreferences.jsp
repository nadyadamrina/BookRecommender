<%--
  Created by IntelliJ IDEA.
  User: nadiiadamrina
  Date: 12/17/21
  Time: 4:04 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<t:genericpage title="Find Preferences">
  <jsp:attribute name="header">
    <h1>Preferences for a User by Username</h1>
  </jsp:attribute>
    <jsp:body>
        <div class="row justify-content-start">
            <div class="col-4">
                <form action="findpreferences" method="post">
                    <div class="mb-3">
                        <label for="username" class="form-label">Username</label>
                        <input id="username" name="username" value="${fn:escapeXml(param.username)}"
                               class="form-control">
                    </div>
                    <button type="submit" class="btn btn-primary">Submit</button>
                    <br/><br/><br/>
                    <span id="successMessage"><b>${messages.success}</b></span>
                </form>
            </div>
        </div>

        <table border="1" class="table table-striped">
            <tr>
                <th>Primary</th>
                <th>Secondary</th>
            </tr>

            <c:forEach items="${preferences}" var="pref">
                <tr>
                    <td><c:out value="${pref.getPrimaryGenre()}"/></td>
                    <td><c:out value="${pref.getSecondaryGenre()}"/></td>
                </tr>
            </c:forEach>

        </table>
    </jsp:body>
</t:genericpage>
