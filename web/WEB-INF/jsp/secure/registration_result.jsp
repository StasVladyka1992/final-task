<%--
  Created by IntelliJ IDEA.
  User: NoteBook
  Date: 19.02.2019
  Time: 19:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../constant_part/navbar.jsp" %>

<fmt:message bundle="${loc}" key="registrationResult" var="registrationResult"/>
<fmt:message bundle="${loc}" key="userMessageBeforeTable" var="userMessageBeforeTable"/>
<fmt:message bundle="${loc}" key="userMessageAfterTable" var="userMessageAfterTable"/>
<fmt:message bundle="${loc}" key="firstName" var="firstName"/>
<fmt:message bundle="${loc}" key="lastName" var="lastName"/>
<fmt:message bundle="${loc}" key="email" var="email"/>
<fmt:message bundle="${loc}" key="startWork" var="startWork"/>

<h3>
    <c:out value="${registrationResult}"/>
</h3>
</br>
<c:out value="${userMessageBeforeTable}"></c:out>
<div class="table-responsive-md col-sm-10">
    <table class="table col-sm-10 table-bordered">
        <thead>
        <tr>
            <th>${firstName}</th>
            <th>${lastName}</th>
            <th>${email}</th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td><c:out value="${user.firstName}"></c:out></td>
            <td><c:out value="${user.lastName}"></c:out></td>
            <td><c:out value="${user.email}"></c:out></td>
        </tr>
        </tbody>
    </table>
</div>
    <c:out value="${userMessageAfterTable}"></c:out>
    <a href="/secure?command=go_to_authorized_user_main_page"/>${startWork}</a>
<div class="container-fluid fixed-bottom" id="footer">
    <%@ include file="../constant_part/footer.jsp"%>
</div>

