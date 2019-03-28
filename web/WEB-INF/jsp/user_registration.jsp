<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="constant_part/navbar.jsp" %>

<fmt:message bundle="${loc}" key="email" var="email"/>
<fmt:message bundle="${loc}" key="firstName" var="remedyName"/>
<fmt:message bundle="${loc}" key="lastName" var="description"/>
<fmt:message bundle="${loc}" key="phone" var="phone"/>
<fmt:message bundle="${loc}" key="password" var="password"/>
<fmt:message bundle="${loc}" key="submit" var="submit"/>
<fmt:message bundle="${loc}" key="type" var="type"/>
<fmt:message bundle="${loc}" key="registrationForm" var="registrationForm"/>
<fmt:message bundle="${loc}" key="doctor" var="doctor"/>
<fmt:message bundle="${loc}" key="pharmacist" var="pharmacist"/>
<fmt:message bundle="${loc}" key="client" var="client"/>
<fmt:message bundle="${loc}" key="userExist" var="userExist"/>
<fmt:message bundle="${loc}" key="incorrectPassword" var="incorrectPassword"/>
<fmt:message bundle="${loc}" key="incorrectEmail" var="incorrectEmail"/>
<fmt:message bundle="${loc}" key="incorrectUserName" var="incorrectUserName"/>
<fmt:message bundle="${loc}" key="incorrectPhone" var="incorrectPhone"/>
<fmt:message bundle="${loc}" key="incorrectRole" var="incorrectRole"/>
<fmt:message bundle="${loc}" key="emailContentMessage" var="emailContentMessage"/>
<fmt:message bundle="${loc}" key="nameContentMessage" var="nameContentMessage"/>
<fmt:message bundle="${loc}" key="surnameContentMessage" var="surnameContentMessage"/>
<fmt:message bundle="${loc}" key="passwordContentMessage" var="passwordContentMessage"/>



<div class="container-fluid mt-2 mb-2">
    <div class="card col-6 m-auto">
        <div class="card-body">
            <h4 class="card-title text-center">${registrationForm}</h4>
            <form action="/index?command=registration" method="post">
                <div class="form-group">
                    <label for="email">${email}</label>
                    <input type="text" id="email" name="email" class="form-control" aria-describedby="emailHelpBlock" placeholder="newemail@mail.ru"
                           required maxlength="40"/>
                    <small id="emailHelpBlock" class="form-text text-muted">
                        ${emailContentMessage}
                    </small>
                </div>
                <div class="form-group">
                    <label for="password">${password}:</label>
                    <input type="password" id="password" name="password" class="form-control" aria-describedby="passwordHelpBlock" placeholder="${password}"
                           required maxlength="30">
                    <small id="passwordHelpBlock" class="form-text text-muted">
                        ${passwordContentMessage}
                    </small>
                </div>

                <div class="form-group">
                    <label for="firstName">${remedyName}</label>
                    <input type="text" id="firstName" name="firstName" class="form-control" aria-describedby="firstNameHelpBlock" placeholder="${remedyName}"
                           required maxlength="30">
                    <small id="firstNameHelpBlock" class="form-text text-muted">
                        ${nameContentMessage}
                    </small>
                </div>
                <div class="form-group">
                    <label for="lastName">${description}</label>
                    <input type="text" id="lastName" name="lastName" class="form-control" aria-describedby="lastNameHelpBlock" placeholder="${description}"
                           required maxlength="30">
                    <small id="lastNameHelpBlock" class="form-text text-muted">
                        ${surnameContentMessage}
                    </small>
                </div>
                <div class="form-group">
                    <label for="phone">${phone}</label>
                    <input type="text" id="phone" name="phone" class="form-control" placeholder="+375-29-111-11-11"
                           required maxlength="17">
                </div>
                <div class="form-group">
                    <label for="type">${type}</label>
                    <select class="form-control" id="type" name="role">
                        <option value="PHARMACIST">${pharmacist} </option>
                        <option value="CLIENT">${client}</option>
                        <option value="DOCTOR">${doctor}</option>
                    </select>
                </div>
                <div class="form-group">
                    <input type="submit" class="btn btn-success" value="${submit}">
                </div>
            </form>
            <c:if test="${not empty param.userExist}">
                <p class="text-danger">
                    <c:out value="${userExist}"/>
                </p>
            </c:if>
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
            <c:if test="${not empty param.incorrectRole}">
                <p class="text-danger">
                    <c:out value="${incorrectRole}"/>
                </p>
            </c:if>
        </div>
    </div>
</div>
<div class="container-fluid" id="footer">
    <%@ include file="constant_part/footer.jsp" %>
</div>
