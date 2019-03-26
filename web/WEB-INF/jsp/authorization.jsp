<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="constant_part/navbar.jsp" %>

<fmt:message bundle="${loc}" key="email" var="email"/>
<fmt:message bundle="${loc}" key="password" var="password"/>
<fmt:message bundle="${loc}" key="submit" var="submit"/>
<fmt:message bundle="${loc}" key="remember" var="remember"/>
<fmt:message bundle="${loc}" key="error" var="error"/>
<fmt:message bundle="${loc}" key="sessionExpired" var="sessionExpired"/>

<div class="container-fluid">
    <form action="/index?command=authorization" method="post">
        <div class="form-group w-25">
            <label for="email">${email}</label>
            <input type="text" class="form-control" id="email" name="email" required maxlength="40">
        </div>
        <div class="form-group w-25">
            <label for="pwd">${password}</label>
            <input type="password" class="form-control" id="pwd" name="password" required maxlength="30">
        </div>
        <input type="submit" class="btn btn-primary" value="${submit}">
    </form>
    <c:if test="${param.get('commandStatus').equals('user not found')}">
        <p class="text-danger">${error}</p>
    </c:if>
    <c:if test="${param.get('sessionExpired').equals('true')}">
        <p class="text-danger">
            <c:out value="${sessionExpired}"/>
        </p>
    </c:if>
</div>
<div class="container-fluid fixed-bottom" id="footer">
    <%@ include file="constant_part/footer.jsp" %>
</div>

