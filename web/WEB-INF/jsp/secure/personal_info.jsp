<%--
  Created by IntelliJ IDEA.
  User: NoteBook
  Date: 19.03.2019
  Time: 12:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../constant_part/navbar.jsp" %>
<fmt:message bundle="${loc}" key="alter" var="alter"/>
<fmt:message bundle="${loc}" key="yes" var="yes"/>
<fmt:message bundle="${loc}" key="no" var="no"/>
<fmt:message bundle="${loc}" key="back" var="back"/>
<fmt:message bundle="${loc}" key="editUserInfo" var="editUserInfo"/>
<fmt:message bundle="${loc}" key="firstName" var="firstName"/>
<fmt:message bundle="${loc}" key="lastName" var="lastName"/>
<fmt:message bundle="${loc}" key="email" var="email"/>
<fmt:message bundle="${loc}" key="phone" var="lastName"/>
<fmt:message bundle="${loc}" key="password" var="password"/>
<fmt:message bundle="${loc}" key="incorrectPassword" var="incorrectPassword"/>
<fmt:message bundle="${loc}" key="incorrectEmail" var="incorrectEmail"/>
<fmt:message bundle="${loc}" key="incorrectUserName" var="incorrectUserName"/>
<fmt:message bundle="${loc}" key="incorrectPhone" var="incorrectPhone"/>
<fmt:message bundle="${loc}" key="changePassword" var="changePassword"/>
<fmt:message bundle="${loc}" key="goToAuthorizedUserMain" var="goToAuthorizedUserMain"/>

<div class="container-fluid">
    <div class="container-fluid col-sm-8 mb-3 mt-5">
        <form class="border border-secondary pl-5 pr-5 pt-3 pb-3" action="/secure?command=update_personal_info"
              method="post">
            <h3 class="text-center">${editUserInfo}</h3>
            <div class="form-row">
                <div class="col-sm-6 mb-3">
                    <label for="firstName">${firstName}</label>
                    <input type="text" name="firstName" class="form-control form-control-sm" id="firstName"
                           value="${sessionScope.get('user').firstName}"
                           required>
                </div>
                <div class="col-sm-6 mb-3">
                    <label for="lastName">${lastName}</label>
                    <input type="text" class="form-control form-control-sm" id="lastName"
                           name="lastName" value="${sessionScope.get('user').lastName}" required>
                </div>
            </div>
            <div class="form-row">
                <div class="col-sm-6 mb-3">
                    <label for="email">${email}</label>
                    <input type="text" name="email" class="form-control form-control-sm" id="email"
                           value="${sessionScope.get('user').email}"
                           required>
                </div>
                <div class="col-sm-6 mb-3">
                    <label for="phone">${lastName}</label>
                    <input type="text" class="form-control form-control-sm" id="phone"
                           name="phone" value="${sessionScope.get('user').phone}" required>
                </div>
            </div>
            <button class="btn btn-sm btn-primary mr-2" type="submit">${alter}</button>
        </form>
    </div>
    <c:if test="${not empty param.incorrectUserName}">
        <p class="text-danger">
            <c:out value="${incorrectUserName}"/>
        </p>
    </c:if>
    <c:if test="${not empty param.incorrectEmail}">
        <p class="text-danger">
            <c:out value="${incorrectEmail}"/>
        </p>
    </c:if>
    <c:if test="${not empty param.incorrectPassword}">
        <p class="text-danger">
            <c:out value="${incorrectPassword}"/>
        </p>
    </c:if>
    <c:if test="${not empty param.incorrectPhone}">
        <p class="text-danger">
            <c:out value="${incorrectPhone}"/>
        </p>
    </c:if>
    <div class="d-flex justify-content-start">
        <a href="/secure?command=go_to_authorized_user_main_page">${goToAuthorizedUserMain}</a>
    </div>
</div>
<div class="container-fluid fixed-bottom" id="footer">
    <%@ include file="../constant_part/footer.jsp" %>
</div>