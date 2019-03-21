<%--
  Created by IntelliJ IDEA.
  User: NoteBook
  Date: 21.03.2019
  Time: 15:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../../constant_part/navbar.jsp" %>
<fmt:message bundle="${loc}" key="orderCreated" var="orderCreated"/>
<fmt:message bundle="${loc}" key="orderError" var="orderError"/>

<c:if test="${param.operationResult.equals('true')}">
    <c:out value="${orderCreated}"/>
</c:if>
<c:if test="${param.operationResult.equals('fail')}">
    <c:out value="${orderError}"/>
</c:if>

<div class="container-fluid fixed-bottom" id="footer">
    <%@ include file="../../constant_part/footer.jsp" %>
</div>
