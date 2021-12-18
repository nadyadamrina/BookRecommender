<%--
  Created by IntelliJ IDEA.
  User: nadiiadamrina
  Date: 11/18/21
  Time: 10:23 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<t:genericpage title="Delete a User">
  <jsp:attribute name="header">
    <h1>${messages.title}</h1>
  </jsp:attribute>
    <jsp:body>
        <div class="row justify-content-start">
            <div class="col-4">
                <form action="deleteuser" method="post">
                    <div class="mb-3">
                        <div <c:if test="${messages.disableSubmit}">style="display:none"</c:if>>
                            <label for="username" class="form-label">UserName</label>
                            <input id="username" name="username"
                                   value="${fn:escapeXml(param.username)}" class="form-control">
                        </div>
                    </div>
                    <span id="submitButton"
                          <c:if test="${messages.disableSubmit}">style="display:none"</c:if>>
                        <button type="submit" class="btn btn-primary">Submit</button>
                  </span>
                </form>
            </div>
        </div>
    </jsp:body>
</t:genericpage>
