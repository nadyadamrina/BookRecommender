<%--
  Created by IntelliJ IDEA.
  User: nadiiadamrina
  Date: 11/18/21
  Time: 10:35 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>


<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<t:genericpage title="Find Recommendations">
  <jsp:attribute name="header">
    <h1>Recommendations for User with Username: ${fn:escapeXml(param.username)}</h1>
  </jsp:attribute>
    <jsp:body>
        <table border="1" class="table table-striped mt-5">
            <tr>
                <th>ISBN</th>
                <th>Title</th>
                <th>Authors</th>
            </tr>

            <c:forEach items="${recommendations}" var="rec" varStatus="status">

                <tr>
                    <td><c:out value="${rec.getIsbn()}"/></td>
                    <td>${books[status.index].getTitle()}</td>
                    <td>${authors[status.index].getFirstName().concat(" ").concat(authors[status.index].getLastName())}</td>
                </tr>

            </c:forEach>
        </table>
    </jsp:body>
</t:genericpage>
