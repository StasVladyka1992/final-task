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
<fmt:message bundle="${loc}" key="goToRemedySearching" var="goToRemedySearching"/>

<c:if test="${param.operationResult.equals('success')}">
    <c:out value="${orderCreated}"/>
</c:if>
<c:if test="${param.operationResult.equals('fail')}">
    <c:out value="${orderError}"/>
</c:if>

<div class="d-flex container-fluid justify-content-start">
    <a href="/secure?command=go_to_client_searching_page">${goToRemedySearching}</a>
</div>

<div class="container-fluid fixed-bottom" id="footer">
    <%@ include file="../../constant_part/footer.jsp" %>
</div>
